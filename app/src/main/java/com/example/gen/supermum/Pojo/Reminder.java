package com.example.gen.supermum.Pojo;

public class Reminder {
    private String reminderTitle;
    private String description;
    private String date;
    private String time;

    public Reminder() {
    }

    public Reminder(String reminderTitle, String description,
                    String date, String time) {
        this.reminderTitle = reminderTitle;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


