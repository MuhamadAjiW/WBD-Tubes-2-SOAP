package wbdsoap.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import wbdsoap.models.LogEntity;
import wbdsoap.models.SubscriptionEntity;
import wbdsoap.test.TestEntity;

import java.util.Properties;

public class HibernateUtil {
    private static final String DB_HOST = Dotenv.load().get("MYSQL_HOST", "localhost");
    private static final String DB_PORT = Dotenv.load().get("MYSQL_PORT", "8033");
    private static final String DB_NAME = Dotenv.load().get("MYSQL_DATABASE", "soap_db");
    private static final String DB_USER = Dotenv.load().get("MYSQL_USER", "root");
    private static final String DB_PASS = Dotenv.load().get("MYSQL_PASSWORD", "");

    private static final String DB_URL = String.format("jdbc:mysql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME);

    private static SessionFactory sessionFactory = createSessionFactory();
    private static SessionFactory createSessionFactory(){
        System.out.println("DBConfig is: " + DB_URL);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", HibernateUtil.DB_URL);
        properties.setProperty("hibernate.connection.username", HibernateUtil.DB_USER);
        properties.setProperty("hibernate.connection.password", HibernateUtil.DB_PASS);
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.current_session_context_class", "thread");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(TestEntity.class);
        configuration.addAnnotatedClass(LogEntity.class);
        configuration.addAnnotatedClass(SubscriptionEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
