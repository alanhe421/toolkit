package com.qq.reader.module.comic.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.charge.voucher.a.d;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDownloadActivity;
import com.qq.reader.module.comic.entity.l;
import com.qq.reader.module.comic.entity.m;
import com.qq.reader.module.comic.entity.n;
import com.qq.reader.module.comic.entity.o;
import com.qq.reader.view.af;
import com.qrcomic.downloader.u;
import com.qrcomic.util.j;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ComicMultiDownloadAdapter */
public class b extends BaseExpandableListAdapter {
    public List<String> a;
    private Context b;
    private List<b> c;
    private volatile HashMap<String, m> d = new HashMap();
    private Set<m> e;
    private List<m> f;
    private List<d> g;
    private int h;
    private int i;
    private int j;
    private int k;
    private long l;
    private c m;
    private String n;
    private l o;

    /* compiled from: ComicMultiDownloadAdapter */
    class a {
        public m a;
        final /* synthetic */ b b;
        private boolean c;
        private boolean d = true;

        public a(b bVar, m mVar, boolean z) {
            this.b = bVar;
            this.a = mVar;
            this.c = z;
        }

        public void a(boolean z) {
            this.d = z;
        }

        public boolean a() {
            return this.d;
        }

        public void b() {
            this.c = !this.c;
        }

        public boolean c() {
            return this.c;
        }

        public String d() {
            return this.a.b();
        }

        public int e() {
            return this.a.c();
        }

        public long f() {
            return this.a.d();
        }

        public String g() {
            return this.a.e();
        }

        public m h() {
            return this.a;
        }

        public boolean i() {
            return this.a.a().a();
        }

        public boolean j() {
            return this.a.j();
        }

        public boolean k() {
            return this.a.a().j() == 104;
        }

        public boolean l() {
            return m() != 0;
        }

        public int m() {
            return this.a.a().j();
        }

        public boolean n() {
            return this.a.a().i();
        }
    }

    /* compiled from: ComicMultiDownloadAdapter */
    class b {
        final /* synthetic */ b a;
        private String b;
        private ArrayList<a> c = new ArrayList();
        private boolean d = false;
        private boolean e = true;

        public b(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        public void a(boolean z) {
            this.d = z;
            f();
        }

        public void b(boolean z) {
            this.e = z;
        }

        public boolean a() {
            return this.e;
        }

        public void b() {
            this.d = !this.d;
            f();
        }

        private void f() {
            if (this.d) {
                this.a.a.add(this.b);
            } else if (this.a.a.contains(this.b)) {
                this.a.a.remove(this.b);
            }
        }

        public boolean c() {
            return this.d;
        }

        public String d() {
            return this.b;
        }

        public void a(a aVar) {
            this.c.add(aVar);
        }

        public int e() {
            return this.c.size();
        }

        public a a(int i) {
            return (a) this.c.get(i);
        }
    }

    /* compiled from: ComicMultiDownloadAdapter */
    public interface c {
        void onClick(int i, boolean z);
    }

    public b(Context context) {
        this.b = context;
        this.c = Collections.synchronizedList(new ArrayList());
        this.e = Collections.synchronizedSet(new HashSet());
        this.f = Collections.synchronizedList(new ArrayList());
        this.a = Collections.synchronizedList(new ArrayList());
    }

    public void a(String str) {
        this.n = str;
    }

    public Object getChild(int i, int i2) {
        return ((b) this.c.get(i)).a(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public int getChildrenCount(int i) {
        int size = this.c.size();
        if (i >= size) {
            i = size - 1;
        }
        return ((b) this.c.get(i)).e();
    }

    public Object getGroup(int i) {
        int size = this.c.size();
        if (i >= size) {
            i = size - 1;
        }
        return this.c.get(i);
    }

    public int getGroupCount() {
        return this.c.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void a(c cVar) {
        this.m = cVar;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.b instanceof NativeBookStoreComicDownloadActivity) {
            ((NativeBookStoreComicDownloadActivity) this.b).a();
            ((NativeBookStoreComicDownloadActivity) this.b).b();
        }
    }

    public void a(List<String> list) {
        List<m> k = k();
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);
            for (m mVar : k) {
                if (str != null && str.equals(mVar.f())) {
                    this.h--;
                    this.j -= mVar.c();
                    this.l -= mVar.d();
                    this.k -= (mVar.c() * this.i) / 100;
                    break;
                }
            }
        }
        if (this.j < 0) {
            this.j = 0;
        }
        if (this.k < 0) {
            this.k = 0;
        }
        if (this.l < 0) {
            this.l = 0;
        }
    }

