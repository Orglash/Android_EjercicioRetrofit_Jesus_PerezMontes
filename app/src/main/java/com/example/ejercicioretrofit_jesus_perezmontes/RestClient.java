package com.example.ejercicioretrofit_jesus_perezmontes;

import retrofit2.Call;
import retrofit2.http.GET;
public interface RestClient {
    @GET("character/?limit=151")
    Call<RyMFeed> getData();
}
