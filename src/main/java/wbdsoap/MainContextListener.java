package wbdsoap;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.xml.ws.Endpoint;
import wbdsoap.services.HelloService;


public class MainContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Server is running");
//        HibernateUtil.getSessionFactory();
        HelloService helloService = new HelloService();
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/wbdsoap-1.0/api/hello", helloService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}