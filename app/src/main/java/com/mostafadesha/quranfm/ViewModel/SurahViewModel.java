package com.mostafadesha.quranfm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mostafadesha.quranfm.Repository.SurahRep;
import com.mostafadesha.quranfm.Response.SurahResponse;

public class SurahViewModel extends ViewModel {

    private SurahRep surahRep;

    public SurahViewModel() {
        surahRep = new SurahRep();
    }

    public LiveData<SurahResponse> getSurah(){
        return surahRep.getSurah();
    }
}
