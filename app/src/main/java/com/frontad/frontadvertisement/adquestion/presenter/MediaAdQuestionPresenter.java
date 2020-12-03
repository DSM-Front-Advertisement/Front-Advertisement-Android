package com.frontad.frontadvertisement.adquestion.presenter;

import com.frontad.frontadvertisement.adquestion.view.MediaAdView;
import com.frontad.frontadvertisement.adquestion.model.AdQuestionModel;
import com.frontad.frontadvertisement.adquestion.model.MediaAdQuestionRepository;
import com.frontad.frontadvertisement.remote.NetworkService;

public class MediaAdQuestionPresenter extends AdQuestionPresenter<MediaAdView, MediaAdQuestionRepository>{

    public MediaAdQuestionPresenter(MediaAdView view) {
        super(view, new MediaAdQuestionRepository(new AdQuestionModel(NetworkService.getInstance().service)));
    }







}
