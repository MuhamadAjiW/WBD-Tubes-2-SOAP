package wbdsoap;

import wbdsoap.services.HelloService;
import wbdsoap.utils.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

public class Main implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Server is running");

//        HibernateUtil.getSessionFactory();

        Endpoint endpoint = Endpoint.create(new HelloService());
        endpoint.publish("http://0.0.0.0:8080/test");
    }
}
