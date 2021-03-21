package com.example.schooldiary.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.schooldiary.model.DayItem;
import com.example.schooldiary.utils.daos.NotesDao;
import com.example.schooldiary.utils.daos.SubjectsDao;
import com.example.schooldiary.utils.daos.DaysDiaryDao;
import com.example.schooldiary.utils.daos.TableItemsDao;
import com.example.schooldiary.view.MainActivity;

import java.util.Calendar;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DBSingleton {
    private AllDatabases databases;
    private static DBSingleton singleton;

    private DBSingleton(Context context){
        databases = Room.databaseBuilder(context,AllDatabases.class,"database").fallbackToDestructiveMigration().allowMainThreadQueries()
               .build();
    }

    public static DBSingleton getInstance(Context context) {
        if (singleton == null){
            singleton = new DBSingleton(context);
        }
        return singleton;
    }

    public DaysDiaryDao getDiaryDao(){
        return databases.getDiaryDao();
    }

    public TableItemsDao getTableItemsDao(){
        return databases.getTableItemsDao();
    }
    public SubjectsDao getSubjectsDao(){
        return databases.getSubjectsDao();
    }

    public NotesDao getNotesDao(){
        return databases.getNotesDao();
    }



}
