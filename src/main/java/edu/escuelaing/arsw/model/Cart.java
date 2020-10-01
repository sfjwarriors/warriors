package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cart", schema = "public")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private long fk_product_cart;
    @Column
    private long fk_order_cart;
    @Column
    private long fk_services_cart;

    public Cart() {
    }
}
