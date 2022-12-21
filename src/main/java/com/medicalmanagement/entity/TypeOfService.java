package com.medicalmanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "type_of_service")
public class TypeOfService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "typeOfService", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiceEntity> services;
}
