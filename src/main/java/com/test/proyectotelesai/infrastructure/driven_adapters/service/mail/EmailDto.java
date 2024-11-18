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
        this.body =
                """
                        <!DOCTYPE html>
                        <html lang="es">
                        <head>
                            <meta charset="UTF-8">
                            <meta http-equiv="X-UA-Compatible" content="IE=edge">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>%snull;</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    color: #333;
                                    background-color: #f9f9f9;
                                    margin: 0;
                                    padding: 20px;
                                }
                                .container {
                                    background-color: #ffffff;
                                    padding: 20px;
                                    border-radius: 8px;
                                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                                }
                                .header {
                                    background-color: #D40808;
                                    color: white;
                                    padding: 10px 0;
                                    text-align: center;
                                    border-radius: 8px;
                                }
                                .content {
                                    margin-top: 20px;
                                }
                                .content p {
                                    font-size: 16px;
                                    line-height: 1.5;
                                }
                                .button {
                                    background-color: #D40808;
                                    color: white;
                                    text-decoration: none;
                                    padding: 10px 20px;
                                    border-radius: 5px;
                                    margin-top: 20px;
                                    display: inline-block;
                                }
                                .footer {
                                    font-size: 12px;
                                    color: #777;
                                    margin-top: 30px;
                                    text-align: center;
                                }
                            </style>
                        </head>
                        <body>
                            <div class="container">
                            %s
                             </div>
                                <div class="footer">
                                    <p>Gracias por confiar en nuestro servicio.</p>
                                    <p>Atentamente,</p>
                                    <p><strong>Equipo de Telesai</strong></p>
                                </div>
                            </div>
                        </body>
                        </html>
                """.formatted(asunto,body);
        this.destinatario = destinatario;
    }
}
