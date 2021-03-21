package com.example.schooldiary.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentSubjectsBinding;
import com.example.schooldiary.model.SubjectItem;
import com.example.schooldiary.utils.DBSingleton;
import com.example.schooldiary.utils.RecViewAdapter;
import com.example.schooldiary.view.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class SubjectsFragment extends Fragment {
    private FragmentSubjectsBinding binding;
    private Callback callback;
    private RecViewAdapter<SubjectItem> subjectsAdapter;


    public static SubjectsFragment newInstance() {
        Bundle args = new Bundle();
        SubjectsFragment fragment = new SubjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subjects,container,false);
        subjectsAdapter = new RecViewAdapter<>(RecViewAdapter.ViewType.SubjectHolder);
        binding.subjectsRecycler.setAdapter(subjectsAdapter);
        binding.subjectsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        fillAdapter();
    }

    @Override
    public void onPause() {
        super.onPause();
        subjectsAdapter.clearList();
    }

    private void fillAdapter(){

        DBSingleton.getInstance(getActivity()).getSubjectsDao().getSubjects().subscribeOn(Schedulers.io()).subscribe(
                new DisposableMaybeObserver<List<SubjectItem>>() {
                    @Override
                    public void onSuccess(@NonNull List<SubjectItem> subjectItems) {
                        for (SubjectItem subjectItem:subjectItems){
                            subjectsAdapter.addDataToList(subjectItem);
                            getActivity().runOnUiThread(() -> subjectsAdapter.notifyItemChanged(subjectsAdapter.getItemCount()));
                        }
                       // getActivity().runOnUiThread(() -> subjectsAdapter.notifyDataSetChanged());

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_subject_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.plus_subject:{
                addSubject();
            }break;
        }
        return true;
    }

    private void addSubject() {
        callback.replaceFragmentWithBackStack(AddSubjectFragment.newInstance());
    }

}
