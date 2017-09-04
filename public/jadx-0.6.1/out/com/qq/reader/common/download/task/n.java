package com.qq.reader.common.download.task;

import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.download.task.state.TaskStateEnum;

/* compiled from: TaskStateContext */
public class n {
    private TaskStateEnum a;
    private TaskActionEnum b;
    private TaskStateEnum c;
    private g d;
    private k e;
    private boolean f;
    private long g;

    public n(k kVar, g gVar, TaskActionEnum taskActionEnum) {
        this.e = kVar;
        this.d = gVar;
        this.b = taskActionEnum;
        this.a = gVar.getState();
    }

    public TaskStateEnum a() {
        return this.a;
    }

    public TaskActionEnum b() {
        return this.b;
    }

    public TaskStateEnum c() {
        return this.c;
    }

    public void a(TaskStateEnum taskStateEnum) {
        this.c = taskStateEnum;
        if (this.d != null) {
            this.d.setState(taskStateEnum);
        }
    }

    public g d() {
        return this.d;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(long j) {
        this.g = j;
    }

    public k e() {
        return this.e;
    }
}
