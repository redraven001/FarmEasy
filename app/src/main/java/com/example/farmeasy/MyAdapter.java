package com.example.farmeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Item> item;

    public MyAdapter(Context context, List<Item> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         holder.cname.setText(item.get(position).getName());
        holder.pest.setText(item.get(position).getPest());
        holder.fert.setText(item.get(position).getFert());
        holder.irri.setText(item.get(position).getIrri());
        holder.imgv.setImageResource(item.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
