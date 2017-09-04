package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: NativeBookStroeAdapter */
public class f extends a {
    Context b;
    protected List<a> c = new ArrayList();
    protected List<View> d = new ArrayList();
    protected Map<String, View> e = new HashMap();
    protected int f = 0;
    protected b g;

    public /* synthetic */ Object getItem(int i) {
        return b(i);
    }

    public f(Context context) {
        super(30);
        this.b = context;
    }

    public void a(b bVar) {
        this.g = bVar;
        this.e.clear();
    }

    public void a() {
        this.c.clear();
        this.d.clear();
    }

    public boolean b() {
        boolean z = false;
        if (this.g != null) {
            a();
            String str = "'";
            int i = 0;
            for (Object obj : new ArrayList(this.g.m())) {
                if (obj.isDataReady()) {
                    this.c.add(obj);
                    if (this.a.a(obj)) {
                        z = true;
                    }
                    obj.setIndexOnPage(i);
                    obj.setLastCardName(str);
                    str = obj.getClass().getSimpleName();
                    i++;
                    if (this.g.a()) {
                        Object obj2 = (View) this.e.get(obj.getCardId());
                        if (obj2 == null) {
                            obj2 = obj.inflateView(this.b, null);
                            this.e.put(obj.getCardId(), obj2);
                        }
                        this.d.add(obj2);
                    }
                }
                str = str;
                i = i;
                z = z;
            }
        }
        return z;
    }

    public int getCount() {
        return this.c.size();
    }

    public a b(int i) {
        return (a) this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a b = b(i);
        c.d("native", "card get view before attach card is " + b);
        b.setPosition(i);
        if (view == null) {
            if (!this.g.a() || this.d.size() <= 0 || i >= this.d.size()) {
                view = b.inflateView(this.b, viewGroup);
            } else {
                view = (View) this.d.get(i);
            }
        }
        Object tag = view.getTag(R.string.obj_tag);
        if (tag instanceof com.qq.reader.module.bookstore.search.b) {
            if (((com.qq.reader.module.bookstore.search.b) tag).isAnimationReady()) {
                ((com.qq.reader.module.bookstore.search.b) tag).doAnimation(view);
            } else if (((com.qq.reader.module.bookstore.search.b) tag).isNeedExchange()) {
                ((com.qq.reader.module.bookstore.search.b) tag).parserExchangeData();
            }
        }
        view.setTag(R.string.obj_tag, b);
        try {
            b.attachView(view);
            c.d("NativeBookStroeAdapter", "notify   get   view");
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("native", "Card attachView  ERROR:  " + b.getClass().getName());
            e.printStackTrace();
        }
        return view;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
