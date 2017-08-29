package com.yn.timberextended;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by Whyn on 2017/8/28.
 */

public class ReleaseTree extends Timber.DebugTree {
    @Override
    protected boolean isLoggable(String tag, int priority) {
        return priority == Log.WARN
                || priority == Log.ERROR
                || priority == Log.ASSERT;
    }

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + ":" + element.getLineNumber();
    }
}
