package com.LatinasSexCam.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Multimedia {
    private Long idMedia;
    private MultimediaType mediaType;
    private String url;
    private Women women;

}
