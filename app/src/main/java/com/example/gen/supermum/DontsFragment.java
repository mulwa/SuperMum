package com.example.gen.supermum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DontsFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.donts_fragments, container, false);
        // get the listview
        expListView = view.findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        return view;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Avoid drinking and smoking");
        listDataHeader.add("Travel");
        listDataHeader.add("Watch out for stress");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Avoid drinking and smoking strictly during and before planning for pregnancy");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Avoid travelling during the first trimester; the second trimester is safe for travelling");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Try to avoid major upheavals like moving or changing jobs while you are pregnant. If stress creeps in to your life, minimize it " +
                "with these relaxations tips.");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
