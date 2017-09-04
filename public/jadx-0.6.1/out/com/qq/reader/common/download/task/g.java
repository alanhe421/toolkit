package com.qq.reader.common.download.task;

import com.qq.reader.common.download.task.state.TaskStateEnum;

/* compiled from: Task */
public interface g {
    String getName();

    int getProgress();

    TaskStateEnum getState();

    int getTaskType();

    void reInit();

    void setState(TaskStateEnum taskStateEnum);
}
