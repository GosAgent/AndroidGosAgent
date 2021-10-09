package com.example.gosagent;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private LatLng coordinate;
    private EditText mSearchText;

    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final float DEFAULT_ZOOM = 15f;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        coordinate = new LatLng(54.985253, 73.367064);

        addMarker(mMap, "АДМИНИСТРАЦИЯ ЧЕРНОЛУЧИНСКОГО ГОРОДСКОГО ПОСЕЛЕНИЯ ОМСКОГО МУНИЦИПАЛЬНОГО РАЙОНА ОМСКОЙ ОБЛАСТИ",
                coordinate, "ОМСКОГО МУНИЦИПАЛЬНОГО РАЙОНА ОМСКОЙ ОБЛАСТИ");

        mMap.setOnInfoWindowClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));

        //mMap.setMyLocationEnabled(true);  // много ошибок !!!!!!!



        init();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map); // так
        mapFragment.getMapAsync(this);
        mSearchText = (EditText) findViewById(R.id.input_search);

        init();

    }

    private void init() {
        // чекаем текст
        Log.d(TAG, "init: ");

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    keyEvent.getAction() == KeyEvent.ACTION_DOWN ||
                    keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    geoLocate();
                }
                return false;
            }
        });
    }

    private void geoLocate() {
        // ищем место в поисковой строке
        Log.d(TAG, "geoLocate: 1");
        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> listAddresses = new ArrayList<>();
        try {
            listAddresses = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.d(TAG, "geoLocate: 2 " + e.getMessage());
        }
        if (listAddresses.size() > 0) {
            Address address = listAddresses.get(0);

            Log.d(TAG, "geoLocate: мы нашли место: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    public void addMarker(GoogleMap map, String title, LatLng coord, String info) {
        Marker marker = map.addMarker(new MarkerOptions().title(title).draggable(true).
                position(coord).snippet(info));


    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: "
                + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(title);
                mMap.addMarker(options);
            }
            hideSoftKeyboard();
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        // set infoWindow
        Toast.makeText(this,"Омский р-н, Чернолучинский дп, Лесная ул",
                Toast.LENGTH_LONG).show();

    }
}