package com.frontad.frontadvertisement.watchedad;

import com.frontad.frontadvertisement.main.model.Ad;

import java.util.List;

public interface WatchedAdContract {
    interface Presenter{
        void onCreate();

        void onAdClicked(int advertisementId);
    }


    interface View{
        void loadWatchedAds(List<Ad> ads);

        WatchedAdDao setWatchedAdForLoading();

    }
}
