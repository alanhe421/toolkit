package com.dynamicload;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.dynamicload.Lib.IDLProxyActivity;
import com.dynamicload.internal.f;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class DLProxyFragmentActivity extends FragmentActivity implements IDLProxyActivity {
    f a;
    private float b;
    private float c;
    private float d;
    private float e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        c.a(getIntent());
        this.a = a(this);
        this.a.a(getIntent(), bundle);
    }

    protected f a(Context context) {
        return new f(context);
    }

    public Context getContext() {
        return this;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.a.a(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    protected void onStart() {
        this.a.a();
        super.onStart();
    }

    protected void onRestart() {
        this.a.b();
        super.onRestart();
    }

    protected void onResume() {
        this.a.c();
        super.onResume();
    }

    protected void onPause() {
        this.a.d();
        super.onPause();
    }

    protected void onStop() {
        this.a.e();
        super.onStop();
    }

    protected void onDestroy() {
        this.a.f();
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        this.a.a(bundle);
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        this.a.b(bundle);
        super.onRestoreInstanceState(bundle);
    }

    protected void onNewIntent(Intent intent) {
        this.a.a(intent);
        super.onNewIntent(intent);
    }

    public void onBackPressed() {
        this.a.h();
        super.onBackPressed();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.a.a(motionEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        super.onKeyUp(i, keyEvent);
        return this.a.a(i, keyEvent);
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        this.a.a(layoutParams);
        super.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        this.a.a(z);
        super.onWindowFocusChanged(z);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.a.a(menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);
        return this.a.a(menuItem);
    }

    public void setContentView(int i) {
        super.setContentView(this.a.a(i));
    }

    public Object getHostSystemService(String str) {
        return this.a.a(str);
    }

    public void finish() {
        super.finish();
    }

    public View findViewById(int i) {
        return this.a.a(getWindow().getDecorView(), i);
    }

    public LayoutInflater getHostLayoutInflater() {
        return this.a.l();
    }

    public void setRequestedOrientation(int i) {
        super.setRequestedOrientation(i);
    }

    public int getRequestedOrientation() {
        return super.getRequestedOrientation();
    }

    public boolean onSuperKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onSuperKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a.j()) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (motionEvent.getAction() == 0) {
                this.b = x;
                this.d = y;
            } else if (motionEvent.getAction() == 1) {
                this.c = x;
                this.e = y;
                if (this.c > this.b && Math.abs(this.c - this.b) > ((float) ao.a(100.0f)) && ((double) (Math.abs(this.e - this.d) / Math.abs(this.c - this.b))) < 0.4d) {
                    this.a.i();
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
