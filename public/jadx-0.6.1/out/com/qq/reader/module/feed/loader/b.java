package com.qq.reader.module.feed.loader;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: FeedColdBootDataProvider */
public class b {
    public static int a() {
        int i = 2;
        Context applicationImp = ReaderApplication.getApplicationImp();
        if (!d.bE(applicationImp)) {
            return 0;
        }
        int bC = d.bC(applicationImp);
        if (bC > 2) {
            d.L(applicationImp, 2);
        } else {
            i = bC;
        }
        return 2 - i;
    }

    public static String b() {
        String str;
        InputStream open;
        Exception e;
        int i;
        Throwable th;
        int i2;
        InputStream inputStream = null;
        c.e("FeedColdBootDataProvider", "getColdBootData begin----");
        Context applicationImp = ReaderApplication.getApplicationImp();
        int bC = d.bC(applicationImp);
        if (bC < 2) {
            try {
                str = "coldbootfeed/feed";
                switch (d.aV(applicationImp)) {
                    case 0:
                    case 4:
                        str = str + "boynew";
                        break;
                    case 1:
                        str = str + "boy";
                        break;
                    case 2:
                        str = str + "girl";
                        break;
                    case 3:
                        str = str + "pub";
                        break;
                    case 5:
                        str = str + "girlnew";
                        break;
                }
                str = str + bC + ".o";
                c.e("FeedColdBootDataProvider", "fileName : " + str);
                open = applicationImp.getResources().getAssets().open(str);
            } catch (Exception e2) {
                e = e2;
                open = null;
                try {
                    e.printStackTrace();
                    i = bC + 1;
                    d.L(applicationImp, i);
                    if (i >= 2) {
                        d.I(applicationImp, false);
                    }
                    if (open != null) {
                        try {
                            open.close();
                            str = null;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            str = null;
                        }
                        c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
                        c.e("FeedColdBootDataProvider", "getColdBootData end----");
                        return str;
                    }
                    str = null;
                    c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
                    c.e("FeedColdBootDataProvider", "getColdBootData end----");
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = open;
                    i2 = bC + 1;
                    d.L(applicationImp, i2);
                    if (i2 >= 2) {
                        d.I(applicationImp, false);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                i2 = bC + 1;
                d.L(applicationImp, i2);
                if (i2 >= 2) {
                    d.I(applicationImp, false);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                str = new String(bArr, "UTF-8");
                int i3 = bC + 1;
                d.L(applicationImp, i3);
                if (i3 >= 2) {
                    d.I(applicationImp, false);
                }
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e42) {
                        e42.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                i = bC + 1;
                d.L(applicationImp, i);
                if (i >= 2) {
                    d.I(applicationImp, false);
                }
                if (open != null) {
                    open.close();
                    str = null;
                    c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
                    c.e("FeedColdBootDataProvider", "getColdBootData end----");
                    return str;
                }
                str = null;
                c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
                c.e("FeedColdBootDataProvider", "getColdBootData end----");
                return str;
            }
            c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
            c.e("FeedColdBootDataProvider", "getColdBootData end----");
            return str;
        }
        str = null;
        c.e("FeedColdBootDataProvider", "ColdBootData : " + str);
        c.e("FeedColdBootDataProvider", "getColdBootData end----");
        return str;
    }
}
