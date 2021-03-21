package com.example.schooldiary.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schooldiary.model.TableItem;

public class TableItemViewModel extends ViewModel {
    private MutableLiveData<TableItem> liveData=new MutableLiveData<>();

    public MutableLiveData<TableItem> getLiveData() {
        return liveData;
    }

    public void setLiveData(TableItem item) {
        liveData.setValue(item);
    }
}
