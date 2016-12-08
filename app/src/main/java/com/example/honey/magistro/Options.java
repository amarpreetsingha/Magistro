package com.example.honey.magistro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by honey on 7/12/16.
 */

public class Options extends Fragment {

    ListView listView;
    String selectedItem = "";
    public Options() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options, container, false);
        listView = (ListView)view.findViewById(R.id.listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = String.valueOf(adapterView.getItemAtPosition(i));

                if(selectedItem.contentEquals("Add Staff"))
                    goTo(new Intent(getActivity(), AddStaff.class));
                if(selectedItem.contentEquals("Add Institute"))
                    goTo(new Intent(getActivity(), AddInstitute.class));
                if(selectedItem.contentEquals("Add Course"))
                    goTo(new Intent(getActivity(), AddCourse.class));
                if(selectedItem.contentEquals("Add Batches"))
                    goTo(new Intent(getActivity(), AddBatches.class));

            }
        });

        return view;
    }

    public void goTo(Intent i) {
        startActivity(i);
    }
}