    public void a() {
        for (m a : this.e) {
            a.a().a(100);
        }
        notifyDataSetChanged();
    }

    public void a(ArrayList<String> arrayList) {
        if (this.e != null) {
            Collection arrayList2 = new ArrayList();
            for (m mVar : this.e) {
                if (arrayList.contains(mVar.a().d())) {
                    arrayList2.add(mVar);
                }
            }
            this.e.removeAll(arrayList2);
        }
    }

    public void b() {
        Collection linkedList = new LinkedList();
        for (m mVar : this.e) {
            if (mVar.j()) {
                linkedList.add(mVar);
            }
        }
        this.e.removeAll(linkedList);
    }

    public synchronized void a(boolean z) {
        for (int i = 0; i < this.c.size(); i++) {
            ((b) this.c.get(i)).a(z);
            b(i);
        }
    }

    public synchronized void b(boolean z) {
        for (int i = 0; i < this.c.size(); i++) {
            ((b) this.c.get(i)).b(z);
            c(i);
        }
    }

    public boolean c() {
        for (int i = 0; i < this.c.size(); i++) {
            if (!((b) this.c.get(i)).c()) {
                return false;
            }
        }
        return true;
    }

    public boolean d() {
        for (int i = 0; i < this.c.size(); i++) {
            if (!a((b) this.c.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void e() {
        for (o oVar : this.o.h()) {
            if (oVar.j() == 100 || oVar.j() == 101) {
                oVar.a(102);
            }
        }
        notifyDataSetChanged();
    }

    public void f() {
        for (o oVar : this.o.h()) {
            if (oVar.j() == 102 || oVar.j() == 103) {
                oVar.a(100);
            }
        }
        notifyDataSetChanged();
    }

    public int g() {
        return this.j;
    }

    public long h() {
        return this.l;
    }

    public int i() {
        return this.k;
    }

    public int j() {
        return this.e.size();
    }

    public List<m> k() {
        this.f.clear();
        this.f.addAll(this.e);
        return this.f;
    }

    public HashMap<String, m> l() {
        return this.d;
    }

    public void a(int i, int i2) {
        a a = ((b) this.c.get(i)).a(i2);
        if (!a.j() || a.m() != 104) {
            if (a.l()) {
                u b = b(a);
                switch (a.m()) {
                    case 101:
                        if (b == null) {
                            a.h().a().a(102);
                            break;
                        } else {
                            b.b(true, true);
                            break;
                        }
                    case 102:
                        a.h().a().a(100);
                        com.qrcomic.downloader.d.b().a(this.n, a.g());
                        break;
                    case 103:
                        if (this.n != null) {
                            if (ao.d(this.b)) {
                                a.h().a().a(100);
                                n();
                                break;
                            }
                            af.a(this.b, this.b.getString(R.string.net_not_available), 0);
                            return;
                        }
                        break;
                }
            }
            a(a);
            d(i);
            notifyDataSetChanged();
        }
    }

    private void n() {
        List arrayList = new ArrayList();
        arrayList.add(this.n);
        com.qrcomic.downloader.d.b().a(arrayList);
    }

    private void a(a aVar) {
        if (aVar != null) {
            aVar.b();
            boolean c = aVar.c();
            if (c) {
                this.e.add(aVar.h());
            } else {
                this.e.remove(aVar.h());
            }
            if (!aVar.i()) {
                int e = aVar.e();
                if (e > 0) {
                    this.h = c ? this.h + 1 : this.h - 1;
                    this.j = c ? this.j + e : this.j - e;
                    if (this.j < 0) {
                        this.j = 0;
                    }
                    int i = (this.i * e) / 100;
                    this.k = c ? i + this.k : this.k - i;
                    if (this.k < 0) {
                        this.k = 0;
                    }
                }
            }
            long f = aVar.f();
            this.l = c ? this.l + f : this.l - f;
            if (this.l < 0) {
                this.l = 0;
            }
        }
    }

    public void a(l lVar) {
        this.o = lVar;
        List p = lVar.p();
        if (p != null) {
            this.c.clear();
            this.d.clear();
            for (int i = 0; i < p.size(); i++) {
                b bVar;
                n nVar = (n) p.get(i);
                String b = nVar.b();
                if (b != null) {
                    bVar = new b(this, b);
                } else {
                    bVar = new b(this, "第" + i + "段");
                }
                for (int i2 = 0; i2 < nVar.a(); i2++) {
                    boolean z;
                    m mVar = new m(nVar.a(i2));
                    if (this.e.isEmpty() || !this.e.contains(mVar)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    a aVar = new a(this, mVar, z);
                    this.d.put(mVar.e(), mVar);
                    bVar.a(aVar);
                    if (this.a.contains(bVar.b)) {
                        bVar.a(true);
                    }
                }
                this.c.add(bVar);
            }
        }
        this.i = lVar.j();
        this.g = lVar.o();
        if (this.e.isEmpty()) {
            this.j = 0;
            this.l = 0;
            this.k = 0;
            this.h = 0;
        }
    }

    private boolean a(b bVar) {
        int e = bVar.e();
        for (int i = 0; i < e; i++) {
            if (!bVar.a(i).k()) {
                return false;
            }
        }
        return true;
    }

    public View getGroupView(final int i, final boolean z, View view, ViewGroup viewGroup) {
        b bVar = (b) getGroup(i);
        if (view == null) {
            view = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.chapter_pay_choose_group, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.tvGroup);
        textView.setText(bVar.d());
        TextView textView2 = (TextView) view.findViewById(R.id.chapter_pay_group_exist);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chbGroup);
        ImageView imageView = (ImageView) view.findViewById(R.id.chapter_pay_failed_icon);
        imageView.setVisibility(8);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b c;

            public void onClick(View view) {
                if (this.c.m != null) {
                    this.c.m.onClick(i, !z);
                }
            }
        });
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.b.getResources().getDrawable(R.drawable.list_arrow_down), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.b.getResources().getDrawable(R.drawable.list_arrow_r), null, null, null);
        }
        boolean z2 = true;
        for (int i2 = 0; i2 < bVar.e(); i2++) {
            if (bVar.a(i2).m() != 103) {
                z2 = false;
            }
        }
        if (z2) {
            imageView.setVisibility(0);
            checkBox.setVisibility(8);
        } else {
            imageView.setVisibility(8);
            checkBox.setVisibility(0);
        }
        if (a(bVar)) {
            textView2.setVisibility(0);
            checkBox.setVisibility(4);
        } else {
            checkBox.setVisibility(0);
            checkBox.setChecked(bVar.c());
            checkBox.setEnabled(bVar.a());
            textView2.setVisibility(4);
        }
        checkBox.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b c;

            public void onClick(View view) {
                if (!z2) {
                    this.c.a(i);
                } else if (this.c.n != null && ao.d(this.c.b)) {
                    List arrayList = new ArrayList();
                    arrayList.add(this.c.n);
                    com.qrcomic.downloader.d.b().a(arrayList);
                }
            }
        });
        return view;
    }

    public void a(int i) {
        if (i >= 0) {
            ((b) this.c.get(i)).b();
            b(i);
            notifyDataSetChanged();
        }
    }

    private synchronized void b(int i) {
        try {
            int e = ((b) this.c.get(i)).e();
            boolean c = ((b) this.c.get(i)).c();
            for (int i2 = 0; i2 < e; i2++) {
                a a = ((b) this.c.get(i)).a(i2);
                if (!(a.c() == c || a.l())) {
                    a(a);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return;
    }

    private synchronized void c(int i) {
        try {
            int e = ((b) this.c.get(i)).e();
            boolean a = ((b) this.c.get(i)).a();
            for (int i2 = 0; i2 < e; i2++) {
                ((b) this.c.get(i)).a(i2).a(a);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return;
    }

    private void d(int i) {
        b bVar = (b) this.c.get(i);
        int e = bVar.e();
        boolean z = true;
        for (int i2 = 0; i2 < e; i2++) {
            int i3;
            a a = bVar.a(i2);
            if (a.c() || a.l()) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            z &= i3;
        }
        bVar.a(z);
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a a = ((b) this.c.get(i)).a(i2);
        if (view == null) {
            view = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.chapter_pay_choose_child, null);
        }
        ((TextView) view.findViewById(R.id.tvChild_chaptername)).setText(a.d());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chbChild);
        TextView textView = (TextView) view.findViewById(R.id.chapter_pay_child_fileExist);
        TextView textView2 = (TextView) view.findViewById(R.id.chapter_pay_child_file_downloading);
        ImageView imageView = (ImageView) view.findViewById(R.id.chapter_pay_child_file_downloading_icon);
        imageView.setVisibility(4);
        textView.setVisibility(4);
        imageView.clearAnimation();
        int m = a.m();
        Object obj = (this.o.d() || a.i()) ? 1 : null;
        Object obj2 = (a.n() || this.o.a() || obj != null) ? 1 : null;
        if (obj2 != null && a.h().j()) {
            textView2.setText("已下载");
            textView2.setVisibility(0);
            imageView.setVisibility(4);
            checkBox.setVisibility(4);
        } else if (m != 0) {
            com.qq.reader.common.monitor.debug.c.d("ComicMultiDownloadAdapter", " status is " + m);
            if (m == 101) {
                imageView.setVisibility(0);
                Animation loadAnimation = AnimationUtils.loadAnimation(this.b, R.anim.rotaterepeate);
                loadAnimation.setInterpolator(new LinearInterpolator());
                imageView.setBackgroundResource(R.drawable.audio_dl_ing);
                imageView.startAnimation(loadAnimation);
                textView2.setVisibility(4);
                checkBox.setVisibility(4);
            } else if (m == 104) {
                textView2.setText("已下载");
                textView2.setVisibility(0);
                imageView.setVisibility(4);
                checkBox.setVisibility(4);
            } else if (m == 103) {
                textView2.setVisibility(4);
                imageView.setBackgroundResource(R.drawable.audio_dl_failed);
                imageView.setVisibility(0);
                checkBox.setVisibility(4);
            } else if (m == 102) {
                textView2.setVisibility(4);
                imageView.setBackgroundResource(R.drawable.audio_dl_pause);
                imageView.setVisibility(0);
                checkBox.setVisibility(4);
            } else if (m == 100) {
                textView2.setText("等待下载");
                textView2.setVisibility(0);
                imageView.setVisibility(4);
                checkBox.setVisibility(4);
            }
        } else {
            checkBox.setVisibility(0);
            checkBox.setChecked(a.c());
            checkBox.setEnabled(a.a());
            textView.setVisibility(4);
            textView2.setVisibility(4);
        }
        TextView textView3 = (TextView) view.findViewById(R.id.tvChild_price);
        if (a.n()) {
            textView3.setText("免费");
        } else if (obj != null) {
            textView3.setText("已购买");
        } else if (this.o.d()) {
            textView3.setText("已购买整本");
        } else if (this.o.c()) {
            textView3.setText(String.valueOf(this.o.k()) + "书币/本");
        } else {
            textView3.setText(String.valueOf(a.e()) + "书币");
        }
        return view;
    }

    private u b(a aVar) {
        ConcurrentHashMap g = com.qrcomic.downloader.d.b().g();
        com.qq.reader.common.monitor.debug.c.d("ComicMultiDownloadAdapter", "getDownloadTask size is " + g.size());
        String c = aVar.h().a().c();
        String d = aVar.h().a().d();
        u uVar = (u) g.get(j.a(c, d));
        return (uVar != null && uVar.f.b.equals(d) && uVar.f.a.equals(c)) ? uVar : null;
    }

    public void c(boolean z) {
        this.o.a(z);
        notifyDataSetChanged();
    }

    public l m() {
        return this.o;
    }
}
