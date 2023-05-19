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

    private Auxdata Auxdata;

    public String getName() {
        return name;
    }
    @NonNull
    public String toString() {
        return name + " " + location;
    }
}
