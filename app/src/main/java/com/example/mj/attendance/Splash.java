package com.example.mj.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by MJ on 15-Jul-15.
 */
public class Splash extends Activity {

    public boolean isNetworkAvailable() {
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return (activeNetwork != null) && (activeNetwork.getState() == NetworkInfo.State.CONNECTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);


        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                if(isNetworkAvailable())
                {Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                }
                else
                {
                    onStop();
                }
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!isNetworkAvailable()){
            Toast t = Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_LONG);
            t.show();
            finish();
    }
}}
