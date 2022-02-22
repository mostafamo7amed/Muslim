package com.mostafadesha.quranfm.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mostafadesha.quranfm.Data.SurahInterface;
import com.mostafadesha.quranfm.Request.Api;
import com.mostafadesha.quranfm.Response.SurahDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahDetailsRep {
    private SurahInterface surahInterface;

    public SurahDetailsRep() {

        surahInterface= Api.getInstance().create(SurahInterface.class);
    }

    public LiveData<SurahDetailsResponse> getSurahDetails(String trans, int id){
        MutableLiveData<SurahDetailsResponse> data = new MutableLiveData<>();
        surahInterface.getSurahDetails(trans,id).enqueue(new Callback<SurahDetailsResponse>() {
            @Override
            public void onResponse(Call<SurahDetailsResponse> call, Response<SurahDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SurahDetailsResponse> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }
}
