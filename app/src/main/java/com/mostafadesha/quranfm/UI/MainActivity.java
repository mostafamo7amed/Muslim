package com.mostafadesha.quranfm.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mostafadesha.quranfm.Data.Constants;
import com.mostafadesha.quranfm.Fragments.AdditionalFragment;
import com.mostafadesha.quranfm.Fragments.BeadsFragment;
import com.mostafadesha.quranfm.Fragments.CompassFragment;
import com.mostafadesha.quranfm.Fragments.PrayerTimeFragment;
import com.mostafadesha.quranfm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FusedLocationProviderClient fusedLocationProviderClient;
    double longitude,latitude;
    private RequestQueue requestQueue;
    Bundle bundle = new Bundle();
    Bundle  compassBundle = new Bundle();
    CountDownTimer countDownTimer;
    long timeLeftInMilli;
    TextView nextPrayer, remainingTime ,zone,prayerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        nextPrayer = findViewById(R.id.nextTime);
        remainingTime = findViewById(R.id.remainingTime);
        zone = findViewById(R.id.zone);
        prayerName = findViewById(R.id.prayerName);

        requestQueue = Volley.newRequestQueue(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(OnSelect);

        request();


    }

    private final BottomNavigationView.OnItemSelectedListener OnSelect = new BottomNavigationView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected = null;
            int select=0;

            switch (item.getItemId()) {
                case R.id.prayer: {
                    selected = new PrayerTimeFragment();
                    selected.setArguments(bundle);
                    select =1;
                }
                break;
                case R.id.home_menu: {
                    selected = new AdditionalFragment();
                    select = 4;
                }
                break;
                case R.id.compass: {
                    selected = new CompassFragment();
                    selected.setArguments(compassBundle);
                    select = 2;
                }
                break;
                case R.id.beads: {
                    selected = new BeadsFragment();
                    select = 3;
                }
                break;
            }
            if (selected != null) {
                if(select != Constants.current) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selected).commit();
                    Constants.current = select;
                }
                else {
                    return true;
                }
            }
            return true;
        }
    };
    public void StartCountDownTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilli,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilli = l;
                int seconds = (int) (timeLeftInMilli / 1000) % 60 ;
                int minutes = (int) ((timeLeftInMilli / (1000*60)) % 60);
                int hours   = (int) ((timeLeftInMilli / (1000*60*60)) % 24);
                String timeRemain = String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,seconds);
                remainingTime.setText(String.format("- %s", timeRemain));
            }

            @Override
            public void onFinish() {
                this.start();

            }
        }.start();

    }

    public void Time(String f,String s ,String d ,String a,String m, String i){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String str = sdf.format(new Date());

        long minutes =Long.parseLong(str.substring(3,5));
        long hours   = Long.parseLong(str.substring(0,2));
        long currentTime =(hours *60*60+minutes*60)*1000;

        long fh = Long.parseLong(f.substring(0,2));
        long fm = Long.parseLong(f.substring(3,5));
        long fajrTime =(fh *60*60+fm*60)*1000;

        long sh = Long.parseLong(s.substring(0,2));
        long sm= Long.parseLong( s.substring(3,5));
        long sunTime =(sh *60*60+sm*60)*1000;

        long dh = Long.parseLong(d.substring(0,2));
        long dm = Long.parseLong(d.substring(3,5));
        long duhrTime =(dh *60*60+dm*60)*1000;


        long ah = Long.parseLong(a.substring(0,2));
        long am = Long.parseLong(a.substring(3,5));
        long asrTime =(ah *60*60+am*60)*1000;


        long mh = Long.parseLong(m.substring(0,2));
        long mm = Long.parseLong(m.substring(3,5));
        long magrTime =(mh *60*60+mm*60)*1000;


        long ih = Long.parseLong( i.substring(0,2));
        long im = Long.parseLong( i.substring(3,5));
        long ishaTime =(ih *60*60+im*60)*1000;

        if( currentTime >=0 && currentTime<=fajrTime || currentTime >=ishaTime ) {
            //86400000 = 24:00:00
            timeLeftInMilli = fajrTime + (86400000 - currentTime);
            StartCountDownTimer();
            nextPrayer.setText(f);
            prayerName.setText(R.string.fajr);
        } else if(currentTime >= fajrTime && currentTime <=sunTime){
            timeLeftInMilli = sunTime - currentTime;
            StartCountDownTimer();
            nextPrayer.setText(s);
            prayerName.setText(R.string.sunrise);
        }else if (currentTime>=sunTime && currentTime<=duhrTime){
            timeLeftInMilli = duhrTime - currentTime;
            StartCountDownTimer();
            nextPrayer.setText(d);
            prayerName.setText(R.string.dhuhr);
        }else if(currentTime>=duhrTime && currentTime <=asrTime){
            timeLeftInMilli = asrTime - currentTime;
            StartCountDownTimer();
            nextPrayer.setText(a);
            prayerName.setText(R.string.asr);
        }else if(currentTime>=asrTime && currentTime <=magrTime){
            timeLeftInMilli = magrTime - currentTime;
            StartCountDownTimer();
            nextPrayer.setText(m);
            prayerName.setText(R.string.magrhib);
        }else if(currentTime>=magrTime ){
            timeLeftInMilli = ishaTime - currentTime;
            StartCountDownTimer();
            nextPrayer.setText(i);
            prayerName.setText(R.string.isha);
        }



    }
    public void JsonParsing(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONObject timings = jsonObject1.getJSONObject("timings");

                    bundle.putString("Fajr",timings.getString("Fajr"));
                    bundle.putString("Sunrise",timings.getString("Sunrise"));
                    bundle.putString("Dhuhr",timings.getString("Dhuhr"));
                    bundle.putString("Asr",timings.getString("Asr"));
                    bundle.putString("Maghrib",timings.getString("Maghrib"));
                    bundle.putString("Isha",timings.getString("Isha"));


                    Time(timings.getString("Fajr"),timings.getString("Sunrise"),timings.getString("Dhuhr"),
                            timings.getString("Asr"),timings.getString("Maghrib"),timings.getString("Isha"));

                    JSONObject dateT = jsonObject1.getJSONObject("date");


                    JSONObject gregorian = dateT.getJSONObject("gregorian");
                    JSONObject weekday = gregorian.getJSONObject("weekday");
                    JSONObject month = gregorian.getJSONObject("month");
                    String date_melady =  weekday.getString("en")+" "+month.getString("en")+" "+gregorian.getString("day");
                    bundle.putString("date_melady",date_melady);

                    JSONObject hijri = dateT.getJSONObject("hijri");
                    JSONObject monthH = hijri.getJSONObject("month");
                    String date_hj = hijri.getString("day")+" "+monthH.getString("ar")+" "+hijri.getString("year");
                    bundle.putString("date_hajr",date_hj);
                    PrayerTimeFragment prayerTimeFragment = new PrayerTimeFragment();
                    prayerTimeFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, prayerTimeFragment).commit();


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(request);
    }
    public void CompassDirection(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    compassBundle.putString("compass",jsonObject1.getString("direction"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(request);
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Needed Permission!")
                    .setMessage("this permission to access GPS")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                request();
            }
        }
    }
    private void request(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location=task.getResult();
                    if(location != null)
                    {
                        try {

                            Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                            latitude = addresses.get(0).getLatitude();
                            longitude = addresses.get(0).getLongitude();
                            addresses.get(0).getCountryName();
                            addresses.get(0).getLocality();
                            TimeZone timeZone = TimeZone.getDefault();
                            String id = timeZone.getID();
                            zone.setText(id);
                            CompassDirection(Constants.QIBLA_URL+latitude+"/"+longitude);
                            GetTimeZone getTimeZone = new GetTimeZone(Constants.ZONE_URL+id , latitude , longitude);
                            getTimeZone.execute();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        } else {
            requestPermission();

        }
    }

    public class GetTimeZone extends AsyncTask<String,String,String> {
        String ur;
        double lat , lon;


        public GetTimeZone (String ur , double lat ,double lon) {
            this.ur = ur;
            this.lon = lon;
            this.lat = lat;
        }

        @Override
        protected String doInBackground(String... strings) {
            String current ="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(ur);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int Data =isr.read();
                    while(Data != -1)
                    {
                        current += (char) Data;
                        Data = isr.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null)
                        urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {


            try {
                JSONObject jsonObject = new JSONObject(s);
                String code = jsonObject.getString("data");
                JsonParsing(Constants.TIMINGS_URL+code+"?latitude="+lat+"&longitude="+lon+"&method=2");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}