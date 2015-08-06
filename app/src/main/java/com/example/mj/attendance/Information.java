package com.example.mj.attendance;

import android.widget.CheckBox;

/**
 * Created by AK PC on 24-07-2015.
 */
public class Information {
    //int iconId;
   // CheckBox icon;
    public boolean isSelected;
    public String title;

    public Information(String name) {
        this.title = name;
    }

    public Information(String name, boolean isSelected) {
        this.title = name;
        this.isSelected = isSelected;
    }
    public  Information(boolean isSelected)
    {
        this.isSelected=isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
