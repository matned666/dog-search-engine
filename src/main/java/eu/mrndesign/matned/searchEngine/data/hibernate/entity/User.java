package eu.mrndesign.matned.searchEngine.data.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "ip_address")
    private String ipAddress;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (userId != null) sb.append("Id: ").append(userId).append(";  ");
        if (firstName != null) sb.append("Name: ").append(firstName).append(";  ");
        if (lastName != null) sb.append("Last name: ").append(lastName).append(";  ");
        if (email != null) sb.append("email: ").append(email).append(";  ");
        if (gender != null) sb.append("Gender: ").append(gender).append(";  ");
        if (ipAddress != null) sb.append("IP address:: ").append(ipAddress).append(";  ");
        return sb.toString();
    }

}
