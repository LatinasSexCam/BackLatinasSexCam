package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.SubService;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SubServiceResponseDTO {

    private String description;
    private double price;
    private String quantity;
    private String time;

    public SubServiceResponseDTO(SubService subService){
        this.description = subService.getDescription();
        this.price = subService.getPrice();
        this.quantity = subService.getQuantity();
        this.time = subService.getTime();
    }
}
