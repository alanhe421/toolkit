package com.qq.reader.common.protocol;

import com.qq.reader.common.utils.ao;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DailyAllProtocolHandler */
public class a {
    private static int a = 0;
    private static List<b> b = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask r11, java.lang.String r12, android.content.Context r13) {
        /*
        r1 = 0;
        r7 = -1;
        r2 = 1;
        r4 = 0;
        r0 = "all";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = " ";
        r3 = r3.append(r5);
        r3 = r3.append(r12);
        r3 = r3.toString();
        com.qq.reader.common.monitor.debug.c.a(r0, r3);
        if (r12 != 0) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r0 = 0;
        r3 = 0;
        r5 = new org.json.JSONObject;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r5.<init>(r12);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = "code";
        r6 = r5.optInt(r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r6 == 0) goto L_0x0048;
    L_0x0031:
        if (r1 == 0) goto L_0x0036;
    L_0x0033:
        r0.close();	 Catch:{ Exception -> 0x003c }
    L_0x0036:
        if (r1 == 0) goto L_0x0020;
    L_0x0038:
        r3.close();	 Catch:{ Exception -> 0x003c }
        goto L_0x0020;
    L_0x003c:
        r0 = move-exception;
        r1 = "RecommendMsgHandler";
        r2 = r0.toString();
        com.qq.reader.common.monitor.f.a(r1, r2, r0);
        goto L_0x0020;
    L_0x0048:
        r0 = "gtype";
        r0 = r5.optInt(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 == r7) goto L_0x0054;
    L_0x0051:
        com.qq.reader.appconfig.a.d.H(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x0054:
        r0 = "usertype";
        r0 = r5.optInt(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 == r7) goto L_0x0069;
    L_0x005d:
        com.qq.reader.appconfig.a.d.F(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = com.qq.reader.appconfig.a.d.aO(r13);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 == 0) goto L_0x0069;
    L_0x0066:
        com.qq.reader.appconfig.a.d.D(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x0069:
        r0 = "redBagShow";
        r3 = 0;
        r0 = r5.optBoolean(r0, r3);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.d.B(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "cacheWindow";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 != 0) goto L_0x009d;
    L_0x0081:
        r3 = "\\|";
        r0 = r0.split(r3);	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        r3 = r0.length;	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        r6 = 2;
        if (r3 != r6) goto L_0x009d;
    L_0x008c:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        r3 = 1;
        r0 = r0[r3];	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        r8 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
        com.qq.reader.common.utils.m.a(r13, r6, r8);	 Catch:{ Exception -> 0x01f3, all -> 0x0241 }
    L_0x009d:
        r0 = "domain";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 != 0) goto L_0x00ad;
    L_0x00aa:
        com.qq.reader.appconfig.a.c.b(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x00ad:
        r0 = "port";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 != 0) goto L_0x00bd;
    L_0x00ba:
        com.qq.reader.appconfig.a.c.a(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x00bd:
        r0 = com.qq.reader.b.a.a.c();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 != 0) goto L_0x0109;
    L_0x00c3:
        r0 = new com.qq.reader.common.protocol.c;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0.<init>();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = "update_code";
        r3 = r5.optInt(r3);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 == 0) goto L_0x00f5;
    L_0x00d1:
        r6 = "update_url";
        r6 = r5.optString(r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r0.a(r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r6.a(r3);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r7 = "update_intro";
        r7 = r5.optString(r7);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r6.b(r7);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r7 = "update_version";
        r7 = r5.optString(r7);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6.c(r7);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x00f5:
        r6 = r0.a();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r6 == 0) goto L_0x0109;
    L_0x00fb:
        r6 = r0.a();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r6.trim();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r6.length();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r6 != 0) goto L_0x0217;
    L_0x0109:
        r0 = "pluginseries";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = 0;
        com.qq.reader.appconfig.a.d.t = r3;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 == 0) goto L_0x012e;
    L_0x0115:
        r3 = r0.length();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 <= 0) goto L_0x012e;
    L_0x011b:
        r3 = com.qq.reader.appconfig.a.d.s(r13);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = "PLUGIN_DEFAULT_SERIES";
        r6 = r3.equals(r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r6 == 0) goto L_0x024e;
    L_0x0128:
        com.qq.reader.appconfig.a.d.f(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.d.e(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x012e:
        r0 = "sid";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0265, all -> 0x0241 }
    L_0x0139:
        com.qq.reader.appconfig.a.d.a(r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "qqnum";
        r0 = r5.optString(r0);	 Catch:{ Exception -> 0x02c8, all -> 0x0241 }
        r3 = com.qq.reader.appconfig.a.d.z(r13);	 Catch:{ Exception -> 0x02c8, all -> 0x0241 }
        if (r3 == 0) goto L_0x014f;
    L_0x0149:
        r3 = r3.length();	 Catch:{ Exception -> 0x02c8, all -> 0x0241 }
        if (r3 != 0) goto L_0x015d;
    L_0x014f:
        if (r0 == 0) goto L_0x015d;
    L_0x0151:
        r3 = "0";
        r3 = r3.equals(r0);	 Catch:{ Exception -> 0x02c8, all -> 0x0241 }
        if (r3 != 0) goto L_0x015d;
    L_0x015a:
        com.qq.reader.appconfig.a.d.j(r13, r0);	 Catch:{ Exception -> 0x02c8, all -> 0x0241 }
    L_0x015d:
        r0 = "colddataFinished";
        r0 = r5.optBoolean(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 != 0) goto L_0x0288;
    L_0x0166:
        r0 = r2;
    L_0x0167:
        com.qq.reader.appconfig.a.d.I(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "activedays";
        r2 = -1;
        r0 = r5.optInt(r0, r2);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.d.N(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "isnewuser";
        r0 = r5.optBoolean(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.d.J(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "otherLoginShow";
        r2 = 1;
        r0 = r5.optBoolean(r0, r2);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.common.login.define.a.a(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = "newslist";
        r5 = r5.optJSONArray(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r6 = r5.length();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r6 <= 0) goto L_0x02cb;
    L_0x0197:
        r0 = com.qq.reader.common.c.a.aC;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = com.qq.reader.common.utils.ao.b(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r0 == 0) goto L_0x01a8;
    L_0x019f:
        r2 = r0.exists();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r2 == 0) goto L_0x01a8;
    L_0x01a5:
        r0.delete();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
    L_0x01a8:
        r0 = com.qq.reader.common.c.a.aC;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = com.qq.reader.common.utils.ao.c(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x02bd, all -> 0x02b3 }
        r0 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x02bd, all -> 0x02b3 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x02bd, all -> 0x02b3 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x02bd, all -> 0x02b3 }
        r3.writeInt(r6);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r0 = "";
        r0 = "";
        r1 = r4;
    L_0x01c7:
        if (r1 >= r6) goto L_0x028b;
    L_0x01c9:
        r0 = r5.get(r1);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r4 = "url";
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r7 = "title";
        r7 = r0.getString(r7);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r8 = "type";
        r0 = r0.getInt(r8);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        if (r3 == 0) goto L_0x01ef;
    L_0x01e6:
        r3.writeUTF(r7);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r3.writeUTF(r4);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
        r3.writeInt(r0);	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
    L_0x01ef:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x01c7;
    L_0x01f3:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        goto L_0x009d;
    L_0x01f9:
        r0 = move-exception;
        r2 = r1;
    L_0x01fb:
        r0.printStackTrace();	 Catch:{ all -> 0x02ba }
        if (r2 == 0) goto L_0x0203;
    L_0x0200:
        r2.close();	 Catch:{ Exception -> 0x020a }
    L_0x0203:
        if (r1 == 0) goto L_0x0020;
    L_0x0205:
        r1.close();	 Catch:{ Exception -> 0x020a }
        goto L_0x0020;
    L_0x020a:
        r0 = move-exception;
        r1 = "RecommendMsgHandler";
        r2 = r0.toString();
        com.qq.reader.common.monitor.f.a(r1, r2, r0);
        goto L_0x0020;
    L_0x0217:
        r6 = r0.a();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.b = r6;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.a = r3;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r7 = r0.c();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.c = r7;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r7 = r0.b();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.d = r7;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.a(r13, r3);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.c(r13, r6);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r3 = r0.b();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.e(r13, r3);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = r0.c();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.appconfig.a.c.d(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        goto L_0x0109;
    L_0x0241:
        r0 = move-exception;
        r3 = r1;
    L_0x0243:
        if (r3 == 0) goto L_0x0248;
    L_0x0245:
        r3.close();	 Catch:{ Exception -> 0x02a7 }
    L_0x0248:
        if (r1 == 0) goto L_0x024d;
    L_0x024a:
        r1.close();	 Catch:{ Exception -> 0x02a7 }
    L_0x024d:
        throw r0;
    L_0x024e:
        r3 = r3.equals(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        if (r3 != 0) goto L_0x012e;
    L_0x0254:
        com.qq.reader.appconfig.a.d.e(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = com.qq.reader.appconfig.a.d.al(r13);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = r0 & -17;
        com.qq.reader.appconfig.a.d.t(r13, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = 1;
        com.qq.reader.appconfig.a.d.t = r0;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        goto L_0x012e;
    L_0x0265:
        r0 = move-exception;
        r6 = 0;
        r3 = "Update";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r8.<init>();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r9 = "ReadJson error :e = ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = r8.append(r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        com.qq.reader.common.monitor.f.b(r3, r0);	 Catch:{ Exception -> 0x01f9, all -> 0x0241 }
        goto L_0x0139;
    L_0x0288:
        r0 = r4;
        goto L_0x0167;
    L_0x028b:
        r3.flush();	 Catch:{ Exception -> 0x02c3, all -> 0x02b7 }
    L_0x028e:
        if (r3 == 0) goto L_0x0293;
    L_0x0290:
        r3.close();	 Catch:{ Exception -> 0x029a }
    L_0x0293:
        if (r2 == 0) goto L_0x0020;
    L_0x0295:
        r2.close();	 Catch:{ Exception -> 0x029a }
        goto L_0x0020;
    L_0x029a:
        r0 = move-exception;
        r1 = "RecommendMsgHandler";
        r2 = r0.toString();
        com.qq.reader.common.monitor.f.a(r1, r2, r0);
        goto L_0x0020;
    L_0x02a7:
        r1 = move-exception;
        r2 = "RecommendMsgHandler";
        r3 = r1.toString();
        com.qq.reader.common.monitor.f.a(r2, r3, r1);
        goto L_0x024d;
    L_0x02b3:
        r0 = move-exception;
        r3 = r1;
        r1 = r2;
        goto L_0x0243;
    L_0x02b7:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0243;
    L_0x02ba:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0243;
    L_0x02bd:
        r0 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x01fb;
    L_0x02c3:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x01fb;
    L_0x02c8:
        r0 = move-exception;
        goto L_0x015d;
    L_0x02cb:
        r2 = r1;
        r3 = r1;
        goto L_0x028e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.protocol.a.a(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask, java.lang.String, android.content.Context):void");
    }

    public static b a() {
        if (b == null || b.size() == 0) {
            b = c();
        }
        if (b == null || b.size() <= 0) {
            return null;
        }
        if (a < 0 || a >= b.size()) {
            a = 0;
        }
        b bVar = (b) b.get(a);
        a = (a + 1) % b.size();
        return bVar;
    }

    public static void b() {
        if (b != null) {
            b.clear();
            b = null;
        }
    }

    public static List<b> c() {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        DataInputStream dataInputStream = null;
        List<b> arrayList = new ArrayList();
        DataInputStream dataInputStream2;
        try {
            fileInputStream = new FileInputStream(ao.c(com.qq.reader.common.c.a.aC));
            try {
                dataInputStream2 = new DataInputStream(new BufferedInputStream(fileInputStream));
                try {
                    int readInt = dataInputStream2.readInt();
                    String str = "";
                    str = "";
                    for (int i = 0; i < readInt; i++) {
                        String readUTF = dataInputStream2.readUTF();
                        String readUTF2 = dataInputStream2.readUTF();
                        int readInt2 = dataInputStream2.readInt();
                        b bVar = new b(readUTF, readUTF2);
                        bVar.a(readInt2);
                        arrayList.add(bVar);
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e) {
                        }
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                } catch (Exception e2) {
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    dataInputStream = dataInputStream2;
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            throw th;
                        }
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                dataInputStream2 = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            dataInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
        return arrayList;
    }
}
