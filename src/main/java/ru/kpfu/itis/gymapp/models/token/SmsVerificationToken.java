package ru.kpfu.itis.gymapp.models.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.gymapp.models.User;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "sms_verification")
public class SmsVerificationToken extends VerificationToken {
    private static final int EXPIRATION = 10;

    private String phone;

    public SmsVerificationToken(String token, User user, String phone) {
        this.token = token;
        this.user = user;
        this.phone = phone;
        expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
