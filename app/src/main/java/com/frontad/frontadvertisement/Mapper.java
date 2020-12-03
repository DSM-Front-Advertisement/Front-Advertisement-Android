package com.frontad.frontadvertisement;

import com.frontad.frontadvertisement.main.SearchResult;
import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.watchedad.WatchedAd;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private Mapper(){}

    public static List<Ad> searchResultToAd(List<SearchResult> searchResult){
        List<Ad> arrayList = new ArrayList<>();
        for (SearchResult result: searchResult) {
            if(result.mediaType ==1) {
                arrayList.add(new Ad(result.title, result.mediaType, result.subLink, result.value));
            }
            else{
                arrayList.add(new Ad(result.title, result.mediaType, result.subLink, Integer.parseInt(result.value)));

            }
        }
        return arrayList;

    }

    public static WatchedAd AdToWatchedAd(Ad ad){
        return new WatchedAd(ad.advertisementId);
    }

}
