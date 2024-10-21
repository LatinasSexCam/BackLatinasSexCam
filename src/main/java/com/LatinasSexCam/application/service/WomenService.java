package com.LatinasSexCam.application.service;


import com.LatinasSexCam.domain.model.*;
import com.LatinasSexCam.domain.model.Package;
import com.LatinasSexCam.domain.ports.*;
import com.LatinasSexCam.infrastructure.dto.request.*;
import com.LatinasSexCam.infrastructure.dto.response.WomenInfoResponseDTO;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import com.LatinasSexCam.domain.model.Services;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WomenService {
    private final WomenRepositoryPort womenRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPort userRepositoryPort;
    private final PackageRepositoryPort packageRepositoryPort;
    private final ServiceRepositoryPort serviceRepositoryPort;

    public ResponseEntity<String> registerWomen(@Valid RegisterWomenRequest request) {
        String responseMessage;
        HttpStatus status;
        try {
            User userExist = userRepositoryPort.findByEmail(request.getEmail());
            if (userExist == null) {
                Role roleUser = roleRepositoryPort.findById(2);
                if (roleUser == null) {
                    responseMessage = "Rol no encontrado";
                    status = HttpStatus.BAD_REQUEST;
                    return new ResponseEntity<>(responseMessage, status);
                }

                User user = User.builder()
                        .userName(request.getUserName())
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(roleUser)
                        .status(UserStatus.ACTIVE)
                        .nationality(request.getNationality())
                        .build();
                userRepositoryPort.save(user);

                userExist = userRepositoryPort.findByEmail(request.getEmail());
                Package selectePackage = packageRepositoryPort.findById(request.getIdPackage());
                Women women = Women.builder()
                        .name(request.getName())
                        .user(userExist)
                        .packageS(selectePackage)
                        .status(WomenStatus.WAITING)
                        .build();
                womenRepositoryPort.save(women);
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
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseMessage, status);
    }

    public ResponseEntity<String> updateInfoWomen(UpdateWomenRequest request) {
        try {

            System.out.println("Se inicia actualizacion de women con el email: " + request.getEmail());
            User user = userRepositoryPort.findByEmail(request.getEmail());
            if (user == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
            System.out.println("Buscando info de women con iduser " + user.getIdUser());
            Women women = womenRepositoryPort.findByUser_IdUser(user.getIdUser());
            List<Services> selectServices = serviceRepositoryPort.findByIdServiceIn(request.getSelectedServiceIds());
            if (women == null) {
                return new ResponseEntity<>("No se encontro infomración", HttpStatus.NOT_FOUND);
            }

            System.out.println("Informacion actual de women: " + women);


            women.setAge(request.getAge());
            women.setColorEyes(request.getColorEyes());
            women.setColorHair(request.getColorHair());
            women.setColorSkin(request.getColorSkin());
            women.setCupSize(request.getCupSize());
            women.setDescription(request.getDescription());
            women.setHeight(request.getHeight());
            women.setHips(request.getHips());
            women.setPiercings(request.getPiercings());
            women.setShaving(request.getShaving());
            women.setShoeSize(request.getShoeSize());
            women.setSmoker(request.getSmoker());
            women.setTattoos(request.getTattoos());
            women.setWeight(request.getWeight());
            women.setServices(new HashSet<>(selectServices));
            women.setStatus(WomenStatus.WAITING);

            womenRepositoryPort.save(women);
            System.out.println("Datos actualizados");


            return new ResponseEntity<>("Datos registrados correctamente", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error al registrar los datos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<WomensResponseDTO> getWomenByCategories(List<String> categoryNames) {
        List<Women> womens = womenRepositoryPort.findByCategoryNames(categoryNames);
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<WomensResponseDTO> getWomens(){
        List<Women> womens = womenRepositoryPort.findAll();
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }

    public WomenInfoResponseDTO getWomesInfo(RegisterRequest userName) {
        System.out.println("Buscando a " + userName);
        Optional<Women> womenOpt = womenRepositoryPort.findByUser_UserName(userName.getUser_name());

        if (womenOpt.isEmpty()) {
            return null;
        }
        return new WomenInfoResponseDTO(womenOpt.get());
    }


}
