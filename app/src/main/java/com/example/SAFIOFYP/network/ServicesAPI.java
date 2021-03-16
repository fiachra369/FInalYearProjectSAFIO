package com.example.SAFIOFYP.network;

import com.example.SAFIOFYP.models.LocationDetailResponse;
import com.example.SAFIOFYP.models.LocationResponse;
import com.example.SAFIOFYP.models.RecordsBody;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ServicesAPI {

    @GET()
    Call<RecordsBody> requestForRecords(@Url String url);


    @GET()
    Call<LocationResponse> requestForBikeLocations(@Url String url);

    @GET()
    Call<List<LocationDetailResponse>> requestForBikeLocationDetails(@Url String url);



}
