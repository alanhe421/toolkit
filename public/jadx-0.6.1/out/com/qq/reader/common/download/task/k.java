package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.download.task.state.TaskDeactivateStartedState;
import com.qq.reader.common.download.task.state.TaskDeactivePreparedState;
import com.qq.reader.common.download.task.state.TaskFailedState;
import com.qq.reader.common.download.task.state.TaskFinishedState;
import com.qq.reader.common.download.task.state.TaskInstallCompletedState;
import com.qq.reader.common.download.task.state.TaskInstallFailedState;
import com.qq.reader.common.download.task.state.TaskInstallingState;
import com.qq.reader.common.download.task.state.TaskPausedState;
import com.qq.reader.common.download.task.state.TaskPreparedState;
import com.qq.reader.common.download.task.state.TaskRemovedState;
import com.qq.reader.common.download.task.state.TaskStartedState;
import com.qq.reader.common.download.task.state.TaskState;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.download.task.state.TaskUninstallState;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.NetworkStateForConfig;
import com.qq.reader.common.readertask.NetworkStateForConfig.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: TaskManager */
public class k {
    protected volatile Thread a;
    protected volatile h b;
    protected ExecutorService c;
    protected final LinkedBlockingQueue<g> d = new LinkedBlockingQueue();
    protected final Map<g, o> e = Collections.synchronizedMap(new HashMap());
    protected final List<g> f = new LinkedList();
    protected final List<g> g = new LinkedList();
    protected volatile boolean h = false;
    protected volatile Context i;
    protected volatile boolean j;
    protected volatile d k;
    protected volatile int l = 1;
    protected m m;
    private final Map<g, Long> n = Collections.synchronizedMap(new HashMap());
    private final Map<g, n> o = Collections.synchronizedMap(new HashMap());
    private final Map<TaskStateEnum, List<m>> p = Collections.synchronizedMap(new HashMap());
    private a q = new a(this) {
        final /* synthetic */ k a;

        {
            this.a = r1;
        }

        public void a(boolean z) {
            if (z) {
                f.c("PhoneStateChangeListene:", "Data Connected.");
                this.a.h();
                return;
            }
            f.c("PhoneStateChangeListener:", "Data Disonnected.");
            this.a.i();
        }
    };

    k(int i) {
        this.l = i;
        b();
    }

    protected boolean a(Context context) {
        return ao.j(context);
    }

