package org.techtown.myrecyclerview;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dictionary {


    private String region;
    private String category;
    private String number;
    private String restriction;



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }



    public Dictionary(String region, String category, String number, String restriction) {
        this.region = region;
        this.category = category;
        this.number = number;
        this.restriction = restriction;



    }






}