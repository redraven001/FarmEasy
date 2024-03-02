package com.example.farmeasy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imgv;
    TextView  cname,pest,fert,irri;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imgv=itemView.findViewById(R.id.imgview);
        cname=itemView.findViewById(R.id.cname);
        pest=itemView.findViewById(R.id.pest);
        fert=itemView.findViewById(R.id.fert);
        irri=itemView.findViewById(R.id.irri);
    }
}
