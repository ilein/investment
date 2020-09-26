package com.gdtest.investment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gdtest.investment.model.enums.LegalFormEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_client")
public class Client implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OBJECT")
    @SequenceGenerator(sequenceName = "seq_object", allocationSize = 1, name = "SEQ_OBJECT")
    private Integer id;

    @Column(name = "name", nullable = true, length = 1024)
    private String name;

    @Column(name = "short_name", nullable = true, length = 256)
    private String shortName;

    @Column(name = "address", nullable = true, length = 9)
    private String address;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "legal_form_id", nullable = true, length = 9)
    private LegalFormEnum legalForm;

    @OneToMany(mappedBy = "client")
    private List<Investment> investments;

    public Client() {
    }

    public Client(String name, String shortName, String address, LegalFormEnum legalForm) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.legalForm = legalForm;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LegalFormEnum getLegalForm() {
        return this.legalForm;
    }

    public void setLegalForm(LegalFormEnum legalFormId) {
        this.legalForm = legalFormId;
    }

    @JsonIgnore
    public String getLegalFormName() {
        return this.legalForm != null
                ? this.legalForm.getLegalFormName()
                : null;
    }

    public String toString() {
        return "id="+this.id.toString()+";"+
                "name="+this.name+";"+
                "shortName="+this.shortName+";"+
                "address="+this.address+";"+
                "legalForm="+this.legalForm.getLegalFormName()+";";
    }
}
