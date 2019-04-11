package com.weacadt.log.adapter;


import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.weacadt.log.R;
import com.weacadt.log.data.TodoItem;
import com.weacadt.log.database.DaoSession;
import com.weacadt.log.database.TodoItemDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<TodoItem> myTestList;
    private TodoItemDao todoItemDao;


    public TodoAdapter(List<TodoItem> myTestList, TodoItemDao todoItemDao) {
        this.myTestList = myTestList;
        this.todoItemDao = todoItemDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.context_recycler_view_todo, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TodoItem test = myTestList.get(i);
        final TextView textView = viewHolder.textView;
        final CheckBox checkBox = viewHolder.checkBox;

        textView.setText(test.getTodoThing());
        checkBox.setChecked(test.isDone());

        if (test.isDone()) {
            viewHolder.textView.setPaintFlags(viewHolder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            viewHolder.textView.setPaintFlags(viewHolder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.getPaint().setAntiAlias(true);
                    test.setDone(true);

                }else {
                    textView.setPaintFlags(textView.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.getPaint() .setAntiAlias(true);
                    test.setDone(false);
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTestList.size();
    }

    //ItemTouchHelperAdapter 的接口实现
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        myTestList.get(fromPosition).setOrder((long)toPosition);
        todoItemDao.update(myTestList.get(fromPosition));

        myTestList.get(toPosition).setOrder((long)fromPosition);
        todoItemDao.update(myTestList.get(toPosition));

        myTestList.add(toPosition, myTestList.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        todoItemDao.delete(myTestList.get(position));
        myTestList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.item_checkbox);
            textView = itemView.findViewById(R.id.item_textview);
        }
    }
    
    public void addItem(TodoItem test) {
        myTestList.add(test);
        todoItemDao.insert(test);
        notifyItemInserted(myTestList.size());
    }

    public void setItem(int position, TodoItem test) {
        myTestList.set(position, test);
        notifyDataSetChanged();
    }
}
