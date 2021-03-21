package com.example.schooldiary.utils.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schooldiary.model.DayItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DaysDiaryDao {

    @Query("SELECT * FROM diary")
    Flowable<List<DayItem>> getDiary();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDay(DayItem item);

    @Update
    void updateDay(DayItem item);

    @Delete
    void deleteDay(DayItem item);

    @Query("DELETE FROM diary WHERE id = :id")
    void deleteForId(int id);
}
