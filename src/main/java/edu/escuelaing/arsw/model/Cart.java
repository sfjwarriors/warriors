package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cart", schema = "public")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private Long fkProductCart;
    @Column(nullable = false)
    private Long fkOrderCart;
    @Column
    private Long fkServicesCart;

    public Cart() {
    }

    public Cart(Long fkProductCart, Long fkOrderCart, Long fkServicesCart) {
        this.fkProductCart = fkProductCart;
        this.fkOrderCart = fkOrderCart;
        this.fkServicesCart = fkServicesCart;
    }

    public Cart(Long id, Long fkProductCart, Long fkOrderCart, Long fkServicesCart) {
        this.id = id;
        this.fkProductCart = fkProductCart;
        this.fkOrderCart = fkOrderCart;
        this.fkServicesCart = fkServicesCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkProductCart() {
        return fkProductCart;
    }

    public void setFkProductCart(Long fkProductCart) {
        this.fkProductCart = fkProductCart;
    }

    public Long getFkOrderCart() {
        return fkOrderCart;
    }

    public void setFkOrderCart(Long fkOrderCart) {
        this.fkOrderCart = fkOrderCart;
    }

    public Long getFkServicesCart() {
        return fkServicesCart;
    }

    public void setFkServicesCart(Long fkServicesCart) {
        this.fkServicesCart = fkServicesCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", fkProductCart=" + fkProductCart +
                ", fkOrderCart=" + fkOrderCart +
                ", fkServicesCart=" + fkServicesCart +
                '}';
    }
}
