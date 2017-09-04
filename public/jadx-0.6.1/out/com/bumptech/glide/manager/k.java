package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.g.h;
import com.bumptech.glide.i;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RequestManagerRetriever */
public class k implements Callback {
    private static final k c = new k();
    final Map<FragmentManager, j> a = new HashMap();
    final Map<android.support.v4.app.k, SupportRequestManagerFragment> b = new HashMap();
    private volatile i d;
    private final Handler e = new Handler(Looper.getMainLooper(), this);

    public static k a() {
        return c;
    }

    k() {
    }

    private i b(Context context) {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new i(context.getApplicationContext(), new b(), new f());
                }
            }
        }
        return this.d;
    }

    public i a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (h.c() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return a((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return a((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return a(((ContextWrapper) context).getBaseContext());
            }
        }
        return b(context);
    }

    public i a(FragmentActivity fragmentActivity) {
        if (h.d()) {
            return a(fragmentActivity.getApplicationContext());
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager());
    }

    public i a(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (h.d()) {
            return a(fragment.getActivity().getApplicationContext());
        } else {
            return a(fragment.getActivity(), fragment.getChildFragmentManager());
        }
    }

    @TargetApi(11)
    public i a(Activity activity) {
        if (h.d() || VERSION.SDK_INT < 11) {
            return a(activity.getApplicationContext());
        }
        b(activity);
        return a((Context) activity, activity.getFragmentManager());
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    j a(FragmentManager fragmentManager) {
        j jVar = (j) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (jVar != null) {
            return jVar;
        }
        jVar = (j) this.a.get(fragmentManager);
        if (jVar != null) {
            return jVar;
        }
        android.app.Fragment jVar2 = new j();
        this.a.put(fragmentManager, jVar2);
        fragmentManager.beginTransaction().add(jVar2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(1, fragmentManager).sendToTarget();
        return jVar2;
    }

    @TargetApi(11)
    i a(Context context, FragmentManager fragmentManager) {
        j a = a(fragmentManager);
        i b = a.b();
        if (b != null) {
            return b;
        }
        b = new i(context, a.a(), a.c());
        a.a(b);
        return b;
    }

    SupportRequestManagerFragment a(android.support.v4.app.k kVar) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) kVar.a("com.bumptech.glide.manager");
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        supportRequestManagerFragment = (SupportRequestManagerFragment) this.b.get(kVar);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        Fragment supportRequestManagerFragment2 = new SupportRequestManagerFragment();
        this.b.put(kVar, supportRequestManagerFragment2);
        kVar.a().a(supportRequestManagerFragment2, "com.bumptech.glide.manager").b();
        this.e.obtainMessage(2, kVar).sendToTarget();
        return supportRequestManagerFragment2;
    }

    i a(Context context, android.support.v4.app.k kVar) {
        SupportRequestManagerFragment a = a(kVar);
        i requestManager = a.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        requestManager = new i(context, a.getLifecycle(), a.getRequestManagerTreeNode());
        a.setRequestManager(requestManager);
        return requestManager;
    }

    public boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case 1:
                FragmentManager fragmentManager = (FragmentManager) message.obj;
                remove = this.a.remove(fragmentManager);
                break;
            case 2:
                android.support.v4.app.k kVar = (android.support.v4.app.k) message.obj;
                remove = this.b.remove(kVar);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && r1 == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj);
        }
        return z;
    }
}
