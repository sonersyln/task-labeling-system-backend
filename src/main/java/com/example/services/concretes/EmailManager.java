package com.example.services.concretes;

import com.example.services.abstracts.EmailService;
import com.example.services.dtos.requests.AddTaskRequest;
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



    public void sendTaskAddEmail(String to, String username, String taskName, String labelNames) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("noreply@rentogo.com.tr", "Rent2Go Şirketi");
        helper.setTo(to);  // Alıcının e-posta adresini burada belirtin
        helper.setSubject("Yeni Görev Eklendi");

        String htmlMsg = "<div style=\"font-family: Arial, sans-serif; color: #333;\">"
                + "<p style=\"font-size: 18px;\">Sayın " + username + ",</p>"
                + "<p>Adı '<b>" + taskName + "</b>' olan yeni görevinizin, şu etiketlere eklediğini bildirmek isteriz: " + labelNames + ".</p>"
                + "<p>Görevlerinizi düzenli tuttuğunuz için teşekkür ederiz. Görevlerinizi verimli bir şekilde yönetmenize yardımcı olmak için buradayız.</p>"
                + "<p>Saygılarımızla,</p>"
                + "<p><b style=\"color: #007BFF;\">Rent2Go Ekibi</b></p>"
                + "</div>";

        String textMsg = "Sayın " + username + ",\n"
                + "Adı '" + taskName + "' olan yeni görevinizin, şu etiketlere eklediğini bildirmek isteriz: " + labelNames + ".\n"
                + "Görevlerinizi düzenli tuttuğunuz için teşekkür ederiz. Görevlerinizi verimli bir şekilde yönetmenize yardımcı olmak için buradayız.\n"
                + "Saygılarımızla,\n"
                + "Rent2Go Ekibi";

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }
}
