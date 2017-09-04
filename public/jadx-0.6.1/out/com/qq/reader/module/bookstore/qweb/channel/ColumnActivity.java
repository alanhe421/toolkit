package com.qq.reader.module.bookstore.qweb.channel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;

public class ColumnActivity extends ReaderBaseActivity implements OnItemClickListener {
    private GestureDetector A;
    private int B = -1;
    private int C = 0;
    private a D;
    private a E = new a(this) {
        final /* synthetic */ ColumnActivity a;

        {
            this.a = r1;
        }
    };
    private TextView a;
    private TextView b;
    private DragGrid c;
    private b d;
    private ArrayList<ColumnWebEntity> e = new ArrayList();
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private LinearLayout i;
    private OtherGridView j;
    private OtherGridView k;
    private OtherGridView l;
    private OtherGridView m;
    private c n;
    private c o;
    private c p;
    private c q;
    private ArrayList<ColumnWebEntity> r = new ArrayList();
    private ArrayList<ColumnWebEntity> s = new ArrayList();
    private ArrayList<ColumnWebEntity> t = new ArrayList();
    private ArrayList<ColumnWebEntity> u = new ArrayList();
    private ArrayList<ColumnWebEntity> v = new ArrayList();
    private transient boolean w = false;
    private StringBuffer x = new StringBuffer();
    private int y = 0;
    private View z;

