package com.frontad.frontadvertisement.adquestion.model;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public class AdQuestion {


    public final String adName;
    public final String category;
    public final int mediaType;
    public final String subLink;
    public final String youtubeLink;
    public final File adImage;

    public AdQuestion(String adName, String category, String subLink, int mediaType, String youtubeLink, File adImage){
        this.adName = adName;
        this.category = category;
        this.subLink = subLink;
        this.mediaType = mediaType;
        this.youtubeLink = youtubeLink;
        this.adImage = adImage;

    }




}
