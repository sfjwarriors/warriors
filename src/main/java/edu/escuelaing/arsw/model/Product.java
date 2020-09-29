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
    private String description;
    
    @Column(length = 25, updatable = true)
    private long price;
    @Column(name = "image")
    private String image;
    
    @Column(length = 25, updatable = true)
    private String status;

    public Product() {
    }

    public Product(String name, String description, long price, String image) {
       
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
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

    public void setDescrtiption(String description) {
        this.description = description;
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
