package com.example.mj.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    EditText login;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login =(EditText )findViewById(R.id.et);
        next =(Button)findViewById(R.id.button);
        next.setOnClickListener(this);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {
        String na=login.getText().toString();

        switch (v.getId()) {
            case R.id.button:

                if (na.equals("123")) {
                    Intent i=new Intent(getApplicationContext(),SelectSectionRV.class);
                    startActivity(i);
                    finish();
                }

                else if (na.equals("abc")) {
                    Intent i=new Intent(this,SelectSectionRV.class);
                    startActivity(i);
                    finish();
                }

                else {

                      Toast  t = Toast.makeText(this,"Wrong ID",Toast.LENGTH_LONG);
                        t.show();

                }

                break;
            default:
                break;

        }

    }
}
