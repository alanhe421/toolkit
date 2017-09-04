package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.Q;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.h;
import java.util.Iterator;

/* compiled from: RQDSRC */
public final class d implements com.tencent.feedback.upload.d {
    private Context a;

    public d(Context context) {
        this.a = context;
    }

    public final void a(int i, byte[] bArr, boolean z) {
        if (i == 510 && bArr != null) {
            try {
                f l = f.l();
                if (l == null) {
                    e.c("rqdp{  imposiable handle response ,but no eup instance!}", new Object[0]);
                    return;
                }
                Q q = new Q();
                q.a(new h(bArr));
                CrashStrategyBean q2 = l.q();
                if (q2 == null) {
                    q2 = l.p();
                    if (q2 == null) {
                        e.b("rqdp{  init eup sStrategy by default}", new Object[0]);
                        q2 = new CrashStrategyBean();
                    } else {
                        e.b("rqdp{  init eup sStrategy by uStrategy}", new Object[0]);
                        q2 = q2.clone();
                    }
                    l.a(q2);
                }
                if (a(q, q2) && z) {
                    e.b("rqdp{  save eup strategy}", new Object[0]);
                    a.a(this.a, i, bArr);
                }
                e.b("rqdp{  crashStrategy}[%s]", q);
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  process crash strategy error} %s", th.toString());
            }
        }
    }

    private static boolean a(Q q, CrashStrategyBean crashStrategyBean) {
        boolean z;
        boolean z2;
        boolean z3;
        Throwable e;
        Throwable th;
        if (q == null || crashStrategyBean == null || q.b == null || q.b.size() == 0) {
            return false;
        }
        boolean z4;
        boolean z5;
        int intValue;
        Iterator it = q.b.keySet().iterator();
        while (it.hasNext()) {
            e.b("key %s", (String) it.next());
        }
        String str = (String) q.b.get("B11");
        String str2 = (String) q.b.get("B12");
        String str3 = (String) q.b.get("B13");
        String str4 = (String) q.b.get("B21");
        String str5 = (String) q.b.get("B22");
        String str6 = (String) q.b.get(Integer.valueOf(60));
        String str7 = (String) q.b.get(Integer.valueOf(50));
        if (str != null) {
            e.b("%b", Boolean.valueOf(!str.equals("0")));
            z = z4;
        } else {
            z = true;
        }
        if (str2 != null) {
            if (str2.equals("0")) {
                z4 = false;
            } else {
                z4 = true;
            }
            e.b("%b", Boolean.valueOf(z4));
            z5 = z4;
        } else {
            z5 = false;
        }
        if (str3 != null) {
            if (str3.equals("0")) {
                z4 = false;
            } else {
                z4 = true;
            }
            e.b("%b", Boolean.valueOf(z4));
            z2 = z4;
        } else {
            z2 = false;
        }
        if (str4 != null) {
            if (str4.equals("0")) {
                z4 = false;
            } else {
                z4 = true;
            }
            if (crashStrategyBean.isMerged() != z4) {
                e.b("rqdp{  is merged changed} %b", Boolean.valueOf(z4));
                crashStrategyBean.setMerged(z4);
                z3 = true;
                if (str5 != null) {
                    if (str5.equals("0")) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (crashStrategyBean.isAssertOn() != z4) {
                        crashStrategyBean.setAssertEnable(z4);
                        e.b("rqdp{ Assert enable changed: } %s", Boolean.valueOf(z4));
                        z4 = true;
                        if (str6 != null) {
                            try {
                                intValue = Integer.valueOf(str6).intValue();
                                if (crashStrategyBean.getAssertTaskInterval() != intValue) {
                                    crashStrategyBean.setAssertTaskInterval(intValue);
                                    try {
                                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                                        z4 = true;
                                    } catch (Exception e2) {
                                        e = e2;
                                        z3 = true;
                                        if (!e.a(e)) {
                                            e.printStackTrace();
                                        }
                                        z4 = z3;
                                        if (str7 != null) {
                                            try {
                                                intValue = Integer.valueOf(str7).intValue();
                                                if (crashStrategyBean.getAssertLimitCount() != intValue) {
                                                    crashStrategyBean.setAssertLimitCount(intValue);
                                                    try {
                                                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                                                        z4 = true;
                                                    } catch (Throwable e3) {
                                                        th = e3;
                                                        z4 = true;
                                                        if (!e.a(th)) {
                                                            th.printStackTrace();
                                                        }
                                                        if (crashStrategyBean.isOpenANR() != z) {
                                                            crashStrategyBean.setOpenANR(z);
                                                            e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                                                            z4 = true;
                                                        }
                                                        if (crashStrategyBean.isBroadcast() != z5) {
                                                            crashStrategyBean.setBroadcast(z5);
                                                            e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                                                            z4 = true;
                                                        }
                                                        if (crashStrategyBean.isReceiveBroadcast() == z2) {
                                                            return z4;
                                                        }
                                                        crashStrategyBean.setReceiveBroadcast(z2);
                                                        e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                                                        return true;
                                                    }
                                                }
                                            } catch (Exception e4) {
                                                th = e4;
                                                if (e.a(th)) {
                                                    th.printStackTrace();
                                                }
                                                if (crashStrategyBean.isOpenANR() != z) {
                                                    crashStrategyBean.setOpenANR(z);
                                                    e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                                                    z4 = true;
                                                }
                                                if (crashStrategyBean.isBroadcast() != z5) {
                                                    crashStrategyBean.setBroadcast(z5);
                                                    e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                                                    z4 = true;
                                                }
                                                if (crashStrategyBean.isReceiveBroadcast() == z2) {
                                                    return z4;
                                                }
                                                crashStrategyBean.setReceiveBroadcast(z2);
                                                e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                                                return true;
                                            }
                                        }
                                        if (crashStrategyBean.isOpenANR() != z) {
                                            crashStrategyBean.setOpenANR(z);
                                            e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                                            z4 = true;
                                        }
                                        if (crashStrategyBean.isBroadcast() != z5) {
                                            crashStrategyBean.setBroadcast(z5);
                                            e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                                            z4 = true;
                                        }
                                        if (crashStrategyBean.isReceiveBroadcast() == z2) {
                                            return z4;
                                        }
                                        crashStrategyBean.setReceiveBroadcast(z2);
                                        e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                                        return true;
                                    }
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                z3 = z4;
                                e3 = th3;
                                if (e.a(e3)) {
                                    e3.printStackTrace();
                                }
                                z4 = z3;
                                if (str7 != null) {
                                    intValue = Integer.valueOf(str7).intValue();
                                    if (crashStrategyBean.getAssertLimitCount() != intValue) {
                                        crashStrategyBean.setAssertLimitCount(intValue);
                                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                                        z4 = true;
                                    }
                                }
                                if (crashStrategyBean.isOpenANR() != z) {
                                    crashStrategyBean.setOpenANR(z);
                                    e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                                    z4 = true;
                                }
                                if (crashStrategyBean.isBroadcast() != z5) {
                                    crashStrategyBean.setBroadcast(z5);
                                    e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                                    z4 = true;
                                }
                                if (crashStrategyBean.isReceiveBroadcast() == z2) {
                                    return z4;
                                }
                                crashStrategyBean.setReceiveBroadcast(z2);
                                e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                                return true;
                            }
                        }
                        if (str7 != null) {
                            intValue = Integer.valueOf(str7).intValue();
                            if (crashStrategyBean.getAssertLimitCount() != intValue) {
                                crashStrategyBean.setAssertLimitCount(intValue);
                                e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                                z4 = true;
                            }
                        }
                        if (crashStrategyBean.isOpenANR() != z) {
                            crashStrategyBean.setOpenANR(z);
                            e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                            z4 = true;
                        }
                        if (crashStrategyBean.isBroadcast() != z5) {
                            crashStrategyBean.setBroadcast(z5);
                            e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                            z4 = true;
                        }
                        if (crashStrategyBean.isReceiveBroadcast() == z2) {
                            return z4;
                        }
                        crashStrategyBean.setReceiveBroadcast(z2);
                        e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                        return true;
                    }
                }
                z4 = z3;
                if (str6 != null) {
                    intValue = Integer.valueOf(str6).intValue();
                    if (crashStrategyBean.getAssertTaskInterval() != intValue) {
                        crashStrategyBean.setAssertTaskInterval(intValue);
                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                        z4 = true;
                    }
                }
                if (str7 != null) {
                    intValue = Integer.valueOf(str7).intValue();
                    if (crashStrategyBean.getAssertLimitCount() != intValue) {
                        crashStrategyBean.setAssertLimitCount(intValue);
                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                        z4 = true;
                    }
                }
                if (crashStrategyBean.isOpenANR() != z) {
                    crashStrategyBean.setOpenANR(z);
                    e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                    z4 = true;
                }
                if (crashStrategyBean.isBroadcast() != z5) {
                    crashStrategyBean.setBroadcast(z5);
                    e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                    z4 = true;
                }
                if (crashStrategyBean.isReceiveBroadcast() == z2) {
                    return z4;
                }
                crashStrategyBean.setReceiveBroadcast(z2);
                e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                return true;
            }
        }
        z3 = false;
        if (str5 != null) {
            if (str5.equals("0")) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (crashStrategyBean.isAssertOn() != z4) {
                crashStrategyBean.setAssertEnable(z4);
                e.b("rqdp{ Assert enable changed: } %s", Boolean.valueOf(z4));
                z4 = true;
                if (str6 != null) {
                    intValue = Integer.valueOf(str6).intValue();
                    if (crashStrategyBean.getAssertTaskInterval() != intValue) {
                        crashStrategyBean.setAssertTaskInterval(intValue);
                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                        z4 = true;
                    }
                }
                if (str7 != null) {
                    intValue = Integer.valueOf(str7).intValue();
                    if (crashStrategyBean.getAssertLimitCount() != intValue) {
                        crashStrategyBean.setAssertLimitCount(intValue);
                        e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                        z4 = true;
                    }
                }
                if (crashStrategyBean.isOpenANR() != z) {
                    crashStrategyBean.setOpenANR(z);
                    e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
                    z4 = true;
                }
                if (crashStrategyBean.isBroadcast() != z5) {
                    crashStrategyBean.setBroadcast(z5);
                    e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
                    z4 = true;
                }
                if (crashStrategyBean.isReceiveBroadcast() == z2) {
                    return z4;
                }
                crashStrategyBean.setReceiveBroadcast(z2);
                e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
                return true;
            }
        }
        z4 = z3;
        if (str6 != null) {
            intValue = Integer.valueOf(str6).intValue();
            if (crashStrategyBean.getAssertTaskInterval() != intValue) {
                crashStrategyBean.setAssertTaskInterval(intValue);
                e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                z4 = true;
            }
        }
        if (str7 != null) {
            intValue = Integer.valueOf(str7).intValue();
            if (crashStrategyBean.getAssertLimitCount() != intValue) {
                crashStrategyBean.setAssertLimitCount(intValue);
                e.b("rqdp{ Assert task interval changed: } %s", Integer.valueOf(intValue));
                z4 = true;
            }
        }
        if (crashStrategyBean.isOpenANR() != z) {
            crashStrategyBean.setOpenANR(z);
            e.b("rqdp{ anr changed: } %b", Boolean.valueOf(z));
            z4 = true;
        }
        if (crashStrategyBean.isBroadcast() != z5) {
            crashStrategyBean.setBroadcast(z5);
            e.b("rqdp{ broad changed: } %b", Boolean.valueOf(z5));
            z4 = true;
        }
        if (crashStrategyBean.isReceiveBroadcast() == z2) {
            return z4;
        }
        crashStrategyBean.setReceiveBroadcast(z2);
        e.b("rqdp{ receiver changed: } %b", Boolean.valueOf(z2));
        return true;
    }
}
