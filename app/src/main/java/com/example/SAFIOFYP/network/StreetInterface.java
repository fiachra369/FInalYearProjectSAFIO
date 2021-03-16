package com.example.SAFIOFYP.network;

import com.example.SAFIOFYP.models.RecordsBody;
import com.example.SAFIOFYP.models.StreetBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface StreetInterface {
    @GET()
    Call<StreetBody> requestForStreet(@Url String url);
}
