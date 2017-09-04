package com.dynamicload.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.IDLPluginActivity;
import com.dynamicload.Lib.IDLProxyActivity;
import com.dynamicload.c;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.InvocationTargetException;

/* compiled from: DLProxyActivityImpl */
public class f extends h {
    protected IDLPluginActivity a;
    protected Theme b;
    private ActivityInfo j;
    private Factory k = new Factory(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            c.a("inflate onCreateView:" + str);
            if (!str.startsWith(DLConstants.EXPORT_PACKAGE_VIEW)) {
                return null;
            }
            try {
                return (View) Class.forName(str).getConstructor(new Class[]{Context.class, AttributeSet.class}).newInstance(new Object[]{this.a.c, attributeSet});
            } catch (Exception e) {
                c.c("onCreateView Exception e= " + e);
                com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "exception when inflate [" + str + "]" + e.toString());
                return null;
            }
        }
    };

    public f(Context context) {
        super(context);
    }

    public void a(int i, int i2, Intent intent) {
        this.a.peformOnActivityResult(i, i2, intent);
    }

    public void a() {
        if (this.a != null) {
            this.a.peformOnStart();
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.peformOnRestart();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.peformOnResume();
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.peformOnPause();
        }
    }

    public void e() {
        if (this.a != null) {
            this.a.peformOnStop();
        }
    }

    public void f() {
        if (this.a != null) {
            this.a.peformOnDestroy();
        }
        g();
        this.a = null;
        this.j = null;
    }

    protected void g() {
        this.g.popActivity(this.a);
    }

    public void a(Bundle bundle) {
        bundle.setClassLoader(this.f.classLoader);
        if (this.a != null) {
            this.a.peformOnSaveInstanceState(bundle);
        }
        bundle.putString(DLConstants.EXTRA_PACKAGE, this.e);
    }

    public void b(Bundle bundle) {
        if (this.a != null) {
            bundle.setClassLoader(this.f.classLoader);
            this.a.peformOnRestoreInstanceState(bundle);
        }
    }

    public void a(Intent intent) {
        if (this.a != null) {
            intent.setExtrasClassLoader(this.f.classLoader);
            this.a.peformOnNewIntent(intent);
        }
    }

    public void h() {
        if (this.a != null) {
            this.a.peformOnBackPressed();
        }
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.a != null) {
            return this.a.peformOnTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.a != null) {
            return this.a.peformOnKeyUp(i, keyEvent);
        }
        return false;
    }

    public boolean b(int i, KeyEvent keyEvent) {
        if (this.a != null) {
            return this.a.peformOnKeyDown(i, keyEvent);
        }
        return false;
    }

    public void a(LayoutParams layoutParams) {
        if (this.a != null) {
            this.a.peformOnWindowAttributesChanged(layoutParams);
        }
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.peformOnWindowFocusChanged(z);
        }
    }

    public boolean a(Menu menu) {
        if (this.a != null) {
            this.a.peformOnCreateOptionsMenu(menu);
        }
        return true;
    }

    public boolean a(MenuItem menuItem) {
        if (this.a != null) {
            this.a.peformOnOptionsItemSelected(menuItem);
        }
        return true;
    }

    public View a(int i) {
        View b = b(i);
        this.a.saveContentView(b);
        return b;
    }

    public void i() {
        if (this.a != null) {
            this.a.targetActivity();
        }
    }

    public boolean j() {
        if (this.a != null) {
            return this.a.isGesture();
        }
        return false;
    }

    protected void a(Bundle bundle, Intent intent) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.setClassLoader(this.f.classLoader);
        bundle.putInt(DLConstants.FROM, 1);
        this.a.peformOnCreate(bundle);
        k();
    }

    protected void k() {
        this.g.pushActivity(this.a);
    }

    protected void b(Intent intent) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.j = (ActivityInfo) intent.getParcelableExtra(DLConstants.EXTRA_ACTIVITY_INFO);
        this.b = this.i.newTheme();
        this.b.setTo(this.f.application.getTheme());
        if (this.j.theme > 0) {
            this.b.applyStyle(this.j.theme, true);
        }
        Object newInstance = this.f.classLoader.loadClass(this.d).getConstructor(new Class[0]).newInstance(new Object[0]);
        c.a((Context) newInstance, this.f.application);
        this.a = (IDLPluginActivity) newInstance;
        this.a.attach((IDLProxyActivity) this.c, this.f, this.b);
    }

    public Object a(String str) {
        if (!"layout_inflater".equals(str)) {
            return this.c.getSystemService(str);
        }
        Object cloneInContext = LayoutInflater.from(this.c).cloneInContext((Context) this.a);
        if (cloneInContext.getFactory() != null) {
            return cloneInContext;
        }
        cloneInContext.setFactory(this.k);
        return cloneInContext;
    }

    public LayoutInflater l() {
        LayoutInflater cloneInContext = LayoutInflater.from(this.c).cloneInContext((Context) this.a);
        if (cloneInContext.getFactory() == null) {
            cloneInContext.setFactory(this.k);
        }
        return cloneInContext;
    }

    public View b(int i) {
        return LayoutInflater.from(this.c).cloneInContext((Context) this.a).inflate(i, null, false);
    }

    public View a(View view, int i) {
        return this.g.findViewByID(view, i);
    }

    public void a(Intent intent, Bundle bundle) {
        try {
            c(intent);
            b(intent);
            a(bundle, intent);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "fail to create plugin activity [" + this.d + "] exception is " + e.toString());
            af.a(this.c.getApplicationContext(), (int) R.string.dl_proxy_insufficient_space, 1);
            ((Activity) this.c).finish();
        }
    }

    public void a(Configuration configuration) {
        this.a.peformOnConfigurationChanged(configuration);
    }
}
