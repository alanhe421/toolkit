package com.tencent.av;

public class PingResult {
    int receivePkg;
    ServerInfo server;
    int totalPkg;
    int useTime;

    public int getReceivePkg() {
        return this.receivePkg;
    }

    public ServerInfo getServer() {
        return this.server;
    }

    public int getTotalPkg() {
        return this.totalPkg;
    }

    public int getUseTime() {
        return this.useTime;
    }

    public void setReceivePkg(int i) {
        this.receivePkg = i;
    }

    public void setServer(ServerInfo serverInfo) {
        this.server = serverInfo;
    }

    public void setTotalPkg(int i) {
        this.totalPkg = i;
    }

    public void setUseTime(int i) {
        this.useTime = i;
    }
}
