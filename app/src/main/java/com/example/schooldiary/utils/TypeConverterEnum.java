package com.example.schooldiary.utils;

import androidx.room.TypeConverter;

import com.example.schooldiary.model.Subjects;

public class TypeConverterEnum {

    @TypeConverter
    public String fromEnumSubjects(Subjects subjects){
        return subjects.name();
    }

    @TypeConverter
    public Subjects toEnumSubjects(String subjects){
        if (subjects.equals("Science")){
            return Subjects.Science;
        }
        else if(subjects.equals("Literature")){
            return Subjects.Literature;
        }
        else{
            return Subjects.Another;
        }
    }
}
