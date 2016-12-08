package com.example.honey.magistro;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentInfo extends Fragment {

    EditText name, address, phoneno, dob;
    Spinner institute, course, batch;
    int s_id;
    Button submit;
    String inst[][], ins[], inswisecourse[][], coursewisebatch[], batch_name;
    int  c_id;

    public AddStudentInfo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addstudentinfo,  container, false);
        name = (EditText) view.findViewById(R.id.name);
        address = (EditText) view.findViewById(R.id.address);
        phoneno = (EditText) view.findViewById(R.id.phoneno);
        dob = (EditText) view.findViewById(R.id.dateofbirth);
        submit = (Button)view.findViewById(R.id.submit);

        institute = (Spinner) view.findViewById(R.id.institute);
        course = (Spinner) view.findViewById(R.id.course);
        batch = (Spinner) view.findViewById(R.id.batch);

        try {
            s_id = new Database().getLastRowId(Dashboard.sqLiteDatabase, "student");
        }catch (Exception e) {
            s_id = 0;
        }

        try {
            inst = new Database().getInstitute(Dashboard.sqLiteDatabase);

            ins = getNames(inst);

            ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ins);
            gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            institute.setAdapter(gameKindArray);
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
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

                try {
                    batch.setVisibility(View.VISIBLE);
                    coursewisebatch = new Database().getBatches(Dashboard.sqLiteDatabase, c_id);

                    ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, coursewisebatch);
                    gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    batch.setAdapter(gameKindArray);
                }catch (Exception e) {
                    batch.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       batch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               batch_name = adapterView.getItemAtPosition(i).toString();

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    try {
                        s_id = new Database().getLastRowId(Dashboard.sqLiteDatabase, "student");
                    }catch (Exception e) {
                        s_id = 0;
                    }
                  new Database().addStudent(Dashboard.sqLiteDatabase, name.getText().toString(), address.getText().toString(), Long.valueOf(phoneno.getText().toString()), dob.getText().toString(), s_id + 1, batch_name, c_id);
                    Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    public String [] getNames(String arr[][]) {
        String st[] = new String[arr.length];

        for(int i = 0; i < st.length; i++) {
            st[i] = arr[i][1];
        }
        return st;
    }

}