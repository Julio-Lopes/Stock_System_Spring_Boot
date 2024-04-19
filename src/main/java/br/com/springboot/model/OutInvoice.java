package br.com.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "out_invoice")
public class OutInvoice {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false, name = "date_hours", columnDefinition = "DATETIME")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "outInvoice", cascade = CascadeType.ALL)
    private List<ItemOutInvoice> items;

    @Transient
    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Float getTotal() {
        this.total = 0f;
        if(this.items != null){
            for (ItemOutInvoice itemInvoice : items) {
                total += itemInvoice.getTotal();
            }
        }
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<ItemOutInvoice> getItems() {
        return items;
    }

    public void setItems(List<ItemOutInvoice> items) {
        this.items = items;
    }

}