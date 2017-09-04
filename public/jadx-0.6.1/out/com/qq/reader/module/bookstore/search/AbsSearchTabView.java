package com.qq.reader.module.bookstore.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.search.SearchTabInfo.SearchActionTagLv3InitialDataModel;
import com.qq.reader.module.bookstore.search.SearchTabInfo.a;
import com.qq.reader.widget.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbsSearchTabView extends RelativeLayout {
    SparseArray<PopupWindow> a;
    SparseArray<List<TextView>> b;
    SparseArray<List<b>> c;
    String d;
    LayoutInflater e;
    View f;
    c g;
    final int h;
    final int i;
    final int j;
    final int k;
    TextView[] l;
    ViewGroup[] m;
    HashMap<Integer, List<SearchTabInfo.b>> n;
    protected SearchTabInfo o;
    boolean p;
    private int q;
    private int[] r;
    private int s;
    private ArrayList<SearchActionTagLv3InitialDataModel> t;

    public abstract String a(List<SearchTabInfo.b> list);

    protected abstract void a(int i, boolean z);

    protected abstract OnClickListener b(int i);

    protected abstract OnClickListener c(int i);

    protected abstract OnClickListener d(int i);

    protected abstract void e(int i);

    protected abstract void g(int i);

    protected abstract Integer getExceptId();

    protected abstract void h(int i);

    public abstract OnClickListener j(int i);

    public abstract void k(int i);

    public void setInitSelectedItems(ArrayList<SearchActionTagLv3InitialDataModel> arrayList) {
        this.t = arrayList;
    }

    public void setUseLocation(int i) {
        this.s = i;
        c.e(getClass().getSimpleName(), "setUseLocation = " + i);
    }

    public void setMustSelectedId(int i) {
        this.r = new int[]{i};
        c.e(getClass().getSimpleName(), "setMustSelectedId = " + i);
    }

    public void setMustSelectedIds(int[] iArr) {
        this.r = iArr;
    }

    public void a(int i) {
        int i2 = 0;
        if (this.r != null) {
            int length = this.r.length;
            int[] iArr = new int[(length + 1)];
            while (i2 < this.r.length) {
                iArr[i2] = this.r[i2];
                i2++;
            }
            iArr[length] = i;
            this.r = iArr;
            return;
        }
        this.r = new int[1];
        this.r[0] = i;
    }

    public AbsSearchTabView(Context context) {
        super(context);
        this.a = new SparseArray();
        this.b = new SparseArray();
        this.c = new SparseArray();
        this.q = 0;
        this.g = new c(this) {
            final /* synthetic */ AbsSearchTabView a;

            {
                this.a = r1;
            }

            public void a(String str) {
                c.e("ZQN", str);
            }

            public void a(int i, int i2) {
            }
        };
        this.h = 0;
        this.i = 1;
        this.j = 2;
        this.k = 0;
        this.l = new TextView[3];
        this.m = new ViewGroup[3];
        this.n = new HashMap();
        this.p = false;
    }

    private void a(Context context) {
        this.e = LayoutInflater.from(context);
        this.f = this.e.inflate(R.layout.search_tab_layout, null);
        ViewGroup viewGroup = (ViewGroup) this.f.findViewById(R.id.ll_tab_comprehensive);
        viewGroup.setOnClickListener(d(0));
        this.m[0] = viewGroup;
        viewGroup.setTag(Integer.valueOf(0));
        viewGroup = (ViewGroup) this.f.findViewById(R.id.ll_tab_collect);
        viewGroup.setOnClickListener(c(1));
        this.m[1] = viewGroup;
        viewGroup.setTag(Integer.valueOf(1));
        viewGroup = (ViewGroup) this.f.findViewById(R.id.ll_tab_select);
        viewGroup.setOnClickListener(b(2));
        this.m[2] = viewGroup;
        viewGroup.setTag(Integer.valueOf(2));
        this.l[0] = (TextView) this.f.findViewById(R.id.tv_tab_comprehensive);
        this.l[1] = (TextView) this.f.findViewById(R.id.tv_tab_collect);
        this.l[2] = (TextView) this.f.findViewById(R.id.tv_tab_select);
        addView(this.f, new LayoutParams(-1, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics())));
    }

    public void a(SearchTabInfo searchTabInfo) {
        this.o = searchTabInfo;
        if (searchTabInfo != null && searchTabInfo.a != null) {
            int i;
            if (searchTabInfo.a.size() > this.m.length) {
                searchTabInfo.a.subList(this.m.length, searchTabInfo.a.size()).clear();
            }
            for (i = 0; i < this.a.size(); i++) {
                PopupWindow popupWindow = (PopupWindow) this.a.valueAt(i);
                if (popupWindow.isShowing() && (getContext() instanceof Activity) && !((Activity) getContext()).isFinishing()) {
                    popupWindow.dismiss();
                }
            }
            this.a.clear();
            this.b.clear();
            this.n.clear();
            this.c.clear();
            i = 0;
            for (a aVar : searchTabInfo.a) {
                PopupWindow popupWindow2;
                View inflate;
                final PopupWindow bVar;
                switch (aVar.c) {
                    case 0:
                        a(i, aVar);
                        break;
                    case 1:
                        inflate = this.e.inflate(R.layout.search_tab_first_pop_list_layout, null);
                        bVar = new com.qq.reader.view.b(inflate, -1, -1);
                        inflate.setOnTouchListener(new OnTouchListener(this) {
                            final /* synthetic */ AbsSearchTabView c;

                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                c.e("onTouch", motionEvent.getAction() + "");
                                if (motionEvent.getAction() == 1 && bVar.isShowing() && !this.c.a()) {
                                    bVar.dismiss();
                                    view.requestFocus();
                                    this.c.k(i);
                                }
                                return true;
                            }
                        });
                        a((ViewGroup) inflate.findViewById(R.id.ll_item_container), aVar, i);
                        popupWindow2 = bVar;
                        break;
                    case 2:
                        inflate = this.e.inflate(R.layout.search_tab_third_pop_layout, null);
                        View findViewById = inflate.findViewById(R.id.btn_ok);
                        findViewById.setOnClickListener(j(i));
                        findViewById.setTag(Integer.valueOf(i));
                        bVar = new com.qq.reader.view.b(inflate, -1, -1);
                        b((ViewGroup) inflate.findViewById(R.id.ll_item_container), aVar, i);
                        popupWindow2 = bVar;
                        break;
                }
                popupWindow2 = null;
                if (i < this.l.length) {
                    this.l[i].setText(String.valueOf(aVar.d));
                }
                if (popupWindow2 != null) {
                    popupWindow2.setBackgroundDrawable(new ColorDrawable(0));
                    popupWindow2.setTouchable(true);
                    popupWindow2.setOutsideTouchable(false);
                    this.a.put(i, popupWindow2);
                }
                if (aVar.c == 0) {
                    this.l[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                if (aVar.a) {
                    setSelectedItem(i);
                }
                this.m[i].setTag(R.string.obj_tag, aVar);
                this.m[i].setVisibility(0);
                i++;
            }
        }
    }

    private void a(int i, a aVar) {
        this.n.put(Integer.valueOf(i), new ArrayList());
    }

    private void a(ViewGroup viewGroup, a aVar, final int i) {
        List arrayList = new ArrayList();
        this.b.put(i, arrayList);
        this.n.put(Integer.valueOf(i), new ArrayList());
        if (aVar.b != null && aVar.b.size() > 0) {
            SearchTabInfo.c cVar = (SearchTabInfo.c) aVar.b.get(0);
            for (int i2 = 0; i2 < cVar.i.size(); i2++) {
                SearchTabInfo.b bVar = (SearchTabInfo.b) cVar.i.get(i2);
                if (bVar != null) {
                    View inflate = this.e.inflate(R.layout.search_tab_first_pop_item_layout, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.tv_comprehensive);
                    arrayList.add(textView);
                    textView.setText(String.valueOf(bVar.b));
                    textView.setTag(bVar);
                    textView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AbsSearchTabView c;

                        public void onClick(View view) {
                            this.c.a(i2, true, i);
                        }
                    });
                    viewGroup.addView(inflate);
                    if (bVar.a == cVar.a) {
                        a(i2, false, i);
                    }
                }
            }
        }
    }

    private void b(ViewGroup viewGroup, a aVar, final int i) {
        this.c.put(i, new ArrayList());
        viewGroup.removeAllViews();
        this.n.put(Integer.valueOf(i), new ArrayList());
        for (SearchTabInfo.c cVar : aVar.b) {
            if (cVar.i != null && cVar.i.size() > 0) {
                b bVar = new b(getContext());
                bVar.a(new b.a(this) {
                    final /* synthetic */ AbsSearchTabView b;

                    public void a(SearchTabInfo.b bVar) {
                        this.b.l[i].setTextColor(this.b.getResources().getColor(R.color.search_tab_txt_selected_color));
                        this.b.l[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_tab_tv_drawable_selected_selector, 0);
                        ((List) this.b.n.get(Integer.valueOf(i))).add(bVar);
                    }
                });
                ((List) this.c.get(i)).add(bVar);
                if (this.t != null && this.t.size() > 0) {
                    for (int i2 = 0; i2 < this.t.size(); i2++) {
                        SearchActionTagLv3InitialDataModel searchActionTagLv3InitialDataModel = (SearchActionTagLv3InitialDataModel) this.t.get(i2);
                        if (searchActionTagLv3InitialDataModel.selectedSubId == cVar.h) {
                            bVar.a(searchActionTagLv3InitialDataModel.selectedItemIds);
                            if (searchActionTagLv3InitialDataModel.itemShouldInvisible) {
                                bVar.a().setVisibility(8);
                            }
                        }
                    }
                }
                if (this.s == cVar.h) {
                    bVar.a(this.r);
                }
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                bVar.a(cVar);
                viewGroup.addView(bVar.a(), layoutParams);
            }
        }
    }

    public AbsSearchTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new SparseArray();
        this.b = new SparseArray();
        this.c = new SparseArray();
        this.q = 0;
        this.g = /* anonymous class already generated */;
        this.h = 0;
        this.i = 1;
        this.j = 2;
        this.k = 0;
        this.l = new TextView[3];
        this.m = new ViewGroup[3];
        this.n = new HashMap();
        this.p = false;
        a(context);
    }

    public AbsSearchTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new SparseArray();
        this.b = new SparseArray();
        this.c = new SparseArray();
        this.q = 0;
        this.g = /* anonymous class already generated */;
        this.h = 0;
        this.i = 1;
        this.j = 2;
        this.k = 0;
        this.l = new TextView[3];
        this.m = new ViewGroup[3];
        this.n = new HashMap();
        this.p = false;
        a(context);
    }

    public AbsSearchTabView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new SparseArray();
        this.b = new SparseArray();
        this.c = new SparseArray();
        this.q = 0;
        this.g = /* anonymous class already generated */;
        this.h = 0;
        this.i = 1;
        this.j = 2;
        this.k = 0;
        this.l = new TextView[3];
        this.m = new ViewGroup[3];
        this.n = new HashMap();
        this.p = false;
        a(context);
    }

    protected void setSelectedItem(int i) {
        int i2 = 0;
        while (i2 < this.m.length) {
            boolean z;
            int color;
            ViewGroup viewGroup = this.m[i2];
            if (i == i2) {
                z = true;
            } else {
                z = false;
            }
            viewGroup.setSelected(z);
            TextView textView = this.l[i2];
            if (textView.getCompoundDrawables()[2] != null) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i == i2 ? R.drawable.search_tab_tv_drawable_selected_selector : R.drawable.search_tab_tv_drawable_selector, 0);
            }
            if (i == i2) {
                color = getResources().getColor(R.color.search_tab_txt_selected_color);
            } else {
                color = getResources().getColor(R.color.search_tab_txt_def_color);
            }
            textView.setTextColor(color);
            i2++;
        }
    }

    private void l(int i) {
        PopupWindow popupWindow = (PopupWindow) this.a.get(i);
        if (popupWindow != null) {
            View findViewById = popupWindow.getContentView().findViewById(R.id.sv_item_container);
            if (findViewById instanceof ScrollView) {
                findViewById.scrollTo(0, 0);
            }
        }
    }

    protected void f(int i) {
        for (int i2 = 0; i2 < this.l.length; i2++) {
            if (i2 != i) {
                this.l[i2].setSelected(false);
                PopupWindow popupWindow = (PopupWindow) this.a.get(i2);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    a(i2, false);
                }
            }
        }
    }

    protected void a(int i, boolean z, int i2) {
        List list = (List) this.b.get(i2);
        if (list != null) {
            h(i2);
            SearchTabInfo.b bVar = (SearchTabInfo.b) ((TextView) list.get(i)).getTag();
            if (!(this.g == null || bVar == null)) {
                ((List) this.n.get(Integer.valueOf(i2))).clear();
                ((List) this.n.get(Integer.valueOf(i2))).add(bVar);
                if (z) {
                    this.g.a(a(getSelectedData()));
                }
                g(i2);
            }
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (i3 == i) {
                    ((TextView) list.get(i3)).setSelected(true);
                    Map hashMap;
                    if (z && getInfoType() == 4) {
                        hashMap = new HashMap();
                        hashMap.put("order", ((TextView) list.get(i3)).getText().toString());
                        i.a("event_F302", hashMap, ReaderApplication.getApplicationImp());
                    } else if (z && getInfoType() == 5) {
                        hashMap = new HashMap();
                        hashMap.put("order", ((TextView) list.get(i3)).getText().toString());
                        i.a("event_B287", hashMap, ReaderApplication.getApplicationImp());
                    }
                } else {
                    ((TextView) list.get(i3)).setSelected(false);
                }
            }
            if (bVar != null) {
                this.l[i2].setText(bVar.b);
            }
            if (z) {
                PopupWindow popupWindow = (PopupWindow) this.a.get(i2);
                this.l[i2].setSelected(false);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        }
    }

    protected List<SearchTabInfo.b> getSelectedData() {
        List<SearchTabInfo.b> arrayList = new ArrayList();
        for (Entry entry : this.n.entrySet()) {
            List list = (List) entry.getValue();
            if (((Integer) entry.getKey()) != getExceptId()) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    protected void i(int i) {
        if (getWindowVisibility() != 8) {
            PopupWindow popupWindow = (PopupWindow) this.a.get(i);
            if (popupWindow == null) {
                return;
            }
            if (!popupWindow.isShowing() || a()) {
                try {
                    if (!a()) {
                        a(i, true);
                        l(i);
                        if (VERSION.SDK_INT < 24) {
                            popupWindow.showAsDropDown(this);
                        } else {
                            int[] iArr = new int[2];
                            getLocationOnScreen(iArr);
                            int height = iArr[1] + getHeight();
                            popupWindow.setHeight(getResources().getDisplayMetrics().heightPixels - height);
                            popupWindow.showAtLocation(this, 0, 0, height);
                        }
                        if (getInfoType() == 5) {
                            i.a("event_B291", null, ReaderApplication.getApplicationImp());
                        }
                        this.l[i].setSelected(true);
                        this.p = false;
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            popupWindow.dismiss();
            this.l[i].setSelected(false);
            a(i, false);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setSearchTabListener(c cVar) {
        if (cVar != null) {
            this.g = cVar;
        }
    }

    public boolean a(Activity activity) {
        if (activity != null && activity.isFinishing() && !this.p) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.a.size(); i++) {
            int keyAt = this.a.keyAt(i);
            PopupWindow popupWindow = (PopupWindow) this.a.get(keyAt);
            boolean isShowing = popupWindow.isShowing();
            if (isShowing) {
                if (!(popupWindow.getContentView().findViewById(R.id.btn_ok) == null || this.g == null)) {
                    a(keyAt, false);
                }
                popupWindow.dismiss();
                this.l[keyAt].setSelected(false);
            }
            z |= isShowing;
        }
        this.p = true;
        return z;
    }

    private boolean a() {
        Activity activity;
        Context context = getContext();
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            if (context instanceof ContextThemeWrapper) {
                context = ((ContextThemeWrapper) context).getBaseContext();
                if (context instanceof Activity) {
                    activity = (Activity) context;
                }
            }
            activity = null;
        }
        if (activity != null) {
            return activity.isFinishing();
        }
        return false;
    }

    public void setInfoType(int i) {
        this.q = i;
    }

    public int getInfoType() {
        return this.q;
    }

    public void setActionTag(String str) {
        this.d = str;
    }
}
