package com.example.SAFIOFYP.auth;

import android.app.ActionBar;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.adapters.SliderAdapter;
import com.example.SAFIOFYP.main.MyLocationActivity;
import com.example.SAFIOFYP.main.NewMainActivity;
import com.example.SAFIOFYP.models.SliderData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        // creating a new array list fr our array list.
        sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList2 = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        // initializing our slider view and
        // firebase firestore instance.
        sliderView = findViewById(R.id.slider);
        sliderView2 = findViewById(R.id.slider1);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.getInstance().getCurrentUser();

        // calling our method to load images.
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
        // getting data from our collection and after
        // that calling a method for on success listener.
        db.collection("Slider2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                // inside the on success method we are running a for loop
                // and we are getting the data from Firebase Firestore
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    // after we get the data we are passing inside our object class.
                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();

                    // below line is use for setting our
                    // image url for our modal class.
                    model.setImgUrl(sliderData.getImgUrl());

                    // after that we are adding that
                    // data inside our array list.
                    sliderDataArrayList2.add(model);

                    // after adding data to our array list we are passing
                    // that array list inside our adapter class.
                    adapter = new SliderAdapter(HomeActivity.this, sliderDataArrayList2);

                    // belows line is for setting adapter
                    // to our slider view
                    sliderView2.setSliderAdapter(adapter);

                    // below line is for setting animation to our slider.
                    sliderView2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    // below line is for setting auto cycle duration.
                    sliderView2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);

                    // below line is for setting
                    // scroll time animation
                    sliderView2.setScrollTimeInSec(3);

                    // below line is for setting auto
                    // cycle animation to our slider
                    sliderView2.setAutoCycle(true);

                    // below line is use to start
                    // the animation of our slider view.
                    sliderView2.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(HomeActivity.this, "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadImages() {
        // getting data from our collection and after
        // that calling a method for on success listener.
        db.collection("Slider").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                // inside the on success method we are running a for loop
                // and we are getting the data from Firebase Firestore
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    // after we get the data we are passing inside our object class.
                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();

                    // below line is use for setting our
                    // image url for our modal class.
                    model.setImgUrl(sliderData.getImgUrl());

                    // after that we are adding that
                    // data inside our array list.
                    sliderDataArrayList.add(model);

                    // after adding data to our array list we are passing
                    // that array list inside our adapter class.
                    adapter = new SliderAdapter(HomeActivity.this, sliderDataArrayList);

                    // belows line is for setting adapter
                    // to our slider view
                    sliderView.setSliderAdapter(adapter);

                    // below line is for setting animation to our slider.
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    // below line is for setting auto cycle duration.
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);

                    // below line is for setting
                    // scroll time animation
                    sliderView.setScrollTimeInSec(3);

                    // below line is for setting auto
                    // cycle animation to our slider
                    sliderView.setAutoCycle(true);

                    // below line is use to start
                    // the animation of our slider view.
                    sliderView.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(HomeActivity.this, "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }


}
