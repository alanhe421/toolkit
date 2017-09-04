package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardRankBoardBook;
import com.qq.reader.module.bookstore.qnative.item.aa;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.widget.PinnedHeaderListView;
import com.qq.reader.widget.PinnedHeaderListView.a;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: NativeBookStoreRankBStickHeaderAdapter */
public class e extends BaseAdapter implements OnScrollListener, SectionIndexer, a {
    public LinkedHashMap<Integer, Integer> a;
    Context b;
    List<s> c;
    private int d = -1;
    private ListCardRankBoardBook e;
    private com.qq.reader.module.bookstore.qnative.c.a f = null;
    private boolean g = false;

    public e(Context context, ListCardRankBoardBook listCardRankBoardBook, boolean z, LinkedHashMap<Integer, Integer> linkedHashMap) {
        this.b = context;
        this.e = listCardRankBoardBook;
        this.c = this.e.getItemList();
        this.g = z;
        this.a = linkedHashMap;
        this.e = listCardRankBoardBook;
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.f = aVar;
    }

    public Object getItem(int i) {
        if (this.c == null) {
            return null;
        }
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(this.e.getCardItemLayoutId(), null);
        }
        final aa aaVar = (aa) getItem(i);
        int sectionForPosition = getSectionForPosition(i);
        if (aaVar.h == 0 || getPositionForSection(sectionForPosition) != i) {
            view.findViewById(R.id.rankboard_head).setVisibility(8);
        } else {
            view.findViewById(R.id.rankboard_head).setVisibility(0);
            ((TextView) view.findViewById(R.id.header_name)).setText(String.valueOf(aaVar.h));
            Map hashMap = new HashMap();
            hashMap.put("rankboard", "abtest_B");
            hashMap.put("year", String.valueOf(aaVar.h));
            i.a("event_B222", hashMap, this.f.getFromActivity());
        }
        aaVar.a(view, i, this.g);
        if (this.f != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e c;

                public void onClick(View view) {
                    if (aaVar != null) {
                        aaVar.a(this.c.f, ((Integer) this.c.getSections()[this.c.getSectionForPosition(i)]).intValue());
                    }
                }
            });
        }
        return view;
    }

    public Object[] getSections() {
        return this.a.keySet().toArray();
    }

    public int getPositionForSection(int i) {
        int i2 = 0;
        for (Entry entry : this.a.entrySet()) {
            if (i2 == i) {
                return ((Integer) entry.getValue()).intValue();
            }
            i2++;
        }
        return 0;
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        for (Entry value : this.a.entrySet()) {
            if (((Integer) value.getValue()).intValue() > i) {
                return i2 - 1;
            }
            i2++;
        }
        return i2 - 1;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) absListView).a(i);
        }
    }

    public int a(int i) {
        if (i < 0 || (this.d != -1 && this.d == i)) {
            return 0;
        }
        this.d = -1;
        int positionForSection = getPositionForSection(getSectionForPosition(i) + 1);
        if (positionForSection == -1 || i != positionForSection - 1) {
            return 1;
        }
        return 2;
    }

    public int b(int i) {
        if (this.a == null || !this.a.containsKey(Integer.valueOf(i))) {
            return -1;
        }
        return ((Integer) this.a.get(Integer.valueOf(i))).intValue();
    }

    public void a(View view, int i, int i2) {
        ((TextView) view.findViewById(R.id.header_name)).setText("" + ((Integer) getSections()[getSectionForPosition(i)]).intValue());
    }
}
