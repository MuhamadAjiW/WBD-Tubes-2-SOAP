package wbdsoap;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import wbdsoap.classes.SOAPEndpoint;
import wbdsoap.middlewares.Logger;
import wbdsoap.services.HelloService;
import wbdsoap.services.TestService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

public class Main implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            try{
                AbandonedConnectionCleanupThread.checkedShutdown();
            } catch (Exception e){
                System.out.println("Mysql failed to initialize");
                e.printStackTrace();
            }
            SOAPEndpoint helloEndpoint = new SOAPEndpoint(Endpoint.create(new HelloService()));
            helloEndpoint.addMiddleware(new Logger());
            helloEndpoint.publish("http://0.0.0.0:8080/hello");

            SOAPEndpoint testEndpoint = new SOAPEndpoint(Endpoint.create(new TestService()));
            testEndpoint.addMiddleware(new Logger());
            testEndpoint.publish("http://0.0.0.0:8080/test");

            System.out.println("Server is running");
        } catch (Exception e){
            System.out.println("Server error");
            e.printStackTrace();
        }
    }
}

//For local testing
//public class Main {
//    public static void main(String[] args) {
//        try {
//            try{
//                AbandonedConnectionCleanupThread.checkedShutdown();
//            } catch (Exception e){
//                System.out.println("Mysql failed to initialize");
//                e.printStackTrace();
//            }
//            Endpoint.publish("http://0.0.0.0:8080/hello", new HelloService());
//            Endpoint.publish("http://0.0.0.0:8080/test", new TestService());
//            System.out.println("Server successfully started");
//
//        } catch (Exception e){
//            System.out.println("Server error");
//        }
//    }
//}