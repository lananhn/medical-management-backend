package com.medicalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName")
    private String fullName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @NotNull
    @Column(name = "insurance")
    private Integer insurance;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceRegistration> registration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private UserEntity user;
}
