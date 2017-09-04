package com.qq.reader.wxapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.af;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class WXApiManager {
    public static String a;
    public static String b;
    private static IWXAPI d = null;
    private static WXApiManager g;
    private static Boolean i = Boolean.valueOf(false);
    af c;
    private Req e = null;
    private Resp f = null;
    private Context h;

    static {
        a = null;
        b = null;
        a = "wxdc7f47c00c8f2396";
        b = "7b036609c135ad01390321a8ac27bf3f";
    }

    private WXApiManager(Context context) {
        this.h = context;
        this.c = af.a(context, (CharSequence) "", 0);
    }

    public static WXApiManager getInstance(Context context) {
        if (g == null) {
            g = new WXApiManager(context);
        }
        return g;
    }

    public IWXAPI getWXAPIInterface() {
        if (d == null) {
            d = WXAPIFactory.createWXAPI(this.h, a);
        }
        return d;
    }

    public void justRegisterWXNoBroadcast() {
        if (!i.booleanValue()) {
            try {
                IWXAPI wXAPIInterface = getWXAPIInterface();
                if (wXAPIInterface.isWXAppInstalled() && !i.booleanValue() && wXAPIInterface.isWXAppSupportAPI() && wXAPIInterface.registerApp(a)) {
                    i = Boolean.valueOf(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerWX() {
        IWXAPI wXAPIInterface = getWXAPIInterface();
        Intent intent = new Intent();
        try {
            if (wXAPIInterface.isWXAppInstalled() && !i.booleanValue()) {
                if (!wXAPIInterface.isWXAppSupportAPI()) {
                    intent.setAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_SDK_NOT_MATCH");
                } else if (wXAPIInterface.registerApp(a)) {
                    i = Boolean.valueOf(true);
                } else {
                    intent.setAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_FAILED");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wXAPIInterface.isWXAppInstalled()) {
            intent.setAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_SUCCESS");
        } else {
            intent.setAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_NOT_INSTALLED");
        }
        this.h.sendBroadcast(intent);
    }

    public void unRegisterWX() {
        getWXAPIInterface().unregisterApp();
        i = Boolean.valueOf(false);
    }

    private String a(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
    }

    private void a(String str, int i) {
        if (str != null && str.length() != 0) {
            IMediaObject wXTextObject = new WXTextObject();
            wXTextObject.text = str;
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.mediaObject = wXTextObject;
            wXMediaMessage.description = str;
            BaseReq req = new Req();
            req.transaction = a("reader");
            req.message = wXMediaMessage;
            req.scene = i;
            getWXAPIInterface().sendReq(req);
        }
    }

    public void sendTxtToSession(String str) {
        a(str, 0);
    }

    public void sendTxtToTimeline(String str) {
        a(str, 1);
    }

    public void sendBookToWX(Context context, Mark mark, String str) {
        if (mark.getType() == 1) {
            if (mark.getBookName().endsWith(".teb")) {
                a(mark, str, ao.a((LocalMark) mark));
            } else {
                a(mark, str);
            }
        } else if (mark.getType() == 4) {
            a(mark, str, 0);
        }
    }

    private long a(File file) {
        Throwable th;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2 = null;
        try {
            if (file.exists()) {
                FileInputStream fileInputStream3 = new FileInputStream(file);
                try {
                    long available = (long) fileInputStream3.available();
                    fileInputStream3.close();
                    FileInputStream fileInputStream4 = null;
                    if (null == null) {
                        return available;
                    }
                    try {
                        fileInputStream4.close();
                        return available;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return available;
                    }
                } catch (Exception e2) {
                    fileInputStream2 = fileInputStream3;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            if (null != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
            return 0;
        } catch (Exception e5) {
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    private void b(String str) {
        if (this.c != null) {
            this.c.a((CharSequence) str);
            this.c.a();
        }
    }

    private String a(Mark mark) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("device", "android");
            jSONObject.put("name", mark.getBookName());
            jSONObject.put("author", mark.getAuthor());
            jSONObject.put("type", WXShareTypeEnum.EBookWeixin_1.ordinal());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void a(Mark mark, String str) {
        String id = mark.getId();
        WXAppExtendObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = id;
        wXAppExtendObject.extInfo = a(mark);
        long a = a(new File(wXAppExtendObject.filePath));
        if (a == 0) {
            b("获取文件错误");
        } else if (a < 0 || a >= 10485760) {
            b("分享内容大小不能超过10MB");
        } else {
            new Thread(new 1(this, mark, str, wXAppExtendObject)).start();
        }
    }

    private void a(Mark mark, String str, long j) {
        String str2 = "";
        if (j != 0) {
            str2 = String.valueOf(j);
        } else {
            str2 = mark.getId();
        }
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = e.aA + str2;
        ao.a(new StringBuffer(mark.getBookName()));
        File file = new File(mark.getImagePath());
        new Thread(new 2(this, mark, str, wXWebpageObject, mark.getAuthor())).start();
    }

    private Bitmap a(Bitmap bitmap, int i, int i2, boolean z) {
        Options options = new Options();
        try {
            Bitmap bitmap2;
            int height;
            int i3;
            options.inJustDecodeBounds = true;
            if (bitmap != null) {
                bitmap.recycle();
                bitmap2 = null;
            } else {
                bitmap2 = bitmap;
            }
            double height2 = (((double) bitmap2.getHeight()) * 1.0d) / ((double) i);
            double width = (((double) bitmap2.getWidth()) * 1.0d) / ((double) i2);
            double d = z ? height2 > width ? width : height2 : height2 < width ? width : height2;
            options.inSampleSize = (int) d;
            if (options.inSampleSize <= 1) {
                options.inSampleSize = 1;
            }
            while ((bitmap2.getHeight() * bitmap2.getWidth()) / options.inSampleSize > 2764800) {
                options.inSampleSize++;
            }
            if (z) {
                if (height2 > width) {
                    height = (int) (((((double) i2) * 1.0d) * ((double) bitmap2.getHeight())) / ((double) bitmap2.getWidth()));
                    i3 = i2;
                } else {
                    i3 = (int) (((((double) i) * 1.0d) * ((double) bitmap2.getWidth())) / ((double) bitmap2.getHeight()));
                    height = i;
                }
            } else if (height2 < width) {
                height = (int) (((((double) i2) * 1.0d) * ((double) bitmap2.getHeight())) / ((double) bitmap2.getWidth()));
                i3 = i2;
            } else {
                i3 = (int) (((((double) i) * 1.0d) * ((double) bitmap2.getWidth())) / ((double) bitmap2.getHeight()));
                height = i;
            }
            options.inJustDecodeBounds = false;
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i3, height, true);
            if (createScaledBitmap == null) {
                createScaledBitmap = bitmap2;
            }
            if (z) {
                bitmap2 = Bitmap.createBitmap(createScaledBitmap, (createScaledBitmap.getWidth() - i2) >> 1, (createScaledBitmap.getHeight() - i) >> 1, i2, i);
                if (bitmap2 == null) {
                    return createScaledBitmap;
                }
                createScaledBitmap.recycle();
            } else {
                bitmap2 = createScaledBitmap;
            }
            return bitmap2;
        } catch (OutOfMemoryError e) {
            Log.e("WXApiManager", "decode bitmap failed: " + e.getMessage());
            return null;
        }
    }

    public boolean isWXinstalled() {
        return getWXAPIInterface().isWXAppInstalled();
    }

    public boolean isWXsupportApi() {
        return getWXAPIInterface().isWXAppSupportAPI();
    }

    public void shareWebPageToWXFriendCircle(String str, Bitmap bitmap, String str2, String str3) {
        shareWebPage(1, str, bitmap, str2, str3, 1);
    }

    public void shareWebPageToWXFriend(String str, Bitmap bitmap, String str2, String str3) {
        shareWebPage(0, str, bitmap, str2, str3, 0);
    }

    public void shareWebPage(int i, String str, Bitmap bitmap, String str2, String str3, int i2) {
        WXMediaMessage wXMediaMessage = new WXMediaMessage(new WXWebpageObject(str3));
        wXMediaMessage.description = str2;
        wXMediaMessage.title = str;
        wXMediaMessage.setThumbImage(bitmap);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("share_type", i);
            jSONObject.put("share_targeturl", str3);
        } catch (Exception e) {
            c.e("WXApiManager", e.getMessage());
        }
        BaseReq req = new Req();
        req.transaction = jSONObject.toString();
        req.message = wXMediaMessage;
        req.scene = i2;
        getWXAPIInterface().sendReq(req);
    }

    public void shareImageToWX(int i, String str) {
        IMediaObject wXImageObject = new WXImageObject();
        wXImageObject.setImagePath(str);
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXImageObject);
        Bitmap a = ao.a(str, 300, 300, false);
        if (a.getByteCount() / 1024 > 37) {
            byte[] a2 = ao.a(a, 37.0f);
            a = BitmapFactory.decodeByteArray(a2, 0, a2.length);
        }
        wXMediaMessage.setThumbImage(a);
        BaseReq req = new Req();
        req.transaction = a(SocialConstants.PARAM_IMG_URL);
        req.message = wXMediaMessage;
        req.scene = i;
        getWXAPIInterface().sendReq(req);
    }
}
