package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.view.ClassifyGrid;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ClassifySelectedContainer */
public class a {
    private ClassifyGrid a;
    private ClassifyGrid b;
    private ClassifyGrid c;
    private ClassifyGrid d;
    private a e;
    private a f;
    private a g;
    private a h;
    private JSONObject i;
    private c[] j = new c[4];
    private int[] k = new int[]{-1, -1, -1, -1};
    private Activity l;
    private WeakReferenceHandler m;
    private Bundle n;

    /* compiled from: ClassifySelectedContainer */
    public class a extends BaseAdapter {
        final /* synthetic */ a a;
        private Context b;
        private ArrayList<c> c = new ArrayList();
        private ArrayList<Float> d = new ArrayList();
        private int e = 0;

        public a(a aVar, Context context, int i) {
            this.a = aVar;
            this.b = context;
            this.e = i;
        }

        public void a(c cVar) {
            this.c.add(cVar);
            this.d.add(Float.valueOf(0.0f));
        }

        public c a(int i) {
            return (c) this.c.get(i);
        }

        public void a() {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((c) it.next()).c = false;
            }
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public float b(int i) {
            return ((Float) this.d.get(i)).floatValue();
        }

        public float b() {
            return (float) (com.qq.reader.common.c.a.bU - ao.a(14.0f));
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar = (c) this.c.get(i);
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.classify_selected_item, null);
                view.setLayoutParams(new LayoutParams(-1, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.classify_container_item_height)));
            }
            TextView textView = (TextView) ap.a(view, R.id.classify_item);
            textView.setText(cVar.b);
            if (cVar.c) {
                view.setSelected(true);
            }
            if (view.isSelected()) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.classify_container_selected_textcolor));
                textView.setBackgroundResource(R.drawable.classify_container_item_bg_checked_selector);
            } else {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.classify_container_unselected_textcolor));
                textView.setBackgroundResource(R.drawable.classify_container_item_bg_unchecked_selector);
            }
            int dimensionPixelOffset = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.classify_item_padding);
            textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
            this.d.set(i, Float.valueOf(ao.a(textView.getPaint(), cVar.b) + ((float) ao.a(18.0f))));
            view.setOnClickListener(new b(this.a, this.e, i));
            return view;
        }
    }

    /* compiled from: ClassifySelectedContainer */
    class b implements OnClickListener {
        final /* synthetic */ a a;
        private int b = 0;
        private int c = 0;

        public b(a aVar, int i, int i2) {
            this.a = aVar;
            this.b = i;
            this.c = i2;
        }

        public void onClick(View view) {
            try {
                switch (this.b) {
                    case 0:
                        this.a.j[0] = this.a.e.a(this.c);
                        this.a.e.a();
                        this.a.j[0].c = true;
                        this.a.e.notifyDataSetChanged();
                        this.a.k[0] = this.c;
                        this.a.a(this.a.a);
                        break;
                    case 1:
                        this.a.j[1] = this.a.f.a(this.c);
                        this.a.f.a();
                        this.a.j[1].c = true;
                        this.a.f.notifyDataSetChanged();
                        this.a.k[1] = this.c;
                        this.a.a(this.a.b);
                        break;
                    case 2:
                        this.a.j[2] = this.a.g.a(this.c);
                        this.a.g.a();
                        this.a.j[2].c = true;
                        this.a.g.notifyDataSetChanged();
                        this.a.k[2] = this.c;
                        this.a.a(this.a.c);
                        break;
                    case 3:
                        this.a.j[3] = this.a.h.a(this.c);
                        this.a.h.a();
                        this.a.j[3].c = true;
                        this.a.h.notifyDataSetChanged();
                        this.a.k[3] = this.c;
                        this.a.a(this.a.d);
                        break;
                }
                Message obtainMessage = this.a.m.obtainMessage();
                obtainMessage.obj = this.a.e();
                obtainMessage.what = 10000002;
                this.a.m.sendMessage(obtainMessage);
                view.setSelected(true);
            } catch (Exception e) {
                if (e != null) {
                    com.qq.reader.common.monitor.debug.c.a("Native", "ClassifySelectedContainer onClick" + e.toString());
                }
            }
        }
    }

    /* compiled from: ClassifySelectedContainer */
    public class c {
        public int a;
        public String b;
        public boolean c;
        final /* synthetic */ a d;

        public c(a aVar, int i, String str, boolean z) {
            this.d = aVar;
            this.a = i;
            this.b = str;
            this.c = z;
        }
    }

    public a(Activity activity) {
        this.l = activity;
    }

    private Activity d() {
        return this.l;
    }

    public View a(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.classityselectedlayout, null);
        this.a = (ClassifyGrid) inflate.findViewById(R.id.gridview1);
        this.b = (ClassifyGrid) inflate.findViewById(R.id.gridview2);
        this.c = (ClassifyGrid) inflate.findViewById(R.id.gridview3);
        this.d = (ClassifyGrid) inflate.findViewById(R.id.gridview4);
        this.e = new a(this, d(), 0);
        this.f = new a(this, d(), 1);
        this.g = new a(this, d(), 2);
        this.h = new a(this, d(), 3);
        this.a.setAdapter(this.e);
        this.b.setAdapter(this.f);
        this.c.setAdapter(this.g);
        this.d.setAdapter(this.h);
        f();
        return inflate;
    }

    public void a(WeakReferenceHandler weakReferenceHandler) {
        this.m = weakReferenceHandler;
    }

    private String e() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < 4) {
            try {
                c cVar = this.j[i];
                if (cVar == null) {
                    cVar = a(i).a(0);
                    cVar.c = false;
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("actionId", cVar.a);
                jSONObject2.put("title", cVar.b);
                jSONObject2.put("isSelected", cVar.c);
                jSONArray.put(jSONObject2);
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jSONObject.put("actionIdList", jSONArray);
        return jSONObject.toString();
    }

    public void a(int[] iArr) {
        this.k = iArr;
        this.e.a();
        this.f.a();
        this.g.a();
        this.h.a();
        if (this.e.getCount() > this.k[0] && this.k[0] >= 0) {
            this.j[0] = this.e.a(this.k[0]);
            this.j[0].c = true;
        }
        if (this.f.getCount() > this.k[1] && this.k[1] >= 0) {
            this.j[1] = this.f.a(this.k[1]);
            this.j[1].c = true;
        }
        if (this.g.getCount() > this.k[2] && this.k[2] >= 0) {
            this.j[2] = this.g.a(this.k[2]);
            this.j[2].c = true;
        }
        if (this.h.getCount() > this.k[3] && this.k[3] >= 0) {
            this.j[3] = this.h.a(this.k[3]);
            this.j[3].c = true;
        }
        this.e.notifyDataSetChanged();
        this.f.notifyDataSetChanged();
        this.g.notifyDataSetChanged();
        this.h.notifyDataSetChanged();
    }

    public int[] a() {
        return this.k;
    }

    public void a(Bundle bundle) {
        this.n = bundle;
    }

    public Bundle b() {
        return this.n;
    }

    private void f() {
        Bundle b = b();
        if (b != null) {
            Object string = b.getString("classify_list");
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.i = new JSONObject(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray optJSONArray = this.i.optJSONArray("actionIdList");
                int i = 0;
                while (optJSONArray != null && i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        String str;
                        int optInt = optJSONObject.optInt("actionId");
                        if (i == 0) {
                            str = BookShelfFragment.CATEGORY_ALL;
                        } else {
                            str = optJSONObject.optString("title");
                        }
                        boolean optBoolean = optJSONObject.optBoolean("isSelected");
                        this.e.a(new c(this, optInt, str, optBoolean));
                        if (optBoolean) {
                            this.j[0] = this.e.a(i);
                            this.k[0] = i;
                        }
                    }
                    i++;
                }
            }
        }
        this.f.a(new c(this, -1, BookShelfFragment.CATEGORY_ALL, true));
        this.f.a(new c(this, 0, "免费", false));
        this.f.a(new c(this, 1, "包月", false));
        this.f.a(new c(this, 6, "收费", false));
        this.j[1] = this.f.a(0);
        this.k[1] = 0;
        this.g.a(new c(this, -1, BookShelfFragment.CATEGORY_ALL, true));
        this.g.a(new c(this, 1, "完结", false));
        this.g.a(new c(this, 0, "连载", false));
        this.g.a(new c(this, 2, "节选", false));
        this.j[2] = this.g.a(0);
        this.k[2] = 0;
        this.h.a(new c(this, 6, "按人气", true));
        this.h.a(new c(this, 2, "按最新", false));
        this.h.a(new c(this, 3, "按收藏", false));
        this.h.a(new c(this, 9, "按字数", false));
        this.j[3] = this.h.a(0);
        this.k[3] = 0;
        this.e.notifyDataSetChanged();
        this.f.notifyDataSetChanged();
        this.g.notifyDataSetChanged();
        this.h.notifyDataSetChanged();
    }

    public c[] c() {
        return this.j;
    }

    private void a(ClassifyGrid classifyGrid) {
        int i = 0;
        while (classifyGrid.getChildCount() == classifyGrid.getAdapter().getCount() && i < classifyGrid.getChildCount()) {
            classifyGrid.getChildAt(i).setSelected(false);
            i++;
        }
    }

    private a a(int i) {
        switch (i) {
            case 0:
                return this.e;
            case 1:
                return this.f;
            case 2:
                return this.g;
            case 3:
                return this.h;
            default:
                return null;
        }
    }
}
