package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "public")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 25,unique = true, updatable = true)
    private String name;
    @Column(length = 25, updatable = true)
    private String descrtiption;
    
    @Column(length = 25, updatable = true)
    private long price;
    @Column(name = "image")
    private String image;
    
    @Column(length = 25, updatable = true)
    private String status;

    public Product() {
    }

    public Product(long id, String name, String descrtiption, long price, String image, String status) {
        this.id = id;
        this.name = name;
        this.descrtiption = descrtiption;
        this.price = price;
        this.image = image;
        this.status = status;
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

    public String getDescrtiption() {
        return descrtiption;
    }

    public void setDescrtiption(String descrtiption) {
        this.descrtiption = descrtiption;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
