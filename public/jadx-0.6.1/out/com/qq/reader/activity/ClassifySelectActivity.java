package com.qq.reader.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qweb.channel.OtherGridView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassifySelectActivity extends ReaderBaseActivity implements OnClickListener, OnItemClickListener {
    private TextView a;
    private TextView b;
    private OtherGridView c;
    private OtherGridView d;
    private OtherGridView e;
    private OtherGridView f;
    private a g;
    private a h;
    private a i;
    private a j;
    private JSONObject k;
    private b[] l = new b[4];

    private class a extends BaseAdapter {
        final /* synthetic */ ClassifySelectActivity a;
        private Context b;
        private ArrayList<b> c = new ArrayList();

        public a(ClassifySelectActivity classifySelectActivity, Context context) {
            this.a = classifySelectActivity;
            this.b = context;
        }

        public void a(b bVar) {
            this.c.add(bVar);
        }

        public b a(int i) {
            return (b) this.c.get(i);
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

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar = (b) this.c.get(i);
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.classify_selected_item, null);
            }
            TextView textView = (TextView) ap.a(view, R.id.classify_item);
            textView.setText(bVar.b);
            if (view.isSelected()) {
                textView.setTextColor(Color.parseColor("#5986b3"));
            } else {
                textView.setTextColor(Color.parseColor("#000000"));
            }
            return view;
        }
    }

    public class b {
        public int a;
        public String b;
        public boolean c;
        final /* synthetic */ ClassifySelectActivity d;

        public b(ClassifySelectActivity classifySelectActivity, int i, String str, boolean z) {
            this.d = classifySelectActivity;
            this.a = i;
            this.b = str;
            this.c = z;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.classityselectedlayout_1);
        this.c = (OtherGridView) findViewById(R.id.gridview1);
        this.d = (OtherGridView) findViewById(R.id.gridview2);
        this.e = (OtherGridView) findViewById(R.id.gridview3);
        this.f = (OtherGridView) findViewById(R.id.gridview4);
        this.a = (TextView) findViewById(R.id.ok_btn);
        this.b = (TextView) findViewById(R.id.cancle_btn);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        findViewById(R.id.profile_header_left_back).setOnClickListener(this);
        this.g = new a(this, this);
        this.h = new a(this, this);
        this.i = new a(this, this);
        this.j = new a(this, this);
        this.c.setAdapter(this.g);
        this.d.setAdapter(this.h);
        this.e.setAdapter(this.i);
        this.f.setAdapter(this.j);
        this.c.setOnItemClickListener(this);
        this.d.setOnItemClickListener(this);
        this.e.setOnItemClickListener(this);
        this.f.setOnItemClickListener(this);
        b();
    }

    private String a() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            for (b bVar : this.l) {
                if (bVar != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("actionId", bVar.a);
                    jSONObject2.put("title", bVar.b);
                    jSONObject2.put("isSelected", bVar.c);
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("actionIdList", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject3 = jSONObject.toString();
        c.a("classify", "result " + jSONObject3);
        return jSONObject3;
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                Object stringExtra = intent.getStringExtra("classify_list");
                if (!TextUtils.isEmpty(stringExtra)) {
                    try {
                        this.k = new JSONObject(stringExtra);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray optJSONArray = this.k.optJSONArray("actionIdList");
                    int i = 0;
                    while (optJSONArray != null && i < optJSONArray.length()) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            this.g.a(new b(this, optJSONObject.optInt("actionId"), optJSONObject.optString("title"), optJSONObject.optBoolean("isSelected")));
                        }
                        i++;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.h.a(new b(this, 0, "免费", false));
        this.h.a(new b(this, 1, "包月", false));
        this.h.a(new b(this, 6, "收费", false));
        this.h.a(new b(this, -1, BookShelfFragment.CATEGORY_ALL, false));
        this.i.a(new b(this, -1, BookShelfFragment.CATEGORY_ALL, false));
        this.i.a(new b(this, 0, "连载", false));
        this.i.a(new b(this, 0, "完结", false));
        this.i.a(new b(this, 2, "节选", false));
        this.j.a(new b(this, 2, "最新", false));
        this.j.a(new b(this, 3, "收藏", false));
        this.j.a(new b(this, 9, "字数", false));
        this.j.a(new b(this, 10, "人气", false));
        this.g.notifyDataSetChanged();
        this.h.notifyDataSetChanged();
        this.i.notifyDataSetChanged();
        this.j.notifyDataSetChanged();
        c.a("classify", " initData ");
    }

    private void a(AdapterView adapterView) {
        int i = 0;
        while (adapterView.getChildCount() == adapterView.getAdapter().getCount() && i < adapterView.getChildCount()) {
            adapterView.getChildAt(i).setSelected(false);
            i++;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (adapterView.getId()) {
            case R.id.gridview1:
                this.l[0] = this.g.a(i);
                this.g.notifyDataSetChanged();
                break;
            case R.id.gridview2:
                this.l[1] = this.h.a(i);
                this.h.notifyDataSetChanged();
                break;
            case R.id.gridview3:
                this.l[2] = this.i.a(i);
                this.i.notifyDataSetChanged();
                break;
            case R.id.gridview4:
                this.l[3] = this.j.a(i);
                this.j.notifyDataSetChanged();
                break;
        }
        a(adapterView);
        view.setSelected(true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                Intent intent = new Intent();
                intent.putExtra("classify_list", a());
                setResult(-1, intent);
                break;
        }
        finish();
    }
}
