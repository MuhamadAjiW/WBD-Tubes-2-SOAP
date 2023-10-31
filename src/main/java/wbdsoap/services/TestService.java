package wbdsoap.services;

import wbdsoap.daos.TestDAO;
import wbdsoap.models.TestEntity;
import wbdsoap.utils.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(endpointInterface = "Test")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class TestService {
    private TestDAO testDAO;

    public TestService(){
        testDAO = new TestDAO(HibernateUtil.getSessionFactory());
    }

    @WebMethod
    public void InsertData(String description){
        TestEntity testData = new TestEntity(description);
        testDAO.insertData(testData);
    }
}
