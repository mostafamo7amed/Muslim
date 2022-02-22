package com.mostafadesha.quranfm.Response;

import com.google.gson.annotations.SerializedName;
import com.mostafadesha.quranfm.Models.SurahDetails;

import java.util.List;

public class SurahDetailsResponse {

    @SerializedName("result")
    private List<SurahDetails> list;

    public List<SurahDetails> getList() {
        return list;
    }

    public void setList(List<SurahDetails> list) {
        this.list = list;
    }
}
