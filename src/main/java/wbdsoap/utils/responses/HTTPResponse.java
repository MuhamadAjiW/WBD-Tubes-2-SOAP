package wbdsoap.utils.responses;

import javax.xml.bind.annotation.XmlElement;

public class HTTPResponse {
    @XmlElement
    public Integer statusCode;

    @XmlElement
    public String statusMessage;

    @XmlElement
    public String contents;

    public HTTPResponse(){
        this.statusCode = 500;
        this.statusMessage = "Internal Error";
        this.contents = null;
    }

    public HTTPResponse(Integer statusCode, String statusMessage, String contents){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return this.statusCode + " " + statusMessage + "\n"
                + contents;
    }
}
