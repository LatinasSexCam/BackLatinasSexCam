package com.LatinasSexCam.infrastructure.entity;

import com.LatinasSexCam.domain.model.MultimediaType;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class MultimediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedia;

    @Enumerated(EnumType.STRING)
    private MultimediaType mediaType;

    private String url;


    @ManyToOne
    @JoinColumn(name = "fkid_women", nullable = false)
    @JsonBackReference
    private WomenEntity women;

}