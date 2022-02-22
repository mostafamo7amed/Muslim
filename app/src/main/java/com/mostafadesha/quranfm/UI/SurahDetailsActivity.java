package com.mostafadesha.quranfm.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mostafadesha.quranfm.Common.Common;
import com.mostafadesha.quranfm.Adapter.SurahDetailsAdapter;
import com.mostafadesha.quranfm.Models.SurahDetails;
import com.mostafadesha.quranfm.R;
import com.mostafadesha.quranfm.ViewModel.SurahDetailsViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurahDetailsActivity extends AppCompatActivity {

    TextView surahName , startTime ,totalTime;
    RecyclerView recyclerView;
    androidx.appcompat.widget.SearchView searchView;
    ImageButton setting , play ,iBack;
    List<SurahDetails> list;
    SurahDetailsAdapter surahDetailsAdapter;
    SurahDetailsViewModel surahDetailsViewModel;
    MediaPlayer mediaPlayer;
    ShimmerFrameLayout shimmerFrameLayout;
    SeekBar seekBar;
    Handler handler = new Handler();
    String trans ="english_saheeh" , str="" ,qari="ahmed_ibn_3ali_al-3ajamy";
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_deitals);

        surahName = findViewById(R.id.de_name);
        recyclerView = findViewById(R.id.de_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        iBack = findViewById(R.id.de_back);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        searchView = findViewById(R.id.search_ayah);
        setting = findViewById(R.id.setting);
        list = new ArrayList<>();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                fitter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });
        id = getIntent().getIntExtra(Common.SURAH_NO,0);
        surahName.setText(getIntent().getStringExtra(Common.SURAH_NAME));
        iBack.setOnClickListener(view -> finish());

        surahTranslation(trans,id);


        GetReview review = new GetReview(qari);
        review.execute();
        setting.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SurahDetailsActivity.this
                    ,R.style.BottomSheetDialogTheme);

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view1 =inflater.inflate(R.layout.bottom_sheet_layout,findViewById(R.id.sheetContainer));

            Spinner spinner=view1.findViewById(R.id.translate_sp);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SurahDetailsActivity.this,R.array.Translation
            , android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String language = adapterView.getItemAtPosition(i).toString();
                    switch (language) {
                        case "Hindi":
                        case "الهندية":
                            trans = "hindi_omari";
                            break;
                        case "English":
                        case "الانجليزية":
                            trans = "english_saheeh";
                            break;
                        case "French":
                        case "الفرنسية":
                            trans = "french_montada";
                            break;
                        case "Spanish":
                        case "الاسبانية":
                            trans = "spanish_montada_eu";
                            break;
                        case "Portuguese":
                        case "البرتغالية":
                            trans = "portuguese_nasr";
                            break;
                        case "German":
                        case "الالمانية":
                            trans = "german_bubenheim";
                            break;
                        case "Turkish":
                        case "التركية":
                            trans = "turkish_rwwad";
                            break;
                        case "Indonesian":
                        case "الاندونيسية":
                            trans = "indonesian_sabiq";
                            break;
                        case "Tagalog":
                        case "التغالوغية":
                            trans = "tagalog_rwwad";
                            break;
                        case "Persian":
                        case "الفارسية":
                            trans = "persian_ih";
                            break;
                        case "Chinese":
                        case "الصينية":
                            trans = "chinese_makin";
                            break;
                        case "Japanese":
                        case "اليبانية":
                            trans = "japanese_saeedsato";
                            break;
                        case "Vietnamese":
                        case "الفيتنامية":
                            trans = "vietnamese_rwwad";
                            break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Spinner audioSpinner = view1.findViewById(R.id.audio_sp);
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(SurahDetailsActivity.this,R.array.Qari
                    , android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            audioSpinner.setAdapter(adapter1);
            audioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String sp = adapterView.getItemAtPosition(i).toString();
                    switch (sp) {
                        case "AbdulBaset AbdulSamad [Murattal]":
                        case "عبد الباسط عبد الصمد":
                            qari = "abdul_basit_murattal";
                            break;
                        case "AbdulMuhsin al-Qasim":
                        case "عبد المحسن القاسم":
                            qari = "abdul_muhsin_alqasim";
                            break;
                        case "Abdullah Awad al-Juhani":
                        case "عبد الله عواد الجهني":
                            qari = "abdullaah_3awwaad_al-juhaynee";
                            break;
                        case "Yasser ad-Dussary":
                        case "ياسر الدوسري":
                            qari = "yasser_ad-dussary";
                            break;
                        case "Abdullah Basfar":
                        case "عبد الله بن علي بصفر":
                            qari = "abdullaah_basfar";
                            break;
                        case "Maher al-Muaiqly":
                        case "ماهر المعيقلي":
                            qari = "maher_almu3aiqly/year1440";
                            break;
                        case "Mishari Rashid al-`Afasy":
                        case "مشاري راشد العفاسي":
                            qari = "mishaari_raashid_al_3afaasee";
                            break;
                        case "Fares Abbad":
                        case "فارس عباد":
                            qari = "fares";
                            break;
                        case "Sudais and Shuraym":
                        case "السديس والشريم":
                            qari = "sodais_and_shuraim";
                            break;
                        case "Ahmed ibn Ali al-Ajmy":
                        case "أحمد علي العجمي":
                            qari = "ahmed_ibn_3ali_al-3ajamy";
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            view1.findViewById(R.id.translate_SV).setOnClickListener(view2 -> {

                recyclerView.setVisibility(View.GONE);
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                surahTranslation(trans, id);
                mediaPlayer.reset();
                mediaPlayer.release();

                GetReview review1 = new GetReview(qari);
                review1.execute();
                bottomSheetDialog.dismiss();

            });


            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();
        });



    }

    private void fitter(String s) {
        ArrayList<SurahDetails>arrayList= new ArrayList<>();
        for (SurahDetails details : list){
            if(String.valueOf(details.getAya()).equals(s)){
                arrayList.add(details);
            }
        }
        surahDetailsAdapter.filter(arrayList);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void surahTranslation(String trans, int id){

        if(list.size()>0)
            list.clear();

        surahDetailsViewModel=new ViewModelProvider(this).get(SurahDetailsViewModel.class);
        surahDetailsViewModel.getSurahDetails(trans,id).observe(this,surahDetailsResponse -> {


           for(int i=0;i<surahDetailsResponse.getList().size();i++){
                list.add(new SurahDetails(surahDetailsResponse.getList().get(i).getId(),
                        surahDetailsResponse.getList().get(i).getSura(),
                        surahDetailsResponse.getList().get(i).getAya(),
                        String.valueOf(surahDetailsResponse.getList().get(i).getArabic_text()),
                        String.valueOf(surahDetailsResponse.getList().get(i).getTranslation()),
                        String.valueOf(surahDetailsResponse.getList().get(i).getFootnotes())));
            }
            if(list.size()!=0){
                surahDetailsAdapter = new SurahDetailsAdapter(this,list);
                recyclerView.setAdapter(surahDetailsAdapter);
                surahDetailsAdapter.notifyDataSetChanged();
            }
        });


    }

    private void prepareMediaPlayer(String qari) throws IOException {
        if(id<10){
            str = "00"+id;
        }else if(id<100){
            str = "0"+id;
        }else {
            str = String.valueOf(id);
        }

        mediaPlayer.setDataSource("https://download.quranicaudio.com/quran/"+qari+"/"+str.trim()+".mp3");
        mediaPlayer.prepare();

    }
    private final Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekPar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            startTime.setText(timeToMilliSecond(currentDuration));
        }
    };
    private void updateSeekPar(){
        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }
    private String timeToMilliSecond(long milliSecond){
        String timer ="";
        String secondString;

        int hours = (int)(milliSecond / (1000 * 60 * 60));
        int minutes = (int)(milliSecond % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int)((milliSecond % (1000 * 60 * 60)) % (1000 * 60) /1000);

        if(hours>0){
            timer = hours +":";
        }
        if(second<10){
            secondString = "0"+second;
        }else {
            secondString = ""+second;
        }
        timer =timer+minutes+":"+secondString;
        return timer;
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer.isPlaying()){
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            play.setImageResource(R.drawable.ic_pause);

        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }




    @SuppressLint("StaticFieldLeak")
    public class GetReview extends AsyncTask<String,String,String> {
        String quri;

        public GetReview( String quai) {
            this.quri = quai;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            shimmerFrameLayout.startShimmer();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(String... strings) {

            play = findViewById(R.id.play_btn);
            startTime = findViewById(R.id.start_tv);
            totalTime = findViewById(R.id.total_tv);
            seekBar = findViewById(R.id.seek);
            mediaPlayer = new MediaPlayer();
            seekBar.setMax(100);
            startTime.setText("0:00");

            try {
                prepareMediaPlayer(qari);
            } catch (IOException e) {
                e.printStackTrace();
            }




            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);
        }

        @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
        @Override
        protected void onPostExecute(String s) {
            recyclerView.setVisibility(View.VISIBLE);
            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmer();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

            totalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
            play.setOnClickListener(view -> {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_pause);

                }else{
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_play);
                    updateSeekPar();
                }
            });
            mediaPlayer.setOnCompletionListener(media -> {
                seekBar.setProgress(0);
                startTime.setText("0:00");
                //totalTime.setText("0:00");
                play.setImageResource(R.drawable.ic_play);
                mediaPlayer.reset();
                try {
                    prepareMediaPlayer(qari);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            seekBar.setOnTouchListener((view, motionEvent) -> {
                SeekBar seekBar = (SeekBar) view;
                int playPosition= (mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                startTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
                return false;
            });

            mediaPlayer.setOnBufferingUpdateListener((mediaPlayer, i) ->
                    seekBar.setSecondaryProgress(i));

                }

        }


}