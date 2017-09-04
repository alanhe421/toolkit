package com.qq.reader.module.bookchapter.online;

import android.content.Context;
import android.util.SparseArray;
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
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.AudioBookDownloadActivity;
import com.qq.reader.activity.ChapterBatDownloadActivity;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.buy.chapter.d;
import com.qq.reader.cservice.download.a.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/* compiled from: ChapterBatDownloadAdapter */
public class a extends BaseExpandableListAdapter {
    private Context a;
    private final List<c> b;
    private volatile SparseArray<d> c = new SparseArray();
    private final Set<d> d;
    private final List<d> e;
    private List<Object> f;
    private List<com.qq.reader.common.charge.voucher.a.d> g;
    private int h;
    private int i;
    private int j;
    private float k;
    private int l;
    private a m;
    private int n;
    private int o = -1;
    private String p;
    private int q;
    private boolean r;

    /* compiled from: ChapterBatDownloadAdapter */
    public interface a {
        void onClick(int i, boolean z);
    }

    public a(Context context) {
        this.a = context;
        this.b = Collections.synchronizedList(new ArrayList());
        this.q = 1;
        this.d = Collections.synchronizedSet(new HashSet());
        this.e = Collections.synchronizedList(new ArrayList());
    }

    public a(Context context, int i) {
        this.a = context;
        this.b = Collections.synchronizedList(new ArrayList());
        this.q = i;
        this.d = Collections.synchronizedSet(new HashSet());
        this.e = Collections.synchronizedList(new ArrayList());
    }

