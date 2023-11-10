package wbdsoap.utils.responses;

import wbdsoap.models.SubscriptionEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArraySubsResponse extends BasicSOAPResponse{
    @XmlElement
    private SubscriptionEntity[] data;

    public ArraySubsResponse(){
        super();
        this.data = null;
    }
    public ArraySubsResponse(String message, boolean valid, SubscriptionEntity[] data){
        super(message, valid);
        this.data = data;
    }
}
