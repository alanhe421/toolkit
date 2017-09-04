package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.b.a;

/* compiled from: ProGuard */
public class i {
    private long a = -1;
    private long b = -1;
    private long c = -1;
    private String d = "";
    private long e = -1;
    private long f = -1;
    private Context g = null;
    private Intent h = null;
    private a i = null;

    private i(Context context, Intent intent) {
        this.g = context;
        this.h = intent;
    }

    public static i a(Context context, Intent intent) {
        i iVar = new i(context, intent);
        String decrypt = Rijndael.decrypt(intent.getStringExtra(MessageKey.MSG_CONTENT));
        iVar.d = decrypt;
        iVar.a = intent.getLongExtra(MessageKey.MSG_ID, -1);
        iVar.b = intent.getLongExtra("accId", -1);
        iVar.c = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1);
        iVar.e = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1);
        iVar.f = intent.getLongExtra("type", -1);
        a aVar = null;
        switch ((int) iVar.f) {
            case 1:
                aVar = new d(decrypt);
                break;
            case 2:
                aVar = new j(decrypt);
                break;
            case 3:
                a.a().a(context, context.getPackageName(), decrypt);
                XGPushManager.msgAck(context, iVar);
                break;
            default:
                com.tencent.android.tpush.a.a.h(Constants.LogTag, "error type for message, drop it, type:" + iVar.f + ",intent:" + intent);
                XGPushManager.msgAck(context, iVar);
                break;
        }
        if (aVar != null) {
            iVar.i = aVar;
            iVar.i.b();
        }
        return iVar;
    }

    public void a() {
        if (this.i.c() == 1) {
            b.b(this.g, this);
        }
    }

    public long b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return this.e;
    }

    public String f() {
        return this.d;
    }

    public a g() {
        return this.i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PushMessageManager [msgId=").append(this.a).append(", accessId=").append(this.b).append(", busiMsgId=").append(this.c).append(", content=").append(this.d).append(", timestamps=").append(this.e).append(", type=").append(this.f).append(", intent=").append(this.h).append(", messageHolder=").append(this.i).append("]");
        return stringBuilder.toString();
    }
}
