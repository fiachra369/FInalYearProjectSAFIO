package com.example.safiofyp.main;

import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safiofyp.R;
import com.example.safiofyp.adapters.SliderAdapter;
import com.example.safiofyp.models.SliderData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    }



    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ShowDetails.this, MyLocationActivity.class));
    }


    }


