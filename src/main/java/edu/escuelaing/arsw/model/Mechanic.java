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
@Table(name="mechanics", schema = "public")
public class Mechanic implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idMechanic;
    @Column(length = 50, updatable = true)
    private String name;
    @Column(length = 50, name = "lastname")
    private String lastName;
    @Column(length = 100, unique = true, updatable = false)
    private String email;
    @Column(length = 100, name = "password")
    private String password;
    @Column (length= 100, name = "store_name")
    private String storeName;
    @Column (length = 10)
    private int cellphone;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Mechanic_Product")
    private List<Product> products;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Mechanic_Service")
    private List<Service> services;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Mechanic_Order")
    private List<Order> orders;

    public long getIdMechanic() {
        return idMechanic;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreLocal() {
        return storeName;
    }

    public int getCelular() {
        return cellphone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Order> getOrders() {
        return orders;
    }
    
    
   
    
}
