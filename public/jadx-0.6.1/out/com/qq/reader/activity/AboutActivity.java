package com.qq.reader.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.widget.viewpager.c;
import com.qq.reader.module.b.a;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;

public class AboutActivity extends ReaderBaseActivity implements OnItemClickListener {
    c a;
    private Context b;
    private ListView c = null;
    private a d = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getApplicationContext();
        setContentView(R.layout.aboutactivity);
        this.c = (ListView) findViewById(R.id.about_list);
        View inflate = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.about_version_info, null);
        ((TextView) inflate.findViewById(R.id.about_version)).setText(getResources().getString(R.string.app_name) + "6.5.3");
        this.c.addHeaderView(inflate);
        this.d = new a(this.b);
        this.c.setAdapter(this.d);
        this.c.setOnItemClickListener(this);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AboutActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText("关于" + getResources().getString(R.string.app_name));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void a() {
        Uri parse = Uri.parse("market://details?id=" + "com.qq.reader");
        if (parse == null) {
            b();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        if (a(intent)) {
            startActivity(intent);
        } else {
            b();
        }
    }

    private boolean a(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private void b() {
        Intent intent = new Intent();
        intent.setClass(this.b, SuggestActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void c() {
        Intent intent = new Intent();
        intent.setClass(this.b, WebBrowserForContents.class);
        intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
        intent.putExtra("com.qq.reader.WebContent", "file:///android_asset/about.html");
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void d() {
        Intent intent = new Intent();
        intent.setClass(this.b, WebBrowserForContents.class);
        intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
        intent.putExtra("com.qq.reader.WebContent", "help1.html");
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void e() {
        checkUpdate(false, false);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a != null && this.a.a()) {
            this.a.a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a != null && this.a.a()) {
            this.a.b();
            return true;
        } else if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        } else {
            finish();
            return true;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.c.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount <= this.d.getCount()) {
            switch (headerViewsCount) {
                case 0:
                    j.a(1, 3);
                    a();
                    return;
                case 1:
                    j.a(56, 3);
                    d();
                    return;
                case 2:
                    j.a(2, 3);
                    b();
                    return;
                case 3:
                    c();
                    return;
                case 4:
                    j.a(55, 3);
                    e();
                    return;
                default:
                    return;
            }
        }
    }
}
