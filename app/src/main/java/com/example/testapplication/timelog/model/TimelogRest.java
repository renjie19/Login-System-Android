package com.example.testapplication.timelog.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TimelogRest {

    @POST("api/facade/save")
    Call<TimeLog> login(@Body int id);
}
