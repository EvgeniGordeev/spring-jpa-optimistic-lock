package sample.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonView;
import sample.data.jpa.web.View;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author EvgeniGordeev
 * @since 2/27/2015.
 */
@Entity
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(View.Summary.class)
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Order order;

    private BigDecimal txAmount;

    private Date startTimestamp;

    private Date endTimestamp;
    @JsonView(View.Summary.class)
    private BigDecimal presetAmount;

    private String deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public BigDecimal getPresetAmount() {
        return presetAmount;
    }

    public void setPresetAmount(BigDecimal presetAmount) {
        this.presetAmount = presetAmount;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
