package com.frontad.frontadvertisement.adquestion.contract;

import com.frontad.frontadvertisement.adquestion.model.AdQuestion;

public interface AdQuestionContract {

    interface Presenter{
        void onQuestClicked();
    }

    interface View{
        void completeQuest();

        void failQuest();

        AdQuestion getAdQuestion();

        String getCheckedChip();


    }
}
