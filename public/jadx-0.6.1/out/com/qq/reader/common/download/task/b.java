package com.qq.reader.common.download.task;

import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.f;

/* compiled from: AbstractTask */
public abstract class b implements g {
    private String name;
    private int progress;
    private TaskStateEnum state = TaskStateEnum.Prepared;

    public abstract void onReinit();

    public b(String str) {
        this.name = str;
    }

    public TaskStateEnum getState() {
        return this.state;
    }

    public void setState(int i) {
        TaskStateEnum[] values = TaskStateEnum.values();
        if (i < 0 || i >= values.length) {
            this.state = TaskStateEnum.Failed;
            f.a("setState ERROR : ", i + " outof " + values.length);
            return;
        }
        this.state = values[i];
    }

    public void setState(TaskStateEnum taskStateEnum) {
        this.state = taskStateEnum;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public boolean hasFinish() {
        return getState() == TaskStateEnum.Finished || getState() == TaskStateEnum.Failed || getState() == TaskStateEnum.Installing || getState() == TaskStateEnum.InstallCompleted || getState() == TaskStateEnum.InstallFailed;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void reInit() {
        this.state = TaskStateEnum.Prepared;
        this.progress = 0;
        onReinit();
    }

    public void reStart() {
        this.state = TaskStateEnum.Paused;
        this.progress = 0;
        onReinit();
    }
}
