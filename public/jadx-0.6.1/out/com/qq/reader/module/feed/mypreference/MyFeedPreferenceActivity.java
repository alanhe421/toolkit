package com.qq.reader.module.feed.mypreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.protocol.ReportRookieUserGeneTagTask;
import com.qq.reader.common.readertask.protocol.ReportUserTagTask;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.bookstore.qnative.page.impl.am;
import com.qq.reader.module.feed.mypreference.d.d;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.List;

public class MyFeedPreferenceActivity extends ReaderBaseActivity implements OnClickListener, a {
    public static boolean a = false;
    protected b b = null;
    protected f c;
    private int d;
    private int e;
    private int f;
    private Button g;
    private StickyGridHeadersGridView h;
    private String i;
    private String j;
    private List<Long> k = new ArrayList();
    private View l;
    private View m;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private c q;
    private XListView r;

    protected void onDestroy() {
        super.onDestroy();
        this.p = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.myfeedpreference_layout);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("fromFeedAction")) {
                this.p = extras.getBoolean("fromFeedAction");
            }
        }
        this.n = getIntent().getBooleanExtra("isRookie", false);
        this.o = getIntent().getBooleanExtra("shouldHideOtherSexGene", false);
        h();
        i();
        a();
    }

    private void a() {
        d();
        g.a().a(new 1(this));
    }

    private void b() {
        j();
        e();
        g();
    }

    private void c() {
        k();
    }

    private void d() {
        this.l.setVisibility(0);
        c();
        g();
    }

    private void e() {
        this.l.setVisibility(8);
    }

    private void f() {
        this.m.setVisibility(0);
        e();
        c();
    }

    private void g() {
        this.m.setVisibility(8);
    }

    private void h() {
        this.d = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.select_pref_gridview_margin);
        this.e = getResources().getDimensionPixelOffset(R.dimen.modify_gene_item_width);
        this.f = getResources().getDimensionPixelOffset(R.dimen.modify_gene_item_height);
        this.g = (Button) findViewById(R.id.btn_save);
        this.g.setOnClickListener(this);
        ((TextView) findViewById(R.id.profile_header_title)).setText(getString(R.string.modify_my_read_gene));
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(this);
        if (!this.n) {
            d c = c.b().c();
            if (c != null) {
                a(c);
            }
        }
        this.h = (StickyGridHeadersGridView) findViewById(R.id.prefrerencegrid);
        this.q = new 2(this);
        this.h.setNumColumns(3);
        this.h.setAreHeadersSticky(false);
        this.l = findViewById(R.id.loading_layout);
        this.m = findViewById(R.id.loading_failed_layout);
        this.m.setOnClickListener(new 3(this));
    }

    private void i() {
        TextView textView = (TextView) findViewById(R.id.profile_header_title);
        if (this.n) {
            textView.setText(getString(R.string.modify_my_rookie_read_gene));
            this.g.setText(R.string.save_all_rookie_changes);
            this.g.setEnabled(false);
            i.a("event_F155", null, ReaderApplication.getApplicationImp());
        } else {
            textView.setText(getString(R.string.modify_my_read_gene));
            this.g.setText(R.string.save_all_changes);
            this.g.setEnabled(true);
            Intent intent = getIntent();
            if (intent != null) {
                CharSequence stringExtra = intent.getStringExtra("PREFERENCE_ACTIVITY_EXTRA_BTN_SAVE_TEXT");
                CharSequence stringExtra2 = intent.getStringExtra("LOCAL_STORE_IN_TITLE");
                this.i = intent.getStringExtra("PREFERENCE_ACTIVITY_EXTRA_NEXT_QURL");
                this.j = intent.getStringExtra("newuser");
                if (!TextUtils.isEmpty(stringExtra)) {
                    this.g.setText(stringExtra);
                }
                if (!TextUtils.isEmpty(stringExtra2)) {
                    textView.setText(stringExtra2);
                }
            }
        }
        this.r = (XListView) findViewById(R.id.list);
        this.b = new am(new Bundle());
        this.b.b((a) this);
        if (this.c == null) {
            this.c = new f(this);
        }
        this.c.a(this.b);
        this.r.setPullLoadEnable(false);
        this.r.setAdapter(this.c);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 118:
                try {
                    ListAdapter aVar;
                    b();
                    if (this.n) {
                        aVar = new a(this, this, null);
                        aVar.a(this.q);
                    } else {
                        aVar = new a(this, this, this.k);
                    }
                    this.h.setAdapter(aVar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case Opcodes.INVOKE_STATIC_RANGE /*119*/:
                f();
                return true;
            case 500000:
            case 500001:
                try {
                    i.a("event_F158", null, ReaderApplication.getApplicationImp());
                    if (message.obj != null) {
                        this.b.a((af) message.obj);
                    }
                    k();
                    if (this.c != null) {
                        if (this.c.b() || this.r.getAdapter() == null) {
                            this.r.setAdapter(this.c);
                        } else {
                            this.c.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e2) {
                    c.e("MyFeedPre", e2.getMessage());
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void j() {
        this.g.setVisibility(0);
        this.h.setVisibility(0);
        findViewById(R.id.modify_gene_bottom_shadow).setVisibility(0);
    }

    private void k() {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        findViewById(R.id.modify_gene_bottom_shadow).setVisibility(8);
    }

    public void doFunction(Bundle bundle) {
        if (bundle.getInt("function_type") == 4 && this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    private void a(d dVar) {
        this.k.clear();
        ArrayList arrayList = dVar.a;
        int i = 0;
        while (arrayList != null && i < arrayList.size()) {
            this.k.add(Long.valueOf((long) ((d) arrayList.get(i)).a));
            i++;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                finish();
                return;
            case R.id.btn_save:
                m();
                if (this.n) {
                    i.a("event_F156", null, ReaderApplication.getApplicationImp());
                    return;
                }
                i.a("event_A150", null, ReaderApplication.getApplicationImp());
                if (this.p) {
                    Intent intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.cL);
                    sendBroadcast(intent);
                }
                setResult(-1);
                if (!TextUtils.isEmpty(this.i)) {
                    try {
                        i.a("event_F193", null, ReaderApplication.getApplicationImp());
                        com.qq.reader.qurl.c.a(this, this.i, null);
                    } catch (Exception e) {
                        c.e("Error", e.getMessage());
                    }
                }
                finish();
                return;
            default:
                return;
        }
    }

    private a l() {
        return (a) ((StickyGridHeadersBaseAdapterWrapper) this.h.getAdapter()).a();
    }

    private void m() {
        List b = l().b();
        if (this.n) {
            g.a().a(new ReportRookieUserGeneTagTask(new 4(this), b));
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.j)) {
            bundle.putString("newuser", this.j);
        }
        a = false;
        g.a().a(new ReportUserTagTask(new 5(this), b, bundle));
        c.b().a(l().c());
    }
}
