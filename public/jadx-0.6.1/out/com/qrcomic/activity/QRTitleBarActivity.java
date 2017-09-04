package com.qrcomic.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import b.a.a.a.a.a;
import b.a.a.a.a.a.e;
import b.a.a.a.a.a.f;
import b.a.a.a.a.a.g;
import com.qrcomic.f.d;

public class QRTitleBarActivity extends FragmentActivity {
    protected TextView a;
    protected TextView b;
    protected TextView c;
    protected TextView d;
    protected ImageView e;
    protected ViewGroup f;
    protected float g;
    public boolean h = true;
    protected OnClickListener i = new OnClickListener(this) {
        final /* synthetic */ QRTitleBarActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b();
        }
    };
    private View j = null;

    public void onCreate(Bundle bundle) {
        d.a((Activity) this);
        super.onCreate(bundle);
    }

    @TargetApi(14)
    public void setContentView(int i) {
        super.setContentView(i);
        if (this.h) {
            View linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            LayoutInflater from = LayoutInflater.from(this);
            this.j = from.inflate(f.custom_commen_title, linearLayout, false);
            View inflate = from.inflate(i, null);
            linearLayout.addView(this.j);
            linearLayout.addView(inflate);
            super.setContentView(linearLayout);
        } else {
            super.setContentView(i);
            getWindow().setFeatureInt(7, f.custom_commen_title);
        }
        if (this.j != null) {
            this.j.setVisibility(0);
        }
        this.g = getResources().getDisplayMetrics().density;
        a(getIntent());
    }

    @TargetApi(14)
    public void setContentView(View view) {
        this.g = getResources().getDisplayMetrics().density;
        if (!this.h) {
            super.setContentView(view);
            getWindow().setFeatureInt(7, f.custom_commen_title);
        }
        if (this.j != null) {
            this.j.setVisibility(0);
        }
        a(getIntent());
    }

    protected void a(Intent intent) {
        ((FrameLayout) findViewById(16908290)).setForeground(getResources().getDrawable(a.d.skin_header_bar_shadow));
        if (this.a == null) {
            this.f = (ViewGroup) findViewById(e.rlCommenTitle);
            a(this.f);
            a();
            c();
            d();
            b(intent);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.c != null && (this.c instanceof TextView)) {
            this.c.setText(charSequence);
            super.setTitle(charSequence);
        }
    }

    public void setTitle(int i) {
        setTitle(getString(i));
    }

    protected View a() {
        this.a = (TextView) findViewById(e.ivTitleBtnLeft);
        if (this.a != null && (this.a instanceof TextView)) {
            this.a.setOnClickListener(this.i);
            a(this.a);
        }
        return this.a;
    }

    public void b(Intent intent) {
        if (this.a != null && (this.a instanceof TextView) && intent != null && intent.getExtras() != null) {
            if (this.b != null) {
                this.b.setVisibility(8);
            }
            TextView textView = this.a;
            CharSequence string = intent.getExtras().getString("leftViewText");
            if (string == null) {
                string = getString(g.button_back);
            }
            textView.setText(string);
            textView.setVisibility(0);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            b();
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected boolean b() {
        finish();
        return false;
    }

    protected View c() {
        this.c = (TextView) findViewById(e.ivTitleName);
        return this.c;
    }

    protected View d() {
        this.d = (TextView) findViewById(e.ivTitleBtnRightText);
        this.e = (ImageView) findViewById(e.ivTitleBtnRightImage);
        if (this.d != null) {
            a(this.d);
            a(this.e);
        }
        return this.d;
    }

    @TargetApi(11)
    public static void a(View view) {
        if (view != null && VERSION.SDK_INT > 10) {
            view.setLayerType(0, null);
        }
    }
}
