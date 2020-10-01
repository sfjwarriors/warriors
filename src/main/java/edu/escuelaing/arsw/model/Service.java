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
    @Column(name = "fk_store_service")
    private long fk_store_service;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_services_cart")
    private List<Cart> carts;

    public Service(String name, String description, long price, String status, long fk_store_service) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.fk_store_service = fk_store_service;
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

    public long getFk_store_service() {
        return fk_store_service;
    }

    public void setFk_store_service(long fk_store_service) {
        this.fk_store_service = fk_store_service;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", fk_store_service=" + fk_store_service +
                ", carts=" + carts +
                '}';
    }
}
