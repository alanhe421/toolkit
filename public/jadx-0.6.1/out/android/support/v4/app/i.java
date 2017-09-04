package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.util.h;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FragmentController */
public class i {
    private final j<?> a;

    public static final i a(j<?> jVar) {
        return new i(jVar);
    }

    private i(j<?> jVar) {
        this.a = jVar;
    }

    public k a() {
        return this.a.k();
    }

    public o b() {
        return this.a.l();
    }

    public int c() {
        List list = this.a.d.f;
        return list == null ? 0 : list.size();
    }

    public List<Fragment> a(List<Fragment> list) {
        if (this.a.d.f == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList(c());
        }
        list.addAll(this.a.d.f);
        return list;
    }

    public void a(Fragment fragment) {
        this.a.d.a(this.a, this.a, fragment);
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.a.d.a(view, str, context, attributeSet);
    }

    public void d() {
        this.a.d.k();
    }

    public Parcelable e() {
        return this.a.d.j();
    }

    public void a(Parcelable parcelable, List<Fragment> list) {
        this.a.d.a(parcelable, (List) list);
    }

    public List<Fragment> f() {
        return this.a.d.i();
    }

    public void g() {
        this.a.d.l();
    }

    public void h() {
        this.a.d.m();
    }

    public void i() {
        this.a.d.n();
    }

    public void j() {
        this.a.d.o();
    }

    public void k() {
        this.a.d.p();
    }

    public void l() {
        this.a.d.q();
    }

    public void m() {
        this.a.d.r();
    }

    public void n() {
        this.a.d.t();
    }

    public void a(Configuration configuration) {
        this.a.d.a(configuration);
    }

    public void o() {
        this.a.d.u();
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        return this.a.d.a(menu, menuInflater);
    }

    public boolean a(Menu menu) {
        return this.a.d.a(menu);
    }

    public boolean a(MenuItem menuItem) {
        return this.a.d.a(menuItem);
    }

    public boolean b(MenuItem menuItem) {
        return this.a.d.b(menuItem);
    }

    public void b(Menu menu) {
        this.a.d.b(menu);
    }

    public boolean p() {
        return this.a.d.g();
    }

    public void q() {
        this.a.m();
    }

    public void a(boolean z) {
        this.a.a(z);
        this.a.d.a(z);
    }

    public void r() {
        this.a.n();
    }

    public void s() {
        this.a.o();
    }

    public h<String, o> t() {
        return this.a.p();
    }

    public void a(h<String, o> hVar) {
        this.a.a((h) hVar);
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.a.b(str, fileDescriptor, printWriter, strArr);
    }
}
