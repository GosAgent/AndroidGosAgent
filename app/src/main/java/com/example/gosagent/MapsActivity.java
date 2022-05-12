package com.example.gosagent;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.fragment.app.FragmentActivity;

import com.example.gosagent.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private SearchView searchView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PluginCore.init();

        // эмитируем многопоточность, после добовления экрана загрузики - вынесим в отдельным поток
        try {
            PluginCore.DB.GetMarkersData();
            PluginCore.DB.DataReading.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LatLng NSK = new LatLng(55.0415, 82.9346);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(NSK));

        googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

        for (MarkerData lot : PluginCore.Marker) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(lot.coordinatesX, lot.coordinatesY));
            markerOptions.icon(PluginCore.bitmapDescriptorFromVector(this));

            Marker marker = googleMap.addMarker(markerOptions);

            marker.setTag(lot.coordinatesX + " " + lot.coordinatesY);
            marker.showInfoWindow();
        }
        
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        PluginCore.LotInformationData.clear();

        String[] coordinates = marker.getTag().toString().split(" ");
        // эмитируем многопоточность, после добовления экрана загрузики - вынесим в отдельным поток
        try {
            PluginCore.DB.GetLotData(coordinates[0], coordinates[1]);
            PluginCore.DB.DataReading.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(".info_window_layout");
        startActivity(intent);

        return false;
    }
}