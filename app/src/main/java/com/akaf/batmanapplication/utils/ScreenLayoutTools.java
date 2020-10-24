package com.akaf.batmanapplication.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.ImageView;

import com.akaf.batmanapplication.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ScreenLayoutTools {

    private static ScreenLayoutTools screen_layout_tools_instance = null;

    // static method to create instance of Singleton class
    public static ScreenLayoutTools getInstance() {
        if (screen_layout_tools_instance == null)
            screen_layout_tools_instance = new ScreenLayoutTools();

        return screen_layout_tools_instance;
    }

    public int screenLayout() {
        boolean normal = ((App.context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL);
        boolean large = ((App.context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        boolean xlarge = ((App.context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        if (normal)
            return 2;
        else if (large)
            return 3;
        else if (xlarge)
            return 5;
        return 5;
    }
}
