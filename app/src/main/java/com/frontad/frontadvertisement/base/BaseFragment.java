package com.frontad.frontadvertisement.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = setBinding(inflater, container);
        return binding.getRoot();
    }

    public abstract T setBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
