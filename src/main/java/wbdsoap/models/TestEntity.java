package wbdsoap.models;

import wbdsoap.utils.HibernateUtil;

import javax.persistence.*;

// TODO: Delete when done
@Entity
@Table(name = "test_table")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int Id;

    @Column(nullable = false)
    private String description;

    public TestEntity() {
        description = "";
    }
    public TestEntity(String description){
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
