package com.tencent.android.tpush.service.channel.b;

import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: ProGuard */
public abstract class i extends f {
    static final /* synthetic */ boolean o = (!i.class.desiredAssertionStatus());
    protected short d;
    protected int e;
    protected long f;
    protected long g;
    protected short h;
    protected short i;
    protected short k;
    protected short l;
    protected short m;
    protected byte[] n = new byte[0];

    public boolean e() {
        return (this.h & 128) != 0;
    }

    public byte f() {
        return (byte) (this.h & Opcodes.NEG_FLOAT);
    }

    public String g() {
        switch (f()) {
            case (byte) 1:
                return "TPNS_COMMAND_PUSH";
            case (byte) 2:
                return "TPNS_COMMAND_GET_APLIST";
            case (byte) 3:
                return "TPNS_COMMAND_PULLCONFIG";
            case (byte) 4:
                return "TPNS_COMMAND_REGISTER";
            case (byte) 5:
                return "TPNS_COMMAND_UNREGISTER";
            case (byte) 6:
                return "TPNS_COMMAND_RECONNECT";
            case (byte) 7:
                return "TPNS_COMMAND_HEARTBEAT";
            case (byte) 8:
                return "TPNS_COMMAND_UNINSTALL";
            case (byte) 9:
                return "TPNS_COMMAND_CLIENTREPORT";
            case (byte) 10:
                return "TPNS_COMMAND_REDIRECT";
            case (byte) 11:
                return "TPNS_COMMAND_PUSH_VERIFY";
            case (byte) 12:
                return "TPNS_COMMAND_SPEEDTEST";
            case (byte) 13:
                return "TPNS_COMMAND_TRIGGER_REPORT";
            case (byte) 14:
                return "TPNS_COMMAND_CHECK_MSG";
            case (byte) 15:
                return "TPNS_COMMAND_TAG";
            case (byte) 16:
                return "TPNS_COMMAND_PUSH_CLICK";
            case (byte) 17:
                return "TPNS_COMMAND_UPDATE_OTH_TOKEN";
            default:
                return "TPNS_COMMAND_UNKNOWN " + f();
        }
    }

    public short h() {
        return this.h;
    }

    public void a(short s) {
        this.h = s;
    }

    public int i() {
        return this.e;
    }

    public short j() {
        return this.k;
    }

    public void b(short s) {
        this.k = s;
    }

    public byte[] k() {
        return this.n;
    }

    public void a(byte[] bArr) {
        this.n = bArr;
    }

    public short l() {
        return this.m;
    }

    public String toString() {
        return new StringBuffer(getClass().getSimpleName()).append("(p:").append(this.k).append("|v:").append(this.l).append("|r:").append(this.g).append("|s:").append(this.e).append("|c:").append(Integer.toHexString(this.h)).append("|r:").append(this.m).append("|l:").append(this.f).append(")").toString();
    }

    public String m() {
        return new StringBuffer(getClass().getSimpleName()).append(" protocol = ").append(this.k).append(" command = ").append(g()).append(" isResponse = ").append(e()).append(" packetLength = ").append(this.f).toString();
    }
}
