package eu.mrndesign.matned.searchEngine.data.hibernate.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private int productValue;

    @Column(name = "product_details_id")
    private int productDetailsId;

}

