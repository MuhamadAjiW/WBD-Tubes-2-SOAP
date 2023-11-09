package wbdsoap.middlewares;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class Logger implements SOAPHandler<SOAPMessageContext> {
    private void insertLog(SOAPMessageContext soapMessage) throws SOAPException {
            System.out.println("Instruction received");
            System.out.println(soapMessage.getMessage().getSOAPBody().toString());
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            insertLog(context);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to log instruction");
            return false;
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        try {
            insertLog(context);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to log instruction");
            return false;
        }
    }

    @Override
    public void close(MessageContext context) {}
}