    public Object getChild(int i, int i2) {
        return ((c) this.b.get(i)).a(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public int getChildrenCount(int i) {
        int size = this.b.size();
        if (i >= size) {
            i = size - 1;
        }
        return ((c) this.b.get(i)).e();
    }

    public Object getGroup(int i) {
        int size = this.b.size();
        if (i >= size) {
            i = size - 1;
        }
        return this.b.get(i);
    }

    public int getGroupCount() {
        return this.b.size();
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

    public void a(a aVar) {
        this.m = aVar;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.a instanceof ChapterBatDownloadActivity) {
            ((ChapterBatDownloadActivity) this.a).c();
            ((ChapterBatDownloadActivity) this.a).a(l(), m());
        } else if (this.a instanceof AudioBookDownloadActivity) {
            ((AudioBookDownloadActivity) this.a).c();
            ((AudioBookDownloadActivity) this.a).a(l(), m());
        }
    }

    public void a(List<Integer> list) {
        List<d> g = g();
        for (int i = 0; i < list.size(); i++) {
            int intValue = ((Integer) list.get(i)).intValue();
            for (d dVar : g) {
                if (intValue == dVar.e()) {
                    this.i--;
                    this.j -= (int) dVar.c();
                    this.k -= dVar.d();
                    if (this.o > 0) {
                        this.l -= this.o;
                    } else {
                        this.l -= (int) ((dVar.c() * ((float) this.n)) / 100.0f);
                    }
                }
            }
        }
        if (this.j < 0) {
            this.j = 0;
        }
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.k < 0.0f) {
            this.k = 0.0f;
        }
    }

    public void a(ArrayList<Integer> arrayList) {
        synchronized (this.d) {
            Collection arrayList2 = new ArrayList();
            for (d dVar : this.d) {
                if (arrayList.contains(Integer.valueOf(dVar.e()))) {
                    arrayList2.add(dVar);
                }
            }
            this.d.removeAll(arrayList2);
        }
    }

    public void a() {
        synchronized (this.d) {
            Collection linkedList = new LinkedList();
            for (d dVar : this.d) {
                if (dVar.i()) {
                    linkedList.add(dVar);
                }
            }
            this.d.removeAll(linkedList);
        }
    }

    public synchronized void a(boolean z) {
        for (int i = 0; i < this.b.size(); i++) {
            ((c) this.b.get(i)).a(z);
            b(i);
        }
    }

    public synchronized void b(boolean z) {
        for (int i = 0; i < this.b.size(); i++) {
            ((c) this.b.get(i)).b(z);
            c(i);
        }
    }

    private synchronized boolean l() {
        boolean z;
        for (int i = 0; i < this.b.size(); i++) {
            c cVar = (c) this.b.get(i);
            if (!cVar.c() && !a(cVar)) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    private synchronized boolean m() {
        boolean z;
        for (int i = 0; i < this.b.size(); i++) {
            if (!a((c) this.b.get(i))) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    public int b() {
        return this.j;
    }

    public float c() {
        return this.k;
    }

    public String d() {
        return this.p;
    }

    public int e() {
        return this.l;
    }

    public int f() {
        return this.d.size();
    }

    public List<d> g() {
        this.e.clear();
        this.e.addAll(this.d);
        return this.e;
    }

    public SparseArray<d> h() {
        return this.c;
    }

    public void a(int i, int i2) {
        b a = ((c) this.b.get(i)).a(i2);
        if (this.q == 1) {
            if (!a.j() && a.a()) {
                a(a);
                d(i);
            } else {
                return;
            }
        } else if (this.q == 2) {
            b b = b(a);
            if (b != null) {
                i.a("event_C198", null, this.a);
                switch (b.j()) {
                    case 0:
                    case 30:
                        com.qq.reader.cservice.download.audio.a.a().b(b, true);
                        break;
                    case 10:
                        com.qq.reader.cservice.download.audio.a.a().c(b, false);
                        break;
                    case 50:
                        com.qq.reader.cservice.download.audio.a.a().b(b, false);
                        break;
                    default:
                        break;
                }
            }
            a(a);
            d(i);
        }
        notifyDataSetChanged();
    }

    private void a(b bVar) {
        if (bVar != null) {
            bVar.b();
            boolean c = bVar.c();
            if (c) {
                this.d.add(bVar.h());
            } else {
                this.d.remove(bVar.h());
            }
            if (!bVar.i()) {
                float e = bVar.e();
                if (e > 0.0f) {
                    int i;
                    int i2;
                    this.i = c ? this.i + 1 : this.i - 1;
                    if (c) {
                        i = this.j + ((int) e);
                    } else {
                        i = this.j - ((int) e);
                    }
                    this.j = i;
                    if (this.j < 0) {
                        this.j = 0;
                    }
                    if (this.o > 0) {
                        i2 = this.o;
                    } else {
                        i2 = (int) ((((float) this.n) * e) / 100.0f);
                    }
                    this.l = c ? this.l + i2 : this.l - i2;
                    c.e("ChapterBatDownload", "childDiscountPrice : " + i2 + " [discountPrice :" + this.l + "  childDiscountPrice :" + i2 + "]");
                }
            }
            float f = bVar.f();
            this.k = c ? f + this.k : this.k - f;
            if (this.k < 0.0f) {
                this.k = 0.0f;
            }
        }
    }

    public void a(f fVar) {
        List a = k.a(this.a).a(fVar.g());
        List f = fVar.f();
        if (f != null) {
            this.b.clear();
            this.c.clear();
            for (int i = 0; i < f.size(); i++) {
                c cVar;
                j jVar = (j) f.get(i);
                String b = jVar.b();
                if (b != null) {
                    cVar = new c(b);
                } else {
                    cVar = new c("第" + i + "段");
                }
                for (int i2 = 0; i2 < jVar.a(); i2++) {
                    d dVar = new d(jVar.a(i2));
                    if (a.contains(Integer.valueOf(dVar.e()))) {
                        dVar.a(true);
                    } else if (this.r) {
                        dVar.a(true);
                    }
                    b bVar = new b(dVar);
                    if (bVar != null) {
                        this.c.put(dVar.e(), dVar);
                        cVar.a(bVar);
                    }
                }
                this.b.add(cVar);
            }
        }
        this.f = fVar.h();
        this.h = fVar.l();
        this.n = fVar.s();
        this.p = fVar.r();
        if (fVar.y().Q() == 2000000804 && fVar.y().s() > 0) {
            this.o = fVar.y().s();
        }
        this.g = fVar.y().M();
        this.j = 0;
        this.k = 0.0f;
        this.l = 0;
        this.i = 0;
    }

    public void b(f fVar) {
        this.f = fVar.h();
        this.h = fVar.l();
        this.n = fVar.s();
        this.p = fVar.r();
        if (fVar.y().Q() == 2000000804 && fVar.y().s() > 0) {
            this.o = fVar.y().s();
        }
    }

    private boolean a(c cVar) {
        int e = cVar.e();
        for (int i = 0; i < e; i++) {
            if (!cVar.a(i).j()) {
                return false;
            }
        }
        return true;
    }

    public View getGroupView(final int i, final boolean z, View view, ViewGroup viewGroup) {
        c cVar = (c) getGroup(i);
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.chapter_pay_choose_group, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.tvGroup);
        textView.setText(cVar.d());
        TextView textView2 = (TextView) view.findViewById(R.id.chapter_pay_group_exist);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chbGroup);
        ImageView imageView = (ImageView) view.findViewById(R.id.chapter_pay_failed_icon);
        imageView.setVisibility(8);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a c;

            public void onClick(View view) {
                if (this.c.m != null) {
                    this.c.m.onClick(i, !z);
                }
            }
        });
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.a.getResources().getDrawable(R.drawable.list_arrow_down), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.a.getResources().getDrawable(R.drawable.list_arrow_r), null, null, null);
        }
        boolean z2 = true;
        for (int i2 = 0; i2 < cVar.e(); i2++) {
            b b = b(cVar.a(i2));
            if (b == null || b.j() != 50) {
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
        if (a(cVar)) {
            textView2.setVisibility(0);
            checkBox.setVisibility(4);
        } else {
            checkBox.setVisibility(0);
            checkBox.setChecked(cVar.c());
            checkBox.setEnabled(cVar.a());
            textView2.setVisibility(4);
        }
        checkBox.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a c;

            public void onClick(View view) {
                if (z2) {
                    com.qq.reader.cservice.download.audio.a.a().i();
                } else {
                    this.c.a(i);
                }
            }
        });
        return view;
    }

    public void a(int i) {
        if (i >= 0) {
            ((c) this.b.get(i)).b();
            b(i);
            notifyDataSetChanged();
        }
    }

    private synchronized void b(int i) {
        try {
            int e = ((c) this.b.get(i)).e();
            boolean c = ((c) this.b.get(i)).c();
            for (int i2 = 0; i2 < e; i2++) {
                b a = ((c) this.b.get(i)).a(i2);
                if (!(a.c() == c || a.j() || b(a) != null)) {
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
            int e = ((c) this.b.get(i)).e();
            boolean a = ((c) this.b.get(i)).a();
            for (int i2 = 0; i2 < e; i2++) {
                ((c) this.b.get(i)).a(i2).a(a);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return;
    }

    private void d(int i) {
        c cVar = (c) this.b.get(i);
        boolean z = true;
        for (int i2 = 0; i2 < cVar.e(); i2++) {
            z &= cVar.a(i2).c();
        }
        cVar.a(z);
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b a = ((c) this.b.get(i)).a(i2);
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.chapter_pay_choose_child, null);
        }
        ((TextView) view.findViewById(R.id.tvChild_chaptername)).setText(a.d());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chbChild);
        TextView textView = (TextView) view.findViewById(R.id.chapter_pay_child_fileExist);
        TextView textView2 = (TextView) view.findViewById(R.id.chapter_pay_child_file_downloading);
        ImageView imageView = (ImageView) view.findViewById(R.id.chapter_pay_child_file_downloading_icon);
        if (this.q == 1) {
            if (a.j()) {
                checkBox.setVisibility(4);
                textView.setVisibility(0);
            } else {
                checkBox.setVisibility(0);
                checkBox.setChecked(a.c());
                checkBox.setEnabled(a.a());
                textView.setVisibility(4);
            }
        } else if (this.q == 2) {
            b b = b(a);
            imageView.setVisibility(4);
            textView.setVisibility(4);
            imageView.clearAnimation();
            if (b == null) {
                checkBox.setVisibility(0);
                checkBox.setChecked(a.c());
                checkBox.setEnabled(a.a());
                textView.setVisibility(4);
                textView2.setVisibility(4);
            } else if (b.j() == 10) {
                imageView.setVisibility(0);
                Animation loadAnimation = AnimationUtils.loadAnimation(this.a, R.anim.rotaterepeate);
                loadAnimation.setInterpolator(new LinearInterpolator());
                imageView.setBackgroundResource(R.drawable.audio_dl_ing);
                imageView.startAnimation(loadAnimation);
                textView2.setVisibility(4);
                checkBox.setVisibility(4);
            } else if (b.j() == 40) {
                textView2.setText("已下载");
                textView2.setVisibility(0);
                imageView.setVisibility(4);
                checkBox.setVisibility(4);
            } else if (b.j() == 50) {
                textView2.setVisibility(4);
                imageView.setBackgroundResource(R.drawable.audio_dl_failed);
                imageView.setVisibility(0);
                checkBox.setVisibility(4);
            } else if (b.j() == 30) {
                if (b.h()) {
                    textView2.setText("等待下载");
                    textView2.setVisibility(0);
                    imageView.setVisibility(4);
                    checkBox.setVisibility(4);
                } else {
                    textView2.setVisibility(4);
                    imageView.setBackgroundResource(R.drawable.audio_dl_pause);
                    imageView.setVisibility(0);
                    checkBox.setVisibility(4);
                }
            } else if (b.h()) {
                textView2.setText("等待下载");
                textView2.setVisibility(0);
                imageView.setVisibility(4);
                checkBox.setVisibility(4);
            } else {
                textView2.setVisibility(4);
                imageView.setBackgroundResource(R.drawable.audio_dl_pause);
                imageView.setVisibility(0);
                checkBox.setVisibility(4);
            }
        }
        TextView textView3 = (TextView) view.findViewById(R.id.tvChild_price);
        if (a.k()) {
            textView3.setText("免费");
        } else if (a.i() || this.r) {
            textView3.setText("已付费");
        } else {
            textView3.setText(((int) a.e()) + "书币");
        }
        return view;
    }

    private b b(b bVar) {
        Vector g = com.qq.reader.cservice.download.audio.a.a().g();
        for (int i = 0; i < g.size(); i++) {
            com.qq.reader.cservice.download.audio.b bVar2 = (com.qq.reader.cservice.download.audio.b) g.get(i);
            if (bVar2.b == bVar.g() && bVar2.b().equals(bVar.d())) {
                return bVar2;
            }
        }
        return null;
    }

    public void c(boolean z) {
        this.r = z;
    }

    public int i() {
        return this.i;
    }

    public String j() {
        if (this.g == null || this.g.size() == 0) {
            return "";
        }
        int i = i();
        if (i == 0) {
            return "";
        }
        int i2 = 0;
        while (i2 < this.g.size() && i > ((com.qq.reader.common.charge.voucher.a.d) this.g.get(i2)).a()) {
            i2++;
        }
        if (i2 == this.g.size()) {
            return "";
        }
        int i3;
        com.qq.reader.common.charge.voucher.a.d dVar = (com.qq.reader.common.charge.voucher.a.d) this.g.get(i2);
        int a = dVar.a() - i;
        if (a == 0) {
            i3 = i2 + 1;
            if (i3 < this.g.size()) {
                dVar = (com.qq.reader.common.charge.voucher.a.d) this.g.get(i3);
                i2 = dVar.a() - i;
                a = i3;
            } else {
                i2 = a;
                a = i3;
            }
        } else {
            int i4 = a;
            a = i2;
            i2 = i4;
        }
        i3 = dVar.b();
        c.d("INDEX", " index = " + a + " voucherRules = " + ((com.qq.reader.common.charge.voucher.a.d) this.g.get(Math.min(a, this.g.size() - 1))).a() + " chargeNum = " + i);
        if (a == 0) {
            return String.format(ReaderApplication.getApplicationImp().getString(R.string.voucher_discount_next_level), new Object[]{Integer.valueOf(i2), i3 + "%"});
        }
        return String.format(ReaderApplication.getApplicationImp().getString(R.string.voucher_discount_upper_level), new Object[]{Integer.valueOf(i2), i3 + "%"});
    }

    public int k() {
        if (this.g == null || this.g.size() == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            com.qq.reader.common.charge.voucher.a.d dVar = (com.qq.reader.common.charge.voucher.a.d) this.g.get(i2);
            if (dVar != null && i() >= dVar.a()) {
                i = Math.max(dVar.b(), i);
            }
        }
        return (e() * i) / 100;
    }
}
