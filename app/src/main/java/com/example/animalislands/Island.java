package com.example.animalislands;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Island {
    @SerializedName("ID")
    private String id;

    private String name;
    private String location;
    private String category;

    @SerializedName("size")
    private int squarekm;

    private Auxdata auxdata;

    public Auxdata getAuxdata() {
        return auxdata;
    }

    public String getDetails() {
        return "ID: " + id + "\nPart of: " + category + "\nSize: " + squarekm + "km²" + "\nPopulation: " + auxdata.getPopulation();
    }
    @NonNull
    public String toString() {
        return name + ", " + location;
    }
}
