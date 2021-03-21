package com.example.schooldiary.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.schooldiary.R;
import com.example.schooldiary.model.SubjectItem;
import com.example.schooldiary.model.Subjects;
import com.example.schooldiary.utils.DBSingleton;
import com.example.schooldiary.viewmodel.SubjectViewModel;

public class UpdateSubjectFragment extends AddSubjectFragment {
    private int idSubject;

    public static UpdateSubjectFragment newInstance() {
        Bundle args = new Bundle();
        UpdateSubjectFragment fragment = new UpdateSubjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void writeSubjectItem(SubjectItem subject) {
        subject.setId(idSubject);
        DBSingleton.getInstance(getActivity()).getSubjectsDao().updateSubject(subject);
    }

    @Override
    protected void setSubjectItemFromLiveData() {
        SubjectViewModel viewModel = ViewModelProviders.of(getActivity()).get(SubjectViewModel.class);
        viewModel.getData().observe(this, this::setSubjectItemDataToAllView);
    }

    private void setSubjectItemDataToAllView(SubjectItem subjectItem) {
        idSubject = subjectItem.getId();
        binding.nameEditText.setText(subjectItem.getName());
      //  binding.cabEditText.setText(subjectItem.getCab());
        binding.teacherEditText.setText(subjectItem.getTeacher());
        if (subjectItem.getType() == Subjects.Science) {
            binding.scienceCheck.setChecked(true);
        }
        else if(subjectItem.getType() == Subjects.Literature){
            binding.literatureCheck.setChecked(true);
        }
        else {
            binding.anotherCheck.setChecked(true);
        }
        binding.addSubjectButton.setText(R.string.update_subject);
    }
}
