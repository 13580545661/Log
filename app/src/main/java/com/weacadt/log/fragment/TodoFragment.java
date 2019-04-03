package com.weacadt.log.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weacadt.log.R;
import com.weacadt.log.adapter.Test;
import com.weacadt.log.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TodoFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Test> testList = new ArrayList<Test>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initTestData();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private void initTestData() {
        testList.add(new Test("打扫卫生"));
        testList.add(new Test("背诵单词"));
        testList.add(new Test("看动漫"));
        testList.add(new Test("洗衣服"));
        testList.add(new Test("做作业"));
    }

    private void initData() {
        recyclerView = getActivity().findViewById(R.id.recycler_view_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TestAdapter adapter = new TestAdapter(testList);
        recyclerView.setAdapter(adapter);
    }
}
