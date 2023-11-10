package wbdsoap.utils.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenericSOAPResponse<T> extends SOAPResponse {
    @XmlElement
    private T data;
    //IMPORTANT: data cannot be array for whatever reason, if you need it to be array, extend BasicSOAPResponse

    public GenericSOAPResponse(){
        super();
        this.data = null;
    }
    public GenericSOAPResponse(String message, boolean valid, T data){
        super(message, valid);
        this.data = data;
    }
}
