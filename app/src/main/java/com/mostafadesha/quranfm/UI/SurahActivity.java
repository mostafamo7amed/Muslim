package com.mostafadesha.quranfm.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.mostafadesha.quranfm.Common.Common;
import com.mostafadesha.quranfm.Adapter.SurahAdapter;
import com.mostafadesha.quranfm.Listener.SurahListener;
import com.mostafadesha.quranfm.Models.Surah;
import com.mostafadesha.quranfm.R;
import com.mostafadesha.quranfm.ViewModel.SurahViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurahActivity extends AppCompatActivity implements SurahListener{

    RecyclerView recyclerView;
    SurahAdapter surahAdapter;
    SurahViewModel surahViewModel;
    List<Surah>list;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getSurah().observe(this, surahResponse -> {
            for(int i=0;i<surahResponse.getList().size();i++){
                list.add(new Surah(surahResponse.getList().get(i).getNumber(),
                        String.valueOf(surahResponse.getList().get(i).getName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishName()),
                        surahResponse.getList().get(i).getNumberOfAyahs(),
                        String.valueOf(surahResponse.getList().get(i).getRevelationType())
                        ));
            }
            if(list.size()!=0){
                surahAdapter = new SurahAdapter(this,list, this);
                recyclerView.setAdapter(surahAdapter);
                surahAdapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public void onSurahListener(int position) {
        Intent intent = new Intent(SurahActivity.this, SurahDetailsActivity.class);
        intent.putExtra(Common.SURAH_NO,list.get(position).getNumber());
        intent.putExtra(Common.SURAH_NAME,list.get(position).getName());
        intent.putExtra(Common.SURAH_TOTAL_AYA,list.get(position).getNumberOfAyahs());
        intent.putExtra(Common.SURAH_TYPE,list.get(position).getRevelationType());
        intent.putExtra(Common.SURAH_TRANSLATION,list.get(position).getEnglishName());
        startActivity(intent);
    }
}