package com.example.schooldiary.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.schooldiary.utils.TypeConverterEnum;


@Entity(tableName = "Subjects")
public class SubjectItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "teacher")
    private String teacher;

    @TypeConverters({TypeConverterEnum.class})
    private Subjects type;

   // public SubjectItem(){}

    public SubjectItem(String name, String teacher, Subjects type){
        this.name = name;
        this.type = type;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Subjects getType() {
        return type;
    }

    public void setType(Subjects type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdString(){
        return String.valueOf(id);
    }
    //сюда же добавим учителя
}
