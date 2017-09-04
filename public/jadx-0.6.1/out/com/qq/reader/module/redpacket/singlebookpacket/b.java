package com.qq.reader.module.redpacket.singlebookpacket;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookInValidCard;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookValidCard;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RedPacketSingleBookListAdapter */
public class b extends BaseAdapter {
    Context a;
    private List<a> b = new ArrayList();
    private d c;

    public b(Context context) {
        this.a = context;
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    private void b() {
        this.b.clear();
    }

    public void a() {
        if (this.c != null) {
            b();
            int i = 0;
            String str = "'";
            for (a aVar : new ArrayList(this.c.d())) {
                this.b.add(aVar);
                aVar.setIndexOnPage(i);
                aVar.setLastCardName(str);
                i++;
                str = aVar.getClass().getSimpleName();
            }
        }
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
        a aVar = (a) getItem(i);
        if (aVar instanceof SingleBookValidCard) {
            return 1;
        }
        return aVar instanceof SingleBookInValidCard ? 2 : 0;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar = (a) getItem(i);
        aVar.setPosition(i);
        if (view == null) {
            view = aVar.inflateView(this.a);
        }
        try {
            aVar.attachView(view);
        } catch (Exception e) {
            f.a("native", "Card attachView  ERROR:  " + aVar.getClass().getName());
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
