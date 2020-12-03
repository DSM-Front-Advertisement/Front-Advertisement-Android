package com.frontad.frontadvertisement.adquestion.model;

import android.util.Log;

import com.frontad.frontadvertisement.remote.Api;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AdQuestionModel {
    private final Api api;

    public AdQuestionModel(Api api){
        this.api = api;
    }
    public boolean questImageAd(AdQuestion adQuestion){
        final AtomicBoolean isSuccess = new AtomicBoolean();
        Runnable r = () -> {
            try {
                RequestBody fileBody = RequestBody.create(adQuestion.adImage, MediaType.parse("image/*"));
                isSuccess.set(api.registerImageAd(
                        MultipartBody.Part.createFormData("advertisementTitle", adQuestion.adName),
                        MultipartBody.Part.createFormData("advertisementCategoryType", adQuestion.category),
                        MultipartBody.Part.createFormData("subLink", adQuestion.subLink),
                        MultipartBody.Part.createFormData("mediaType", Integer.toString(adQuestion.mediaType)),
                        MultipartBody.Part.createFormData("imageFile", adQuestion.adImage.getName(), fileBody)
                ).execute().isSuccessful());

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isSuccess.get();
    }



    public boolean questYoutubeAd(AdQuestion adQuestion){
        final AtomicBoolean isSuccess = new AtomicBoolean();
        Runnable r = () -> {
            try {
                isSuccess.set(api.registerYoutubeLinkAd(
                        MultipartBody.Part.createFormData("advertisementTitle", adQuestion.adName),
                        MultipartBody.Part.createFormData("advertisementCategoryType", adQuestion.category),
                        MultipartBody.Part.createFormData("subLink", adQuestion.subLink),
                        MultipartBody.Part.createFormData("mediaType", Integer.toString(adQuestion.mediaType)),
                        MultipartBody.Part.createFormData("youtubeLink", adQuestion.youtubeLink)
                ).execute().isSuccessful());

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("isSuccess", isSuccess.toString());
        return isSuccess.get();
    }


}
