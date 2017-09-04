package com.qq.reader.module.feed.mypreference;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.feed.mypreference.d.b;
import com.qq.reader.module.feed.mypreference.d.d;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.List;

public class MyReadingGeneActivity extends ReaderBaseActivity implements OnClickListener {
    boolean a = false;
    private d b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private LinearLayout g;
    private final int h = 241;
    private final int i = 242;
    private ImageView j;
    private ArrayList<Point> k = new ArrayList();
    private RoundRelativeLayout l;
    private RoundRelativeLayout m;
    private RelativeLayout n;
    private TextView o;
    private ImageView p;
    private Button q;
    private CustomScrollView r;
    private LinearLayout s;
    private int t = 255;
    private final int u = 6;
    private int v = 0;
    private BroadcastReceiver w = new 1(this);

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 241:
                g();
                return true;
            case 242:
                c();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void b() {
        this.mHandler.sendEmptyMessageDelayed(241, 0);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_read_gene);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(a.cB);
        registerReceiver(this.w, intentFilter);
        this.b = c.b().c();
        a.a().a(null);
        d();
        if (c.b()) {
            b();
            c();
            return;
        }
        loginWithTask(242);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.w);
    }

    private void c() {
        new Thread(new 2(this)).start();
    }

    private void d() {
        this.c = (TextView) findViewById(R.id.tv_book_count);
        this.d = (TextView) findViewById(R.id.tv_day_count);
        this.e = (TextView) findViewById(R.id.tv_total_word_count);
        this.f = (TextView) findViewById(R.id.tv_day_word_count);
        this.g = (LinearLayout) findViewById(R.id.action_container);
        this.j = (ImageView) findViewById(R.id.img_user_icon);
        this.l = (RoundRelativeLayout) findViewById(R.id.gene_outer_container);
        this.m = (RoundRelativeLayout) findViewById(R.id.gene_inner_container);
        this.n = (RelativeLayout) findViewById(R.id.common_titler);
        this.n.setBackgroundDrawable(null);
        this.o = (TextView) findViewById(R.id.profile_header_title);
        this.o.setText(getString(R.string.my_read_gene));
        this.p = (ImageView) findViewById(R.id.profile_header_left_back);
        this.p.setOnClickListener(this);
        this.q = (Button) findViewById(R.id.btn_modify_my_gene);
        this.q.setOnClickListener(this);
        this.s = (LinearLayout) findViewById(R.id.ll_share);
        this.s.setOnClickListener(this);
        this.r = (CustomScrollView) findViewById(R.id.read_gene_scrollview);
        this.r.setSmoothScrollingEnabled(true);
        this.r.setOnScrollListener(new 3(this));
        TextView textView = (TextView) findViewById(R.id.tv_my_gene_tip);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.bookstore_titlerbar_height);
        if (VERSION.SDK_INT >= 19) {
            dimensionPixelOffset += a.ca;
        }
        layoutParams.topMargin = dimensionPixelOffset;
        textView.setLayoutParams(layoutParams);
        e();
        f();
        this.r.setOverScrollMode(2);
    }

    private void e() {
        int[] iArr = new int[]{Opcodes.INT_TO_CHAR, 214, 300};
        int[] iArr2 = new int[]{R.drawable.read_gene_circle_1, R.drawable.read_gene_circle_2, R.drawable.read_gene_circle_3};
        for (int i = 0; i < iArr.length; i++) {
            View imageView = new ImageView(this);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(a((float) iArr[i]), a((float) iArr[i])));
            imageView.setBackgroundResource(iArr2[i]);
            this.l.addView(imageView);
        }
        String[] stringArray = getResources().getStringArray(R.array.read_gene_temp_circle_pos_array);
        for (String split : stringArray) {
            String[] split2 = split.split(",");
            int a = a((float) Integer.parseInt(split2[0]));
            int a2 = a((float) Integer.parseInt(split2[1]));
            View imageView2 = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = a;
            layoutParams.topMargin = a2;
            imageView2.setLayoutParams(layoutParams);
            imageView2.setBackgroundResource(R.drawable.read_gene_temp_circle);
            this.l.addView(imageView2);
        }
    }

    private void f() {
        this.k.clear();
        String[] stringArray = getResources().getStringArray(R.array.read_gene_pos_array);
        for (String split : stringArray) {
            String[] split2 = split.split(",");
            Point point = new Point();
            point.x = a((float) Integer.parseInt(split2[0]));
            point.y = a((float) Integer.parseInt(split2[1]));
            this.k.add(point);
        }
    }

    public int a(float f) {
        return (int) ((((float) getResources().getInteger(R.integer.read_gene_ratio)) * TypedValue.applyDimension(1, f, getResources().getDisplayMetrics())) / 10.0f);
    }

    private void g() {
        i();
        h();
        j();
    }

    private void h() {
        TextView textView = (TextView) findViewById(R.id.tv_book_count_unit);
        TextView textView2 = (TextView) findViewById(R.id.tv_day_count_unit);
        TextView textView3 = (TextView) findViewById(R.id.tv_total_word_count_unit);
        TextView textView4 = (TextView) findViewById(R.id.tv_day_word_count_unit);
        if (this.b != null) {
            b bVar = this.b.c;
            if (bVar != null) {
                this.c.setText(bVar.a);
                this.d.setText(bVar.b);
                this.e.setText(bVar.d);
                this.f.setText(bVar.c);
                textView.setText("本");
                textView2.setText("天");
                textView3.setText("字");
                textView4.setText("字");
                return;
            }
            this.c.setText("--");
            this.d.setText("--");
            this.e.setText("--");
            this.f.setText("--");
            textView.setText("");
            textView2.setText("");
            textView3.setText("");
            textView4.setText("");
            return;
        }
        this.c.setText("--");
        this.d.setText("--");
        this.e.setText("--");
        this.f.setText("--");
        textView.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
    }

    private void i() {
        List list;
        int i = 0;
        com.qq.reader.common.imageloader.c.a((Context) this).a(c.c().b(), this.j, com.qq.reader.common.imageloader.a.a().q());
        this.m.removeAllViews();
        if (this.b != null) {
            list = this.b.a;
        } else {
            list = null;
        }
        if (list != null) {
            int i2;
            int size = list.size();
            if (size > 10) {
                i2 = 10;
            } else {
                i2 = size;
            }
            while (i < i2) {
                d dVar = (d) list.get(i);
                if (dVar != null) {
                    int a = a(dVar.c);
                    ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
                    layoutParams.leftMargin = ((Point) this.k.get(i)).x;
                    layoutParams.topMargin = ((Point) this.k.get(i)).y;
                    View readingGeneView = new ReadingGeneView(this);
                    readingGeneView.setLayoutParams(layoutParams);
                    if (TextUtils.isEmpty(dVar.b) || "null".equals(dVar.b)) {
                        readingGeneView.setCategoryById(String.valueOf(dVar.a));
                    } else {
                        readingGeneView.setCategory(dVar.b);
                    }
                    readingGeneView.setPercent(String.valueOf(dVar.c));
                    readingGeneView.setIconByCategoryId(String.valueOf(dVar.a), a, a);
                    this.m.addView(readingGeneView);
                }
                i++;
            }
            if (i2 < 6) {
                b(i2);
                return;
            }
            return;
        }
        b(0);
    }

    private void j() {
        if (this.b == null || this.b.b == null || this.b.b.size() <= 0) {
            ((LinearLayout) findViewById(R.id.ll_action)).setVisibility(8);
            return;
        }
        ((LinearLayout) findViewById(R.id.ll_action)).setVisibility(0);
        List list = this.b.b;
        this.g.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            d.a aVar = (d.a) list.get(i);
            View inflate = View.inflate(this, R.layout.read_gene_action_view, null);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_datetime);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_action_type);
            TextView textView3 = (TextView) inflate.findViewById(R.id.tv_category_name);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_trend);
            ((TextView) inflate.findViewById(R.id.tv_bookname)).setText("《" + aVar.b + "》");
            textView.setText(aVar.d);
            textView2.setText(aVar.f);
            textView3.setText(aVar.c);
            this.g.addView(inflate);
            if (aVar.g == 0) {
                imageView.setImageResource(R.drawable.read_gene_trend_down);
            } else {
                imageView.setImageResource(R.drawable.read_gene_trend_up);
            }
        }
    }

    private int a(int i) {
        return a((float) (45 + ((i * 45) / 100)));
    }

    private void k() {
        Intent intent = new Intent();
        intent.setClass(this, MyFeedPreferenceActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        i.a("event_A152", null, ReaderApplication.getApplicationImp());
        startActivityForResult(intent, this.t);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == this.t && i2 == -1) {
            this.v = -1;
            l();
            this.b = c.b().c();
            g();
        } else if (i != 4098 || i2 == -1) {
            super.onActivityResult(i, i2, intent);
        } else {
            finish();
        }
    }

    private void l() {
        int childCount = this.m.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.m.getChildAt(i);
            if (childAt instanceof ReadingGeneView) {
                ((ReadingGeneView) childAt).setPercent("");
            }
        }
    }

    private void b(int i) {
        while (i < 6) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.empty_gene_width);
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            layoutParams.leftMargin = ((Point) this.k.get(i)).x;
            layoutParams.topMargin = ((Point) this.k.get(i)).y;
            View readingGeneView = new ReadingGeneView(this);
            readingGeneView.setLayoutParams(layoutParams);
            readingGeneView.setIcon(R.drawable.add_gene_background);
            readingGeneView.setCategory(null);
            readingGeneView.setPercent(null);
            readingGeneView.a();
            readingGeneView.setIconOnClickListener(new 4(this));
            this.m.addView(readingGeneView);
            i++;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                setResult(this.v);
                finish();
                return;
            case R.id.btn_modify_my_gene:
                k();
                return;
            case R.id.ll_share:
                if (this.b != null && this.b.d != null) {
                    a(a(), this.b.d.d, this.b.d.c, this.b.d.b, null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public String a() {
        return e.n + "readGeneShareV2.html?tf=1" + "&" + "qqid=" + this.b.d.e;
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        new a(this, this, str, str2, str3, str4, str5).f_();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            setResult(this.v);
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
