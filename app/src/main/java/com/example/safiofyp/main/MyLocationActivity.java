package com.example.safiofyp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safiofyp.R;
import com.example.safiofyp.models.Record;
import com.example.safiofyp.models.RecordsBody;
import com.example.safiofyp.models.Street;
import com.example.safiofyp.models.StreetBody;
import com.example.safiofyp.network.RestClient;
import com.example.safiofyp.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLocationActivity extends AppCompatActivity {

    private static final String TAG = "MyLocationActivity";
    TextView tvAddress, date, street, people, safeStreet;
    View firstLayout;
    AppLocationService appLocationService;

    private final int REQUEST_LOCATION_PERMISSION = 1;

    String location_Address;

    //String dataandtime = "2021-01-01T14:00:00";

    String dataandtime, dateandtimetext;

    String tempDate;

    Button checkAllStreets, showalldetails;


    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;


    String currentStreetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        date = findViewById(R.id.date);
        firstLayout = findViewById(R.id.firstLayout);
        street = findViewById(R.id.location);
        people = findViewById(R.id.people);
        safeStreet = findViewById(R.id.safe_check);
        showalldetails = findViewById(R.id.showalldetails);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        checkAllStreets = findViewById(R.id.checkAllStreets);

        Calendar calendar = Calendar.getInstance();

        Calendar cal = Calendar.getInstance();

        cal.get(Calendar.HOUR_OF_DAY);

        dataandtime = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "T" + cal.get(Calendar.HOUR_OF_DAY) + ":00:00";


        //dataandtime = "2021-" + String.valueOf(calendar.MONTH) + "-" + String.valueOf(calendar.DAY_OF_MONTH) + "T" + String.valueOf(calendar.HOUR_OF_DAY) + ":00:00";


        dateandtimetext = "Last Updated: " + dataandtime;

        tvAddress = (TextView) findViewById(R.id.tvAddress);
        appLocationService = new AppLocationService(
                MyLocationActivity.this);
        tempDate = dataandtime;
        dataandtime = decrementYear(dataandtime);

        requestLocationPermission();

        checkAllStreets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLocationActivity.this, AllStreetsActivity.class);
                intent.putExtra("date", dataandtime);
                intent.putExtra("street_name", currentStreetName);
                startActivity(intent);
            }
        });

        showalldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyLocationActivity.this, ShowDetails.class);
                startActivity(intent);
            }
        });

    }

    public void getAddress() {
        Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
//            //Grafton Street
            //double latitude = 53.341389;
            //double longitude = -6.260278;

////            O'Connell Street Lower
//            double latitude = 53.34890;
//            double longitude = -6.25966;

            //O'Connell Street Upper
//            double latitude = 53.35243725117745;
//            double longitude = -6.261760672940594;

            //Mary Street
//            double latitude = 53.348904665227245;
//            double longitude = -6.266646301943025;

            //Nassau Street
//            double latitude = 53.34280927721292;
//            double longitude = -6.256785344273235;

            //Capel Street
//            double latitude = 53.34915506282352;
//            double longitude = -6.269045009209919;

            //Aston Quay
//            double latitude = 53.34670167151478;
//            double longitude = -6.2606700019430646;

            //D'Olier Street
//            double latitude = 53.34665047649582;
//            double longitude = -6.258229944273079;

            //Dawson Street
//            double latitude = 53.34074979308416;
//            double longitude = -6.2584020091943975;

            //Dame Street
//            double latitude = 53.34430467835604;
//            double longitude = -6.264846388448104;

            //Talbot Street
//            double latitude = 53.35093715523616;
//            double longitude = -6.253974273107926;

            //Parnell Street
//            double latitude = 53.34982695136974;
//            double longitude = -6.268467038764602;

            //Suffolk Street
//            double latitude = 53.34373239053676;
//            double longitude = -6.260277588448151;

            //College Green
//            double latitude = 53.34446576280247;
//            double longitude = -6.2605477620031085;

            //Henry Street
//            double latitude = 53.349589158881145;
//            double longitude = -6.262141501942967;

            //Westmoreland Street
//            double latitude = 53.34597348840575;
//            double longitude = -6.259176630778113;

            //Liffey Street
//            double latitude = 53.34738506484093;
//            double longitude = -6.263397373108035;

            //Bachelors Walk
//            double latitude = 53.347240165232684;
//            double longitude = -6.2609646731080515;


            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location1 = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude1 = location1.getLongitude();
            double latitude1 = location1.getLatitude();


            location_Address = getCompleteAddressString(latitude1, longitude1);

            requestForData();


        }

    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                //Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                //Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            getAddress();
        } else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    int width = 0;
    int length = 0;
    int average = 0;
    String streetName = "";

    public void requestForData() {
        final ProgressDialog progressDialog = new ProgressDialog(MyLocationActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Connecting with Smart Dublin. Please wait, this may take a while");
        progressDialog.show();

        Call<RecordsBody> apiForRecord = RestClient.getInstance().getApiServices().requestForRecords(Constants.RECORD_URL);
        apiForRecord.enqueue(new Callback<RecordsBody>() {
            @Override
            public void onResponse(Call<RecordsBody> call, Response<RecordsBody> response) {
                Log.e(TAG, "onResponse: " + response.body().toString());
                if (response.isSuccessful()) {


                    int tempSum = 0;

                    String currentDay = getDay(tempDate);
                    int currentMonth = getMonth(tempDate);
                    int currentHour = getHour(tempDate);
                    List<Integer> footFallList = new ArrayList<>();


                    for (Record record : response.body().getResult().getRecords()) {

                        String recordDay = getDay(record.getDateTime());
                        int recordMonth = getMonth(record.getDateTime());
                        int recordYear = getYear(record.getDateTime());
                        int recordHour = getHour(record.getDateTime());


                        if (recordYear == 2020) {

                            if (recordMonth == currentMonth) {

                                if (recordDay.equals(currentDay)) {

                                    if (recordHour == currentHour) {


                                        if (location_Address.contains("O'Connell Street Lower")) {
                                            if (record.getOConnellStOutsideClerys() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getOConnellStOutsideClerys());
                                            }
                                            streetName = "O'Connell Street Lower";
                                            width = 10;
                                        } else if (location_Address.contains("O'Connell Street Upper")) {
                                            if (record.getOConnellStParnellStAIB() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getOConnellStParnellStAIB());
                                            }
                                            streetName = "O'Connell Street Upper";
                                            width = 10;
                                        } else if (location_Address.contains("Mary St")) {
                                            if (record.getMaryStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getMaryStreet());
                                            }
                                            streetName = "Mary Street";
                                            width = 5;
                                        } else if (location_Address.contains("Nassau St")) {
                                            if (record.getGraftonStreetNassauStreetSuffolkStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getGraftonStreetNassauStreetSuffolkStreet());
                                            }
                                            streetName = "Nassau Street";
                                            width = 5;
                                        } else if (location_Address.contains("Capel St")) {
                                            if (record.getCapelStreetRemovedFromSite2010() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getCapelStreetRemovedFromSite2010());
                                            }
                                            streetName = "Capel Street";
                                            width = 5;
                                        } else if (location_Address.contains("Aston Quay")) {
                                            if (record.getAstonQuay() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getAstonQuay());
                                            }
                                            streetName = "Aston Quay";
                                            width = 5;
                                        } else if (location_Address.contains("D'Olier Street")) {
                                            people.setText(String.valueOf(record.getDoilierStreetBurghQuay()));
                                            street.setText("D'Olier Street");
                                            if (record.getDoilierStreetBurghQuay() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getDoilierStreetBurghQuay());
                                            }
                                            streetName = "D'Olier Street";
                                            width = 5;
                                        } else if (location_Address.contains("Dawson St")) {
                                            people.setText(String.valueOf(record.getDawsonStreetReplacement()));
                                            street.setText("Dawson Street");
                                            if (record.getDawsonStreetReplacement() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getDawsonStreetReplacement());
                                            }

                                            streetName = "Dawson Street";
                                            width = 5;
                                        } else if (location_Address.contains("Dame St")) {
                                            if (record.getCollegeGreenDameStSide() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getCollegeGreenDameStSide());
                                            }
                                            streetName = "Dame Street";
                                            width = 5;
                                        } else if (location_Address.contains("Talbot St")) {
                                            if (record.getTalbotStreetNorth() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getTalbotStreetNorth());
                                            }
                                            streetName = "Talbot Street";
                                            width = 5;
                                        } else if (location_Address.contains("Parnell St")) {
                                            if (record.getOConnellStParnellStAIB() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getOConnellStParnellStAIB());
                                            }
                                            streetName = "Parnell Street";
                                            width = 5;
                                        } else if (location_Address.contains("Suffolk St")) {
                                            if (record.getGraftonStreetNassauStreetSuffolkStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getGraftonStreetNassauStreetSuffolkStreet());
                                            }
                                            streetName = "Suffolk Street";
                                            width = 5;
                                        } else if (location_Address.contains("College Green")) {
                                            if (record.getCollegeGreenBankOfIreland() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getCollegeGreenBankOfIreland());
                                            }
                                            streetName = "College Green";
                                            width = 5;
                                        } else if (location_Address.contains("Henry St")) {
                                            if (record.getHenryStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getHenryStreet());
                                            }
                                            streetName = "Henry Street";
                                            width = 10;
                                        } else if (location_Address.contains("Westmoreland St")) {
                                            if (record.getWestmorelandStreetEast() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getWestmorelandStreetEast());
                                            }
                                            streetName = "Westmoreland Street";
                                            width = 5;
                                        } else if (location_Address.contains("Liffey Street")) {
                                            if (record.getLiffeyStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getLiffeyStreet());
                                            }
                                            streetName = "Liffey Street";
                                            width = 5;
                                        } else if (location_Address.contains("Grafton Street")) {
                                            if (record.getGraftonStreet() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getGraftonStreet());
                                            }
                                            streetName = "Grafton Street";
                                            width = 10;
                                        } else if (location_Address.contains("Bachelors Walk")) {
                                            if (record.getBachelorsWalk() == null) {
                                                footFallList.add(0);
                                            } else {
                                                footFallList.add(record.getBachelorsWalk());
                                            }
                                            streetName = "Bachelors Walk";
                                            width = 10;
                                        } else {
                                            Toast.makeText(MyLocationActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                                            date.setText(dateandtimetext);
                                            street.setText(location_Address);
                                            people.setText("0");
                                            break;
                                        }
                                    } else {
                                    }
                                } else {
                                }
                            } else {
                            }
                        } else {
                        }
                    }

                    currentStreetName = streetName;
                    average = 0;
                    tempSum = 0;
                    for (int i = 0; i < footFallList.size(); i++) {
                        tempSum = tempSum + footFallList.get(i);
                    }

                    if (tempSum == 0) {
                        average = 0;
                    } else {
                        average = tempSum / footFallList.size();
                        String uid = user.getUid();
                        DocumentReference docRef = firebaseFirestore.collection("user").document(uid);
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("street_average", String.valueOf(average));
                        docRef.update(edited);
                    }


                    Call<StreetBody> apiForStreet = RestClient.getInstance().getStreetInterface().requestForStreet(Constants.STREET_AREA_URL_1 + streetName + Constants.STREET_AREA_URL_2);
                    apiForStreet.enqueue(new Callback<StreetBody>() {
                        @Override
                        public void onResponse(Call<StreetBody> call, Response<StreetBody> response) {
                            Log.e(TAG, "onResponse apiForStreet: "+response.body().toString());
                            if (response.isSuccessful()) {

                                for (Street street : response.body().getResult().getStreets()) {

                                    if (street.getStreetName().toLowerCase().equals("grafton street")) {
                                        if (street.getId() == 1809) {
                                            length = street.getLineLength();
                                            break;
                                        }
                                    } else {
                                        if (street.getStreetName().toLowerCase().equals("liffey street upper")) {
                                            if (length == 0) {
                                                length += street.getLineLength();
                                            } else {
                                                length += street.getLineLength();
                                                break;
                                            }
                                        } else {
                                            if (street.getStreetName().toLowerCase().equals("liffey street lower")) {
                                                if (length == 0) {
                                                    length += street.getLineLength();
                                                } else {
                                                    length += street.getLineLength();
                                                    break;
                                                }
                                            } else {
                                                if (street.getStreetName().toLowerCase().equals("parnell street")) {
                                                    if (street.getId() == 3047) {
                                                        length = street.getLineLength();
                                                        break;
                                                    }
                                                } else {
                                                    if (street.getStreetName().toLowerCase().equals("capel street")) {
                                                        if (street.getId() == 630) {
                                                            length = street.getLineLength();
                                                            break;
                                                        }
                                                    } else {
                                                        if (street.getStreetName().toLowerCase().equals(streetName.toLowerCase())) {
                                                            length = street.getLineLength();
                                                            break;
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }


                                int thresh = ((length * width) / 2);
                                int area = length * width;
                                double streetRisk = ((average * 1.0) / area);
                                String uid = user.getUid();
                                DocumentReference docRef = firebaseFirestore.collection("user").document(uid);
                                Map<String, Object> edited = new HashMap<>();
                                edited.put("street_area", String.valueOf(area));
                                edited.put("street_density", String.valueOf(streetRisk));
                                docRef.update(edited);
                                double finalRisk = streetRisk * Constants.USER_RISK_SCORE;
                                if (finalRisk >= .5) {
                                    safeStreet.setText("NOT SAFE" +
                                            "");
                                } else {
                                    safeStreet.setText("SAFE");
                                }
//                                if (average >= thresh)
//                                {
//                                    safeStreet.setText("NOT SAFE");
//                                }
//                                else
//                                {
//                                    safeStreet.setText("SAFE");
//                                }
                                if (average == 0 || thresh == 0) {
                                    safeStreet.setText("UNKNOWN");
                                }

                                firstLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                        }

                        @Override
                        public void onFailure(Call<StreetBody> call, Throwable t) {
                            Log.e(TAG, "onFailure: "+t.getMessage() );
                            firstLayout.setVisibility(View.VISIBLE);
                            progressDialog.dismiss();
                            Toast.makeText(MyLocationActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                            date.setText(dateandtimetext);
                            street.setText(location_Address);
                            people.setText("0");
                            safeStreet.setText("UNKNOWN");
                        }
                    });

                    date.setText(dateandtimetext);

                    if (location_Address.contains("O'Connell Street Lower")) {
                        street.setText("O'Connell Street Lower");
                        people.setText(String.valueOf(average));
                    } else if (location_Address.contains("O'Connell Street Upper")) {
                        street.setText("O'Connell Street Upper");
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Mary St")) {
                        street.setText("Mary Street");
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Nassau St")) {
                        street.setText("Nassau Street");
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Capel St")) {
                        street.setText("Capel Street");
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Aston Quay")) {
                        street.setText("Aston Quay");
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("D'Olier Street")) {
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Dawson St")) {
                        people.setText(String.valueOf(average));

                    } else if (location_Address.contains("Dame St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Dame Street");

                    } else if (location_Address.contains("Talbot St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Talbot Street");

                    } else if (location_Address.contains("Parnell St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Parnell Street");

                    } else if (location_Address.contains("Suffolk St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Suffolk Street");

                    } else if (location_Address.contains("College Green")) {
                        people.setText(String.valueOf(average));
                        street.setText("College Green");

                    } else if (location_Address.contains("Henry St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Henry Street");

                    } else if (location_Address.contains("Westmoreland St")) {
                        people.setText(String.valueOf(average));
                        street.setText("Westmoreland Street");

                    } else if (location_Address.contains("Liffey Street")) {
                        people.setText(String.valueOf(average));
                        street.setText("Liffey Street");

                    } else if (location_Address.contains("Grafton Street")) {
                        people.setText(String.valueOf(average));
                        street.setText("Grafton Street");

                    } else if (location_Address.contains("Bachelors Walk")) {
                        people.setText(String.valueOf(average));
                        street.setText("Bachelors Walk");

                    } else {
                        firstLayout.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                        Toast.makeText(MyLocationActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                        date.setText(dateandtimetext);
                        street.setText(location_Address);
                        people.setText("0");

                    }
                }

            }

            @Override
            public void onFailure(Call<RecordsBody> call, Throwable t) {
                firstLayout.setVisibility(View.VISIBLE);
                Log.e(TAG, "onFailure: "+t.getMessage());
                date.setText(dateandtimetext);
                street.setText(location_Address);
                people.setText("0");
                progressDialog.dismiss();
                Toast.makeText(appLocationService, "Network URL Not Found!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private int getHour(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(dateString);
            sdf.applyPattern("HH");
            int hour = Integer.parseInt(sdf.format(myDate));
            return hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getYear(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(dateString);
            sdf.applyPattern("yyyy");
            int year = Integer.parseInt(sdf.format(myDate));
            return year;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getMonth(String dateString) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(dateString);
            sdf.applyPattern("MM");
            int month = Integer.parseInt(sdf.format(myDate));
            return month;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getDay(String dateString) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(dateString);
            sdf.applyPattern("EEE");
            String day = sdf.format(myDate);


            return day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "null";
    }


    private String decrementYear(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(dateString);
            sdf.applyPattern("yyyy");
            int year = Integer.parseInt(sdf.format(myDate));
            if (year != 2020) {
                String tempDateArray[] = dataandtime.split("-");
                String newTempDate = String.valueOf(year);
                for (int i = 0; i < tempDateArray.length; i++) {
                    if (i > 0) {
                        newTempDate += "-" + tempDateArray[i];
                    }
                }
                return newTempDate;
            } else {
                return dateString;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyLocationActivity.this, NewMainActivity.class));
    }


}