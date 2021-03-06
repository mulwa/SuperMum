package com.example.gen.supermum.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gen.supermum.Adapters.ProgressAdapter;
import com.example.gen.supermum.Pojo.Progress;
import com.example.gen.supermum.R;

import java.util.ArrayList;
import java.util.List;

public class Trimester1 extends Fragment {
    private RecyclerView recyclerView;
    private ProgressAdapter progressAdapter;
    private List<Progress> trimester1list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.trimester_1_fragment,container,false);
        recyclerView = view.findViewById(R.id.rv_trimester1);
        progressAdapter = new ProgressAdapter(trimester1list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(progressAdapter);
        LoadData();
        return view;
    }
    private void LoadData(){
        Progress progress = new Progress("Week 1","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);
         progress = new Progress("Week 2","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);
         progress = new Progress("Week 3","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);
         progress = new Progress("Week 4","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);
        progress = new Progress("Week 5","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);
         progress = new Progress("Week 6","Your  last Menstral period","Your  last Menstral period Your  last Menstral " +
                "period Your  last Menstral period,Your  last Menstral period");
        trimester1list.add(progress);

        progressAdapter.notifyDataSetChanged();

    }
}
