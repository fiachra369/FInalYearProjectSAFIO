package com.example.safiofyp.auth;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safiofyp.R;
import com.example.safiofyp.adapters.SliderAdapter;
import com.example.safiofyp.main.NewMainActivity;
import com.example.safiofyp.models.SliderData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button btnLogin, btnRegister, btnLogout;
    private SliderAdapter adapter;

    private ArrayList<SliderData> sliderDataArrayList;
    private ArrayList<SliderData> sliderDataArrayList2;

    FirebaseFirestore db;
    private SliderView sliderView;
    private SliderView sliderView2;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList2 = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();


        sliderView = findViewById(R.id.slider);
        sliderView2 = findViewById(R.id.slider1);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.getInstance().getCurrentUser();

        loadImages();
        loadImages2();

        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(HomeActivity.this, NewMainActivity.class));
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegister = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intRegister);
            }
        });
    }


    private void loadImages2() {

        db.collection("Slider2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();


                    model.setImgUrl(sliderData.getImgUrl());


                    sliderDataArrayList2.add(model);


                    adapter = new SliderAdapter(HomeActivity.this, sliderDataArrayList2);


                    sliderView2.setSliderAdapter(adapter);


                    sliderView2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);


                    sliderView2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);


                    sliderView2.setScrollTimeInSec(3);


                    sliderView2.setAutoCycle(true);


                    sliderView2.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(HomeActivity.this, "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadImages() {

        db.collection("Slider").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();



                    model.setImgUrl(sliderData.getImgUrl());


                    sliderDataArrayList.add(model);


                    adapter = new SliderAdapter(HomeActivity.this, sliderDataArrayList);


                    sliderView.setSliderAdapter(adapter);

                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);


                    sliderView.setScrollTimeInSec(3);


                    sliderView.setAutoCycle(true);


                    sliderView.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(HomeActivity.this, "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }


}
