package ru.myproject.practika1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

public class Fragment_2 extends Fragment {


    TextView textView;
    String[] name_country = new String[10];
    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static List<String> name_country_list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);

        swipeRefreshLayout=view.findViewById(R.id.my_refresh_layout);


        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        name_country[0] = "Список пуст!";



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            LocationManager locationManager = (LocationManager) getActivity()
                    .getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                String country = hereLocation(location.getLatitude(), location.getLongitude());
                textView.setText(country);
                name_country[1]=country;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Not found!", Toast.LENGTH_SHORT).show();
            }
        }
        name_country_list = Arrays.asList(name_country);
        adapter = new MyRecyclerViewAdapterSecond(name_country_list,null,null);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                new MyParser(getActivity(),recyclerView).execute();
            }
        });
        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1000: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getActivity()
                            .getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        String country =hereLocation(location.getLatitude(),location.getLongitude());
                        textView.setText(country);
                        name_country[1]=country;
                        return;
                    }

                }else{
                    Toast.makeText(getActivity(),"Permission not granted",Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }

    }

    private String hereLocation (double lat, double lon){
        String countryName ="";

        Geocoder geocoder =new Geocoder(getActivity(),Locale.getDefault());
        List<Address> addresses;
        try{
            addresses=geocoder.getFromLocation(lat,lon,10);
            if(addresses.size()>0){
               // for(Address adr:addresses){
                   // if (adr.getCountryName()!=null&&adr.getCountryName().length()>0){
                        countryName=addresses.get(0).getCountryName();
                   //     break;
                   // }
                //}
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return countryName;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}