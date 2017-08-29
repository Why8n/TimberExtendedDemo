package com.yn.timberextended;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import timber.log.Timber;

/**
 * Created by Whyn on 2017/8/28.
 */

public class MultiTagTree extends Timber.DebugTree {
    private static final String SEPARATOR = "-";
    private List<String> mTags = new CopyOnWriteArrayList<>();

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + ":" + element.getLineNumber();
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        String newTag = produceTag(tag);
        super.log(priority, newTag, message, t);
    }

    //override this method to produce your own multi tag
    protected String produceTag(String originTag) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, lenth = mTags.size(); i < lenth; ++i) {
            builder.append(mTags.get(i))
                    .append(i != lenth - 1 ? SEPARATOR :
                            (originTag == null ? "" : SEPARATOR + originTag));
        }
        return builder.toString();
    }

    public boolean removeTag(@NonNull final String tag) {
        return mTags.remove(tag);
    }

    public MultiTagTree addTag(String... tags) {
        for (String tag : tags) {
            if (!mTags.contains(tag)) {
                mTags.add(tag);
            }
        }
        return this;
    }
}
