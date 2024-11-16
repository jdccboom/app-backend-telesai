package com.test.proyectotelesai.application;

import com.test.proyectotelesai.domain.model.bitacora.gateway.BitacoraGateway;
import com.test.proyectotelesai.domain.model.evidencia.gateway.EvidenciaGateway;
import com.test.proyectotelesai.domain.model.observacion.gateway.ObservacionGateway;
import com.test.proyectotelesai.domain.model.rol.gateway.RolGateway;
import com.test.proyectotelesai.domain.model.servicio.gateway.ServicioGateway;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import com.test.proyectotelesai.domain.model.usuario.gateway.UsuarioGateway;
import com.test.proyectotelesai.domain.usecase.*;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.CloudinaryGateway;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.mail.MailGateway;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.pdf.PdfGetaway;
import com.test.proyectotelesai.infrastructure.helpers.utils.JWTUtils;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapperImp();}

    @Bean
    public ServicioUseCase servicioUseCase(ServicioGateway servicioGateway) {
        return new ServicioUseCase(servicioGateway);
    }

    @Bean
    public AutenticacionUseCase autenticacionUseCase(UsuarioGateway usuarioGateway, JWTUtils jwtUtils, RolUseCase rolUseCase) {
        return new AutenticacionUseCase(usuarioGateway,rolUseCase,jwtUtils);
    }

    @Bean
    public RolUseCase rolUseCase(RolGateway rolGateway) {
        return new RolUseCase(rolGateway);
    }

    @Bean
    public SolicitudUseCase solicitudUseCase(SolicitudGateway solicitudGateway) {
        return new SolicitudUseCase(solicitudGateway);
    }

    @Bean
    public ObservacionUseCase observacionUseCase(ObservacionGateway observacionGateway) {
        return new ObservacionUseCase(observacionGateway);
    }

    @Bean
    public ClodinaryUseCase clodinaryUseCase(CloudinaryGateway cloudinaryGateway){
        return new ClodinaryUseCase(cloudinaryGateway);
    }

    @Bean
    public MailUseCase mailUseCase(MailGateway mailGateway){
        return new MailUseCase(mailGateway);
    }

    @Bean
    public EvidenciaUseCase evidenciaUseCase(EvidenciaGateway evidenciaGateway){
        return new EvidenciaUseCase(evidenciaGateway);
    }

    @Bean
    public PdfUseCase pdfUseCase(PdfGetaway pdfGetaway){
        return new PdfUseCase(pdfGetaway);
    }

    @Bean
    public BitacoraUseCase bitacoraUseCase(BitacoraGateway bitacoraGateway){
        return new BitacoraUseCase(bitacoraGateway);
    }
}