package com.qq.reader.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.GridView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.protocol.ProjectStateTask;
import com.qq.reader.common.readertask.protocol.QueryBookIntroTask;
import com.qq.reader.common.readertask.protocol.QueryMediaBookInfoTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.view.ak.a;
import com.qq.reader.weiboapi.WeiboShareActivity;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.Tencent;
import java.io.FileOutputStream;
import java.io.IOException;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShareDialog */
public class aj extends BaseDialog {
    public static Tencent b;
    private Intent a;
    protected String c;
    protected a d;
    private GridView e;
    private Activity i;
    private String j;
    private String k;
    private String l;
    private Bitmap m;
    private String n;
    private String o;
    private String p;
    private volatile int q;
    private int r;
    private b s;
    private c t;
    private boolean u;
    private final a v;

    public aj(Activity activity) {
        this.a = new Intent();
        this.c = null;
        this.d = null;
        this.u = false;
        this.v = new a(this, null);
        if (this.f == null) {
            this.a.setFlags(SigType.WLOGIN_LHSIG);
            super.a(activity, null, R.layout.sharedialog, 1, false);
            this.i = activity;
            LayoutParams attributes = this.f.getWindow().getAttributes();
            attributes.width = activity.getWindow().getAttributes().width;
            this.f.getWindow().setAttributes(attributes);
            this.e = (GridView) this.f.findViewById(R.id.grid);
            this.s = new b(this);
            this.e.setAdapter(this.s);
            this.e.setOnItemClickListener(new 1(this));
        }
        this.r = 10;
        try {
            b = Tencent.createInstance("100686853", this.i.getApplicationContext());
        } catch (Throwable th) {
            b = null;
        }
    }

    public aj(Activity activity, String str, String str2) {
        this(activity);
        this.j = str;
        this.k = str2;
        this.r = 10;
    }

    public aj(Activity activity, String str, String str2, String str3, int i) {
        this(activity);
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.r = i;
        if (this.j == null || this.j.length() <= 0) {
            this.o = "http://ireader.qq.com/android/common/down.html";
        }
        this.n = "http://wfqqreader.3g.qq.com/cover/72icon.png";
    }

    public aj(Activity activity, String str, String str2, String str3, String str4, String str5) {
        this(activity, str, str2, str3, str4, str5, 11);
    }

    public aj(Activity activity, String str, String str2, String str3, String str4, String str5, int i) {
        this(activity);
        this.r = i;
        this.o = str;
        this.k = str3;
        this.l = str4;
        this.n = str2;
        this.j = str5;
    }

    public aj(Activity activity, String str, int i) {
        this(activity);
        this.r = i;
        this.p = str;
    }

    private void b(int i) {
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
        if (this.s.getCount() == 6) {
            this.q = i;
        } else {
            this.q = i + 2;
        }
        switch (this.r) {
            case 10:
            case 12:
                if (this.q == 5) {
                    long j;
                    if (this.j == null || this.j.length() <= 0) {
                        j = 0;
                    } else {
                        j = Long.valueOf(this.j).longValue();
                    }
                    if (10 == this.r) {
                        com.qq.reader.cservice.b.a.a(this.i, this.k, b(this.j));
                    } else {
                        if (this.o == null || this.o.length() <= 0) {
                            if (j > 0) {
                                this.o = b(this.j);
                            } else {
                                this.o = "http://ireader.qq.com/android/common/down.html";
                            }
                        }
                        com.qq.reader.cservice.b.a.b(this.i, this.p, "#" + ReaderApplication.getApplicationImp().getString(R.string.app_name) + "#" + this.k + ":" + this.l + this.o);
                    }
                } else {
                    g();
                }
                if (j.k <= 0) {
                    j.k++;
                    return;
                }
                return;
            case 11:
            case 13:
            case 16:
                h();
                return;
            case 14:
            case 17:
                c(this.q);
                return;
            default:
                return;
        }
    }

    public void a(String str, String str2) {
        this.j = str;
        this.k = str2;
        this.r = 10;
    }

    public void a(boolean z) {
        this.u = z;
    }

    private String b(String str) {
        String str2;
        if (this.u) {
            str2 = e.bw + str + "&g_f=10593";
            this.n = ao.a(Long.parseLong(str), true, 180);
        } else {
            str2 = e.bv + str + "&g_f=10593";
        }
        if (!c.b()) {
            return str2;
        }
        com.qq.reader.common.login.b.a c = c.c();
        return str2 + "&uin=" + c.c() + GetVoteUserIconsTask.TIME + System.currentTimeMillis();
    }

    private void g() {
        if (this.j == null || this.j.length() <= 0) {
            this.m = null;
            c(this.q);
            return;
        }
        ReaderTask queryMediaBookInfoTask;
        if (this.u) {
            queryMediaBookInfoTask = new QueryMediaBookInfoTask(new 4(this), this.j);
        } else {
            queryMediaBookInfoTask = new QueryBookIntroTask(new 5(this), this.j);
        }
        this.t = new c(this.i);
        this.t.c(true);
        this.t.a("正在加载...");
        this.t.a(new 6(this, queryMediaBookInfoTask));
        if (!this.t.f()) {
            this.t.f_();
        }
        this.o = b(this.j);
        g.a().a(queryMediaBookInfoTask);
    }

