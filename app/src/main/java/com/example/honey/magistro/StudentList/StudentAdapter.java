package com.example.honey.magistro.StudentList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honey.magistro.R;
import com.example.honey.magistro.StudentInfo;
import com.example.honey.magistro.Student_List;

import java.util.List;

/**
 * Created by honey on 5/12/16.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.AdapterHolder> {

    Context context;
    List<StudentListData> datalist ;
    LayoutInflater layoutInflater;

    public StudentAdapter(List<StudentListData> data, Context context) {
        this.layoutInflater = layoutInflater.from(context);
        this.datalist = data;
        this.context = context;

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.studentlistadapter, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        StudentListData data = datalist.get(position);
        holder.textView1.setText(String.valueOf(data.getId()));
        holder.textView2.setText(data.getName());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView1, textView2;

        public AdapterHolder(View itemView) {

            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);

            textView2.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, StudentInfo.class);
            i.putExtra("id", textView1.getText().toString());
            i.putExtra("name", textView2.getText().toString());
            i.putExtra("c_id", Student_List.c_id);
            i.putExtra("i_id", Student_List.i_id);
            i.putExtra("batch_name", Student_List.batch_name);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
