package com.frontad.frontadvertisement.remote;

import com.frontad.frontadvertisement.main.SearchResult;
import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.main.model.AdResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("advertisements")
    Call<AdResponse> getAds(@Query("size") int size , @Query("page") int page);

    @GET("image/{advertisementId}")
    Call<AdResponse> getImage(@Path("advertisementId") int advertisementId);

    @Multipart
    @POST("advertisement")
    Call<Void> registerYoutubeLinkAd(@Part MultipartBody.Part mediaType,
                                @Part MultipartBody.Part advertisementTitle,
                                @Part MultipartBody.Part subLink,
                                @Part MultipartBody.Part advertisementCategoryType,
                                @Part MultipartBody.Part youtubeLink);
    @Multipart
    @POST("advertisement")
    Call<Void> registerImageAd(@Part MultipartBody.Part mediaType,
                                @Part MultipartBody.Part advertisementTitle,
                                @Part MultipartBody.Part subLink,
                                @Part MultipartBody.Part advertisementCategoryType,
                                @Part MultipartBody.Part imageFile);


    @GET("advertisement/")
    Call<List<SearchResult>> searchAd(@Query("keyword") String keyword);

    @GET("advertisement/{advertisementId}")
    Call<Ad> getAd(@Path("advertisementId") int advertisementId);

}
