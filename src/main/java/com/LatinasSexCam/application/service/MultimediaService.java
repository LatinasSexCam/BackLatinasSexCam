package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.MultimediaRepositoryPort;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.request.MultimediaRequest;
import com.LatinasSexCam.infrastructure.dto.response.MultimediaResponseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MultimediaService {

    private final MultimediaRepositoryPort multimediaRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final WomenRepositoryPort womenRepositoryPort;

    public ResponseEntity<String> UploadImage(MultimediaRequest request) {
        String responseMessage;
        HttpStatus status;
        try {
            User user = userRepositoryPort.findByUserName(request.getUser_name());
            if (user == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
            Optional<Women> optionalWomen = womenRepositoryPort.findByUser_IdUser(user.getIdUser());
            if (optionalWomen.isEmpty()) {
                return new ResponseEntity<>("No se encontró información", HttpStatus.NOT_FOUND);
            }

            Women women = optionalWomen.get();

            Multimedia multimedia = Multimedia.builder()
                    .mediaType(request.getMediaType())
                    .url(request.getUrl())
                    .women(women)
                    .build();
            multimediaRepositoryPort.save(multimedia);
            return new ResponseEntity<>("Multimedia subida exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al subir " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<MultimediaResponseDTO> getMultimedia(@NotNull String userName){
        List<Multimedia> multimedia = multimediaRepositoryPort.findByWomen_User_UserName(userName);
        System.out.println("Username" + userName);
        return multimedia.stream()
                .map(MultimediaResponseDTO::new)
                .collect(Collectors.toList());
    }


}