    public interface a {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.subscribe_activity);
        this.C = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        this.y = getIntent().getIntExtra(a.b, 0);
        this.A = new GestureDetector(this, new OnGestureListener(this) {
            final /* synthetic */ ColumnActivity a;

            {
                this.a = r1;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent != null && motionEvent2 != null && f > 0.0f && motionEvent2.getX() - motionEvent.getX() > ((float) (this.a.C * 2))) {
                    this.a.g();
                }
                return false;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }
        });
        this.D = new a();
        b();
        a();
    }

    private void a() {
        int dimension;
        try {
            int dimension2 = (int) (((float) getResources().getDisplayMetrics().widthPixels) - (getResources().getDimension(R.dimen.column_manage_margin) * 2.0f));
            dimension = (int) (((float) dimension2) / getResources().getDimension(R.dimen.column_item_width));
            if (((float) ((int) ((((float) dimension2) % getResources().getDimension(R.dimen.column_item_width)) / ((float) dimension)))) < getResources().getDimension(R.dimen.column_gridview_min_hpace) && dimension - 1 > 0) {
                dimension--;
            }
        } catch (Exception e) {
            dimension = 4;
        }
        ArrayList b = d.a().b(Boolean.valueOf(false));
        a(b, dimension);
        b(b, dimension);
        this.c.setAutoNumColumns(dimension);
    }

    private void b() {
        this.z = (ScrollView) findViewById(R.id.scroll_layout);
        this.z.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ ColumnActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return this.a.A.onTouchEvent(motionEvent);
            }
        });
        this.a = (TextView) findViewById(R.id.profile_header_title);
        this.a.setText("频道管理");
        this.b = (TextView) findViewById(R.id.counttext);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ColumnActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
            }
        });
        this.c = (DragGrid) findViewById(R.id.userGridView);
        this.j = (OtherGridView) findViewById(R.id.otherGridView_1);
        this.k = (OtherGridView) findViewById(R.id.otherGridView_2);
        this.l = (OtherGridView) findViewById(R.id.otherGridView_3);
        this.m = (OtherGridView) findViewById(R.id.otherGridView_4);
        this.f = (LinearLayout) findViewById(R.id.column_edit_layout_1);
        this.g = (LinearLayout) findViewById(R.id.column_edit_layout_2);
        this.h = (LinearLayout) findViewById(R.id.column_edit_layout_3);
        this.i = (LinearLayout) findViewById(R.id.column_edit_layout_4);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!this.w) {
            final ImageView a;
            final int[] iArr;
            final int i2;
            switch (adapterView.getId()) {
                case R.id.otherGridView_2:
                case R.id.otherGridView_1:
                case R.id.otherGridView_3:
                case R.id.otherGridView_4:
                    a = a(view);
                    if (a != null) {
                        i.a("event_C19", null, getApplicationContext());
                        StatisticsManager.a().a("event_C19", null);
                        j.a(18, 2);
                        this.w = true;
                        iArr = new int[2];
                        ((RelativeLayout) view.findViewById(R.id.column_edit_items_layout)).getLocationInWindow(iArr);
                        final ColumnWebEntity a2 = ((c) adapterView.getAdapter()).a(i);
                        a2.setSelected(1);
                        final c a3 = this.D.a(a2.getTitleid());
                        final OtherGridView otherGridView = (OtherGridView) this.D.b(a2.getTitleid());
                        this.d.a(false);
                        this.d.a(a2);
                        i2 = i;
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ ColumnActivity g;

                            public void run() {
                                try {
                                    int[] iArr = new int[2];
                                    this.g.c.getChildAt(this.g.c.getLastVisiblePosition()).getLocationInWindow(iArr);
                                    a3.b(i2);
                                    this.g.a(a, iArr, iArr, a2, otherGridView, a3);
                                } catch (Exception e) {
                                    f.a("ChannelActivity", e.toString());
                                }
                            }
                        }, 50);
                        return;
                    }
                    return;
                case R.id.userGridView:
                    if (i >= 2) {
                        i.a("event_C20", null, getApplicationContext());
                        StatisticsManager.a().a("event_C20", null);
                        j.a(19, 2);
                        this.w = true;
                        a = a(view);
                        if (a != null) {
                            iArr = new int[2];
                            ((RelativeLayout) view.findViewById(R.id.column_edit_items_layout)).getLocationInWindow(iArr);
                            final ColumnWebEntity a4 = ((b) adapterView.getAdapter()).a(i);
                            a4.setSelected(0);
                            final c a5 = this.D.a(a4.getTitleid());
                            a5.a(false);
                            a5.a(a4);
                            e();
                            i2 = i;
                            new Handler().postDelayed(new Runnable(this) {
                                final /* synthetic */ ColumnActivity f;

                                public void run() {
                                    try {
                                        int[] iArr = new int[2];
                                        OtherGridView otherGridView = (OtherGridView) this.f.D.b(a4.getTitleid());
                                        otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(iArr);
                                        this.f.d.b(i2);
                                        this.f.a(a, iArr, iArr, a4, this.f.c, a5);
                                    } catch (Exception e) {
                                        f.a("ChannelActivity", e.toString());
                                    }
                                }
                            }, 50);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void a(View view, int[] iArr, int[] iArr2, ColumnWebEntity columnWebEntity, GridView gridView, c cVar) {
        int[] iArr3 = new int[2];
        view.getLocationInWindow(iArr3);
        final ViewGroup c = c();
        final View a = a(c, view, iArr3);
        Animation translateAnimation = new TranslateAnimation((float) (iArr[0] - (this.B / 2)), (float) (iArr2[0] - (this.B / 2)), (float) iArr[1], (float) iArr2[1]);
        translateAnimation.setDuration(300);
        Animation animationSet = new AnimationSet(true);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(translateAnimation);
        a.startAnimation(animationSet);
        final GridView gridView2 = gridView;
        final c cVar2 = cVar;
        animationSet.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ColumnActivity e;

            public void onAnimationStart(Animation animation) {
                this.e.w = true;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                c.removeView(a);
                if (gridView2 instanceof DragGrid) {
                    cVar2.a(true);
                    cVar2.notifyDataSetChanged();
                    this.e.d.a();
                } else {
                    this.e.d.a(true);
                    this.e.d.notifyDataSetChanged();
                    cVar2.a();
                }
                this.e.e();
                this.e.w = false;
            }
        });
    }

    private View a(ViewGroup viewGroup, View view, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        viewGroup.addView(view);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        view.setLayoutParams(layoutParams);
        return view;
    }

    private ViewGroup c() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        View linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private ImageView a(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(createBitmap);
        return imageView;
    }

    private int d() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
            if (!arrayList.contains(columnWebEntity)) {
                arrayList.add(columnWebEntity);
            }
        }
        it = this.s.iterator();
        while (it.hasNext()) {
            columnWebEntity = (ColumnWebEntity) it.next();
            if (!arrayList.contains(columnWebEntity)) {
                arrayList.add(columnWebEntity);
            }
        }
        it = this.t.iterator();
        while (it.hasNext()) {
            columnWebEntity = (ColumnWebEntity) it.next();
            if (!arrayList.contains(columnWebEntity)) {
                arrayList.add(columnWebEntity);
            }
        }
        it = this.u.iterator();
        while (it.hasNext()) {
            columnWebEntity = (ColumnWebEntity) it.next();
            if (!arrayList.contains(columnWebEntity)) {
                arrayList.add(columnWebEntity);
            }
        }
        it = this.v.iterator();
        while (it.hasNext()) {
            columnWebEntity = (ColumnWebEntity) it.next();
            if (!arrayList.contains(columnWebEntity)) {
                arrayList.add(columnWebEntity);
            }
        }
        d.a().a(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (((ColumnWebEntity) arrayList.get(i)).getTitleid() == this.y) {
                return i;
            }
        }
        return -1;
    }

    public void onBackPressed() {
        d();
        super.onBackPressed();
    }

    private void a(ArrayList<ColumnWebEntity> arrayList, int i) {
        this.e.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
            if (columnWebEntity.getSelect() == 1) {
                this.e.add(columnWebEntity);
            }
        }
        this.d = new b(this, this.e);
        this.c.setNumColumns(i);
        this.c.setAdapter(this.d);
        this.c.setOnItemClickListener(this);
    }

    private void b(ArrayList<ColumnWebEntity> arrayList, int i) {
        this.r.clear();
        this.s.clear();
        this.t.clear();
        this.u.clear();
        this.v.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
            if (columnWebEntity.getSelect() == 0) {
                this.r.add(columnWebEntity);
            }
        }
        it = this.r.iterator();
        while (it.hasNext()) {
            columnWebEntity = (ColumnWebEntity) it.next();
            switch (columnWebEntity.getTitleType()) {
                case 1:
                    this.s.add(columnWebEntity);
                    break;
                case 2:
                    this.t.add(columnWebEntity);
                    break;
                case 3:
                    this.u.add(columnWebEntity);
                    break;
                case 4:
                    this.v.add(columnWebEntity);
                    break;
                default:
                    break;
            }
        }
        this.n = new c(this, this.s);
        this.j.setNumColumns(i);
        this.j.setOnItemClickListener(this);
        this.j.setAdapter(this.n);
        this.o = new c(this, this.t);
        this.k.setNumColumns(i);
        this.k.setOnItemClickListener(this);
        this.k.setAdapter(this.o);
        this.p = new c(this, this.u);
        this.l.setNumColumns(i);
        this.l.setOnItemClickListener(this);
        this.l.setAdapter(this.p);
        this.q = new c(this, this.v);
        this.m.setNumColumns(i);
        this.m.setOnItemClickListener(this);
        this.m.setAdapter(this.q);
        this.D.a(this.n, this.o, this.p, this.q);
        this.D.a(this.j, this.k, this.l, this.m);
        e();
    }

    private void e() {
        if (this.n.getCount() == 0) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
        }
        if (this.o.getCount() == 0) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        if (this.p.getCount() == 0) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
        }
        if (this.q.getCount() == 0) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
        }
        f();
    }

    private void f() {
        this.x.setLength(0);
        this.x.append("已关注");
        this.x.append(this.d.getCount());
        this.x.append("个频道");
        this.b.setText(this.x.toString());
    }

    private void g() {
        d();
        Intent intent = new Intent();
        intent.setAction("com.column.change");
        sendBroadcast(intent);
        finish();
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } catch (Exception e) {
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        g();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.A.onTouchEvent(motionEvent);
    }
}
