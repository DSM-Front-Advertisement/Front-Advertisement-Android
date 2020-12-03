package com.frontad.frontadvertisement.watchedad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watched_ad")
public class WatchedAd {

    public WatchedAd(int advertisementId){
        this.advertisementId = advertisementId;

    }

    @PrimaryKey
    public int advertisementId;


}