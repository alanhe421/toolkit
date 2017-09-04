package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import java.util.List;

/* compiled from: AbTaskManagerDelegate */
public abstract class a implements e {
    protected i a = null;
    protected j b = null;

    public a(int i) {
        this.a = new i(i);
        this.b = l.c(i);
    }

    public boolean a(g gVar) {
        if (this.b.a(gVar) || !this.a.a(gVar)) {
            return false;
        }
        this.b.c(gVar);
        return true;
    }

    public boolean a(String str) {
        return this.a.a(str);
    }

    public List<g> a() {
        return this.b.a();
    }

    public void b(g gVar) {
        this.a.f(gVar);
    }

    public void c(g gVar) {
        this.a.c(gVar);
    }

    public void a(TaskStateEnum[] taskStateEnumArr, m mVar) {
        this.a.a(taskStateEnumArr, mVar);
    }

    public void b(TaskStateEnum[] taskStateEnumArr, m mVar) {
        this.a.b(taskStateEnumArr, mVar);
    }

    public void d(final g gVar) {
        if (gVar != null) {
            this.b.d(gVar);
            new Thread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.a.e(gVar);
                }
            }).start();
        }
    }

    public void e(g gVar) {
        this.a.b(gVar);
    }

    public synchronized boolean a(Context context) {
        boolean z;
        if (d()) {
            z = false;
        } else {
            this.b.b();
            this.a.a(context);
            b();
            z = true;
        }
        return z;
    }

    protected synchronized void b() {
        List<g> c = this.a.c();
        if (c != null) {
            for (g gVar : c) {
                switch (gVar.getState()) {
                    case DeactivePrepared:
                    case DeactiveStarted:
                    case Prepared:
                    case Started:
                        c(gVar);
                        break;
                }
                if (!this.b.a(gVar)) {
                    this.b.b(gVar);
                }
            }
        }
    }

    public synchronized void c() {
        this.a.a();
        this.b.b();
    }

    public boolean d() {
        return this.a.d();
    }
}
