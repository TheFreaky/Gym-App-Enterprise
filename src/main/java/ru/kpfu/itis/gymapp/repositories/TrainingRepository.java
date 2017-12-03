package ru.kpfu.itis.gymapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.gymapp.models.Training;

import java.util.List;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Training findByName(String name);
    
    List<Training> findAllByMinLvlLessThanEqual(Integer lvl);

    List<Training> findAllByMinLvlLessThanEqualOrderByComplexity(Integer lvl);

    List<Training> findAllByMinLvlLessThanEqualOrderByType(Integer lvl);
}
