package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.ports.RoleRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.response.TokenResponse;
import com.LatinasSexCam.domain.model.Role;
import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.model.UserStatus;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.request.LoginRequest;
import com.LatinasSexCam.infrastructure.dto.request.RegisterRequest;
import com.LatinasSexCam.infrastructure.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepositoryPort userRepositoryPort;
    private final TokenUtils tokenUtils;
    private final RoleRepositoryPort roleRepositoryPort;
//    private final JavaMailSender javaMailSender;


    public ResponseEntity<String> registerUser(@Validated RegisterRequest request) {
        String responseMessage;
        HttpStatus status;

        try {
            System.out.println("Buscando usuario con email: " + request.getEmail());
            User userExisting = userRepositoryPort.findByEmail(request.getEmail());

            if (userExisting == null) {
                Role roleuser = roleRepositoryPort.findById(1);
                if (roleuser == null) {
                    responseMessage = "Rol no encontrado";
                    status = HttpStatus.BAD_REQUEST;
                    return new ResponseEntity<>(responseMessage, status);
                }

                User user = User.builder()
                        .user_name(request.getUser_name())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .nationality(request.getNacionality())
                        .gender(request.getGender())
                        .phoneNumber(request.getPhoneNumber())
                        .status(UserStatus.ACTIVE)
                        .role(roleuser)
                        .build();

                userRepositoryPort.save(user);
                // sendRegistrationEmail(user.getEmail(), user.getUser_name());
                responseMessage = "Te has registrado con éxito";
                status = HttpStatus.CREATED;
            } else {
                responseMessage = "Usuario existente";
                status = HttpStatus.CONFLICT;
            }
        } catch (Exception e) {
            responseMessage = "Error en el registro: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();  // Log the exception
        }

        return new ResponseEntity<>(responseMessage, status);
    }

    public TokenResponse loginUser(LoginRequest request){
        User user = userRepositoryPort.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return null;
        }
        String token = tokenUtils.getTokenUser(user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

/*    public void sendRegistrationEmail(String to, String name) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("!Registro Exitoso¡");

        // HTML del contenido del correo electrónico
        String htmlContent = "<div style='background-image: url(\"cid:background\"); background-size: cover; background-position: center; background-repeat: repeat; background-color: rgba(255, 255, 255, 0.3); height: 500px; display: flex; justify-content: center; align-items: center ;'>" +
                "<div style='text-align: center; color: black;'>" +
                "<p style='font-size: 24px; font-weight: bold; color: black;'>¡Hola " + name + ",!</p><br>" +
                "<p style='font-size: 16px; color: black;'>¡Bienvenido a TuCafe!</p>"+
                "<p style='font-size: 14px; color: black;'>Gracias por registrarte en TuCafe! Ahora podrás disfrutar de esta maravillosa experiencia.</p>" +
                "<img src='cid:logo' width='150' style='border: 3px solid #663300; border-radius: 50%;'>" +
                "<p style='font-size: 14px; color: white;'>Queremos agradecerte por registrarte con nosotros y confiar en nuestra plataforma para disfrutar de una experiencia única. Estamos emocionados de tenerte a bordo y estamos seguros de que te encantará lo que tenemos preparado para ti." +
                "<br>" +
                "¡Gracias por unirte a nuestra comunidad de amantes del café!" +
                "<br>" +
                "Atentamente," +
                "<b style='color: black;'>TuCafe</b></p>"+
                "</div>" +
                "</div>";

        String cssStyles = "<style>" +
                "@media screen and (max-width: 768px) {" +
                "   div { height: auto !important; }" +
                "   p { font-size: 14px !important; }" +
                "   .logo { max-width: 40px !important; }" +
                "</style>";


        htmlContent = cssStyles + htmlContent;


        helper.setText(htmlContent, true);

        ClassPathResource backgroundImg = new ClassPathResource("images/fondo.jpeg");
        helper.addInline("background", backgroundImg);

        ClassPathResource logoImg = new ClassPathResource("images/tucafe2.jpeg");
        helper.addInline("logo", logoImg);


        javaMailSender.send(message);

    }*/
}
