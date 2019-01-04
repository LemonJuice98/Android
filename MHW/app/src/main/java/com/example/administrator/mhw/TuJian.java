package com.example.administrator.mhw;

public class TuJian {
    private String pinyin;
    private String name;
    private String classify;
    private String weak;
    private String state;
    private int id;

    public TuJian(String pinyin, String name, String classify, String weak, String state) {
        this.pinyin = pinyin;
        this.name = name;
        this.classify = classify;
        this.weak = weak;
        this.state = state;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getName() {
        return name;
    }

    public String getClassify() {
        return classify;
    }

    public String getWeak() {
        return weak;
    }

    public String getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
