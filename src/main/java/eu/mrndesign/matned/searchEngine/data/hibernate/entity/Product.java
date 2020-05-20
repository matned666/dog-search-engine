package eu.mrndesign.matned.searchEngine.data.hibernate.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_value")
    private Integer productValue;

    @Column(name = "product_details_id")
    private Integer productDetailsId;

    @Override
    public String toString() {
        return
                "Id: " + productId +
                ", Name: " + productName+
                ", Value: " + productValue +
                ", Details Id: " + productDetailsId;
    }
}

