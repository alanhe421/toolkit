package com.qq.reader.appconfig;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.conn.a.c;
import com.qq.reader.common.utils.ao;
import com.tencent.beacon.event.UserAction;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.io.File;

/* compiled from: ServerUrl */
public class e {
    public static String A;
    public static String B;
    public static String C;
    public static String D;
    public static String E;
    public static String F;
    public static String G;
    public static String H;
    public static String I;
    public static String J;
    public static String K;
    public static String L;
    public static final String M = (a + "common/needgeneinfo");
    public static final String N = (a + "audio/auth?");
    public static final String O = (a + "audio/index?");
    public static final String P = (a + "audio/authChapters?");
    public static final String Q = e;
    public static final String R = (G + "mqq/upbookstatusbatch?");
    public static final String S = (f + "/interact/report?");
    public static final String T = (h + "addTopicShareNum?");
    public static final String U = (c + "/index.html?");
    public static final String V = (c + "/classify.html?");
    public static final String W = U;
    public static final String X = (c + "/book/search/index?");
    public static final String Y = (c + "/themeDetail.html?");
    public static final String Z = (c + "/feedbackIndex.html?");
    public static String a;
    public static final String aA = (l + "book/weixin?bid=");
    public static final String aB = (e + "privilegeV2.html?");
    public static final String aC = (f + "/questionaire");
    public static final String aD = j;
    public static final String aE = (aD + "handleall");
    public static final String aF = (aD + "pluginupdatev2?id=");
    public static final String aG = (aD + "getplugincategory");
    public static final String aH = (aD + "getpluginlistv2?cid=");
    public static final String aI = (aD + "checkupdate");
    public static final String aJ = (aD + BuglyBroadcastRecevier.ACTION_ENCRY_KEY);
    public static final String aK = (aD + "reportchapterbug");
    public static final String aL = (aD + "report");
    public static final String aM = (aD + "auth?");
    public static final String aN = (aD + "book/getAccount");
    public static final String aO = (aD + "");
    public static final String aP = (aD + "querylastchapter?");
    public static final String aQ = (E + "newhotkey?");
    public static final String aR = (E + "audiohotkey?");
    public static final String aS = (aD + "exception");
    public static final String aT = (h + "adV2");
    public static final String aU = (d + "/coin/recharge");
    public static final String aV = (d + "/coin/chargelist");
    public static final String aW = (aD + "cloud/updatepaged3?clientversion=");
    public static final String aX = (aD + "cloud/commit3?tid=");
    public static final String aY = (k + "cloud/update?clientversion=");
    public static final String aZ = (k + "cloud/updatebook?bookid=");
    public static final String aa = (j + "/readonline");
    public static final String ab = (a + "nativepage/aqa/settlement/authorInfo");
    public static final String ac = (a + "nativepage/aqa/settlement/bindingWX");
    public static final String ad = (a + "nativepage/aqa/settlement/detail");
    public static final String ae = (n + "readAchievementShare.html?");
    public static final String af = (c + "/buybook.html?");
    public static final String ag = (j + "common/dobuybook?");
    public static final String ah = (c + "/buyChapter.html?");
    public static final String ai = (c + "/buyplugin.html?");
    public static final String aj = (c + "/buyaudiobook.html?");
    public static final String ak = (j + "common/dobuychapters?");
    public static final String al = (j + "common/dobuymediabook?");
    public static final String am = (j + "common/dobuymediachapters?");
    public static final String an = (j + "common/ismediabookbuy?");
    public static final String ao = (j + "common/isbookbuy?");
    public static final String ap = (h + "queryChapterLoad?");
    public static final String aq = (a + "queryChapterLoad?");
    public static final String ar = (c + "/kt_vip.html?");
    public static final String as = (c + "/buyhistory.html?");
    public static final String at = (c + "/book/favorlist?");
    public static final String au = (c + "/book/ybaccount?");
    public static final String av = (c + "/account.html?");
    public static final String aw = (j + "bookalldown?");
    public static final String ax = (c + "/tslist.html?");
    public static final String ay = (f + "/downmedia?");
    public static final String az = (f + "/downmediaall?");
    public static String b;
    public static final String bA = (h + "nativepage/comment/" + "setreplytop?");
    public static final String bB = (h + "nativepage/comment/" + "addreply?");
    public static final String bC = (h + "nativepage/comment/" + "delreply?");
    public static final String bD = (h + "nativepage/comment/" + "index?");
    public static final String bE = (h + "nativepage/comment/" + "detail?");
    public static final String bF = (h + "nativepage/comment/" + "list?sort=2&subtype=0");
    public static final String bG = (h + "nativepage/comment/" + "list?sort=1&subtype=1");
    public static final String bH = (h + "nativepage/comment/" + "addcomment?bid=");
    public static final String bI = (h + "nativepage/comment/" + "delcomment?");
    public static final String bJ = (h + "reportusertag?");
    public static final String bK = (K + "common/newUser/getGift?");
    public static final String bL = (h + "queryselectedtag");
    public static final String bM = (h + "readgene");
    public static final String bN = (a + "combine/list");
    public static final String bO = (h + "day8gift");
    public static final String bP = (h + "nativepage/comment/" + "zonelist?");
    public static final String bQ = (h + "nativepage/comment/" + "indexforcommonzone?ctype=4&bid=");
    public static final String bR = (a + "discover");
    public static final String bS = (h + "nativepage/infostream/list?");
    public static final String bT = (h + "topic/addreply?");
    public static final String bU = (a + "getManitoHome?platform=1");
    public static final String bV = (a + "nativepage/user/profile?platform=android");
    public static final String bW = (a + "nativepage/user/shelf?");
    public static final String bX = (a + "nativepage/user/interactive?");
    public static final String bY = (a + "nativepage/user/signature?");
    public static final String bZ = (h + "nativepage/comment/" + "rewardcomment?");
    public static final String ba = (k + "cloud/commit?tid=");
    public static final String bb = (aD + "CheckInterface");
    public static final String bc = (o + "game/docharge?");
    public static final String bd = (o + "v2/game/docharge?");
    public static final String be = (o + "game/queryopenid");
    public static final String bf = (o + "game/sendCoin?");
    public static final String bg = (o + "game/presentBookCoin?");
    public static final String bh = (o + "game/getBalance");
    public static final String bi = (i + "v" + "6_5_3" + "/endPage/getUserIcons?");
    public static final String bj = (m + "note/get");
    public static final String bk = (m + "note/save");
    public static final String bl = (f + "/downloadChaptersBatch?bid=%s&pageSize=%d&pageNo=%d&oldVersion=%d&oldCount=%d");
    public static final String bm = (aD + "querybookinfo?bid=%s&lutime=%d&lucc=%d&luv=%d");
    public static final String bn = (aD + "querymediainfo?bid=%s");
    public static final String bo = (f + "/queryBookNews?");
    public static final String bp = (h + "getOpenVipConfig?");
    public static final String bq = (h + "getQOpenVipConfig?");
    public static final String br = (h + "openQQVip?");
    public static final String bs = (h + "openVip?");
    public static final String bt = (h + "giveVipcard");
    public static final String bu = (b + "monthRechargeGift?month=");
    public static final String bv = (e + "bookDetailShare.html?tf=1&bid=");
    public static final String bw = (e + "mediaBookShare.html?tf=1&bid=");
    public static final String bx = (h + "nativepage/theme/list?list_version=");
    public static final String by = (h + "nativepage/theme/get?id=");
    public static final String bz = (h + "nativepage/theme/enable?ids=");
    public static String c;
    public static String cA = (a + "nativepage/aqa/update");
    public static String cB = (a + "nativepage/aqa/update");
    public static String cC = (a + "nativepage/listen?qid=");
    public static String cD = (c + "/manitoShare.html?");
    public static String cE = (r + "activepage/activepage.html");
    public static String cF = (a + "nativepage/user/updateIntPri");
    public static String cG = (a + "nativepage/user/followManitos");
    public static String cH = (a + "nativepage/game/getMainPage");
    public static String cI = (a + "nativepage/game/getTomorrowBanner");
    public static String cJ = (a + "nativepage/game/getCategoryPage");
    public static String cK = (a + "nativepage/game/getListByType");
    public static String cL = (a + "nativepage/game/postClientClickInfo");
    public static String cM = (H + "User/mine");
    public static final String cN = (a + "nativepage/community/list");
    public static final String cO = (x + "pluginbuy");
    public static final String cP = (a + "nativepage/comment/" + "top?");
    public static final String cQ = (a + "nativepage/comment/" + "better?");
    public static final String cR = (a + "nativepage/comment/" + "lock?");
    public static final String cS = (a + "nativepage/comment/" + "blackuser?");
    public static final String cT = (s + "nativepage/cloudLive/over/user");
    public static final String cU = (s + "nativepage/cloudLive/over/author");
    public static final String cV = (s + "nativepage/user/cloudLive");
    public static final String cW = (s + "nativepage/cloudLive/author");
    public static final String cX = (e + "agreement.html?tf=1");
    public static final String cY = (e + "privacy.html");
    public static final String cZ = (e + "about.html");
    public static final String ca = (a + "common/classicallist?");
    public static final String cb = (p + "/redbag/create");
    public static final String cc = (n + "redbag.html?tf=1&rid=");
    public static final String cd = (p + "/redbag/send");
    public static final String ce = (j + "afterlogin");
    public static final String cf = (h + "nativepage/score/update");
    public static final String cg = (h + "nativepage/score/get");
    public static final String ch = (h + "getWeekReadTime");
    public static final String ci = (i + "common/report/content");
    public static final String cj = (h + "navigationBar?sex=");
    public static final String ck = (h + "chapterOver?");
    public static final String cl = (h + "chapterOverAd?");
    public static final String cm = (a + "nativepage/bookstart?");
    public static final String cn = (h + "config");
    public static final String co = a;
    public static final String cp = (a + "audio/autoAuth?");
    public static String cq = (D + "autocmp?");
    public static String cr = E;
    public static String cs = (cr + "search?");
    public static String ct = (D + "newaudio?");
    public static String cu = (E + "newaudiosearch?");
    public static String cv = (a + "nativepage/aqa/askQuestion?");
    public static String cw = (a + "nativepage/aqa/doAsk");
    public static String cx = (a + "nativepage/aqa/question");
    public static String cy = (a + "nativepage/aqa/addreply");
    public static String cz = (a + "nativepage/aqa/delreply");
    public static String d;
    public static final String da = (e + "newUserGiftsV2.html");
    public static final String db = (e + "newUserGrowUp.html");
    public static final String dc = (K + "common/newUserGift?giftId=13");
    public static final String dd = (a + "nativepage/infostream/firstpage");
    public static final String de = (a + "seckill/list");
    public static final String df = (e + "seckill.html");
    public static final String dg = (a + "seckill");
    public static final String dh = (h + "nativepage/book/dynamicInfos?");
    public static final String di = (h + "nativepage/discover/welfarelist?");
    public static final String dj = (a + "common/manitoDynamicList?");
    public static final String dk = (b + "nativepage/rechargeList");
    public static final String dl = (b + "monthpage");
    public static final String dm = (L + "v" + "6_5_3" + "/nativepage/cartoon/columns");
    public static final String dn = (L + "v" + "6_5_3" + "/nativepage/cartoon/dir");
    public static final String do = (L + "v" + "6_5_3" + "/nativepage/cartoon/column");
    public static final String dp = (L + "v" + "6_5_3" + "/nativepage/cartoon/detail");
    public static String e;
    @Deprecated
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    public static String s;
    public static String t;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;

