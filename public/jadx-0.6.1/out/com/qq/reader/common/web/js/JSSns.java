package com.qq.reader.common.web.js;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.module.feed.a;
import com.qq.reader.view.aj;
import com.qq.reader.view.ak;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class JSSns extends b {
    private Activity a;
    private a b;

    public JSSns(Activity activity) {
        this.a = activity;
    }

    public JSSns(Activity activity, a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    public void shareBook(String str, String str2) {
        new aj(this.a, str, str2).f_();
    }

    public void closePage() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public void sharePage(String str, String str2, String str3, String str4, String str5) {
        new aj(this.a, str, str2, str3, str4, str5).f_();
    }

    private void a(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap copy = bitmap.copy(Config.RGB_565, true);
        new Canvas(copy).drawBitmap(bitmap2, 0.0f, 0.0f, new Paint());
        a(copy);
        new aj(this.a, ak.a, 17).f_();
        ((ReaderBaseActivity) this.a).progressCancel();
    }

    public void cacheImage(String str) {
        c.a(ReaderApplication.getApplicationImp()).c(str);
    }

    public void shareStyleImage(final String str, final String str2) {
        ((ReaderBaseActivity) this.a).showPorgress("正在生成图片");
        new Thread(new Runnable(this) {
            final /* synthetic */ JSSns c;

            public void run() {
                final Bitmap a = c.a(this.c.a).a(str);
                final Bitmap a2 = c.a(this.c.a).a(str2);
                if (this.c.a != null && !this.c.a.isFinishing() && a != null && a2 != null) {
                    this.c.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void run() {
                            this.c.c.a(a, a2);
                        }
                    });
                }
            }
        }).start();
    }

    private void a(Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        try {
            fileOutputStream = new FileOutputStream(ak.a);
            try {
                fileOutputStream.write(ao.a(bitmap, 1024.0f));
                System.out.println("saveBmp is here");
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public void shareLuckyMoney(String str, String str2, String str3, String str4) {
        int indexOf = str.indexOf("rid");
        String substring = str.substring(indexOf + 4, str.length());
        if (substring.contains("%")) {
            aj ajVar = new aj(this.a, str, str2, str3, str4, null, 13);
            ajVar.a(str3);
            ajVar.f_();
            return;
        }
        String substring2 = str.substring(0, indexOf + 4);
        String str5 = "";
        try {
            str5 = URLEncoder.encode(substring, "utf-8");
        } catch (Exception e) {
        }
        ajVar = new aj(this.a, substring2 + str5, str2, str3, str4, null, 13);
        ajVar.a(str3);
        ajVar.f_();
    }
}
