package ru.kpfu.itis.gymapp.services;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface EmailService {
    void sendMail(String text, String subject, String to);
}
