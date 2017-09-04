package com.qq.reader.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookstore.search.SearchTabInfo.c;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SearchTabMultiSelectorItem */
public class b {
    private c a;
    private View b;
    private SearchSwitcherLayout c;
    private TextView d;
    private LayoutInflater e;
    private int[] f;
    private int g;
    private int h;
    private List<TextView> i = new ArrayList();
    private List<TextView> j = new ArrayList();
    private List<TextView> k = new ArrayList();
    private a l;

    /* compiled from: SearchTabMultiSelectorItem */
    public interface a {
        void a(com.qq.reader.module.bookstore.search.SearchTabInfo.b bVar);
    }

    private boolean g() {
        return this.k.size() < this.g;
    }

    private boolean h() {
        if (this.k.size() > this.h) {
            return true;
        }
        return false;
    }

    public b(Context context) {
        this.e = LayoutInflater.from(context);
        this.b = this.e.inflate(R.layout.search_tab_third_pop_item_group_layout, null);
        this.c = (SearchSwitcherLayout) this.b.findViewById(R.id.ssl_search_container);
        this.d = (TextView) this.b.findViewById(R.id.tv_title);
        this.b.setTag(this);
    }

    private void a(boolean z, TextView textView) {
        textView.setSelected(z);
        if (z) {
            this.k.add(textView);
        } else {
            this.k.remove(textView);
        }
    }

    public void a(final c cVar) {
        if (cVar != null && cVar.i != null) {
            this.a = cVar;
            this.c.removeAllViews();
            this.j.clear();
            this.k.clear();
            this.i.clear();
            if (TextUtils.isEmpty(cVar.e)) {
                this.d.setVisibility(8);
            } else {
                this.d.setText(cVar.e);
                this.d.setVisibility(0);
            }
            this.g = cVar.d == 0 ? cVar.i.size() : cVar.d;
            this.h = cVar.f;
            for (com.qq.reader.module.bookstore.search.SearchTabInfo.b bVar : cVar.i) {
                int dimension;
                View inflate = this.e.inflate(R.layout.search_tab_third_pop_item_grid_layout, null);
                final TextView textView = (TextView) inflate.findViewById(R.id.cb_switch_item);
                textView.setAlpha(1.0f);
                this.c.setFold(cVar.c, false);
                if (cVar.g) {
                    dimension = (com.qq.reader.common.c.a.bU - (((int) (ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.search_tab_container_padding) - ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.search_tab_item_container_padding))) * 2)) / 3;
                    LayoutParams layoutParams = textView.getLayoutParams();
                    layoutParams.width = -1;
                    textView.setLayoutParams(layoutParams);
                } else {
                    dimension = -2;
                }
                this.c.addView(inflate, new LayoutParams(dimension, -2));
                textView.setText(bVar.b);
                textView.setTag(bVar);
                this.i.add(textView);
                if (bVar.a == cVar.a) {
                    textView.setSelected(true);
                    this.j.add(textView);
                    if (this.l != null) {
                        this.l.a(bVar);
                    }
                }
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b c;

                    public void onClick(View view) {
                        if (view.isSelected()) {
                            if (this.c.h() && !this.c.a((com.qq.reader.module.bookstore.search.SearchTabInfo.b) view.getTag(), false)) {
                                this.c.a(false, textView);
                            }
                        } else if (this.c.g()) {
                            this.c.a(true, textView);
                        } else if (!this.c.i()) {
                            af.a(ReaderApplication.getApplicationImp(), String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.search_max_can_selected), new Object[]{Integer.valueOf(this.c.g), cVar.e}), 0).a();
                            return;
                        } else if (this.c.k.size() > 0) {
                            this.c.a(false, (TextView) this.c.k.remove(0));
                            this.c.a(true, textView);
                        }
                        this.c.j();
                    }
                });
                textView.setAlpha(1.0f);
                if (a(bVar, true)) {
                    textView.setSelected(true);
                    textView.setAlpha(0.5f);
                    this.j.add(textView);
                    if (this.l != null) {
                        this.l.a(bVar);
                    }
                }
            }
            if (cVar.c) {
                this.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.d.isSelected()) {
                            this.a.d.setSelected(false);
                            this.a.c.setFold(true, true);
                            return;
                        }
                        this.a.d.setSelected(true);
                        this.a.c.setFold(false, true);
                    }
                });
            } else {
                this.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            this.c.setLinesListener(new com.qq.reader.widget.SearchSwitcherLayout.a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i <= 1) {
                        this.a.d.setOnClickListener(null);
                        this.a.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                }
            });
        }
    }

    protected boolean a(int i) {
        return i == 0 || i == 6;
    }

    private boolean a(com.qq.reader.module.bookstore.search.SearchTabInfo.b bVar, boolean z) {
        if ((!a(bVar.c) && !z) || this.f == null) {
            return false;
        }
        for (int i : this.f) {
            if (bVar.a == i) {
                return true;
            }
        }
        return false;
    }

    private boolean i() {
        return this.g == 1;
    }

    private void j() {
        if (!i()) {
            Object obj = this.k.size() < this.g ? 1 : null;
            for (TextView textView : this.i) {
                if (!textView.isSelected()) {
                    if (obj != null) {
                        textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.search_tab_grid_item_txt_color_selector));
                    } else {
                        textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                    }
                }
            }
        }
    }

    public View a() {
        return this.b;
    }

    public List<com.qq.reader.module.bookstore.search.SearchTabInfo.b> b() {
        List<com.qq.reader.module.bookstore.search.SearchTabInfo.b> arrayList = new ArrayList();
        for (TextView tag : this.j) {
            arrayList.add((com.qq.reader.module.bookstore.search.SearchTabInfo.b) tag.getTag());
        }
        return arrayList;
    }

    public void c() {
        com.qq.reader.common.monitor.debug.c.e("TAG", "onViewShow");
        this.k.clear();
        this.k.addAll(this.j);
        j();
    }

    public void d() {
        for (TextView selected : this.j) {
            selected.setSelected(true);
        }
    }

    public void e() {
        com.qq.reader.common.monitor.debug.c.e("TAG", "clearTempChecked");
        for (TextView textView : this.k) {
            if (!this.j.contains(textView)) {
                textView.setSelected(false);
            }
        }
        this.k.clear();
        d();
    }

    public void f() {
        this.j.clear();
        this.j.addAll(this.k);
        this.k.clear();
    }

    public void a(int[] iArr) {
        this.f = iArr;
    }

    public void a(a aVar) {
        this.l = aVar;
    }
}
