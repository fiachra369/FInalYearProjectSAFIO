package com.example.SAFIOFYP.main;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.utils.Constants;
import com.example.SAFIOFYP.utils.PagerInterface;

public class Question1Fragment extends Fragment {


    public Question1Fragment() {
        Log.d("FRAGMENT1 CREATED", "==============================================================");
    }


    View rootView;
    CardView yesCard, noCard;
    PagerInterface pagerInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_question1, container, false);

        Log.d("FRAGMENT1 VIEWCREATED", "==============================================================");

        yesCard = rootView.findViewById(R.id.yes_card);

        noCard = rootView.findViewById(R.id.no_card);


        pagerInterface = (PagerInterface) getActivity();

        yesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.CLICKABLE == 1) {
                    pagerInterface.addPoints(2, 1);
                    pagerInterface.nextPage();
                    pagerInterface.updateRiskScore();
                    yesCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.lightGray));
                    noCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.white));
                }
                else
                {
                    Toast.makeText(getContext(), "Please wait while the app is updating values!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.CLICKABLE == 1) {
                pagerInterface.minusPoints(2,  1);
                pagerInterface.nextPage();
                pagerInterface.updateRiskScore();
                yesCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.white));
                noCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.lightGray));
                }
                else
                {
                    Toast.makeText(getContext(), "Please wait while the app is updating values!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }
}