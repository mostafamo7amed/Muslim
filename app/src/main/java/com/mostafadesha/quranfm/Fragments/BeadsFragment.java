package com.mostafadesha.quranfm.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafadesha.quranfm.R;
public class BeadsFragment extends Fragment {
    ImageView restart;
    TextView beadsCounter;
    CardView addCounter;
    int counter = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restart = getActivity().findViewById(R.id.restart_IV);
        beadsCounter = getActivity().findViewById(R.id.beads_tv);
        addCounter = getActivity().findViewById(R.id.add_card);

        beadsCounter.setText(String.valueOf(counter));

        addCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter+=1;
                beadsCounter.setText(String.valueOf(counter));
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter=0;
                beadsCounter.setText(String.valueOf(counter));

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beads, container, false);
    }
}