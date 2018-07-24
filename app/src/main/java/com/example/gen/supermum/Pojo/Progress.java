package com.example.gen.supermum.Pojo;

public class Progress {
    private String weekNo;
    private String progressTitle;
    private String progressDesc;

    public Progress(String weekNo, String progressTitle, String progressDesc) {
        this.weekNo = weekNo;
        this.progressTitle = progressTitle;
        this.progressDesc = progressDesc;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public String getProgressTitle() {
        return progressTitle;
    }

    public void setProgressTitle(String progressTitle) {
        this.progressTitle = progressTitle;
    }

    public String getProgressDesc() {
        return progressDesc;
    }

    public void setProgressDesc(String progressDesc) {
        this.progressDesc = progressDesc;
    }
}
