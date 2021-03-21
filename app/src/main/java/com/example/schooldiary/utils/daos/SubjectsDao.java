package com.example.schooldiary.utils.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schooldiary.model.SubjectItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface SubjectsDao {

    @Query("SELECT * FROM Subjects")
    Maybe<List<SubjectItem>> getSubjects();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addSubject(SubjectItem subject);

    @Delete
    void deleteSubject(SubjectItem subject);

    @Update
    void updateSubject(SubjectItem subjectItem);
}
