package com.example.schooldiary.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DiaryViewModel extends BaseObservable {


    private String textNote;
    private String date;
    private String hintEditText;
    private String note;

    @Bindable
    public String getTextNote() {
        return textNote;
    }

    @Bindable
    public String getDate() {
        return date;
    }


    @Bindable
    public String getHintEditText() {
        return hintEditText;
    }

    @Bindable
    public String getNote() {
        return note;
    }
    public void setHintEditText(String hintEditText) {
        this.hintEditText = hintEditText;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
