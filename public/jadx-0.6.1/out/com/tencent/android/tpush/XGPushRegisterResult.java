package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ProGuard */
public class XGPushRegisterResult implements XGIResult {
    long a = 0;
    String b = "";
    String c = "";
    String d = "";
    short e = (short) 0;
    String f = "";

    public long getAccessId() {
        return this.a;
    }

    public String getDeviceId() {
        return this.b;
    }

    public String getAccount() {
        return this.c;
    }

    public String getTicket() {
        return this.d;
    }

    public short getTicketType() {
        return this.e;
    }

    public String getToken() {
        return this.f;
    }

    XGPushRegisterResult() {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TPushRegisterMessage [accessId=").append(this.a).append(", deviceId=").append(this.b).append(", account=").append(this.c).append(", ticket=").append(this.d).append(", ticketType=").append(this.e).append(", token=").append(this.f).append("]");
        return stringBuilder.toString();
    }

    public void parseIntent(Intent intent) {
        this.a = intent.getLongExtra("accId", -1);
        this.b = intent.getStringExtra(Constants.FLAG_DEVICE_ID);
        this.c = intent.getStringExtra(Constants.FLAG_ACCOUNT);
        this.d = intent.getStringExtra(Constants.FLAG_TICKET);
        this.e = intent.getShortExtra(Constants.FLAG_TICKET_TYPE, (short) 0);
        this.f = intent.getStringExtra(Constants.FLAG_TOKEN);
    }
}
