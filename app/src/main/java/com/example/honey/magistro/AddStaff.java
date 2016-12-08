package com.example.honey.magistro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by honey on 7/12/16.
 */

public class AddStaff extends Activity {
    EditText name, address, qual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstaff);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.add);
        qual = (EditText) findViewById(R.id.qual);
    }

    public void addStaff(View view) {
        try {
            new Database().addStaff(Dashboard.sqLiteDatabase, name.getText().toString(), address.getText().toString(), qual.getText().toString());
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
}
