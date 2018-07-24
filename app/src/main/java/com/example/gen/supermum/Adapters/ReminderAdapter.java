package com.example.gen.supermum.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gen.supermum.Pojo.Reminder;
import com.example.gen.supermum.R;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.myViewHolder> {
    private List<Reminder> reminderList;

    public ReminderAdapter(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_list_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.mTitle.setText(reminder.getReminderTitle());
        holder.mDesc.setText(reminder.getDescription());
        holder.mDate.setText(reminder.getDate());

    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle, mDesc, mDate;

        public myViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDesc = itemView.findViewById(R.id.tv_desc);
            mDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
