package com.mostafadesha.quranfm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafadesha.quranfm.Listener.SurahListener;
import com.mostafadesha.quranfm.Models.Surah;
import com.mostafadesha.quranfm.R;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {

    private Context context;
    private List<Surah> list;
    SurahListener surahListener;

    public SurahAdapter(Context context, List<Surah> list , SurahListener surahListener ) {
        this.context = context;
        this.list = list;
        this.surahListener=surahListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_item,parent, false);
        return new ViewHolder(view,surahListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number_aya.setText("Aya : "+list.get(position).getNumberOfAyahs());
        holder.ar_name.setText(list.get(position).getName());
        holder.en_name.setText(list.get(position).getEnglishName());
        holder.number_surah.setText(String.valueOf(list.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView en_name,ar_name,number_aya,number_surah;
        public ViewHolder(@NonNull View itemView,SurahListener surahListener) {
            super(itemView);
            en_name=itemView.findViewById(R.id.en_name);
            ar_name=itemView.findViewById(R.id.ar_name);
            number_aya=itemView.findViewById(R.id.number_aya);
            number_surah= itemView.findViewById(R.id.number_surah);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    surahListener.onSurahListener(getAdapterPosition());
                }
            });
        }
    }
}
