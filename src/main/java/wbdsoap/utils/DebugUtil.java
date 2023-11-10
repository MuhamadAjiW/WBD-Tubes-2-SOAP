package wbdsoap.utils;

import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;

public class DebugUtil {
    private static void printMessage(SOAPMessage message){
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            message.writeTo(out);
            String messageString = out.toString();
            System.out.println(messageString);
        } catch (Exception e){
            System.out.println("Message printing failed");
            e.printStackTrace();
        }
    }
}
