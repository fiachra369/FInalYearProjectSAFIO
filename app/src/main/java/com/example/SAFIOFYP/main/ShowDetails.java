package com.example.SAFIOFYP.main;

import android.app.ActionBar;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.adapters.SliderAdapter;
import com.example.SAFIOFYP.auth.RegisterActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ShowDetails extends AppCompatActivity {
    private SliderAdapter adapter;

    private ArrayList<SliderData> sliderDataArrayList;

    FirebaseFirestore db;
    private SliderView sliderView;

    TextView area, footfall, density, riskscore;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference databaseReference;
    FirebaseUser UserId;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        sliderDataArrayList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        sliderView = findViewById(R.id.slider);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        area = findViewById(R.id.area);
        footfall = findViewById(R.id.footfall);
        density = findViewById(R.id.density);
        riskscore = findViewById(R.id.riskscore);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        UserId = fAuth.getCurrentUser();

        FirebaseAuth.getInstance().getCurrentUser();

        final DocumentReference documentReference = fStore.collection("user").document(UserId.getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                density.setText(documentSnapshot.getString("street_density"));
                footfall.setText(documentSnapshot.getString("street_average"));
                area.setText(documentSnapshot.getString("street_area"));
                riskscore.setText(documentSnapshot.getString("risk_score"));
            }
        });

        loadImages();
    }

        private void loadImages() {
            // getting data from our collection and after
            // that calling a method for on success listener.
            db.collection("Slider3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
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
                        adapter = new SliderAdapter(ShowDetails.this, sliderDataArrayList);

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
                    Toast.makeText(ShowDetails.this, "Fail to load slider data..", Toast.LENGTH_SHORT).show();
                }
            });

        }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ShowDetails.this, MyLocationActivity.class));
    }


    }


