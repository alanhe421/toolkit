package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.util.c;
import android.support.v4.util.d;
import android.support.v4.view.m;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.tencent.mm.performance.WxPerformanceHandle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: FragmentManager */
final class l extends k implements m {
    static final Interpolator A = new DecelerateInterpolator(2.5f);
    static final Interpolator B = new DecelerateInterpolator(1.5f);
    static final Interpolator C = new AccelerateInterpolator(2.5f);
    static final Interpolator D = new AccelerateInterpolator(1.5f);
    static boolean a = false;
    static final boolean b;
    static Field r = null;
    ArrayList<Runnable> c;
    Runnable[] d;
    boolean e;
    ArrayList<Fragment> f;
    ArrayList<Fragment> g;
    ArrayList<Integer> h;
    ArrayList<e> i;
    ArrayList<Fragment> j;
    ArrayList<e> k;
    ArrayList<Integer> l;
    ArrayList<android.support.v4.app.k.a> m;
    int n = 0;
    j o;
    h p;
    Fragment q;
    boolean s;
    boolean t;
    boolean u;
    String v;
    boolean w;
    Bundle x = null;
    SparseArray<Parcelable> y = null;
    Runnable z = new Runnable(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.g();
        }
    };

    /* compiled from: FragmentManager */
    static class a implements AnimationListener {
        private AnimationListener a = null;
        private boolean b = false;
        private View c = null;

        public a(View view, Animation animation) {
            if (view != null && animation != null) {
                this.c = view;
            }
        }

        public a(View view, Animation animation, AnimationListener animationListener) {
            if (view != null && animation != null) {
                this.a = animationListener;
                this.c = view;
            }
        }

        public void onAnimationStart(Animation animation) {
            if (this.c != null) {
                this.b = l.a(this.c, animation);
                if (this.b) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            z.a(this.a.c, 2, null);
                        }
                    });
                }
            }
            if (this.a != null) {
                this.a.onAnimationStart(animation);
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.c != null && this.b) {
                this.c.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        z.a(this.a.c, 0, null);
                    }
                });
            }
            if (this.a != null) {
                this.a.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationRepeat(animation);
            }
        }
    }

    /* compiled from: FragmentManager */
    static class b {
        public static final int[] a = new int[]{16842755, 16842960, 16842961};
    }

    l() {
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        b = z;
    }

    static boolean a(Animation animation) {
        if (animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(animation instanceof AnimationSet)) {
            return false;
        }
        List animations = ((AnimationSet) animation).getAnimations();
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean a(View view, Animation animation) {
        return VERSION.SDK_INT >= 19 && z.g(view) == 0 && z.t(view) && a(animation);
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new d("FragmentManager"));
        if (this.o != null) {
            try {
                this.o.a("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                a("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public m a() {
        return new e(this);
    }

    public boolean b() {
        return g();
    }

    public void c() {
        a(new Runnable(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.o.j(), null, -1, 0);
            }
        }, false);
    }

    public boolean d() {
        w();
        b();
        return a(this.o.j(), null, -1, 0);
    }

    public void a(final int i, final int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Bad id: " + i);
        }
        a(new Runnable(this) {
            final /* synthetic */ l c;

            public void run() {
                this.c.a(this.c.o.j(), null, i, i2);
            }
        }, false);
    }

    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    public Fragment a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.f.size()) {
            a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        }
        Fragment fragment = (Fragment) this.f.get(i);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public List<Fragment> e() {
        return this.f;
    }

    public SavedState a(Fragment fragment) {
        if (fragment.mIndex < 0) {
            a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0) {
            return null;
        }
        Bundle g = g(fragment);
        if (g != null) {
            return new SavedState(g);
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.q != null) {
            c.a(this.q, stringBuilder);
        } else {
            c.a(this.o, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.f != null) {
            size = this.f.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.dump(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        if (this.g != null) {
            size = this.g.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.g.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.j != null) {
            size = this.j.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.j.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.i != null) {
            size = this.i.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    e eVar = (e) this.i.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(eVar.toString());
                    eVar.a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.k != null) {
                int size2 = this.k.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        eVar = (e) this.k.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(eVar);
                    }
                }
            }
            if (this.l != null && this.l.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.l.toArray()));
            }
        }
        if (this.c != null) {
            i = this.c.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.c.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(runnable);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.o);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.p);
        if (this.q != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.q);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.t);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.u);
        if (this.s) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.s);
        }
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.v);
        }
        if (this.h != null && this.h.size() > 0) {
            printWriter.print(str);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.h.toArray()));
        }
    }

    static Animation a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(A);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(B);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    static Animation a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(B);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    Animation a(Fragment fragment, int i, boolean z, int i2) {
        Animation onCreateAnimation = fragment.onCreateAnimation(i, z, fragment.mNextAnim);
        if (onCreateAnimation != null) {
            return onCreateAnimation;
        }
        if (fragment.mNextAnim != 0) {
            onCreateAnimation = AnimationUtils.loadAnimation(this.o.i(), fragment.mNextAnim);
            if (onCreateAnimation != null) {
                return onCreateAnimation;
            }
        }
        if (i == 0) {
            return null;
        }
        int b = b(i, z);
        if (b < 0) {
            return null;
        }
        switch (b) {
            case 1:
                return a(this.o.i(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(this.o.i(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(this.o.i(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(this.o.i(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(this.o.i(), 0.0f, 1.0f);
            case 6:
                return a(this.o.i(), 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.o.e()) {
                    i2 = this.o.f();
                }
                if (i2 == 0) {
                    return null;
                }
                return null;
        }
    }

    public void b(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.e) {
            this.w = true;
            return;
        }
        fragment.mDeferStart = false;
        a(fragment, this.n, 0, 0, false);
    }

    private void b(View view, Animation animation) {
        if (view != null && animation != null && a(view, animation)) {
            AnimationListener animationListener;
            try {
                if (r == null) {
                    r = Animation.class.getDeclaredField("mListener");
                    r.setAccessible(true);
                }
                animationListener = (AnimationListener) r.get(animation);
            } catch (Throwable e) {
                Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e);
                animationListener = null;
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
                animationListener = null;
            }
            animation.setAnimationListener(new a(view, animation, animationListener));
        }
    }

    void a(boolean z) {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment fragment = (Fragment) this.f.get(i);
                if (fragment != null) {
                    fragment.mRetainLoader = z;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(final android.support.v4.app.Fragment r11, int r12, int r13, int r14, boolean r15) {
        /*
        r10 = this;
        r9 = 4;
        r6 = 3;
        r5 = 1;
        r3 = 0;
        r7 = 0;
        r0 = r11.mAdded;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r11.mDetached;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r12 <= r5) goto L_0x0010;
    L_0x000f:
        r12 = r5;
    L_0x0010:
        r0 = r11.mRemoving;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r11.mState;
        if (r12 <= r0) goto L_0x001a;
    L_0x0018:
        r12 = r11.mState;
    L_0x001a:
        r0 = r11.mDeferStart;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r11.mState;
        if (r0 >= r9) goto L_0x0025;
    L_0x0022:
        if (r12 <= r6) goto L_0x0025;
    L_0x0024:
        r12 = r6;
    L_0x0025:
        r0 = r11.mState;
        if (r0 >= r12) goto L_0x0285;
    L_0x0029:
        r0 = r11.mFromLayout;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r11.mInLayout;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r11.mAnimatingAway = r7;
        r2 = r11.mStateAfterAnimating;
        r0 = r10;
        r1 = r11;
        r4 = r3;
        r0.a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r11.mState;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0146;
            case 2: goto L_0x021c;
            case 3: goto L_0x021c;
            case 4: goto L_0x023f;
            default: goto L_0x0045;
        };
    L_0x0045:
        r11.mState = r12;
        goto L_0x0031;
    L_0x0048:
        r0 = a;
        if (r0 == 0) goto L_0x0066;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0066:
        r0 = r11.mSavedFragmentState;
        if (r0 == 0) goto L_0x00b2;
    L_0x006a:
        r0 = r11.mSavedFragmentState;
        r1 = r10.o;
        r1 = r1.i();
        r1 = r1.getClassLoader();
        r0.setClassLoader(r1);
        r0 = r11.mSavedFragmentState;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r11.mSavedViewState = r0;
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_state";
        r0 = r10.a(r0, r1);
        r11.mTarget = r0;
        r0 = r11.mTarget;
        if (r0 == 0) goto L_0x009e;
    L_0x0093:
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r11.mTargetRequestCode = r0;
    L_0x009e:
        r0 = r11.mSavedFragmentState;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r11.mUserVisibleHint = r0;
        r0 = r11.mUserVisibleHint;
        if (r0 != 0) goto L_0x00b2;
    L_0x00ad:
        r11.mDeferStart = r5;
        if (r12 <= r6) goto L_0x00b2;
    L_0x00b1:
        r12 = r6;
    L_0x00b2:
        r0 = r10.o;
        r11.mHost = r0;
        r0 = r10.q;
        r11.mParentFragment = r0;
        r0 = r10.q;
        if (r0 == 0) goto L_0x00f4;
    L_0x00be:
        r0 = r10.q;
        r0 = r0.mChildFragmentManager;
    L_0x00c2:
        r11.mFragmentManager = r0;
        r11.mCalled = r3;
        r0 = r10.o;
        r0 = r0.i();
        r11.onAttach(r0);
        r0 = r11.mCalled;
        if (r0 != 0) goto L_0x00fb;
    L_0x00d3:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00f4:
        r0 = r10.o;
        r0 = r0.k();
        goto L_0x00c2;
    L_0x00fb:
        r0 = r11.mParentFragment;
        if (r0 != 0) goto L_0x0104;
    L_0x00ff:
        r0 = r10.o;
        r0.b(r11);
    L_0x0104:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x010d;
    L_0x0108:
        r0 = r11.mSavedFragmentState;
        r11.performCreate(r0);
    L_0x010d:
        r11.mRetaining = r3;
        r0 = r11.mFromLayout;
        if (r0 == 0) goto L_0x0146;
    L_0x0113:
        r0 = r11.mSavedFragmentState;
        r0 = r11.getLayoutInflater(r0);
        r1 = r11.mSavedFragmentState;
        r0 = r11.performCreateView(r0, r7, r1);
        r11.mView = r0;
        r0 = r11.mView;
        if (r0 == 0) goto L_0x0274;
    L_0x0125:
        r0 = r11.mView;
        r11.mInnerView = r0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 11;
        if (r0 < r1) goto L_0x026a;
    L_0x012f:
        r0 = r11.mView;
        android.support.v4.view.z.a(r0, r3);
    L_0x0134:
        r0 = r11.mHidden;
        if (r0 == 0) goto L_0x013f;
    L_0x0138:
        r0 = r11.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x013f:
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r11.onViewCreated(r0, r1);
    L_0x0146:
        if (r12 <= r5) goto L_0x021c;
    L_0x0148:
        r0 = a;
        if (r0 == 0) goto L_0x0166;
    L_0x014c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0166:
        r0 = r11.mFromLayout;
        if (r0 != 0) goto L_0x020c;
    L_0x016a:
        r0 = r11.mContainerId;
        if (r0 == 0) goto L_0x03ee;
    L_0x016e:
        r0 = r10.p;
        r1 = r11.mContainerId;
        r0 = r0.a(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x01c0;
    L_0x017a:
        r1 = r11.mRestored;
        if (r1 != 0) goto L_0x01c0;
    L_0x017e:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "No view found for id 0x";
        r2 = r2.append(r4);
        r4 = r11.mContainerId;
        r4 = java.lang.Integer.toHexString(r4);
        r2 = r2.append(r4);
        r4 = " (";
        r2 = r2.append(r4);
        r4 = r11.getResources();
        r8 = r11.mContainerId;
        r4 = r4.getResourceName(r8);
        r2 = r2.append(r4);
        r4 = ") for fragment ";
        r2 = r2.append(r4);
        r2 = r2.append(r11);
        r2 = r2.toString();
        r1.<init>(r2);
        r10.a(r1);
    L_0x01c0:
        r11.mContainer = r0;
        r1 = r11.mSavedFragmentState;
        r1 = r11.getLayoutInflater(r1);
        r2 = r11.mSavedFragmentState;
        r1 = r11.performCreateView(r1, r0, r2);
        r11.mView = r1;
        r1 = r11.mView;
        if (r1 == 0) goto L_0x0282;
    L_0x01d4:
        r1 = r11.mView;
        r11.mInnerView = r1;
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 11;
        if (r1 < r2) goto L_0x0278;
    L_0x01de:
        r1 = r11.mView;
        android.support.v4.view.z.a(r1, r3);
    L_0x01e3:
        if (r0 == 0) goto L_0x01fa;
    L_0x01e5:
        r1 = r10.a(r11, r13, r5, r14);
        if (r1 == 0) goto L_0x01f5;
    L_0x01eb:
        r2 = r11.mView;
        r10.b(r2, r1);
        r2 = r11.mView;
        r2.startAnimation(r1);
    L_0x01f5:
        r1 = r11.mView;
        r0.addView(r1);
    L_0x01fa:
        r0 = r11.mHidden;
        if (r0 == 0) goto L_0x0205;
    L_0x01fe:
        r0 = r11.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x0205:
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r11.onViewCreated(r0, r1);
    L_0x020c:
        r0 = r11.mSavedFragmentState;
        r11.performActivityCreated(r0);
        r0 = r11.mView;
        if (r0 == 0) goto L_0x021a;
    L_0x0215:
        r0 = r11.mSavedFragmentState;
        r11.restoreViewState(r0);
    L_0x021a:
        r11.mSavedFragmentState = r7;
    L_0x021c:
        if (r12 <= r6) goto L_0x023f;
    L_0x021e:
        r0 = a;
        if (r0 == 0) goto L_0x023c;
    L_0x0222:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x023c:
        r11.performStart();
    L_0x023f:
        if (r12 <= r9) goto L_0x0045;
    L_0x0241:
        r0 = a;
        if (r0 == 0) goto L_0x025f;
    L_0x0245:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x025f:
        r11.mResumed = r5;
        r11.performResume();
        r11.mSavedFragmentState = r7;
        r11.mSavedViewState = r7;
        goto L_0x0045;
    L_0x026a:
        r0 = r11.mView;
        r0 = android.support.v4.app.q.a(r0);
        r11.mView = r0;
        goto L_0x0134;
    L_0x0274:
        r11.mInnerView = r7;
        goto L_0x0146;
    L_0x0278:
        r1 = r11.mView;
        r1 = android.support.v4.app.q.a(r1);
        r11.mView = r1;
        goto L_0x01e3;
    L_0x0282:
        r11.mInnerView = r7;
        goto L_0x020c;
    L_0x0285:
        r0 = r11.mState;
        if (r0 <= r12) goto L_0x0045;
    L_0x0289:
        r0 = r11.mState;
        switch(r0) {
            case 1: goto L_0x0290;
            case 2: goto L_0x0316;
            case 3: goto L_0x02f3;
            case 4: goto L_0x02d0;
            case 5: goto L_0x02aa;
            default: goto L_0x028e;
        };
    L_0x028e:
        goto L_0x0045;
    L_0x0290:
        if (r12 >= r5) goto L_0x0045;
    L_0x0292:
        r0 = r10.u;
        if (r0 == 0) goto L_0x02a1;
    L_0x0296:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x02a1;
    L_0x029a:
        r0 = r11.mAnimatingAway;
        r11.mAnimatingAway = r7;
        r0.clearAnimation();
    L_0x02a1:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x0387;
    L_0x02a5:
        r11.mStateAfterAnimating = r12;
        r12 = r5;
        goto L_0x0045;
    L_0x02aa:
        r0 = 5;
        if (r12 >= r0) goto L_0x02d0;
    L_0x02ad:
        r0 = a;
        if (r0 == 0) goto L_0x02cb;
    L_0x02b1:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02cb:
        r11.performPause();
        r11.mResumed = r3;
    L_0x02d0:
        if (r12 >= r9) goto L_0x02f3;
    L_0x02d2:
        r0 = a;
        if (r0 == 0) goto L_0x02f0;
    L_0x02d6:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02f0:
        r11.performStop();
    L_0x02f3:
        if (r12 >= r6) goto L_0x0316;
    L_0x02f5:
        r0 = a;
        if (r0 == 0) goto L_0x0313;
    L_0x02f9:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0313:
        r11.performReallyStop();
    L_0x0316:
        r0 = 2;
        if (r12 >= r0) goto L_0x0290;
    L_0x0319:
        r0 = a;
        if (r0 == 0) goto L_0x0337;
    L_0x031d:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0337:
        r0 = r11.mView;
        if (r0 == 0) goto L_0x034a;
    L_0x033b:
        r0 = r10.o;
        r0 = r0.a(r11);
        if (r0 == 0) goto L_0x034a;
    L_0x0343:
        r0 = r11.mSavedViewState;
        if (r0 != 0) goto L_0x034a;
    L_0x0347:
        r10.f(r11);
    L_0x034a:
        r11.performDestroyView();
        r0 = r11.mView;
        if (r0 == 0) goto L_0x037f;
    L_0x0351:
        r0 = r11.mContainer;
        if (r0 == 0) goto L_0x037f;
    L_0x0355:
        r0 = r10.n;
        if (r0 <= 0) goto L_0x03eb;
    L_0x0359:
        r0 = r10.u;
        if (r0 != 0) goto L_0x03eb;
    L_0x035d:
        r0 = r10.a(r11, r13, r3, r14);
    L_0x0361:
        if (r0 == 0) goto L_0x0378;
    L_0x0363:
        r1 = r11.mView;
        r11.mAnimatingAway = r1;
        r11.mStateAfterAnimating = r12;
        r1 = r11.mView;
        r2 = new android.support.v4.app.l$4;
        r2.<init>(r10, r1, r0, r11);
        r0.setAnimationListener(r2);
        r1 = r11.mView;
        r1.startAnimation(r0);
    L_0x0378:
        r0 = r11.mContainer;
        r1 = r11.mView;
        r0.removeView(r1);
    L_0x037f:
        r11.mContainer = r7;
        r11.mView = r7;
        r11.mInnerView = r7;
        goto L_0x0290;
    L_0x0387:
        r0 = a;
        if (r0 == 0) goto L_0x03a5;
    L_0x038b:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x03a5:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x03ac;
    L_0x03a9:
        r11.performDestroy();
    L_0x03ac:
        r11.mCalled = r3;
        r11.onDetach();
        r0 = r11.mCalled;
        if (r0 != 0) goto L_0x03d6;
    L_0x03b5:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x03d6:
        if (r15 != 0) goto L_0x0045;
    L_0x03d8:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x03e1;
    L_0x03dc:
        r10.e(r11);
        goto L_0x0045;
    L_0x03e1:
        r11.mHost = r7;
        r11.mParentFragment = r7;
        r11.mFragmentManager = r7;
        r11.mChildFragmentManager = r7;
        goto L_0x0045;
    L_0x03eb:
        r0 = r7;
        goto L_0x0361;
    L_0x03ee:
        r0 = r7;
        goto L_0x01c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.l.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    void c(Fragment fragment) {
        a(fragment, this.n, 0, 0, false);
    }

    void a(int i, boolean z) {
        a(i, 0, 0, z);
    }

    void a(int i, int i2, int i3, boolean z) {
        if (this.o == null && i != 0) {
            throw new IllegalStateException("No host");
        } else if (z || this.n != i) {
            this.n = i;
            if (this.f != null) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.f.size()) {
                    int a;
                    Fragment fragment = (Fragment) this.f.get(i4);
                    if (fragment != null) {
                        a(fragment, i, i2, i3, false);
                        if (fragment.mLoaderManager != null) {
                            a = i5 | fragment.mLoaderManager.a();
                            i4++;
                            i5 = a;
                        }
                    }
                    a = i5;
                    i4++;
                    i5 = a;
                }
                if (i5 == 0) {
                    f();
                }
                if (this.s && this.o != null && this.n == 5) {
                    this.o.d();
                    this.s = false;
                }
            }
        }
    }

    void f() {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment fragment = (Fragment) this.f.get(i);
                if (fragment != null) {
                    b(fragment);
                }
            }
        }
    }

    void d(Fragment fragment) {
        if (fragment.mIndex < 0) {
            if (this.h == null || this.h.size() <= 0) {
                if (this.f == null) {
                    this.f = new ArrayList();
                }
                fragment.setIndex(this.f.size(), this.q);
                this.f.add(fragment);
            } else {
                fragment.setIndex(((Integer) this.h.remove(this.h.size() - 1)).intValue(), this.q);
                this.f.set(fragment.mIndex, fragment);
            }
            if (a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    void e(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f.set(fragment.mIndex, null);
            if (this.h == null) {
                this.h = new ArrayList();
            }
            this.h.add(Integer.valueOf(fragment.mIndex));
            this.o.b(fragment.mWho);
            fragment.initState();
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        d(fragment);
        if (!fragment.mDetached) {
            if (this.g.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.g.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.s = true;
            }
            if (z) {
                c(fragment);
            }
        }
    }

    public void a(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            int i3;
            if (this.g != null) {
                this.g.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.s = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            if (z) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            a(fragment, i3, i, i2, false);
        }
    }

    public void b(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mView != null) {
                Animation a = a(fragment, i, false, i2);
                if (a != null) {
                    b(fragment.mView, a);
                    fragment.mView.startAnimation(a);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.s = true;
            }
            fragment.onHiddenChanged(true);
        }
    }

    public void c(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (fragment.mView != null) {
                Animation a = a(fragment, i, true, i2);
                if (a != null) {
                    b(fragment.mView, a);
                    fragment.mView.startAnimation(a);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.s = true;
            }
            fragment.onHiddenChanged(false);
        }
    }

    public void d(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.g != null) {
                    if (a) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.g.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.s = true;
                }
                fragment.mAdded = false;
                a(fragment, 1, i, i2, false);
            }
        }
    }

    public void e(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                if (this.g.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.g.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.s = true;
                }
                a(fragment, this.n, i, i2, false);
            }
        }
    }

    public Fragment a(int i) {
        int size;
        Fragment fragment;
        if (this.g != null) {
            for (size = this.g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.g.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            }
        }
        if (this.f != null) {
            for (size = this.f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment a(String str) {
        int size;
        Fragment fragment;
        if (!(this.g == null || str == null)) {
            for (size = this.g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.g.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (!(this.f == null || str == null)) {
            for (size = this.f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment b(String str) {
        if (!(this.f == null || str == null)) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.f.get(size);
                if (fragment != null) {
                    fragment = fragment.findFragmentByWho(str);
                    if (fragment != null) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }

    private void w() {
        if (this.t) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.v != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.v);
        }
    }

    public void a(Runnable runnable, boolean z) {
        if (!z) {
            w();
        }
        synchronized (this) {
            if (this.u || this.o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(runnable);
            if (this.c.size() == 1) {
                this.o.j().removeCallbacks(this.z);
                this.o.j().post(this.z);
            }
        }
    }

    public int a(e eVar) {
        int size;
        synchronized (this) {
            if (this.l == null || this.l.size() <= 0) {
                if (this.k == null) {
                    this.k = new ArrayList();
                }
                size = this.k.size();
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + eVar);
                }
                this.k.add(eVar);
            } else {
                size = ((Integer) this.l.remove(this.l.size() - 1)).intValue();
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + eVar);
                }
                this.k.set(size, eVar);
            }
        }
        return size;
    }

    public void a(int i, e eVar) {
        synchronized (this) {
            if (this.k == null) {
                this.k = new ArrayList();
            }
            int size = this.k.size();
            if (i < size) {
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + eVar);
                }
                this.k.set(i, eVar);
            } else {
                while (size < i) {
                    this.k.add(null);
                    if (this.l == null) {
                        this.l = new ArrayList();
                    }
                    if (a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.l.add(Integer.valueOf(size));
                    size++;
                }
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + eVar);
                }
                this.k.add(eVar);
            }
        }
    }

    public void b(int i) {
        synchronized (this) {
            this.k.set(i, null);
            if (this.l == null) {
                this.l = new ArrayList();
            }
            if (a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.l.add(Integer.valueOf(i));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean g() {
        /*
        r6 = this;
        r0 = 1;
        r2 = 0;
        r1 = r6.e;
        if (r1 == 0) goto L_0x000f;
    L_0x0006:
        r0 = new java.lang.IllegalStateException;
        r1 = "Recursive entry to executePendingTransactions";
        r0.<init>(r1);
        throw r0;
    L_0x000f:
        r1 = android.os.Looper.myLooper();
        r3 = r6.o;
        r3 = r3.j();
        r3 = r3.getLooper();
        if (r1 == r3) goto L_0x0028;
    L_0x001f:
        r0 = new java.lang.IllegalStateException;
        r1 = "Must be called from main thread of process";
        r0.<init>(r1);
        throw r0;
    L_0x0028:
        r1 = r2;
    L_0x0029:
        monitor-enter(r6);
        r3 = r6.c;	 Catch:{ all -> 0x009d }
        if (r3 == 0) goto L_0x0036;
    L_0x002e:
        r3 = r6.c;	 Catch:{ all -> 0x009d }
        r3 = r3.size();	 Catch:{ all -> 0x009d }
        if (r3 != 0) goto L_0x005e;
    L_0x0036:
        monitor-exit(r6);	 Catch:{ all -> 0x009d }
        r0 = r6.w;
        if (r0 == 0) goto L_0x00ab;
    L_0x003b:
        r3 = r2;
        r4 = r2;
    L_0x003d:
        r0 = r6.f;
        r0 = r0.size();
        if (r3 >= r0) goto L_0x00a4;
    L_0x0045:
        r0 = r6.f;
        r0 = r0.get(r3);
        r0 = (android.support.v4.app.Fragment) r0;
        if (r0 == 0) goto L_0x005a;
    L_0x004f:
        r5 = r0.mLoaderManager;
        if (r5 == 0) goto L_0x005a;
    L_0x0053:
        r0 = r0.mLoaderManager;
        r0 = r0.a();
        r4 = r4 | r0;
    L_0x005a:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x003d;
    L_0x005e:
        r1 = r6.c;	 Catch:{ all -> 0x009d }
        r3 = r1.size();	 Catch:{ all -> 0x009d }
        r1 = r6.d;	 Catch:{ all -> 0x009d }
        if (r1 == 0) goto L_0x006d;
    L_0x0068:
        r1 = r6.d;	 Catch:{ all -> 0x009d }
        r1 = r1.length;	 Catch:{ all -> 0x009d }
        if (r1 >= r3) goto L_0x0071;
    L_0x006d:
        r1 = new java.lang.Runnable[r3];	 Catch:{ all -> 0x009d }
        r6.d = r1;	 Catch:{ all -> 0x009d }
    L_0x0071:
        r1 = r6.c;	 Catch:{ all -> 0x009d }
        r4 = r6.d;	 Catch:{ all -> 0x009d }
        r1.toArray(r4);	 Catch:{ all -> 0x009d }
        r1 = r6.c;	 Catch:{ all -> 0x009d }
        r1.clear();	 Catch:{ all -> 0x009d }
        r1 = r6.o;	 Catch:{ all -> 0x009d }
        r1 = r1.j();	 Catch:{ all -> 0x009d }
        r4 = r6.z;	 Catch:{ all -> 0x009d }
        r1.removeCallbacks(r4);	 Catch:{ all -> 0x009d }
        monitor-exit(r6);	 Catch:{ all -> 0x009d }
        r6.e = r0;
        r1 = r2;
    L_0x008c:
        if (r1 >= r3) goto L_0x00a0;
    L_0x008e:
        r4 = r6.d;
        r4 = r4[r1];
        r4.run();
        r4 = r6.d;
        r5 = 0;
        r4[r1] = r5;
        r1 = r1 + 1;
        goto L_0x008c;
    L_0x009d:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x009d }
        throw r0;
    L_0x00a0:
        r6.e = r2;
        r1 = r0;
        goto L_0x0029;
    L_0x00a4:
        if (r4 != 0) goto L_0x00ab;
    L_0x00a6:
        r6.w = r2;
        r6.f();
    L_0x00ab:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.l.g():boolean");
    }

    void h() {
        if (this.m != null) {
            for (int i = 0; i < this.m.size(); i++) {
                ((android.support.v4.app.k.a) this.m.get(i)).a();
            }
        }
    }

    void b(e eVar) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(eVar);
        h();
    }

    boolean a(Handler handler, String str, int i, int i2) {
        if (this.i == null) {
            return false;
        }
        int size;
        e eVar;
        if (str == null && i < 0 && (i2 & 1) == 0) {
            size = this.i.size() - 1;
            if (size < 0) {
                return false;
            }
            eVar = (e) this.i.remove(size);
            SparseArray sparseArray = new SparseArray();
            SparseArray sparseArray2 = new SparseArray();
            eVar.a(sparseArray, sparseArray2);
            eVar.a(true, null, sparseArray, sparseArray2);
            h();
        } else {
            int size2;
            size = -1;
            if (str != null || i >= 0) {
                size2 = this.i.size() - 1;
                while (size2 >= 0) {
                    eVar = (e) this.i.get(size2);
                    if ((str != null && str.equals(eVar.c())) || (i >= 0 && i == eVar.p)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    size2--;
                    while (size2 >= 0) {
                        eVar = (e) this.i.get(size2);
                        if ((str == null || !str.equals(eVar.c())) && (i < 0 || i != eVar.p)) {
                            break;
                        }
                        size2--;
                    }
                }
                size = size2;
            }
            if (size == this.i.size() - 1) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (size2 = this.i.size() - 1; size2 > size; size2--) {
                arrayList.add(this.i.remove(size2));
            }
            int size3 = arrayList.size() - 1;
            SparseArray sparseArray3 = new SparseArray();
            SparseArray sparseArray4 = new SparseArray();
            for (size2 = 0; size2 <= size3; size2++) {
                ((e) arrayList.get(size2)).a(sparseArray3, sparseArray4);
            }
            android.support.v4.app.e.b bVar = null;
            int i3 = 0;
            while (i3 <= size3) {
                boolean z;
                if (a) {
                    Log.v("FragmentManager", "Popping back stack state: " + arrayList.get(i3));
                }
                eVar = (e) arrayList.get(i3);
                if (i3 == size3) {
                    z = true;
                } else {
                    z = false;
                }
                i3++;
                bVar = eVar.a(z, bVar, sparseArray3, sparseArray4);
            }
            h();
        }
        return true;
    }

    ArrayList<Fragment> i() {
        ArrayList<Fragment> arrayList = null;
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment fragment = (Fragment) this.f.get(i);
                if (fragment != null && fragment.mRetainInstance) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.mRetaining = true;
                    fragment.mTargetIndex = fragment.mTarget != null ? fragment.mTarget.mIndex : -1;
                    if (a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
            }
        }
        return arrayList;
    }

    void f(Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.y == null) {
                this.y = new SparseArray();
            } else {
                this.y.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.y);
            if (this.y.size() > 0) {
                fragment.mSavedViewState = this.y;
                this.y = null;
            }
        }
    }

    Bundle g(Fragment fragment) {
        Bundle bundle;
        if (this.x == null) {
            this.x = new Bundle();
        }
        fragment.performSaveInstanceState(this.x);
        if (this.x.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.x;
            this.x = null;
        }
        if (fragment.mView != null) {
            f(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    Parcelable j() {
        BackStackState[] backStackStateArr = null;
        g();
        if (b) {
            this.t = true;
        }
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        int size = this.f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.f.get(i);
            if (fragment != null) {
                if (fragment.mIndex < 0) {
                    a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.mState <= 0 || fragmentState.j != null) {
                    fragmentState.j = fragment.mSavedFragmentState;
                } else {
                    fragmentState.j = g(fragment);
                    if (fragment.mTarget != null) {
                        if (fragment.mTarget.mIndex < 0) {
                            a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTarget));
                        }
                        if (fragmentState.j == null) {
                            fragmentState.j = new Bundle();
                        }
                        a(fragmentState.j, "android:target_state", fragment.mTarget);
                        if (fragment.mTargetRequestCode != 0) {
                            fragmentState.j.putInt("android:target_req_state", fragment.mTargetRequestCode);
                        }
                    }
                }
                if (a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.j);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            FragmentManagerState fragmentManagerState;
            if (this.g != null) {
                i = this.g.size();
                if (i > 0) {
                    iArr = new int[i];
                    for (i2 = 0; i2 < i; i2++) {
                        iArr[i2] = ((Fragment) this.g.get(i2)).mIndex;
                        if (iArr[i2] < 0) {
                            a(new IllegalStateException("Failure saving state: active " + this.g.get(i2) + " has cleared index: " + iArr[i2]));
                        }
                        if (a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.g.get(i2));
                        }
                    }
                    if (this.i != null) {
                        i = this.i.size();
                        if (i > 0) {
                            backStackStateArr = new BackStackState[i];
                            for (i2 = 0; i2 < i; i2++) {
                                backStackStateArr[i2] = new BackStackState((e) this.i.get(i2));
                                if (a) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.i.get(i2));
                                }
                            }
                        }
                    }
                    fragmentManagerState = new FragmentManagerState();
                    fragmentManagerState.a = fragmentStateArr;
                    fragmentManagerState.b = iArr;
                    fragmentManagerState.c = backStackStateArr;
                    return fragmentManagerState;
                }
            }
            iArr = null;
            if (this.i != null) {
                i = this.i.size();
                if (i > 0) {
                    backStackStateArr = new BackStackState[i];
                    for (i2 = 0; i2 < i; i2++) {
                        backStackStateArr[i2] = new BackStackState((e) this.i.get(i2));
                        if (a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.i.get(i2));
                        }
                    }
                }
            }
            fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.a = fragmentStateArr;
            fragmentManagerState.b = iArr;
            fragmentManagerState.c = backStackStateArr;
            return fragmentManagerState;
        } else if (!a) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    void a(Parcelable parcelable, List<Fragment> list) {
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.a != null) {
                int i;
                Fragment fragment;
                int i2;
                if (list != null) {
                    for (i = 0; i < list.size(); i++) {
                        fragment = (Fragment) list.get(i);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        FragmentState fragmentState = fragmentManagerState.a[fragment.mIndex];
                        fragmentState.k = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.mTarget = null;
                        if (fragmentState.j != null) {
                            fragmentState.j.setClassLoader(this.o.i().getClassLoader());
                            fragment.mSavedViewState = fragmentState.j.getSparseParcelableArray("android:view_state");
                            fragment.mSavedFragmentState = fragmentState.j;
                        }
                    }
                }
                this.f = new ArrayList(fragmentManagerState.a.length);
                if (this.h != null) {
                    this.h.clear();
                }
                for (i2 = 0; i2 < fragmentManagerState.a.length; i2++) {
                    FragmentState fragmentState2 = fragmentManagerState.a[i2];
                    if (fragmentState2 != null) {
                        Fragment a = fragmentState2.a(this.o, this.q);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + a);
                        }
                        this.f.add(a);
                        fragmentState2.k = null;
                    } else {
                        this.f.add(null);
                        if (this.h == null) {
                            this.h = new ArrayList();
                        }
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + i2);
                        }
                        this.h.add(Integer.valueOf(i2));
                    }
                }
                if (list != null) {
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        fragment = (Fragment) list.get(i3);
                        if (fragment.mTargetIndex >= 0) {
                            if (fragment.mTargetIndex < this.f.size()) {
                                fragment.mTarget = (Fragment) this.f.get(fragment.mTargetIndex);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                                fragment.mTarget = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.b != null) {
                    this.g = new ArrayList(fragmentManagerState.b.length);
                    for (i = 0; i < fragmentManagerState.b.length; i++) {
                        fragment = (Fragment) this.f.get(fragmentManagerState.b[i]);
                        if (fragment == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.b[i]));
                        }
                        fragment.mAdded = true;
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + fragment);
                        }
                        if (this.g.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.g.add(fragment);
                    }
                } else {
                    this.g = null;
                }
                if (fragmentManagerState.c != null) {
                    this.i = new ArrayList(fragmentManagerState.c.length);
                    for (i2 = 0; i2 < fragmentManagerState.c.length; i2++) {
                        e a2 = fragmentManagerState.c[i2].a(this);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.p + "): " + a2);
                            a2.a("  ", new PrintWriter(new d("FragmentManager")), false);
                        }
                        this.i.add(a2);
                        if (a2.p >= 0) {
                            a(a2.p, a2);
                        }
                    }
                    return;
                }
                this.i = null;
            }
        }
    }

    public void a(j jVar, h hVar, Fragment fragment) {
        if (this.o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.o = jVar;
        this.p = hVar;
        this.q = fragment;
    }

    public void k() {
        this.t = false;
    }

    public void l() {
        this.t = false;
        a(1, false);
    }

    public void m() {
        this.t = false;
        a(2, false);
    }

    public void n() {
        this.t = false;
        a(4, false);
    }

    public void o() {
        this.t = false;
        a(5, false);
    }

    public void p() {
        a(4, false);
    }

    public void q() {
        this.t = true;
        a(3, false);
    }

    public void r() {
        a(2, false);
    }

    public void s() {
        a(1, false);
    }

    public void t() {
        this.u = true;
        g();
        a(0, false);
        this.o = null;
        this.p = null;
        this.q = null;
    }

    public void a(Configuration configuration) {
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                Fragment fragment = (Fragment) this.g.get(i);
                if (fragment != null) {
                    fragment.performConfigurationChanged(configuration);
                }
            }
        }
    }

    public void u() {
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                Fragment fragment = (Fragment) this.g.get(i);
                if (fragment != null) {
                    fragment.performLowMemory();
                }
            }
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        boolean z;
        Fragment fragment;
        int i = 0;
        ArrayList arrayList = null;
        if (this.g != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.g.size()) {
                fragment = (Fragment) this.g.get(i2);
                if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                    z = true;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i2++;
                z = z;
            }
        } else {
            z = false;
        }
        if (this.j != null) {
            while (i < this.j.size()) {
                fragment = (Fragment) this.j.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.onDestroyOptionsMenu();
                }
                i++;
            }
        }
        this.j = arrayList;
        return z;
    }

    public boolean a(Menu menu) {
        if (this.g == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean a(MenuItem menuItem) {
        if (this.g == null) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(MenuItem menuItem) {
        if (this.g == null) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void b(Menu menu) {
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                Fragment fragment = (Fragment) this.g.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public static int c(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int b(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String string;
        String attributeValue = attributeSet.getAttributeValue(null, WxPerformanceHandle.MESSAGE_CLASS);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.a);
        if (attributeValue == null) {
            string = obtainStyledAttributes.getString(0);
        } else {
            string = attributeValue;
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.o.i(), string)) {
            return null;
        }
        int id;
        if (view != null) {
            id = view.getId();
        } else {
            id = 0;
        }
        if (id == -1 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        Fragment fragment;
        Fragment a = resourceId != -1 ? a(resourceId) : null;
        if (a == null && string2 != null) {
            a = a(string2);
        }
        if (a == null && id != -1) {
            a = a(id);
        }
        if (a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + a);
        }
        if (a == null) {
            Fragment instantiate = Fragment.instantiate(context, string);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : id;
            instantiate.mContainerId = id;
            instantiate.mTag = string2;
            instantiate.mInLayout = true;
            instantiate.mFragmentManager = this;
            instantiate.mHost = this.o;
            instantiate.onInflate(this.o.i(), attributeSet, instantiate.mSavedFragmentState);
            a(instantiate, true);
            fragment = instantiate;
        } else if (a.mInLayout) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + string);
        } else {
            a.mInLayout = true;
            if (!a.mRetaining) {
                a.onInflate(this.o.i(), attributeSet, a.mSavedFragmentState);
            }
            fragment = a;
        }
        if (this.n >= 1 || !fragment.mFromLayout) {
            c(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + string + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.mView.setId(resourceId);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(string2);
        }
        return fragment.mView;
    }

    m v() {
        return this;
    }
}
