package basar.data;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Position implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, optional = false)
    private User seller;

    @Basic(optional = true)
    private Date timeOfPurchase;

    @Basic(optional = false)
    private Long price;

    @Basic(optional = true)
    private String description;

    @Basic(optional = true)
    private Boolean purchased;

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Date getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(Date timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

}
