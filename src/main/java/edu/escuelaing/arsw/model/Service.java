/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rojas
 */
@Entity
@Table(name="services", schema = "public")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idService;
    @Column(length = 100,unique = true, updatable = true)
    private String name;
    @Column(length = 300, updatable = true)
    private String description;
    @Column(length = 25, updatable = true)
    private long price;
    @Column(length = 25, updatable = true)
    private String status;
    @Column(name = "fkStoreService")
    private long fkStoreService;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkServicesCart")
    private List<Cart> carts;

    public Service(String name, String description, long price, String status, long fkStoreService) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.fkStoreService = fkStoreService;
    }

    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getFkStoreService() {
        return fkStoreService;
    }

    public void setFkStoreService(long fkStoreService) {
        this.fkStoreService = fkStoreService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", fkStoreService=" + fkStoreService +
                ", carts=" + carts +
                '}';
    }
}
