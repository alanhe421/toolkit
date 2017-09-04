package com.qq.reader.module.bookstore.charge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.common.charge.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.tencent.feedback.proguard.R;

public class NativeBookStoreChargeBaseAcitivty extends NativeBookStoreConfigBaseActivity {
    protected b a;
    protected Bundle b;
    protected int c = 2;
    protected Intent d;
    private final String n = "NativeBookStoreChargeBaseAcitivty";
    private boolean o;
    private final int p = 2;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new b();
        f();
        if (getResources().getConfiguration().orientation == 2) {
            getWindow().setSoftInputMode(32);
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        if (bundle != null) {
            extras.putAll(bundle);
        }
        this.b = extras;
    }

    protected void f() {
        a();
        ((TextView) findViewById(R.id.profile_header_title)).setText(getIntent().getStringExtra("LOCAL_STORE_IN_TITLE"));
        this.g = (ListView) findViewById(R.id.list);
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreChargeBaseAcitivty a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(2);
                this.a.finish();
            }
        });
    }

    protected void a(Bundle bundle) {
        try {
            this.j = e.a().a(bundle, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this);
            }
            this.h.a(this.j);
            this.g.setAdapter(this.h);
            a(false, false);
        }
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        this.j.a((af) message.obj);
                    }
                    c();
                    this.o = false;
                    if (this.h != null) {
                        if (this.h.b() || this.g.getAdapter() == null) {
                            this.g.setAdapter(this.h);
                        } else {
                            this.h.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                    c.e("NativeBookStoreChargeBaseAcitivty", e.getMessage());
                    this.mHandler.sendEmptyMessage(500004);
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void d() {
        super.d();
    }

    protected void c() {
        super.c();
    }

    public void doFunction(Bundle bundle) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.qq.reader.common.monitor.f.d("MonthVip", "bookcoinchage requestCode is " + i + " and resultCode is " + i2);
        this.c = i2;
        this.d = intent;
    }

    public void finish() {
        setResult(this.c, this.d);
        super.finish();
    }

    public Activity getFromActivity() {
        return this;
    }
}
