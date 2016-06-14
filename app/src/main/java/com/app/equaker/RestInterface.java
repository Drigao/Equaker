package com.app.equaker;


import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

//Call<KuakeLocation> getFormEarthquakes(@Path("year") String yearId, @Path("month") String monthId, @Query("min_magnitude") double minMagnitude);
public interface RestInterface {
    @FormUrlEncoded
    @POST("php.php")
    Call<List<KuakeLocation>> getEartquakes(@Field(value = "get_markers_by") String markers,
                                            @Field(value = "mags", encoded = true) String mags,
                                            @Field(value = "date_from", encoded = true) String fromDate,
                                            @Field(value = "date_to", encoded = true) String toDate,
                                            @Field(value = "orderby") String order);
}
