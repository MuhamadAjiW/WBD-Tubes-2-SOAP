package wbdsoap.services;

import wbdsoap.daos.TestDAO;
import wbdsoap.models.TestEntity;
import wbdsoap.utils.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TestService {
    private final TestDAO testDAO;

    public TestService(){
        testDAO = new TestDAO(HibernateUtil.getSessionFactory());
    }

    @WebMethod
    @WebResult(name = "message")
    public String insertData(@WebParam(name = "description") String description){
        TestEntity testData = new TestEntity(description);
        testDAO.insertData(testData);
        return "Data insertion success";
    }
}
