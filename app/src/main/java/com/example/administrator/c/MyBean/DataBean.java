package com.example.administrator.c.MyBean;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/5/2 16:41
 */

public class DataBean {
    private String img;
    private String name;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataBean(String img, String name) {

        this.img = img;
        this.name = name;
    }
}
