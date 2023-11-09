package wbdsoap.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class HelloService {
    @WebMethod
    @WebResult(name = "message")
    public String hello(@WebParam(name = "message") String message){
        return "Hello, your message is " + message;
    }
}
