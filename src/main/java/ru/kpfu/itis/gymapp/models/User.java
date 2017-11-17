package ru.kpfu.itis.gymapp.models;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.kpfu.itis.gymapp.models.enums.Role;
import ru.kpfu.itis.gymapp.models.enums.Specialization;

import javax.persistence.*;

/**
 * 26.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@TypeDef(name = "specialization", typeClass = PostgreSQLEnumType.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String name;
    private Float weight;
    private Short height;

    @Enumerated(EnumType.STRING)
    @Type(type = "specialization")
    private Specialization specialization;

    private Long xp;
    private Short strength;
    private Short stamina;
    private Short flexibility;
    private Boolean gender;

    @Enumerated(EnumType.STRING)
    private Role role;
}
