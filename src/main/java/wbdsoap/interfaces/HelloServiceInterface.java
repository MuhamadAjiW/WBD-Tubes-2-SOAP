package wbdsoap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface HelloServiceInterface {
    @WebMethod
    public String hello(String message);
}
