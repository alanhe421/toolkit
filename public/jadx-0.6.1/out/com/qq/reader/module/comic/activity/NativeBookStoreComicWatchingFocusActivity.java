package com.qq.reader.module.comic.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.b.k;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.entity.ComicWatchingFocusItem;
import com.qq.reader.module.comic.views.PhotoView;
import com.qq.reader.module.comic.views.RectInfo;
import com.qq.reader.view.CirclePageIndicator;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import java.util.List;

public class NativeBookStoreComicWatchingFocusActivity extends Activity implements e {
    private String a = "NativeBookStoreComicWatchingFocusActivity";
    private ViewPager b;
    private ImageView c;
    private CirclePageIndicator d;
    private a e;
    private List<ComicWatchingFocusItem> f;
    private SparseArray<PhotoView> g;
    private SparseArray<PhotoView> h;
    private int i;
    private int j;
    private RectInfo k;
    private AlphaAnimation l = new AlphaAnimation(0.0f, 1.0f);
    private AlphaAnimation m = new AlphaAnimation(1.0f, 0.0f);

    private class a extends PagerAdapter {
        final /* synthetic */ NativeBookStoreComicWatchingFocusActivity a;

        private a(NativeBookStoreComicWatchingFocusActivity nativeBookStoreComicWatchingFocusActivity) {
            this.a = nativeBookStoreComicWatchingFocusActivity;
        }

        public int a() {
            if (this.a.f != null) {
                return this.a.f.size();
            }
            return 0;
        }

        public boolean a(View view, Object obj) {
            return (obj == null || view == null || !obj.equals(view)) ? false : true;
        }

