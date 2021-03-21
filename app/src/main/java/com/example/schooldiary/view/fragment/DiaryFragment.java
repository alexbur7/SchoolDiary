package com.example.schooldiary.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.schooldiary.model.DayAndTableItems;
import com.example.schooldiary.model.DayItem;
import com.example.schooldiary.utils.DBSingleton;
import com.example.schooldiary.utils.DateManager;
import com.example.schooldiary.utils.RecViewAdapter;
import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentDiaryBinding;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class DiaryFragment extends Fragment {


    private FragmentDiaryBinding diaryBinding;
    private RecViewAdapter<DayAndTableItems> adapter;
    private DateManager dateManager;
    private boolean isCalendarOpened=false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateManager=new DateManager(getContext());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        diaryBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_diary,container,false);
        setupRecyclerView();
        diaryBinding.datePicker.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar=new GregorianCalendar(year,month,dayOfMonth);
            Flowable<DayAndTableItems> dayItemFlowable=dateManager.setupTwoWeeksFromCurrentCalendar(calendar);
            addAdapterChangingObserver(dayItemFlowable);
        });

        return diaryBinding.getRoot();
    }


    private void addAdapterChangingObserver(Flowable<DayAndTableItems> itemsFlowable){
        itemsFlowable.observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSubscriber<DayAndTableItems>() {

            @Override
            protected void onStart() {
                super.onStart();
                adapter.getDataList().clear();
            }

            @Override
            public void onNext(DayAndTableItems dayTableItem) {
                Log.d("tut","Лог онНекста: "+dayTableItem.getDayItem().getDateTitle());
                adapter.addDataToList(dayTableItem);
                adapter.notifyItemChanged(adapter.getItemCount()-1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.diary_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.calendar:{
                if (!isCalendarOpened) {
                    isCalendarOpened=true;
                    diaryBinding.motionContainer.transitionToEnd();
                }
                else{
                    isCalendarOpened=false;
                    diaryBinding.motionContainer.transitionToStart();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static DiaryFragment newInstance() {
        Bundle args = new Bundle();
        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setupRecyclerView(){
        diaryBinding.diaryRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        diaryBinding.diaryRecView.setItemViewCacheSize(14);
        Flowable<DayAndTableItems> dayItemFlowable= dateManager.setupTwoWeeksFromToday();
        adapter=new RecViewAdapter<>(RecViewAdapter.ViewType.DayHolder);
        diaryBinding.diaryRecView.setAdapter(adapter);
        addAdapterChangingObserver(dayItemFlowable);
    }
}