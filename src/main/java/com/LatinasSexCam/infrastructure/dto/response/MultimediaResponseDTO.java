package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.model.MultimediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaResponseDTO {
    private MultimediaType mediaType;
    private String url;

    public MultimediaResponseDTO(Multimedia multimedia){
        this.mediaType = multimedia.getMediaType();
        this.url = multimedia.getUrl();
    }

}
