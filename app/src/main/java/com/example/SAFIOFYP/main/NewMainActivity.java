package com.example.SAFIOFYP.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.adapters.PlaceAutoSuggestAdapter;
import com.example.SAFIOFYP.auth.EditProfile;
import com.example.SAFIOFYP.auth.HomeActivity;
import com.example.SAFIOFYP.directions.FetchURL;
import com.example.SAFIOFYP.directions.TaskLoadedCallback;
import com.example.SAFIOFYP.models.Historic;
import com.example.SAFIOFYP.models.LocationDetailResponse;
import com.example.SAFIOFYP.models.LocationResponse;
import com.example.SAFIOFYP.models.Record;
import com.example.SAFIOFYP.models.RecordsBody;
import com.example.SAFIOFYP.network.RestClient;
import com.example.SAFIOFYP.utils.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class NewMainActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private AppBarConfiguration mAppBarConfiguration;
List<Record> bikesPlaces=new ArrayList<>();
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Button clearButton;
    private static final String TAG = "NewMainActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    FirebaseFirestore db;


    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    Button getDirection;
    private Polyline currentPolyline;
    private MarkerOptions place2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e(TAG, "onCreate: " );


        db = FirebaseFirestore.getInstance();

        FirebaseAuth.getInstance().getCurrentUser();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.edit_profile) {
                    Intent intent = new Intent(NewMainActivity.this, EditProfile.class);
                    startActivity(intent);
                } else {
                    if (id == R.id.check_location) {
                        Intent intent = new Intent(NewMainActivity.this, MyLocationActivity.class);
                        startActivity(intent);
                    }
                    else if(id == R.id.checkTrafic){
                        Intent intent = new Intent(NewMainActivity.this, TraficIndexActivity.class);
                        startActivity(intent);
                    }
                    else if(id == R.id.questionnaire) {
                            Intent intent = new Intent(NewMainActivity.this, QuestionsActivity.class);
                            startActivity(intent);
                    }
                    else if(id == R.id.logout) {
                            Intent intent = new Intent(NewMainActivity.this, HomeActivity.class);
                            FirebaseAuth.getInstance().signOut();
                            startActivity(intent);
                            Toast.makeText(NewMainActivity.this, "You Have Successfully Signed Out", Toast.LENGTH_LONG).show();
                        }
                    }
                drawer.close();
                return false;
            }
        });


        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("SAFIO");
        }

        toggle = new ActionBarDrawerToggle(this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        // drawerListener must be set before syncState is called
        drawer.setDrawerListener(toggle);

        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.searchDestination);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            getLocationPermission();
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null){
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            getDirection = findViewById(R.id.directionsBtn);
            getDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new FetchURL(NewMainActivity.this).execute(getUrl((new LatLng(latitude,longitude)), place2.getPosition(), "walking"), "walking");
                }
            });
        }


        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        Log.e(TAG, "onCreate: onMapReadySet" );
        getLocationPermission();



        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(NewMainActivity.this,android.R.layout.simple_list_item_1));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location = autoCompleteTextView.getText().toString();
                List<Address> addressList = null;

                PlaceAutoSuggestAdapter placeAutoSuggestAdapter= (PlaceAutoSuggestAdapter) parent.getAdapter();
