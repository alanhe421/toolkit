package com.qq.reader.module.question.loader;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.question.card.FamousAuthorSayBaseCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: FamousAuthorSayAdapter */
public class a extends BaseAdapter {
    Context a;
    private List<FamousAuthorSayBaseCard> b = new ArrayList();
    private List<View> c = new ArrayList();
    private Map<String, View> d = new HashMap();
    private int e = 0;
    private com.qq.reader.module.question.a.a f;
    private HashMap<String, Integer> g = new HashMap();
    private boolean h = false;
    private SparseArray<Integer> i = new SparseArray();

    public a(Context context) {
        this.a = context;
    }

    public void a(com.qq.reader.module.question.a.a aVar) {
        this.f = aVar;
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
        for (FamousAuthorSayBaseCard famousAuthorSayBaseCard : new ArrayList(this.f.g())) {
            String simpleName;
            int i3;
            if (famousAuthorSayBaseCard.isDataReady()) {
                this.b.add(famousAuthorSayBaseCard);
                famousAuthorSayBaseCard.setIndexOnPage(i2);
                famousAuthorSayBaseCard.setLastCardName(str);
                simpleName = famousAuthorSayBaseCard.getClass().getSimpleName();
                i3 = i2 + 1;
            } else {
                simpleName = str;
                i3 = i2;
            }
            i2 = i3;
            str = simpleName;
        }
        Map hashMap = new HashMap();
        int i4 = 0;
        i2 = 0;
        while (i4 < this.b.size()) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.b.get(i4);
            Integer num = (Integer) hashMap.get(Integer.valueOf(aVar.getResLayoutId()));
            if (num == null) {
                num = new Integer(i2);
                hashMap.put(Integer.valueOf(aVar.getResLayoutId()), num);
                this.i.put(i4, num);
                i = i2 + 1;
            } else {
                this.i.put(i4, num);
                i = i2;
            }
            i4++;
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
            f.a("FamousAuthorSay", "Card attachView  ERROR:  " + aVar.getClass().getName());
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
