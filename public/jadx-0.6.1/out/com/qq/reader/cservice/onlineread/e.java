package com.qq.reader.cservice.onlineread;

import android.content.Context;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.m;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: OnlineProvider */
public class e implements c {
    private Context a;
    private OnlineTag b;
    private c c = null;
    private Set<Long> d;
    private volatile boolean e = false;

    public e(Context context, OnlineTag onlineTag) {
        this.a = context;
        this.b = onlineTag;
        this.d = Collections.synchronizedSet(new HashSet());
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public boolean b() {
        this.b.g(this.b.g() + 1);
        this.b.b(true);
        return true;
    }

    public boolean c() {
        if (this.b.g() <= 1) {
            return false;
        }
        this.b.g(this.b.g() - 1);
        this.b.b(false);
        return true;
    }

    public boolean d() {
        this.b.g(this.b.g());
        return true;
    }

    public boolean a(int i) {
        if (i <= 0) {
            return false;
        }
        this.b.g(i);
        return true;
    }

    public void e() {
        if (this.d.add(Long.valueOf((long) this.b.s()))) {
            a(this.b.z(), false, false);
        }
    }

    private void a(OnlineTag onlineTag, boolean z, boolean z2) {
        ReaderTask onlineChapterDownloadTask = new OnlineChapterDownloadTask(onlineTag, this);
        onlineChapterDownloadTask.setPriority(4);
        onlineChapterDownloadTask.setBackgroundRun(z);
        if (z2) {
            onlineChapterDownloadTask.setRetryTag();
        }
        g.a().a(onlineChapterDownloadTask);
    }

    public void b(OnlineTag onlineTag) {
        if (onlineTag.g() < onlineTag.n()) {
            onlineTag.g(onlineTag.g() + 1);
            File a = v.b().a(onlineTag);
            if (a != null && a.exists() && a.length() > 0) {
                if (!c(onlineTag)) {
                    if (((long) (onlineTag.g() + 2)) <= ((long) onlineTag.n())) {
                        onlineTag.g(onlineTag.g() + 2);
                    }
                } else {
                    return;
                }
            }
            if (this.d.add(Long.valueOf((long) onlineTag.s()))) {
                a(onlineTag, true, false);
            }
        }
    }

    public boolean c(OnlineTag onlineTag) {
        int g = onlineTag.g() + 2;
        if (g > onlineTag.n()) {
            return false;
        }
        String a = v.a(onlineTag.k(), g);
        if (a == null || !new File(a).exists()) {
            return false;
        }
        return true;
    }

    public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult, OnlineChapterDownloadTask onlineChapterDownloadTask) {
        this.d.remove(Long.valueOf((long) onlineTag.s()));
        boolean d = ao.d(this.a);
        int i = -1;
        if (readOnlineResult != null) {
            i = readOnlineResult.H();
        }
        if (i == 1002 && d && onlineChapterDownloadTask != null && !onlineChapterDownloadTask.hasRetryTag() && !onlineChapterDownloadTask.isBackgroundRun() && onlineChapterDownloadTask.tryDelPerOnlineChapter() && this.d.add(Long.valueOf((long) onlineTag.s()))) {
            a(onlineTag, onlineChapterDownloadTask.isBackgroundRun(), true);
        } else if (!m.a(this.a)) {
            if (i == 1002 || !d || onlineChapterDownloadTask == null || onlineChapterDownloadTask.hasRetryTag()) {
                f.d("OKHTTP", "============ERROR CALLBACK USER============");
                if (this.c != null) {
                    this.c.a(this.b, readOnlineResult, onlineChapterDownloadTask);
                }
            } else if (!this.e && this.d.add(Long.valueOf((long) onlineTag.s()))) {
                f.d("OKHTTP", "============ERROR TRY AGAIN============");
                a(onlineTag, onlineChapterDownloadTask.isBackgroundRun(), true);
            }
        }
    }

    public void f() {
        this.e = true;
    }

    public void a(OnlineTag onlineTag, OnlineChapterDownloadTask onlineChapterDownloadTask) {
        this.d.remove(Long.valueOf((long) onlineTag.s()));
        if (this.b.k() == onlineTag.k() && this.b.s() == onlineTag.s()) {
            onlineTag.c(onlineTag.s());
            onlineTag.b(onlineTag.h());
            if (onlineTag.E()) {
                this.b.c(d.a);
                onlineTag.c(d.a);
            }
            onlineTag.d(false);
            if (this.c != null) {
                this.c.a(onlineTag, onlineChapterDownloadTask);
            }
            b(onlineTag.z());
        }
    }

    public void b(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
        this.d.remove(Long.valueOf((long) onlineTag.s()));
        if (this.b.k() == onlineTag.k() && this.b.s() == onlineTag.s() && this.c != null) {
            this.c.b(this.b, readOnlineResult);
        }
    }

    public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
        this.d.remove(Long.valueOf((long) onlineTag.s()));
        if (this.b.k() == onlineTag.k() && this.b.s() == onlineTag.s() && this.c != null) {
            this.c.a(this.b, readOnlineResult);
        }
    }

    public File b(int i) {
        boolean d;
        switch (i) {
            case -12:
                d = d();
                break;
            case -11:
                d = c();
                break;
            case -10:
                d = b();
                break;
            default:
                d = a(i);
                break;
        }
        if (d) {
            return v.b().a(this.b);
        }
        return null;
    }

    public OnlineTag g() {
        return this.b;
    }

    public void d(OnlineTag onlineTag) {
        this.b = onlineTag;
    }

    public void a() {
        if (this.c != null) {
            this.c.a();
        }
    }

    public void a(OnlineTag onlineTag) {
        if (this.c != null) {
            this.c.a(onlineTag);
        }
    }

    public void a(List<ReadOnlineFile> list) {
        if (this.c != null) {
            this.c.a((List) list);
        }
    }
}
