package com.stoffe.ibs2.data;


import android.content.Context;

import androidx.databinding.adapters.Converters;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {Day.class}, version = 13,exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class Database extends RoomDatabase {
    public abstract DayDao dayDao();

    private static Database INSTANCE;

    public static Database getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class,"ibs-database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}