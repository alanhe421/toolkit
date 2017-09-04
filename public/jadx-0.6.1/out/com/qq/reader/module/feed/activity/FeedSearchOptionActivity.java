package com.qq.reader.module.feed.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.fragment.NativeFragmentForSearchOption;
import com.qq.reader.view.FlowLayout;
import com.qq.reader.view.LinearListView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedSearchOptionActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener, com.qq.reader.module.bookstore.qnative.c.a, com.qq.reader.module.bookstore.qnative.fragment.NativeFragmentForSearchOption.a {
    protected Bundle a = null;
    com.qq.reader.view.LinearListView.b b = new com.qq.reader.view.LinearListView.b(this) {
        final /* synthetic */ FeedSearchOptionActivity a;

        {
            this.a = r1;
        }

        public void a(LinearListView linearListView, View view, int i, long j) {
            ((c) this.a.p.b(this.a.q).getTag()).b();
            this.a.q = i;
            ((c) this.a.p.b(i).getTag()).a();
            this.a.a(i);
        }
    };
    private Context c;
    private String d = "";
    private TextView n = null;
    private int o;
    private LinearListView p;
    private int q = 0;
    private String r;
    private ArrayList<NativeFragmentForSearchOption> s;
    private JSONArray t;
    private Button u;
    private FlowLayout v;
    private HashMap<String, View> w = new HashMap();
    private int x = 0;

    private class a extends BaseAdapter {
        final /* synthetic */ FeedSearchOptionActivity a;
        private ArrayList<b> b;

        public a(FeedSearchOptionActivity feedSearchOptionActivity) {
            this.a = feedSearchOptionActivity;
            this.b = null;
            this.b = new ArrayList();
            String[] stringArray = feedSearchOptionActivity.getResources().getStringArray(R.array.search_tool_left_items);
            int i = 0;
            while (stringArray != null && i < stringArray.length) {
                b bVar = new b();
                bVar.a = stringArray[i];
                this.b.add(bVar);
                i++;
            }
        }

        public int getCount() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.localstore_card_author_left, viewGroup, false);
            }
            c cVar = new c(this.a, this.a.c, null, view);
            cVar.a(((b) this.b.get(i)).a);
            if (i == 0) {
                cVar.a();
            }
            view.setTag(cVar);
            return view;
        }
    }

    private class b {
        public String a;
        final /* synthetic */ FeedSearchOptionActivity b;

        private b(FeedSearchOptionActivity feedSearchOptionActivity) {
            this.b = feedSearchOptionActivity;
        }
    }

    private class c extends LinearLayout {
        final /* synthetic */ FeedSearchOptionActivity a;
        private View b;
        private TextView c;
        private View d;
        private LinearLayout e;

        public c(FeedSearchOptionActivity feedSearchOptionActivity, Context context, AttributeSet attributeSet, View view) {
            this.a = feedSearchOptionActivity;
            super(context, attributeSet);
            LayoutInflater.from(context).inflate(R.layout.localstore_card_author_left, null, false);
            a(view);
        }

        public void a(String str) {
            this.c.setText(str);
        }

        private void a(View view) {
            this.b = view.findViewById(R.id.author_tab_line);
            this.c = (TextView) view.findViewById(R.id.author_tab_title);
            this.d = view.findViewById(R.id.author_divider_line);
            this.e = (LinearLayout) view.findViewById(R.id.author_tab_layout);
        }

        public void a() {
            this.e.setBackgroundResource(R.color.concept_card_bg);
            this.c.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
            this.b.setVisibility(0);
            this.d.setVisibility(8);
        }

        public void b() {
            this.e.setBackgroundResource(R.color.concept_divider_bg);
            this.c.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_primary));
            this.b.setVisibility(8);
            this.d.setVisibility(0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getApplicationContext();
        setContentView(R.layout.local_search_option);
        this.a = getIntent().getExtras();
        this.d = this.a.getString("LOCAL_STORE_IN_TITLE");
        this.x = this.a.getInt("default_pos", 0);
        a();
        g();
        a(this.x);
    }

    protected void onResume() {
        super.onResume();
    }

    private void g() {
        String string = this.a.getString("search_option");
        for (int i = 0; i < this.s.size(); i++) {
            ((NativeFragmentForSearchOption) this.s.get(i)).setInitialValues(string);
        }
    }

    public void a() {
        super.a();
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedSearchOptionActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.u = (Button) findViewById(R.id.btn_search_option_comfirm);
        this.u.setText(getString(R.string.search_option_confirm_disabled));
        this.u.setOnClickListener(this);
        this.v = (FlowLayout) findViewById(R.id.flow_layout);
        this.n = (TextView) findViewById(R.id.profile_header_title);
        this.n.setText(this.d);
        this.p = (LinearListView) findViewById(R.id.haffoffame_tab_list);
        this.p.setOnItemClickListener(this.b);
        this.p.setAdapter(new a(this));
        if (this.r == null) {
            int aS = d.aS(ReaderApplication.getApplicationImp());
            String str = "search/search_option_publish.txt";
            if (aS == 1) {
                str = "search/search_option_male.txt";
            } else if (aS == 2) {
                str = "search/search_option_female.txt";
            }
            this.r = ao.d.a(str);
        }
        try {
            this.t = new JSONObject(this.r).optJSONArray("data");
            this.s = new ArrayList(this.t.length());
            for (int i = 0; i < this.t.length(); i++) {
                Bundle bundle = new Bundle();
                bundle.putString("data", this.t.getJSONObject(i).toString());
                NativeFragmentForSearchOption nativeFragmentForSearchOption = new NativeFragmentForSearchOption();
                nativeFragmentForSearchOption.setFragmentArgs(bundle);
                nativeFragmentForSearchOption.setOptionChangeListner(this);
                this.s.add(nativeFragmentForSearchOption);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    public void startActivity(Intent intent) {
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    public void a(String str, String str2) {
        b(str, str2);
    }

    public void b(String str, String str2) {
        View view = (View) this.w.get(str);
        if (view == null) {
            view = a(str2);
            this.v.addView(view);
            this.w.put(str, view);
        } else if (TextUtils.isEmpty(str2)) {
            this.v.removeView(view);
            this.w.put(str, null);
        } else {
            ((TextView) view.findViewById(R.id.tv_text)).setText(str2);
        }
        if (this.v.getChildCount() <= 0) {
            this.v.setVisibility(8);
            this.u.setEnabled(false);
            this.u.setText(getString(R.string.search_option_confirm_disabled));
            return;
        }
        this.v.setVisibility(0);
        this.u.setEnabled(true);
        this.u.setText(getString(R.string.search_option_comfirm));
    }

    private View a(String str) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.search_option_flow_ui, this.v, false);
        ((TextView) inflate.findViewById(R.id.tv_text)).setText(str);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedSearchOptionActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                for (String str : this.a.w.keySet()) {
                    if (view == ((View) this.a.w.get(str))) {
                        int i = 0;
                        while (this.a.s != null && i < this.a.s.size()) {
                            ((NativeFragmentForSearchOption) this.a.s.get(i)).clearAllValueAndUpdate(str);
                            i++;
                        }
                    }
                }
            }
        });
        return inflate;
    }

    private void a(int i) {
        if (i < this.s.size()) {
            getSupportFragmentManager().a().b(R.id.ll_container, (Fragment) this.s.get(i)).b();
        }
    }

    protected void b() {
        l();
        this.p.setVisibility(8);
        this.e.setVisibility(0);
    }

    protected void c() {
        l();
        this.p.setVisibility(0);
        this.e.setVisibility(8);
    }

    protected void d() {
        if (this.p.getVisibility() != 0 && this.o <= 0) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        } else if (this.i != null) {
            this.i.setRefreshing(false);
        }
    }

    private String i() {
        String str = "";
        for (int i = 0; i < this.s.size(); i++) {
            str = str + ((NativeFragmentForSearchOption) this.s.get(i)).generateSearchParam();
            if (i != this.s.size() - 1) {
                str = str + "&";
            }
        }
        return str;
    }

    public String f() {
        String str;
        Exception exception;
        String str2 = "";
        try {
            if (this.v != null) {
                int childCount = this.v.getChildCount();
                int i = 0;
                while (i < childCount) {
                    View childAt = this.v.getChildAt(i);
                    if (childAt != null) {
                        Object obj;
                        for (String str3 : this.w.keySet()) {
                            View view = (View) this.w.get(str3);
                            if (view != null && view == childAt) {
                                TextView textView = (TextView) view.findViewById(R.id.tv_text);
                                if (!(textView == null || textView.getText() == null || TextUtils.isEmpty(textView.getText().toString()))) {
                                    str3 = str2 + textView.getText().toString();
                                    obj = 1;
                                    break;
                                }
                            }
                        }
                        str3 = str2;
                        obj = null;
                        if (!(obj == null || i == childCount - 1)) {
                            try {
                                str3 = str3 + "&";
                            } catch (Exception e) {
                                Exception exception2 = e;
                                str2 = str3;
                                exception = exception2;
                            }
                        }
                    } else {
                        str3 = str2;
                    }
                    i++;
                    str2 = str3;
                }
            }
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            return str2;
        }
        return str2;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_option_comfirm:
                o.a(this, f(), i(), true, null);
                return;
            default:
                return;
        }
    }
}
