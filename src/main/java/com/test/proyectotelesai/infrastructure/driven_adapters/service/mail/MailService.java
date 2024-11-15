package com.test.proyectotelesai.infrastructure.driven_adapters.service.mail;

public interface MailService {
    void sendMail(EmailDto emailDto) throws Exception;
}
