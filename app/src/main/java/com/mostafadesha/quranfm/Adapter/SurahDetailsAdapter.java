package com.mostafadesha.quranfm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafadesha.quranfm.Models.SurahDetails;
import com.mostafadesha.quranfm.R;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailsAdapter extends RecyclerView.Adapter<SurahDetailsAdapter.ViewHolder> {

    private  Context context;
    private List<SurahDetails> list;

    public SurahDetailsAdapter(Context context, List<SurahDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_detalis_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.trans.setText(String.valueOf(list.get(position).getTranslation()));
        holder.arabic.setText(String.valueOf(list.get(position).getArabic_text()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView arabic;
         TextView trans;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            arabic= itemView.findViewById(R.id.arabic_txt);
            trans = itemView.findViewById(R.id.trans_txt);
        }
    }

    public void filter(ArrayList<SurahDetails> details){
        list = details;
        notifyDataSetChanged();
    }

}
