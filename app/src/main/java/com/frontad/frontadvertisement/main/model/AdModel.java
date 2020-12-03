package com.frontad.frontadvertisement.main.model;

import com.frontad.frontadvertisement.main.SearchResult;
import com.frontad.frontadvertisement.remote.Api;
import com.frontad.frontadvertisement.watchedad.WatchedAd;
import com.frontad.frontadvertisement.watchedad.WatchedAdDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;

public class AdModel {

    private final Api api;

    private final WatchedAdDao watchedAdDao;

    public AdModel(Api api, WatchedAdDao watchedAdDao){
        this.api = api;
        this.watchedAdDao = watchedAdDao;
    }

    public List<WatchedAd> getWatchedAds(){
        return watchedAdDao.getAll();
    }


    public List<Ad> getAds(int page) {
        final List<Ad> ads = new ArrayList<>();
        Runnable r = () -> {
            try {
                Response<AdResponse> response =api.getAds(13,page).execute();
                if(response.isSuccessful()) {
                    ads.addAll(response.body().advertiseResponse);
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

    public List<SearchResult> searchAds(String keyword) {
        final List<SearchResult> ads = new ArrayList<>();
        Runnable r = () -> {
            try {
                Response<List<SearchResult>> response =api.searchAd(keyword).execute();
                if(response.isSuccessful()) {
                    ads.addAll(Objects.requireNonNull(response.body()));
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

    public void insertWatchedAd(int advertisementId){
        Runnable r = () -> watchedAdDao.insert(new WatchedAd(advertisementId));
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
