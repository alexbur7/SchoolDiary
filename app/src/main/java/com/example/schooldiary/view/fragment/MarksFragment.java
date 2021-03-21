package com.example.schooldiary.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentMarksBinding;
import com.example.schooldiary.model.NotesTable;
import com.example.schooldiary.utils.DBSingleton;
import com.example.schooldiary.utils.RecViewAdapter;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class MarksFragment extends Fragment {
    private static final String SUBJECT_NAME_KEY="subject_name_key";
    private static final String DATE_KEY="date_key";

    private FragmentMarksBinding binding;
    private String nameSubject;
    private String date;
    private RecViewAdapter<String> adapter;
    private NotesTable notesTable;

    public static MarksFragment newInstance(String nameSubject, String date) {
        Bundle args = new Bundle();
        args.putString(SUBJECT_NAME_KEY,nameSubject);
        args.putString(DATE_KEY, date);
        MarksFragment fragment = new MarksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_marks,container,false);
        nameSubject = getArguments().getString(SUBJECT_NAME_KEY);
        date = getArguments().getString(DATE_KEY);
        setRecyclerView();
        binding.saveMarks.setOnClickListener(v -> saveMarksInBd());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getNotesTable();
    }

    private void setRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        adapter = new RecViewAdapter<>(RecViewAdapter.ViewType.MarkHolder);
        binding.recyclerMarks.setLayoutManager(manager);
        binding.recyclerMarks.setAdapter(adapter);
        addMarksToAdapter(binding.oneButton);
        addMarksToAdapter(binding.twoButton);
        addMarksToAdapter(binding.threeButton);
        addMarksToAdapter(binding.fourButton);
        addMarksToAdapter(binding.fiveButton);
    }

    private void addMarksToAdapter(Button button){
        button.setOnClickListener(v->{ adapter.addDataToList(button.getText().toString());
        adapter.notifyItemChanged(adapter.getItemCount()-1);});
    }

    private void saveMarksInBd(){
        if (!adapter.getDataList().isEmpty()) {
            String markToBd = "";
            for (String mark : adapter.getDataList()) {
                markToBd += mark;
                markToBd += ",";
            }
            markToBd = markToBd.substring(0,markToBd.length()-1);
            if (notesTable == null) {
                notesTable = new NotesTable(date, nameSubject);
            }
            notesTable.setMarks(markToBd);
            Observable.create((ObservableOnSubscribe<String>) emitter -> {
                DBSingleton.getInstance(getActivity()).getNotesDao().addNotesTable(notesTable);
            }).subscribeOn(Schedulers.io()).subscribe();
            Toast.makeText(getActivity(),getActivity().getString(R.string.save),Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("CheckResult")
    private void getNotesTable(){
        DBSingleton.getInstance(getActivity()).getNotesDao().getNotesTable(date,nameSubject).subscribeOn(Schedulers.io())
                .subscribe(it->{
                    notesTable = it;
                    String[] marksArray = notesTable.getMarks().split(",");
                    for (String str:marksArray){
                        adapter.addDataToList(str);
                    }
                    getActivity().runOnUiThread(()->adapter.notifyDataSetChanged());
                });

    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.clearList();
    }
}
