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
    private long fkMechanic;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStore")
    private List<Order> orders;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStoreProduct")
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkStoreService")
    private List<Service> services;

    public Store(String storeName, long fkMechanic) {
        this.storeName = storeName;
        this.fkMechanic = fkMechanic;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
