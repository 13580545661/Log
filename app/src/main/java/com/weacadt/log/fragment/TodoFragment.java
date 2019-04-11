package com.weacadt.log.fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weacadt.log.R;
import com.weacadt.log.application.BaseApplication;
import com.weacadt.log.data.ItemTouchHelperCallback;
import com.weacadt.log.data.TodoItem;
import com.weacadt.log.adapter.TodoAdapter;
import com.weacadt.log.database.DaoSession;
import com.weacadt.log.database.TodoItemDao;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TodoFragment extends Fragment {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<TodoItem> testList = new ArrayList<>();

    private DaoSession daoSession;
    private TodoItemDao todoItemDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initDatabase();
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
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initDatabase() {
        daoSession = ((BaseApplication)(getActivity().getApplication())).getDaoSession();
        todoItemDao = daoSession.getTodoItemDao();

        Calendar cal = Calendar.getInstance();
        QueryBuilder<com.weacadt.log.data.TodoItem> qb = todoItemDao.queryBuilder();

        testList = qb.where(TodoItemDao.Properties.Year.eq(cal.get(Calendar.YEAR)),
                TodoItemDao.Properties.Month.eq(cal.get(Calendar.MONTH) + 1),
                TodoItemDao.Properties.DayOfMonth.eq(cal.get(Calendar.DAY_OF_MONTH)))
                .orderAsc(TodoItemDao.Properties.Order)
                .list();

    }
    private void initData() {
        recyclerView = getActivity().findViewById(R.id.recycler_view_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodoAdapter(testList, todoItemDao);
        recyclerView.setAdapter(adapter);

        //ItemTouchHelper
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    public void addItem(TodoItem todoItem) {
        adapter.addItem(todoItem);
    }
}
