package com.qq.reader.module.qmessage;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.qmessage.data.d;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MessageAdapter */
public class a extends BaseAdapter {
    Context a;
    private List<MessageBaseCard> b = new ArrayList();
    private List<View> c = new ArrayList();
    private Map<String, View> d = new HashMap();
    private int e = 0;
    private d f;
    private HashMap<String, Integer> g = new HashMap();
    private boolean h = false;
    private SparseArray<Integer> i = new SparseArray();

    public a(Context context) {
        this.a = context;
    }

    public void a(d dVar) {
        this.f = dVar;
        this.d.clear();
    }

    public void a() {
        this.b.clear();
        this.c.clear();
        this.i.clear();
    }

    public boolean b() {
        if (this.f == null) {
            return false;
        }
        int i;
        a();
        String str = "'";
        int i2 = 0;
        for (MessageBaseCard messageBaseCard : new ArrayList(this.f.e())) {
            this.b.add(messageBaseCard);
            messageBaseCard.setIndexOnPage(i2);
            messageBaseCard.setLastCardName(str);
            i2++;
            str = messageBaseCard.getClass().getSimpleName();
        }
        Map hashMap = new HashMap();
        int i3 = 0;
        i2 = 0;
        while (i3 < this.b.size()) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.b.get(i3);
            Integer num = (Integer) hashMap.get(Integer.valueOf(aVar.getResLayoutId()));
            if (num == null) {
                num = new Integer(i2);
                hashMap.put(Integer.valueOf(aVar.getResLayoutId()), num);
                this.i.put(i3, num);
                i = i2 + 1;
            } else {
                this.i.put(i3, num);
                i = i2;
            }
            i3++;
            i2 = i;
        }
        i = this.e;
        if (i2 == 0) {
            i2 = 1;
        }
        this.e = i2;
        if (i == this.e && this.g.equals(hashMap)) {
            return false;
        }
        this.g = new HashMap(hashMap);
        return true;
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

    public int getItemViewType(int i) {
        if (this.i.size() == 0) {
            return 0;
        }
        return ((Integer) this.i.get(i)).intValue();
    }

    public int getViewTypeCount() {
        return this.e == 0 ? 1 : this.e;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) getItem(i);
        aVar.setPosition(i);
        if (view != null) {
            view2 = view;
        } else if (this.c.size() > 0) {
            view2 = (View) this.c.get(i);
        } else {
            view2 = aVar.inflateView(this.a);
        }
        try {
            aVar.attachView(view2);
        } catch (Exception e) {
            f.a("native", "Card attachView  ERROR:  " + aVar.getClass().getName());
            e.printStackTrace();
        }
        return view2;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
