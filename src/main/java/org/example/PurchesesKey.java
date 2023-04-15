package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class PurchesesKey  implements Serializable{

        @Column(name = "id_product",insertable=false, updatable=false)
        long productId;

        @Column(name = "id_buyer",insertable=false, updatable=false)
        long buyerId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PurchesesKey id = (PurchesesKey) o;
            return productId == id.productId &&
                    buyerId == id.buyerId;
        }

        @Override
        public int hashCode() {
            return (int) (productId + buyerId);
        }
    }