        public Object a(ViewGroup viewGroup, final int i) {
            View inflate = View.inflate(this.a, R.layout.comic_detail_watching_focus_zoom_img_item_layout, null);
            final PhotoView photoView = (PhotoView) inflate.findViewById(R.id.small_img);
            photoView.setVisibility(0);
            final PhotoView photoView2 = (PhotoView) inflate.findViewById(R.id.big_img);
            photoView2.setVisibility(0);
            photoView2.setTag(R.id.loading, inflate.findViewById(R.id.loading));
            photoView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    this.c.a.c.startAnimation(this.c.a.m);
                    if (i == this.c.a.i) {
                        photoView.a(this.c.a.k, new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.c.a.finish();
                            }
                        });
                        return;
                    }
                    photoView.a(this.c.a.a(i), new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a.finish();
                        }
                    });
                }
            });
            photoView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    this.c.a.c.startAnimation(this.c.a.m);
                    if (i == this.c.a.i) {
                        photoView2.a(this.c.a.k, new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.c.a.finish();
                            }
                        });
                        return;
                    }
                    photoView2.a(this.c.a.a(i), new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a.finish();
                        }
                    });
                }
            });
            c.a(this.a).a(((ComicWatchingFocusItem) this.a.f.get(i)).smallUrl, photoView, new com.bumptech.glide.request.e<String, b>(this) {
                final /* synthetic */ a c;

                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                    photoView.setImageResource(R.drawable.comic_watch_focus_default_bg);
                    return true;
                }

                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                    if (jVar instanceof k) {
                        View b_ = ((k) jVar).b_();
                        if (b_ instanceof PhotoView) {
                            PhotoView photoView = (PhotoView) b_;
                            this.c.a.h.put(i, photoView);
                            if (i == this.c.a.j) {
                                photoView.a(this.c.a.k);
                            }
                        }
                    }
                    return false;
                }
            });
            c.a(this.a).a(((ComicWatchingFocusItem) this.a.f.get(i)).bigUrl, photoView2, new com.bumptech.glide.request.e<String, b>(this) {
                final /* synthetic */ a d;

                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                    photoView2.setVisibility(8);
                    if (jVar instanceof k) {
                        View b_ = ((k) jVar).b_();
                        if (!(b_ == null || b_.getContext() == null || i != this.d.a.j)) {
                            af.a(b_.getContext(), "加载原图失败", 0).a();
                        }
                        if (b_ != null) {
                            Object tag = b_.getTag(R.id.loading);
                            if (tag instanceof View) {
                                ((View) tag).setVisibility(8);
                            }
                        }
                    }
                    return false;
                }

                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                    if (jVar instanceof k) {
                        View b_ = ((k) jVar).b_();
                        if (b_ != null) {
                            Object tag = b_.getTag(R.id.loading);
                            if (tag instanceof View) {
                                ((View) tag).setVisibility(8);
                            }
                            photoView.setVisibility(8);
                        }
                        if (b_ instanceof PhotoView) {
                            this.d.a.g.put(i, (PhotoView) b_);
                        }
                    }
                    return false;
                }
            });
            viewGroup.addView(inflate, new LayoutParams(-1, -1));
            return inflate;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null && (obj instanceof View)) {
                viewGroup.removeView((View) obj);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.comic_detail_watchig_focus_activity_layout);
        a();
        a(getIntent());
    }

    private void a() {
        this.b = (ViewPager) findViewById(R.id.vp_gallery);
        this.d = (CirclePageIndicator) findViewById(R.id.vp_indicator);
        this.c = (ImageView) findViewById(R.id.comic_focus_bg);
        this.l.setDuration(200);
        this.m.setDuration(200);
        this.l.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ NativeBookStoreComicWatchingFocusActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.c.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.m.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ NativeBookStoreComicWatchingFocusActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.c.setVisibility(4);
                this.a.finish();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.c.startAnimation(this.l);
    }

    private void a(Intent intent) {
        this.f = (List) intent.getSerializableExtra("KEY_URLS");
        this.g = new SparseArray();
        this.h = new SparseArray();
        this.i = intent.getIntExtra("KEY_SELECTED_INDEX", 0);
        this.j = this.i;
        this.k = (RectInfo) intent.getParcelableExtra("KEY_SELECTED_INFO");
        this.e = new a();
        this.b.setAdapter(this.e);
        this.b.a(this.d);
        this.d.setRadius((float) getResources().getDimensionPixelSize(R.dimen.common_dp_3));
        this.d.setOnPageChangeListener(this);
        this.d.setViewPager(this.b, this.j);
    }

    public static int a(Activity activity, List<ComicWatchingFocusItem> list, int i, RectInfo rectInfo) {
        if (list == null || list.size() <= 0 || i >= list.size()) {
            return -1;
        }
        Intent intent = new Intent(activity, NativeBookStoreComicWatchingFocusActivity.class);
        intent.putExtra("KEY_URLS", (Serializable) list);
        intent.putExtra("KEY_SELECTED_INDEX", i);
        intent.putExtra("KEY_SELECTED_INFO", rectInfo);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.short_alpha_in, R.anim.short_alpha_out);
        return 0;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.j = i;
    }

    public void onPageScrollStateChanged(int i) {
    }

    private RectInfo a(int i) {
        RectInfo a = this.k.a();
        float a2 = (((float) ao.a(12.0f)) + this.k.c.width()) * ((float) (i - this.i));
        RectF rectF = a.a;
        rectF.left += a2;
        rectF = a.a;
        rectF.right = a2 + rectF.right;
        return a;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.short_alpha_in, R.anim.short_alpha_out);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!hasWindowFocus()) {
            return false;
        }
        if (i == 4 && (a(this.g, this.j) || a(this.h, this.j))) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private boolean a(SparseArray<PhotoView> sparseArray, int i) {
        if (sparseArray == null || i >= sparseArray.size() || sparseArray.get(i) == null || !((PhotoView) sparseArray.get(i)).isShown()) {
            return false;
        }
        this.c.startAnimation(this.m);
        ((PhotoView) sparseArray.get(i)).a(a(i), new Runnable(this) {
            final /* synthetic */ NativeBookStoreComicWatchingFocusActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.finish();
            }
        });
        return true;
    }

    protected void onDestroy() {
        this.l.cancel();
        this.m.cancel();
        super.onDestroy();
    }
}
