package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Handler.Callback;
import android.support.v4.util.h;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.rookie.presenter.a;
import com.qq.reader.plugin.w;
import com.qq.reader.qurl.d;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.web.i;
import com.qq.reader.view.web.l;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.util.WeakReferenceHandler;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfClient */
public class f extends d {
    private static Callback a;
    private static i b;
    private static h<String, Integer> c = new h(20);

    public f(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    private static synchronized void v() {
        synchronized (f.class) {
            if (c.size() <= 0) {
                c.put("reward", Integer.valueOf(1));
                c.put("recommend", Integer.valueOf(2));
                c.put("monthlyticket", Integer.valueOf(3));
                c.put("suggestion", Integer.valueOf(4));
                c.put("readepage", Integer.valueOf(5));
                c.put("bookshelf", Integer.valueOf(6));
                c.put("toast", Integer.valueOf(7));
                c.put("advjump", Integer.valueOf(8));
                c.put("skinlist", Integer.valueOf(9));
                c.put("skin", Integer.valueOf(10));
                c.put("listenpage", Integer.valueOf(11));
                c.put("interactive", Integer.valueOf(12));
                c.put("recommendpage", Integer.valueOf(13));
                c.put("usercenterpage", Integer.valueOf(14));
                c.put("dialogshare", Integer.valueOf(15));
                c.put("liveshow", Integer.valueOf(16));
                c.put("advdialog", Integer.valueOf(17));
                c.put("rookiezone", Integer.valueOf(18));
                c.put("voucherdetail", Integer.valueOf(19));
                c.put("rookiegiftdialog", Integer.valueOf(20));
                c.put("webradio", Integer.valueOf(21));
                c.put("gorookiefreepage", Integer.valueOf(22));
            }
        }
    }

    public static int b(String str) {
        try {
            if (c.size() == 0) {
                v();
            }
            return ((Integer) c.get(str)).intValue();
        } catch (Exception e) {
            return -1;
        }
    }

    public void f() throws Exception {
        switch (b(d())) {
            case 1:
                a(1);
                return;
            case 2:
                a(2);
                return;
            case 3:
                a(3);
                return;
            case 4:
                n();
                return;
            case 5:
                k();
                return;
            case 6:
                l();
                return;
            case 7:
                j();
                return;
            case 8:
                i();
                return;
            case 9:
                g();
                return;
            case 10:
                h();
                return;
            case 11:
                o();
                return;
            case 12:
                m();
                return;
            case 13:
                p();
                return;
            case 14:
                q();
                return;
            case 15:
                z();
                return;
            case 16:
                y();
                return;
            case 17:
                x();
                return;
            case 18:
                r();
                return;
            case 19:
                w();
                return;
            case 20:
                s();
                return;
            case 21:
                t();
                return;
            case 22:
                o.c(b());
                return;
            default:
                return;
        }
    }

    private void w() {
        o.E(b(), null);
    }

    private void x() {
        Map e = e();
        String str = (String) e.get("type");
        String str2 = (String) e.get("id");
        if ("1".equals(str)) {
            if (b.a || b.b || a.a().d()) {
                c.d("ADV", "adv dialog is already showing advloading=" + b.a + " advshowing=" + b.b + " busy=" + a.a().d());
                return;
            }
            List b = b.a(b().getApplicationContext()).b("100126");
            for (int i = 0; i < b.size(); i++) {
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(i);
                if (aVar.d() == Long.valueOf(str2).longValue()) {
                    if (aVar.q() == 2 && !b.b(aVar)) {
                        b.c(aVar);
                        c.d("ADV", "adv is offline but res is not ready");
                        return;
                    } else if (b() != null) {
                        b = new i(b(), aVar.q(), aVar.r());
                        a = new 1(this);
                        b.b(aVar, new WeakReferenceHandler(a));
                        b.a(new 4(this, aVar));
                        b.a(new 5(this));
                        b.b(new 6(this));
                    }
                }
            }
        } else if (!"2".equals(str)) {
        } else {
            if (a.a().d() || b.b || b.a) {
                c.d("ADV", "rookie want to show but dialogs is busy rookie busy= " + a.a().d() + " advshowing=" + b.b + " advloading=" + b.a);
                return;
            }
            com.qq.reader.module.rookie.a.b a = a.a().a(Integer.valueOf(str2).intValue(), (String) e().get("location"));
            if (a == null) {
                c.d("ADV", "rookie want to show but rookie data is none ");
                return;
            }
            b = l.a(b(), a, new 7(this), 3);
            if (b != null) {
                a = new 8(this);
                b.b(new 9(this));
                ((l) b).a(a, new WeakReferenceHandler(a), true);
            }
        }
    }

    private void y() {
        o.c(b(), Integer.valueOf((String) e().get("room_num")).intValue());
    }

    private void z() {
        String str;
        CharSequence charSequence = "encode_";
        Map e = e();
        String str2 = (String) e.get("sharetype");
        if (e.get("bid") == null) {
            str = "0";
        } else {
            str = (String) e.get("bid");
        }
        String str3 = (String) e.get("encode_pageurl".replace(charSequence, ""));
        String str4 = (String) e.get("encode_picurl".replace(charSequence, ""));
        String str5 = (String) e.get("encode_summery".replace(charSequence, ""));
        String str6 = (String) e.get("encode_title".replace(charSequence, ""));
        Activity b = b();
        if (b != null && !b.isFinishing()) {
            try {
                new aj(b, str3, str4, str6, str5, str, Integer.valueOf(str2).intValue()).f_();
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                af.a(b(), (CharSequence) "吊起分享失败", 0);
            }
        }
    }

    public void g() {
        o.g(b(), a());
    }

    public void h() {
        String str = (String) e().get("id");
        if (w.b().c(str) == null) {
            o.g(b(), a());
        } else {
            o.b(b(), str, a());
        }
    }

    public void i() {
        if (e() != null) {
            String str = (String) e().get("pro_qurl");
            String str2 = (String) e().get("suc_qurl");
            String str3 = (String) e().get("execute_type");
            if (!TextUtils.isEmpty(str)) {
                ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new 10(this, str3, str2));
                readerProtocolJSONTask.setUrl(str);
                g.a().a(readerProtocolJSONTask);
            }
        }
    }

