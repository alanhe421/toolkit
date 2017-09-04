package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.c.a;
import com.qq.reader.common.c.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class FeedCardSingleView extends RelativeLayout {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private ImageView H;
    private int a = 6;
    private int b;
    private int c;
    private long d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private String k;
    private String l;
    private String m;
    private String n;
    private long o;
    private long p;
    private JSONObject q;
    private String r;
    private String s;
    private String t;
    private String u;
    private int v;
    private boolean w = false;
    private String x;
    private ImageView y;
    private TextView z;

    public FeedCardSingleView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.concept_single_bookitem_layout, this, true);
        b();
    }

    public FeedCardSingleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.concept_single_bookitem_layout, this, true);
        b();
    }

    private void b() {
        this.y = (ImageView) findViewById(R.id.concept_cover_img);
        this.z = (TextView) findViewById(R.id.concept_author);
        this.A = (TextView) findViewById(R.id.concept_tag_subscript);
        this.B = (TextView) findViewById(R.id.concept_title);
        this.C = (TextView) findViewById(R.id.concept_content);
        this.D = (TextView) findViewById(R.id.concept_tag_1);
        this.E = (TextView) findViewById(R.id.concept_tag_2);
        this.F = (TextView) findViewById(R.id.concept_tag_3);
        this.G = (TextView) findViewById(R.id.concept_tag_4);
        this.H = (ImageView) findViewById(R.id.concept_cover_tag);
        if (b.a == 2) {
            this.D.setVisibility(8);
            this.E.setVisibility(8);
            this.F.setVisibility(8);
        }
    }

    public void a(JSONObject jSONObject, String str) {
        b(jSONObject, str);
        a();
    }

    public void a() {
        int i;
        CustomTailIconTextView customTailIconTextView;
        float parseFloat;
        boolean z;
        a(this.y, getCoverUrl(), null);
        CharSequence author = getAuthor();
        if (author.length() > this.a) {
            author = author.substring(0, this.a - 1) + "…";
        }
        this.z.setText(author);
        if (this.v != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.o || currentTimeMillis > this.p) {
                i = 1;
                if (i == 0 || TextUtils.isEmpty(this.e) || TextUtils.isEmpty(getIconDest())) {
                    this.A.setVisibility(8);
                } else {
                    this.A.setVisibility(0);
                    try {
                        ao.a(this.A, Color.parseColor(this.e));
                        this.A.setText(getIconDest());
                    } catch (Exception e) {
                        c.e("FeedSingleBookCard", e.getMessage());
                        this.A.setVisibility(8);
                    }
                }
                this.B.setText(getTitle());
                customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
                if (b.a != 2 || b.a == 3) {
                    this.B.setText(getDesc());
                    this.B.setSingleLine(false);
                    this.B.setMaxLines(2);
                    this.C.setVisibility(8);
                } else {
                    this.B.setText(getTitle());
                    if (TextUtils.isEmpty(getDesc())) {
                        this.C.setVisibility(8);
                        this.B.setMaxLines(2);
                        customTailIconTextView.setMaxlines(2);
                    } else {
                        this.B.setMaxLines(1);
                        customTailIconTextView.setMaxlines(1);
                        this.C.setVisibility(0);
                        this.C.setText(getDesc());
                    }
                }
                if (TextUtils.isEmpty(getCateL2Name())) {
                    this.D.setVisibility(0);
                    this.D.setText(getCateL2Name());
                } else {
                    this.D.setVisibility(8);
                }
                if (TextUtils.isEmpty(getCateTag()) || !TextUtils.isEmpty(getCateL3Name())) {
                    this.E.setVisibility(0);
                    if (TextUtils.isEmpty(getCateTag())) {
                        this.E.setText(getCateTag());
                    } else {
                        this.E.setText(getCateL3Name());
                    }
                } else {
                    this.E.setVisibility(8);
                }
                if (TextUtils.isEmpty(this.x)) {
                    try {
                        parseFloat = Float.parseFloat(this.x);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        parseFloat = 0.0f;
                    }
                    if (parseFloat <= 5.0f) {
                        this.G.setVisibility(0);
                        this.G.setText(this.x + "分");
                        z = true;
                    } else {
                        this.G.setVisibility(8);
                        z = false;
                    }
                } else {
                    this.G.setVisibility(8);
                    z = false;
                }
                if (this.b != 5 || this.d <= 0 || r0) {
                    this.F.setVisibility(8);
                } else {
                    this.F.setVisibility(0);
                    this.F.setText(s.countTransform(this.d) + "字");
                }
                if (this.w) {
                    this.H.setVisibility(0);
                    this.H.setImageResource(R.drawable.feed_corner_mark_hard_cover);
                } else if (this.b == 5 && this.c == 1) {
                    this.H.setVisibility(0);
                    this.H.setImageResource(R.drawable.feed_corner_mark_finish);
                } else {
                    this.H.setVisibility(8);
                }
                if (b.a == 2) {
                    this.D.setVisibility(8);
                    this.E.setVisibility(8);
                    this.F.setVisibility(8);
                }
            }
        }
        z = false;
        if (i == 0) {
        }
        this.A.setVisibility(8);
        this.B.setText(getTitle());
        customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
        if (b.a != 2) {
        }
        this.B.setText(getDesc());
        this.B.setSingleLine(false);
        this.B.setMaxLines(2);
        this.C.setVisibility(8);
        if (TextUtils.isEmpty(getCateL2Name())) {
            this.D.setVisibility(0);
            this.D.setText(getCateL2Name());
        } else {
            this.D.setVisibility(8);
        }
        if (TextUtils.isEmpty(getCateTag())) {
        }
        this.E.setVisibility(0);
        if (TextUtils.isEmpty(getCateTag())) {
            this.E.setText(getCateL3Name());
        } else {
            this.E.setText(getCateTag());
        }
        if (TextUtils.isEmpty(this.x)) {
            parseFloat = Float.parseFloat(this.x);
            if (parseFloat <= 5.0f) {
                this.G.setVisibility(8);
                z = false;
            } else {
                this.G.setVisibility(0);
                this.G.setText(this.x + "分");
                z = true;
            }
        } else {
            this.G.setVisibility(8);
            z = false;
        }
        if (this.b != 5) {
        }
        this.F.setVisibility(8);
        if (this.w) {
            this.H.setVisibility(0);
            this.H.setImageResource(R.drawable.feed_corner_mark_hard_cover);
        } else {
            if (this.b == 5) {
            }
            this.H.setVisibility(8);
        }
        if (b.a == 2) {
            this.D.setVisibility(8);
            this.E.setVisibility(8);
            this.F.setVisibility(8);
        }
    }

    public boolean b(JSONObject jSONObject, String str) {
        this.b = jSONObject.optInt("auth");
        this.c = jSONObject.optInt("finished");
        this.d = jSONObject.optLong("wordcount");
        this.e = jSONObject.optString("iconColor");
        this.u = jSONObject.optString("showTitle");
        this.f = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.g = jSONObject.optString("catetag");
        this.h = jSONObject.optString("categoryname ");
        this.i = jSONObject.optString("author");
        this.j = jSONObject.optInt("cardicon");
        this.k = jSONObject.optString("cover");
        this.l = jSONObject.optString("bid");
        this.m = jSONObject.optString("catel2name");
        this.n = jSONObject.optString("catel3name");
        this.o = jSONObject.optLong("lmstarttime");
        this.p = jSONObject.optLong("lmendtime");
        this.t = jSONObject.optString("icondesc");
        this.q = jSONObject.optJSONObject(s.STATPARAM_KEY);
        this.v = jSONObject.optInt("lftag");
        if (this.q != null) {
            this.r = this.q.optString("tags");
            this.s = this.q.optString("alg_info");
        }
        this.x = jSONObject.optString("score");
        if (this.b == 5) {
            if (a.bZ < 2.0f) {
                this.a = 10;
            } else {
                this.a = 14;
            }
        } else if (a.bZ < 2.0f) {
            this.a = 6;
        } else {
            this.a = 8;
        }
        if (!TextUtils.isEmpty(this.x)) {
            this.a -= 3;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("dlfile");
        if (!(optJSONObject == null || TextUtils.isEmpty(optJSONObject.optString("qteb")))) {
            this.w = true;
        }
        return true;
    }

    public String getDesc() {
        return this.f;
    }

    public String getCateTag() {
        return this.g;
    }

    public String getCategoryName() {
        return this.h;
    }

    public String getAuthor() {
        return this.i;
    }

    public int getCardicon() {
        return this.j;
    }

    public String getBookid() {
        return this.l;
    }

    public String getCoverUrl() {
        if (this.k == null || this.k.trim().equalsIgnoreCase("")) {
            this.k = ao.g(Long.valueOf(this.l).longValue());
        }
        return this.k;
    }

    public String getCateL2Name() {
        return this.m;
    }

    public String getCateL3Name() {
        return this.n;
    }

    public String getIconDest() {
        return this.t;
    }

    public String getTitle() {
        return this.u;
    }

    protected void a(ImageView imageView, String str, OnClickListener onClickListener) {
        com.qq.reader.common.imageloader.c.a(getContext()).a(str, imageView, com.qq.reader.common.imageloader.a.a().j());
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setTopDividerVisibility(boolean z) {
        View a = ap.a(getRootView(), R.id.concept_bookitem_divider);
        if (a == null) {
            return;
        }
        if (z) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
    }
}
