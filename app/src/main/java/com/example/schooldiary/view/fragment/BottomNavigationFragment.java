package com.example.schooldiary.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentMainBinding;
import com.example.schooldiary.view.Callback;

public class BottomNavigationFragment extends Fragment {


    public static BottomNavigationFragment newInstance() {
        Bundle args = new Bundle();
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentMainBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("tut","onCreateView");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        setupNavigationMenu();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (binding.bottomNavigation.getSelectedItemId()){
            case R.id.page_subjects:{
                replaceFragment(SubjectsFragment.newInstance());
            }
            break;
            case R.id.page_about_company:{
                replaceFragment(AboutCompanyFragment.newInstance());
            }
            break;
            case R.id.page_diary:{
                replaceFragment(DiaryFragment.newInstance());
            }
            break;
        }
    }

    private void setupNavigationMenu() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.page_subjects:{
                    getActivity().setTitle(R.string.subjects_page_title);
                    replaceFragment(SubjectsFragment.newInstance());
                }
                break;
                case R.id.page_about_company:{
                    getActivity().setTitle(R.string.about_company_page_title);
                    replaceFragment(AboutCompanyFragment.newInstance());
                }
                break;
                case R.id.page_diary:{
                    getActivity().setTitle(R.string.diary_page_title);
                    replaceFragment(DiaryFragment.newInstance());
                }
                break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left, R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                .replace(R.id.container,fragment).commit();
    }
}
