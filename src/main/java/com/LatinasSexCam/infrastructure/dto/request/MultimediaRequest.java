package com.LatinasSexCam.infrastructure.dto.request;

import com.LatinasSexCam.domain.model.MultimediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaRequest {

    private String user_name;
    private String url;
    private MultimediaType mediaType;
}
