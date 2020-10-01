package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product", schema = "public")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 25, unique = true, updatable = true)
    private String name;
    @Column(length = 250, updatable = true)
    private String description;
    @Column(length = 25, updatable = true)
    private long price;
    @Column(name = "image")
    private String image;
    @Column(length = 25, updatable = true)
    private String status;
    @Column(name = "fk_store_product")
    private long fk_store_product;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_cart")
    private List<Cart> carts;

    public Product() {
    }

    public Product(String name, String description, long price, String image, String status, long fk_store_product) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.status = status;
        this.fk_store_product = fk_store_product;
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

    public long getFk_store_product() {
        return fk_store_product;
    }

    public void setFk_store_product(long fk_store_product) {
        this.fk_store_product = fk_store_product;
    }
}
