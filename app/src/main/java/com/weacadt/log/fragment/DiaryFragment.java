package com.weacadt.log.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weacadt.log.R;
import com.weacadt.log.adapter.DiaryAdapter;
import com.weacadt.log.application.BaseApplication;
import com.weacadt.log.data.DiaryItem;
import com.weacadt.log.database.DaoSession;
import com.weacadt.log.database.DiaryItemDao;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {

    private DaoSession daoSession;
    private DiaryItemDao diaryItemDao;

    private RecyclerView recyclerView;
    private DiaryAdapter diaryAdapter;
    private List<DiaryItem> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initDatabase() {
        daoSession = ((BaseApplication)(getActivity().getApplication())).getDaoSession();
        diaryItemDao = daoSession.getDiaryItemDao();
        list = diaryItemDao.queryBuilder().orderDesc(DiaryItemDao.Properties.Id).list();
    }

    public void initData() {
        recyclerView = getActivity().findViewById(R.id.recycler_view_diary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        diaryAdapter = new DiaryAdapter(list, diaryItemDao, getActivity());
        recyclerView.setAdapter(diaryAdapter);

    }

    public void addItem(DiaryItem diaryItem) {
        diaryAdapter.addItem(diaryItem);
    }
    public void updateData() {
        diaryAdapter.notifyDataSetChanged();
    }
}

