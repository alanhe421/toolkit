package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.d;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class BookClubBookInfoCard extends a {
    private int mCtype;

    public BookClubBookInfoCard(b bVar, String str) {
        super(bVar, str);
        setCardId(str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_bookclubbookinfo;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        s dVar = new d();
        dVar.parseData(jSONObject);
        dVar.a(this.mCtype);
        getItemList().clear();
        addItem(dVar);
        return true;
    }

    public long getBookId() {
        d dVar = (d) getItemList().get(0);
        if (dVar != null) {
            return dVar.b();
        }
        return 0;
    }

    public int getCtype() {
        return this.mCtype;
    }

    public void setCtype(int i) {
        this.mCtype = i;
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            int a;
            d dVar = (d) getItemList().get(0);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.bookinfo_cover);
            c.a(getEvnetListener().getFromActivity()).a(dVar.a(), imageView, com.qq.reader.common.imageloader.a.a().j());
            TextView textView = (TextView) ap.a(getRootView(), R.id.bookinfo_name);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.bookinfo_author);
            textView2.setText(dVar.d());
            if (!TextUtils.isEmpty(dVar.c())) {
                CharSequence charSequence;
                String d = dVar.d();
                CharSequence c = dVar.c();
                String str = "...";
                int dip2px = (ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - dip2px(118.0f)) - 22;
                int a2 = (int) ao.a(textView.getPaint(), (String) c);
                Object obj;
                if (((int) ao.a(textView2.getPaint(), d)) + a2 > dip2px) {
                    if (textView2.length() <= 4 || ao.a(textView2.getPaint(), (float) (dip2px - a2), str + d) >= 4) {
                        obj = d;
                    } else {
                        int i;
                        if (d.length() > 4) {
                            i = 4;
                        } else {
                            i = d.length();
                        }
                        charSequence = d.substring(0, i) + str;
                    }
                    a = (int) ao.a(textView2.getPaint(), (String) charSequence);
                    if (a2 + a > dip2px) {
                        a = ao.a(textView.getPaint(), (float) (dip2px - a), str + c);
                        if (a < c.length() + 2) {
                            d = c.substring(0, a - str.length()) + str;
                        } else {
                            CharSequence charSequence2 = c;
                        }
                        int a3 = (int) ao.a(textView.getPaint(), d);
                        c = d;
                    }
                } else {
                    obj = d;
                }
                textView.setText(c);
                textView2.setText(charSequence);
            }
            ((TextView) ap.a(getRootView(), R.id.bookinfo_comments)).setText("" + dVar.g());
            textView = (TextView) ap.a(getRootView(), R.id.bookinfo_hotweek_title);
            textView2 = (TextView) ap.a(getRootView(), R.id.bookinfo_hotweek);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.bookinfo_rank);
            a = dVar.f();
            if (this.mCtype == 9 || a <= 0) {
                textView.setVisibility(4);
                textView2.setVisibility(4);
                textView3.setVisibility(4);
            } else {
                textView.setVisibility(0);
                textView2.setVisibility(0);
                textView3.setVisibility(0);
                textView2.setText("" + dVar.f());
                String str2 = "( 排名";
                String str3 = "" + dVar.j();
                CharSequence spannableStringBuilder = new SpannableStringBuilder(str2 + str3 + " )");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_datainfo)), str2.length(), str2.length() + str3.length(), 33);
                if (dVar.j() > 0) {
                    textView3.setText(spannableStringBuilder);
                }
            }
            textView = (TextView) ap.a(getRootView(), R.id.bookinfo_admin);
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_bookinfo_admin);
            CharSequence e = dVar.e();
            if (TextUtils.isEmpty(e)) {
                linearLayout.setVisibility(8);
                return;
            }
            linearLayout.setVisibility(0);
            textView.setText(e);
        }
    }

    public void addCommentCount() {
        final d dVar = (d) getItemList().get(0);
        dVar.h();
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ BookClubBookInfoCard b;

            public void run() {
                TextView textView = (TextView) ap.a(this.b.getRootView(), R.id.bookinfo_comments);
                if (textView != null && dVar != null) {
                    textView.setText("" + dVar.g());
                }
            }
        });
    }

    public void minusCommentCount() {
        final d dVar = (d) getItemList().get(0);
        dVar.i();
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ BookClubBookInfoCard b;

            public void run() {
                TextView textView = (TextView) ap.a(this.b.getRootView(), R.id.bookinfo_comments);
                if (textView != null && dVar != null) {
                    textView.setText("" + dVar.g());
                }
            }
        });
    }

    private static int dip2px(float f) {
        return (int) ((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
