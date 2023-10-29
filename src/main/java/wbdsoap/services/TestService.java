package wbdsoap.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.http.HttpServlet;
import wbdsoap.daos.TestDAO;
import wbdsoap.models.TestEntity;
import wbdsoap.utils.HibernateUtil;

@WebService(endpointInterface = "Test")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class TestService extends HttpServlet {
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
