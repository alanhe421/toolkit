package com.qq.reader.liveshow.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.b.j;
import com.qq.reader.liveshow.model.im.viewdata.b;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.d;
import java.util.List;

/* compiled from: ChatMsgListAdapter */
public class a extends BaseAdapter implements OnScrollListener {
    private static String a = "ChatMsgListAdapter";
    private List<b> b;
    private Context c;
    private ListView d;
    private boolean e = false;
    private boolean f = false;
    private j g;

    public a(Context context, ListView listView, List<b> list, j jVar) {
        this.c = context;
        this.d = listView;
        this.b = list;
        this.g = jVar;
        this.d.setOnScrollListener(this);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar;
        b bVar = (b) this.b.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(bVar.a(), null);
            dVar = new d(view);
            view.setTag(e.tag_first, dVar);
        } else {
            dVar = (d) view.getTag(e.tag_first);
        }
        bVar.a(dVar, i);
        return view;
    }

    private void a() {
        while (this.b.size() > 200) {
            this.b.remove(0);
        }
    }

    public void notifyDataSetChanged() {
        SxbLog.a(a, "notifyDataSetChanged->scroll: " + this.f);
        if (this.f) {
            super.notifyDataSetChanged();
            return;
        }
        a();
        super.notifyDataSetChanged();
        this.d.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                int count = this.a.d.getCount() - 1;
                if (count - this.a.d.getFirstVisiblePosition() < 10) {
                    this.a.d.smoothScrollToPosition(count);
                } else {
                    this.a.d.setSelection(count);
                }
            }
        });
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                SxbLog.e("list", "SCROLL_STATE_IDLE");
                this.f = false;
                if (this.e && this.d.getLastVisiblePosition() == this.d.getCount() - 1) {
                    this.g.b();
                    this.e = false;
                    return;
                }
                return;
            case 1:
                SxbLog.e("list", "SCROLL_STATE_TOUCH_SCROLL");
                this.g.a();
                this.e = true;
                this.f = true;
                return;
            case 2:
                SxbLog.e("list", "SCROLL_STATE_FLING");
                return;
            default:
                return;
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        SxbLog.e(a, "onScroll f=" + i + " v=" + i2 + " t=" + i3);
    }
}
