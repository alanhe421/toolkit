package com.qq.reader.liveshow.utils;

import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* compiled from: ServerUrl */
public class l {
    private static String a = null;
    private static String b = null;
    private static String c = null;

    /* compiled from: ServerUrl */
    public static class a {
        public static String a(String str, String str2, String str3, String str4) {
            return "dialogshare?encode_pageurl=" + l.a(str) + "&" + "encode_picurl" + "=" + l.a(str2) + "&" + "encode_summery" + "=" + l.a(str3) + "&" + "encode_title" + "=" + l.a(str4);
        }

        public static String a(int i, String str) {
            return "recharge?value=" + i + "&" + "type" + "=" + str;
        }
    }

    static void a() {
        if (e.a) {
            a = "https://zbtest.tingbook.com/";
            b = "https://ptcommon.tingbook.com/";
            c = "http://solomotest4.3g.qq.com/book_res/reader/common/common/";
            return;
        }
        a = "https://zhibo.tingbook.com/";
        b = "https://zb.tingbook.com/";
        c = "http://yuedu.tingbook.com/common/common/";
    }

    public static String b() {
        d();
        return c + "liveRank.html";
    }

    private static void d() {
        if (a == null || b == null || c == null) {
            a = "https://zhibo.tingbook.com/";
            b = "https://zb.tingbook.com/";
            c = "http://yuedu.tingbook.com/common/common/";
        }
    }

    public static String a(int i, String str, int i2) {
        d();
        String str2 = a + "v" + "1_0" + "/live/user/forbid" + "?roomId=" + i + "&forbidUid=" + str;
        if (i2 >= 0) {
            return str2 + "&shutUpTime=" + i2;
        }
        return str2;
    }

    public static String a(int i, int i2, int i3, long j) {
        d();
        return a + "v" + "1_0" + "/live/host/heartbeat" + "?roomId=" + i + "&admireCount=" + i2 + "&watchCount=" + i3 + "&timeSpan=" + j;
    }

    public static String a(int i) {
        d();
        return a + "v" + "1_0" + "/live/user/getSig?roomId=" + i;
    }

    public static String b(int i) {
        d();
        return a + "v" + "1_0" + "/live/room/detail?roomId=" + i;
    }

    public static String a(int i, String str) {
        d();
        return a + "v" + "1_0" + "/live/user/addblack?roomId=" + i + "&addblackUid=" + str;
    }

    public static String c(int i) {
        d();
        return a + "v" + "1_0" + "/live/user/addcomment?roomId=" + i;
    }

    public static String a(int i, int i2, int i3) {
        d();
        return a + "v" + "1_0" + "/live/user/buygift?roomId=" + i + "&propId=" + i2 + H5GameGrantTicketTask.COMMON_COUNT + i3;
    }

    public static String a(int i, boolean z) {
        d();
        if (z) {
            return a + "v" + "1_0" + "/live/host/startlive?roomId=" + i;
        }
        return a + "v" + "1_0" + "/live/host/endlive?roomId=" + i;
    }

    public static String a(int i, String str, String str2) {
        d();
        return a + "v" + "1_0" + "/live/room/reportlivelink?roomId=" + i + "&hlsLink=" + str + "&rtmpLink=" + str2;
    }

    public static String b(int i, String str) {
        d();
        return a + "v" + "1_0" + "/live/room/reportrecordplayvid?roomId=" + i + "&vid=" + str;
    }

    public static String c(int i, String str) {
        d();
        return a + "v" + "1_0" + "/live/user/tipoff?roomId=" + i + "&tipoffUid=" + str;
    }

    public static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e2) {
            return "";
        }
    }

    public static String d(int i) {
        d();
        return c + "liveRoomShare.html?roomId=" + i + "&tf=1" + "&hlsLink=" + com.qq.reader.liveshow.model.a.r() + "&rtmpLink=" + com.qq.reader.liveshow.model.a.s();
    }

    public static String c() {
        d();
        return a + "v" + "1_0" + "/live/user/balance";
    }
}
