package com.example.mj.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

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


public class MarkAttendance extends Activity {

    String sec;
    SecondPage2.MyAdapter myAdapter;
    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);


      /*  Intent in=getIntent();
        SecondPage2 secondPage2=new SecondPage2();
        sec = in.getStringExtra(secondPage2.section);
        Log.d("hardik",sec);
        new mark_att().execute();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mark_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* public int getItemCount() {
        return names.size();
    }*/

    /*class mark_att extends AsyncTask<Void, Void, Boolean> {
        String url = "http://csemil.web44.net/mark_attendance.php", ID = "sec";

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            HttpContext httpContext = new BasicHttpContext();

            try {
                multipartEntity.addPart(ID, new StringBody("" + sec));
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
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            myAdapter.notifyDataSetChanged();
        }

    }
    private void parseJson(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
                String name = jsonArray.getString(i);
                names.add(name);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}