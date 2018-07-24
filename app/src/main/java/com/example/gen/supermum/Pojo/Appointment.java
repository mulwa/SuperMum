package com.example.gen.supermum.Pojo;

public class Appointment {
    private String appointmentWith;
    private String purpose;
    private String time;
    private String date;

    public Appointment() {
    }

    public Appointment(String appointmentWith, String purpose,
                       String time, String date) {
        this.appointmentWith = appointmentWith;
        this.purpose = purpose;
        this.time = time;
        this.date = date;
    }

    public String getAppointmentWith() {
        return appointmentWith;
    }

    public void setAppointmentWith(String appointmentWith) {
        this.appointmentWith = appointmentWith;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
