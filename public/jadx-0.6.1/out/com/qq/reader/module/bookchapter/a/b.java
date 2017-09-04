package com.qq.reader.module.bookchapter.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.ReaderApplication;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.bookchapter.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: LocalChapterListAdapter */
public class b extends com.qq.reader.module.bookchapter.b {
    private ArrayList<Mark> a = new ArrayList();
    private long b;
    private ArrayList<Integer> c = new ArrayList();

    public long a(long j) {
        Object obj = null;
        long j2 = 0;
        Mark[] e = c.a().e();
        if (e == null || e.length == 0) {
            return 0;
        }
        for (int i = 0; i < e.length; i++) {
            Mark mark = e[i];
            if (mark.getStartPoint() >= j) {
                if (mark.getStartPoint() == j) {
                    this.b = (long) i;
                    obj = 1;
                } else {
                    long j3;
                    if (i - 1 > 0) {
                        j3 = (long) (i - 1);
                    } else {
                        j3 = 0;
                    }
                    this.b = j3;
                    int i2 = 1;
                }
                if (obj == null) {
                    if (e.length - 1 > 0) {
                        j2 = (long) (e.length - 1);
                    }
                    this.b = j2;
                }
                return this.b;
            }
        }
        if (obj == null) {
            if (e.length - 1 > 0) {
                j2 = (long) (e.length - 1);
            }
            this.b = j2;
        }
        return this.b;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        int size;
        synchronized (this.a) {
            size = this.a.size();
        }
        return size;
    }

    public Object getItem(int i) {
        if (i > getCount() || i < 0) {
            return null;
        }
        Object obj;
        synchronized (this.a) {
            obj = this.a.get(i);
        }
        return obj;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        boolean z;
        if (view == null) {
            View view2 = (ChapterAdapterItem) LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.chapterlistitem, viewGroup, false);
            view2.a();
            view = view2;
        } else {
            ChapterAdapterItem chapterAdapterItem = (ChapterAdapterItem) view;
        }
        Mark mark = (Mark) getItem(i);
        view.setText(mark.getDescriptionStr());
        String percentStr = mark.getPercentStr();
        view.setIsDownloaded(true);
        if (this.b == ((long) i)) {
            z = true;
        } else {
            z = false;
        }
        view.setCurChapter(z);
        view.setPurchased(true);
        if (percentStr != null && percentStr.equals("1")) {
            view.setIsFree(true);
        }
        return view;
    }

    public void a(Object obj) {
        if (obj != null && (obj instanceof Mark)) {
            this.a.add((Mark) obj);
        }
    }

    public void a() {
        this.a.clear();
    }

    public void a(Collection<? extends Object> collection) {
        if (collection != null) {
            this.a.addAll(collection);
        }
    }

    public void a(int i) {
        this.b = (long) i;
    }

    public void a(ArrayList<Integer> arrayList) {
        this.c = arrayList;
    }

    public void a(boolean z) {
    }
}
