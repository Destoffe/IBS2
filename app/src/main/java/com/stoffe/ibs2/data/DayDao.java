package com.stoffe.ibs2.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DayDao {

    @Query("SELECT * FROM day ORDER BY date ASC")
    LiveData<List<Day>> getAll();

    @Query("SELECT * FROM day WHERE uid IN (:userIds)")
    List<Day> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Day... days);

    @Delete
    void deleteDay(Day user);

}
