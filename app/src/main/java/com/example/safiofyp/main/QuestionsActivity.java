package com.example.safiofyp.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safiofyp.R;
import com.example.safiofyp.adapters.ViewPagerAdapter;
import com.example.safiofyp.utils.Constants;
import com.example.safiofyp.utils.PagerAnimation;
import com.example.safiofyp.utils.PagerInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuestionsActivity extends FragmentActivity implements PagerInterface {


    private ViewPager2 viewPager;

    TextView previous, next, riskScoreTV;

    Question1Fragment question1Fragment;
    Question2Fragment question2Fragment;
    Question3Fragment question3Fragment;
    Question4Fragment question4Fragment;
    Question5Fragment question5Fragment;
    int fragment1 = 0;
    int fragment2 = 0;
    int fragment3 = 0;
    int fragment4 = 0;
    int fragment5 = 0;

    private int totalRiskScore = 0;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;

    ProgressBar progressBar;

    int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        progressBar = findViewById(R.id.progressbar);

        progressBar.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        previous = findViewById(R.id.prevtv);

        next = findViewById(R.id.nexttv);

        riskScoreTV = findViewById(R.id.risk_score);

        question1Fragment = new Question1Fragment();
        question2Fragment = new Question2Fragment();
        question3Fragment = new Question3Fragment();
        question4Fragment = new Question4Fragment();
        question5Fragment = new Question5Fragment();

        viewPager = findViewById(R.id.pager);
        FragmentStateAdapter pagerAdapter = new ViewPagerAdapter(
                this,
                question1Fragment,
                question2Fragment,
                question3Fragment,
                question4Fragment,
                question5Fragment);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new PagerAnimation());
        viewPager.setOffscreenPageLimit(4);



        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 4)
                {
                    next.setText("CLOSE");
                }
                else
                {
                    next.setText("NEXT");
                }
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        calculateAge();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    @Override
    public void addPoints(int points, int fragmentNumber) {
        switch (fragmentNumber)
        {
            case 1:
                if (fragment1 == 0)
                {
                    fragment1 = 1;
                    totalRiskScore += 2;

                }
                break;
            case 2:
                if (fragment2 == 0)
                {
                    fragment2 = 1;
                    totalRiskScore += 2;

                }
                break;
            case 3:
                if (fragment3 == 0)
                {
                    fragment3 = 1;
                    totalRiskScore += 2;

                }
                break;
            case 4:
                if (fragment4 == 0)
                {
                    fragment4 = 1;
                    totalRiskScore += 4;

                }
                break;
            case 5:
                if (fragment5 == 0)
                {
                    fragment5 = 1;
                    totalRiskScore += 2;

                }
                break;
        }

        if (fragmentNumber == 5)
        {
            if (user != null)
            {
                Constants.CLICKABLE = 2;
                progressBar.setVisibility(View.VISIBLE);
                int myRisk = totalRiskScore;
                if (age >= 64)
                {
                    myRisk += 2;
                }
                if (myRisk < 1)
                {
                    myRisk = 1;
                }
                else
                {

                    if (myRisk > 5)
                    {
                        myRisk = 5;
                    }
                }
                String uid = user.getUid();
                DocumentReference docRef = firebaseFirestore.collection("user").document(uid);
                Map<String, Object> edited = new HashMap<>();
                edited.put("risk_score", String.valueOf(myRisk));
                docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Constants.CLICKABLE = 1;
                        Intent intent = new Intent(QuestionsActivity.this, NewMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Constants.CLICKABLE = 1;
                        Toast.makeText(QuestionsActivity.this, "Some problem occurred!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                progressBar.setVisibility(View.GONE);
                Constants.CLICKABLE = 1;
                Toast.makeText(QuestionsActivity.this, "Please login and retry!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void minusPoints(int points, int fragmentNumber) {
        switch (fragmentNumber)
        {
            case 1:
                if (fragment1 != 0)
                {
                    fragment1 = 0;
                    totalRiskScore -= 2;
                    if (totalRiskScore < 0)
                    {
                        totalRiskScore = 0;
                    }
                }
                break;
            case 2:
                if (fragment2 != 0)
                {
                    fragment2 = 0;
                    totalRiskScore -= 2;
                    if (totalRiskScore < 0)
                    {
                        totalRiskScore = 0;
                    }
                }
                break;
            case 3:
                if (fragment3 != 0)
                {
                    fragment3 = 0;
                    totalRiskScore -= 2;
                    if (totalRiskScore < 0)
                    {
                        totalRiskScore = 0;
                    }
                }
                break;
            case 4:
                if (fragment4 != 0)
                {
                    fragment4 = 0;
                    totalRiskScore -= 4;
                    if (totalRiskScore < 0)
                    {
                        totalRiskScore = 0;
                    }
                }
                break;
            case 5:
                if (fragment5 != 0)
                {
                    fragment5 = 0;
                    totalRiskScore -= 2;
                    if (totalRiskScore < 0)
                    {
                        totalRiskScore = 0;
                    }
                }
                break;
        }

        if (fragmentNumber == 5)
        {
            if (user != null)
            {
                Constants.CLICKABLE = 2;
                progressBar.setVisibility(View.VISIBLE);
                int myRisk = totalRiskScore;
                if (age >= 64)
                {
                    myRisk += 2;
                }
                if (myRisk < 1)
                {
                    myRisk = 1;
                }
                else
                {

                    if (myRisk > 5)
                    {
                        myRisk = 5;
                    }
                }
                String uid = user.getUid();
                DocumentReference docRef = firebaseFirestore.collection("user").document(uid);
                Map<String, Object> edited = new HashMap<>();
                edited.put("risk_score", String.valueOf(myRisk));
                docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Constants.CLICKABLE = 1;
                        Intent intent = new Intent(QuestionsActivity.this, NewMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Constants.CLICKABLE = 1;
                        Toast.makeText(QuestionsActivity.this, "Some problem occurred!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                progressBar.setVisibility(View.GONE);
                Constants.CLICKABLE = 1;
                Toast.makeText(QuestionsActivity.this, "Please login and retry!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void nextPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Override
    public void previousPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    @Override
    public void updateRiskScore() {
        String message = "Your risk score is : " + totalRiskScore;
        riskScoreTV.setText(message);
    }

    private void calculateAge()
    {
        String ageString = Constants.USER_DOB;
        String[] parts = ageString.split("/");
        int year = 0;

        try {
            String yearString = parts[2];
            year = Integer.parseInt(yearString);
            age = 2000 - year;
            age += 20;
        }
        catch (Exception e)
        {

        }
    }
}