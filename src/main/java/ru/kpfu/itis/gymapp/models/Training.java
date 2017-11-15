package ru.kpfu.itis.gymapp.models;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.kpfu.itis.gymapp.models.enums.Complexity;
import ru.kpfu.itis.gymapp.models.enums.Specialization;

import javax.persistence.*;
import java.util.Set;

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
@Table(name = "trainings")
@TypeDef(
        name = "complexity",
        typeClass = PostgreSQLEnumType.class
)
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long xp;
    private Integer minLvl;
    @Enumerated(EnumType.STRING)
    @Type(type = "complexity")
    private Complexity complexity;
    @Enumerated(EnumType.STRING)
    @Type(type = "specialization")
    private Specialization type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"))
    private Set<Exercise> exercises;
}
