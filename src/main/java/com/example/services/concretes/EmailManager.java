package com.example.services.concretes;

import com.example.core.utilities.constants.MailConstants;
import com.example.services.abstracts.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailManager implements EmailService {
    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;



    public void sendTaskAddEmail(String to,
                                 String username,
                                 String taskName,
                                 String labelNames) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(MailConstants.SENDER_EMAIL.getValue(), MailConstants.SENDER_NAME.getValue());
        helper.setTo(MailConstants.RECEIVER_EMAIL.getValue());
        helper.setSubject(MailConstants.SUBJECT.getValue());

        Context context = new Context();
        context.setVariable("to", to);
        context.setVariable("username", username);
        context.setVariable("taskName", taskName);
        context.setVariable("labelNames", labelNames);

        String htmlContent = templateEngine.process("email-template.html", context);
        String textContent = templateEngine.process("email-template.txt", context);

        helper.setText(textContent, htmlContent);

        mailSender.send(message);
    }
}
