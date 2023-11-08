package wbdsoap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestServiceInterface {
    @WebMethod
    public String insertData(String description);
}
