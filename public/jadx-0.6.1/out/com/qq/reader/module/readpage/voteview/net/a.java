package com.qq.reader.module.readpage.voteview.net;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType;
import org.json.JSONArray;

/* compiled from: GetVoteUserIconsHandler */
public class a implements c {
    private final String a = getClass().getSimpleName();
    private Handler b;
    private Context c;
    private ViewType d;
    private String e;
    private String f;
    private String g;
    private String h;
    private JSONArray i;
    private JSONArray j;
    private JSONArray k;

    public a(Context context, Handler handler, ViewType viewType, String str) {
        this.c = context;
        this.b = handler;
        this.d = viewType;
        this.e = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask r5, java.lang.String r6, long r7) {
        /*
        r4 = this;
        r0 = r4.a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "onConnectionRecieveData -->";
        r1 = r1.append(r2);
        r1 = r1.append(r6);
        r1 = r1.toString();
        com.qq.reader.common.monitor.f.d(r0, r1);
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0154 }
        r0.<init>(r6);	 Catch:{ Exception -> 0x0154 }
        r1 = "time";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0154 }
        r2 = r4.d;	 Catch:{ Exception -> 0x0154 }
        r3 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.b(r1, r2, r3);	 Catch:{ Exception -> 0x0154 }
        r1 = "reward";
        r1 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0154 }
        r2 = "mticket";
        r2 = r0.optJSONObject(r2);	 Catch:{ Exception -> 0x0154 }
        r3 = "rticket";
        r0 = r0.optJSONObject(r3);	 Catch:{ Exception -> 0x0154 }
        if (r1 == 0) goto L_0x0055;
    L_0x0043:
        r3 = "rewardIcons";
        r3 = r1.optJSONArray(r3);	 Catch:{ Exception -> 0x0154 }
        r4.i = r3;	 Catch:{ Exception -> 0x0154 }
        r3 = "rewardCount";
        r1 = r1.getString(r3);	 Catch:{ Exception -> 0x0154 }
        r4.f = r1;	 Catch:{ Exception -> 0x0154 }
    L_0x0055:
        if (r2 == 0) goto L_0x0069;
    L_0x0057:
        r1 = "mticketIcons";
        r1 = r2.optJSONArray(r1);	 Catch:{ Exception -> 0x0154 }
        r4.j = r1;	 Catch:{ Exception -> 0x0154 }
        r1 = "mticketCount";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x0154 }
        r4.g = r1;	 Catch:{ Exception -> 0x0154 }
    L_0x0069:
        if (r0 == 0) goto L_0x007d;
    L_0x006b:
        r1 = "rticketIcons";
        r1 = r0.optJSONArray(r1);	 Catch:{ Exception -> 0x0154 }
        r4.k = r1;	 Catch:{ Exception -> 0x0154 }
        r1 = "rticketCount";
        r0 = r0.getString(r1);	 Catch:{ Exception -> 0x0154 }
        r4.h = r0;	 Catch:{ Exception -> 0x0154 }
    L_0x007d:
        r0 = r4.i;	 Catch:{ Exception -> 0x0154 }
        if (r0 != 0) goto L_0x0091;
    L_0x0081:
        r0 = r4.j;	 Catch:{ Exception -> 0x0154 }
        if (r0 != 0) goto L_0x0091;
    L_0x0085:
        r0 = r4.k;	 Catch:{ Exception -> 0x0154 }
        if (r0 != 0) goto L_0x0091;
    L_0x0089:
        r0 = 1;
        r1 = "";
        r4.a(r0, r1);	 Catch:{ Exception -> 0x0154 }
    L_0x0090:
        return;
    L_0x0091:
        r0 = com.qq.reader.module.readpage.voteview.net.a.AnonymousClass1.a;	 Catch:{ Exception -> 0x0154 }
        r1 = r4.d;	 Catch:{ Exception -> 0x0154 }
        r1 = r1.ordinal();	 Catch:{ Exception -> 0x0154 }
        r0 = r0[r1];	 Catch:{ Exception -> 0x0154 }
        switch(r0) {
            case 1: goto L_0x0160;
            case 2: goto L_0x0170;
            case 3: goto L_0x0180;
            default: goto L_0x009e;
        };	 Catch:{ Exception -> 0x0154 }
    L_0x009e:
        r0 = r4.i;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x00d6;
    L_0x00a2:
        r0 = r4.i;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0154 }
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.REWARD;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.a(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = "-1";
        r1 = r4.f;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x0190;
    L_0x00ba:
        r0 = "0";
    L_0x00bd:
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.REWARD;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.c(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = com.qq.reader.appconfig.a.d.o;	 Catch:{ NumberFormatException -> 0x0194 }
        if (r0 != 0) goto L_0x00d6;
    L_0x00c8:
        r0 = r4.f;	 Catch:{ NumberFormatException -> 0x0194 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0194 }
        if (r0 <= 0) goto L_0x00d6;
    L_0x00d0:
        r0 = r4.c;	 Catch:{ NumberFormatException -> 0x0194 }
        r1 = 1;
        com.qq.reader.appconfig.a.d.j(r0, r1);	 Catch:{ NumberFormatException -> 0x0194 }
    L_0x00d6:
        r0 = r4.j;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x010e;
    L_0x00da:
        r0 = r4.j;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0154 }
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.MONTHTICKET;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.a(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = "-1";
        r1 = r4.g;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x01a0;
    L_0x00f2:
        r0 = "0";
    L_0x00f5:
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.MONTHTICKET;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.c(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = com.qq.reader.appconfig.a.d.o;	 Catch:{ NumberFormatException -> 0x01a4 }
        if (r0 != 0) goto L_0x010e;
    L_0x0100:
        r0 = r4.g;	 Catch:{ NumberFormatException -> 0x01a4 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x01a4 }
        if (r0 <= 0) goto L_0x010e;
    L_0x0108:
        r0 = r4.c;	 Catch:{ NumberFormatException -> 0x01a4 }
        r1 = 1;
        com.qq.reader.appconfig.a.d.j(r0, r1);	 Catch:{ NumberFormatException -> 0x01a4 }
    L_0x010e:
        r0 = r4.k;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x0090;
    L_0x0112:
        r0 = r4.k;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0154 }
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.RECOMMENT;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.a(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = "-1";
        r1 = r4.h;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x01b0;
    L_0x012a:
        r0 = "0";
    L_0x012d:
        r1 = com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType.RECOMMENT;	 Catch:{ Exception -> 0x0154 }
        r2 = r4.e;	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.module.readpage.voteview.net.b.c(r0, r1, r2);	 Catch:{ Exception -> 0x0154 }
        r0 = com.qq.reader.appconfig.a.d.o;	 Catch:{ NumberFormatException -> 0x0148 }
        if (r0 != 0) goto L_0x0090;
    L_0x0138:
        r0 = r4.h;	 Catch:{ NumberFormatException -> 0x0148 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0148 }
        if (r0 <= 0) goto L_0x0090;
    L_0x0140:
        r0 = r4.c;	 Catch:{ NumberFormatException -> 0x0148 }
        r1 = 1;
        com.qq.reader.appconfig.a.d.j(r0, r1);	 Catch:{ NumberFormatException -> 0x0148 }
        goto L_0x0090;
    L_0x0148:
        r0 = move-exception;
        r1 = r4.a;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0154 }
        goto L_0x0090;
    L_0x0154:
        r0 = move-exception;
        r1 = r4.a;
        r0 = r0.getMessage();
        com.qq.reader.common.monitor.f.a(r1, r0);
        goto L_0x0090;
    L_0x0160:
        r0 = r4.i;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x009e;
    L_0x0164:
        r0 = 2;
        r1 = r4.i;	 Catch:{ Exception -> 0x0154 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0154 }
        r4.a(r0, r1);	 Catch:{ Exception -> 0x0154 }
        goto L_0x009e;
    L_0x0170:
        r0 = r4.j;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x009e;
    L_0x0174:
        r0 = 2;
        r1 = r4.j;	 Catch:{ Exception -> 0x0154 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0154 }
        r4.a(r0, r1);	 Catch:{ Exception -> 0x0154 }
        goto L_0x009e;
    L_0x0180:
        r0 = r4.k;	 Catch:{ Exception -> 0x0154 }
        if (r0 == 0) goto L_0x009e;
    L_0x0184:
        r0 = 2;
        r1 = r4.k;	 Catch:{ Exception -> 0x0154 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0154 }
        r4.a(r0, r1);	 Catch:{ Exception -> 0x0154 }
        goto L_0x009e;
    L_0x0190:
        r0 = r4.f;	 Catch:{ Exception -> 0x0154 }
        goto L_0x00bd;
    L_0x0194:
        r0 = move-exception;
        r1 = r4.a;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0154 }
        goto L_0x00d6;
    L_0x01a0:
        r0 = r4.g;	 Catch:{ Exception -> 0x0154 }
        goto L_0x00f5;
    L_0x01a4:
        r0 = move-exception;
        r1 = r4.a;	 Catch:{ Exception -> 0x0154 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0154 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0154 }
        goto L_0x010e;
    L_0x01b0:
        r0 = r4.h;	 Catch:{ Exception -> 0x0154 }
        goto L_0x012d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.readpage.voteview.net.a.onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask, java.lang.String, long):void");
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        f.a(this.a, "onConnectionError -->" + exception.getMessage());
        a(1, "");
    }

    private void a(int i, String str) {
        Message obtainMessage = this.b.obtainMessage();
        switch (i) {
            case 1:
                obtainMessage.what = 106;
                break;
            case 2:
                Bundle bundle = new Bundle();
                bundle.putString("data", str);
                obtainMessage.setData(bundle);
                obtainMessage.what = 105;
                break;
        }
        this.b.sendMessage(obtainMessage);
    }
}
