package wbdsoap.utils.test;

import wbdsoap.models.SubscriptionEntity;
import wbdsoap.utils.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


// TODO: Delete when done
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TestService {
    private final TestDAO testDAO;

    public TestService(){
        testDAO = new TestDAO(HibernateUtil.getSessionFactory());
    }

    @WebMethod
    public String hello(@WebParam(name = "message") String message){
        return "Hello, your message is " + message;
    }

    @WebMethod
    public String insertData(@WebParam(name = "description") String description){
        TestEntity testData = new TestEntity(description);
        testDAO.insertData(testData);
        return "Data insertion success";
    }


    @WebMethod
    public TestXML<SubscriptionEntity> getData(){
        return new TestXML<>(new SubscriptionEntity[]{new SubscriptionEntity(), new SubscriptionEntity()});
    }
}