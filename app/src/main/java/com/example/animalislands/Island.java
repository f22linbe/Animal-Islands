package com.example.animalislands;

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
}
