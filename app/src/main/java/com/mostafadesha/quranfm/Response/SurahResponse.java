package com.mostafadesha.quranfm.Response;

import com.google.gson.annotations.SerializedName;
import com.mostafadesha.quranfm.Models.Surah;

import java.util.List;

public class SurahResponse {


    @SerializedName("data")
    private List<Surah> list;

    public List<Surah> getList() {
        return list;
    }

    public void setList(List<Surah> list) {
        this.list = list;
    }
}
