package com.example.SAFIOFYP.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.main.NewMainActivity;
import com.example.SAFIOFYP.main.QuestionsActivity;
import com.example.SAFIOFYP.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView forgotTextLink;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    public static final String NAME = "NAME";
    public static final String NUMBER = "NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String CONDITION = "CONDITION";
    public static final String DOB = "DOB";

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        fStore = FirebaseFirestore.getInstance();
        mEmail = findViewById(R.id.loginName);
        mPassword = findViewById(R.id.loginPassword);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }


                // authenticate the user


                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            setRiskScore();

                        }else {
                            Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }

    public void setRiskScore()
    {
        FirebaseUser firebaseUser = fAuth.getCurrentUser();
        final DocumentReference documentReference = fStore.collection("user").document(firebaseUser.getUid());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("TASK SUCCESS", "=======================================ABOVE");
                    String dob;
                    if (task.getResult().get("date") != null)
                    {
                        dob = Objects.requireNonNull(task.getResult().get("date")).toString();
                        Constants.USER_DOB = dob;
                    }
                    try {
                        int risk_score = Integer.parseInt(Objects.requireNonNull(task.getResult().get("risk_score").toString()));
                        Log.d("TASK SUCCESS", "================BELOW=================" +  risk_score);


                        if (risk_score >= 0 || risk_score <= 5) {
                            Constants.USER_RISK_SCORE = risk_score;
                        }
                        Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, NewMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    catch (Exception e)
                    {
                        Intent intent = new Intent(LoginActivity.this, QuestionsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Log.d("TASK FAIL", "=======================================" );
                    Toast.makeText(LoginActivity.this, "Some problem occurred while getting your risk score", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Some problem occurred while logging in!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
