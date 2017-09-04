package com.qq.reader.common.readertask.ordinal;

import com.qq.reader.common.readertask.ReaderTask;

public abstract class ReaderDBTask extends ReaderTask {
    public static final String TASKNAME = "ReaderDBTask";

    public String getTaskName() {
        return TASKNAME;
    }
}
