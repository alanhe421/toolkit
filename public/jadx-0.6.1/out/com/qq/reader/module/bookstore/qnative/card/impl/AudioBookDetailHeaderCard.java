package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.content.d;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.QRImageView;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class AudioBookDetailHeaderCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a headerItem;
    private JSONObject mBookInfo;

    public class a extends s {
        public String a;
        public String b;
        public String c;
        public int d;
        public int e;
        public String f;
        public boolean g;
        public String h;
        public String i;
        public long j;
        public int k;
        public boolean l = false;
        public String m;
        public String n;
        final /* synthetic */ AudioBookDetailHeaderCard o;

        public a(AudioBookDetailHeaderCard audioBookDetailHeaderCard) {
            this.o = audioBookDetailHeaderCard;
        }

        public void parseData(JSONObject jSONObject) {
            boolean z = true;
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("audio");
                this.l = jSONObject.optInt("isBuyed", 0) > 0;
                if (optJSONObject != null) {
                    this.k = optJSONObject.optInt("chargeType");
                    this.a = optJSONObject.optString("anchorName");
                    this.i = optJSONObject.optString("audioName");
                    this.c = optJSONObject.optString("subcategoryName");
                    this.j = Long.valueOf(optJSONObject.optString("adid")).longValue();
                    this.b = ao.a(this.j, true, 180);
                    this.d = optJSONObject.optInt("money", 0);
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("discountInfo");
                if (optJSONObject2 != null) {
                    this.e = optJSONObject2.optInt("discount", 100);
                    this.f = optJSONObject2.optString(SocialConstants.PARAM_APP_DESC);
                }
                optJSONObject2 = jSONObject.optJSONObject("detailDes");
                if (optJSONObject2 != null) {
                    this.m = optJSONObject2.optString("firstL");
                    this.n = optJSONObject2.optString("first");
                    if (optJSONObject2.optInt("needOpenVip", 0) <= 0) {
                        z = false;
                    }
                    this.g = z;
                    this.h = optJSONObject2.optString("second");
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
    }

    public AudioBookDetailHeaderCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_book_detail_header_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mBookInfo = jSONObject;
        JSONObject optJSONObject = jSONObject.optJSONObject("audio");
        if (optJSONObject == null || optJSONObject.optLong("cpid", 0) == 2000000804) {
            return false;
        }
        this.headerItem = new a(this);
        this.headerItem.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        if (this.headerItem != null) {
            final ImageView imageView = (QRImageView) ap.a(getRootView(), R.id.img_cover);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.headerItem.b, imageView, com.qq.reader.common.imageloader.a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ AudioBookDetailHeaderCard b;

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    return false;
                }

                public boolean a(final com.bumptech.glide.load.resource.a.b bVar, final String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void run() {
                            d a = d.a(ReaderApplication.getInstance().getApplication());
                            if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                                Parcelable b = ((com.bumptech.glide.load.resource.bitmap.j) bVar).b();
                                Intent intent = new Intent("detail.loadimg");
                                intent.putExtra("message", str);
                                intent.putExtra("image", b);
                                a.a(intent);
                            }
                        }
                    }, 200);
                    imageView.setImageDrawable(bVar);
                    return true;
                }
            });
            imageView.setBorderColor(-1);
            imageView.setBorderWidth((float) ao.a(2.0f));
            TextView textView = (TextView) ap.a(getRootView(), R.id.tv_title);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_author);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_origin_price);
            TextView textView4 = (TextView) ap.a(getRootView(), R.id.tv_discount_price);
            TextView textView5 = (TextView) ap.a(getRootView(), R.id.tv_action);
            TextView textView6 = (TextView) ap.a(getRootView(), R.id.tv_category);
            if (!(TextUtils.isEmpty(this.headerItem.c) || TextUtils.isEmpty(this.headerItem.i))) {
                LayoutParams layoutParams;
                if (this.headerItem.c.length() + this.headerItem.i.length() >= 11) {
                    layoutParams = (LayoutParams) textView.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.weight = 1.0f;
                    textView.setLayoutParams(layoutParams);
                } else {
                    layoutParams = (LayoutParams) textView.getLayoutParams();
                    layoutParams.width = -2;
                    textView.setLayoutParams(layoutParams);
                }
            }
            fillContent(textView, this.headerItem.i);
            fillContent(textView2, this.headerItem.a);
            fillContent(textView6, this.headerItem.c);
            fillContent(textView3, this.headerItem.m);
            textView3.getPaint().setFlags(16);
            fillContent(textView4, this.headerItem.n);
            fillContent(textView5, this.headerItem.h);
            if (this.headerItem.g) {
                textView5.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.localstore_textcolor_detail_orange_selector));
                textView5.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ AudioBookDetailHeaderCard a;

                    {
                        this.a = r1;
                    }

                    public void a(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("KEY_ACTION", "detail_2_openvip");
                        this.a.getEvnetListener().doFunction(bundle);
                    }
                });
                return;
            }
            textView5.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.text_color_c102));
        }
    }

    public JSONObject getBookInfo() {
        return this.mBookInfo;
    }

    private void fillContent(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
        } else {
            textView.setText(str);
        }
    }
}
