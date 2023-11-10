package wbdsoap.utils.others;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.List;

public class SOAPEndpoint{
    private final Endpoint endpoint;
    private String address;

    public SOAPEndpoint(String address, Endpoint endpoint){
        this.endpoint = endpoint;
        this.address = address;
    }

    public SOAPEndpoint(Endpoint endpoint){
        this.endpoint = endpoint;
    }

    public String getAddress() {
        return address;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void addMiddleware(SOAPHandler<SOAPMessageContext> middleware){
        List<Handler> handlerchain = endpoint.getBinding().getHandlerChain();
        handlerchain.add(middleware);
        endpoint.getBinding().setHandlerChain(handlerchain);
    }

    public void publish(){
        endpoint.publish(address);
    }

    public void publish(String address){
        this.address = address;
        endpoint.publish(address);
    }
}
