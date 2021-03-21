package com.example.schooldiary.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentAboutBinding;

public class AboutCompanyFragment  extends Fragment {

    FragmentAboutBinding binding;

    public static AboutCompanyFragment newInstance() {

        Bundle args = new Bundle();

        AboutCompanyFragment fragment = new AboutCompanyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_about,container,false);
        //TODO Текст для компании
        //binding.aboutTextView.setText();
        return binding.getRoot();
    }
}
