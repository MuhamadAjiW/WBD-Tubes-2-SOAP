package wbdsoap.services;

import wbdsoap.interfaces.HelloServiceInterface;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class HelloService implements HelloServiceInterface {
    @WebResult(name = "String")
    public String hello(@WebParam(name = "message") String message){
        return "Hello, your message is " + message;
    }
}
