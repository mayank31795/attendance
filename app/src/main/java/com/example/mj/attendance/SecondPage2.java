package com.example.mj.attendance;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by AK PC on 22-07-2015.
 */
public class SecondPage2 extends FragmentActivity {

    ViewPager viewPager=null;

  @Override
  protected void onCreate(Bundle saveInstanceState){
      super.onCreate(saveInstanceState);
      setContentView(R.layout.activity_second_page2);
      viewPager = (ViewPager) findViewById(R.id.pager);
      FragmentManager fragmentManager = getSupportFragmentManager();
      viewPager.setAdapter(new MyAdapter(fragmentManager));
  }
}

class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if(i==0)
        {
           fragment = new FragmentA();
        }
        if(i==1)
        {
            fragment = new FragmentB();
        }if(i==2)
        {
            fragment = new FragmentC();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = new String();
        if(position==0) {
            title="MARK ATTENDANCE";


        }
        if(position==1) {
            title= "OVERALL ATTENDANCE";
        }
        if(position==2) {
            title= "EACH DAY ATTENDANCE";
        }
        return title;
        }


}

