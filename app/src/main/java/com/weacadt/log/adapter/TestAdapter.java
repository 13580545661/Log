package com.weacadt.log.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.weacadt.log.R;
import com.weacadt.log.data.Test;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<Test> myTestList;
    

    public TestAdapter(List<Test> myTestList) {
        this.myTestList = myTestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Test test = myTestList.get(i);
        viewHolder.textView.setText(test.getTodoThing());
        viewHolder.checkBox.setChecked(test.isDone());

    }

    @Override
    public int getItemCount() {
        return myTestList.size();
    }

    //ItemTouchHelperAdapter的接口实现
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        myTestList.add(toPosition, myTestList.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }
    @Override
    public void onItemDissmiss(int position) {
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
    
    public void addItem(Test test) {
        myTestList.add(test);
        notifyItemInserted(myTestList.size());
    }
    
    public void addItem(int position, Test test) {
        myTestList.add(position, test);
        notifyItemInserted(position);
    }
    
    public void setItem(int position, Test test) {
        myTestList.set(position, test);
        notifyDataSetChanged();
    }
}
