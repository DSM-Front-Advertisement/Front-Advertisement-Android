package com.frontad.frontadvertisement.adquestion.model;

public class MediaAdQuestionRepository implements AdQuestionRepository {

    private final AdQuestionModel model;

    public MediaAdQuestionRepository(AdQuestionModel model) {
        this.model = model;
    }

    @Override
    public boolean questAd(AdQuestion adQuestion) {
        return model.questYoutubeAd(adQuestion);
    }
}
