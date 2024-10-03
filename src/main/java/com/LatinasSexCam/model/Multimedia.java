package com.LatinasSexCam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedia;

    @Enumerated(EnumType.STRING)
    private MultimediaType mediaType;

    private String url;


    @ManyToOne
    @JoinColumn(name = "fk_id_women", nullable = false)
    private Women women;

}
