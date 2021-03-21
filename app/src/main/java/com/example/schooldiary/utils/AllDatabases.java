package com.example.schooldiary.utils;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.schooldiary.model.DayItem;
import com.example.schooldiary.model.NotesTable;
import com.example.schooldiary.model.SubjectItem;
import com.example.schooldiary.utils.daos.NotesDao;
import com.example.schooldiary.utils.daos.SubjectsDao;
import com.example.schooldiary.utils.daos.DaysDiaryDao;
import com.example.schooldiary.model.TableItem;
import com.example.schooldiary.utils.daos.TableItemsDao;

@Database(version = 5,entities = {TableItem.class, DayItem.class, SubjectItem.class, NotesTable.class})
public abstract class AllDatabases  extends RoomDatabase {
    public abstract SubjectsDao getSubjectsDao();
    public abstract DaysDiaryDao getDiaryDao();
    public abstract TableItemsDao getTableItemsDao();
    public abstract NotesDao getNotesDao();
}
