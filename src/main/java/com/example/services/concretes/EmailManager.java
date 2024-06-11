package com.example.services.concretes;

import com.example.core.utilities.constants.MailConstants;
import com.example.services.abstracts.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailManager implements EmailService {
    private JavaMailSender mailSender;



    public void sendTaskAddEmail(String to,
                                 String username,
                                 String taskName,
                                 String labelNames) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(MailConstants.SENDER_EMAIL.getValue(), MailConstants.SENDER_NAME.getValue());
        helper.setTo(MailConstants.RECEIVER_EMAIL.getValue());
        helper.setSubject(MailConstants.SUBJECT.getValue());

        String htmlMsg = "<div style=\"font-family: Arial, sans-serif; color: #333;\">"
                + "<p style=\"font-size: 18px;\">Kullanıcı sisteme giriş yaptı: " + to + ",</p>"
                + "<p><b>" + username + "</b> adlı kullanıcı, '<b>" + taskName +
                "</b>' adında yeni bir görev ekledi. Bu görev, şu etiketlere eklenmiştir: " +
                "<b>" + labelNames + "</b>.</p>"
                + "<p>Görevi en kısa sürede ilgili departmana iletiniz.</p>"
                + "<p>Saygılarımızla,</p>"
                + "<p><b style=\"color: #007BFF;\">Rent2Go Ekibi</b></p>"
                + "</div>";

        String textMsg = "Sayın " + username + ",\n"
                + username + " adlı kullanıcı, '" + taskName + "' adında yeni bir görev ekledi. " +
                "Bu görev, şu etiketlere eklenmiştir: " + labelNames + ".\n"
                + "Görevi en kısa sürede ilgili departmana iletiniz.\n"
                + "Saygılarımızla,\n"
                + "Rent2Go Ekibi";

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }
}
