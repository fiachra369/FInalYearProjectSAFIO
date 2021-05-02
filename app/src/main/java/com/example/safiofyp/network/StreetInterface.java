package com.example.safiofyp.network;

import com.example.safiofyp.models.StreetBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface StreetInterface {
    @GET()
    Call<StreetBody> requestForStreet(@Url String url);
}
