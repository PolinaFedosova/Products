package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;


@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="nameProd")
    private String nameProd;
    @Column(name="price")
    private Double price;
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Purcheses> getPurcheses() {
        return purcheses;
    }

    public Long getId() {
        return id;
    }

    public Product() {
    }

    public Product(Long id, String nameProd, Double price) {
        this.id = id;
        this.nameProd = nameProd;
        this.price = price;
    }
   @OneToMany(mappedBy = "product")
   @Cascade(org.hibernate.annotations.CascadeType.ALL)
   private List<Purcheses> purcheses;
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProd='" + nameProd + '\'' +
                ", price=" + price +
                '}';
    }
}
