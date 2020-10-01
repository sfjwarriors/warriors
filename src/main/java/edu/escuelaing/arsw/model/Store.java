package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="store", schema = "public")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 50, updatable = true)
    private String storeName;
    @Column
    private long fk_mecanico_store;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_mecanico")
    private List<Order> orders;


}
