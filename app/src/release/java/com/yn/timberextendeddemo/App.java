package com.yn.timberextendeddemo;

import android.app.Application;
import android.os.Environment;

import com.yn.timberextended.ConstTagTree;
import com.yn.timberextended.FileTree;
import com.yn.timberextended.MultiTagTree;
import com.yn.timberextended.ReleaseTree;

import timber.log.Timber;

/**
 * Created by Whyn on 2017/8/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new ConstTagTree().setTag("ConstTag")); //set const tag
        Timber.plant(new ReleaseTree());
//        Logger.addLogAdapter(new AndroidLogAdapter());
//        Timber.plant(new Timber.DebugTree(){
//            @Override
//            protected void log(int priority, String tag, String message, Throwable t) {
//                Logger.log(priority, tag, message, t);
//            }
//        });
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Timber.plant(new FileTree().name("FileTree.txt").storeAt(path));

        Timber.plant(new MultiTagTree().addTag("Whyn111"));
    }
}
