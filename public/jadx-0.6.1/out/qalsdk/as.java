package qalsdk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings.System;
import com.dynamicload.Lib.DLConstants;
import com.qq.taf.jce.d;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.im_open.QalMonitor.Request;
import com.tencent.qalsdk.im_open.QalMonitor.Request.Conn;
import com.tencent.qalsdk.im_open.QalMonitor.Request.Environment;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.util.QLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StatReporter */
public class as {
    static final String a = "MSF.C.StatReport";
    static final String b = "param_sendTime";
    static final String c = "param_connResult";
    public static volatile boolean d = false;
    static long e = 0;
    static long f = 0;
    private static AtomicInteger i = new AtomicInteger(1);
    private static int j = 50;
    private static ConcurrentHashMap<Long, Long> k = new ConcurrentHashMap();
    private static ap n = new ap(j.a().u);
    private static SQLiteDatabase o;
    Runnable g = new at(this);
    private ArrayList<aq> h = new ArrayList();
    private volatile Handler l;
    private int m = 0;

    public as() {
        HandlerThread handlerThread = new HandlerThread("NetInfoCollection", 5);
        handlerThread.start();
        this.l = new Handler(handlerThread.getLooper());
    }

    private String a(c cVar) {
        if (cVar == null) {
            return "null EndpointKey";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cVar.b()).append("://");
        stringBuilder.append(cVar.c()).append(":").append(cVar.d());
        return stringBuilder.toString();
    }

    public static boolean a() {
        return !MsfSdkUtils.isTopActivity(j.a().u);
    }

    private static boolean e() {
        return !MsfSdkUtils.isScreenOn(j.a().u);
    }

    private static boolean f() {
        return System.getInt(j.a().u.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public static byte a(boolean z, long j, long j2) {
        int i;
        int i2 = 0;
        byte b = (byte) (e() ? 1 : 0);
        if (a()) {
            i = 2;
        } else {
            i = 0;
        }
        byte b2 = (byte) i;
        if (!z && j <= j2) {
            for (Entry entry : k.entrySet()) {
                if (j < ((Long) entry.getValue()).longValue() && j2 > ((Long) entry.getKey()).longValue()) {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "find deep sleep. report time:[" + j + ", " + j2 + "], sleep time:[" + entry.getKey() + ", " + entry.getValue() + "]");
                    }
                    z = true;
                }
            }
        }
        byte b3 = (byte) (z ? 4 : 0);
        if (f()) {
            i2 = 8;
        }
        return (byte) ((b3 | (b | b2)) | ((byte) i2));
    }

    public void b() {
        this.h.clear();
        this.l.post(this.g);
    }

    public void c() {
        this.m = 0;
        this.l.removeCallbacks(this.g);
    }

    private aq g() {
        Object obj = (m.e() || (!m.d() && m.l() == null)) ? 1 : null;
        if (obj != null) {
            return f.e();
        }
        aq aqVar = new aq();
        aqVar.a = f.b();
        aqVar.f = f.c();
        aqVar.g = NetConnInfoCenter.isGSM() ? NetConnInfoCenter.getGsmStrength() : NetConnInfoCenter.getCdmaStrength();
        return aqVar;
    }

