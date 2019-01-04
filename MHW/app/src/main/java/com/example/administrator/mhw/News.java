package com.example.administrator.mhw;

public class News {
    private int resid;
    private int id;
    private String title;
    private String time;
    private String message;

    public News(int id, String title, String time, String message) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.message = message;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
