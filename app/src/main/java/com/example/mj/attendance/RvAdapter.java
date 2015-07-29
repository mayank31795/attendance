package com.example.mj.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by MJ on 28-Jul-15.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    List<Information> data= Collections.emptyList();
    private LayoutInflater inflator;
    private Context context;
    public RvAdapter(Context context, List<Information> data){
        this.context=context;
        inflator=LayoutInflater.from(context);
        this.data=data;
    }

    public void delete(int postion){
        data.remove(postion);
        notifyItemRemoved(postion);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflator.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText2);
            icon = (ImageView) itemView.findViewById(R.id.listIcon2);
        }

        @Override
        public void onClick(View v) {
                context.startActivity(new Intent(context,MainActivity.class));

        }
    }

}