    protected synchronized void a() {
        this.m = new m(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(n nVar) {
                switch (nVar.c()) {
                    case Removed:
                        this.a.h(nVar);
                        return;
                    case DeactivePrepared:
                        this.a.c(nVar);
                        this.a.o(nVar.d());
                        return;
                    case DeactiveStarted:
                        this.a.b(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Failed:
                        this.a.g(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Finished:
                        this.a.f(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Paused:
                        this.a.e(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Started:
                        this.a.d(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Prepared:
                        this.a.a(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Installing:
                        this.a.i(nVar);
                        this.a.o(nVar.d());
                        return;
                    case Uninstalled:
                        this.a.j(nVar);
                        this.a.o(nVar.d());
                        return;
                    default:
                        this.a.o(nVar.d());
                        return;
                }
            }
        };
        for (TaskStateEnum a : TaskStateEnum.values()) {
            a(a, this.m);
        }
    }

    protected synchronized void b() {
        if (this.c != null) {
            this.c.shutdownNow();
            this.c = null;
        }
        if (this.l > 0) {
            this.c = Executors.newFixedThreadPool(this.l);
        } else {
            this.c = Executors.newFixedThreadPool(1);
        }
    }

    g c() throws InterruptedException {
        return (g) this.d.take();
    }

    boolean d() {
        boolean z;
        synchronized (this.e) {
            z = this.l - this.e.size() > 0;
        }
        return z;
    }

    synchronized boolean b(Context context) {
        boolean z = false;
        synchronized (this) {
            if (this.h) {
                f.e(g() + "setConcurrentTasks", "DownloadTaskManager has already started, command has been ignored.");
            } else {
                this.j = false;
                this.d.clear();
                this.e.clear();
                this.i = context;
                a();
                NetworkStateForConfig.a().a(this.q);
                b();
                m();
                this.h = true;
                f.e(g() + "setConcurrentTasks", "DownloadTaskManager is started.");
                z = true;
            }
        }
        return z;
    }

    public void a(d dVar) {
        this.k = dVar;
    }

    protected synchronized void e() {
        this.h = false;
        n();
        Map hashMap = new HashMap(this.e);
        for (o oVar : hashMap.values()) {
            oVar.f();
            a(oVar.e(), TaskActionEnum.Pause);
        }
        hashMap.clear();
        this.e.clear();
        List<g> linkedList = new LinkedList(this.d);
        for (g a : linkedList) {
            a(a, TaskActionEnum.Pause);
        }
        linkedList.clear();
        this.d.clear();
        if (this.c != null) {
            this.c.shutdownNow();
            this.c = null;
        }
        NetworkStateForConfig.a().b(this.q);
        f.e(g() + "shutdown", "DownloadManager is terminated ...");
    }

    protected synchronized void f() {
        f.e(g() + "shutdown", "DownloadManager is shutting down ...");
        this.h = false;
        n();
        Map hashMap = new HashMap(this.e);
        for (o oVar : hashMap.values()) {
            oVar.f();
            a(oVar.e(), TaskActionEnum.Deactivate);
        }
        hashMap.clear();
        this.e.clear();
        List<g> linkedList = new LinkedList(this.d);
        for (g a : linkedList) {
            a(a, TaskActionEnum.Deactivate);
        }
        linkedList.clear();
        this.d.clear();
        if (this.c != null) {
            this.c.shutdownNow();
            this.c = null;
        }
        NetworkStateForConfig.a().b(this.q);
        f.e(g() + "shutdown", "DownloadManager is terminated ...");
    }

    protected synchronized void a(g gVar) {
        Runnable a = l.a(this, gVar, this.a, this.i);
        this.e.put(gVar, a);
        this.c.submit(a);
    }

    protected synchronized boolean b(g gVar) {
        boolean z;
        if (!this.h) {
            f.e(g() + "createTask", "Download Task Manager is not started.");
            z = false;
        } else if (this.e.containsKey(gVar) || this.d.contains(gVar)) {
            z = false;
        } else {
            if (this.k.a(gVar) && (gVar instanceof DownloadBookTask)) {
                d.h(this.i.getApplicationContext(), String.valueOf(((DownloadBookTask) gVar).getId()));
            }
            if (c(gVar)) {
                f.a(g(), " fail to create download task, ignore this task.");
                z = false;
            } else {
                if (this.j) {
                    if (a(this.i)) {
                        h();
                    } else {
                        a(gVar, TaskActionEnum.Deactivate);
                    }
                }
                try {
                    this.d.put(gVar);
                } catch (Exception e) {
                    f.a(g() + "createTask", "put operation is interrupted", e);
                }
                z = true;
            }
        }
        return z;
    }

    protected boolean c(g gVar) {
        return false;
    }

    protected String g() {
        return "Thread = " + Thread.currentThread().getName() + " " + getClass().getSimpleName() + ".";
    }

    public void d(g gVar) {
        f.e(g() + "restart", gVar + " is Restarting.");
        gVar.reInit();
        e(gVar);
        if (this.k != null) {
            this.k.c(gVar);
        }
        f.e(g() + "restart", gVar + " is Restarted.");
    }

    public void e(g gVar) {
        f.e(g() + "removeTask", gVar + " is Removing.");
        t(gVar);
        synchronized (this.d) {
            if (this.d.contains(gVar)) {
                this.d.remove(gVar);
            }
        }
        synchronized (this.e) {
            if (this.e.containsKey(gVar)) {
                o oVar = (o) this.e.remove(gVar);
                if (oVar != null) {
                    oVar.f();
                }
            }
        }
        f.e(g() + "removeTask", gVar + " is Removed.");
    }

    public void f(g gVar) {
        f.e(g() + "uninstallTask", gVar + " is Uninstalled.");
    }

    public void g(g gVar) {
        f.e(g() + "startTask", gVar + " is Starting.");
        if (this.h) {
            f.e(g() + "startTask", gVar + " is Started.");
            return;
        }
        throw new IllegalStateException("Download Task Manager is not started.");
    }

    public void h(g gVar) {
        if (!this.h) {
            throw new IllegalStateException("Download Task Manager is not started.");
        }
    }

    public void i(g gVar) {
        if (this.e.containsKey(gVar)) {
            this.e.remove(gVar);
        }
    }

    public void j(g gVar) {
        synchronized (this.d) {
            if (this.d.contains(gVar)) {
                this.d.remove(gVar);
            }
        }
        synchronized (this.e) {
            if (this.e.containsKey(gVar)) {
                ((o) this.e.remove(gVar)).f();
            }
        }
    }

    public void k(g gVar) {
        synchronized (this.d) {
            if (this.d.contains(gVar)) {
                this.d.remove(gVar);
            }
        }
        synchronized (this.e) {
            if (this.e.containsKey(gVar)) {
                this.e.remove(gVar);
            }
        }
    }

    public void l(g gVar) {
    }

    public synchronized void m(g gVar) {
    }

    public void n(g gVar) {
    }

    public synchronized void h() {
        this.j = false;
        if (this.h) {
            if (this.g.size() > 0) {
                for (g a : new LinkedList(this.g)) {
                    a(a, TaskActionEnum.Activate);
                }
                this.g.clear();
            }
            if (this.f.size() > 0) {
                for (g a2 : this.f) {
                    a(a2, TaskActionEnum.Activate);
                }
                this.f.clear();
            }
        } else {
            f.e(g() + "activateTasks", "Download Task Manager is not started.");
        }
    }

    public synchronized void i() {
        this.j = true;
        synchronized (this.d) {
            if (this.d.size() > 0) {
                this.f.addAll(this.d);
                for (g a : this.f) {
                    a(a, TaskActionEnum.Deactivate);
                }
            }
        }
    }

    private void t(g gVar) {
    }

    public synchronized List<g> j() {
        f.e(g() + "getDownloadTasks", "fetching all tasks.");
        if (this.k == null) {
            throw new IllegalStateException("TaskManager.start should be called before getTasks method.");
        }
        return this.k.a();
    }

    protected void o(g gVar) {
        if (this.k != null) {
            this.k.b(gVar);
        }
    }

    public boolean k() {
        return this.j;
    }

    protected void a(n nVar) {
        if (!this.e.containsKey(nVar.d()) && !this.d.contains(nVar.d())) {
            try {
                m();
                this.d.put(nVar.d());
            } catch (InterruptedException e) {
            }
        }
    }

    protected synchronized void b(n nVar) {
        synchronized (this.e) {
            if (this.e.containsKey(nVar.d())) {
                this.e.remove(nVar.d());
            }
        }
        if (!this.j && this.h) {
            a(nVar.d(), TaskActionEnum.Activate);
        } else if (this.h && !this.g.contains(nVar.d())) {
            this.g.add(nVar.d());
        }
    }

    protected synchronized void c(n nVar) {
        synchronized (this.d) {
            if (this.d.contains(nVar.d())) {
                this.d.remove(nVar.d());
            }
        }
        if (!this.j && this.h) {
            a(nVar.d(), TaskActionEnum.Activate);
        }
    }

    protected void d(n nVar) {
    }

    protected void e(n nVar) {
    }

    protected void f(n nVar) {
        p(nVar.d());
    }

    void p(g gVar) {
        a(gVar, TaskActionEnum.Install);
    }

    protected void g(n nVar) {
        f.e(g() + "onTaskFailed", "StateChange calls back");
        f.e(g() + "onTaskFailed", "StateChange called back finished");
    }

    protected synchronized void h(n nVar) {
        f.e(g() + "onTaskRemoved", "StateChange calls back");
        if (this.k != null) {
            this.k.a(nVar);
        } else {
            f.e(g(), "helper is not initialized. Remove operation ignored");
        }
        f.e(g() + "onTaskRemoved", "StateChange called back finished");
    }

    protected void i(n nVar) {
    }

    protected void j(n nVar) {
        f.e(g() + "onTaskUninstall", "StateChange calls back");
        f.e(g() + "onTaskUninstall", "StateChange called back finished");
    }

    public void q(g gVar) {
    }

    public void r(g gVar) {
    }

    public void s(g gVar) {
    }

    public boolean a(String str) {
        if (this.k == null) {
            return false;
        }
        return this.k.a(str);
    }

    public boolean l() {
        return this.h;
    }

    protected synchronized void m() {
        if (this.b == null) {
            this.b = new h(this);
            this.a = new Thread(this.b);
            this.a.start();
        }
    }

    synchronized void n() {
        if (this.b != null) {
            this.b.a();
            this.a.interrupt();
        }
        this.b = null;
        this.a = null;
    }

    public TaskState a(g gVar, TaskActionEnum taskActionEnum) {
        TaskState doStateChange;
        synchronized (TaskState.class) {
            n nVar = new n(this, gVar, taskActionEnum);
            TaskState a = a(gVar.getState());
            try {
                doStateChange = a.doStateChange(nVar);
                if (!a.equals(doStateChange) || taskActionEnum == TaskActionEnum.Receive) {
                    k(nVar);
                }
            } catch (TaskStateChangeException e) {
                return a;
            }
        }
        return doStateChange;
    }

    private static TaskState a(TaskStateEnum taskStateEnum) {
        if (taskStateEnum == null) {
            throw new NullPointerException();
        }
        switch (taskStateEnum) {
            case Removed:
                return new TaskRemovedState();
            case DeactivePrepared:
                return new TaskDeactivePreparedState();
            case DeactiveStarted:
                return new TaskDeactivateStartedState();
            case Failed:
                return new TaskFailedState();
            case Finished:
                return new TaskFinishedState();
            case Paused:
                return new TaskPausedState();
            case Started:
                return new TaskStartedState();
            case Prepared:
                return new TaskPreparedState();
            case Installing:
                return new TaskInstallingState();
            case Uninstalled:
                return new TaskUninstallState();
            case InstallCompleted:
                return new TaskInstallCompletedState();
            case InstallFailed:
                return new TaskInstallFailedState();
            default:
                throw new IllegalStateException();
        }
    }

    void k(n nVar) {
        if (this.p.size() <= 0) {
            f.a("notifyStateChange", "no listener registered");
            return;
        }
        long j;
        g d = nVar.d();
        if (this.n.get(d) == null) {
            j = 0;
        } else {
            j = ((Long) this.n.get(d)).longValue();
        }
        n nVar2 = (n) this.o.get(d);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - j >= 500 || nVar2 == null || nVar2.b() != nVar.b() || nVar2.c() != nVar.c()) {
            if (this.p.get(nVar.c()) == null) {
                this.p.put(nVar.c(), new LinkedList());
            }
            try {
                for (m a : (List) this.p.get(nVar.c())) {
                    a.a(nVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (nVar.c() == TaskStateEnum.Finished || nVar.c() == TaskStateEnum.Removed) {
                this.n.remove(d);
                this.o.remove(d);
                return;
            }
            this.n.put(d, Long.valueOf(currentTimeMillis));
            this.o.put(d, nVar);
        }
    }

    public synchronized void a(TaskStateEnum taskStateEnum, m mVar) {
        List list;
        if (this.p.containsKey(taskStateEnum)) {
            list = (List) this.p.get(taskStateEnum);
        } else {
            list = Collections.synchronizedList(new LinkedList());
            this.p.put(taskStateEnum, list);
        }
        list.add(mVar);
    }

    public synchronized void b(TaskStateEnum taskStateEnum, m mVar) {
        List list;
        if (this.p.containsKey(taskStateEnum)) {
            list = (List) this.p.get(taskStateEnum);
        } else {
            list = Collections.synchronizedList(new LinkedList());
            this.p.put(taskStateEnum, list);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((m) it.next()) == mVar) {
                it.remove();
            }
        }
    }
}
