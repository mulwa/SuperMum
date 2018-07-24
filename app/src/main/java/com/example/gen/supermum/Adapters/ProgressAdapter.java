package com.example.gen.supermum.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gen.supermum.Pojo.Progress;
import com.example.gen.supermum.R;

import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.myViewHolder> {
    private List<Progress> progressList;

    public ProgressAdapter(List<Progress> progressList) {
        this.progressList = progressList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.week_by_week_list_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Progress progress = progressList.get(position);
        holder.mWeekNo.setText(progress.getWeekNo());
        holder.mProgressTitle.setText(progress.getProgressTitle());
        holder.mProgressDesc.setText(progress.getProgressDesc());

    }

    @Override
    public int getItemCount() {
        return progressList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        public TextView mWeekNo, mProgressTitle, mProgressDesc;
        public ImageView progressImage;

        public myViewHolder(View itemView) {
            super(itemView);
            mWeekNo = itemView.findViewById(R.id.tv_week_no);
            mProgressTitle = itemView.findViewById(R.id.tv_progress_title);
            mProgressDesc = itemView.findViewById(R.id.tv_progress_desc);
            progressImage = itemView.findViewById(R.id.progress_image);
        }
    }
}
