package com.ankitshubham.gocorona.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ankitshubham.gocorona.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static GoogleMap mMap;
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
        homeViewModel =
          ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {

                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);

                mMap.clear(); //clear old markers
                CameraPosition googlePlex = CameraPosition.builder()
                  .target(new LatLng(12.9522902,77.5830063))
                  .zoom(10)
                  .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);


                ArrayList<LatLng> list = new ArrayList<LatLng>();
                InputStream inputStream = getResources().openRawResource(R.raw.police_stations);
                String json = new Scanner(inputStream).useDelimiter("\\A").next();
                try{
                    JSONArray array = new JSONArray(json);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        double lat = object.getDouble("lat");
                        double lng = object.getDouble("lng");
                        list.add(new LatLng(lat, lng));
                    }
                } catch (JSONException e) {
                    System.err.println(e.toString());
                }

//                return list;

//                List<LatLng> list = null;
//
//                // Get the data: latitude/longitude positions of police stations.
//                try {
//                    list = readItems(R.raw.police_stations);
//                } catch (JSONException e) {
//                    Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
//                }

                // Create a heat map tile provider, passing it the latlngs of the police stations.
                mProvider = new HeatmapTileProvider.Builder().maxIntensity(5)
                  .data(list)
                  .build();
                // Add a tile overlay to the map, using the heat map tile provider.
                mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

//                CameraPosition googlePlex = CameraPosition.builder()
//                  .target(new LatLng(37.4219999,-122.0862462))
//                  .zoom(10)
//                  .bearing(0)
//                  .tilt(45)
//                  .build();
//
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
//
//                mMap.addMarker(new MarkerOptions()
//                  .position(new LatLng(37.4219999, -122.0862462))
//                  .title("Spider Man")
//                  .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));
//
//                mMap.addMarker(new MarkerOptions()
//                  .position(new LatLng(37.4629101,-122.2449094))
//                  .title("Iron Man")
//                  .snippet("His Talent : Plenty of money"));
//
//                mMap.addMarker(new MarkerOptions()
//                  .position(new LatLng(37.3092293,-122.1136845))
//                  .title("Captain America"));
            }
        });

        return root;
    }
//    private void addHeatMap() {
//        List<LatLng> list = null;
//
//        // Get the data: latitude/longitude positions of police stations.
//        try {
//            list = readItems(R.raw.police_stations);
//        } catch (JSONException e) {
//            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
//        }
//
//        // Create a heat map tile provider, passing it the latlngs of the police stations.
//        mProvider = new HeatmapTileProvider.Builder()
//          .data(list)
//          .build();
//        // Add a tile overlay to the map, using the heat map tile provider.
//        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
//    }
////
//    private ArrayList<LatLng> readItems(int resource) throws JSONException {
//        ArrayList<LatLng> list = new ArrayList<LatLng>();
//        InputStream inputStream = getResources().openRawResource(resource);
//        String json = new Scanner(inputStream).useDelimiter("\\A").next();
//        JSONArray array = new JSONArray(json);
//        for (int i = 0; i < array.length(); i++) {
//            JSONObject object = array.getJSONObject(i);
//            double lat = object.getDouble("lat");
//            double lng = object.getDouble("lng");
//            list.add(new LatLng(lat, lng));
//        }
//        return list;
//    }
}
