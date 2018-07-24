package com.example.gen.supermum.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gen.supermum.Pojo.Appointment;
import com.example.gen.supermum.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.myViewHolder> {
    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_list_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.mWith.setText(appointment.getAppointmentWith());
        holder.mPurpose.setText(appointment.getPurpose());
        holder.mDate.setText(appointment.getDate());
        holder.mTime.setText(appointment.getTime());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        public TextView mWith, mPurpose,mDate,mTime;

        public myViewHolder(View itemView) {
            super(itemView);
            mWith = itemView.findViewById(R.id.tv_with);
            mPurpose = itemView.findViewById(R.id.tv_purpose);
            mDate = itemView.findViewById(R.id.tv_date);
            mTime = itemView.findViewById(R.id.tv_time);


        }
    }
}