    /* compiled from: ServerUrl */
    public static class a {
        public static final String a = (e.a + "nativepage/redpacket/redpacketlist");
        public static final String b = (e.a + "nativepage/redpacket/creat?");
        public static final String c = (e.a + "nativepage/redpacket/getRedPacketUUID?");
        public static final String d = (e.a + "nativepage/redpacket/get?");
        public static final String e = (e.a + "nativepage/redpacket/open?");
        public static final String f = (e.a + "nativepage/redpacket/bookredpacketlist");
        public static final String g = (e.e + "redPacket.html?code=share&tf=1");
        public static final String h = e.e;
        public static String i = (e.c + "/helpDetail.html?id=1045");
        public static String j = (e.c + "/helpDetail.html?id=1039");
        public static String k = (e.c + "/helpDetail.html?id=1037");
        public static String l = (e.c + "/helpList.html?type=18");
    }

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        t = null;
        u = null;
        v = null;
        w = null;
        x = null;
        y = null;
        z = null;
        A = null;
        B = null;
        C = null;
        D = null;
        E = null;
        F = null;
        G = null;
        H = null;
        I = null;
        J = null;
        K = null;
        L = null;
        t = c.a;
        u = c.b;
        v = c.c;
        w = c.d;
        x = c.e;
        y = c.f;
        z = c.g;
        A = c.h;
        B = c.i;
        C = c.j;
        D = c.k;
        E = c.l;
        F = c.m;
        G = c.n;
        H = c.o;
        I = c.p;
        J = c.q;
        K = c.s;
        L = c.t;
        if (b.a) {
            c = t + "book_res/reader/android/" + "6_5_3" + "";
            n = t + "book_res/reader/android/common/";
            r = t + "book_res/reader/android/publicity/";
            e = t + "book_res/reader/common/common/";
        } else {
            c = t + "android/" + "6_5_3";
            n = t + "android/common/";
            r = t + "android/publicity/";
            e = t + "common/common/";
        }
        d = u + "6_5_3" + "/nativepage";
        f = u + "6_5_3";
        g = v + "weixin/";
        h = w + "v" + "6_5_3" + "/";
        i = w;
        j = x;
        k = y;
        l = z;
        m = A;
        o = B;
        p = u + "common";
        q = C + "touch/";
        a = F + "v" + "6_5_3" + "/";
        b = K + "v" + "6_5_3" + "/";
        s = J + "v" + "6_5_3" + "/";
    }

    public static String a(String str) {
        String str2 = "";
        if (b.a) {
            return "http://61.135.172.185:8000/ChapBatAuthWithPD?";
        }
        return c.a().b() + "/ChapBatAuthWithPD?";
    }

    public static String b(String str) {
        return "http://" + c.r + "/ChapBatAuthWithPD?";
    }

    public static String a(Context context) {
        return ar + b(context);
    }

    public static String b(Context context) {
        long c = d.c();
        com.qq.reader.common.login.b.a c2 = com.qq.reader.common.login.c.c();
        if (com.qq.reader.common.login.c.b()) {
            String str;
            switch (c2.d()) {
                case 1:
                    str = "";
                    if (c2 != null) {
                        str = c2.a(context);
                    }
                    if (str.length() > 0) {
                        str = "&usid=" + str;
                    }
                    if (c == 0) {
                        return "client=1&version=qqreader_6.5.3.0888_android" + str + "&" + "channel=" + ao.h(context) + "&" + "safekey" + "=" + d.y(context);
                    }
                    return "sid=" + c + "&" + "version=" + "qqreader_6.5.3.0888_android" + str + "&" + "channel=" + ao.h(context) + "&" + "client=1" + "&" + "safekey" + "=" + d.y(context);
                case 2:
                case 10:
                case 50:
                    String str2 = "";
                    str = "";
                    if (c2 != null) {
                        str2 = c2.a(context);
                        str = c2.c();
                    }
                    if (c == 0) {
                        return "client=1&version=qqreader_6.5.3.0888_android&accesstoken=" + str2 + "&" + "uid=" + str + "&" + "channel=" + ao.h(context) + "&" + "safekey" + "=" + d.y(context);
                    }
                    return "sid=" + c + "&" + "version=" + "qqreader_6.5.3.0888_android" + "&" + "accesstoken=" + str2 + "&" + "uid=" + str + "&" + "channel=" + ao.h(context) + "&" + "client=1" + "&" + "safekey" + "=" + d.y(context);
            }
        }
        if (c == 0) {
            return "client=1&version=qqreader_6.5.3.0888_android&channel=" + ao.h(context) + "&" + "safekey" + "=" + d.y(context);
        }
        return "sid=" + c + "&" + "version=" + "qqreader_6.5.3.0888_android" + "&" + "channel=" + ao.h(context) + "&" + "client=1" + "&" + "safekey" + "=" + d.y(context);
    }

    public static String c(Context context) {
        String qimei;
        String z = d.z(context);
        try {
            qimei = UserAction.getQIMEI();
        } catch (Exception e) {
            qimei = "";
        }
        return "&qimei=" + qimei + "&" + "timi=" + z;
    }

    public static String a(Context context, String str, String str2) {
        return af + "bid=" + str + "&" + b(context) + ("&origin=" + str2);
    }

    public static String a(int i) {
        File file;
        switch (i) {
            case 0:
                file = new File(com.qq.reader.common.c.a.cY + "/error.html");
                if (file.exists()) {
                    return "file://" + file.getAbsolutePath();
                }
                return c + "/error.html";
            case 1:
                file = new File(com.qq.reader.common.c.a.cY + "/web_error.html");
                if (file.exists()) {
                    return "file://" + file.getAbsolutePath();
                }
                return c + "/web_error.html";
            default:
                return null;
        }
    }

    public static String a(Context context, long j) {
        return "nativepage/book/detail?bid=" + j + "&" + b(context);
    }

    public static String b(Context context, long j) {
        return "reward.html?bid=" + j + "&" + b(context);
    }

    public static String c(Context context, long j) {
        return "rticket.html?bid=" + j + "&" + b(context);
    }

    public static String d(Context context, long j) {
        return "monthTicket.html?bid=" + j + "&" + b(context);
    }

    public static String e(Context context, long j) {
        return "fans.html?bid=" + j + "&" + b(context);
    }

    public static String d(Context context) {
        return "grow.html?" + b(context);
    }

    public static String e(Context context) {
        return "vipgrow.html?" + b(context);
    }

    public static String f(Context context) {
        return "account.html?" + b(context);
    }

    public static String g(Context context) {
        return c + "/" + "todaytask.html?" + b(context);
    }

    public static String f(Context context, long j) {
        return c + "/tsdetail.html?" + "bid=" + j + "&" + b(context);
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("usid=");
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf("&", indexOf + 1);
        if (indexOf2 != -1) {
            return str.substring(indexOf + 5, indexOf2);
        }
        return str.substring(indexOf + 5, str.length());
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        int indexOf = str.indexOf("usid=");
        int indexOf2;
        if (str2.length() > 0) {
            if (indexOf == -1) {
                if (str.indexOf("?") == -1) {
                    return str + "?" + "usid=" + str2;
                }
                if (str.indexOf("?") != str.length() - 1) {
                    return str + "&" + "usid=" + str2;
                }
                if (str.indexOf("?") == str.length() - 1) {
                    return str + "usid=" + str2;
                }
                return null;
            } else if (str.indexOf(str2) != -1) {
                return null;
            } else {
                indexOf2 = str.indexOf("&", indexOf + 1);
                if (indexOf2 < 0) {
                    return str.substring(0, indexOf + 5) + str2;
                }
                return (str.substring(0, indexOf + 5) + str2) + str.substring(indexOf2, str.length());
            }
        } else if (indexOf == -1) {
            return str;
        } else {
            indexOf2 = str.indexOf("&", indexOf);
            if (indexOf2 < 0) {
                return str.substring(0, indexOf - 1);
            }
            return str.substring(0, indexOf) + str.substring(indexOf2 + 1, str.length());
        }
    }

    public static String a(String str, Context context, String str2) {
        String str3 = "";
        if (!(str == null || str.equals(""))) {
            if (str.startsWith("http://")) {
                return str;
            }
            str3 = com.qq.reader.common.c.a.cY + "/" + str;
            if (str3.contains("?")) {
                str3 = str3.substring(0, str3.indexOf("?"));
            }
            str3 = com.qq.reader.common.offline.a.a(context).b() == 2 ? b.a ? str2 + "/" + str : (a() && new File(str3).exists()) ? "file:///" + str3 : str2 + "/" + str : str2 + "/" + str;
        }
        return str3;
    }

    private static boolean a() {
        if (new File(com.qq.reader.common.c.a.cW).exists()) {
            return false;
        }
        return true;
    }
}
