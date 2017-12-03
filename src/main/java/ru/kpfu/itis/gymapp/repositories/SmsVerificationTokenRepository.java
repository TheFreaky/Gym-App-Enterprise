package ru.kpfu.itis.gymapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.gymapp.models.token.SmsVerificationToken;

import java.util.Date;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface SmsVerificationTokenRepository extends JpaRepository<SmsVerificationToken, Long> {
    SmsVerificationToken getByTokenAndExpiryDateAfter(String token, Date now);
}
