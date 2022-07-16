package com.example.admincollegeapp.api;

import static com.example.admincollegeapp.Constants.CONTENT_TYPE;
import static com.example.admincollegeapp.Constants.SERVER_KEY;

import com.example.admincollegeapp.PushNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Authorization : key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
@POST("fcm/send")
    Call<PushNotification> sendNotificatiion(@Body PushNotification notification);
}
