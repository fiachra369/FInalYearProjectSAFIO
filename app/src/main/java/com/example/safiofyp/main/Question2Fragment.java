package com.example.safiofyp.main;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.safiofyp.R;
import com.example.safiofyp.utils.Constants;
import com.example.safiofyp.utils.PagerInterface;


public class Question2Fragment extends Fragment {


    public Question2Fragment() {
        // Required empty public constructor
    }


    View rootView;
    CardView yesCard, noCard;
    PagerInterface pagerInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_question2, container, false);

        yesCard = rootView.findViewById(R.id.yes_card);

        noCard = rootView.findViewById(R.id.no_card);

        pagerInterface = (PagerInterface) getActivity();

        yesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.CLICKABLE == 1) {
                    pagerInterface.addPoints(2, 2);
                    pagerInterface.nextPage();
                    pagerInterface.updateRiskScore();
                    yesCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.lightGray));
                    noCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.white));
                } else {
                    Toast.makeText(getContext(), "Please wait while the app is updating values!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.CLICKABLE == 1) {
                    pagerInterface.minusPoints(2, 2);
                    pagerInterface.nextPage();
                    pagerInterface.updateRiskScore();
                    yesCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.white));
                    noCard.setCardBackgroundColor(getContext().getResources().getColor(R.color.lightGray));
                } else {
                    Toast.makeText(getContext(), "Please wait while the app is updating values!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }
}