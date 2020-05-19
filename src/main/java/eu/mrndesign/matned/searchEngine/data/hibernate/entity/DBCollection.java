package eu.mrndesign.matned.searchEngine.data.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "db_collection")
public class DBCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_id")
    private int dbId;

    @Column(name = "db_name")
    private String dbName;

    public DBCollection(String dbName) {
        this.dbName = dbName;
    }

}
