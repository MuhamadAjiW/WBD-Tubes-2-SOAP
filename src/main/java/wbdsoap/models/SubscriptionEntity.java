package wbdsoap.models;

import wbdsoap.enums.SubscriptionStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "subscription")
public class SubscriptionEntity implements Serializable {
    @Id
    @Column(nullable = false)
    @XmlElement
    public int user_id;

    @Id
    @Column(nullable = false)
    @XmlElement
    public int author_id;

    @Column(nullable = false)
    @XmlElement
    public SubscriptionStatus status;

    @Column(nullable = false)
    @XmlElement
    public Instant timestamp;

    public SubscriptionEntity() {
        this.user_id = 0;
        this.author_id = 0;
        this.status = SubscriptionStatus.ERROR;
        this.timestamp = Instant.now();
    }
    public SubscriptionEntity(int user_id, int author_id){
        this.user_id = user_id;
        this.author_id = author_id;
        this.status = SubscriptionStatus.PENDING;
        this.timestamp = Instant.now();
    }
    public void accept(){
        this.status = SubscriptionStatus.ACCEPTED;
    }

    public void reject(){
        this.status = SubscriptionStatus.REJECTED;
    }
}