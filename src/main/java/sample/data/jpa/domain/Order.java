package sample.data.jpa.domain;

// Generated Apr 11, 2007 11:39:28 AM by Hibernate Tools 3.2.0.b9

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "card_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String orderName;

    private BigDecimal orderAmount;

    private String createUser;

    private Date createTimestamp;

    private String lastUpdateUser;

    private Date lastUpdateTimestamp;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    private String hashId;

    @OneToOne
    private Transaction transaction;

    @Version
    private int versionNumber;

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
