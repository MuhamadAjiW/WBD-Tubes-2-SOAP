package wbdsoap.services;

import org.json.simple.JSONObject;
import wbdsoap.enums.HTTPMethod;
import wbdsoap.test.TestDAO;
import wbdsoap.test.TestEntity;
import wbdsoap.utils.HibernateUtil;
import wbdsoap.utils.RESTUtil;
import wbdsoap.utils.responses.HTTPResponse;

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
    public HTTPResponse someMethod(){
        JSONObject json = new JSONObject();
        json.put("email", "soap@example.com");
        json.put("username", "soap");
        json.put("password", "dummy_password");
        json.put("name", "dummy_password");
        json.put("bio", "dummy_bio");
        return RESTUtil.sendRequest("/api/authors", HTTPMethod.POST, json);
    }
}
