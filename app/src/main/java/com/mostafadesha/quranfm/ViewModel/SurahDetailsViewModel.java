package com.mostafadesha.quranfm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mostafadesha.quranfm.Repository.SurahDetailsRep;
import com.mostafadesha.quranfm.Response.SurahDetailsResponse;

public class SurahDetailsViewModel extends ViewModel {
    private SurahDetailsRep surahDetailsRep;

    public SurahDetailsViewModel() {
        surahDetailsRep= new SurahDetailsRep();
    }

    public LiveData<SurahDetailsResponse> getSurahDetails(String trans,int id){
        return surahDetailsRep.getSurahDetails(trans,id);
    }
}
