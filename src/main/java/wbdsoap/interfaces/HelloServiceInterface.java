package wbdsoap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloServiceInterface {
    @WebMethod
    public String hello(String message);
}
