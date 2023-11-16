package wbdsoap;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import wbdsoap.tasks.SubscriberDeletionTask;
import wbdsoap.utils.ServerUtil;
import wbdsoap.utils.others.SOAPEndpoint;
import wbdsoap.middlewares.LoggerMiddleware;
import wbdsoap.services.SubscriptionService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;
import java.util.Timer;

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

            SOAPEndpoint subscribeEndpoint = new SOAPEndpoint(Endpoint.create(new SubscriptionService()));
            subscribeEndpoint.addMiddleware(new LoggerMiddleware());
            subscribeEndpoint.publish("http://0.0.0.0:8080/api/subscribe");

            Timer timer = new Timer(true);
            long delay = 0;
            long period = ServerUtil.subDeletionRoutineTime;
            timer.scheduleAtFixedRate(new SubscriberDeletionTask(), delay, period);

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