package wbdsoap.services;

import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import wbdsoap.interfaces.HelloServiceInterface;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class HelloService implements HelloServiceInterface {
    @WebResult(name = "String")
    public String hello(@WebParam(name = "message") String message){
        return "Hello, your message is " + message;
    }
}
