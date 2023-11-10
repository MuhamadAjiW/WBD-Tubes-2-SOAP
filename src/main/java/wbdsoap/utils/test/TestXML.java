package wbdsoap.utils.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Delete when done
@XmlRootElement
public class TestXML<T> {
    @XmlElement
    private String message;
    @XmlElement
    private boolean valid;
    @XmlElement
    private T[] data;
    public TestXML(){}
    public TestXML(T[] data){
        this.message = "Fak";
        this.valid = true;
        this.data = data;
    }
}
