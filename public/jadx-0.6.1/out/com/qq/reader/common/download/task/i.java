package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import java.util.List;

/* compiled from: TaskFacade */
public class i {
    private k a;

    public i(int i) {
        this.a = l.a(i);
    }

    public synchronized boolean a(Context context) {
        return this.a.b(context);
    }

    public synchronized void a() {
        this.a.f();
    }

    public synchronized void b() {
        this.a.e();
    }

    public boolean a(g gVar) {
        return this.a.b(gVar);
    }

    public void b(g gVar) {
        this.a.a(gVar, TaskActionEnum.Resume);
    }

    public void c(g gVar) {
        this.a.a(gVar, TaskActionEnum.Pause);
    }

    public void d(g gVar) {
        this.a.a(gVar, TaskActionEnum.Restart);
    }

    public void e(g gVar) {
        this.a.a(gVar, TaskActionEnum.Remove);
    }

    public void a(TaskStateEnum[] taskStateEnumArr, m mVar) {
        if (taskStateEnumArr != null && taskStateEnumArr.length > 0) {
            for (TaskStateEnum a : taskStateEnumArr) {
                this.a.a(a, mVar);
            }
        }
    }

    public void a(TaskStateEnum taskStateEnum, m mVar) {
        this.a.a(taskStateEnum, mVar);
    }

    public void b(TaskStateEnum taskStateEnum, m mVar) {
        this.a.b(taskStateEnum, mVar);
    }

    public void b(TaskStateEnum[] taskStateEnumArr, m mVar) {
        if (taskStateEnumArr != null && taskStateEnumArr.length > 0) {
            for (TaskStateEnum b : taskStateEnumArr) {
                this.a.b(b, mVar);
            }
        }
    }

    public List<g> c() {
        return this.a.j();
    }

    public void f(g gVar) {
        this.a.a(gVar, TaskActionEnum.InstallComplete);
    }

    public boolean a(String str) {
        return this.a.a(str);
    }

    public boolean d() {
        return this.a.l();
    }
}
