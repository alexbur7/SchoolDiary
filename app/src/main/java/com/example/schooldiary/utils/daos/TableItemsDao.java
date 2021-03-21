package com.example.schooldiary.utils.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.example.schooldiary.model.DayAndTableItems;
import com.example.schooldiary.model.TableItem;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface TableItemsDao {
    @Transaction
    @Query("SELECT * FROM diary")
    //Flowable<List<DayAndTableItems>> getDayTableItems();
    Flowable<List<DayAndTableItems>> getDayTableItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTableItem(TableItem item);

    @Delete
    void deleteTableItem(TableItem subject);

    @Update
    void updateTableItem(TableItem subjectItem);
}
