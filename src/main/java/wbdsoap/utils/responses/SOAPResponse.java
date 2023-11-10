package wbdsoap.utils.responses;

import javax.xml.bind.annotation.XmlElement;

public class SOAPResponse {
    @XmlElement
    protected String message;
    @XmlElement
    protected boolean valid;

    public SOAPResponse(){
        this.message = "Invalid request";
        this.valid = false;
    }

    public SOAPResponse(String message, boolean valid){
        this.message = message;
        this.valid = valid;
    }
}
