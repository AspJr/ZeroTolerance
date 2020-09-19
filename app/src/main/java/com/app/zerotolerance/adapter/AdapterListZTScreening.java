package com.app.zerotolerance.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zerotolerance.FrontlineEditActivity;
import com.app.zerotolerance.R;
import com.app.zerotolerance.model.ClsListDetailZTScreening;
import com.app.zerotolerance.model.ClsListZTScreening;

import java.util.List;

public class AdapterListZTScreening extends RecyclerView.Adapter<AdapterListZTScreening.ViewHolder>{

    private List<ClsListDetailZTScreening> clsListDetailZTScreenings;
    private Context context;

    //List<ClsData> clsData;

    public AdapterListZTScreening(List<ClsListDetailZTScreening> clsListDetailZTScreenings, Context context) {
        this.clsListDetailZTScreenings = clsListDetailZTScreenings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_zt_screening, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txt_name.setText(clsListDetailZTScreenings.get(position).getNama());
        holder.txt_phone.setText(clsListDetailZTScreenings.get(position).getNo_tlpn());
        holder.txt_id.setText(Integer.toString( clsListDetailZTScreenings.get(position).getId_zt()));
        holder.txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FrontlineEditActivity.class);
                intent.putExtra("id_zt", Integer.toString(clsListDetailZTScreenings.get(position).getId_zt()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clsListDetailZTScreenings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context mcontext = itemView.getContext();
        TextView txt_name, txt_phone, txt_id, txt_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_phone = itemView.findViewById(R.id.txt_phone);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_edit = itemView.findViewById(R.id.txt_edit);
        }
    }
}
