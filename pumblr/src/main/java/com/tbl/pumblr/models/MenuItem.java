package com.tbl.pumblr.models;

/**
 * Created by 201503105229 on 2015/5/2.
 */
public class MenuItem {
    private int icon;
    private String name;

    public MenuItem(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
