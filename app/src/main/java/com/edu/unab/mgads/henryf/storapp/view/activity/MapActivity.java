package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.edu.unab.mgads.henryf.storapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        myMap = googleMap;
        LatLng bogota = new LatLng(4.666761925813064, -74.0988917417561);
        LatLng unab = new LatLng(4.699320830325194, -74.0988917417561);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bogota, 10f));

        googleMap.addMarker(new MarkerOptions().title("UNAB").position(unab));

        updateMap();
    }

    private void updateMap() {
        myMap.setOnMapLongClickListener(latLng -> {
            myMap.addMarker(new MarkerOptions().position(latLng));
        });

        myMap.setOnMapClickListener(latLng -> {
            Toast.makeText(this, "Estoy en "+latLng.latitude+", "+latLng.longitude, Toast.LENGTH_SHORT).show();
        });
    }
}