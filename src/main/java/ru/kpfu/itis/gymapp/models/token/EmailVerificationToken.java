package ru.kpfu.itis.gymapp.models.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.gymapp.models.User;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 17.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "user_verification")
public class EmailVerificationToken extends VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    public EmailVerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
