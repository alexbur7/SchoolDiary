package com.example.schooldiary.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schooldiary.model.SubjectItem;

public class SubjectViewModel extends ViewModel {

    private MutableLiveData<SubjectItem> liveData = new MutableLiveData<>();

    public void setLiveData(SubjectItem subjectItem){
        liveData.setValue(subjectItem);
    }

    public LiveData<SubjectItem> getData(){
        return liveData;
    }
}
