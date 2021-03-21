package com.example.schooldiary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.StringReader;

@Entity(tableName = "tables")
public class TableItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "num")
    private int id;
    private int dayOfWeek;
    private boolean isWeekEven;

    private String cab;
    private String name;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isWeekEven() {
        return isWeekEven;
    }

    public void setWeekEven(boolean weekEven) {
        isWeekEven = weekEven;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdString(){
        return String.valueOf(id);
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }
}
