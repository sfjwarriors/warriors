/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rojas
 */
@Entity
@Table(name="orders", schema = "public")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idOrder;
        
    @Column
    private Date date;
    
    @Column (name = "delivery_date")
    private Date deliveryDate;
    
    @Column (name = "total_value")
    private int totalValue;
    
    @Column
    private String status;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Order_Services")
    private List<Service> services;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Order_Product")
    private List<Product> products;

    public long getIdOrder() {
        return idOrder;
    }

   
    public Date getFecha() {
        return date;
    }

    public Date getFechaEntrega() {
        return deliveryDate;
    }

    public int getValorTotal() {
        return totalValue;
    }

   

    public List<Service> getServices() {
        return services;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setFechaEntrega(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setValorTotal(int totalValue) {
        this.totalValue = totalValue;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
