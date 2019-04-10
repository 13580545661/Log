package com.weacadt.log.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weacadt.log.R;
import com.weacadt.log.data.DiaryItem;

import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<DiaryItem> list;
    private int editPosition = -1;

    public DiaryAdapter(List<DiaryItem> list) {
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_diary, parent, false);
        DiaryAdapter.ViewHolder holder = new DiaryAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Calendar cal = Calendar.getInstance();

        if(list.get(position).getYear()==cal.get(Calendar.YEAR) && list.get(position).getMonth() == (cal.get(Calendar.MONTH) + 1) && list.get(position).getDayOfMonth() == cal.get(Calendar.DAY_OF_MONTH)){
            holder.mIvCircle.setImageResource(R.drawable.circle_orange);
        }
        StringBuilder date = new StringBuilder();
        date.append(list.get(position).getYear());
        date.append("年");
        date.append(list.get(position).getMonth());
        date.append("月");
        date.append(list.get(position).getDayOfMonth());
        date.append("日");

        holder.mTvDate.setText(date.toString());
        holder.mTvTitle.setText(list.get(position).getTitle());
        holder.mTvContent.setText("       " + list.get(position).getContent());
        holder.mIvEdit.setVisibility(View.INVISIBLE);
        if(editPosition == position){
            holder.mIvEdit.setVisibility(View.VISIBLE);
        }else {
            holder.mIvEdit.setVisibility(View.GONE);
        }
        holder.mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mIvEdit.getVisibility() == View.VISIBLE){
                    holder.mIvEdit.setVisibility(View.GONE);
                }else {
                    holder.mIvEdit.setVisibility(View.VISIBLE);
                }
                if(editPosition != position){
                    notifyItemChanged(editPosition);
                }
                editPosition = position;
            }
        });

//        holder.mIvEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new StartUpdateDiaryEvent(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTvDate;
        TextView mTvTitle;
        TextView mTvContent;
        ImageView mIvEdit;
        LinearLayout mLlTitle;
        LinearLayout mLl;
        ImageView mIvCircle;
        LinearLayout mLlControl;
        RelativeLayout mRlEdit;

        ViewHolder(View view){
            super(view);
            mIvCircle = (ImageView) view.findViewById(R.id.main_iv_circle);
            mTvDate = (TextView) view.findViewById(R.id.main_tv_date);
            mTvTitle = (TextView) view.findViewById(R.id.main_tv_title);
            mTvContent = (TextView) view.findViewById(R.id.main_tv_content);
            mIvEdit = (ImageView) view.findViewById(R.id.main_iv_edit);
            mLlTitle = (LinearLayout) view.findViewById(R.id.main_ll_title);
            mLl = (LinearLayout) view.findViewById(R.id.item_ll);
            mLlControl = (LinearLayout) view.findViewById(R.id.item_ll_control);
            mRlEdit = (RelativeLayout) view.findViewById(R.id.item_rl_edit);
        }
    }

    public void addItem(DiaryItem diaryItem) {
        list.add(0, diaryItem);
        notifyItemInserted(0);
    }
}
