package com.qq.reader.view.web;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.k;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OnlineHistoryAdapter */
public class g extends BaseAdapter {
    private List<com.qq.reader.common.db.handle.r.a> a = new ArrayList();
    private Context b;

    /* compiled from: OnlineHistoryAdapter */
    private class a {
        final /* synthetic */ g a;
        private TextView b;
        private TextView c;
        private int d;
        private ImageView e;
        private TextView f;

        public a(g gVar, View view) {
            this.a = gVar;
            this.b = (TextView) view.findViewById(R.id.online_bookname);
            this.c = (TextView) view.findViewById(R.id.online_time);
            this.e = (ImageView) view.findViewById(R.id.history_type_icon);
            this.f = (TextView) view.findViewById(R.id.history_type_tv);
        }

        public void a(String str) {
            this.b.setText(str);
            this.b.requestLayout();
        }

        public void a(int i) {
            this.d = i;
            if (this.d == 2) {
                this.e.setVisibility(0);
                this.e.setBackgroundResource(R.drawable.listen_topic_icon);
                this.f.setVisibility(8);
            } else if (this.d == 1) {
                this.e.setVisibility(8);
                this.e.setBackgroundResource(R.drawable.listen_topic_icon);
                this.f.setVisibility(0);
            } else if (this.d == 3) {
                this.e.setVisibility(0);
                this.e.setBackgroundResource(R.drawable.comic_icon);
                this.f.setVisibility(8);
            } else {
                this.e.setVisibility(8);
                this.f.setVisibility(8);
            }
        }

        public void b(String str) {
            this.c.setText(str);
        }
    }

    public g(Activity activity) {
        this.b = activity;
    }

    public void a() {
        this.a = r.a().c();
    }

    public int getCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.a != null) {
            return this.a.get(i);
        }
        return null;
    }

    public boolean a(Object obj) {
        try {
            boolean z;
            com.qq.reader.common.db.handle.r.a aVar = (com.qq.reader.common.db.handle.r.a) obj;
            if (aVar != null) {
                r.a().a(aVar.b, aVar.a);
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return z;
            }
            try {
                if (this.a == null || obj == null) {
                    return z;
                }
                this.a.remove(obj);
                return z;
            } catch (Exception e) {
                f.a("OnlineHistoryAdapter", e.toString(), e);
                return z;
            }
        } catch (Exception e2) {
            f.a("OnlineHistoryAdapter", e2.toString(), e2);
            return false;
        }
    }

    public boolean b() {
        try {
            r.a().b();
            if (this.a != null) {
                this.a.clear();
            }
            return true;
        } catch (Exception e) {
            f.a("OnlineHistoryAdapter delAllItem", e.toString(), e);
            return false;
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.onlinehistoryitem, viewGroup, false);
            a aVar2 = new a(this, view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        com.qq.reader.common.db.handle.r.a aVar3 = (com.qq.reader.common.db.handle.r.a) getItem(i);
        aVar.a(aVar3.c);
        aVar.a(aVar3.b);
        aVar.b(k.b(aVar3.d));
        if (b.l && view != null) {
            view.setBackgroundResource(R.drawable.list_item_trans_bg);
        }
        return view;
    }
}
