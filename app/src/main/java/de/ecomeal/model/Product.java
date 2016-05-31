package de.ecomeal.model;


import android.graphics.drawable.Drawable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LS on 26.05.2016.
 */
@IgnoreExtraProperties
public class Product {

    private String name;
    private String image;
    private String textColor;
    private String detailDescriprtion;

    public Product(String name, String image, String textColor, String detailDescriprtion) {
        this.name = name;
        this.image = image;
        this.textColor = textColor;
        this.detailDescriprtion = detailDescriprtion;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailDescriprtion() {
        return detailDescriprtion;
    }

    public void setDetailDescriprtion(String detailDescriprtion) {
        this.detailDescriprtion = detailDescriprtion;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

}
