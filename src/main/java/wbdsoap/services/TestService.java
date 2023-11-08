package wbdsoap.services;

import wbdsoap.daos.TestDAO;
import wbdsoap.interfaces.TestServiceInterface;
import wbdsoap.models.TestEntity;
import wbdsoap.utils.HibernateUtil;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TestService implements TestServiceInterface {
    private TestDAO testDAO;

    public TestService(){
        testDAO = new TestDAO(HibernateUtil.getSessionFactory());
    }

    @WebResult(name = "message")
    public String insertData(@WebParam(name = "description") String description){
        TestEntity testData = new TestEntity(description);
        testDAO.insertData(testData);
        return "Data insertion success";
    }
}
