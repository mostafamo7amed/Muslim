package com.mostafadesha.quranfm.Data;

import com.mostafadesha.quranfm.Response.SurahDetailsResponse;
import com.mostafadesha.quranfm.Response.SurahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SurahInterface {
    @GET("surah")
    Call<SurahResponse> getSurah();


    @GET("sura/{language}/{id}")
    Call<SurahDetailsResponse> getSurahDetails(
            @Path("language") String translation,
            @Path("id") int surahId
    );
}
