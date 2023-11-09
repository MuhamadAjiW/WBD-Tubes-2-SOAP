package wbdsoap.models;

import javax.persistence.*;

@Entity
@Table(name = "TestTable")
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