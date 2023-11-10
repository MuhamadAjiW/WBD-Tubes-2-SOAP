package wbdsoap.middlewares;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.ws.developer.JAXWSProperties;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import wbdsoap.daos.LogDAO;
import wbdsoap.models.LogEntity;
import wbdsoap.utils.HibernateUtil;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.time.Instant;
import java.util.Set;

public class Logger implements SOAPHandler<SOAPMessageContext> {
    private final LogDAO logDAO;

    public Logger(){
        logDAO = new LogDAO(HibernateUtil.getSessionFactory());
    }

    private void insertLog(SOAPMessageContext context) throws SOAPException {

        boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isResponse){
            System.out.println("Message is response, No logging required");
        } else{
            System.out.println("Message is request");

            HttpExchange httpExchange = (HttpExchange) context.get(JAXWSProperties.HTTP_EXCHANGE);
            SOAPMessage message = context.getMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            SOAPBody body = envelope.getBody();
            Node operation = body.getChildNodes().item(1);
            NodeList opArgs = operation.getChildNodes();

            LogEntity logEntry;

            String addrStr = httpExchange.getRemoteAddress().getHostString();
            String endpointStr = httpExchange.getRequestURI().getPath();
            StringBuilder desc = new StringBuilder(operation.getLocalName());
            System.out.println(opArgs.item(1).getLocalName());
            System.out.println(opArgs.item(1).getTextContent());

            int argLen = opArgs.getLength();
            desc.append("(");
            if(argLen > 2){
                int last = argLen - 2;
                for(int i = 1; i < last; i += 2){
                    desc.append(opArgs.item(i).getLocalName()).append(":").append(opArgs.item(i).getTextContent()).append(",");
                }
                desc.append(opArgs.item(last).getLocalName()).append(":").append(opArgs.item(last).getTextContent());
            }
            desc.append(")");

            System.out.println("Inserting into log");
            System.out.println("IP: " + addrStr);
            System.out.println("Endpoint: " + endpointStr);
            System.out.println("Operation: " + desc);

            logEntry = new LogEntity(addrStr, endpointStr, desc.toString(), Instant.now());

            logDAO.insertData(logEntry);
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            System.out.println("Logging message");

            insertLog(context);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to log instruction");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        try {
            System.out.println("Logging faults");

            insertLog(context);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to log instruction");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close(MessageContext context) {}
}