    public void a(int i, long j, ArrayList<g> arrayList, int i2) {
        Object a;
        StringBuilder stringBuilder;
        Iterator it;
        String stringBuilder2;
        String str;
        String m;
        int i3;
        Cursor query;
        Throwable e;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int i4 = (int) j;
        String str2 = VERSION.RELEASE;
        String str3 = a.bg;
        int i5 = a() ? 1 : 2;
        for (int i6 = 0; i6 < this.h.size(); i6++) {
            if (((aq) this.h.get(i6)).e <= -70) {
                i = 3;
                break;
            }
        }
        if (this.h.size() >= 1) {
            ar arVar = new ar();
            arVar.a(this.h);
            if (arVar != null && arVar.c().size() > 0) {
                d dVar = new d();
                arVar.writeTo(dVar);
                a = com.qq.taf.jce.a.a(dVar.b());
                this.h.clear();
                stringBuilder = new StringBuilder();
                it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuilder.append(((g) it.next()).b + DLConstants.DEPENDENCY_PACKAGE_DIV);
                }
                stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.endsWith(DLConstants.DEPENDENCY_PACKAGE_DIV)) {
                    str = stringBuilder2;
                } else {
                    str = stringBuilder2.substring(0, stringBuilder2.length() - 1);
                }
                m = o.m();
                if (i != 1) {
                    if (i2 == 0) {
                        i3 = 1;
                    } else if (i2 == 1) {
                        i3 = 0;
                    } else if (i2 == 2) {
                        i3 = 3;
                    } else if (i2 == 3) {
                        i3 = 2;
                    }
                    QLog.d(a, 1, "insert db," + currentTimeMillis + ":" + i4 + ":" + i + ":" + 1 + ":" + str2 + ":" + str3 + ":" + i5 + ":" + str + ":" + m + ":" + i3 + ":" + this.h.size());
                    if (o == null) {
                        o = n.getWritableDatabase();
                    }
                    query = o.query("conntime", new String[]{"timestamp", "cost_time"}, null, null, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() > j) {
                                QLog.i(a, "statreport over,clear record.record_num:" + query.getCount());
                                o.execSQL("DELETE FROM conntime");
                            }
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                QLog.d(a, 1, "qal_monitor data store failed.", e);
                                if (query != null) {
                                    query.close();
                                }
                            } catch (Throwable th) {
                                e = th;
                                if (query != null) {
                                    query.close();
                                }
                                throw e;
                            }
                        }
                    }
                    o.execSQL("insert into conntime(timestamp, cost_time,result,os, os_version, sdk_version, process_status,server_ip_port,gateway_ip,errcode,net_info) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[]{Long.valueOf(currentTimeMillis), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(1), str2, str3, Integer.valueOf(i5), str, m, Integer.valueOf(i3), a});
                    if (query == null) {
                        query.close();
                    }
                }
                i3 = 0;
                QLog.d(a, 1, "insert db," + currentTimeMillis + ":" + i4 + ":" + i + ":" + 1 + ":" + str2 + ":" + str3 + ":" + i5 + ":" + str + ":" + m + ":" + i3 + ":" + this.h.size());
                if (o == null) {
                    o = n.getWritableDatabase();
                }
                query = o.query("conntime", new String[]{"timestamp", "cost_time"}, null, null, null, null, null);
                if (query != null) {
                    if (query.getCount() > j) {
                        QLog.i(a, "statreport over,clear record.record_num:" + query.getCount());
                        o.execSQL("DELETE FROM conntime");
                    }
                }
                o.execSQL("insert into conntime(timestamp, cost_time,result,os, os_version, sdk_version, process_status,server_ip_port,gateway_ip,errcode,net_info) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[]{Long.valueOf(currentTimeMillis), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(1), str2, str3, Integer.valueOf(i5), str, m, Integer.valueOf(i3), a});
                if (query == null) {
                    query.close();
                }
            }
        }
        a = null;
        this.h.clear();
        stringBuilder = new StringBuilder();
        it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((g) it.next()).b + DLConstants.DEPENDENCY_PACKAGE_DIV);
        }
        stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(DLConstants.DEPENDENCY_PACKAGE_DIV)) {
            str = stringBuilder2;
        } else {
            str = stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        m = o.m();
        if (i != 1) {
            if (i2 == 0) {
                i3 = 1;
            } else if (i2 == 1) {
                i3 = 0;
            } else if (i2 == 2) {
                i3 = 3;
            } else if (i2 == 3) {
                i3 = 2;
            }
            QLog.d(a, 1, "insert db," + currentTimeMillis + ":" + i4 + ":" + i + ":" + 1 + ":" + str2 + ":" + str3 + ":" + i5 + ":" + str + ":" + m + ":" + i3 + ":" + this.h.size());
            if (o == null) {
                o = n.getWritableDatabase();
            }
            query = o.query("conntime", new String[]{"timestamp", "cost_time"}, null, null, null, null, null);
            if (query != null) {
                if (query.getCount() > j) {
                    QLog.i(a, "statreport over,clear record.record_num:" + query.getCount());
                    o.execSQL("DELETE FROM conntime");
                }
            }
            o.execSQL("insert into conntime(timestamp, cost_time,result,os, os_version, sdk_version, process_status,server_ip_port,gateway_ip,errcode,net_info) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[]{Long.valueOf(currentTimeMillis), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(1), str2, str3, Integer.valueOf(i5), str, m, Integer.valueOf(i3), a});
            if (query == null) {
                query.close();
            }
        }
        i3 = 0;
        QLog.d(a, 1, "insert db," + currentTimeMillis + ":" + i4 + ":" + i + ":" + 1 + ":" + str2 + ":" + str3 + ":" + i5 + ":" + str + ":" + m + ":" + i3 + ":" + this.h.size());
        try {
            if (o == null) {
                o = n.getWritableDatabase();
            }
            query = o.query("conntime", new String[]{"timestamp", "cost_time"}, null, null, null, null, null);
            if (query != null) {
                if (query.getCount() > j) {
                    QLog.i(a, "statreport over,clear record.record_num:" + query.getCount());
                    o.execSQL("DELETE FROM conntime");
                }
            }
            o.execSQL("insert into conntime(timestamp, cost_time,result,os, os_version, sdk_version, process_status,server_ip_port,gateway_ip,errcode,net_info) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[]{Long.valueOf(currentTimeMillis), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(1), str2, str3, Integer.valueOf(i5), str, m, Integer.valueOf(i3), a});
            if (query == null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            QLog.d(a, 1, "qal_monitor data store failed.", e);
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    public void d() {
        Cursor query;
        Throwable e;
        if (o == null) {
            o = n.getWritableDatabase();
        }
        try {
            query = o.query("conntime", new String[]{"timestamp", "cost_time", "result", "os", "os_version", "sdk_version", "process_status", "server_ip_port", "gateway_ip", "errcode", "net_info"}, null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() >= 1) {
                        query.moveToFirst();
                        Request request = new Request();
                        int i = 0;
                        while (!query.isAfterLast()) {
                            MessageMicro conn = new Conn();
                            conn.timestamp.set(query.getInt(0));
                            conn.cost_time.set(query.getInt(1));
                            conn.result.set(query.getInt(2));
                            conn.os.set(query.getInt(3));
                            conn.os_version.set(query.getString(4));
                            conn.sdk_version.set(query.getString(5));
                            conn.process_status.set(query.getInt(6));
                            conn.server_ip_port.set(query.getString(7));
                            conn.gateway_ip.set(query.getString(8));
                            conn.errcode.set(query.getInt(9));
                            String string = query.getString(10);
                            if (!(string == null || string.length() == 0)) {
                                com.qq.taf.jce.c cVar = new com.qq.taf.jce.c(com.qq.taf.jce.a.a(string));
                                ar arVar = new ar();
                                arVar.readFrom(cVar);
                                for (int i2 = 0; i2 < arVar.c().size(); i2++) {
                                    aq aqVar = (aq) arVar.c().get(i2);
                                    MessageMicro environment = new Environment();
                                    environment.apn.set(aqVar.a);
                                    environment.wifi_supplicant_state.set(aqVar.b);
                                    environment.wifi_ssid.set(aqVar.c);
                                    environment.wifi_bssid.set(aqVar.d);
                                    environment.wifi_rssi.set(aqVar.e);
                                    environment.rat.set(aqVar.f);
                                    environment.rat_ss.set(aqVar.g);
                                    conn.env.add(environment);
                                }
                            }
                            request.conn.add(conn);
                            QLog.d(a, 4, "report conn time" + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.timestamp.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.cost_time.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.result.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.os.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.os_version.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.sdk_version.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.process_status.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.server_ip_port.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.gateway_ip.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.errcode.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + conn.env.size());
                            query.moveToNext();
                            i++;
                        }
                        request.seqno.set(i.incrementAndGet());
                        byte[] toByteArray = request.toByteArray();
                        ToServiceMsg toServiceMsg = new ToServiceMsg("", j.a().b().c(), a.cs);
                        toServiceMsg.putWupBuffer(o.b(toByteArray));
                        toServiceMsg.setUinType(20);
                        toServiceMsg.setMsfCommand(MsfCommand.qal_reportEvent);
                        toServiceMsg.setAppId(a.bm);
                        toServiceMsg.setTimeout(a.ap);
                        MsfSdkUtils.addToMsgProcessName(MsfSdkUtils.getProcessName(j.a().u), toServiceMsg);
                        j.a().a(toServiceMsg);
                        if (i > j) {
                            QLog.d(a, 4, "stat over max,clear record.record_num:" + i);
                            o.execSQL("DELETE FROM conntime");
                        }
                        if (query != null) {
                            query.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        QLog.d(a, 1, "qal_monitor query conn time failed. " + e, e);
                        if (query != null) {
                            query.close();
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (query != null) {
                            query.close();
                        }
                        throw e;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            QLog.d(a, 1, "qal_monitor query conn time failed. " + e, e);
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    public void a(FromServiceMsg fromServiceMsg) {
        if (o == null) {
            o = n.getWritableDatabase();
        }
        if (fromServiceMsg.isSuccess()) {
            o.execSQL("DELETE FROM conntime");
            QLog.d(a, "delete all conntime record");
        }
    }
}
