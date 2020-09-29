/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.model;

import java.io.Serializable;
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
    @Column(length = 25,unique = true, updatable = true)
    private String name;
    @Column(length = 300, updatable = true)
    private String description;
    @Column(length = 25, updatable = true)
    private long price;
    @Column(length = 25, updatable = true)
    private String status;

    public long getIdService() {
        return idService;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
    
}
