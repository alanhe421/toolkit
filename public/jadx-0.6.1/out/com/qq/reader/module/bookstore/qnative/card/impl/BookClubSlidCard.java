package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.FlowIndicator;
import com.qq.reader.view.MeasureTextLayout;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookClubSlidCard extends com.qq.reader.module.bookstore.qnative.card.a implements Callback {
    private static final int AUTOFLIP = 1001;
    private static final int INVALL_TIME = 5000;
    private FlowIndicator mFlowIndicator;
    private WeakReferenceHandler mHandler;
    private LayoutInflater mLayoutInflater;
    private e mPageChangeListener = new e(this) {
        final /* synthetic */ BookClubSlidCard a;

        {
            this.a = r1;
        }

        public void onPageSelected(int i) {
            this.a.mFlowIndicator.setCurrent(i);
            this.a.mHandler.removeMessages(1001);
            this.a.mHandler.sendMessageDelayed(this.a.mHandler.obtainMessage(1001), 5000);
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    };
    private ArrayList<View> mViewList = new ArrayList();
    private ViewPager mViewPager;

    private class a extends PagerAdapter {
        final /* synthetic */ BookClubSlidCard a;

        private a(BookClubSlidCard bookClubSlidCard) {
            this.a = bookClubSlidCard;
        }

        public int a() {
            return this.a.mViewList.size();
        }

        public boolean a(View view, Object obj) {
            return obj == view;
        }

        public Object a(ViewGroup viewGroup, int i) {
            View view = (View) this.a.mViewList.get(i);
            viewGroup.addView(view);
            final com.qq.reader.module.bookstore.qnative.item.e eVar = (com.qq.reader.module.bookstore.qnative.item.e) this.a.getItemList().get(i);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    c cVar = new c(bundle);
                    long j;
                    String str;
                    switch (view.getId()) {
                        case R.id.bookclubslideitemmax:
                            if (eVar.a[0] != null) {
                                j = (long) eVar.a[0].r;
                            } else {
                                j = (long) (eVar.a[1] != null ? eVar.a[1].r : 0);
                            }
                            str = eVar.a[0] != null ? eVar.a[0].b : eVar.a[1] != null ? eVar.a[1].b : "";
                            bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
                            bundle.putString("KEY_JUMP_PAGENAME", "bookclubreward");
                            bundle.putString("COMMENT_ID", str);
                            bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubreward));
                            cVar.a(this.b.a.getEvnetListener());
                            return;
                        case R.id.bookclubslideitemtop:
                            j = eVar.a[0] != null ? (long) eVar.a[0].r : 0;
                            str = eVar.a[0] != null ? eVar.a[0].b : "";
                            String str2 = eVar.a[0] != null ? eVar.a[0].q.g : "";
                            bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
                            bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
                            bundle.putString("COMMENT_ID", str);
                            bundle.putString("PARA_TYPE_COMMENT_UID", str2);
                            bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubdetail));
                            cVar.a(this.b.a.getEvnetListener());
                            return;
                        default:
                            return;
                    }
                }
            });
            return view;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) this.a.mViewList.get(i));
        }
    }

    private class b extends Scroller {
        final /* synthetic */ BookClubSlidCard a;
        private int b = 1000;

        public b(BookClubSlidCard bookClubSlidCard, Context context, Interpolator interpolator) {
            this.a = bookClubSlidCard;
            super(context, interpolator);
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.b);
        }

        public void startScroll(int i, int i2, int i3, int i4) {
            super.startScroll(i, i2, i3, i4, this.b);
        }
    }

    public BookClubSlidCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
        setCardId(str);
        this.mLayoutInflater = (LayoutInflater) ReaderApplication.getApplicationImp().getApplicationContext().getSystemService("layout_inflater");
    }

    public int getResLayoutId() {
        return R.layout.bookclubslid;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                com.qq.reader.module.bookstore.qnative.item.e eVar = new com.qq.reader.module.bookstore.qnative.item.e();
                eVar.parseData(optJSONArray.optJSONObject(i));
                getItemList().add(eVar);
            }
        }
        return true;
    }

    String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    public void attachView() {
        if (this.mHandler == null) {
            this.mHandler = new WeakReferenceHandler(this);
        }
        this.mViewPager = (ViewPager) ap.a(getRootView(), R.id.commentveiwpage);
        try {
            Field declaredField = ViewPager.class.getDeclaredField("m");
            declaredField.setAccessible(true);
            declaredField.set(this.mViewPager, new b(this, this.mViewPager.getContext(), null));
        } catch (NoSuchFieldException e) {
            f.d("slide", " " + e);
        } catch (IllegalArgumentException e2) {
            f.d("slide", " " + e2);
        } catch (IllegalAccessException e3) {
            f.d("slide", " " + e3);
        }
        this.mFlowIndicator = (FlowIndicator) ap.a(getRootView(), R.id.flowindicator);
        this.mViewPager.setOnPageChangeListener(this.mPageChangeListener);
        this.mFlowIndicator.setSelectedColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textgreen));
        this.mFlowIndicator.setUnSelectedColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.emotion_division_line_background));
        this.mFlowIndicator.setRadius(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.bookclub_flowindicate_radius));
        this.mFlowIndicator.setSpace(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.bookclub_flowindicate_radius) * 2);
        this.mViewList.clear();
        for (s sVar : getItemList()) {
            com.qq.reader.module.bookstore.qnative.item.e eVar = (com.qq.reader.module.bookstore.qnative.item.e) sVar;
            View inflate;
            TextView textView;
            TextView textView2;
            TextView textView3;
            if (eVar.b == 1) {
                inflate = this.mLayoutInflater.inflate(R.layout.bookclubsliditemmax, null);
                MeasureTextLayout measureTextLayout = (MeasureTextLayout) inflate.findViewById(R.id.maxexpend_layout);
                textView = (TextView) inflate.findViewById(R.id.maxexpend_content);
                textView2 = (TextView) inflate.findViewById(R.id.maxexpend_name);
                textView3 = (TextView) inflate.findViewById(R.id.maxexpend_time);
                if (eVar.a[1] == null || !"max".equals(eVar.a[1].a)) {
                    measureTextLayout.setVisibility(8);
                } else {
                    com.qq.reader.module.bookstore.qnative.item.e.a aVar = eVar.a[1];
                    measureTextLayout.setVisibility(0);
                    if (aVar.q != null) {
                        textView2.setText(trim(aVar.q.a) + "");
                        textView.setText(" : " + trim(aVar.m));
                    } else {
                        textView2.setText("");
                        textView.setText(trim(aVar.m));
                    }
                    if (aVar.e > 0) {
                        textView3.setText(k.c(aVar.e));
                    } else {
                        textView3.setText(k.c(aVar.d));
                    }
                }
                measureTextLayout = (MeasureTextLayout) inflate.findViewById(R.id.newexpend_layout);
                textView = (TextView) inflate.findViewById(R.id.newexpend_content);
                textView2 = (TextView) inflate.findViewById(R.id.newexpend_name);
                textView3 = (TextView) inflate.findViewById(R.id.newexpend_time);
                if (eVar.a[0] == null || !"new".equals(eVar.a[0].a)) {
                    measureTextLayout.setVisibility(8);
                } else {
                    com.qq.reader.module.bookstore.qnative.item.e.a aVar2 = eVar.a[0];
                    measureTextLayout.setVisibility(0);
                    if (aVar2.q != null) {
                        textView2.setText(trim(aVar2.q.a) + "");
                        textView.setText(" : " + trim(aVar2.m));
                    } else {
                        textView.setText(trim(aVar2.m));
                        textView2.setText("");
                    }
                    if (aVar2.e > 0) {
                        textView3.setText(k.c(aVar2.e));
                    } else {
                        textView3.setText(k.c(aVar2.d));
                    }
                }
                this.mViewList.add(inflate);
            } else if (eVar.b == 2) {
                inflate = this.mLayoutInflater.inflate(R.layout.bookclubsliditemtop, null);
                TextView textView4 = (TextView) inflate.findViewById(R.id.firstexpend_content);
                textView = (TextView) inflate.findViewById(R.id.secondexpend_content);
                textView2 = (TextView) inflate.findViewById(R.id.agree);
                textView3 = (TextView) inflate.findViewById(R.id.reply);
                if (eVar.a[0] != null) {
                    if (TextUtils.isEmpty(eVar.a[0].m)) {
                        textView4.setVisibility(8);
                        textView.setSingleLine(false);
                        textView.setMaxLines(2);
                        textView.setText("" + eVar.a[0].l);
                        textView2.setText("" + j.a((long) eVar.a[0].s));
                        textView3.setText("" + j.a((long) eVar.a[0].k));
                    } else {
                        textView4.setText("" + eVar.a[0].m);
                        textView.setText("" + eVar.a[0].l);
                        textView2.setText("" + j.a((long) eVar.a[0].s));
                        textView3.setText("" + j.a((long) eVar.a[0].k));
                    }
                }
                this.mViewList.add(inflate);
            }
        }
        this.mViewPager.setAdapter(new a());
        this.mFlowIndicator.setSize(this.mViewList.size());
        this.mFlowIndicator.setCurrent(this.mViewPager.getCurrentItem());
        this.mHandler.removeMessages(1001);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1001), 5000);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                int currentItem = this.mViewPager.getCurrentItem();
                if (currentItem == this.mViewList.size() - 1) {
                    this.mViewPager.setCurrentItem(0, false);
                    return true;
                }
                this.mViewPager.setCurrentItem(currentItem + 1, true);
                return true;
            default:
                return false;
        }
    }
}
