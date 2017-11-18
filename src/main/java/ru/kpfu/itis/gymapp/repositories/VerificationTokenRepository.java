package ru.kpfu.itis.gymapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.gymapp.models.VerificationToken;

import java.util.Date;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken getByTokenAndExpiryDateAfter(String token, Date now);
}
