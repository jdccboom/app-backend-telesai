package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.infrastructure.driven_adapters.service.mail.EmailDto;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.mail.MailGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailUseCase {

    private final MailGateway mailGateway;

    public void sendMail(EmailDto emailDto) throws Exception {
        mailGateway.sendMail(emailDto);
    }

}
