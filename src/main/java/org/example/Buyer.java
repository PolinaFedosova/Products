package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name="buyer")
public class Buyer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Purcheses> getPurcheses() {
        return purcheses;
    }

    public Long getId() {
        return id;
    }

    public Buyer() {
    }

    public Buyer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @OneToMany(mappedBy = "buyer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Purcheses> purcheses;
    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
