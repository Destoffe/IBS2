package com.stoffe.ibs2.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DayViewModel extends AndroidViewModel {

    private MutableLiveData<List<Day>> days;
    private DayRepository repository;
    private MutableLiveData<Day> tempDay;

    public DayViewModel(Application application) {
        super(application);
        days = new MutableLiveData<>();
        tempDay = new MutableLiveData<>();
        repository = new DayRepository(application);
    }

    public List<Day> getDays() {
        return days.getValue();
    }

    public LiveData<List<Day>> initDays() {
        return repository.getDays();
    }

    public void insertDay(Day day) {
        repository.insertDay(day);
    }

    public void setDays(List<Day> days) {
        this.days.setValue(days);
    }

    public List<Day> testDays() {
        return repository.getDays().getValue();
    }

    public void setTempDay(Day days) {
        this.tempDay.setValue(days);
    }

    public void removeTempDay(Day days) {
        repository.deleteDay(days);
    }

    public Day getTempDay() {
        return tempDay.getValue();
    }
}