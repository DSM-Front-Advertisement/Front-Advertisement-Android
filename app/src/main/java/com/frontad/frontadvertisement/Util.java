package com.frontad.frontadvertisement;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class Util {
    private Util(){

    }
    public static String sliceYoutubeLink(String youtubeLink){
        List<String> slicedLink = Arrays.asList(youtubeLink.split("v="));
        return slicedLink.get(1).split("&")[0];
    }
}
