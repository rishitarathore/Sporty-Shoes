package org.simplilearn.workshop.model;
import javax.persistence.*;



import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    
    
    @ManyToOne( targetEntity=User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	public User user;
    
    private LocalDate date;
    
    private BigDecimal grossTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(BigDecimal grossTotal) {
        this.grossTotal = grossTotal;
    }
}
