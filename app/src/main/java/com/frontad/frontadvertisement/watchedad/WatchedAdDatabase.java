package com.frontad.frontadvertisement.watchedad;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WatchedAd.class}, version = 2)
public abstract class WatchedAdDatabase extends RoomDatabase {
    public abstract WatchedAdDao watchedAdDao();

    private static volatile WatchedAdDatabase instance;
    public static WatchedAdDatabase getInstance(final Context context){
        if(instance==null){
            synchronized (WatchedAdDatabase.class) {
                if(instance==null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), WatchedAdDatabase.class, "watched_ad_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }


}
