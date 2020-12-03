package com.frontad.frontadvertisement.main.model;

import com.frontad.frontadvertisement.main.SearchResult;

import java.util.List;

public class AdRepository {

    private final AdModel adModel;

    public AdRepository(AdModel adModel){
        this.adModel = adModel;

    }
    public List<Ad> getAds(int page) {
        return adModel.getAds(page);

    }

    public List<SearchResult> searchAd(String searchText) {
        return adModel.searchAds(searchText);
    }

    public void insertWatchedAd(int advertisementId){
        adModel.insertWatchedAd(advertisementId);
    }
}
