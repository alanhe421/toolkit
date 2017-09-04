package com.qq.reader.liveshow.model.filter.queue.impl;

import com.qq.reader.liveshow.model.filter.a.b;
import com.qq.reader.liveshow.model.filter.queue.a;
import com.qq.reader.liveshow.utils.SxbLog;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageBlockingQueue extends LinkedBlockingDeque implements a {
    private static final String TAG = "MessageBlockingDeque";
    private int max = Integer.MAX_VALUE;

    public MessageBlockingQueue(int i) {
        this.max = i;
    }

    public void clear() {
        super.clear();
    }

    public boolean add(b bVar) {
        if (size() < this.max) {
            try {
                put(bVar);
                return true;
            } catch (InterruptedException e) {
                SxbLog.e(TAG, e.getMessage());
            }
        }
        return false;
    }

    public boolean addToFirst(b bVar) {
        if (size() > 2147483646) {
            return false;
        }
        try {
            putFirst(bVar);
            return true;
        } catch (InterruptedException e) {
            SxbLog.e(TAG, e.getMessage());
            return false;
        }
    }

    public b get() {
        try {
            return (b) take();
        } catch (InterruptedException e) {
            SxbLog.e(TAG, e.getMessage());
            return null;
        }
    }
}
