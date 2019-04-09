package com.weacadt.log.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.weacadt.log.R;
import com.weacadt.log.data.TodoItem;
import com.weacadt.log.database.DaoSession;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<TodoItem> myTestList;
    private DaoSession daoSession;

    

    public TodoAdapter(List<TodoItem> myTestList, DaoSession daoSession) {
        this.myTestList = myTestList;
        this.daoSession = daoSession;
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
        TodoItem test = myTestList.get(i);
        viewHolder.textView.setText(test.getTodoThing());
        viewHolder.checkBox.setChecked(test.isDone());

    }

    @Override
    public int getItemCount() {
        return myTestList.size();
    }

    //ItemTouchHelperAdapter 的接口实现
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        myTestList.add(toPosition, myTestList.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {

        daoSession.getTodoItemDao().delete(myTestList.get(position));
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
        notifyItemInserted(myTestList.size());
    }
    
    public void addItem(int position, TodoItem test) {
        myTestList.add(position, test);
        notifyItemInserted(position);
    }
    
    public void setItem(int position, TodoItem test) {
        myTestList.set(position, test);
        notifyDataSetChanged();
    }
}
