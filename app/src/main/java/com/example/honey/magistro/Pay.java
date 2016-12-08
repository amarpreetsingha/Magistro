package com.example.honey.magistro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.honey.magistro.StudentList.StudentAdapter;
import com.example.honey.magistro.StudentList.StudentListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honey on 6/12/16.
 */

public class Pay extends Fragment {


    RecyclerView recyclerView;
    StudentAdapter studentAdapter;

    public Pay() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.studentpay);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        studentAdapter = new StudentAdapter(defineData(), getActivity());
        recyclerView.setAdapter(studentAdapter);
        return view;
    }

    public List<StudentListData> defineData() {
        final String name[] = {"Student1", "Student2", "Student3", "Student4"};
        final int id[] = {1, 2, 3, 4};
        List<StudentListData> data = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            StudentListData item = new StudentListData();
            item.setId(id[i]);
            item.setName(name[i]);
            data.add(item);
        }
        return data;
    }
}