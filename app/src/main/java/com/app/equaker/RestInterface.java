package com.app.equaker;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {
    @GET("/api/eqs/2011/03")
    Call<KuakeLocation> getEarthquakes();
}
