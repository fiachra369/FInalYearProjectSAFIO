package com.example.safiofyp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.safiofyp.main.Question1Fragment;
import com.example.safiofyp.main.Question2Fragment;
import com.example.safiofyp.main.Question3Fragment;
import com.example.safiofyp.main.Question4Fragment;
import com.example.safiofyp.main.Question5Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES = 5;
    private Question1Fragment question1Fragment;
    private Question2Fragment question2Fragment;
    private Question3Fragment question3Fragment;
    private Question4Fragment question4Fragment;
    private Question5Fragment question5Fragment;
    public ViewPagerAdapter(FragmentActivity fa,
                            Question1Fragment question1Fragment,
                            Question2Fragment question2Fragment,
                            Question3Fragment question3Fragment,
                            Question4Fragment question4Fragment,
                            Question5Fragment question5Fragment) {
        super(fa);
        this.question1Fragment = question1Fragment;
        this.question2Fragment = question2Fragment;
        this.question3Fragment = question3Fragment;
        this.question4Fragment = question4Fragment;
        this.question5Fragment = question5Fragment;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0: return question1Fragment;
            case 1: return question2Fragment;
            case 2: return question3Fragment;
            case 3: return question4Fragment;
            case 4: return question5Fragment;
            default: return question5Fragment;
        }
    }


    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
