package ru.kpfu.itis.gymapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
@Table(name = "users_trainings")
public class UserTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="training_id", referencedColumnName="id")
    private Training training;
}
