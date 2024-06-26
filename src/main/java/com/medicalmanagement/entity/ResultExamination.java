package com.medicalmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "results_examination")
public class ResultExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result")
    private String result;
    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "service_registration_id")
    private ServiceRegistration serviceReg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private User user;
}
