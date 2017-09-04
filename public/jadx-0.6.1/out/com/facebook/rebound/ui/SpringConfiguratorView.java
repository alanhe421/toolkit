package com.facebook.rebound.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.facebook.rebound.f;
import com.facebook.rebound.g;
import com.facebook.rebound.h;
import com.facebook.rebound.j;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat a = new DecimalFormat("#.#");
    private final d b;
    private final List<f> c;
    private final com.facebook.rebound.e d;
    private final float e;
    private final float f;
    private final g g;
    private final int h;
    private SeekBar i;
    private SeekBar j;
    private Spinner k;
    private TextView l;
    private TextView m;
    private f n;

    private class a implements OnTouchListener {
        final /* synthetic */ SpringConfiguratorView a;

        private a(SpringConfiguratorView springConfiguratorView) {
            this.a = springConfiguratorView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.a.c();
            }
            return true;
        }
    }

    private class b implements h {
        final /* synthetic */ SpringConfiguratorView a;

        private b(SpringConfiguratorView springConfiguratorView) {
            this.a = springConfiguratorView;
        }

        public void a(com.facebook.rebound.e eVar) {
            float b = (float) eVar.b();
            float h = this.a.f;
            this.a.setTranslationY((b * (this.a.e - h)) + h);
        }

        public void b(com.facebook.rebound.e eVar) {
        }

        public void c(com.facebook.rebound.e eVar) {
        }

        public void d(com.facebook.rebound.e eVar) {
        }
    }

    private class c implements OnSeekBarChangeListener {
        final /* synthetic */ SpringConfiguratorView a;

        private c(SpringConfiguratorView springConfiguratorView) {
            this.a = springConfiguratorView;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (seekBar == this.a.i) {
                float f = ((200.0f * ((float) i)) / 100000.0f) + 0.0f;
                this.a.n.b = com.facebook.rebound.c.a((double) f);
                this.a.m.setText("T:" + SpringConfiguratorView.a.format((double) f));
            }
            if (seekBar == this.a.j) {
                f = ((((float) i) * 50.0f) / 100000.0f) + 0.0f;
                this.a.n.a = com.facebook.rebound.c.c((double) f);
                this.a.l.setText("F:" + SpringConfiguratorView.a.format((double) f));
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    private class d extends BaseAdapter {
        final /* synthetic */ SpringConfiguratorView a;
        private final Context b;
        private final List<String> c = new ArrayList();

        public d(SpringConfiguratorView springConfiguratorView, Context context) {
            this.a = springConfiguratorView;
            this.b = context;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public void a(String str) {
            this.c.add(str);
            notifyDataSetChanged();
        }

        public void a() {
            this.c.clear();
            notifyDataSetChanged();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new TextView(this.b);
                view.setLayoutParams(new LayoutParams(-1, -1));
                int a = a.a(12.0f, this.a.getResources());
                view.setPadding(a, a, a, a);
                view.setTextColor(this.a.h);
            } else {
                TextView textView = (TextView) view;
            }
            view.setText((CharSequence) this.c.get(i));
            return view;
        }
    }

    private class e implements OnItemSelectedListener {
        final /* synthetic */ SpringConfiguratorView a;

        private e(SpringConfiguratorView springConfiguratorView) {
            this.a = springConfiguratorView;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.n = (f) this.a.c.get(i);
            this.a.a(this.a.n);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public SpringConfiguratorView(Context context) {
        this(context, null);
    }

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.h = Color.argb(255, Opcodes.SHR_INT_LIT8, Opcodes.SHR_INT_LIT8, Opcodes.SHR_INT_LIT8);
        j c = j.c();
        this.g = g.a();
        this.b = new d(this, context);
        Resources resources = getResources();
        this.f = (float) a.a(40.0f, resources);
        this.e = (float) a.a(280.0f, resources);
        this.d = c.b();
        this.d.a(1.0d).b(1.0d).a(new b());
        addView(a(context));
        OnSeekBarChangeListener cVar = new c();
        this.i.setMax(100000);
        this.i.setOnSeekBarChangeListener(cVar);
        this.j.setMax(100000);
        this.j.setOnSeekBarChangeListener(cVar);
        this.k.setAdapter(this.b);
        this.k.setOnItemSelectedListener(new e());
        a();
        setTranslationY(this.e);
    }

    private View a(Context context) {
        Resources resources = getResources();
        int a = a.a(5.0f, resources);
        int a2 = a.a(10.0f, resources);
        int a3 = a.a(20.0f, resources);
        ViewGroup.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, a, 0);
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(a.a(-1, a.a(300.0f, resources)));
        View frameLayout2 = new FrameLayout(context);
        ViewGroup.LayoutParams a4 = a.a();
        a4.setMargins(0, a3, 0, 0);
        frameLayout2.setLayoutParams(a4);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.k = new Spinner(context, 0);
        a4 = a.b();
        a4.gravity = 48;
        a4.setMargins(a2, a2, a2, 0);
        this.k.setLayoutParams(a4);
        frameLayout2.addView(this.k);
        View linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams b = a.b();
        b.setMargins(0, 0, 0, a.a(80.0f, resources));
        b.gravity = 80;
        linearLayout.setLayoutParams(b);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        frameLayout2 = new LinearLayout(context);
        b = a.b();
        b.setMargins(a2, a2, a2, a3);
        frameLayout2.setPadding(a2, a2, a2, a2);
        frameLayout2.setLayoutParams(b);
        frameLayout2.setOrientation(0);
        linearLayout.addView(frameLayout2);
        this.i = new SeekBar(context);
        this.i.setLayoutParams(layoutParams);
        frameLayout2.addView(this.i);
        this.m = new TextView(getContext());
        this.m.setTextColor(this.h);
        b = a.a(a.a(50.0f, resources), -1);
        this.m.setGravity(19);
        this.m.setLayoutParams(b);
        this.m.setMaxLines(1);
        frameLayout2.addView(this.m);
        frameLayout2 = new LinearLayout(context);
        b = a.b();
        b.setMargins(a2, a2, a2, a3);
        frameLayout2.setPadding(a2, a2, a2, a2);
        frameLayout2.setLayoutParams(b);
        frameLayout2.setOrientation(0);
        linearLayout.addView(frameLayout2);
        this.j = new SeekBar(context);
        this.j.setLayoutParams(layoutParams);
        frameLayout2.addView(this.j);
        this.l = new TextView(getContext());
        this.l.setTextColor(this.h);
        ViewGroup.LayoutParams a5 = a.a(a.a(50.0f, resources), -1);
        this.l.setGravity(19);
        this.l.setLayoutParams(a5);
        this.l.setMaxLines(1);
        frameLayout2.addView(this.l);
        View view = new View(context);
        ViewGroup.LayoutParams a6 = a.a(a.a(60.0f, resources), a.a(40.0f, resources));
        a6.gravity = 49;
        view.setLayoutParams(a6);
        view.setOnTouchListener(new a());
        view.setBackgroundColor(Color.argb(255, 0, Opcodes.SHR_LONG, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    public void a() {
        Map b = this.g.b();
        this.b.a();
        this.c.clear();
        for (Entry entry : b.entrySet()) {
            if (entry.getKey() != f.c) {
                this.c.add(entry.getKey());
                this.b.a((String) entry.getValue());
            }
        }
        this.c.add(f.c);
        this.b.a((String) b.get(f.c));
        this.b.notifyDataSetChanged();
        if (this.c.size() > 0) {
            this.k.setSelection(0);
        }
    }

    private void a(f fVar) {
        int round = Math.round(((((float) com.facebook.rebound.c.b(fVar.b)) - 0.0f) * 100000.0f) / 200.0f);
        int round2 = Math.round(((((float) com.facebook.rebound.c.d(fVar.a)) - 0.0f) * 100000.0f) / 50.0f);
        this.i.setProgress(round);
        this.j.setProgress(round2);
    }

    private void c() {
        double d = 1.0d;
        double c = this.d.c();
        com.facebook.rebound.e eVar = this.d;
        if (c == 1.0d) {
            d = 0.0d;
        }
        eVar.b(d);
    }
}
