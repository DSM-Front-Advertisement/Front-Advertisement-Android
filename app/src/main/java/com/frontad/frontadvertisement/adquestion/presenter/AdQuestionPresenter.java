package com.frontad.frontadvertisement.adquestion.presenter;

import com.frontad.frontadvertisement.adquestion.contract.AdQuestionContract;
import com.frontad.frontadvertisement.adquestion.model.AdQuestionRepository;

public abstract class AdQuestionPresenter<T extends AdQuestionContract.View, E extends AdQuestionRepository> implements AdQuestionContract.Presenter {

    protected final E repository;

    protected final T view;


    public AdQuestionPresenter(T view, E repository){
        this.view = view;
        this.repository = repository;
    }
    @Override
    public void onQuestClicked() {
        if(repository.questAd(view.getAdQuestion()))
            view.completeQuest();
        else
            view.failQuest();
    }


}
