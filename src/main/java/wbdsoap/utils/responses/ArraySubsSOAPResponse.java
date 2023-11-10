package wbdsoap.utils.responses;

import wbdsoap.models.SubscriptionEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArraySubsSOAPResponse extends SOAPResponse {
    @XmlElement
    private SubscriptionEntity[] data;

    public ArraySubsSOAPResponse(){
        super();
        this.data = null;
    }
    public ArraySubsSOAPResponse(String message, boolean valid, SubscriptionEntity[] data){
        super(message, valid);
        this.data = data;
    }
}
