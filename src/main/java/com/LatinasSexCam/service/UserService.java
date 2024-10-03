package com.LatinasSexCam.service;

import com.LatinasSexCam.controller.response.TokenResponse;
import com.LatinasSexCam.model.Role;
import com.LatinasSexCam.model.User;
import com.LatinasSexCam.model.UserStatus;
import com.LatinasSexCam.model.request.LoginRequest;
import com.LatinasSexCam.model.request.RegisterRequest;
import com.LatinasSexCam.repository.RoleRepository;
import com.LatinasSexCam.repository.UserRepository;
import com.LatinasSexCam.security.TokenUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final RoleRepository roleRepository;
//    private final JavaMailSender javaMailSender;


    public ResponseEntity<String> registerUser(@Validated RegisterRequest request) throws MessagingException {

        String responseMessage;
        HttpStatus status;
        User userExisting = userRepository.findByEmail(request.getEmail());
        Role roleuser = roleRepository.findById(1);


        if (userExisting == null){

            User user = User.builder()
                    .user_name(request.getUser_name())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nacionality(request.getNacionality())
                    .gender(request.getGender())
                    .phoneNumber(request.getPhoneNumber())
                    .status(UserStatus.ACTIVE)
                    .role(roleuser)
                    .build();

            userRepository.save(user);
 //           sendRegistrationEmail(user.getEmail(), user.getUser_name());
             responseMessage = "Te has registrado con éxito";
            status = HttpStatus.CREATED;
        }else {
            responseMessage = "Usuario existente";
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(responseMessage,status);
    }

    public TokenResponse loginUser(LoginRequest request, User user){
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
