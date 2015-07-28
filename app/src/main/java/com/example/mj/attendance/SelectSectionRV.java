package com.example.mj.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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


public class SelectSectionRV extends Activity {
    RecyclerView section;
    Integer key;
    Integer n=0;
    Integer icons[];
    String titles[];
    String number;
    private boolean backPressedToExitOnce = false;
    String message;
    ArrayList<String> subjects = new ArrayList<>();



    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = (RecyclerView) findViewById(R.id.section);
        Button button = (Button) findViewById(R.id.log_out);
        setContentView(R.layout.activity_select_section_rv);
        recyclerView = (RecyclerView) findViewById(R.id.drawerlist);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        new sel_sec().execute();
        Intent in=getIntent();
        message = in.getStringExtra(MainActivity.KEY);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clear();
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void clear()
    {
        SharedPreferences prefs=getSharedPreferences("data", Context.MODE_PRIVATE);; // here you get your prefrences by either of two methods
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        if (backPressedToExitOnce) {
            super.onBackPressed();
        } else {
            this.backPressedToExitOnce = true;
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();

        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //int pos = recyclerView.getChildPosition(view);
                Intent intent = new Intent(getApplicationContext(), SecondPage2.class);
                startActivity(intent);
        }

        class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;

            public MyHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.listText);
                imageView = (ImageView) itemView.findViewById(R.id.listIcon);
            }
        }


        @Override
        public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectsectionrow, parent, false);
            view.setOnClickListener(this);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {

//            holder.imageView.setImageResource(Integer.parseInt(subjects.get(position)));
            holder.textView.setText(subjects.get(position));
        }

        @Override
        public int getItemCount() {
            return subjects.size();
        }
    }


    class sel_sec extends AsyncTask<Void, Void, Boolean> {
        String url="http://csemil.web44.net/select_section.php",ID="id";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            section.setVisibility(View.INVISIBLE);

        }
        

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            HttpContext httpContext = new BasicHttpContext();

            try {
                multipartEntity.addPart(ID, new StringBody("" + message));
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
           /* String response1 = response.substring(0,1);
            number = response1;
            String response2 = response.substring(3,4);
            if(number.equals("2")) {
                String response3 = response.substring(6, 7);
            }*/
            Log.d("Hardik",response);
            parseJson(response);

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            myAdapter.notifyDataSetChanged();
            /*n=Integer.parseInt(number);
            Integer i;
            for(i=1;i<=n;i++)
            {
                icons[i] = R.drawable.circ;
                titles[i]=;
            }*/
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