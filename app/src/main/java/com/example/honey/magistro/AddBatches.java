package com.example.honey.magistro;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by honey on 7/12/16.
 */

public class AddBatches extends Activity {
    Spinner institute, course;
    EditText name, timing, capacity, duration;
    String inst[][], ins[], inswisecourse[][];
    int  c_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbatches);

        name = (EditText) findViewById(R.id.name);
        timing = (EditText) findViewById(R.id.timing);
        capacity = (EditText) findViewById(R.id.capacity);
        duration = (EditText) findViewById(R.id.duration);
        institute = (Spinner) findViewById(R.id.institute);
        course = (Spinner) findViewById(R.id.course);

        try {
            inst = new Database().getInstitute(Dashboard.sqLiteDatabase);

            ins = getNames(inst);

            ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ins);
            gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            institute.setAdapter(gameKindArray);
        }
        catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        institute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int i_id = Integer.valueOf(inst[i][0]);

                try {
                    inswisecourse = new Database().getInstituteWiseCourse(Dashboard.sqLiteDatabase, i_id);

                    ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, getNames(inswisecourse));
                    gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course.setAdapter(gameKindArray);
                }catch (Exception e) {
                    Toast.makeText(AddBatches.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_id = Integer.valueOf(inswisecourse[i][0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void addBatch(View view) {
        try {
            new Database().addBatches(Dashboard.sqLiteDatabase, name.getText().toString(), timing.getText().toString(), capacity.getText().toString(), duration.getText().toString(), c_id);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    public String [] getNames(String arr[][]) {
        String st[] = new String[arr.length];

        for(int i = 0; i < st.length; i++) {
            st[i] = arr[i][1];
        }
        return st;
    }
}
