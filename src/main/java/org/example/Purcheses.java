package org.example;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "prod_buy")
public class Purcheses {

    @EmbeddedId
      private PurchesesKey id;

    @ManyToOne
    @JoinColumn(name = "id_product",insertable=false, updatable=false)
     private Product product;

    @ManyToOne
    @JoinColumn(name = "id_buyer",insertable=false, updatable=false)
     private Buyer buyer;

    @Column(name= "price")
    private Double price;

    @Override
    public String toString() {
        return "Purcheses{" +
                "id=" + id +
                ", product=" + product +
                ", buyer=" + buyer +
                ", price=" + price +
                '}';
    }
}



