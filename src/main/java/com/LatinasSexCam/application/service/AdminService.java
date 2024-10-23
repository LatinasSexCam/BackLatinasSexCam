package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.*;
import com.LatinasSexCam.domain.model.Package;
import com.LatinasSexCam.domain.ports.*;
import com.LatinasSexCam.infrastructure.dto.request.RegisterWomenAdminRequest;
import com.LatinasSexCam.infrastructure.dto.request.UpdateWomenRequest;
import com.LatinasSexCam.infrastructure.repository.CategoryFilterJpaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepositoryPort userRepositoryPort;
    private final WomenRepositoryPort womenRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PackageRepositoryPort packageRepositoryPort;
    private final ServiceRepositoryPort serviceRepositoryPort;
    private final CategoryFilterRepositoryPort categoryFilterRepositoryPort;

    public ResponseEntity<String> registerWomenByAdmin(@Valid RegisterWomenAdminRequest request) {
        String responseMessage;
        HttpStatus status;
        try {
            User userExist = userRepositoryPort.findByUserName(request.getUser_name());

            if (userExist == null){
                Role roleUser =roleRepositoryPort.findById(2);
                if (roleUser == null){
                    responseMessage = "Rol no encontrado";
                    status = HttpStatus.BAD_REQUEST;
                    return new ResponseEntity<>(responseMessage, status);
                }
                User user = User.builder()
                        .userName(request.getUser_name())
                        .nationality(request.getNationality())
                        .profilePhoto(request.getPhotoProfile())
                        .status(UserStatus.ACTIVE)
                        .role(roleUser)
                        .build();
                userRepositoryPort.save(user);

                userExist = userRepositoryPort.findByUserName(request.getUser_name());
                Package selectePackage = packageRepositoryPort.findById(1);
                List<Services> selectServices = serviceRepositoryPort.findByIdServiceIn(request.getSelectedServiceIds());
                List<CategoryFilter> selectedFilter = categoryFilterRepositoryPort.findByNameIn(request.getSelectedFilterNames());
                Women women = Women.builder()
                        .name(request.getName())
                        .age(request.getAge())
                        .packageS(selectePackage)
                        .height(request.getHeight())
                        .weight(request.getWeight())
                        .hips(request.getHips())
                        .shoeSize(request.getShoeSize())
                        .colorHair(request.getColorHair())
                        .colorEyes(request.getColorEyes())
                        .colorSkin(request.getColorSkin())
                        .cupSize(request.getCupSize())
                        .shaving(request.getShaving())
                        .smoker(request.getSmoker())
                        .piercings(request.getPiercings())
                        .tattoos(request.getTattoos())
                        .description(request.getDescription())
                        .user(userExist)
                        .status(WomenStatus.ACCEPTED)
                        .services(new HashSet<>(selectServices))
                        .categoryFilters(new HashSet<>(selectedFilter))
                        .build();
                womenRepositoryPort.save(women);
                responseMessage = "Has registrado con éxito esta chica";
                status = HttpStatus.CREATED;
            }else{
                responseMessage = "Esta chica ya ha sido registrada";
                status = HttpStatus.CONFLICT;
            }
        }catch (Exception e){
            responseMessage = "Error en el registro: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseMessage, status);
    }

    public ResponseEntity<String> updateInfoWomenAdmin(RegisterWomenAdminRequest request) {
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
            women.setName(request.getName());
            women.setSmoker(request.getSmoker());
            women.setTattoos(request.getTattoos());
            women.setWeight(request.getWeight());
            if (request.getSelectedServiceIds() != null && !request.getSelectedServiceIds().isEmpty()) {
                List<Services> selectServices = serviceRepositoryPort.findByIdServiceIn(request.getSelectedServiceIds());
                women.setServices(new HashSet<>(selectServices));
            }
            if (request.getSelectedFilterNames() != null && !request.getSelectedFilterNames().isEmpty()) {
                List<CategoryFilter> selectFilter = categoryFilterRepositoryPort.findByNameIn(request.getSelectedFilterNames());
                women.setCategoryFilters(new HashSet<>(selectFilter));
            }
            if (request.getPhotoProfile() != null){
                user.setProfilePhoto(request.getPhotoProfile());
            }
            userRepositoryPort.save(user);
            womenRepositoryPort.save(women);

            return new ResponseEntity<>("Datos actualizados correctamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al registrar los datos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteWomen(String userName){
        Optional<Women> womenExist = womenRepositoryPort.findByUser_UserName(userName);
        if (womenExist.isPresent()){
            womenRepositoryPort.delete(womenExist.get());
            return new ResponseEntity<>("Chica eliminada correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Chica no encontrado", HttpStatus.NOT_FOUND);
    }

}
