package com.tencent.av;

import java.net.InetAddress;

public class ServerInfo {
    public IDC idc;
    private byte idcNo;
    public InetAddress ip;
    public ServerType isp;
    private byte ispNo;
    public short port;

    public enum IDC {
        UNKNOW,
        SH,
        SZ,
        CD,
        TJ,
        NJ,
        HZ,
        GZ
    }

    public enum ServerType {
        UNKNOW,
        TEL,
        CNC,
        CMCC
    }

    protected byte getIdcNo() {
        return this.idcNo;
    }

    protected byte getIspNo() {
        return this.ispNo;
    }

    protected void setIDC(byte b) {
        this.idcNo = b;
        switch (b) {
            case (byte) 1:
                this.idc = IDC.SH;
                return;
            case (byte) 2:
                this.idc = IDC.SZ;
                return;
            case (byte) 3:
                this.idc = IDC.CD;
                return;
            case (byte) 4:
                this.idc = IDC.TJ;
                return;
            case (byte) 5:
                this.idc = IDC.NJ;
                return;
            case (byte) 6:
                this.idc = IDC.HZ;
                return;
            case (byte) 7:
                this.idc = IDC.GZ;
                return;
            default:
                this.idc = IDC.UNKNOW;
                return;
        }
    }

    protected void setISP(byte b) {
        this.ispNo = b;
        switch (b) {
            case (byte) 2:
                this.isp = ServerType.TEL;
                return;
            case (byte) 3:
                this.isp = ServerType.CNC;
                return;
            case (byte) 5:
                this.isp = ServerType.CMCC;
                return;
            default:
                this.isp = ServerType.UNKNOW;
                return;
        }
    }
}
