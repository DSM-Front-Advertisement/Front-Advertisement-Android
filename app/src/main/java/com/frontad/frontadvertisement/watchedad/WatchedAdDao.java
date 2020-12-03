package com.frontad.frontadvertisement.watchedad;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WatchedAdDao {

    @Query("SELECT * FROM watched_ad")
    List<WatchedAd> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WatchedAd> watchedAds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WatchedAd watchedAd);

}
