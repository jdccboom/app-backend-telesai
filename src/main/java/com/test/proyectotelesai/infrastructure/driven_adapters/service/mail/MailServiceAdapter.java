package com.test.proyectotelesai.infrastructure.driven_adapters.service.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceAdapter implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(EmailDto emailDto) throws Exception {

        new Thread(() -> {
            try {
                MimeMessage mensaje = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mensaje);
                helper.setSubject(emailDto.getAsunto());
                helper.setText(emailDto.getBody(), true);
                helper.setTo(emailDto.getDestinatario());
                helper.setFrom("no_reply@dominio.com");

                javaMailSender.send(mensaje);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ).start();

    }

}