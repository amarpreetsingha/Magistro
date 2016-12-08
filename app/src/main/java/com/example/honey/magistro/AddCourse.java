package com.example.honey.magistro;

import android.app.Activity;
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

public class AddCourse extends Activity {
    EditText name;
    Spinner spinner;
    String inst[][], ins[];
    int i_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse);

        name = (EditText) findViewById(R.id.name);
        spinner = (Spinner) findViewById(R.id.spinner);

        try {
            inst = new Database().getInstitute(Dashboard.sqLiteDatabase);

            ins = getNames(inst);

            ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ins);
            gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(gameKindArray);
        }
        catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                i_id = Integer.valueOf(inst[i][0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void addCourse(View view) {
        try {
            new Database().addCourse(Dashboard.sqLiteDatabase, name.getText().toString(), i_id);
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
