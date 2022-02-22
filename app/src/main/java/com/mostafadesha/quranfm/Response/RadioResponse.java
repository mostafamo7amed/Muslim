package com.mostafadesha.quranfm.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mostafadesha.quranfm.Models.RadioModel;

import java.util.List;

public class RadioResponse {

    @SerializedName("results")
    @Expose
    private List<RadioModel> radio;



    public List<RadioModel> getRadio(){
        return radio;
    }

}
