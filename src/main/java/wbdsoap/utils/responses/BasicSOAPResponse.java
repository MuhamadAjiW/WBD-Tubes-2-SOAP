package wbdsoap.utils.responses;

import javax.xml.bind.annotation.XmlElement;

public class BasicSOAPResponse {
    @XmlElement
    protected String message;
    @XmlElement
    protected boolean valid;

    public BasicSOAPResponse(){
        this.message = "Invalid request";
        this.valid = false;
    }

    public BasicSOAPResponse(String message, boolean valid){
        this.message = message;
        this.valid = valid;
    }
}
