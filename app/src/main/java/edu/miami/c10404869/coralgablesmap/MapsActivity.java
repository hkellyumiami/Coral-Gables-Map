package edu.miami.c10404869.coralgablesmap;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnInfoWindowClickListener{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Adds historic markers in Coral Gables and moves the camera
        mMap.setOnInfoWindowClickListener(this);
        LatLng coralGables = new LatLng(25.748799, -80.279696);
        LatLng congChurch = new LatLng(25.742515, -80.278497);
        mMap.addMarker(new MarkerOptions().position(congChurch).title("Coral Gables Congregational Church"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coralGables,12));
    }

    public void onInfoWindowClick(Marker marker){
        //When an infowindow is clicked, the selected location is returned to the menu activity
        switch (marker.getTitle()) {
            case "Coral Gables Congregational Church":
                Log.i("Infowindow clicked", "CGCC");
                Intent data = new Intent();
                data.putExtra("view_id",1);
                setResult(RESULT_OK,data);
                finish();
                break;
            }
        }
}
