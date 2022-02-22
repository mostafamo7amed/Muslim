package com.mostafadesha.quranfm.Request;

import com.mostafadesha.quranfm.Data.Constants;
import com.mostafadesha.quranfm.Data.RadioInterface;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static Retrofit instance;

    public static Retrofit getRetrofit(){
        if (instance == null){
            instance = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static Retrofit getInstance(){
        if(instance!= null) {
            instance = null;
        }

        instance = new Retrofit.Builder().baseUrl(Constants.SURAH_TRANSLATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }
    public static Retrofit getTimings(){
        if(instance!= null) {
            instance = null;
        }

        instance = new Retrofit.Builder().baseUrl(Constants.PRAYER_TIME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

}
