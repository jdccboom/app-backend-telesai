package com.test.proyectotelesai.infrastructure.driven_adapters.service.mail;

public interface MailGateway {
    void sendMail(EmailDto emailDto) throws Exception;
}
