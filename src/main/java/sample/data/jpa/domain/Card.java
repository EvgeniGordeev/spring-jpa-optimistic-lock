package sample.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author EvgeniGordeev
 * @since 2/27/2015.
 */
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String hashId;

    @Column(name = "account_id")
    private Integer accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
