package com.example.honey.magistro.DashBoard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honey.magistro.Dashboard;
import com.example.honey.magistro.R;
import com.example.honey.magistro.Student_List;

import java.util.List;

/**
 * Created by honey on 5/12/16.
 */

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.AdapterHolder> {

    Context context;
    List<DashBoardData> datalist ;
    LayoutInflater layoutInflater;

    public DashBoardAdapter(List<DashBoardData> data, Context context) {
        this.layoutInflater = layoutInflater.from(context);
        this.datalist = data;
        this.context = context;

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.dashboardadapter, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        DashBoardData data = datalist.get(position);
        holder.textView1.setText(data.getInstitute());
        holder.textView2.setText(data.getCourse());
        ;
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView textView1, textView2;
        Dashboard dashboard = new Dashboard();

        public AdapterHolder(View itemView) {

            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView2.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();
            Intent i = new Intent(context, Student_List.class);
            i.putExtra("c_id", Dashboard.inscourse[pos][2]);
            i.putExtra("i_id", Dashboard.inscourse[pos][3]);
            context.startActivity(i);
        }


        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
