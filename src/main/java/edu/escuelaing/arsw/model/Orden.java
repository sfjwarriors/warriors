/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rojas
 */
@Entity
@Table(name="orden", schema = "public")
public class Orden implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
        
    @Column
    private Date dateOrder;
    
    @Column (name = "deliveryDate")
    private Date deliveryDate;
    
    @Column (name = "totalValue")
    private long totalValue;

    //Se debe poder ver la fase del servicio (Creado, aceptado, en camino, en tu lugar y finalizado).
    @Column
    private String statusOrder;

    @Column
    private long fkStore;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkOrderCart")
    private List<Cart> carts;

    public Orden() {
    }

    public Orden(Date dateOrder, Date deliveryDate, long totalValue, String statusOrder, long fkStore) {
        this.dateOrder = dateOrder;
        this.deliveryDate = deliveryDate;
        this.totalValue = totalValue;
        this.statusOrder = statusOrder;
        this.fkStore = fkStore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(long totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public long getFkStore() {
        return fkStore;
    }

    public void setFkStore(long fkStore) {
        this.fkStore = fkStore;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                ", deliveryDate=" + deliveryDate +
                ", totalValue=" + totalValue +
                ", statusOrder='" + statusOrder + '\'' +
                ", fkStore=" + fkStore +
                '}';
    }
}
