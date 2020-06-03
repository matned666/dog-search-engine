package eu.mrndesign.matned.searchEngine.data.hibernate.dog;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dog")
public class EntityDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dog_id")
    private Integer dogId;

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
    private Integer isDogPureRace;

    @Column(name="owner_name")
    private String ownerName;

    @Column(name="owner_last_name")
    private String ownerLastName;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dogId != null) sb.append("Id: ").append(dogId).append(";  ");
        if (dogName != null) sb.append("Name: ").append(dogName.toUpperCase()).append(";  ");
        if (dogAge != null) sb.append("Age: ").append(dogAge).append(";  ");
        if (dogGender != null) sb.append("Gender: ").append(dogGender).append(";  ");
        if (dogRace != null) sb.append("Race: ").append(dogRace.toString().toLowerCase()).append(";  ");
        if (dogWeight != null) sb.append("Weight: ").append(dogWeight).append(";  ");
        if (isDogPureRace != null) sb.append("PureRace: ").append(isDogPureRace).append(";  ");
        if (ownerName != null) sb.append("Owner name: ").append(ownerName).append(";  ");
        if (ownerLastName != null) sb.append("Owner surname: ").append(ownerLastName).append(";  ");
        return sb.toString();
    }
}
