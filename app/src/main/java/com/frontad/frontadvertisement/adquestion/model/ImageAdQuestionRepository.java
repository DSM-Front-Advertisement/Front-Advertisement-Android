package com.frontad.frontadvertisement.adquestion.model;

public class ImageAdQuestionRepository implements AdQuestionRepository {

    private final AdQuestionModel model;

    public ImageAdQuestionRepository(AdQuestionModel model){
        this.model = model;
    }


    @Override
    public boolean questAd(AdQuestion adQuestion) {
        return model.questImageAd(adQuestion);

    }
}
