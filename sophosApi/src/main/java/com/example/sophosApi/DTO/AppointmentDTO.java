package com.example.sophosApi.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {

    private Long appointmentId;
    private LocalDate date;
    private LocalTime hour;
    private Long test;
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
