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

public class DosFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dos_fragment,container,false);

        // get the listview
        expListView =  view.findViewById(R.id.lvExp);
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
        listDataHeader.add("Stay positive");
        listDataHeader.add("Folic acid");
        listDataHeader.add("Eat well");
        listDataHeader.add("Exercises");


        // Adding child data
        List<String> staypositive  = new ArrayList<>();
        staypositive.add("Pregrnancies are not always joyful. surround yourself in a positive  " +
                "environment. It can help you relax and enjoy your pregnancy better, " +
                "flooding your baby with pleasant emotions");

        List<String> top250 = new ArrayList<String>();
        top250.add("Folic acids have one acid tablets daily when you are pregnant");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Eat well have a home cooked meal, avoid junk. Watch your weight every month you have to put " +
                "on atleast one kilogram");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Exercises you have to walk at least 15 to 20 minutes a day. Aerobic classes are also a good option." +
                "Dress up well choose loose fitting dresses and comfortable footware");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3),staypositive);
    }
}
