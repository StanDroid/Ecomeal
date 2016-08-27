package de.ecomeal.model;

/**
 * Created by LS on 20.06.2016.
 */

public class Address {

    private double lat;
    private double lng;
    private String line;
    private String obl;
    private boolean eat;
    private boolean buy;

    public Address(double lat, double lng, String line, String obl, boolean eat, boolean buy) {
        this.lat = lat;
        this.lng = lng;
        this.line = line;
        this.obl = obl;
        this.eat = eat;
        this.buy = buy;
    }

    public Address() {

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getObl() {
        return obl;
    }

    public void setObl(String obl) {
        this.obl = obl;
    }

    public boolean isEat() {
        return eat;
    }

    public void setEat(boolean eat) {
        this.eat = eat;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}
