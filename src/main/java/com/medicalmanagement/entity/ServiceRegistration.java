package com.medicalmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "service_registration")
public class ServiceRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit")
    private String unit;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private UserEntity user;

    @OneToMany(mappedBy = "serviceReg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bill> bills;

    @OneToOne(mappedBy = "serviceReg", cascade = CascadeType.ALL, orphanRemoval = true)
    private ResultExamination resultExam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
