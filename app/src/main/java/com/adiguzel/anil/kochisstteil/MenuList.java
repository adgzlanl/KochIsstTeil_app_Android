package com.adiguzel.anil.kochisstteil;

/**
 * Created by lenovo on 24.09.2017.
 */

public class MenuList {

    private String name;
    private String desc;
    private String duration;
    private String id;

    public MenuList(String name, String desc ,String id ,String duration) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.duration=duration;
    }


    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getId() {return id; }

    public String getDuration() {return duration; }
}
