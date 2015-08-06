package com.example.mj.attendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainActivity extends Activity implements View.OnClickListener{
    EditText login;
    Button next;
    String login_id;
    RelativeLayout load;
    static String KEY;
    private boolean backPressedToExitOnce = false;
    Integer x=0;
    String name;
    Button dsi;
  //  private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.et);
        next = (Button) findViewById(R.id.button);
        dsi= (Button) findViewById(R.id.dsi);
        next.setOnClickListener(this);
        load= (RelativeLayout) findViewById(R.id.load);


        SharedPreferences sp1=this.getSharedPreferences("data",0);
        if(sp1.getString("password",null)!=null) {
            x=1;
            login_id=sp1.getString("password",null);
            Toast t = Toast.makeText(this, "Welcome Back!!", Toast.LENGTH_SHORT);
            t.show();
            next.setEnabled(false);
            dsi.setEnabled(false);
            new login().execute();
        }

    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return (activeNetwork != null) && (activeNetwork.getState() == NetworkInfo.State.CONNECTED);
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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {
            if(isNetworkAvailable()){
            login_id = login.getText().toString();
            new login().execute();
            next.setEnabled(false);
            }
            else
                Toast.makeText(getApplicationContext(), "Oops..Seems like you are not connected to \n    internet.Please connect and try again.", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.dsi) {
            Toast t = Toast.makeText(this, "Computer Science Engineering Dept", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            for (int i = 0; i < 1000; i++) for (i = 0; i < 999; i++) ;
            Toast x = Toast.makeText(this, "Dayanand Sagar College of Engineering", Toast.LENGTH_SHORT);
            x.setGravity(Gravity.BOTTOM, 0, 0);
            x.show();
        }


    }

    public class login extends AsyncTask<Void, Void, Boolean> {
        String url = "http://csemil.web44.net/teacher_login.php", ID = "id";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "Loading", "Please wait...", true);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            HttpContext httpContext = new BasicHttpContext();

            try {
                multipartEntity.addPart(ID, new StringBody("" + login_id));//login id

                httpPost.setEntity(multipartEntity);

                HttpResponse httpResponse = httpClient.execute(httpPost, httpContext);
                HttpEntity httpEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(httpEntity);
                Log.d("Hardik", response);
                name=response.substring(1,response.indexOf('$'));
                response = response.substring(0,1);

                if (response.equals("1")) {
                    return true;
                } else return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


            load.setVisibility(View.INVISIBLE);
            if (aBoolean) {
                if(x==0)
                {
                   // dialog.dismiss();
                   Toast.makeText(getApplicationContext(), "You have successfully Logged In!!", Toast.LENGTH_SHORT).show();}
                SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("password", login_id);
                editor.putString("name", name);
                editor.commit();
                Intent i = new Intent(getApplicationContext(), SelectSectionRV.class);
                i.putExtra(KEY,login_id);
                startActivity(i);
                finish();
            }
            else {
                next.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Enter a valid id", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

