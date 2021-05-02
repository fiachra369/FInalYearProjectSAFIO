package com.example.safiofyp.network;

import com.example.safiofyp.models.LocationDetailResponse;
import com.example.safiofyp.models.LocationResponse;
import com.example.safiofyp.models.RecordsBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ServicesAPI {

    @GET()
    Call<RecordsBody> requestForRecords(@Url String url);


    @GET()
    Call<LocationResponse> requestForBikeLocations(@Url String url);

    @GET()
    Call<List<LocationDetailResponse>> requestForBikeLocationDetails(@Url String url);



}
