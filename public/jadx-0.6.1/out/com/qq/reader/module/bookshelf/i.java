package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: BooksAdapter */
public class i extends BaseAdapter {
    protected Context a;
    protected volatile ArrayList<Mark> b;
    protected int c = 3;
    protected final int d = Opcodes.INT_TO_CHAR;
    protected final int e = 2;
    protected final int f = 4;
    protected final int g = 3;
    protected final int h = 6;
    protected HashMap<String, Drawable> i = new HashMap();
    protected HashMap<String, Drawable> j = new HashMap();
    protected boolean k = false;
    protected boolean l = false;
    protected int m = -1;
    protected boolean n = false;
    private Mark o;

    public i(Context context, boolean z) {
        this.a = context;
        this.b = new ArrayList();
        this.k = z;
        a();
    }

    public void a(int i) {
        this.m = i;
    }

    private void a() {
    }

    public ArrayList<Mark> b() {
        return this.b;
    }

    public void a(Mark mark) {
        synchronized (this.b) {
            if (mark != null) {
                int size = this.b.size();
                String id = mark.getId();
                for (int i = 0; i < size; i++) {
                    if (((Mark) this.b.get(i)).getId().equals(id)) {
                        this.b.set(i, mark);
                    }
                }
            }
        }
    }

    public void a(Mark[] markArr) {
        String str = null;
        if (this.n) {
            a c = c.c();
            if (c != null) {
                str = c.c();
            }
        }
        synchronized (this.b) {
            if (markArr != null) {
                int length = markArr.length - 1;
                while (length >= 0) {
                    if (markArr[length] != null) {
                        if (str != null && d.a(this.a, str, markArr[length].getBookId()) > 0) {
                            markArr[length].setOperateTime(System.currentTimeMillis());
                        }
                        this.b.add(markArr[length]);
                    }
                    length--;
                }
                c();
            }
        }
    }

    public void a(List<Mark> list) {
        synchronized (this.b) {
            if (list != null) {
                for (Mark mark : list) {
                    if (mark != null) {
                        this.b.add(mark);
                    }
                }
                c();
            }
        }
    }

    public void c() {
        Collections.sort(this.b, f.b);
        if (com.qq.reader.common.db.handle.a.a().c() != null) {
            Collections.sort(this.b, f.c);
        }
    }

    public void b(Mark mark) {
        this.o = mark;
    }

    public void d() {
        synchronized (this.b) {
            if (this.b != null && this.b.size() > 0) {
                this.b.clear();
            }
        }
    }

    public void c(Mark mark) {
        synchronized (this.b) {
            if (mark != null) {
                if (this.b != null && this.b.size() > 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        Mark mark2 = (Mark) it.next();
                        if (mark2.getId().equals(mark.getId())) {
                            this.b.remove(mark2);
                            c();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void a(Mark mark, int i) {
        synchronized (this.b) {
            if (mark != null) {
                if (this.b != null && this.b.size() > 0) {
                    for (int i2 = 0; i2 < this.b.size(); i2++) {
                        if (mark.getId().equals(((Mark) this.b.get(i2)).getId())) {
                            ((Mark) this.b.get(i2)).setPrivateProperty(i);
                        }
                    }
                }
            }
        }
    }

    public void a(String str, int i) {
        if (!TextUtils.isEmpty(str) && this.b != null && this.b.size() > 0) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (str.equals(String.valueOf(((Mark) this.b.get(i2)).getBookId()))) {
                    ((Mark) this.b.get(i2)).setPrivateProperty(i);
                }
            }
        }
    }

    public int e() {
        int size;
        synchronized (this.b) {
            size = this.b.size();
        }
        return size;
    }

    public int getCount() {
        return e();
    }

    public int getItemViewType(int i) {
        b c = com.qq.reader.common.db.handle.a.a().c();
        Mark mark = (Mark) getItem(i);
        if (c != null && c.b() == mark.getBookId()) {
            return 2;
        }
        if (i == 0) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public Object getItem(int i) {
        Object obj;
        synchronized (this.b) {
            if (this.b == null || i >= this.b.size() || i < 0) {
                obj = null;
            } else {
                obj = this.b.get(i);
            }
        }
        return obj;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        g gVar;
        boolean z = true;
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.bookshelf_onlinebook_item, null);
            g gVar2 = new g(view, this.a);
            view.setTag(gVar2);
            gVar = gVar2;
        } else {
            gVar = (g) view.getTag();
        }
        final Mark mark = (Mark) this.b.get(i);
        if (mark != null) {
            boolean z2;
            if (mark.getIsFinish() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            String str = "";
            str = mark.getLastUpdateChapter();
            if (mark instanceof LocalMark) {
                gVar.b(mark.getBookShortName());
                if (z2 || str.length() <= 0) {
                    gVar.c(mark.getAuthor());
                } else {
                    gVar.c(str);
                }
                gVar.a(mark.isLastRead());
                if (mark.getReadTime() <= 0) {
                    z = false;
                }
                gVar.c(z);
                gVar.b(mark.hasNewContent());
                gVar.a(null, false, false);
                gVar.a(false, 0, false);
                gVar.d(ao.b(mark));
            } else if (mark instanceof DownloadMark) {
                gVar.b(mark.getBookShortName());
                if (z2 || str.length() <= 0) {
                    gVar.c(mark.getAuthor());
                } else {
                    gVar.c(str);
                }
                gVar.d(ao.b(mark));
                gVar.a(false);
                gVar.b(false);
                DownloadBookTask downloadTask = ((DownloadMark) mark).getDownloadTask();
                if (downloadTask != null) {
                    gVar.a(true, downloadTask.getProgress(), downloadTask.getIsOnlyDownLoadIcon());
                    gVar.a(downloadTask.getState(), downloadTask.getIsOnlyDownLoadIcon(), false);
                }
            }
            if ((mark instanceof LocalMark) || (mark instanceof DownloadMark)) {
                com.qq.reader.common.imageloader.c.a(this.a).a(mark.getImageURI(), gVar.a, com.qq.reader.common.imageloader.a.a().j(), new e<String, b>(this) {
                    final /* synthetic */ i c;

                    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                        gVar.a(mark.getBookName());
                        return true;
                    }

                    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                        return false;
                    }
                });
            }
        }
        return view;
    }

    public void a(boolean z) {
        this.n = z;
    }
}
