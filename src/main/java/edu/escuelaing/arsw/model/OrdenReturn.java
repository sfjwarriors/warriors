package edu.escuelaing.arsw.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

public class OrdenReturn implements Serializable {
    private Long ordenId;
    private Long fkStore;
    private List<Product> products;
    private List<Servicio> servicios;
    private Long totalValue;
    private String statusOrder;

    public OrdenReturn(Long ordenId, Long fkStore, List<Product> products, List<Servicio> servicios, Long totalValue, String statusOrder) {
        this.ordenId = ordenId;
        this.fkStore = fkStore;
        this.products = products;
        this.servicios = servicios;
        this.totalValue = totalValue;
        this.statusOrder = statusOrder;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public Long getFkStore() {
        return fkStore;
    }

    public void setFkStore(Long fkStore) {
        this.fkStore = fkStore;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Long totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    @Override
    public String toString() {
        return "OrdenReturn{" +
                "ordenId=" + ordenId +
                ", fkStore=" + fkStore +
                ", products=" + products +
                ", servicios=" + servicios +
                ", totalValue=" + totalValue +
                ", statusOrder='" + statusOrder + '\'' +
                '}';
    }
}
