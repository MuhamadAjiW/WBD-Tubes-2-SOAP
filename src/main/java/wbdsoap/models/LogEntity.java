package wbdsoap.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String endpoint;

    @Lob
    @Column(nullable = false)
    private String description;

    public LogEntity(){}
    public LogEntity(String ip, String endpoint, String description, Instant timestamp){
        this.ip = ip;
        this.endpoint = endpoint;
        this.description = description;
        this.timestamp = timestamp;
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

    public Instant setTimestamp(Instant timestamp){
        this.timestamp = timestamp;
        return this.timestamp;
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
