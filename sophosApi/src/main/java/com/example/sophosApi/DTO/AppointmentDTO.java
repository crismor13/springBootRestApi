package com.example.sophosApi.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {

    private Long appointmentId;
    @NotNull(message = "Date is mandatory")
    private LocalDate date;
    @NotNull(message = "Hour is mandatory")
    private LocalTime hour;
    @NotNull(message = "Test id is mandatory")
    private Long test;
    @NotNull(message = "Affiliate id is mandatory")
    private Long affiliate;

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

    public Long getTest() {
        return test;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public Long getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Long affiliate) {
        this.affiliate = affiliate;
    }


    public AppointmentDTO() {

    }
}
