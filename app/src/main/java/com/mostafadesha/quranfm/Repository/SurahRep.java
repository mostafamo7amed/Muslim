package com.mostafadesha.quranfm.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mostafadesha.quranfm.Data.SurahInterface;
import com.mostafadesha.quranfm.Request.Api;
import com.mostafadesha.quranfm.Response.SurahResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahRep {

    private SurahInterface surahInterface;

    public SurahRep(){

        surahInterface = Api.getRetrofit().create(SurahInterface.class);
    }

    public LiveData <SurahResponse> getSurah(){
        MutableLiveData <SurahResponse> data = new MutableLiveData<>();
        surahInterface.getSurah().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }
}
