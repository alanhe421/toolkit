package com.qq.reader.module.bookchapter.online;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.bookchapter.b;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: OnlineChapterListAdapter */
public class h extends b {
    private List<OnlineChapter> a = new ArrayList();
    private int b = -1;
    private int c = -1;
    private ArrayList<Integer> d;
    private boolean e = false;

    public int b(int i) {
        return this.b;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public long a(long j) {
        if (this.a != null && this.a.size() > 0 && this.c >= 1 && this.c <= this.a.size()) {
            for (int i = 0; i < this.a.size(); i++) {
                if (((OnlineChapter) this.a.get(i)).getChapterId() == this.c) {
                    this.b = i;
                    break;
                }
            }
        }
        return (long) this.b;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        if (this.a == null || i > getCount() || i < 0) {
            return null;
        }
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        boolean z;
        OnlineChapter onlineChapter = (OnlineChapter) getItem(i);
        if (view == null) {
            view2 = (ChapterAdapterItem) LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.chapterlistitem, viewGroup, false);
            view2.a();
        } else {
            view2 = (ChapterAdapterItem) view;
        }
        view2.setText(onlineChapter.getChapterName());
        String a = v.a("" + onlineChapter.getBookId(), onlineChapter.getChapterId());
        if (a == null) {
            view2.setIsDownloaded(false);
        } else if (new File(a).exists()) {
            view2.setIsDownloaded(true);
        } else {
            view2.setIsDownloaded(false);
        }
        if (i == this.c) {
            z = true;
        } else {
            z = false;
        }
        view2.setCurChapter(z);
        if (c(i + 1)) {
            view2.setPurchased(true);
        } else {
            view2.setPurchased(false);
        }
        view2.setIsFree(onlineChapter.getBooleanIsFree());
        return view2;
    }

    public void a() {
        this.a.clear();
    }

    public void a(Collection<? extends Object> collection) {
        this.a.clear();
        this.a.addAll((List) collection);
    }

    public void a(Object obj) {
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    private boolean c(int i) {
        if (this.e) {
            return true;
        }
        if (this.d == null || this.d.size() == 0) {
            return false;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() == i) {
                return true;
            }
        }
        return false;
    }
}
