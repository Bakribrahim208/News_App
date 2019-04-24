package com.bakribrahim.newsapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class source implements Serializable {

         @SerializedName("id")
        public  String id ;
        @SerializedName("name")
        public String name;

    public source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
