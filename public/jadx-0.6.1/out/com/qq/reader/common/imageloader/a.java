package com.qq.reader.common.imageloader;

import android.text.TextUtils;
import android.widget.ImageView.ScaleType;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* compiled from: GlideOptionUtil */
public class a {
    private static a a;
    private b b;
    private b c;
    private b d;
    private b e;
    private b f;
    private b g;
    private b h;
    private b i;
    private b j;
    private b k;
    private b l;
    private b m;
    private b n;
    private b o;
    private b p;

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public synchronized b b() {
        if (this.d == null) {
            this.d = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.profile_default_small_avator).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
        }
        return this.d;
    }

    public synchronized b c() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.author_head_default).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
    }

    public synchronized b d() {
        if (this.e == null) {
            this.e = new com.qq.reader.common.imageloader.b.a().a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
        }
        return this.e;
    }

    public synchronized b e() {
        if (this.k == null) {
            this.k = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.localstore_cover_bigavatar_default).a((int) R.drawable.author_headicon_default).a(b.a).a();
        }
        return this.k;
    }

    public synchronized b f() {
        if (this.i == null) {
            this.i = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.author_headicon_default).a((int) R.drawable.author_head_default).a(b.a).a();
        }
        return this.i;
    }

    public synchronized b g() {
        if (this.j == null) {
            this.j = new com.qq.reader.common.imageloader.b.a().a(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.headerpage_conver_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.headerpage_conver_height)).a(ScaleType.CENTER_CROP).a();
        }
        return this.j;
    }

    public synchronized b h() {
        if (this.h == null) {
            this.h = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.my_message_default_avator).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
        }
        return this.h;
    }

    public synchronized b i() {
        if (this.g == null) {
            this.g = new com.qq.reader.common.imageloader.b.a().a(b.a).a();
        }
        return this.g;
    }

    public synchronized b j() {
        if (this.b == null) {
            this.b = new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a();
        }
        return this.b;
    }

    public synchronized b k() {
        if (this.p == null) {
            this.p = new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a(new RoundedCornersTransformation(ReaderApplication.getApplicationImp(), ao.a(3.0f), 0)).a();
        }
        return this.p;
    }

    public synchronized b l() {
        if (this.m == null) {
            this.m = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.redpacket_square_book_default).a();
        }
        return this.m;
    }

    public synchronized b m() {
        if (this.f == null) {
            this.f = new com.qq.reader.common.imageloader.b.a().b((int) R.color.activate_img_loading).a();
        }
        return this.f;
    }

    public synchronized b n() {
        if (this.c == null) {
            this.c = new com.qq.reader.common.imageloader.b.a().b((int) R.color.book_store_default_cover_color).a((int) R.color.book_store_default_cover_color).a();
        }
        return this.c;
    }

    public synchronized b o() {
        if (this.l == null) {
            this.l = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.author_page_default_icon).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
        }
        return this.l;
    }

    public synchronized b p() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.user_center_default_user_icon).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
    }

    public synchronized b q() {
        if (this.o == null) {
            this.o = new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.localstore_cover_bigavatar_default).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
        }
        return this.o;
    }

    public synchronized b r() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.game_loading).a(new RoundedCornersTransformation(ReaderApplication.getApplicationImp(), ao.a(7.0f), 0)).a();
    }

    public synchronized b s() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.vote_default_icon).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
    }

    public synchronized b a(int i) {
        return new com.qq.reader.common.imageloader.b.a().b(i).a();
    }

    public synchronized b a(int i, int i2, int i3) {
        return new com.qq.reader.common.imageloader.b.a().b(i).a(i2, i3).a();
    }

    public synchronized b b(int i) {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a(i).a(Integer.MIN_VALUE, Integer.MIN_VALUE).a();
    }

    public synchronized b a(int i, int i2) {
        return new com.qq.reader.common.imageloader.b.a().b(i).a(i2).a();
    }

    public synchronized b b(int i, int i2) {
        return new com.qq.reader.common.imageloader.b.a().a(i, i2).a();
    }

    public synchronized b a(String str) {
        b e;
        if (TextUtils.isEmpty(str)) {
            e = e();
        } else {
            e = f();
        }
        return e;
    }

    public synchronized b c(int i, int i2) {
        if (this.n == null) {
            this.n = new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a(i2).a(Integer.MIN_VALUE, Integer.MIN_VALUE).a(new com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp(), i)).a();
        }
        return this.n;
    }

    public synchronized b c(int i) {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a(Integer.MIN_VALUE, Integer.MIN_VALUE).a(new com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp(), i)).a();
    }

    public synchronized b d(int i, int i2) {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a(i, i2).a();
    }

    public synchronized b t() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.detail_default_author_head).a(b.a).a(new jp.wasabeef.glide.transformations.a(ReaderApplication.getApplicationImp())).a();
    }

    public synchronized b b(String str) {
        return new com.qq.reader.common.imageloader.b.a().a(b.a).a(new com.qq.reader.common.imageloader.b.a(ReaderApplication.getApplicationImp(), str)).a();
    }

    public synchronized b u() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.gift_32_b).a();
    }

    public synchronized b v() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.drawable.default_sign_free_book_cover).a();
    }

    public synchronized b w() {
        return new com.qq.reader.common.imageloader.b.a().b((int) R.color.localstore_img_loading).a((int) R.color.book_store_default_cover_color).a();
    }
}
