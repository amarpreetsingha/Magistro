package com.example.honey.magistro;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.honey.magistro.DashBoard.DashBoardAdapter;
import com.example.honey.magistro.DashBoard.DashBoardData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    private RecyclerView recview;
    private DashBoardAdapter adapter;
    GridLayoutManager gridLayoutManager;
    Database database;
    static SQLiteDatabase sqLiteDatabase;
    public static String inscourse[][];


    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);
        recview = (RecyclerView)view.findViewById(R.id.rec_list);

        database = new Database();


        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        recview.setLayoutManager(gridLayoutManager);

        try{
            sqLiteDatabase = getActivity().openOrCreateDatabase("magistro", Context.MODE_PRIVATE, null);
            database.createTables(sqLiteDatabase);
            inscourse = new Database().getInstituteCourse(Dashboard.sqLiteDatabase);


        }catch (Exception e) {
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_LONG).show();
        }

        adapter = new DashBoardAdapter(defineData(), getActivity());
        recview.setAdapter(adapter);
        return view;
    }


    public List<DashBoardData> defineData() {
        List<DashBoardData> data = new ArrayList<>();
        if(inscourse != null) {
            for (int i = 0; i < inscourse.length; i++) {
                DashBoardData item = new DashBoardData();
                item.setCourse(inscourse[i][1]);
                item.setInstitute(inscourse[i][0]);
                data.add(item);
            }
        }
        return data;
    }

}
