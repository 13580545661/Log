package com.weacadt.log.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.weacadt.log.activity.DiaryAddActivity;
import com.weacadt.log.R;

public class DiaryFragment extends Fragment {

    public ListView list;

    public FloatingActionButton button1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, null);


        button1 = view.findViewById(R.id.floatingActionButton);//找到当前Fragment的Button按钮
        button1.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaryAddActivity.class); //在这里可以使用getActivity得到当前Activity
                startActivity(intent);

            }
        });

        return view;
    }
}

