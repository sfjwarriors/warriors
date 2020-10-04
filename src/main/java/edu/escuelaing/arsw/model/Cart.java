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
    private long fkProductCart;
    @Column
    private long fkOrderCart;
    @Column
    private long fkServicesCart;

    public Cart() {
    }
}
