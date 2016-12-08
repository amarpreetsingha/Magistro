package com.example.honey.magistro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.honey.magistro.DashBoard.DashBoardAdapter;
import com.example.honey.magistro.DashBoard.DashBoardData;
import com.example.honey.magistro.StudentList.StudentAdapter;
import com.example.honey.magistro.StudentList.StudentListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honey on 6/12/16.
 */

public class Student_List extends Activity{

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    public static String  c_id, i_id;
    Spinner spinner;
    String batches[];
    public static String batch_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentlist);

        recyclerView= (RecyclerView)findViewById(R.id.student_list);
        spinner = (Spinner) findViewById(R.id.spinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        c_id = getIntent().getStringExtra("c_id");
        i_id = getIntent().getStringExtra("i_id");

        try {
            spinner.setVisibility(View.VISIBLE);
            batches = new Database().getBatches(Dashboard.sqLiteDatabase, Integer.valueOf(c_id));

            ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, batches);
            gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(gameKindArray);
        }
        catch (Exception e) {
            spinner.setVisibility(View.INVISIBLE);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                recyclerView.setVisibility(View.VISIBLE);
                batch_name = adapterView.getItemAtPosition(i).toString();
                try {
                    studentAdapter = new StudentAdapter(defineData(), getApplicationContext());
                    recyclerView.setAdapter(studentAdapter);
                }catch (Exception e) {
                    recyclerView.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public List<StudentListData> defineData() {
        List<StudentListData> data = new ArrayList<>();
        String st[][] = new Database().getStudentsBatchWise(Dashboard.sqLiteDatabase, Integer.valueOf(c_id), batch_name);
        for (int i = 0; i < st.length; i++) {
            StudentListData item = new StudentListData();
            item.setId(Integer.valueOf(st[i][0]));
            item.setName(st[i][1]);
            data.add(item);
        }
        return data;
    }
}
