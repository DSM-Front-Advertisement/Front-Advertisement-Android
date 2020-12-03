package com.frontad.frontadvertisement.adquestion.presenter;

import com.frontad.frontadvertisement.adquestion.view.ImageAdView;
import com.frontad.frontadvertisement.adquestion.model.AdQuestionModel;
import com.frontad.frontadvertisement.adquestion.model.ImageAdQuestionRepository;
import com.frontad.frontadvertisement.remote.NetworkService;

public class ImageAdQuestionPresenter extends AdQuestionPresenter<ImageAdView, ImageAdQuestionRepository>{

    public ImageAdQuestionPresenter(ImageAdView view){
        super(view, new ImageAdQuestionRepository(new AdQuestionModel(NetworkService.getInstance().service)));
    }

    public void onAttachImage(){
        view.attachAdImage();
    }
}
