package com.frontad.frontadvertisement.watchedad;

import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.remote.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class WatchedAdModel {
   private final Api api;
   private final WatchedAdDao watchedAdDao;

   public WatchedAdModel(Api api, WatchedAdDao watchedAdDao){
       this.api = api;
       this.watchedAdDao = watchedAdDao;
   }

   public List<Ad> getWatchedAds(){
       final List<Ad> ads = new ArrayList<>();
       Runnable r = () -> {
           try {
               List<WatchedAd> watchedAds = watchedAdDao.getAll();
               for (WatchedAd watchedAd : watchedAds){
                   Response<Ad> response = api.getAd(watchedAd.advertisementId).execute();
                   if (response.isSuccessful()) {
                       ads.add(response.body());
                   }
               }
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
       return ads;
   }


}
