package com.frontad.frontadvertisement.adquestion.ui;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frontad.frontadvertisement.Util;
import com.frontad.frontadvertisement.adquestion.model.AdQuestion;
import com.frontad.frontadvertisement.adquestion.presenter.MediaAdQuestionPresenter;
import com.frontad.frontadvertisement.adquestion.view.MediaAdView;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.databinding.FragmentMediaAdQuestionBinding;
import com.google.android.material.chip.Chip;


public class MediaAdQuestionFragment extends BaseFragment<FragmentMediaAdQuestionBinding> implements MediaAdView {

    private MediaAdQuestionPresenter presenter;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MediaAdQuestionPresenter(this);


        binding.questBtn.setOnClickListener(v -> presenter.onQuestClicked());

    }

    @Override
    public FragmentMediaAdQuestionBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMediaAdQuestionBinding.inflate(inflater, container, false);
    }

    @Override
    public void completeQuest() {
        Navigation.findNavController(requireActivity(),R.id.main_container)
                .navigate(R.id.action_mediaAdQuestionFragment_to_mainFragment);
        Toast.makeText(requireActivity().getApplicationContext(), "문의가 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failQuest() {
        Toast.makeText(requireContext(),"실패", Toast.LENGTH_SHORT).show();

    }

    @Override
    public String getCheckedChip(){

        return ((Chip)requireView().
                findViewById(binding.categoryCg.getCheckedChipId()))
                .getText().toString();
    }

    @Override
    public AdQuestion getAdQuestion() {
        return new AdQuestion(
                binding.mediaAdNameEt.getText().toString(),
                getCheckedChip(),
                binding.siteEt.getText().toString(),
                1, getYoutubeLink(),null);
    }

    @Override
    public String getYoutubeLink() {
        return Util.sliceYoutubeLink(binding.mediaAdContentEt.getText().toString());
    }


}