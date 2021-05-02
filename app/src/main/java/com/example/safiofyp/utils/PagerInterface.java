package com.example.safiofyp.utils;

public interface PagerInterface {
    public void addPoints(int points, int fragmentNumber);
    public void minusPoints(int points, int fragmentNumber);
    public void nextPage();
    public void previousPage();
    public void updateRiskScore();
}
