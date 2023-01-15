package com.medicalmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "money_received")
    private Float moneyReceived;

    @Column(name = "health_insurance_payment")
    private Float healthInsurance;

    @Column(name = "surplus")
    private Float surplus;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "service_registration_id")
    private ServiceRegistration serviceReg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountant_id")
    private User accountantId;
}
