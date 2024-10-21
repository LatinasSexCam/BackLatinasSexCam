package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.ports.MultimediaRepositoryPort;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.request.MultimediaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultimediaService {

/*    private final MultimediaRepositoryPort multimediaRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;*/

/*    public ResponseEntity<String> UploadImage(MultimediaRequest request){
        String responseMessage;
        HttpStatus status;
        try {
            User userExist = userRepositoryPort.findByEmail(request.getEmail());

            if (userExist != null){
                Multimedia multimedia = Multimedia.builder()
                        .url(request.getUrl())
                        .mediaType(request.getMediaType())
                        .women(request.)
            }
        }
    }*/

   }