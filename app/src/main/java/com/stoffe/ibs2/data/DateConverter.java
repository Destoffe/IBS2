package com.stoffe.ibs2.data;


import org.threeten.bp.LocalDate;

import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public static LocalDate toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return LocalDate.parse(dateString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }




}
