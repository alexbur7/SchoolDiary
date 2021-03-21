package com.example.schooldiary.utils.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.schooldiary.model.NotesTable;


import io.reactivex.Maybe;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes WHERE date = :date AND name = :nameSubject")
    Maybe<NotesTable> getNotesTable(String date, String nameSubject);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNotesTable(NotesTable notesTable);

}
