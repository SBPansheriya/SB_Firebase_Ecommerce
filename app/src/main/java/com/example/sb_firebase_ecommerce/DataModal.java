package com.example.sb_firebase_ecommerce;


public class DataModal {

    String id;
    String pName;
    String pPrice;
    String pDes;
    String pImg;

    public DataModal(String id, String pName, String pPrice, String pDes, String pImg) {
        this.id = id;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDes = pDes;
        this.pImg = pImg;
    }

    public DataModal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDes() {
        return pDes;
    }

    public void setpDes(String pDes) {
        this.pDes = pDes;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    @Override
    public String toString() {
        return "DataModal{" +
                "id='" + id + '\'' +
                ", pName='" + pName + '\'' +
                ", pPrice='" + pPrice + '\'' +
                ", pDes='" + pDes + '\'' +
                ", pImg='" + pImg + '\'' +
                '}';
    }
}
