package com.example.schooldiary.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class DayItem {

    @PrimaryKey
    private int id;
    private int day;
    @Ignore
    private String dateTitle;
    @Ignore
    private String dbDate;
    private boolean isEven;

    public DayItem(int id,int day,boolean isEven){
        this.day=day;
        this.isEven=isEven;
        this.id=id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public String getDbDate() {
        return dbDate;
    }

    public void setDbDate(String dbDate) {
        this.dbDate = dbDate;
    }

}
