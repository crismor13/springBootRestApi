package com.example.sophosApi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Cita")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long appointmentId;
    @NotNull(message = "Date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @NotNull(message = "Hour is mandatory")
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime hour;

    @ManyToOne()
    @JoinColumn(name = "testId", referencedColumnName = "testId")
    @NotNull(message = "Test id is mandatory")
    private TestModel test;

    @ManyToOne
    @JoinColumn(name = "affiliateId", referencedColumnName = "affiliateId")
    @NotNull(message = "Affiliate id is mandatory")
    private AffiliateModel affiliate;


    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
//
//    public TestModel getTest() {
//        return prueba;
//    }
//
//    public void setTest(TestModel prueba) {
//        this.prueba = prueba;
//    }
//
//    public AffiliateModel getAffiliate() {
//        return affiliate;
//    }
//
//    public void setAffiliate(AffiliateModel affiliate) {
//        this.affiliate = affiliate;
//    }

//    public AppointmentModel(Long id, LocalDate date, LocalTime hour, TestModel prueba, AffiliateModel affiliate) {
//        this.id = id;
//        this.date = date;
//        this.hour = hour;
//    }
//
//    public AppointmentModel() {
//    }

    public TestModel getTest() {
        return test;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }

    public AffiliateModel getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(AffiliateModel affiliate) {
        this.affiliate = affiliate;
    }

    public AppointmentModel(Long appointmentId, LocalDate date, LocalTime hour, TestModel test, AffiliateModel affiliate) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.hour = hour;
        this.test = test;
        this.affiliate = affiliate;
    }
    public AppointmentModel() {

    }
}
