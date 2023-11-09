package wbdsoap.models;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private java.sql.Timestamp timestamp;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String endpoint;

    @Lob
    @Column(nullable = false)
    private String description;

    public LogEntity(){}
    public LogEntity(String ip, String endpoint, String description){
        this.ip = ip;
        this.endpoint = endpoint;
        this.description = description;
    }

    public String setIP(String ip){
        this.ip = ip;
        return this.ip;
    }

    public String setEndpoint(String endpoint){
        this.endpoint = endpoint;
        return this.endpoint;
    }

    public String setDescription(String description){
        this.description = description;
        return this.description;
    }

    public String getIp(){
        return this.ip;
    }

    public String getEndpoint(){
        return this.endpoint;
    }

    public String getDescription(){
        return this.description;
    }
}
