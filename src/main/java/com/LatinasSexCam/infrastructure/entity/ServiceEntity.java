package com.LatinasSexCam.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    private String title;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private Set<WomenEntity> women;

    @OneToMany(mappedBy = "service")
    private Set<SubServiceEntity> subServices;

    @Override
    public int hashCode() {
        return Objects.hash(idService); // Usa solo el ID
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ServiceEntity that = (ServiceEntity) obj;
        return Objects.equals(idService, that.idService); // Compara solo IDs
    }

}
