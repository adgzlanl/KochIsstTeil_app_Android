package com.adiguzel.anil.kochisstteil;

/**
 * Created by lenovo on 24.09.2017.
 */

public class CombinationList {

    private String mname;
    private String mmenu;
    private String smenu;
    private String desert;
    private String soup;
    private String salad;
    private String duration;
    private String id;

    public CombinationList(String mname, String mmenu, String smenu, String desert, String soup, String salad, String duration, String id) {
        this.mname = mname;
        this.mmenu = mmenu;
        this.smenu = smenu;
        this.desert = desert;
        this.soup = soup;
        this.salad = salad;
        this.duration = duration;
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public String getMmenu() {
        return mmenu;
    }

    public String getSmenu() {
        return smenu;
    }

    public String getDesert() {
        return desert;
    }

    public String getSoup() {
        return soup;
    }

    public String getSalad() {
        return salad;
    }

    public String getDuration() {
        return duration;
    }

    public String getId() {
        return id;
    }
}
