package com.frontad.frontadvertisement.remote;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkService {

    private static NetworkService instance = null;
    private NetworkService(){

    }

    public static NetworkService getInstance(){
        if(instance==null)
            instance = new NetworkService();

        return instance;
    }
    private final Interceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    private final Retrofit retrofit =
             new Retrofit.Builder().baseUrl("http://13.209.255.193:8080/")
             .addConverterFactory(GsonConverterFactory.create())
             .client(client)
             .build();
    public final Api service = retrofit.create(Api.class);

}
