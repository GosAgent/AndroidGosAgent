package com.example.gosagent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.gosagent.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String id;

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
        LatLng NSK = new LatLng(55.0415, 82.9346);

        googleMap.addMarker( new MarkerOptions().title("1 080 000 - 1 772 400 руб.").position(NSK).icon(PluginCore.bitmapDescriptorFromVector(this))).showInfoWindow();

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(NSK));
        googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(".info_window_layout");
        startActivity(intent);

        return false;
    }
}