    private void h() {
        this.t = new c(this.i);
        this.t.c(true);
        this.t.a("正在加载...");
        this.t.a(new 7(this));
        if (!this.t.f()) {
            this.t.f_();
        }
        ReaderTask readerDownloadTask = new ReaderDownloadTask(this.i, com.qq.reader.common.c.a.dc + ao.r(this.n), this.n);
        readerDownloadTask.setListener(new 8(this));
        g.a().a(readerDownloadTask);
    }

    private synchronized void c(int i) {
        this.v.sendEmptyMessageDelayed(100005, 300);
        String str;
        switch (i) {
            case 0:
                if (this.r == 17) {
                    a(this.p, (int) R.drawable.weixin_qrcode);
                    WXApiManager.getInstance(this.i).shareImageToWX(1, this.p);
                } else if (this.r == 14) {
                    WXApiManager.getInstance(this.i).shareImageToWX(1, this.p);
                } else {
                    if (this.c != null) {
                        WXApiManager.getInstance(this.i).shareWebPageToWXFriendCircle(this.c, this.m, this.l, this.o);
                    } else {
                        WXApiManager.getInstance(this.i).shareWebPageToWXFriendCircle(this.k + "：" + this.l, this.m, this.l, this.o);
                    }
                    if (this.r == 13) {
                        i.a("event_D113", null, this.i);
                    }
                }
                i.a("event_B37", null, this.i);
                i.a("event_A114", null, this.i);
                j.a(113, 0);
                break;
            case 1:
                if (this.r == 14 || this.r == 17) {
                    a(this.p, (int) R.drawable.weixin_qrcode);
                    WXApiManager.getInstance(this.i).shareImageToWX(0, this.p);
                } else {
                    WXApiManager.getInstance(this.i).shareWebPageToWXFriend(this.k, this.m, this.l, this.o);
                    if (this.r == 13) {
                        i.a("event_D112", null, this.i);
                    }
                }
                i.a("event_A119", null, this.i);
                i.a("event_B37", null, this.i);
                j.a(118, 0);
                break;
            case 2:
                if (b != null) {
                    if (this.r == 17) {
                        a(this.p, (int) R.drawable.qq_qrcode);
                        str = this.p;
                    } else if (this.r == 14) {
                        str = this.p;
                    } else {
                        str = this.n;
                    }
                    com.qq.reader.cservice.b.a.a.a(this.i, b, com.qq.reader.cservice.b.a.a.a(this.r, 1, this.k, this.l, this.o, str));
                }
                if (this.r == 13) {
                    i.a("event_D110", null, this.i);
                }
                i.a("event_A115", null, this.i);
                j.a(114, 0);
                break;
            case 3:
                if (b != null) {
                    if (this.r == 17) {
                        a(this.p, (int) R.drawable.qq_qrcode);
                        str = this.p;
                    } else if (this.r == 14) {
                        str = this.p;
                    } else {
                        str = this.n;
                    }
                    Bundle a = com.qq.reader.cservice.b.a.a.a(this.r, 2, this.k, this.l, this.o, str);
                    if (this.r == 14 || this.r == 17) {
                        com.qq.reader.cservice.b.a.a.c(this.i, b, a);
                    } else {
                        com.qq.reader.cservice.b.a.a.b(this.i, b, a);
                    }
                }
                if (this.r == 13) {
                    i.a("event_D111", null, this.i);
                }
                i.a("event_A116", null, this.i);
                j.a((int) com.tencent.qalsdk.base.a.cd, 0);
                break;
            case 4:
                String str2;
                Intent intent = new Intent(this.i, WeiboShareActivity.class);
                Bundle bundle = new Bundle();
                if (this.r == 17) {
                    a(this.p, (int) R.drawable.weibo_qrcode);
                    this.o = this.p;
                    bundle.putString("key_share_type", "share_image_type");
                    str2 = "分享图片";
                } else if (this.r == 14) {
                    this.o = this.p;
                    bundle.putString("key_share_type", "share_image_type");
                    str2 = "分享图片";
                } else {
                    String string;
                    str2 = this.i.getString(R.string.weibo_share_default_text_template);
                    switch (this.r) {
                        case 10:
                            string = this.i.getString(R.string.weibo_share_book_text_template);
                            break;
                        case 12:
                            string = this.i.getString(R.string.weibo_share_note_text_template);
                            break;
                        default:
                            string = str2;
                            break;
                    }
                    Object[] objArr = new Object[3];
                    objArr[0] = this.k;
                    if (this.l.length() > 40) {
                        str2 = this.l.substring(0, 39) + "...";
                    } else {
                        str2 = this.l;
                    }
                    objArr[1] = str2;
                    objArr[2] = this.i.getString(R.string.app_name);
                    str2 = String.format(string, objArr);
                }
                bundle.putString("text", str2);
                bundle.putParcelable("bitmap", this.m);
                bundle.putString(SocialConstants.PARAM_URL, this.o);
                intent.putExtras(bundle);
                this.i.startActivity(intent);
                i.a("event_A118", null, this.i);
                break;
            case 5:
                com.qq.reader.cservice.b.a.b(this.i, this.p, this.k + ":" + this.l + " " + this.o);
                break;
        }
        g.a().a(new ProjectStateTask(new 9(this), this.r, this.j));
        if (this.r == 16) {
            i.a("event_z26", null, ReaderApplication.getApplicationImp());
        }
    }

