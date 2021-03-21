package com.example.schooldiary.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentEditDiaryBinding;

public class EditDiaryFragment extends Fragment {

    private static final String SUBJECT_NAME_KEY="subject_name_key";
    private static final String DATE_KEY="date_key";

    private FragmentEditDiaryBinding binding;
    private String nameSubject;
    private String date;

    public static EditDiaryFragment newInstance(String nameSubject,String date) {
        Bundle args = new Bundle();
        args.putString(SUBJECT_NAME_KEY,nameSubject);
        args.putString(DATE_KEY,date);
        EditDiaryFragment fragment = new EditDiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_diary,container,false);
        nameSubject = getArguments().getString(SUBJECT_NAME_KEY);
        date = getArguments().getString(DATE_KEY);
        setViewPagerAdapter();

        return binding.getRoot();
    }


    private void setViewPagerAdapter() {
        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position<2) {
                    return NotesFragment.newInstance(position, nameSubject, date);
                }
                else{
                    return MarksFragment.newInstance(nameSubject,date);
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0){
                    return getActivity().getString(R.string.homework);
                }
                else if(position == 1){
                    return getActivity().getString(R.string.notes);
                }
                else if(position == 2){
                    return getActivity().getString(R.string.rating);
                }
                return super.getPageTitle(position);
            }

        });
    }

}
