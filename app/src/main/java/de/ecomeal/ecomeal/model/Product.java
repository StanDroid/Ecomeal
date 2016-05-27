package de.ecomeal.ecomeal.model;


import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * Created by LS on 26.05.2016.
 */
public class Product {

    private String name;
    private Drawable image;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private int textColor;
    private String detailDescriprtion;

    public Product(String name, Drawable image, int textColor, String detailDescriprtion) {
        this.name = name;
        this.image = image;
        this.textColor = textColor;
        this.detailDescriprtion = detailDescriprtion;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDetailDescriprtion() {
        return detailDescriprtion;
    }

    public void setDetailDescriprtion(String detailDescriprtion) {
        this.detailDescriprtion = detailDescriprtion;
    }
}
