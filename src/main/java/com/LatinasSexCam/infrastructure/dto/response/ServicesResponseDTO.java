package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicesResponseDTO {

    private Long idService;
    private String title;

    public ServicesResponseDTO(Services services){
        this.idService = services.getIdService();
        this.title = services.getTitle();
    }
}
