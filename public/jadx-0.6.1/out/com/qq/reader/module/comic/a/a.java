package com.qq.reader.module.comic.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.comic.entity.l;
import com.qq.reader.module.comic.entity.o;
import com.qrcomic.entity.DownloadHistoryDao.Properties;
import com.qrcomic.entity.h;
import com.qrcomic.entity.i;
import com.qrcomic.manager.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* compiled from: ComicDirAdapter */
public class a extends BaseAdapter {
    private final List<o> a = new ArrayList();
    private l b;
    private int c;
    private List<String> d = new ArrayList();
    private boolean e;

    public a(int i) {
        this.c = i - 1;
    }

    public int getCount() {
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
        boolean z = false;
        if (view == null) {
            View view2 = (ChapterAdapterItem) LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.chapterlistitem, viewGroup, false);
            view2.a();
            view = view2;
        } else {
            ChapterAdapterItem chapterAdapterItem = (ChapterAdapterItem) view;
        }
        o oVar = (o) getItem(i);
        if (oVar != null) {
            boolean z2;
            view.setIsDownloaded(a(oVar));
            view.setText(oVar.e());
            if (this.c == i) {
                z2 = true;
            } else {
                z2 = false;
            }
            view.setCurChapter(z2);
            if (a(this.b, oVar, this.d) || a()) {
                z = true;
            }
            view.setPurchased(z);
        }
        return view;
    }

    private boolean a() {
        return this.e;
    }

    public void a(List<o> list) {
        if (list != null && !list.isEmpty()) {
            this.a.clear();
            this.a.addAll(list);
        }
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(List<String> list) {
        this.d.clear();
        if (list != null) {
            this.d.addAll(list);
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    private boolean a(o oVar) {
        try {
            i iVar = (i) b().a().queryBuilder().where(b().a().queryBuilder().and(Properties.d.eq(oVar.d()), Properties.c.eq(oVar.c()), new WhereCondition[0]), new WhereCondition[0]).unique();
            if (iVar == null || iVar.d() != 104) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private h b() {
        return b.a().b().c();
    }

    public boolean a(l lVar, o oVar, List<String> list) {
        if (oVar == null) {
            return false;
        }
        if (lVar != null && (lVar.b() || lVar.a())) {
            return true;
        }
        if (oVar.i()) {
            return true;
        }
        if (list == null || !list.contains(oVar.d())) {
            return false;
        }
        return true;
    }
}
