package com.frontad.frontadvertisement.watchedad;

import com.frontad.frontadvertisement.remote.NetworkService;

public class WatchedAdPresenter implements WatchedAdContract.Presenter{

    private WatchedAdModel model;

    private final WatchedAdContract.View view;


    public WatchedAdPresenter(WatchedAdContract.View view){
       this.view = view;
       model = new WatchedAdModel(NetworkService.getInstance().service, view.setWatchedAdForLoading());
    }


    @Override
    public void onAdClicked(int advertisementId) {
    }
    @Override
    public void onCreate() {
        view.loadWatchedAds(model.getWatchedAds());

    }
}
