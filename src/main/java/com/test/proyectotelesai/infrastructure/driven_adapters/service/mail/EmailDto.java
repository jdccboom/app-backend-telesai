package com.test.proyectotelesai.infrastructure.driven_adapters.service.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {
    private String asunto;
    private String body;
    private String destinatario;

    public EmailDto(String asunto, String body, String destinatario) {
        this.asunto = asunto;
        this.body = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>"+asunto+"</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "            background: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        p {\n" +
                "            color: #666;\n" +
                "        }\n" +
                "        a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +body+
                "        <p>Si tiene alguna pregunta o necesita ayuda, no dude en <a href=\"mailto:arrowv14570406juandavid@gmail.com\">contactarnos</a>.</p>\n" +
                "        <p>¡Esperamos poder servirle!</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        this.destinatario = destinatario;
    }
}
