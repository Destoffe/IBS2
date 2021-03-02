package com.stoffe.ibs2.data;

import org.threeten.bp.LocalDate;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Day {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "toilet_visits")
    private int toiletVisits;
    @ColumnInfo(name = "exercises")
    private int exercise;
    @ColumnInfo(name = "pain")
    private int pain;
    @ColumnInfo(name = "stool")
    private int stool;
    @ColumnInfo(name = "date")
    @TypeConverters({DateConverter.class})
    private LocalDate date;
    @ColumnInfo(name = "isTempDay")
    private boolean isTempDay;
    @ColumnInfo(name = "foods")
    @TypeConverters({ListConverter.class})
    private List<String> foods;


    public Day(int toiletVisits, int exercise, int pain, int stool, LocalDate date,List<String> foods) {
        this.toiletVisits = toiletVisits;
        this.exercise = exercise;
        this.pain = pain;
        this.stool = stool;
        this.date = date;
        this.foods = foods;
    }

    public Day(int toiletVisits, int exercise, int pain, int stool, LocalDate date,List<String> foods, boolean isTempDay) {
        this.toiletVisits = toiletVisits;
        this.exercise = exercise;
        this.pain = pain;
        this.stool = stool;
        this.date = date;
        this.isTempDay = isTempDay;
        this.foods = foods;
    }

    public Day() {

    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setToiletVisits(int toiletVisits) {
        this.toiletVisits = toiletVisits;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

    public void setPain(int pain) {
        this.pain = pain;
    }

    public void setStool(int stool) {
        this.stool = stool;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getToiletVisits() {
        return toiletVisits;
    }

    public int getExercise() {
        return exercise;
    }

    public int getPain() {
        return pain;
    }

    public int getStool() {
        return stool;
    }

    public int getUid() {
        return uid;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isTempDay() {
        return isTempDay;
    }

    public void setTempDay(boolean tempDay) {
        isTempDay = tempDay;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
