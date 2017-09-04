package com.qq.reader.weiboapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.qq.reader.view.af;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.auth.d;
import com.sina.weibo.sdk.auth.e;
import com.sina.weibo.sdk.share.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;

public class WeiboShareActivity extends Activity implements com.sina.weibo.sdk.share.a {
    int a = 0;
    c b;
    af c;
    boolean d = false;
    Bundle e;
    private b f;
    private int g = 1;
    private com.sina.weibo.sdk.auth.a.a h;
    private com.sina.weibo.sdk.auth.b i;
    private Context j;

    private class a implements d {
        final /* synthetic */ WeiboShareActivity a;

        private a(WeiboShareActivity weiboShareActivity) {
            this.a = weiboShareActivity;
        }

        public void a(final com.sina.weibo.sdk.auth.b bVar) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.a.i = bVar;
                    if (this.b.a.i.a()) {
                        com.sina.weibo.sdk.auth.a.a(this.b.a, this.b.a.i);
                        this.b.a.a("授权成功");
                    }
                    this.b.a.e();
                    this.b.a.d = true;
                }
            });
        }

        public void a() {
            this.a.a("取消关注");
            this.a.finish();
        }

        public void a(e eVar) {
            this.a.a("关注失败");
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = this;
        this.c = af.a(this, "", 0);
        this.e = getIntent().getExtras();
        this.h = new com.sina.weibo.sdk.auth.a.a(this);
        this.f = new b(this);
        this.b = com.sina.weibo.sdk.c.a((Context) this).a();
        this.i = com.sina.weibo.sdk.auth.a.a(this);
        if (this.d) {
            finish();
            return;
        }
        try {
            if (this.e != null) {
                a();
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        if (this.b == null || !this.b.d()) {
            a(this.j.getString(R.string.weibo_share_not_support_api_hint));
        } else if (this.i.a()) {
            e();
            this.d = true;
        } else {
            this.h.a(new a());
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.h != null) {
            this.h.a(i, i2, intent);
        }
    }

    private void e() {
        Bitmap bitmap;
        this.f.a();
        this.a = 1;
        String string = this.e.getString("text");
        String string2 = this.e.getString("key_share_type");
        Parcelable parcelable = this.e.getParcelable("bitmap");
        if (parcelable == null || !(parcelable instanceof Bitmap)) {
            bitmap = null;
        } else {
            bitmap = (Bitmap) parcelable;
        }
        String string3 = this.e.getString(SocialConstants.PARAM_URL);
        com.sina.weibo.sdk.api.a aVar = new com.sina.weibo.sdk.api.a();
        if ("share_image_type".equalsIgnoreCase(string2)) {
            aVar.a = b(string);
            aVar.b = a(BitmapFactory.decodeFile(string3));
        } else {
            if (!TextUtils.isEmpty(string)) {
                aVar.a = b(string + string3);
            }
            if (bitmap != null) {
                aVar.b = a(bitmap);
            }
        }
        this.f.a(aVar, this.g == 1);
    }

    protected void onNewIntent(Intent intent) {
        try {
            super.onNewIntent(intent);
            this.f.a(intent, (com.sina.weibo.sdk.share.a) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        a("分享成功");
        finish();
    }

    public void c() {
        a("取消分享");
        finish();
    }

    public void d() {
        a("分享失败");
        finish();
    }

    private void a(String str) {
        if (this.c != null) {
            this.c.a(str);
            this.c.a();
        }
    }

    private TextObject b(String str) {
        TextObject textObject = new TextObject();
        textObject.g = str;
        return textObject;
    }

    private ImageObject a(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.a(bitmap);
        return imageObject;
    }
}
