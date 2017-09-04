package com.qq.reader.module.bookchapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BookmarkListAdapter */
public class a extends BaseAdapter {
    private Context a;
    private List<Mark> b = new ArrayList();
    private SparseArray<List<Mark>> c;
    private com.qq.reader.module.bookstore.qweb.fragment.a d;

    public a(Context context) {
        this.a = context;
    }

    public void a(com.qq.reader.module.bookstore.qweb.fragment.a aVar) {
        this.d = aVar;
    }

    public void a(List<Mark> list) {
        if (list != null && list.size() != 0) {
            this.b = list;
            if (this.c == null) {
                this.c = new SparseArray();
            }
            for (Mark mark : list) {
                int chapterId = ((UserMark) mark).getChapterId();
                List arrayList;
                if (this.c.indexOfKey(chapterId) < 0) {
                    arrayList = new ArrayList();
                    arrayList.add(mark);
                    this.c.put(chapterId, arrayList);
                } else {
                    arrayList = (List) this.c.get(chapterId);
                    arrayList.add(mark);
                    this.c.put(chapterId, arrayList);
                }
            }
        }
    }

    public void a(Mark mark) {
        int chapterId = ((UserMark) mark).getChapterId();
        List list = (List) this.c.get(chapterId);
        list.remove(mark);
        if (list.size() > 0) {
            this.c.put(chapterId, list);
        } else {
            this.c.remove(chapterId);
        }
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        int size;
        synchronized (this.c) {
            size = this.c.size();
        }
        return size;
    }

    public Object getItem(int i) {
        if (i > getCount() || i < 0) {
            return null;
        }
        Object obj;
        synchronized (this.c) {
            obj = this.c.get(this.c.keyAt(i));
        }
        return obj;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater from = LayoutInflater.from(this.a);
        View inflate = from.inflate(R.layout.bookmarklistgroup, null);
        LinearLayout linearLayout = (LinearLayout) inflate;
        List<Mark> list = (List) getItem(i);
        ((TextView) inflate.findViewById(R.id.title)).setText("第" + this.c.keyAt(i) + "章");
        for (final Mark mark : list) {
            View inflate2 = from.inflate(R.layout.bookmarklistitem, null);
            TextView textView = (TextView) inflate2.findViewById(R.id.content);
            TextView textView2 = (TextView) inflate2.findViewById(R.id.percent);
            ((TextView) inflate2.findViewById(R.id.percentTime)).setText(ao.j(mark.getOperateTime() / 1000));
            textView2.setText(mark.getPercentStr());
            textView.setText(mark.getDescriptionStr());
            inflate2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.d.onClick(mark);
                }
            });
            inflate2.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ a b;

                public boolean onLongClick(View view) {
                    this.b.d.onLongClick(mark);
                    return false;
                }
            });
            linearLayout.addView(inflate2);
        }
        return linearLayout;
    }
}
