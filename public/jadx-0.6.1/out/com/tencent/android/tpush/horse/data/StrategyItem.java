package com.tencent.android.tpush.horse.data;

import android.text.TextUtils;
import com.tencent.qalsdk.core.c;
import java.io.Serializable;

/* compiled from: ProGuard */
public class StrategyItem implements Serializable {
    private static final long serialVersionUID = 1692027785653072243L;
    private int protocolType;
    private String proxyIp;
    private int proxyPort;
    private int redirect;
    private String serverIp;
    private int serverPort;

    public StrategyItem(String str, int i, String str2, int i2, int i3, int i4) {
        this.serverIp = str;
        this.serverPort = i;
        this.proxyIp = str2;
        this.proxyPort = i2;
        this.protocolType = i3;
        this.redirect = i4;
    }

    public String a() {
        return this.serverIp;
    }

    public int b() {
        return this.serverPort;
    }

    public String c() {
        return this.proxyIp;
    }

    public int d() {
        return this.protocolType;
    }

    public int e() {
        return this.proxyPort;
    }

    public int f() {
        return this.redirect;
    }

    public void a(int i) {
        this.redirect = i;
    }

    public boolean a(StrategyItem strategyItem) {
        return strategyItem != null && this.serverIp.equals(strategyItem.serverIp) && this.serverPort == strategyItem.serverPort && this.proxyIp.equals(strategyItem.proxyIp) && this.proxyPort == strategyItem.proxyPort && this.protocolType == strategyItem.protocolType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof StrategyItem) {
            return a((StrategyItem) obj);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.serverIp == null ? 0 : this.serverIp.hashCode()) + 31) * 31) + this.serverPort) * 31;
        if (this.proxyIp != null) {
            i = this.proxyIp.hashCode();
        }
        return (((((((hashCode + i) * 31) + this.proxyPort) * 31) + this.protocolType) * 31) + this.redirect) & Integer.MAX_VALUE;
    }

    public String toString() {
        return new StringBuffer("serverIp=").append(this.serverIp).append(",serverPort=").append(this.serverPort).append(", proxyIp=").append(this.proxyIp).append(",proxyPort=").append(this.proxyPort).append(", protocolType=").append(this.protocolType == 1 ? c.d : "tcp").append(", redirect=").append(this.redirect).toString();
    }

    public boolean g() {
        return (TextUtils.isEmpty(this.serverIp) || this.serverPort == 0) ? false : true;
    }

    public boolean h() {
        return !TextUtils.isEmpty(this.proxyIp);
    }

    public boolean i() {
        return this.protocolType == 1;
    }

    public boolean j() {
        return this.redirect == 1;
    }
}
