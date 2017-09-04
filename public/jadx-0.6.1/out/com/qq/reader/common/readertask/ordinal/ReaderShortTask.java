package com.qq.reader.common.readertask.ordinal;

import android.os.Handler;
import com.qq.reader.common.readertask.ReaderTask;

public abstract class ReaderShortTask extends ReaderTask {
    public static final String TASKNAME = "ReaderShortTask";
    protected Handler mActivityHandler;

    public String getTaskName() {
        return TASKNAME;
    }

    public void setHandler(Handler handler) {
        this.mActivityHandler = handler;
    }
}