//                placeAutoSuggestAdapter.placeDetailsData.get(position).getPlace_id();
                Log.d("Place_id",placeAutoSuggestAdapter.getPlaceDetailsData().get(position).getPlace_id());
                Geocoder geocoder = new Geocoder(NewMainActivity.this);
                try {
                    addressList = geocoder.getFromLocationName(location, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addressList != null;
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                place2 = new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title("Destination");
                mMap.addMarker(place2);

                String place_id = placeAutoSuggestAdapter.getPlaceDetailsData().get(position).getPlace_id();
                Date date = Calendar.getInstance().getTime();
                System.out.println(date);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                int hour = c.get(Calendar.HOUR_OF_DAY);

                Log.d("dayOfWeek", String.valueOf(dayOfWeek));
                Log.d("hour", String.valueOf(hour));

                RequestQueue requestQueue = Volley.newRequestQueue(NewMainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://us-central1-fir-registerlogin-14dba.cloudfunctions.net/getPopularTime", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Data: ",response);
                        String[] res = response.split("//");

                        if(res[1].equals("No Popular Time")){

                        }
                        else {
                            new AlertDialog.Builder(NewMainActivity.this, R.style.CustomAlertDialog)
                                    .setMessage("Live Status : " + res[1] + "\n\nSafety : " + res[0])
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    })
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> prams = new HashMap<>();
                        prams.put("place_id", place_id);
                        prams.put("day", String.valueOf(dayOfWeek));
                        prams.put("hour", String.valueOf(hour));

                        return prams;
                    }
                };

                requestQueue.add(stringRequest);

                Log.d("Address : ",autoCompleteTextView.getText().toString());


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // This is required to make the drawer toggle work
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.home_menu:
                Intent intent = new Intent( NewMainActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        //vars
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();

                            Log.d(TAG, "onComplete: found location!");
                            if (currentLocation==null)
                                return;
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));


                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(NewMainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (mLocationPermissionsGranted) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Location currentLocation = (Location) task.getResult();

                        Log.d(TAG, "onComplete: found location!");
                        if (currentLocation!=null)
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));



                    }else{
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(NewMainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        Log.d("mylog", "Added Markers");


        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }

        loadDataToMap();
    }

    private void loadDataToMap() {
        Log.e(TAG, "loadDataToMap: " );
        final ProgressDialog progressDialog = new ProgressDialog(NewMainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting locations...");
        progressDialog.show();
        Call<LocationResponse> apiForRecord = RestClient.getInstance().getApiServices().requestForBikeLocations(Constants.BIKE_PLACES_URL);
        apiForRecord.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, retrofit2.Response<LocationResponse> response) {
                progressDialog.dismiss();
                if (response.body().getResult().getRecords()==null)
                    return;
                bikesPlaces.clear();
                bikesPlaces.addAll(response.body().getResult().getRecords());
                LatLng latLng = null;
                for (Record a:bikesPlaces){
                    double lat;
                    double lon;
                    lat=Double.parseDouble(a.getLatitude());
                    lon=Double.parseDouble(a.getLongitude());

                    latLng=new LatLng(lat,lon);
                    getMarkerOptions(latLng,a);
                }

                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LinearLayout info = new LinearLayout(NewMainActivity.this);
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(NewMainActivity.this);
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(NewMainActivity.this);
                        snippet.setTextColor(Color.GRAY);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);

                        return info;
                    }
                });
                if (latLng!=null)
                    moveCamera(latLng);
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void getMarkerOptions(LatLng latLng,Record a) {
        Log.e(TAG, "getMarkerOptions: " );
        final ProgressDialog progressDialog = new ProgressDialog(NewMainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting details...");
//        progressDialog.show();
        String url=Constants.BIKE_PLACES_DETAIL_URL+getTime()+a.getNumber();
        Log.e(TAG, "getMarkerOptions: "+url );
        Call<List<LocationDetailResponse>> apiForRecord = RestClient.getInstance().getApiServices().requestForBikeLocationDetails(url);
        apiForRecord.enqueue(new Callback<List<LocationDetailResponse>>() {
            @Override
            public void onResponse(Call<List<LocationDetailResponse>> call, retrofit2.Response<List<LocationDetailResponse>> response) {
                progressDialog.dismiss();
                Log.e(TAG, "onResponse: "+response.body() );
                if (response.body().size()==0||response.body().get(0).getHistoric()==null)
                    return;

                if (response.body().get(0).getHistoric().size()>=2){
                    List<Historic> historics=response.body().get(0).getHistoric();
                    int difference=historics.get(historics.size()-1).getAvailableBikes()-historics.get(historics.size()-2).getAvailableBikes();
                    Log.e(TAG, "onResponse: last:"+historics.get(historics.size()-1).getAvailableBikes() );
                    Log.e(TAG, "onResponse: Slast:"+historics.get(historics.size()-2).getAvailableBikes() );
                    Log.e(TAG, "onResponse: adding marker" );
                    if (difference>=2){
                        //not safe
                        MarkerOptions place2 = new MarkerOptions().position(latLng).title(a.getName()).snippet("Station : "+a.getName()+"\n" +
                                "Available Bikes : "+historics.get(historics.size()-1).getAvailableBikes()+"\n" +
                                "Safety : Not safe");
                        mMap.addMarker(place2);

                    }else{
                        //safe
                        MarkerOptions place2 = new MarkerOptions().position(latLng).title(a.getName()).snippet("Station : "+a.getName()+"\n" +
                                "Available Bikes : "+historics.get(historics.size()-1).getAvailableBikes()+"\n" +
                                "Safety : Safe");
                        mMap.addMarker(place2);

                    }
                }else{
                    Log.e(TAG, "onResponse: not 2" );
                }
            }

            @Override
            public void onFailure(Call<List<LocationDetailResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }

    private String getTime() {
        Calendar cal= Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmm");
        String dto= formatter.format(date);
        cal.add(Calendar.MINUTE,-10);
        Date date2 = cal.getTime();
        String dfrom= formatter.format(date2);
        String embed="dfrom="+dfrom+"&dto="+dto+"&station=";
        Log.e(TAG, "getTime: "+embed );
        return embed;
    }

    private void moveCamera(LatLng latLng){
        Log.e(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void initMap(){
        Log.e(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(NewMainActivity.this);
    }

    private void getLocationPermission(){
        Log.e(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        mLocationPermissionsGranted = false;
                        Log.d(TAG, "onRequestPermissionsResult: permission failed");
                        return;
                    }
                }
                Log.d(TAG, "onRequestPermissionsResult: permission granted");
                mLocationPermissionsGranted = true;
                //initialize our map
                initMap();
            }
        }
    }
}