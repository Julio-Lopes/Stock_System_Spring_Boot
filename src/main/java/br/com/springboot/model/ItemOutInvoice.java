package br.com.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "items_out_invoice")
public class ItemOutInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "outinvoice_id")
    @NotNull
    private OutInvoice outInvoice;
    
    @NotNull(message = "Informe a quantidade")
    private Integer quantity;

    @NotNull(message = "Informe o valor unitario")
    private Float unityValue;

    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OutInvoice getOutInvoice() {
        return outInvoice;
    }

    public void setOutInvoice(OutInvoice outInvoice) {
        this.outInvoice = outInvoice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnityValue() {
        return unityValue;
    }

    public void setUnityValue(Float unityValue) {
        this.unityValue = unityValue;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
}