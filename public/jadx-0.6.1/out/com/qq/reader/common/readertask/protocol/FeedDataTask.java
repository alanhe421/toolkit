package com.qq.reader.common.readertask.protocol;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.loader.h;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

public class FeedDataTask extends ReaderProtocolJSONTask {
    public static final String FEED_EXINFO = "&needexinfo=1";
    public static final String MS_END_SHOWTIME = "endShowTime=";
    public static final String MS_PREFENRENCE = "&preference=1";
    public static final String MS_REGION_SHOWTIME = "periods=";
    public static final String MS_SEX = "&sex=";
    public static final String MS_START_SHOWTIME = "startShowTime=";
    public static final String MS_TYPE = "&type=";
    public static final int MS_TYPE_BOOKINFO = 1;
    public static final String TEST = "&realdata=1";
    private e mFeedPackage;

    public FeedDataTask(e eVar) {
        this.mFeedPackage = eVar;
        buildUrl(eVar);
    }

    private void buildUrl(e eVar) {
        if (eVar.a()) {
            this.mUrl = com.qq.reader.appconfig.e.bS + MS_REGION_SHOWTIME + eVar.d();
        } else {
            String f = eVar.f();
            Object g = eVar.g();
            if (TextUtils.isEmpty(f)) {
                this.mUrl = com.qq.reader.appconfig.e.bS + MS_REGION_SHOWTIME + g;
            } else if (TextUtils.isEmpty(g)) {
                this.mUrl = com.qq.reader.appconfig.e.bS + MS_REGION_SHOWTIME + f;
            } else {
                this.mUrl = com.qq.reader.appconfig.e.bS + MS_START_SHOWTIME + f + "&" + MS_END_SHOWTIME + g;
            }
            c.a(SocialConstants.PARAM_SEND_MSG, this.mUrl.toString());
        }
        this.mUrl += TEST;
        this.mUrl += MS_SEX + d.aU(ReaderApplication.getApplicationImp());
        if (b.g == 1) {
            this.mUrl += MS_PREFENRENCE;
        }
        if (b.i == 1) {
            this.mUrl += FEED_EXINFO;
        }
        Object m = eVar.m();
        if (TextUtils.isEmpty(m)) {
            this.mUrl += "&case=A";
        } else if (m.equals("A")) {
            this.mUrl += "&case=A";
        } else {
            this.mUrl += "&case=B";
        }
        c.a(SocialConstants.PARAM_SEND_MSG, "url " + this.mUrl);
    }

    public HashMap<String, String> getBasicHeader() {
        c.a(SocialConstants.PARAM_SEND_MSG, this.mHeaders.toString());
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    private String getUrl(String str, String str2) {
        if (str2 == null) {
            return com.qq.reader.appconfig.e.bS;
        }
        return com.qq.reader.appconfig.e.bS + str + str2;
    }

    public void run() {
        int a = com.qq.reader.module.feed.loader.b.a();
        if (a > 0) {
            String b = com.qq.reader.module.feed.loader.b.b();
            if (!TextUtils.isEmpty(b)) {
                if (b.indexOf("9.1.1") != -1) {
                    if (com.qq.reader.common.a.b.a() == 0) {
                        b = b.replaceAll("9.1.1", "9.1.2");
                    } else {
                        b = b.replaceAll("9.1.1", "9.1.3");
                    }
                }
                if (a == 2) {
                    this.mFeedPackage.a(h.a());
                } else if (this.mFeedPackage.j() == 0) {
                    this.mFeedPackage.a(h.c(this.mFeedPackage.g()));
                } else if (this.mFeedPackage.j() == 1) {
                    this.mFeedPackage.a(true);
                }
                this.mFeedPackage.b(true);
                if (this.mListener != null) {
                    this.mListener.onConnectionRecieveData(this, b, (long) b.length());
                    return;
                }
                return;
            } else if (this.mListener != null) {
                this.mListener.onConnectionError(this, new Exception("read cold boot data error-->"));
                return;
            } else {
                return;
            }
        }
        super.run();
    }
}
