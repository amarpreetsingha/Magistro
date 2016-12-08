package com.example.honey.magistro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.honey.magistro.StudentList.StudentAdapter;

/**
 * Created by honey on 6/12/16.
 */

public class StudentInfo extends Activity {

    String info[]  = new String[5];
    String id, name, c_id, i_id, batch_name;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.studentinfo);

            /*id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            c_id = getIntent().getStringExtra("c_id");
            i_id = getIntent().getStringExtra("i_id");
            batch_name = getIntent().getStringExtra("batch_name");

            info = new Database().getStudentInfo(Dashboard.sqLiteDatabase, Integer.valueOf(id), Integer.valueOf(c_id), Integer.valueOf(i_id));

            Toast.makeText(this, info[0] + info[1] + info[2]+ info[3]+ info[4], Toast.LENGTH_SHORT).show();*/

        }
}
