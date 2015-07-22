package com.example.mj.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SelectSectionRV extends Activity {

    int[] icons = {R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow};
    String[] titles = {"  Section 1", "  Section 2", "  Section 3", "  Section 4","  Section 5"};
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_section_rv);
        recyclerView = (RecyclerView) findViewById(R.id.drawerlist);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new MyAdapter());


    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        class MyHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView textView;
            public MyHolder(View itemView) {
                super(itemView);
                textView= (TextView) itemView.findViewById(R.id.listText);
                imageView = (ImageView) itemView.findViewById(R.id.listIcon);
            }
        }
        @Override
           public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectsectionrow,parent,false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {

            holder.imageView.setImageResource(icons[position]);
            holder.textView.setText(titles[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
