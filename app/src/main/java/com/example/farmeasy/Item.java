package com.example.farmeasy;

public class Item {
    String name,pest,fert,irri;
    int img;

    public Item(String name, String pest, String fert, String irri, int img) {
        this.name = name;
        this.pest = pest;
        this.fert = fert;
        this.irri = irri;
        this.img = img;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPest() {
        return pest;
    }

    public void setPest(String pest) {
        this.pest = pest;
    }

    public String getFert() {
        return fert;
    }

    public void setFert(String fert) {
        this.fert = fert;
    }

    public String getIrri() {
        return irri;
    }

    public void setIrri(String irri) {
        this.irri = irri;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
