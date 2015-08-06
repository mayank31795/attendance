package com.example.mj.attendance;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AK PC on 22-07-2015.
 */

public class FragmentB extends Fragment {

    private RecyclerView recyclerView;
    private FragmentB_adapter adapter;
    String message=null;
    public ArrayList<String> subjects = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout= inflater.inflate(R.layout.fragment_b, container, false);
        recyclerView= (RecyclerView) layout.findViewById(R.id.drawerlist);
        adapter=new FragmentB_adapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       Intent in = getActivity().getIntent();
        Bundle b = in.getExtras();
        String j =(String) b.get(SelectSectionRV.Key);
        message=j;
      //  Log.d("Hardik", "" + message);
        MyTask myTask = new MyTask();
        myTask.execute();
        //new MyTask().execute();
        return layout;

    }

    public static List<Information_FragmentB> getData(){
        List<Information_FragmentB> data= new ArrayList<>();
        for (int i = 0; i <= 60; i++) {
            Information_FragmentB current = new Information_FragmentB("" + i+".","00", "00%");
            data.add(current);
        }

        return data;
    }

public class FragmentB_adapter extends RecyclerView.Adapter<FragmentB_adapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information_FragmentB> data = Collections.emptyList();

    public FragmentB_adapter(Context context, List<Information_FragmentB> data)
    {
        inflater = LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.fragment_b_row, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       Information_FragmentB current=data.get(position);
       holder.percent.setText(current.percent);
        holder.usn.setText(subjects.get(position));
       holder.no_of_days.setText(current.no_of_days);

    }


    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView percent;
        public TextView usn;
        public TextView no_of_days;

        public MyViewHolder(View itemView) {
            super(itemView);
            usn  = (TextView) itemView.findViewById(R.id.usn);
            no_of_days = (TextView) itemView.findViewById(R.id.no_of_days);
            percent = (TextView) itemView.findViewById(R.id.percent);
        }
    }
}

    class MyTask extends AsyncTask<Void,Void,Void> {

        String url="http://csemil.web44.net/attendance_result.php",ID="sec";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            HttpContext httpContext = new BasicHttpContext();

            try {
                // Log.d("Hardik", ""+message);
                multipartEntity.addPart(ID, new StringBody(""+message));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(multipartEntity);

            HttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(httpPost, httpContext);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = null;
            try {
                response = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("Hardik", response);
            parseJson(response);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }

    }


    private void parseJson(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
                String sub = jsonArray.getString(i);
                subjects.add(sub);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}








