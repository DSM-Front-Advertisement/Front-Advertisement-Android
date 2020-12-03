package com.frontad.frontadvertisement.main;

import com.frontad.frontadvertisement.Mapper;
import com.frontad.frontadvertisement.main.model.AdModel;
import com.frontad.frontadvertisement.main.model.AdRepository;
import com.frontad.frontadvertisement.remote.NetworkService;

public class MainAdPresenter implements MainAdContract.Presenter{

    private final MainAdContract.View view;

    private AdRepository repository;

    public MainAdPresenter(MainAdContract.View view){
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.setUpView();
        view.setAdDetailDrawerLayout();
        view.setNavigationToAdQuestion();
        view.setNavigationToWatchedAd();
        this.repository = new AdRepository(new AdModel(NetworkService.getInstance().service,view.setWatchedAdRecording()));
    }

    @Override
    public void onCategoryClicked(String category) {
        view.sortByCategory(category);
    }

    @Override
    public void onSearchClicked() {
        view.setAdContents(Mapper.searchResultToAd(repository.searchAd(view.getInputKeyword())));
    }

    @Override
    public void onCategoryClicked() {

    }

    @Override
    public void onAdClicked(int advertisementId) {
        repository.insertWatchedAd(advertisementId);
    }

    @Override
    public void loadAds(int page) {
        view.setAdContents(repository.getAds(page));

    }

    @Override
    public void loadMoreAds(int page) {
        view.loadMoreAdContents(repository.getAds(page));
    }
}
