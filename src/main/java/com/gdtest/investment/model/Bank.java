package com.gdtest.investment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_bank")
public class Bank implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OBJECT")
    @SequenceGenerator(sequenceName = "seq_object", allocationSize = 1, name = "SEQ_OBJECT")
    private Integer id;

    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Column(name = "bic", nullable = true, length = 9)
    private String bic;

    @OneToMany(mappedBy = "bank")
    private List<Investment> investments;

    public Bank() {
    }

    public Bank(String name, String bic) {
        this.name = name;
        this.bic = bic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String toString() {
        return "id="+this.id.toString()+";"+
                "name="+this.name+";"+
                "bic="+this.bic+";";
    }
}