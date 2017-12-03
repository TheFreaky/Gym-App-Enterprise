package ru.kpfu.itis.gymapp.models.token;

import lombok.Data;
import ru.kpfu.itis.gymapp.models.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@MappedSuperclass
abstract class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date expiryDate;

    protected Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
