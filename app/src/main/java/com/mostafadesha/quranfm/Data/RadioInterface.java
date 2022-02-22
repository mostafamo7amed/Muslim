package com.mostafadesha.quranfm.Data;

import com.mostafadesha.quranfm.Models.RadioModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RadioInterface {
    @GET("posts")
    Call<List<RadioModel>> getRadio();
}
