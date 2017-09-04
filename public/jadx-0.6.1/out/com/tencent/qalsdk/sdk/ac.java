package com.tencent.qalsdk.sdk;

import android.content.Context;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.util.QLog;
import qalsdk.aw;

/* compiled from: MsfServiceSdk */
public class ac extends ad {
    private static ac h = new ac();
    private static final String i = "MsfServiceSdk";
    z a;
    public volatile boolean b = false;

    public /* bridge */ /* synthetic */ void a(String str, int i, String str2, aw awVar) {
        super.a(str, i, str2, awVar);
    }

    public /* bridge */ /* synthetic */ ae j() {
        return super.j();
    }

    public /* bridge */ /* synthetic */ String k() {
        return super.k();
    }

    public /* bridge */ /* synthetic */ aw l() {
        return super.l();
    }

    public /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    public /* bridge */ /* synthetic */ w n() {
        return super.n();
    }

    private ac() {
    }

    public static ac a() {
        return h;
    }

    public void a(Context context, int i, String str, String str2, aw awVar, String str3) {
        this.a = new z(str2);
        this.a.a(this);
        super.a(str3, i, str, awVar);
        this.b = true;
    }

    public String b() {
        if (this.b) {
            return this.a.a.d;
        }
        this.a = new z("com.tencent.mobileqq.msf.service.MsfService");
        this.a.a(this);
        this.b = true;
        this.c = 88886666;
        return this.a.a.d;
    }

    public void c() {
        this.a.a();
        this.a.b();
    }

    public void d() {
        this.a.a();
    }

    public void e() {
        this.a.b();
    }

    public void f() {
        this.a.f();
    }

    public void g() {
        this.a.a(Boolean.valueOf(true));
    }

    public void a(Boolean bool) {
        this.a.a(bool);
    }

    public void h() {
        this.a.e();
        this.a.g();
    }

    public void i() {
        this.a.c();
    }

    public void a(ToServiceMsg toServiceMsg) {
        this.a.b(toServiceMsg);
    }

    public ToServiceMsg a(c cVar) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(b(), cVar.a, a.N);
        toServiceMsg.setMsfCommand(MsfCommand.registerCmdCallback);
        ai.a(toServiceMsg, cVar);
        c(toServiceMsg);
        return toServiceMsg;
    }

    public ToServiceMsg a(String str, c cVar) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(b(), str, a.Q);
        toServiceMsg.setMsfCommand(MsfCommand.resetCmdCallback);
        ai.a(toServiceMsg, cVar);
        c(toServiceMsg);
        return toServiceMsg;
    }

    public ToServiceMsg a(int i, String str, String str2) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(b(), str, a.S);
        toServiceMsg.setMsfCommand(MsfCommand.proxyUnRegisterPush);
        toServiceMsg.getAttributes().put(toServiceMsg.getServiceCmd(), str2);
        c(toServiceMsg);
        return toServiceMsg;
    }

    public int b(ToServiceMsg toServiceMsg) {
        if (toServiceMsg == null) {
            return -1;
        }
        QLog.d(i, "sdk sendMsg. ssoCmd:" + toServiceMsg.getServiceCmd() + " msfCmd:" + toServiceMsg.getMsfCommand() + " appSeq:" + toServiceMsg.getAppSeq());
        if (toServiceMsg.getServiceName().equals(b())) {
            return this.a.c(toServiceMsg);
        }
        return b.a(toServiceMsg);
    }

    private void c(ToServiceMsg toServiceMsg) {
        if (toServiceMsg != null) {
            toServiceMsg.setAppId(this.c);
            toServiceMsg.setTimeout(a.ap);
            MsfSdkUtils.addToMsgProcessName(this.e, toServiceMsg);
        }
    }

    public ToServiceMsg a(String str) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(b(), str, a.W);
        toServiceMsg.setMsfCommand(MsfCommand.refreshTickets);
        c(toServiceMsg);
        return toServiceMsg;
    }

    public ToServiceMsg b(String str) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(b(), str, "ConfigService.ClientReq");
        toServiceMsg.setMsfCommand(MsfCommand.getPluginConfig);
        c(toServiceMsg);
        return toServiceMsg;
    }
}
