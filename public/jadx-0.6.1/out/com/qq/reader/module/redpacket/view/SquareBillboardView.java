package com.qq.reader.module.redpacket.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.e;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.redpacket.model.RedPacketMessage;
import com.qq.reader.module.redpacket.square.a.d;
import com.qq.reader.view.AdvViewPager;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;

public class SquareBillboardView extends RelativeLayout {
    d a = new d();
    public com.qq.reader.module.bookstore.qnative.c.a b;
    private Context c;
    private AdvViewPager d;
    private LinearLayout e;
    private RedPacketLoopVerticalViewPager f;
    private ArrayList<RedPacketMessageView> g = new ArrayList();
    private com.qq.reader.module.redpacket.square.data.a h;

    class a extends PagerAdapter {
        public ArrayList<View> a;
        final /* synthetic */ SquareBillboardView b;

        public a(SquareBillboardView squareBillboardView, ArrayList<View> arrayList) {
            this.b = squareBillboardView;
            this.a = arrayList;
        }

        public boolean a(View view, Object obj) {
            return view == obj;
        }

        public int a() {
            return this.a.size();
        }

        public Object a(ViewGroup viewGroup, int i) {
            View view = (View) this.a.get(i);
            if (view.getParent() == null) {
                viewGroup.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                viewGroup.addView(view);
            }
            return view;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    public SquareBillboardView(Context context) {
        super(context);
        this.c = context;
        LayoutInflater.from(context).inflate(R.layout.redpacket_square_billboard_card_layout, this, true);
        b();
    }

    public SquareBillboardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        LayoutInflater.from(context).inflate(R.layout.redpacket_square_billboard_card_layout, this, true);
        b();
    }

    public void setEventListener(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.b = aVar;
    }

    private void b() {
        this.d = (AdvViewPager) findViewById(R.id.localstore_adv_0_viewpager);
        this.f = (RedPacketLoopVerticalViewPager) findViewById(R.id.redpacket_square_message_viewpager);
        this.e = (LinearLayout) findViewById(R.id.localstore_adv_0_indicator);
    }

    public void a(HashMap<String, ArrayList<String>> hashMap) {
        ArrayList arrayList = new ArrayList();
        if (hashMap.containsKey("bookrank")) {
            RedPacketSquareBookRankView redPacketSquareBookRankView = new RedPacketSquareBookRankView(this.c);
            redPacketSquareBookRankView.setEventListener(this.b);
            redPacketSquareBookRankView.a((ArrayList) hashMap.get("bookrank"));
            arrayList.add(redPacketSquareBookRankView);
        }
        if (hashMap.containsKey("userrank")) {
            RedPacketSquareUserRankView redPacketSquareUserRankView = new RedPacketSquareUserRankView(this.c);
            redPacketSquareUserRankView.setEventListener(this.b);
            redPacketSquareUserRankView.a((ArrayList) hashMap.get("userrank"));
            arrayList.add(redPacketSquareUserRankView);
        }
        this.d.setAdapter(new a(this, arrayList));
        this.d.j();
        if (arrayList.size() == 0) {
            getViewPager().setVisibility(8);
            getPagerIndicator().setVisibility(8);
        } else if (arrayList.size() == 1) {
            getViewPager().setVisibility(0);
            getPagerIndicator().setVisibility(8);
        } else {
            getViewPager().setVisibility(0);
            getPagerIndicator().setVisibility(0);
            c();
        }
        if (this.g != null) {
            this.g.clear();
        }
        this.h = new com.qq.reader.module.redpacket.square.data.a(this.c);
        RedPacketMessageView d = this.h.d();
        d.a("助力作者，还能抢红包", "");
        d.setEventListener(this.b);
        this.g.add(d);
        this.a.a(this.g);
        this.f.setAdapter(this.a);
        this.f.a();
    }

    private AdvViewPager getViewPager() {
        return this.d;
    }

    private LinearLayout getPagerIndicator() {
        return this.e;
    }

    private void c() {
        int a;
        int i;
        int childCount = getPagerIndicator().getChildCount();
        a aVar = (a) getViewPager().getAdapter();
        if (aVar != null) {
            a = aVar.a();
        } else {
            a = 0;
        }
        if (childCount > a) {
            for (i = 0; i < childCount - a; i++) {
                getPagerIndicator().removeViewAt(0);
            }
        } else {
            int dimensionPixelSize = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_12);
            for (i = 0; i < a - childCount; i++) {
                View imageView = new ImageView(ReaderApplication.getApplicationImp());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                if (i != 0) {
                    layoutParams.leftMargin = dimensionPixelSize;
                }
                layoutParams.gravity = 16;
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ScaleType.FIT_XY);
                getPagerIndicator().addView(imageView);
            }
        }
        a = getPagerIndicator().getChildCount();
        for (i = 0; i < a; i++) {
            getPagerIndicator().getChildAt(i).setBackgroundResource(R.drawable.redpacket_square_viewpagerindicator_unselect);
        }
        getPagerIndicator().getChildAt(getViewPager().getCurrentItem()).setBackgroundResource(R.drawable.redpacket_square_viewpagerindicator_select);
        getViewPager().setOnPageChangeListener(new e(this) {
            final /* synthetic */ SquareBillboardView a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (this.a.getPagerIndicator() != null) {
                    for (int i2 = 0; i2 < this.a.getPagerIndicator().getChildCount(); i2++) {
                        this.a.getPagerIndicator().getChildAt(i2).setBackgroundResource(R.drawable.redpacket_square_viewpagerindicator_unselect);
                    }
                    View childAt = this.a.getPagerIndicator().getChildAt(i);
                    if (childAt != null) {
                        childAt.setBackgroundResource(R.drawable.redpacket_square_viewpagerindicator_select);
                    }
                }
                AdvViewPager b = this.a.getViewPager();
                if (b != null) {
                    b.j();
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void a(ArrayList<RedPacketMessage> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            if (this.f != null) {
                this.f.b();
            }
            if (this.g != null) {
                this.g.clear();
            }
            if (this.h != null) {
                this.h.a();
            }
            for (int i = 0; i < arrayList.size(); i++) {
                RedPacketMessageView c = this.h.c();
                c.setEventListener(this.b);
                c.a((RedPacketMessage) arrayList.get(i));
                this.g.add(c);
            }
            RedPacketMessageView d = this.h.d();
            d.setEventListener(this.b);
            d.a("助力作者，还能抢红包", "");
            this.g.add(d);
            this.a.a(this.g);
            this.f.setAdapter(this.a);
            this.a.c();
            this.f.a();
        }
    }

    public void a() {
        if (this.h != null) {
            this.h.b();
        }
        if (this.g != null) {
            this.g.clear();
        }
    }
}
