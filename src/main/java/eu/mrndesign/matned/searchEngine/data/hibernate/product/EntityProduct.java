package eu.mrndesign.matned.searchEngine.data.hibernate.product;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class EntityProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_value")
    private Integer productValue;

    @Column(name = "product_details_id")
    private Integer productDetailsId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (productId != null) sb.append("Id: ").append(productId).append(";  ");
        if (productName != null) sb.append("Name: ").append(productName.toUpperCase()).append(";  ");
        if (productValue != null) sb.append("Value: ").append(productValue).append(";  ");
        if (productDetailsId != null) sb.append("DetailsId: ").append(productDetailsId).append(";  ");
        return sb.toString();
    }
}

