package com.qq.reader.liveshow.model.im.viewdata;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.View.OnClickListener;
import com.bumptech.glide.g;
import com.qq.reader.liveshow.a.b;
import com.qq.reader.liveshow.a.d;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.l;
import com.qq.reader.liveshow.model.im.message.SenderProfile;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.c;
import com.qq.reader.liveshow.utils.q;

/* compiled from: CommonMessageListItem */
public class a extends b {
    private SpannableString b;
    private String c;
    private String d;
    private int e;
    private boolean f = false;
    private int g;
    private c h;
    private String i;
    private long j;
    private GiftItem k;
    private String l;
    private int m;
    private String n;
    private String o = "";
    private String p = "";
    private int q = 0;
    private final String r = "-";

    /* compiled from: CommonMessageListItem */
    class a extends ReplacementSpan {
        final /* synthetic */ a a;
        private int b = 8;
        private int c = 0;
        private int d = 0;
        private int e;

        public a(a aVar, Context context, int i) {
            this.a = aVar;
            this.d = context.getResources().getColor(b.text_color_c104);
            this.e = (aVar.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_item_height) - aVar.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_tag_height)) / 2;
            switch (i) {
                case 0:
                    this.c = context.getResources().getColor(b.chat_list_assistant_bg);
                    this.b = aVar.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_assist_margin);
                    return;
                case 1:
                    this.c = context.getResources().getColor(b.chat_list_vip_bg);
                    this.b = aVar.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_vip_margin);
                    return;
                default:
                    return;
            }
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            canvas.save();
            canvas.translate(0.0f, (float) ((-this.a.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.text_size_class_1)) + this.e));
            paint.setTextSize(this.a.a.getResources().getDimension(com.qq.reader.liveshow.a.c.text_size_class_1));
            RectF rectF = new RectF(f, (float) this.e, (a(paint, charSequence, i, i2) + f) + ((float) (this.b * 2)), (float) (this.e + this.a.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_tag_height)));
            paint.setColor(this.c);
            canvas.drawRoundRect(rectF, (float) this.b, (float) this.b, paint);
            paint.setColor(this.d);
            canvas.drawText(charSequence, i, i2, f + ((float) this.b), (float) (this.e + this.a.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.text_size_class_1)), paint);
            canvas.restore();
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            return Math.round(paint.measureText(charSequence, i, i2));
        }

        private float a(Paint paint, CharSequence charSequence, int i, int i2) {
            return paint.measureText(charSequence, i, i2);
        }
    }

    public a(Context context) {
        super(context);
    }

    public void a(com.qq.reader.liveshow.model.im.message.a.b bVar) {
        SenderProfile b = bVar.b();
        this.e = bVar.e();
        this.c = b.getNickName();
        if (TextUtils.isEmpty(this.c)) {
            this.c = "";
        }
        this.d = b.getAvatar();
        this.g = b.getVipLevel();
        this.f = b.getPermissionsLevel() == 3;
        if (this.g > this.q) {
            this.c = " " + this.c;
        }
        this.i = b.getId();
        this.j = b.getAuthorId();
        this.n = bVar.c();
        switch (this.e) {
            case 0:
            case 1:
                this.c += ": ";
                this.m = this.a.getResources().getColor(b.text_color_c104);
                break;
            case 2:
            case 3:
                this.n = this.a.getString(h.enter_room);
                this.m = this.a.getResources().getColor(b.text_color_c601);
                break;
            case 4:
                this.k = bVar.f();
                this.l = "-x" + bVar.g();
                this.m = this.a.getResources().getColor(b.text_color_c601);
                this.n = this.a.getResources().getString(h.send_text, new Object[]{this.k.mName}) + this.l;
                break;
            case 5:
                this.n = this.a.getString(h.follow_host);
                this.m = this.a.getResources().getColor(b.text_color_c601);
                break;
            case 6:
                this.m = this.a.getResources().getColor(b.text_color_c401);
                this.b = new SpannableString(this.n);
                return;
            default:
                this.m = this.a.getResources().getColor(b.text_color_c104);
                break;
        }
        if (this.f) {
            this.o = this.a.getResources().getString(h.assist_text);
        }
        if (this.g > this.q) {
            this.p = this.a.getResources().getString(h.vip_text, new Object[]{Integer.valueOf(this.g)});
        }
        this.b = new SpannableString(this.o + this.p + this.c + this.n);
        if (this.f) {
            this.b.setSpan(new a(this, this.a, 0), 0, this.o.length(), 33);
        }
        if (this.g > this.q) {
            this.b.setSpan(new a(this, this.a, 1), this.o.length(), this.o.length() + this.p.length(), 33);
        }
        this.b.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(b.text_color_c801)), b(), b() + this.c.length(), 34);
        if (this.e == 4 && this.k != null) {
            this.h = (c) com.qq.reader.liveshow.model.b.a().get(Integer.valueOf(this.k.mId));
            int c = c();
            if (this.h != null) {
                this.b.setSpan(this.h, c, c + 1, 33);
            } else {
                Drawable drawable = this.a.getResources().getDrawable(d.default_gift);
                drawable.setBounds(0, 0, com.qq.reader.liveshow.model.b.a(this.a), com.qq.reader.liveshow.model.b.a(this.a));
                this.b.setSpan(new c(this.a, drawable, 1), c, c + 1, 33);
            }
        }
        this.c = b.getNickName();
    }

    private int b() {
        int i = 0;
        try {
            int length = this.o == null ? 0 : this.o.length();
            if (this.p != null) {
                i = this.p.length();
            }
            return i + length;
        } catch (Exception e) {
            SxbLog.c("CommonMessageListItem", e.toString());
            return 0;
        }
    }

    private int c() {
        int i = 0;
        try {
            int length = (this.c == null ? 0 : this.c.length()) + b();
            if (this.n != null) {
                i = this.n.indexOf("-");
            }
            return i + length;
        } catch (Exception e) {
            SxbLog.c("CommonMessageListItem", e.toString());
            return 0;
        }
    }

    public void a(final com.qq.reader.liveshow.utils.d dVar, int i) {
        SxbLog.e("attachView", this.b.toString());
        dVar.a.setTextColor(this.m);
        dVar.a.setText("");
        if (this.e != 4 || this.k == null) {
            dVar.a.setText(this.b);
        } else if (this.h != null) {
            dVar.a.setText(this.b);
        } else {
            g.b(this.a).a(this.k.mImgUrl).j().b().a(this.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_gift_height), this.a.getResources().getDimensionPixelOffset(com.qq.reader.liveshow.a.c.chat_list_gift_height)).b(new com.bumptech.glide.request.b.g<Bitmap>(this) {
                final /* synthetic */ a b;

                public void a(Bitmap bitmap, com.bumptech.glide.request.a.c<? super Bitmap> cVar) {
                    if (bitmap != null) {
                        this.b.h = new c(this.b.a, bitmap);
                        int a = this.b.c();
                        this.b.b.setSpan(this.b.h, a + 1, a + 2, 33);
                        dVar.a.setText(this.b.b);
                        com.qq.reader.liveshow.model.b.a().put(Integer.valueOf(this.b.k.mId), this.b.h);
                    }
                }
            });
        }
        if (this.e != 6) {
            dVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    q.a((Activity) view.getContext(), this.a.j, this.a.i, this.a.c, this.a.d, null, false);
                    l.a("event_Z4", null, this.a.a.getApplicationContext());
                }
            });
        } else {
            dVar.a.setOnClickListener(null);
        }
    }

    public int a() {
        return com.qq.reader.liveshow.a.g.item_chatmsg;
    }
}
