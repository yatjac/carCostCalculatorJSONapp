package edu.txstate.jpl77.lafoefinal;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {
    private int id;
    private String make;
    private String model;
    private String url;
    private double msrp;

    public Car(int id, String make, String model, String url, double msrp) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.url = url;
        this.msrp = msrp;
    }
    public Car() {
    }

    public Car(JSONObject object) {
        try {
            this.id = object.getInt("Id");
            this.make = object.getString("Make");
            this.model = object.getString("Model");
            this.url = object.getString("URL");
            this.msrp = object.getDouble("MSRP");
        }
        catch (JSONException e){
            e.printStackTrace();
        }


    }


    @NonNull
    @Override
    public String toString() {
        return id + ": " + make + " " + model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void getModel(String model) {
        this.model = model;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
