package com.qq.reader.common.widget.viewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: ViewPagerAdapter */
public class a extends PagerAdapter {
    private Context a;
    private int[] b;
    private HashMap<Integer, ViewPagerItemView> c;
    private b d;
    private int e;
    private boolean f;

    public void a(View view, int i, Object obj) {
        if (obj instanceof ViewPagerItemView) {
            ((ViewPagerItemView) obj).a();
        }
    }

    public void b(View view) {
    }

    public int a() {
        if (com.qq.reader.common.c.a.M) {
            return this.b.length + 1;
        }
        return this.b.length;
    }

    public Object a(View view, final int i) {
        if (i >= this.b.length) {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.preference_select_view, null);
            RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.rg);
            int aS = d.aS(this.a);
            if (aS != 0) {
                a(radioGroup, aS);
            } else {
                a(radioGroup, d.aP(this.a));
            }
            radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.boy:
                            this.a.e = 1;
                            j.a(86, 1);
                            i.a("event_B87", null, this.a.a);
                            StatisticsManager.a().a("event_B87", null);
                            break;
                        case R.id.girl:
                            this.a.e = 2;
                            j.a(87, 1);
                            i.a("event_B88", null, this.a.a);
                            StatisticsManager.a().a("event_B88", null);
                            break;
                        case R.id.publish:
                            this.a.e = 3;
                            j.a(88, 1);
                            i.a("event_B89", null, this.a.a);
                            StatisticsManager.a().a("event_B89", null);
                            break;
                    }
                    if (!this.a.f) {
                        if (!(this.a.a instanceof MainActivity) || ((MainActivity) this.a.a).getHandler() == null) {
                            this.a.e();
                        } else {
                            ((MainActivity) this.a.a).getHandler().postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.e();
                                }
                            }, 500);
                        }
                    }
                    this.a.f = true;
                }
            });
            ((ViewPager) view).addView(inflate);
            return inflate;
        } else if (this.c.containsKey(Integer.valueOf(i))) {
            ViewPagerItemView viewPagerItemView = (ViewPagerItemView) this.c.get(Integer.valueOf(i));
            viewPagerItemView.setData(this.b[i]);
            return viewPagerItemView;
        } else {
            Object viewPagerItemView2 = new ViewPagerItemView(this.a);
            viewPagerItemView2.setData(this.b[i]);
            this.c.put(Integer.valueOf(i), viewPagerItemView2);
            ((ViewPager) view).addView(viewPagerItemView2);
            viewPagerItemView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.d != null) {
                        this.b.d.a(i);
                    }
                }
            });
            return viewPagerItemView2;
        }
    }

    private void e() {
        Intent intent = new Intent("com.qq.reader.selectpreference.mainactivity");
        intent.putExtra("userType", this.e);
        d.F(ReaderApplication.getApplicationImp().getApplicationContext(), this.e);
        com.qq.reader.module.bookstore.qweb.channel.a.a(this.a);
        this.a.sendBroadcast(intent);
        if (this.d != null) {
            this.d.a();
        }
        c();
    }

    public boolean a(View view, Object obj) {
        return view == obj;
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable b() {
        return null;
    }

    public void a(View view) {
    }

    private void a(RadioGroup radioGroup, int i) {
        if (i != 0) {
            switch (i) {
                case 1:
                    radioGroup.check(R.id.boy);
                    return;
                case 2:
                    radioGroup.check(R.id.girl);
                    return;
                case 3:
                    radioGroup.check(R.id.publish);
                    return;
                default:
                    return;
            }
        }
    }

    public void d() {
        this.d = null;
        if (this.c != null) {
            for (Entry value : this.c.entrySet()) {
                ((ViewPagerItemView) value.getValue()).a();
            }
        }
    }
}
