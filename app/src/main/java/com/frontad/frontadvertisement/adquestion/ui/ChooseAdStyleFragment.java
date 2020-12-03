package com.frontad.frontadvertisement.adquestion.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.databinding.FragmentChooseAdStyleBinding;

public class ChooseAdStyleFragment extends BaseFragment<FragmentChooseAdStyleBinding> {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imgStyleCard.setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.main_container)
                .navigate(R.id.action_chooseAdStyleFragment_to_imageAdQuestionFragment));

        binding.youtubeLinkStyleCard.setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.main_container)
                .navigate(R.id.action_chooseAdStyleFragment_to_mediaAdQuestionFragment));
    }

    @Override
    public FragmentChooseAdStyleBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChooseAdStyleBinding.inflate(inflater,container,false);
    }
}