package com.stoffe.ibs2.data;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.telecom.InCallService;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DayRepository {
    DayDao dayDao;

    DayRepository(Application application){
        Database db = Database.getDatabase(application);
        dayDao = db.dayDao();
    }

    /*
    public void insert(Person person){
        new insertAsyncTask()
    }

    private static class insertAsyncTank extends AsyncTask<Person,Void, void>

     */

    LiveData<List<Day>> getDays(){
        return dayDao.getAll();
    }

    void insertDay(Day day){
        new insertAsyncTank(dayDao).execute(day);
    }
    void deleteDay(Day day){
        new deleteAsynsTask(dayDao).execute(day);
    }


    private static class insertAsyncTank extends AsyncTask<Day, Void, Void> {

        private DayDao mAsyncTaskDao;

        insertAsyncTank(DayDao dayDao){
            mAsyncTaskDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... day) {
            mAsyncTaskDao.insertAll(day[0]);
            return null;
        }
    }

    private static class deleteAsynsTask extends AsyncTask<Day, Void, Void> {

        private DayDao mAsyncTaskDao;

        deleteAsynsTask(DayDao dayDao){
            mAsyncTaskDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... day) {
            mAsyncTaskDao.deleteDay(day[0]);
            return null;
        }
    }

}
