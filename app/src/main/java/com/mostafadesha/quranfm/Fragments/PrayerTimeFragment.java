package com.mostafadesha.quranfm.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mostafadesha.quranfm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class PrayerTimeFragment extends Fragment {


    RelativeLayout relativeLayout;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView fajr , sunrise , duher ,asr , magrhib ,isha, date_tv ,date_hajr;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        relativeLayout= getActivity().findViewById(R.id.container_Relative);
        shimmerFrameLayout=getActivity().findViewById(R.id.shimmer_prayer_time);


        fajr = getActivity().findViewById(R.id.Fajr);
        sunrise = getActivity().findViewById(R.id.Sunrise);
        duher = getActivity().findViewById(R.id.Dhuhr);
        asr = getActivity().findViewById(R.id.Asr);
        magrhib = getActivity().findViewById(R.id.Magrhib);
        isha = getActivity().findViewById(R.id.Isha);
        date_tv = getActivity().findViewById(R.id.date_tv);
        date_hajr = getActivity().findViewById(R.id.date_hujr);
        shimmerFrameLayout.startShimmer();

        if(getArguments()!=null){
            fajr.setText(getArguments().getString("Fajr"));
            sunrise.setText(getArguments().getString("Sunrise"));
            duher.setText(getArguments().getString("Dhuhr"));
            asr.setText(getArguments().getString("Asr"));
            magrhib.setText(getArguments().getString("Maghrib"));
            isha.setText(getArguments().getString("Isha"));
            date_tv.setText(getArguments().getString("date_melady"));
            date_hajr.setText(getArguments().getString("date_hajr"));
        }

        relativeLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmer();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prayer_time, container, false);

    }

















}