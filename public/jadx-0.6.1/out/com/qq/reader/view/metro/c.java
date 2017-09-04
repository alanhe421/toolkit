package com.qq.reader.view.metro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MetroNormalAdapter */
public class c extends BaseAdapter {
    protected Context a;
    protected List<MetroItem> b;
    protected d c;
    protected String d = "";
    private int e = 4;

    /* compiled from: MetroNormalAdapter */
    private class a {
        ArrayList<MetroItem> a = new ArrayList();
        int b;
        final /* synthetic */ c c;

        public a(c cVar, int i) {
            this.c = cVar;
            this.b = i;
        }

        a a(MetroItem metroItem) {
            this.a.add(metroItem);
            return this;
        }
    }

    public c(Context context, int i) {
        this.a = context;
        int dimension = (int) this.a.getResources().getDimension(R.dimen.metro_padding);
        this.e = ((i - dimension) - dimension) / ((int) this.a.getResources().getDimension(R.dimen.metro_h_w));
        if (this.e == 0) {
            this.e = 1;
        }
    }

    public void a(List<MetroItem> list, String str) {
        this.b = list;
        this.d = str;
    }

    public int getCount() {
        int size;
        synchronized (this.b) {
            size = this.b.size() / this.e;
            if (this.b.size() % this.e == 0) {
            } else {
                size++;
            }
        }
        return size;
    }

    public Object getItem(int i) {
        Object obj;
        synchronized (this.b) {
            if (i >= this.b.size() || i < 0) {
                obj = null;
            } else {
                obj = this.b.get(i);
            }
        }
        return obj;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (view == null) {
            linearLayout = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.category_dialog_linear, viewGroup, false);
        } else {
            linearLayout = (LinearLayout) view;
        }
        return a(linearLayout, a(i));
    }

    protected Drawable a(MetroItem metroItem) {
        return this.a.getResources().getDrawable(R.drawable.metro_system_item_selected);
    }

    private void a(MetroItemView metroItemView, int i) {
        metroItemView.setTextViewBackground(null);
        metroItemView.setSelected(false);
    }

    private View a(LinearLayout linearLayout, a aVar) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        if (aVar != null) {
            Iterator it = aVar.a.iterator();
            int i = 0;
            while (it.hasNext()) {
                MetroItemView metroItemView;
                MetroItem metroItem = (MetroItem) it.next();
                View childAt = linearLayout.getChildAt(i);
                if (childAt == null) {
                    metroItemView = (MetroItemView) LayoutInflater.from(this.a).inflate(R.layout.metro_item, linearLayout, false);
                    metroItemView.a();
                    metroItemView.setLayoutParams(layoutParams);
                    linearLayout.addView(metroItemView);
                } else {
                    metroItemView = (MetroItemView) childAt;
                }
                metroItemView.setPosition((aVar.b * this.e) + i);
                a(metroItem, metroItemView);
                i++;
            }
            for (int i2 = i; i2 < this.e; i2++) {
                MetroItemView metroItemView2;
                View childAt2 = linearLayout.getChildAt(i2);
                if (childAt2 == null) {
                    metroItemView2 = (MetroItemView) LayoutInflater.from(this.a).inflate(R.layout.metro_item, linearLayout, false);
                    metroItemView2.a();
                    metroItemView2.setLayoutParams(layoutParams);
                    linearLayout.addView(metroItemView2);
                } else {
                    metroItemView2 = (MetroItemView) childAt2;
                }
                int i3 = (aVar.b * this.e) + i;
                metroItemView2.setPosition(i3);
                a(metroItemView2, i3);
                metroItemView2.setDisplayName("");
            }
        }
        return linearLayout;
    }

    protected void a(MetroItem metroItem, MetroItemView metroItemView) {
        metroItemView.setTextViewBackground(a(metroItem));
        metroItemView.setDisplayName(metroItem.getDisplayName());
        metroItemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c.onClick((MetroItem) this.a.b.get(((Integer) view.getTag()).intValue()));
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.qq.reader.view.metro.c.a a(int r6) {
        /*
        r5 = this;
        r4 = r5.b;
        monitor-enter(r4);
        r0 = r5.getCount();	 Catch:{ all -> 0x0038 }
        if (r6 < r0) goto L_0x000c;
    L_0x0009:
        r0 = 0;
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
    L_0x000b:
        return r0;
    L_0x000c:
        r1 = new com.qq.reader.view.metro.c$a;	 Catch:{ all -> 0x0038 }
        r1.<init>(r5, r6);	 Catch:{ all -> 0x0038 }
        r0 = r5.e;	 Catch:{ all -> 0x0038 }
        r2 = r6 * r0;
        r0 = 0;
        r3 = r2;
        r2 = r0;
    L_0x0018:
        r0 = r5.b;	 Catch:{ all -> 0x0038 }
        r0 = r0.size();	 Catch:{ all -> 0x0038 }
        if (r3 >= r0) goto L_0x0035;
    L_0x0020:
        r0 = r5.e;	 Catch:{ all -> 0x0038 }
        if (r2 >= r0) goto L_0x0035;
    L_0x0024:
        r0 = r5.b;	 Catch:{ all -> 0x0038 }
        r0 = r0.get(r3);	 Catch:{ all -> 0x0038 }
        r0 = (com.qq.reader.view.metro.MetroItem) r0;	 Catch:{ all -> 0x0038 }
        r1.a(r0);	 Catch:{ all -> 0x0038 }
        r3 = r3 + 1;
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0018;
    L_0x0035:
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
        r0 = r1;
        goto L_0x000b;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.metro.c.a(int):com.qq.reader.view.metro.c$a");
    }

    public void a(d dVar) {
        this.c = dVar;
    }
}
