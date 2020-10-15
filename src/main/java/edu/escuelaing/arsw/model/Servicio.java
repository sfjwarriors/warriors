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
@Table(name="servicio", schema = "public")
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 100, updatable = true)
    private String name;
    @Column(name = "image")
    private String image;
    @Column(length = 300, updatable = true)
    private String description;
    @Column(length = 25, updatable = true)
    private long price;
    @Column(length = 25, updatable = true)
    private String status;
    @Column(name = "fkStoreService")
    private long fkStoreService;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkServicesCart", nullable = true)
    private List<Cart> carts;

    public Servicio(){

    }

    public Servicio(String name, String image, String description, long price, String status, long fkStoreService) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.status = status;
        this.fkStoreService = fkStoreService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFkStoreService(long fkStoreService) {
        this.fkStoreService = fkStoreService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", fkStoreService=" + fkStoreService +
                ", carts=" + carts +
                '}';
    }
}