    public void j() {
        if (e() != null) {
            CharSequence charSequence = (String) e().get(MessageKey.MSG_CONTENT);
            if (!TextUtils.isEmpty(charSequence)) {
                af.a(ReaderApplication.getApplicationImp(), charSequence, 0).a();
            }
        }
    }

    public void k() {
        if (e() != null) {
            int intValue;
            int intValue2;
            String str = (String) e().get("bid");
            String str2 = (String) e().get("offest");
            try {
                intValue = Integer.valueOf((String) e().get("cid")).intValue();
            } catch (Exception e) {
                intValue = -1;
            }
            try {
                intValue2 = Integer.valueOf(str2).intValue();
            } catch (Exception e2) {
                intValue2 = -1;
            }
            o.a(b(), str, intValue, intValue2, a().b(SigType.WLOGIN_QRPUSH));
        }
    }

    public void l() {
        o.c(b(), a());
    }

    public void m() {
        if (e() != null) {
            String decode;
            String str;
            String str2;
            String str3;
            int intValue;
            String str4 = (String) e().get("bid");
            String str5 = (String) e().get("cid");
            String str6 = (String) e().get(MessageKey.MSG_ICON);
            if (!ao.s(str6)) {
                try {
                    decode = URLDecoder.decode(str6, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                str = (String) e().get("T");
                str2 = (String) e().get("M");
                str3 = (String) e().get("R");
                intValue = Integer.valueOf(str5).intValue();
                o.a(b(), Long.valueOf(str4).longValue(), intValue, decode, Boolean.valueOf(str).booleanValue(), Boolean.valueOf(str3).booleanValue(), Boolean.valueOf(str2).booleanValue());
            }
            decode = str6;
            str = (String) e().get("T");
            str2 = (String) e().get("M");
            str3 = (String) e().get("R");
            try {
                intValue = Integer.valueOf(str5).intValue();
            } catch (Exception e2) {
                intValue = 1;
            }
            o.a(b(), Long.valueOf(str4).longValue(), intValue, decode, Boolean.valueOf(str).booleanValue(), Boolean.valueOf(str3).booleanValue(), Boolean.valueOf(str2).booleanValue());
        }
    }

    public void a(int i) {
        if (e() != null) {
            String decode;
            int intValue;
            String str = (String) e().get("bid");
            String str2 = (String) e().get("cid");
            String str3 = (String) e().get(MessageKey.MSG_ICON);
            if (!ao.s(str3)) {
                try {
                    decode = URLDecoder.decode(str3, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intValue = Integer.valueOf(str2).intValue();
                o.a(i, b(), Long.valueOf(str).longValue(), intValue, decode, false);
            }
            decode = str3;
            try {
                intValue = Integer.valueOf(str2).intValue();
            } catch (Exception e2) {
                intValue = 1;
            }
            o.a(i, b(), Long.valueOf(str).longValue(), intValue, decode, false);
        }
    }

    public void n() {
        o.m(b(), a());
    }

    public void o() {
        if (e() != null) {
            o.e(b(), (String) e().get("mediaId"), a());
        }
    }

    public void p() {
        if (e() != null) {
            o.g(b(), (String) e().get("bid"), a());
        }
    }

    public void q() {
        if (e() != null) {
            try {
                o.d(b(), (String) e().get("userId"), URLDecoder.decode((String) e().get("userNickName"), "utf-8"), (String) e().get("userIconUrl"), a());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void A() {
        if (!(b instanceof l)) {
            b.a = false;
            b.b = false;
        }
        if (b != null && b.f()) {
            b.dismiss();
        }
        c.d("ADV", "release dialog set all null");
        b = null;
        a = null;
    }

    public void r() {
        if (e() != null) {
            try {
                String str = (String) e().get(s.ORIGIN);
                o.a(b(), Integer.valueOf((String) e().get("tab")).intValue(), null, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void s() {
        if (e() != null) {
            int intValue;
            try {
                intValue = Integer.valueOf((String) e().get("type")).intValue();
            } catch (Exception e) {
                intValue = 0;
            }
            switch (intValue) {
                case 1:
                    o.d(b(), intValue);
                    return;
                case 2:
                    try {
                        intValue = Integer.valueOf((String) e().get("giftid")).intValue();
                    } catch (Exception e2) {
                        intValue = 0;
                    }
                    com.qq.reader.module.rookie.a.b a = a.a().a(intValue, "p4");
                    if (a == null) {
                        c.d("ADV", "rookie want to show but rookie data is none ");
                        return;
                    }
                    b = l.a(b(), a, new 11(this), 3);
                    if (b != null) {
                        a = new 2(this);
                        b.b(new 3(this));
                        ((l) b).a(a, new WeakReferenceHandler(a), true);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void t() {
        if (e() != null) {
            o.k(b(), (String) e().get("pop"), null);
        }
    }
}
