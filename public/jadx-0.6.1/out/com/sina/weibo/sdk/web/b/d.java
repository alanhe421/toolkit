package com.sina.weibo.sdk.web.b;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.a;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.b.c;
import com.sina.weibo.sdk.b.k;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.e;
import com.sina.weibo.sdk.net.f;
import com.sina.weibo.sdk.web.WebRequestType;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: ShareWebViewRequestParam */
public class d extends b {
    private a b;
    private String c;
    private String d;
    private byte[] e;
    private String f;
    private String g;
    private String h;

    public d(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, i, str2, str3, context);
    }

    public boolean a() {
        if (this.e == null || this.e.length <= 0) {
            return super.a();
        }
        return true;
    }

    public void a(final b.a aVar) {
        super.a(aVar);
        f fVar = new f(c().d().a());
        fVar.a(SocialConstants.PARAM_IMG_URL, new String(this.e));
        new com.sina.weibo.sdk.net.a(this.a).a("http://service.weibo.com/share/mobilesdk_uppic.php", fVar, Constants.HTTP_POST, new e(this) {
            final /* synthetic */ d b;

            public void a(WeiboException weiboException) {
                if (aVar != null) {
                    aVar.b("upload pic fail");
                }
            }

            public void a(String str) {
                com.sina.weibo.sdk.web.a a = com.sina.weibo.sdk.web.a.a(str);
                if (a != null && a.a() == 1 && !TextUtils.isEmpty(a.b())) {
                    this.b.c = a.b();
                    if (aVar != null) {
                        aVar.a(this.b.c);
                    }
                } else if (aVar != null) {
                    aVar.b("upload pic fail");
                }
            }
        });
    }

    protected void a(Bundle bundle) {
        if (this.b != null) {
            this.b.a(bundle);
        }
        bundle.putString(com.tencent.android.tpush.common.Constants.FLAG_TOKEN, this.f);
        bundle.putString(com.tencent.android.tpush.common.Constants.FLAG_PACKAGE_NAME, this.g);
        bundle.putString("hashKey", this.h);
    }

    protected void b(Bundle bundle) {
        this.b = new a();
        this.b.b(bundle);
        this.f = bundle.getString(com.tencent.android.tpush.common.Constants.FLAG_TOKEN);
        this.g = bundle.getString(com.tencent.android.tpush.common.Constants.FLAG_PACKAGE_NAME);
        this.h = bundle.getString("hashKey");
        d();
    }

    public String b() {
        String a = c().d().a();
        Builder buildUpon = Uri.parse("http://service.weibo.com/share/mobilesdk.php").buildUpon();
        buildUpon.appendQueryParameter("title", this.d);
        buildUpon.appendQueryParameter(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "0031405000");
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter(SocialConstants.PARAM_SOURCE, a);
        }
        if (!TextUtils.isEmpty(this.f)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.f);
        }
        Object b = k.b(this.a, a);
        if (!TextUtils.isEmpty(b)) {
            buildUpon.appendQueryParameter("aid", b);
        }
        if (!TextUtils.isEmpty(this.g)) {
            buildUpon.appendQueryParameter("packagename", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            buildUpon.appendQueryParameter("key_hash", this.h);
        }
        if (!TextUtils.isEmpty(this.c)) {
            buildUpon.appendQueryParameter("picinfo", this.c);
        }
        buildUpon.appendQueryParameter("luicode", "10000360");
        buildUpon.appendQueryParameter("lfid", "OP_" + a);
        return buildUpon.build().toString();
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.h = str;
    }

    public void c(String str) {
        this.g = str;
    }

    private void d() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b.a instanceof TextObject) {
            stringBuilder.append(this.b.a.g);
        }
        if (this.b.b instanceof ImageObject) {
            ImageObject imageObject = this.b.b;
            a(imageObject.h, imageObject.g);
        }
        this.d = stringBuilder.toString();
    }

    private void a(String str, byte[] bArr) {
        Throwable th;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.canRead() && file.length() > 0) {
                    byte[] bArr2 = new byte[((int) file.length())];
                    FileInputStream fileInputStream = null;
                    FileInputStream fileInputStream2;
                    try {
                        fileInputStream2 = new FileInputStream(file);
                        try {
                            fileInputStream2.read(bArr2);
                            this.e = c.b(bArr2);
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                    return;
                                } catch (Exception e) {
                                    return;
                                }
                            }
                            return;
                        } catch (IOException e2) {
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (bArr != null) {
                            }
                            return;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            fileInputStream = fileInputStream2;
                            th = th3;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (bArr != null) {
                            return;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (SecurityException e6) {
        }
        if (bArr != null && bArr.length > 0) {
            this.e = c.b(bArr);
        }
    }
}
