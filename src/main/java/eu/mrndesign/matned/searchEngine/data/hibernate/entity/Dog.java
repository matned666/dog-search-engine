package eu.mrndesign.matned.searchEngine.data.hibernate.entity;

import eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace;
import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dog_id")
    private int dogId;

    @Column(name="dog_name")
    private String dogName;

    @Column(name="dog_gender")
    private String dogGender;

    @Column(name="dog_age")
    private Integer dogAge;

    @Enumerated(value = EnumType.STRING)
    @Column(name=" dog_race")
    private DogRace dogRace;

    @Column(name="dog_weight")
    private Double dogWeight;

    @Column(name=" dog_pure_race")
    private int isDogPureRace;

    @Column(name="owner_name")
    private String ownerName;

    @Column(name="owner_last_name")
    private String ownerLastName;




}
