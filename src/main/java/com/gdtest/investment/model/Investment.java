package com.gdtest.investment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_investment")
public class Investment implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OBJECT")
    @SequenceGenerator(sequenceName = "seq_object", allocationSize = 1, name = "SEQ_OBJECT")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName="id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bank_id", nullable = false, referencedColumnName="id")
    private Bank bank;

    @Column(name = "create_date", nullable = true, length = 9)
    private Date createDate;

    @Column(name = "perc", nullable = true, length = 9)
    private Integer percent;

    @Column(name = "term_months", nullable = true, length = 9)
    private Integer termMonths;

    protected Investment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getPercent() {
        return this.percent;
    }

    public void setPercent(Integer perc) {
        this.percent = perc;
    }

    public Integer getTermMonths() {
        return this.termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    @JsonIgnore
    public String getClientName() {
        return this.client != null
                ? this.client.getName()
                : null;
    }
}
