package com.frontad.frontadvertisement.main;

import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.watchedad.WatchedAdDao;

import java.util.List;

public interface MainAdContract {

    interface Presenter {
        void onCreate();

        void onCategoryClicked(String category);

        void onSearchClicked();

        void onCategoryClicked();

        void onAdClicked(int advertisementId);

        void loadAds(int page);

        void loadMoreAds(int page);

    }

    interface View {

        void setAdDetailDrawerLayout();

        void setNavigationToAdQuestion();

        void setNavigationToWatchedAd();

        void setUpView();

        String getInputKeyword();

        WatchedAdDao setWatchedAdRecording();

        void showCategoryChip();

        void hideCategoryChip();

        void showToast();


        void setAdContents(List<Ad> adContents);

        void loadMoreAdContents(List<Ad> adContents);

        void sortByCategory(String category);

    }
}
