package com.frontad.frontadvertisement.view;

import com.frontad.frontadvertisement.main.model.Ad;

public interface RecyclerItemClickListener {
    void onClick(int navAction, int position, Ad ad);
}
