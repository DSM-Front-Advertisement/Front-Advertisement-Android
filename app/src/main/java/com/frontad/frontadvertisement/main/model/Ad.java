package com.frontad.frontadvertisement.main.model;

public class Ad {

    public String advertisementTitle;
    public int advertisementId;
    public String advertisementCategoryType;
    public int mediaType;
    public String subLink;
    public String youtubeLink;
    public int imageId;

    public Ad(String advertisementTitle, int mediaType, String subLink, String youtubeLink){
        this.advertisementTitle = advertisementTitle;
        this.mediaType = mediaType;
        this.subLink = subLink;
        this.youtubeLink = youtubeLink;
    }
    public Ad(String advertisementTitle, int mediaType, String subLink, int imageId){
        this.advertisementTitle = advertisementTitle;
        this.mediaType = mediaType;
        this.subLink = subLink;
        this.imageId = imageId;
    }




}