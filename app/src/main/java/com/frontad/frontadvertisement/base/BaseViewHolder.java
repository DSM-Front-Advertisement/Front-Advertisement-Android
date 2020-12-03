package com.frontad.frontadvertisement.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.frontad.frontadvertisement.main.model.Ad;

public abstract class BaseViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    protected final T binding;
    public BaseViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public abstract void bind(Ad ad);
}