    public static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > 0 && width == height) {
            return bitmap;
        }
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        float f6;
        if (width < height) {
            f = (float) width;
            f2 = (float) height;
            f3 = (float) ((height - width) / 2);
            f4 = 0.0f + ((float) width);
            f5 = (float) height;
            width = height;
            i = height;
            f6 = f;
            f = f2;
            f2 = f3;
            f3 = 0.0f;
        } else {
            f3 = (float) ((width - height) / 2);
            f2 = (float) height;
            f4 = (float) width;
            f5 = ((float) height) + 0.0f;
            f6 = (float) width;
            i = width;
            f = f2;
            f2 = 0.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, width, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect((int) null, (int) null, (int) f6, (int) f);
        Rect rect2 = new Rect((int) f2, (int) f3, (int) f4, (int) f5);
        canvas.drawColor(-1);
        if (!bitmap.isRecycled()) {
            canvas.drawBitmap(bitmap, f2, f3, null);
        }
        return createBitmap;
    }

    private Bitmap a(byte[] bArr) {
        Bitmap createScaledBitmap;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        int width = decodeByteArray.getWidth();
        int height = decodeByteArray.getHeight();
        if (width * height > 8000 && this.q != 4) {
            double sqrt = Math.sqrt(8000.0d / ((double) (width * height)));
            createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, (int) (((double) width) * sqrt), (int) (((double) height) * sqrt), true);
            if (createScaledBitmap != decodeByteArray) {
                decodeByteArray.recycle();
            }
            decodeByteArray = createScaledBitmap;
        }
        createScaledBitmap = null;
        if (decodeByteArray != null) {
            createScaledBitmap = a(decodeByteArray);
            if (createScaledBitmap != decodeByteArray) {
                decodeByteArray.recycle();
            }
        }
        return createScaledBitmap;
    }

    private void a(ReaderNetTask readerNetTask, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.r != 12) {
                this.l = jSONObject.optString("intro");
                this.k = jSONObject.optString("title");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.v.sendEmptyMessage(100006);
        }
        this.n = ao.a(Long.valueOf(this.j).longValue(), true, 180);
        if (this.q == 1 || this.q == 0 || this.q == 4) {
            com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp()).b(this.n, new 10(this));
        } else if (!Thread.interrupted() && this.i != null) {
            this.i.runOnUiThread(new 11(this));
        }
    }

    private void b(ReaderNetTask readerNetTask, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.r != 12) {
                this.l = jSONObject.optString("intro");
                this.k = jSONObject.optString("title");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.v.sendEmptyMessage(100006);
        }
        this.n = ao.g(Long.valueOf(this.j).longValue());
        if (this.q == 1 || this.q == 0 || this.q == 4) {
            com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp()).b(this.n, new 2(this));
        } else if (!Thread.interrupted() && this.i != null) {
            this.i.runOnUiThread(new 3(this));
        }
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    private void a(String str, int i) {
        try {
            Bitmap copy = BitmapFactory.decodeFile(str).copy(Config.RGB_565, true);
            Bitmap decodeResource = BitmapFactory.decodeResource(e().getResources(), i);
            Canvas canvas = new Canvas(copy);
            decodeResource = Bitmap.createScaledBitmap(decodeResource, e().getResources().getDimensionPixelOffset(R.dimen.share_style_qrcode_width), e().getResources().getDimensionPixelOffset(R.dimen.share_style_qrcode_height), false);
            canvas.drawBitmap(decodeResource, (float) ((copy.getWidth() - decodeResource.getWidth()) - ao.a(7.0f)), (float) ((copy.getHeight() - decodeResource.getHeight()) - ao.a(26.0f)), new Paint());
            b(copy);
            if (!decodeResource.isRecycled()) {
                decodeResource.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Bitmap bitmap) {
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream;
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
                if (bitmap != null) {
                    bitmap.recycle();
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
                    if (bitmap != null) {
                        bitmap.recycle();
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
                    if (bitmap != null) {
                        bitmap.recycle();
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
            if (bitmap != null) {
                bitmap.recycle();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw th;
        }
    }
}
