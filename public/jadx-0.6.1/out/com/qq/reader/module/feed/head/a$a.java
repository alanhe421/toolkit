package com.qq.reader.module.feed.head;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.z;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.adv.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FeedHeadAdv */
protected class a$a extends PagerAdapter {
    int a = 0;
    final /* synthetic */ a b;
    private List<a> c;
    private ArrayList<View> d = new ArrayList();
    private View[] e = new View[2];
    private Context f;

    public ArrayList<View> d() {
        return this.d;
    }

    public a$a(a aVar, Context context) {
        this.b = aVar;
        this.f = context;
        this.c = new ArrayList();
    }

    public boolean a(List<a> list) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.c.size() != list.size()) {
            this.c.clear();
            this.c.addAll(list);
            return true;
        }
        for (int i = 0; i < this.c.size(); i++) {
            if (((a) this.c.get(i)).d() != ((a) list.get(i)).d()) {
                this.c.clear();
                this.c.addAll(list);
                return true;
            }
        }
        return false;
    }

    public void e() {
        this.d.clear();
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                a((a) this.c.get(i), i).setOnClickListener(this.b);
            }
        }
    }

    private View a(a aVar, int i) {
        View a;
        switch (aVar.n()) {
            case 1:
            case 2:
                a = a(aVar, false, i);
                break;
            case 3:
            case 4:
                a = a(aVar, true, i);
                break;
            case 5:
                a = a(aVar);
                break;
            default:
                a = a(aVar, false, i);
                break;
        }
        a.setTag(aVar);
        a.setTag(R.string.obj_tag, i + "");
        return a;
    }

    private View a(a aVar) {
        final View g = g();
        g.setImageResource(R.drawable.feed_header_adv_loading_icon);
        g.setBackgroundResource(this.b.h());
        g.setScaleType(ScaleType.FIT_XY);
        c.a(this.b.getFromActivity()).a(aVar.g(), g, com.qq.reader.common.imageloader.a.a().a(R.drawable.feedhead_advimg, com.qq.reader.common.c.a.bU, (int) this.b.getFromActivity().getResources().getDimension(R.dimen.feedhead_adv_height)), new e<String, b>(this) {
            final /* synthetic */ a$a b;

            public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                return false;
            }

            public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                g.setBackgroundDrawable(null);
                return false;
            }
        });
        return g;
    }

    private ImageView g() {
        ImageView imageView = new ImageView(this.f);
        imageView.setLayoutParams(new LayoutParams(-1, -1));
        imageView.setBackgroundColor(0);
        imageView.setScaleType(ScaleType.FIT_XY);
        this.d.add(imageView);
        return imageView;
    }

    private View a(a aVar, boolean z, final int i) {
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.feed_headadv_bookinfo, null);
        if (z) {
            ((ViewStub) inflate.findViewById(R.id.feedheadadv_infoAnimation)).inflate();
            NewHistogramView newHistogramView = (NewHistogramView) inflate.findViewById(R.id.feedheadadv_graphlayout);
            inflate.findViewById(R.id.feedheadadv_line).getLayoutParams().width = ao.a(320.0f);
        } else {
            ((ViewStub) inflate.findViewById(R.id.feedheadadv_infopic)).inflate();
            final ImageView imageView = (ImageView) inflate.findViewById(R.id.feedheadadv_infopiclayout);
            c.a(this.f).a(aVar.g(), imageView, com.qq.reader.common.imageloader.a.a().a(R.drawable.feed_header_book_loading_icon), new e<String, b>(this) {
                final /* synthetic */ a$a b;

                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                    return false;
                }

                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                    imageView.setBackgroundDrawable(null);
                    return false;
                }
            });
            final int i2 = inflate.getContext().getResources().getDisplayMetrics().widthPixels;
            this.b.a.a(new ViewPager.e(this) {
                ObjectAnimator a;
                boolean b;
                final /* synthetic */ a$a f;

                public void onPageScrolled(int i, float f, int i2) {
                    if (i % this.f.a() != i) {
                        this.b = false;
                    } else if (f == 0.0f && !this.b) {
                        final float a = (float) ((i2 - ao.a(128.0f)) - ((RelativeLayout.LayoutParams) imageView.getLayoutParams()).rightMargin);
                        if (this.a == null) {
                            this.a = ObjectAnimator.ofFloat(imageView, "x", new float[]{(float) i2, a});
                            this.a.setInterpolator(new DecelerateInterpolator());
                            this.a.addListener(new AnimatorListener(this) {
                                final /* synthetic */ AnonymousClass3 b;

                                public void onAnimationStart(Animator animator) {
                                    imageView.setVisibility(0);
                                }

                                public void onAnimationEnd(Animator animator) {
                                    z.d(imageView, a);
                                }

                                public void onAnimationCancel(Animator animator) {
                                    z.d(imageView, a);
                                }

                                public void onAnimationRepeat(Animator animator) {
                                }
                            });
                            this.a.setDuration(300);
                        }
                        this.a.cancel();
                        this.b = true;
                        imageView.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.start();
                            }
                        });
                    }
                }

                public void onPageSelected(int i) {
                    if (i % this.f.a() != i) {
                        imageView.setVisibility(4);
                        this.b = false;
                    }
                }

                public void onPageScrollStateChanged(int i) {
                }
            });
        }
        View findViewById = inflate.findViewById(R.id.top_layout);
        switch (d.aS(ReaderApplication.getApplicationImp())) {
            case 1:
                if (i % 2 != 0) {
                    findViewById.setBackgroundResource(R.drawable.feed_adv_bg_male_2);
                    break;
                }
                findViewById.setBackgroundResource(R.drawable.feed_adv_bg_male_1);
                break;
            case 2:
                if (i % 2 != 0) {
                    findViewById.setBackgroundResource(R.drawable.feed_adv_bg_female_2);
                    break;
                }
                findViewById.setBackgroundResource(R.drawable.feed_adv_bg_female_1);
                break;
            default:
                if (i % 2 != 0) {
                    findViewById.setBackgroundResource(R.drawable.feed_adv_bg_publish_2);
                    break;
                }
                findViewById.setBackgroundResource(R.drawable.feed_adv_bg_publish_1);
                break;
        }
        RisingNumberView risingNumberView = (RisingNumberView) inflate.findViewById(R.id.feedheadadv_maintext);
        long o = aVar.o();
        if (o > 0) {
            risingNumberView.setNumber(o);
        }
        risingNumberView.setText(aVar.s());
        ((TextView) inflate.findViewById(R.id.feedheadadv_secondtext)).setText(aVar.e());
        ((TextView) inflate.findViewById(R.id.feedheadadv_lasttext)).setText(aVar.i());
        this.d.add(inflate);
        risingNumberView.b();
        return inflate;
    }

    public boolean a(View view, Object obj) {
        return view == obj;
    }

    public int a() {
        return a.a(this.b) == null ? this.c.size() : 1;
    }

    public int a(Object obj) {
        this.a++;
        return -2;
    }

    public Object a(ViewGroup viewGroup, int i) {
        View view = (View) this.d.get(i);
        if (view.getParent() == null) {
            viewGroup.addView(view);
        } else {
            ((ViewGroup) view.getParent()).removeView(view);
            viewGroup.addView(view);
        }
        if (a() == 1) {
            a.a(this.b, 0);
        }
        return view;
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        if (this.a > 0) {
            this.a--;
            viewGroup.removeView((View) obj);
        }
    }

    public List<a> f() {
        return this.c;
    }
}
