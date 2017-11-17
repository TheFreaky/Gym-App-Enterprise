package ru.kpfu.itis.gymapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.gymapp.models.UserTraining;

import java.util.List;

/**
 * 04.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Repository
public interface UserTrainingRepository extends JpaRepository<UserTraining, Integer> {
    List<UserTraining> findAllByUserId(@Param("userId") Long userId);
}
