package com.example.cabbooking;

import android.content.Context;

public class cabItem {

    private String imageUrl, name, area, price, rating, phoneNumber, type, des;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    public cabItem() {
    }

    public cabItem(String imageUrl, String name, String area, String price , String rating, String phoneNumber, String des) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.area = area;
        this.price = price;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getdes() {
        return des;
    }

}
