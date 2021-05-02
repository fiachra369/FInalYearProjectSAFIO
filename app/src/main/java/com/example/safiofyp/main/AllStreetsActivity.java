package com.example.safiofyp.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.safiofyp.R;
import com.example.safiofyp.adapters.AllStreetsListAdapter;
import com.example.safiofyp.models.AllStreetsModel;
import com.example.safiofyp.models.Record;
import com.example.safiofyp.models.RecordsBody;
import com.example.safiofyp.models.Street;
import com.example.safiofyp.models.StreetBody;
import com.example.safiofyp.network.RestClient;
import com.example.safiofyp.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllStreetsActivity extends AppCompatActivity {
    String dataandtime;

    String tempDate;

    List<AllStreetsModel> allStreetsList = new ArrayList<>();

    RecyclerView recyclerView;

    AllStreetsListAdapter allStreetsListAdapter;

    String currentStretName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_streets);
        allStreetsListAdapter = new AllStreetsListAdapter(allStreetsList, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }

        dataandtime = getIntent().getStringExtra("date");

        tempDate = dataandtime;

        currentStretName = getIntent().getStringExtra("street_name");



        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(allStreetsListAdapter);

        requestForData();
    }



    int ConnelLowerLength = 0;
    int ConnelUpperLength = 0;
    int  MarryStreetLength = 0;
    int NassauStreetLength = 0;
    int CapelStreetLength = 0;
    int  AstonQuayLength = 0;
    int DOllierStreetLength = 0;
    int DawnsonStreetLength = 0;
    int  DameStreetLength = 0;
    int  TalbotStreetLength = 0;
    int  ParnellStreetLength = 0;
    int SuffolkStreetLength = 0;
    int CollegeGreenStreetLength = 0;
    int HenryStreetLength = 0;
    int  WestmoreLandStreetLength = 0;
    int  LifeyStreetLength = 0;
    int GraftonStreetLength = 0;
    int BachelorsWalkStreetLength = 0;

    int ConnelLowerThresh = 0;
    int ConnelUpperThresh = 0;
    int  MarryStreetThresh = 0;
    int NassauStreetThresh = 0;
    int CapelStreetThresh = 0;
    int  AstonQuayThresh = 0;
    int DOllierStreetThresh = 0;
    int DawnsonStreetThresh = 0;
    int  DameStreetThresh = 0;
    int  TalbotStreetThresh = 0;
    int  ParnellStreetThresh = 0;
    int SuffolkStreetThresh = 0;
    int CollegeGreenStreetThresh = 0;
    int HenryStreetThresh = 0;
    int  WestmoreLandStreetThresh = 0;
    int  LifeyStreetThresh = 0;
    int GraftonStreetThresh = 0;
    int BachelorsWalkStreetThresh = 0;

    String ConnelLowerName = "O'Connell Street Lower";
    String ConnelUpperName = "O'Connell Street Upper";
    String  MarryStreetName = "Mary Street";
    String NassauStreetName = "Nassau Street";
    String CapelStreetName = "Capel Street";
    String  AstonQuayName = "Aston Quay";
    String DOllierStreetName = "D'Olier Street";
    String DawnsonStreetName = "Dawson Street";
    String  DameStreetName = "Dame Street";
    String  TalbotStreetName = "Talbot Street";
    String  ParnellStreetName = "Parnell Street";
    String SuffolkStreetName = "Suffolk Street";
    String CollegeGreenStreetName = "College Green";
    String HenryStreetName = "Henry Street";
    String  WestmoreLandStreetName = "Westmoreland Street";
    String  LifeyStreetName = "Liffey Street";
    String GraftonStreetName = "Grafton Street";
    String BachelorsWalkStreetName = "Bachelors Walk";


    int ConnelLowerAverage = 0;
    int ConnelUpperAverage = 0;
    int  MarryStreetAverage = 0;
    int NassauStreetAverage = 0;
    int CapelStreetAverage = 0;
    int  AstonQuayAverage = 0;
    int DOllierStreetAverage = 0;
    int DawnsonStreetAverage = 0;
    int  DameStreetAverage = 0;
    int  TalbotStreetAverage = 0;
    int  ParnellStreetAverage = 0;
    int SuffolkStreetAverage = 0;
    int CollegeGreenStreetAverage = 0;
    int HenryStreetAverage = 0;
    int  WestmoreLandStreetAverage = 0;
    int  LifeyStreetAverage = 0;
    int GraftonStreetAverage = 0;
    int BachelorsWalkStreetAverage = 0;


    int widthConnelLower = 10;
    int widthConnelUpper = 10;
    int  widthMarryStreet = 5;
    int widthNassauStreet = 5;
    int widthCapelStreet = 5;
    int  widthAstonQuay = 5;
    int widthDOllierStreet = 5;
    int widthDawnsonStreet = 5;
    int  widthDameStreet = 5;
    int  widthTalbotStreet = 5;
    int  widthParnellStreet = 5;
    int  widthSuffolkStreet = 5;
    int widthCollegeGreenStreet = 5;
    int widthHenryStreet = 10;
    int  widthWestmoreLandStreet = 5;
    int  widthLifeyStreet = 5;
    int widthGraftonStreet = 10;
    int widthBachelorsWalkStreet = 10;


    public void requestForData() {

        final ProgressDialog progressDialog = new ProgressDialog(AllStreetsActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Connecting with Smart Dublin. Please wait, this may take a while");
        progressDialog.show();

        Call<RecordsBody> apiForRecord = RestClient.getInstance().getApiServices().requestForRecords(Constants.RECORD_URL);
        apiForRecord.enqueue(new Callback<RecordsBody>() {
            @Override
            public void onResponse(Call<RecordsBody> call, Response<RecordsBody> response) {
                if (response.isSuccessful()) {

                    String currentDay = getDay(tempDate);
                    int currentMonth = getMonth(tempDate);
                    int currentHour = getHour(tempDate);

                    List<Integer> footFallConnelLower = new ArrayList<>();
                    List<Integer> footFallConnelUpper = new ArrayList<>();
                    List<Integer> footFallMarryStreet = new ArrayList<>();
                    List<Integer> footFallNassauStreet = new ArrayList<>();
                    List<Integer> footFallCapelStreet = new ArrayList<>();
                    List<Integer> footFallAstonQuay = new ArrayList<>();
                    List<Integer> footFallDOllierStreet = new ArrayList<>();
                    List<Integer> footFallDawnsonStreet = new ArrayList<>();
                    List<Integer> footFallDameStreet = new ArrayList<>();
                    List<Integer> footFallTalbotStreet = new ArrayList<>();
                    List<Integer> footFallParnellStreet = new ArrayList<>();
                    List<Integer> footFallSuffolkStreet = new ArrayList<>();
                    List<Integer> footFallCollegeGreenStreet = new ArrayList<>();
                    List<Integer> footFallHenryStreet = new ArrayList<>();
                    List<Integer> footFallWestmoreLandStreet = new ArrayList<>();
                    List<Integer> footFallLifeyStreet = new ArrayList<>();
                    List<Integer> footFallBachelorsWalkStreet = new ArrayList<>();
                    List<Integer> footFallGraftonStreet = new ArrayList<>();

                    for (Record record : response.body().getResult().getRecords()) {


                        String recordDay = getDay(record.getDateTime());
                        int recordMonth = getMonth(record.getDateTime());
                        int recordYear = getYear(record.getDateTime());
                        int recordHour = getHour(record.getDateTime());


                        if (recordYear == 2020) {

                            if (recordMonth == currentMonth) {

                                if (recordDay.equals(currentDay)) {

                                    if (recordHour == currentHour) {



                                        if (record.getOConnellStOutsideClerys() == null)
                                        {
                                            footFallConnelLower.add(0);
                                        }
                                        else
                                        {
                                            footFallConnelLower.add(record.getOConnellStOutsideClerys());
                                        }


                                        if (record.getOConnellStParnellStAIB() == null)
                                        {
                                            footFallConnelUpper.add(0);
                                        }
                                        else {
                                            footFallConnelUpper.add(record.getOConnellStParnellStAIB());
                                        }


                                        if (record.getMaryStreet() == null)
                                        {
                                            footFallMarryStreet.add(0);
                                        }
                                        else {
                                            footFallMarryStreet.add(record.getMaryStreet());
                                        }



                                        if (record.getGraftonStreetNassauStreetSuffolkStreet() == null)
                                        {
                                            footFallNassauStreet.add(0);
                                        }
                                        else {
                                            footFallNassauStreet.add(record.getGraftonStreetNassauStreetSuffolkStreet());
                                        }


                                        if (record.getCapelStreetRemovedFromSite2010() == null)
                                        {
                                            footFallCapelStreet.add(0);
                                        }
                                        else {
                                            footFallCapelStreet.add(record.getCapelStreetRemovedFromSite2010());
                                        }



                                        if (record.getAstonQuay() == null)
                                        {
                                            footFallAstonQuay.add(0);
                                        }
                                        else {
                                            footFallAstonQuay.add(record.getAstonQuay());
                                        }



                                        if (record.getDoilierStreetBurghQuay() == null)
                                        {
                                            footFallDOllierStreet.add(0);
                                        }
                                        else {
                                            footFallDOllierStreet.add(record.getDoilierStreetBurghQuay());
                                        }




                                        if (record.getDawsonStreetReplacement() == null)
                                        {
                                            footFallDawnsonStreet.add(0);
                                        }
                                        else {
                                            footFallDawnsonStreet.add(record.getDawsonStreetReplacement());
                                        }




                                        if (record.getCollegeGreenDameStSide()== null)
                                        {
                                            footFallDameStreet.add(0);
                                        }
                                        else {
                                            footFallDameStreet.add(record.getCollegeGreenDameStSide());
                                        }



                                        if (record.getTalbotStreetNorth() == null)
                                        {
                                            footFallTalbotStreet.add(0);
                                        }
                                        else {
                                            footFallTalbotStreet.add(record.getTalbotStreetNorth());
                                        }



                                        if (record.getOConnellStParnellStAIB() == null)
                                        {
                                            footFallParnellStreet.add(0);
                                        }
                                        else {
                                            footFallParnellStreet.add(record.getOConnellStParnellStAIB());
                                        }



                                        if (record.getGraftonStreetNassauStreetSuffolkStreet() == null)
                                        {
                                            footFallSuffolkStreet.add(0);
                                        }
                                        else {
                                            footFallSuffolkStreet.add(record.getGraftonStreetNassauStreetSuffolkStreet());
                                        }



                                        if (record.getCollegeGreenBankOfIreland() == null)
                                        {
                                            footFallCollegeGreenStreet.add(0);
                                        }
                                        else {
                                            footFallCollegeGreenStreet.add(record.getCollegeGreenBankOfIreland());
                                        }



                                        if (record.getHenryStreet() == null)
                                        {
                                            footFallHenryStreet.add(0);
                                        }
                                        else {
                                            footFallHenryStreet.add(record.getHenryStreet());
                                        }


                                        if (record.getWestmorelandStreetEast() == null)
                                        {
                                            footFallWestmoreLandStreet.add(0);
                                        }
                                        else {
                                            footFallWestmoreLandStreet.add(record.getWestmorelandStreetEast());
                                        }



                                        if (record.getLiffeyStreet() == null)
                                        {
                                            footFallLifeyStreet.add(0);
                                        }
                                        else {
                                            footFallLifeyStreet.add(record.getLiffeyStreet());
                                        }



                                        if (record.getGraftonStreet() == null)
                                        {
                                            footFallGraftonStreet.add(0);
                                        }
                                        else {
                                            footFallGraftonStreet.add(record.getGraftonStreet());
                                        }



                                        if (record.getBachelorsWalk() == null)
                                        {
                                            footFallBachelorsWalkStreet.add(0);
                                        }
                                        else {
                                            footFallBachelorsWalkStreet.add(record.getBachelorsWalk());
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




                    ConnelLowerAverage = findAverage(footFallConnelLower);
                    ConnelUpperAverage = findAverage(footFallConnelUpper);
                    MarryStreetAverage = findAverage(footFallMarryStreet);
                    NassauStreetAverage = findAverage(footFallNassauStreet);
                    CapelStreetAverage = findAverage(footFallCapelStreet);
                    AstonQuayAverage = findAverage(footFallAstonQuay);
                    DOllierStreetAverage = findAverage(footFallDOllierStreet);
                    DawnsonStreetAverage = findAverage(footFallDawnsonStreet);
                    DameStreetAverage = findAverage(footFallDameStreet);
                    TalbotStreetAverage = findAverage(footFallTalbotStreet);
                    ParnellStreetAverage = findAverage(footFallParnellStreet);
                    SuffolkStreetAverage = findAverage(footFallSuffolkStreet);
                    CollegeGreenStreetAverage = findAverage(footFallCollegeGreenStreet);
                    HenryStreetAverage = findAverage(footFallHenryStreet);
                    WestmoreLandStreetAverage = findAverage(footFallWestmoreLandStreet);
                    LifeyStreetAverage = findAverage(footFallLifeyStreet);
                    GraftonStreetAverage = findAverage(footFallGraftonStreet);
                    BachelorsWalkStreetAverage = findAverage(footFallBachelorsWalkStreet);



                    Call<StreetBody> apiForStreet = RestClient.getInstance().getStreetInterface().requestForStreet(Constants.ALL_STREETS_AREA_URL);
                    apiForStreet.enqueue(new Callback<StreetBody>() {
                        @Override
                        public void onResponse(Call<StreetBody> call, Response<StreetBody> response) {
                            if (response.isSuccessful()) {

                                for (Street street : response.body().getResult().getStreets()) {
                                    if (street.getStreetName().toLowerCase().equals("grafton street")) {
                                        if (street.getId() == 1809) {
                                            GraftonStreetLength = street.getLineLength();
                                        }
                                    }
                                    if (street.getStreetName().toLowerCase().equals("liffey street upper")) {
                                        if (LifeyStreetLength == 0) {
                                            LifeyStreetLength += street.getLineLength();
                                        } else {
                                            LifeyStreetLength += street.getLineLength();
                                        }
                                    }

                                    if (street.getStreetName().toLowerCase().equals("liffey street lower")) {
                                        if (LifeyStreetLength == 0) {
                                            LifeyStreetLength += street.getLineLength();
                                        } else {
                                            LifeyStreetLength += street.getLineLength();
                                        }
                                    }
                                    if (street.getStreetName().toLowerCase().equals("parnell street")) {
                                        if (street.getId() == 3047) {
                                            ParnellStreetLength = street.getLineLength();
                                        }
                                    }
                                    if (street.getStreetName().toLowerCase().equals("capel street")) {
                                        if (street.getId() == 630) {
                                            CapelStreetLength = street.getLineLength();
                                        }
                                    }


                                    if (street.getStreetName().toLowerCase().equals("o'connell street lower"))
                                    {
                                        ConnelLowerLength = street.getLineLength();
                                    }

                                    if (street.getStreetName().toLowerCase().equals("o'connell street upper"))
                                    {
                                        ConnelUpperLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("mary street"))
                                    {
                                        MarryStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("nassau street"))
                                    {
                                        NassauStreetLength = street.getLineLength();
                                    }

                                    if (street.getStreetName().toLowerCase().equals("aston quay"))
                                    {
                                        AstonQuayLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("d'olier street"))
                                    {
                                        DOllierStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("dawson street"))
                                    {
                                        DawnsonStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("dame street"))
                                    {
                                        DameStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("talbot street"))
                                    {
                                        TalbotStreetLength = street.getLineLength();
                                    }

                                    if (street.getStreetName().toLowerCase().equals("suffolk street"))
                                    {
                                        SuffolkStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("college green"))
                                    {
                                        CollegeGreenStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("henry street"))
                                    {
                                        HenryStreetLength = street.getLineLength();
                                    }
                                    if (street.getStreetName().toLowerCase().equals("westmoreland street"))
                                    {
                                        WestmoreLandStreetLength = street.getLineLength();
                                    }



                                    if (street.getStreetName().toLowerCase().equals("bachelors walk"))
                                    {
                                        BachelorsWalkStreetLength = street.getLineLength();
                                    }



                                }

                                progressDialog.dismiss();
                                ConnelLowerThresh = ((ConnelLowerLength * widthConnelLower)/2);
                                ConnelUpperThresh = ((ConnelUpperLength * widthConnelUpper)/2);
                                MarryStreetThresh = ((MarryStreetLength * widthMarryStreet)/2);
                                NassauStreetThresh = ((NassauStreetLength * widthNassauStreet)/2);
                                CapelStreetThresh = ((CapelStreetLength * widthCapelStreet)/2);
                                AstonQuayThresh = ((AstonQuayLength * widthAstonQuay)/2);
                                DOllierStreetThresh = ((DOllierStreetLength * widthDOllierStreet)/2);
                                DawnsonStreetThresh = ((DawnsonStreetLength * widthDawnsonStreet)/2);
                                DameStreetThresh = ((DameStreetLength * widthDameStreet)/2);
                                TalbotStreetThresh = ((TalbotStreetLength * widthTalbotStreet)/2);
                                ParnellStreetThresh = ((ParnellStreetLength * widthParnellStreet)/2);
                                SuffolkStreetThresh = ((SuffolkStreetLength * widthSuffolkStreet)/2);
                                CollegeGreenStreetThresh = ((CollegeGreenStreetLength * widthCollegeGreenStreet)/2);
                                HenryStreetThresh = ((HenryStreetLength * widthHenryStreet)/2);
                                WestmoreLandStreetThresh = ((WestmoreLandStreetLength * widthWestmoreLandStreet)/2);
                                LifeyStreetThresh = ((LifeyStreetLength * widthLifeyStreet)/2);
                                GraftonStreetThresh = ((GraftonStreetLength * widthGraftonStreet)/2);
                                BachelorsWalkStreetThresh = ((BachelorsWalkStreetLength * widthBachelorsWalkStreet)/2);




                                AllStreetsModel street1 = new AllStreetsModel(ConnelLowerName, ConnelLowerAverage, ConnelLowerThresh, ConnelLowerLength, widthConnelLower);
                                AllStreetsModel street2 = new AllStreetsModel(ConnelUpperName, ConnelUpperAverage, ConnelUpperThresh, ConnelUpperLength, widthConnelUpper);
                                AllStreetsModel street3 = new AllStreetsModel(MarryStreetName, MarryStreetAverage, MarryStreetThresh, MarryStreetLength, widthMarryStreet);
                                AllStreetsModel street4 = new AllStreetsModel(NassauStreetName, NassauStreetAverage, NassauStreetThresh, NassauStreetLength, widthNassauStreet);
                                AllStreetsModel street5 = new AllStreetsModel(CapelStreetName, CapelStreetAverage, CapelStreetThresh, CapelStreetLength, widthCapelStreet);
                                AllStreetsModel street6 = new AllStreetsModel(AstonQuayName,  AstonQuayAverage,  AstonQuayThresh,  AstonQuayLength, widthAstonQuay);
                                AllStreetsModel street7 = new AllStreetsModel(DOllierStreetName, DOllierStreetAverage, DOllierStreetThresh, DOllierStreetLength, widthDOllierStreet);
                                AllStreetsModel street8 = new AllStreetsModel(DawnsonStreetName, DawnsonStreetAverage, DawnsonStreetThresh, DawnsonStreetLength, widthDawnsonStreet);
                                AllStreetsModel street9 = new AllStreetsModel(DameStreetName, DameStreetAverage, DameStreetThresh, DameStreetLength, widthDameStreet);
                                AllStreetsModel street10 = new AllStreetsModel(TalbotStreetName, TalbotStreetAverage, TalbotStreetThresh, TalbotStreetLength, widthTalbotStreet);
                                AllStreetsModel street11 = new AllStreetsModel(ParnellStreetName, ParnellStreetAverage, ParnellStreetThresh, ParnellStreetLength, widthParnellStreet);
                                AllStreetsModel street12 = new AllStreetsModel(SuffolkStreetName, SuffolkStreetAverage, SuffolkStreetThresh, SuffolkStreetLength, widthSuffolkStreet);
                                AllStreetsModel street13 = new AllStreetsModel(CollegeGreenStreetName, CollegeGreenStreetAverage, CollegeGreenStreetThresh, CollegeGreenStreetLength, widthCollegeGreenStreet);
                                AllStreetsModel street14 = new AllStreetsModel(HenryStreetName, HenryStreetAverage, HenryStreetThresh, HenryStreetLength, widthHenryStreet);
                                AllStreetsModel street15 = new AllStreetsModel(WestmoreLandStreetName, WestmoreLandStreetAverage, WestmoreLandStreetThresh, WestmoreLandStreetLength, widthWestmoreLandStreet);
                                AllStreetsModel street16 = new AllStreetsModel(LifeyStreetName, LifeyStreetAverage, LifeyStreetThresh, LifeyStreetLength, widthLifeyStreet);
                                AllStreetsModel street17 = new AllStreetsModel(GraftonStreetName, GraftonStreetAverage, GraftonStreetThresh, GraftonStreetLength, widthGraftonStreet);
                                AllStreetsModel street18 = new AllStreetsModel(BachelorsWalkStreetName, BachelorsWalkStreetAverage, BachelorsWalkStreetThresh, BachelorsWalkStreetLength, widthBachelorsWalkStreet);


                                allStreetsList.add(street1);
                                allStreetsList.add(street2);
                                allStreetsList.add(street3);
                                allStreetsList.add(street4);
                                allStreetsList.add(street5);
                                allStreetsList.add(street6);
                                allStreetsList.add(street7);
                                allStreetsList.add(street8);
                                allStreetsList.add(street9);
                                allStreetsList.add(street10);
                                allStreetsList.add(street11);
                                allStreetsList.add(street12);
                                allStreetsList.add(street13);
                                allStreetsList.add(street14);
                                allStreetsList.add(street15);
                                allStreetsList.add(street16);
                                allStreetsList.add(street17);
                                allStreetsList.add(street18);


                                for (int i = 0; i < allStreetsList.size(); i++)
                                {

                                    AllStreetsModel singleSteet = allStreetsList.get(i);
                                    if (singleSteet.getStreetName().equals(currentStretName))
                                    {
                                        allStreetsList.remove(singleSteet);
                                        i = allStreetsList.size() + 5;

                                    }

                                }

                                allStreetsListAdapter.notifyDataSetChanged();

                            }
                        }
                        @Override
                        public void onFailure(Call<StreetBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(AllStreetsActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call<RecordsBody> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(AllStreetsActivity.this, "Network URL Not Found!", Toast.LENGTH_SHORT).show();
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

    private int findAverage(List<Integer> footFallList)
    {
        int average = 0;
        int tempSum = 0;

        for (int i = 0; i < footFallList.size(); i++)
        {
            tempSum = tempSum + footFallList.get(i);
        }

        if (tempSum == 0) {

            average = 0;
        }
        else {
            average = tempSum / footFallList.size();
        }
        return average;
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(AllStreetsActivity.this, MyLocationActivity.class));
    }


}