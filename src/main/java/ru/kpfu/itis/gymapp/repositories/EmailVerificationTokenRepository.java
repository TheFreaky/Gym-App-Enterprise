package ru.kpfu.itis.gymapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.gymapp.models.token.EmailVerificationToken;

import java.util.Date;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    EmailVerificationToken getByTokenAndExpiryDateAfter(String token, Date now);
}
