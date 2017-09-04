package com.qq.reader.module.bookstore.qweb;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.k;
import android.support.v4.app.m;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.e;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import java.util.ArrayList;

/* compiled from: SlipedFragmentStatePagerAdapter */
public abstract class a extends PagerAdapter {
    private boolean a = false;
    private k b;
    private m c = null;
    private int d = 0;
    private e e = new e(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onPageSelected(int i) {
            this.a.d = i;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.a.a = false;
                BaseFragment e = this.a.e(this.a.d);
                if (e != null) {
                    this.a.a(e);
                    return;
                }
                for (Fragment fragment : this.a.b.e()) {
                    if (fragment != null) {
                        this.a.a((BaseFragment) fragment);
                    }
                }
                return;
            }
            this.a.a = true;
        }
    };
    private ArrayList<SavedState> f = new ArrayList();
    private ArrayList<BaseFragment> g = new ArrayList();
    private Fragment h = null;

    public a(k kVar) {
        this.b = kVar;
    }

    public BaseFragment d(int i) {
        return (BaseFragment) this.g.get(i);
    }

    public BaseFragment e(int i) {
        if (i < 0 || i > this.g.size() - 1) {
            return null;
        }
        return (BaseFragment) this.g.get(i);
    }

    public void a(ViewGroup viewGroup) {
    }

    public e d() {
        return this.e;
    }

    public void c() {
        super.c();
    }

    public Object a(ViewGroup viewGroup, int i) {
        if (this.g.size() > i) {
            BaseFragment baseFragment = (BaseFragment) this.g.get(i);
            if (baseFragment != null) {
                return baseFragment;
            }
        }
        if (this.c == null) {
            this.c = this.b.a();
        }
        BaseFragment d = d(i);
        f.d("baoyue", " instantiateItem  " + d.getClass().getSimpleName());
        if (this.f.size() > i) {
            SavedState savedState = (SavedState) this.f.get(i);
            if (savedState != null) {
                d.setInitialSavedState(savedState);
            }
        }
        while (this.g.size() <= i) {
            this.g.add(null);
        }
        d.setMenuVisibility(false);
        this.g.set(i, d);
        this.c.a(viewGroup.getId(), (Fragment) d);
        if (!this.a) {
            b(d);
        }
        return d;
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (BaseFragment) obj;
        fragment.cancleLoadData();
        if (this.c == null) {
            this.c = this.b.a();
        }
        while (this.f.size() <= i) {
            this.f.add(null);
        }
        if (this.b.e().contains(fragment)) {
            this.f.set(i, this.b.a(fragment));
        }
        this.g.set(i, null);
        this.c.a(fragment);
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.h) {
            if (this.h != null) {
                this.h.setMenuVisibility(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
            }
            this.h = fragment;
        }
    }

    public void b(ViewGroup viewGroup) {
        if (this.c != null) {
            this.c.b();
            this.c = null;
            try {
                this.b.b();
            } catch (Exception e) {
            }
        }
    }

    public boolean a(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Parcelable b() {
        Bundle bundle = null;
        if (this.f.size() > 0) {
            bundle = new Bundle();
            Parcelable[] parcelableArr = new SavedState[this.f.size()];
            this.f.toArray(parcelableArr);
            bundle.putParcelableArray("states", parcelableArr);
        }
        Parcelable parcelable = bundle;
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null) {
                if (parcelable == null) {
                    parcelable = new Bundle();
                }
                this.b.a(parcelable, "f" + i, fragment);
            }
        }
        return parcelable;
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
        this.f.clear();
        this.g.clear();
        try {
            this.b.c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(BaseFragment baseFragment) {
        baseFragment.executeLoadDataWithDelay();
    }

    private void b(BaseFragment baseFragment) {
        baseFragment.executeLoadData();
    }
}
