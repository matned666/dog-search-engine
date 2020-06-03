package eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "db_collection")
public class EntityDBCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_id")
    private int dbId;

    @Column(name = "db_name")
    private String dbName;

}
