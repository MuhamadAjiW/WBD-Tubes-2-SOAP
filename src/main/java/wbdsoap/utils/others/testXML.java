package wbdsoap.utils.others;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Delete when done
@XmlRootElement
public class testXML<T> {
    @XmlElement
    private String message;
    @XmlElement
    private boolean valid;
    @XmlElement
    private T[] data;
    public testXML(){}
    public testXML(T[] data){
        this.message = "Fak";
        this.valid = true;
        this.data = data;
    }
}
