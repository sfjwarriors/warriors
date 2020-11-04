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
    @Column(unique = true)
    private long fkMechanic;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStore")
    private List<Orden> ordens;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStoreProduct")
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStoreService")
    private List<Servicio> servicios;

    public Store() {
    }

    public Store(String storeName, long fkMechanic) {
        this.storeName = storeName;
        this.fkMechanic = fkMechanic;
    }

    public Store(long id, String storeName, long fkMechanic, List<Orden> ordens, List<Product> products, List<Servicio> servicios) {
        this.id = id;
        this.storeName = storeName;
        this.fkMechanic = fkMechanic;
        this.ordens = ordens;
        this.products = products;
        this.servicios = servicios;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getFkMechanic() {
        return fkMechanic;
    }

    public void setFkMechanic(long fkMechanic) {
        this.fkMechanic = fkMechanic;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Orden> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<Orden> ordens) {
        this.ordens = ordens;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", fkMechanic=" + fkMechanic +
                ", ordens=" + ordens +
                ", products=" + products +
                ", servicios=" + servicios +
                '}';
    }
}
