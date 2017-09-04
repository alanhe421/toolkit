package android.support.v4.app;

import android.os.Build.VERSION;
import android.support.v4.util.d;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: BackStackRecord */
final class e extends m implements Runnable {
    static final boolean a = (VERSION.SDK_INT >= 21);
    final l b;
    a c;
    a d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    boolean l;
    boolean m = true;
    String n;
    boolean o;
    int p = -1;
    int q;
    CharSequence r;
    int s;
    CharSequence t;
    ArrayList<String> u;
    ArrayList<String> v;

    /* compiled from: BackStackRecord */
    static final class a {
        a a;
        a b;
        int c;
        Fragment d;
        int e;
        int f;
        int g;
        int h;
        ArrayList<Fragment> i;

        a() {
        }
    }

    /* compiled from: BackStackRecord */
    public class b {
        public android.support.v4.util.a<String, String> a = new android.support.v4.util.a();
        public ArrayList<View> b = new ArrayList();
        public android.support.v4.app.n.a c = new android.support.v4.app.n.a();
        public View d;
        final /* synthetic */ e e;

        public b(e eVar) {
            this.e = eVar;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.p >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.p);
        }
        if (this.n != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.n);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        a(str, printWriter, true);
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.n);
            printWriter.print(" mIndex=");
            printWriter.print(this.p);
            printWriter.print(" mCommitted=");
            printWriter.println(this.o);
            if (this.j != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.j));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.k));
            }
            if (!(this.f == 0 && this.g == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (!(this.h == 0 && this.i == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.h));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.i));
            }
            if (!(this.q == 0 && this.r == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.q));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.r);
            }
            if (!(this.s == 0 && this.t == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.s));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.t);
            }
        }
        if (this.c != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str2 = str + "    ";
            int i = 0;
            a aVar = this.c;
            while (aVar != null) {
                String str3;
                switch (aVar.c) {
                    case 0:
                        str3 = "NULL";
                        break;
                    case 1:
                        str3 = "ADD";
                        break;
                    case 2:
                        str3 = "REPLACE";
                        break;
                    case 3:
                        str3 = "REMOVE";
                        break;
                    case 4:
                        str3 = "HIDE";
                        break;
                    case 5:
                        str3 = "SHOW";
                        break;
                    case 6:
                        str3 = "DETACH";
                        break;
                    case 7:
                        str3 = "ATTACH";
                        break;
                    default:
                        str3 = "cmd=" + aVar.c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str3);
                printWriter.print(" ");
                printWriter.println(aVar.d);
                if (z) {
                    if (!(aVar.e == 0 && aVar.f == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f));
                    }
                    if (!(aVar.g == 0 && aVar.h == 0)) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.h));
                    }
                }
                if (aVar.i != null && aVar.i.size() > 0) {
                    for (int i2 = 0; i2 < aVar.i.size(); i2++) {
                        printWriter.print(str2);
                        if (aVar.i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str2);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(aVar.i.get(i2));
                    }
                }
                aVar = aVar.a;
                i++;
            }
        }
    }

    public e(l lVar) {
        this.b = lVar;
    }

    void a(a aVar) {
        if (this.c == null) {
            this.d = aVar;
            this.c = aVar;
        } else {
            aVar.b = this.d;
            this.d.a = aVar;
            this.d = aVar;
        }
        aVar.e = this.f;
        aVar.f = this.g;
        aVar.g = this.h;
        aVar.h = this.i;
        this.e++;
    }

    public m a(Fragment fragment, String str) {
        a(0, fragment, str, 1);
        return this;
    }

    public m a(int i, Fragment fragment) {
        a(i, fragment, null, 1);
        return this;
    }

    public m a(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    private void a(int i, Fragment fragment, String str, int i2) {
        fragment.mFragmentManager = this.b;
        if (str != null) {
            if (fragment.mTag == null || str.equals(fragment.mTag)) {
                fragment.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
        }
        if (i != 0) {
            if (fragment.mFragmentId == 0 || fragment.mFragmentId == i) {
                fragment.mFragmentId = i;
                fragment.mContainerId = i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
            }
        }
        a aVar = new a();
        aVar.c = i2;
        aVar.d = fragment;
        a(aVar);
    }

    public m b(int i, Fragment fragment) {
        return b(i, fragment, null);
    }

    public m b(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        a(i, fragment, str, 2);
        return this;
    }

    public m a(Fragment fragment) {
        a aVar = new a();
        aVar.c = 3;
        aVar.d = fragment;
        a(aVar);
        return this;
    }

    public m b(Fragment fragment) {
        a aVar = new a();
        aVar.c = 4;
        aVar.d = fragment;
        a(aVar);
        return this;
    }

    public m c(Fragment fragment) {
        a aVar = new a();
        aVar.c = 5;
        aVar.d = fragment;
        a(aVar);
        return this;
    }

    public m d(Fragment fragment) {
        a aVar = new a();
        aVar.c = 6;
        aVar.d = fragment;
        a(aVar);
        return this;
    }

    public m e(Fragment fragment) {
        a aVar = new a();
        aVar.c = 7;
        aVar.d = fragment;
        a(aVar);
        return this;
    }

    void a(int i) {
        if (this.l) {
            if (l.a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (a aVar = this.c; aVar != null; aVar = aVar.a) {
                Fragment fragment;
                if (aVar.d != null) {
                    fragment = aVar.d;
                    fragment.mBackStackNesting += i;
                    if (l.a) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.d + " to " + aVar.d.mBackStackNesting);
                    }
                }
                if (aVar.i != null) {
                    for (int size = aVar.i.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) aVar.i.get(size);
                        fragment.mBackStackNesting += i;
                        if (l.a) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }

    public int a() {
        return a(false);
    }

    public int b() {
        return a(true);
    }

    int a(boolean z) {
        if (this.o) {
            throw new IllegalStateException("commit already called");
        }
        if (l.a) {
            Log.v("FragmentManager", "Commit: " + this);
            a("  ", null, new PrintWriter(new d("FragmentManager")), null);
        }
        this.o = true;
        if (this.l) {
            this.p = this.b.a(this);
        } else {
            this.p = -1;
        }
        this.b.a((Runnable) this, z);
        return this.p;
    }

    public void run() {
        if (l.a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.l || this.p >= 0) {
            b a;
            a(1);
            if (a) {
                SparseArray sparseArray = new SparseArray();
                SparseArray sparseArray2 = new SparseArray();
                b(sparseArray, sparseArray2);
                a = a(sparseArray, sparseArray2, false);
            } else {
                a = null;
            }
            int i = a != null ? 0 : this.k;
            int i2 = a != null ? 0 : this.j;
            a aVar = this.c;
            while (aVar != null) {
                int i3 = a != null ? 0 : aVar.e;
                int i4 = a != null ? 0 : aVar.f;
                Fragment fragment;
                switch (aVar.c) {
                    case 1:
                        fragment = aVar.d;
                        fragment.mNextAnim = i3;
                        this.b.a(fragment, false);
                        break;
                    case 2:
                        Fragment fragment2;
                        Fragment fragment3 = aVar.d;
                        int i5 = fragment3.mContainerId;
                        if (this.b.g != null) {
                            fragment2 = fragment3;
                            for (int i6 = 0; i6 < this.b.g.size(); i6++) {
                                fragment = (Fragment) this.b.g.get(i6);
                                if (l.a) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment.mContainerId == i5) {
                                    if (fragment == fragment2) {
                                        fragment2 = null;
                                        aVar.d = null;
                                    } else {
                                        if (aVar.i == null) {
                                            aVar.i = new ArrayList();
                                        }
                                        aVar.i.add(fragment);
                                        fragment.mNextAnim = i4;
                                        if (this.l) {
                                            fragment.mBackStackNesting++;
                                            if (l.a) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                                            }
                                        }
                                        this.b.a(fragment, i2, i);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment3;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.mNextAnim = i3;
                        this.b.a(fragment2, false);
                        break;
                    case 3:
                        fragment = aVar.d;
                        fragment.mNextAnim = i4;
                        this.b.a(fragment, i2, i);
                        break;
                    case 4:
                        fragment = aVar.d;
                        fragment.mNextAnim = i4;
                        this.b.b(fragment, i2, i);
                        break;
                    case 5:
                        fragment = aVar.d;
                        fragment.mNextAnim = i3;
                        this.b.c(fragment, i2, i);
                        break;
                    case 6:
                        fragment = aVar.d;
                        fragment.mNextAnim = i4;
                        this.b.d(fragment, i2, i);
                        break;
                    case 7:
                        fragment = aVar.d;
                        fragment.mNextAnim = i3;
                        this.b.e(fragment, i2, i);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + aVar.c);
                }
                aVar = aVar.a;
            }
            this.b.a(this.b.n, i2, i, true);
            if (this.l) {
                this.b.b(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    private static void a(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.mContainerId;
            if (i != 0 && !fragment.isHidden() && fragment.isAdded() && fragment.getView() != null && sparseArray.get(i) == null) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void b(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.mContainerId;
            if (i != 0) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void b(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.b.p.a()) {
            for (a aVar = this.c; aVar != null; aVar = aVar.a) {
                switch (aVar.c) {
                    case 1:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    case 2:
                        Fragment fragment;
                        Fragment fragment2 = aVar.d;
                        if (this.b.g != null) {
                            fragment = fragment2;
                            for (int i = 0; i < this.b.g.size(); i++) {
                                Fragment fragment3 = (Fragment) this.b.g.get(i);
                                if (fragment == null || fragment3.mContainerId == fragment.mContainerId) {
                                    if (fragment3 == fragment) {
                                        fragment = null;
                                    } else {
                                        a((SparseArray) sparseArray, fragment3);
                                    }
                                }
                            }
                        } else {
                            fragment = fragment2;
                        }
                        b((SparseArray) sparseArray2, fragment);
                        break;
                    case 3:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 4:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 5:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    case 6:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 7:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.b.p.a()) {
            for (a aVar = this.c; aVar != null; aVar = aVar.a) {
                switch (aVar.c) {
                    case 1:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 2:
                        if (aVar.i != null) {
                            for (int size = aVar.i.size() - 1; size >= 0; size--) {
                                b((SparseArray) sparseArray2, (Fragment) aVar.i.get(size));
                            }
                        }
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 3:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    case 4:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    case 5:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    case 6:
                        b((SparseArray) sparseArray2, aVar.d);
                        break;
                    case 7:
                        a((SparseArray) sparseArray, aVar.d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public b a(boolean z, b bVar, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (l.a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            a("  ", null, new PrintWriter(new d("FragmentManager")), null);
        }
        if (a) {
            if (bVar == null) {
                if (!(sparseArray.size() == 0 && sparseArray2.size() == 0)) {
                    bVar = a((SparseArray) sparseArray, (SparseArray) sparseArray2, true);
                }
            } else if (!z) {
                a(bVar, this.v, this.u);
            }
        }
        a(-1);
        int i = bVar != null ? 0 : this.k;
        int i2 = bVar != null ? 0 : this.j;
        a aVar = this.d;
        while (aVar != null) {
            int i3 = bVar != null ? 0 : aVar.g;
            int i4 = bVar != null ? 0 : aVar.h;
            Fragment fragment;
            Fragment fragment2;
            switch (aVar.c) {
                case 1:
                    fragment = aVar.d;
                    fragment.mNextAnim = i4;
                    this.b.a(fragment, l.c(i2), i);
                    break;
                case 2:
                    fragment = aVar.d;
                    if (fragment != null) {
                        fragment.mNextAnim = i4;
                        this.b.a(fragment, l.c(i2), i);
                    }
                    if (aVar.i == null) {
                        break;
                    }
                    for (int i5 = 0; i5 < aVar.i.size(); i5++) {
                        fragment2 = (Fragment) aVar.i.get(i5);
                        fragment2.mNextAnim = i3;
                        this.b.a(fragment2, false);
                    }
                    break;
                case 3:
                    fragment2 = aVar.d;
                    fragment2.mNextAnim = i3;
                    this.b.a(fragment2, false);
                    break;
                case 4:
                    fragment2 = aVar.d;
                    fragment2.mNextAnim = i3;
                    this.b.c(fragment2, l.c(i2), i);
                    break;
                case 5:
                    fragment = aVar.d;
                    fragment.mNextAnim = i4;
                    this.b.b(fragment, l.c(i2), i);
                    break;
                case 6:
                    fragment2 = aVar.d;
                    fragment2.mNextAnim = i3;
                    this.b.e(fragment2, l.c(i2), i);
                    break;
                case 7:
                    fragment2 = aVar.d;
                    fragment2.mNextAnim = i3;
                    this.b.d(fragment2, l.c(i2), i);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.c);
            }
            aVar = aVar.b;
        }
        if (z) {
            this.b.a(this.b.n, l.c(i2), i, true);
            bVar = null;
        }
        if (this.p >= 0) {
            this.b.b(this.p);
            this.p = -1;
        }
        return bVar;
    }

    public String c() {
        return this.n;
    }

    private b a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2, boolean z) {
        int i = 0;
        b bVar = new b(this);
        bVar.d = new View(this.b.o.i());
        int i2 = 0;
        int i3 = 0;
        while (i2 < sparseArray.size()) {
            int i4;
            if (a(sparseArray.keyAt(i2), bVar, z, (SparseArray) sparseArray, (SparseArray) sparseArray2)) {
                i4 = 1;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        while (i < sparseArray2.size()) {
            i4 = sparseArray2.keyAt(i);
            if (sparseArray.get(i4) == null && a(i4, bVar, z, (SparseArray) sparseArray, (SparseArray) sparseArray2)) {
                i3 = 1;
            }
            i++;
        }
        if (i3 == 0) {
            return null;
        }
        return bVar;
    }

    private static Object a(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return n.a(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    private static Object b(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return n.a(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    private static Object a(Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return n.b(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition());
    }

    private static Object a(Object obj, Fragment fragment, ArrayList<View> arrayList, android.support.v4.util.a<String, View> aVar, View view) {
        if (obj != null) {
            return n.a(obj, fragment.getView(), arrayList, aVar, view);
        }
        return obj;
    }

    private android.support.v4.util.a<String, View> a(b bVar, Fragment fragment, boolean z) {
        android.support.v4.util.a aVar = new android.support.v4.util.a();
        if (this.u != null) {
            n.a((Map) aVar, fragment.getView());
            if (z) {
                aVar.a(this.v);
            } else {
                aVar = a(this.u, this.v, aVar);
            }
        }
        if (z) {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.a(this.v, (Map) aVar);
            }
            a(bVar, aVar, false);
        } else {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.a(this.v, (Map) aVar);
            }
            b(bVar, aVar, false);
        }
        return aVar;
    }

    private boolean a(int i, b bVar, boolean z, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        View view = (ViewGroup) this.b.p.a(i);
        if (view == null) {
            return false;
        }
        Object obj;
        ArrayList arrayList;
        Object a;
        View view2;
        android.support.v4.app.n.b anonymousClass1;
        ArrayList arrayList2;
        Map aVar;
        boolean z2;
        Object a2;
        final Fragment fragment = (Fragment) sparseArray2.get(i);
        Fragment fragment2 = (Fragment) sparseArray.get(i);
        Object a3 = a(fragment, z);
        Object a4 = a(fragment, fragment2, z);
        Object b = b(fragment2, z);
        Map map = null;
        ArrayList arrayList3 = new ArrayList();
        if (a4 != null) {
            map = a(bVar, fragment2, z);
            if (map.isEmpty()) {
                map = null;
                obj = null;
                if (a3 != null && obj == null && b == null) {
                    return false;
                }
                arrayList = new ArrayList();
                a = a(b, fragment2, arrayList, (android.support.v4.util.a) map, bVar.d);
                if (!(this.v == null || map == null)) {
                    view2 = (View) map.get(this.v.get(0));
                    if (view2 != null) {
                        if (a != null) {
                            n.a(a, view2);
                        }
                        if (obj != null) {
                            n.a(obj, view2);
                        }
                    }
                }
                anonymousClass1 = new android.support.v4.app.n.b(this) {
                    final /* synthetic */ e b;

                    public View a() {
                        return fragment.getView();
                    }
                };
                arrayList2 = new ArrayList();
                aVar = new android.support.v4.util.a();
                z2 = true;
                if (fragment != null) {
                    z2 = z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
                }
                a2 = n.a(a3, a, obj, z2);
                if (a2 != null) {
                    n.a(a3, obj, view, anonymousClass1, bVar.d, bVar.c, (Map) bVar.a, arrayList2, map, aVar, arrayList3);
                    a(view, bVar, i, a2);
                    n.a(a2, bVar.d, true);
                    a(bVar, i, a2);
                    n.a((ViewGroup) view, a2);
                    n.a(view, bVar.d, a3, arrayList2, a, arrayList, obj, arrayList3, a2, bVar.b, aVar);
                }
                if (a2 == null) {
                    return true;
                }
                return false;
            }
            ag agVar = z ? fragment2.mEnterTransitionCallback : fragment.mEnterTransitionCallback;
            if (agVar != null) {
                agVar.a(new ArrayList(map.keySet()), new ArrayList(map.values()), null);
            }
            a(bVar, view, a4, fragment, fragment2, z, arrayList3);
        }
        obj = a4;
        if (a3 != null) {
        }
        arrayList = new ArrayList();
        a = a(b, fragment2, arrayList, (android.support.v4.util.a) map, bVar.d);
        view2 = (View) map.get(this.v.get(0));
        if (view2 != null) {
            if (a != null) {
                n.a(a, view2);
            }
            if (obj != null) {
                n.a(obj, view2);
            }
        }
        anonymousClass1 = /* anonymous class already generated */;
        arrayList2 = new ArrayList();
        aVar = new android.support.v4.util.a();
        z2 = true;
        if (fragment != null) {
            if (z) {
            }
        }
        a2 = n.a(a3, a, obj, z2);
        if (a2 != null) {
            n.a(a3, obj, view, anonymousClass1, bVar.d, bVar.c, (Map) bVar.a, arrayList2, map, aVar, arrayList3);
            a(view, bVar, i, a2);
            n.a(a2, bVar.d, true);
            a(bVar, i, a2);
            n.a((ViewGroup) view, a2);
            n.a(view, bVar.d, a3, arrayList2, a, arrayList, obj, arrayList3, a2, bVar.b, aVar);
        }
        if (a2 == null) {
            return false;
        }
        return true;
    }

    private void a(b bVar, View view, Object obj, Fragment fragment, Fragment fragment2, boolean z, ArrayList<View> arrayList) {
        final View view2 = view;
        final Object obj2 = obj;
        final ArrayList<View> arrayList2 = arrayList;
        final b bVar2 = bVar;
        final boolean z2 = z;
        final Fragment fragment3 = fragment;
        final Fragment fragment4 = fragment2;
        view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener(this) {
            final /* synthetic */ e h;

            public boolean onPreDraw() {
                view2.getViewTreeObserver().removeOnPreDrawListener(this);
                if (obj2 != null) {
                    n.a(obj2, arrayList2);
                    arrayList2.clear();
                    android.support.v4.util.a a = this.h.a(bVar2, z2, fragment3);
                    n.a(obj2, bVar2.d, (Map) a, arrayList2);
                    this.h.a(a, bVar2);
                    this.h.a(bVar2, fragment3, fragment4, z2, a);
                }
                return true;
            }
        });
    }

    private void a(b bVar, Fragment fragment, Fragment fragment2, boolean z, android.support.v4.util.a<String, View> aVar) {
        ag agVar = z ? fragment2.mEnterTransitionCallback : fragment.mEnterTransitionCallback;
        if (agVar != null) {
            agVar.b(new ArrayList(aVar.keySet()), new ArrayList(aVar.values()), null);
        }
    }

    private void a(android.support.v4.util.a<String, View> aVar, b bVar) {
        if (this.v != null && !aVar.isEmpty()) {
            View view = (View) aVar.get(this.v.get(0));
            if (view != null) {
                bVar.c.a = view;
            }
        }
    }

    private android.support.v4.util.a<String, View> a(b bVar, boolean z, Fragment fragment) {
        android.support.v4.util.a b = b(bVar, fragment, z);
        if (z) {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.a(this.v, (Map) b);
            }
            a(bVar, b, true);
        } else {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.a(this.v, (Map) b);
            }
            b(bVar, b, true);
        }
        return b;
    }

    private static android.support.v4.util.a<String, View> a(ArrayList<String> arrayList, ArrayList<String> arrayList2, android.support.v4.util.a<String, View> aVar) {
        if (aVar.isEmpty()) {
            return aVar;
        }
        android.support.v4.util.a<String, View> aVar2 = new android.support.v4.util.a();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) aVar.get(arrayList.get(i));
            if (view != null) {
                aVar2.put(arrayList2.get(i), view);
            }
        }
        return aVar2;
    }

    private android.support.v4.util.a<String, View> b(b bVar, Fragment fragment, boolean z) {
        android.support.v4.util.a aVar = new android.support.v4.util.a();
        View view = fragment.getView();
        if (view == null || this.u == null) {
            return aVar;
        }
        n.a((Map) aVar, view);
        if (z) {
            return a(this.u, this.v, aVar);
        }
        aVar.a(this.v);
        return aVar;
    }

    private void a(View view, b bVar, int i, Object obj) {
        final View view2 = view;
        final b bVar2 = bVar;
        final int i2 = i;
        final Object obj2 = obj;
        view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener(this) {
            final /* synthetic */ e e;

            public boolean onPreDraw() {
                view2.getViewTreeObserver().removeOnPreDrawListener(this);
                this.e.a(bVar2, i2, obj2);
                return true;
            }
        });
    }

    private void a(b bVar, int i, Object obj) {
        if (this.b.g != null) {
            for (int i2 = 0; i2 < this.b.g.size(); i2++) {
                Fragment fragment = (Fragment) this.b.g.get(i2);
                if (!(fragment.mView == null || fragment.mContainer == null || fragment.mContainerId != i)) {
                    if (!fragment.mHidden) {
                        n.a(obj, fragment.mView, false);
                        bVar.b.remove(fragment.mView);
                    } else if (!bVar.b.contains(fragment.mView)) {
                        n.a(obj, fragment.mView, true);
                        bVar.b.add(fragment.mView);
                    }
                }
            }
        }
    }

    private static void a(android.support.v4.util.a<String, String> aVar, String str, String str2) {
        if (str != null && str2 != null) {
            for (int i = 0; i < aVar.size(); i++) {
                if (str.equals(aVar.c(i))) {
                    aVar.a(i, (Object) str2);
                    return;
                }
            }
            aVar.put(str, str2);
        }
    }

    private static void a(b bVar, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                a(bVar.a, (String) arrayList.get(i), (String) arrayList2.get(i));
            }
        }
    }

    private void a(b bVar, android.support.v4.util.a<String, View> aVar, boolean z) {
        int size = this.v == null ? 0 : this.v.size();
        for (int i = 0; i < size; i++) {
            String str = (String) this.u.get(i);
            View view = (View) aVar.get((String) this.v.get(i));
            if (view != null) {
                String a = n.a(view);
                if (z) {
                    a(bVar.a, str, a);
                } else {
                    a(bVar.a, a, str);
                }
            }
        }
    }

    private void b(b bVar, android.support.v4.util.a<String, View> aVar, boolean z) {
        int size = aVar.size();
        for (int i = 0; i < size; i++) {
            String str = (String) aVar.b(i);
            String a = n.a((View) aVar.c(i));
            if (z) {
                a(bVar.a, str, a);
            } else {
                a(bVar.a, a, str);
            }
        }
    }
}
