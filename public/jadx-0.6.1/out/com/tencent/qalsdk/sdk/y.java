package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.util.QLog;
import qalsdk.ax;
import qalsdk.ay;
import qalsdk.ba;
import qalsdk.bb;
import qalsdk.bc;
import qalsdk.bd;
import qalsdk.be;

/* compiled from: MsfRespHandleUtil */
public class y {
    public static final String a = "MsfRespHandleUtil";
    ay b;
    ba c;
    bd d;
    bb e;
    bc f;
    be g;

    public y(ax[] axVarArr) {
        if (axVarArr != null) {
            for (ax axVar : axVarArr) {
                if (axVar instanceof ay) {
                    this.b = (ay) axVar;
                } else if (axVar instanceof ba) {
                    this.c = (ba) axVar;
                } else if (axVar instanceof bd) {
                    this.d = (bd) axVar;
                } else if (axVar instanceof bb) {
                    this.e = (bb) axVar;
                } else if (axVar instanceof bc) {
                    this.f = (bc) axVar;
                } else if (axVar instanceof be) {
                    this.g = (be) axVar;
                }
            }
        }
    }

    public boolean a(FromServiceMsg fromServiceMsg) {
        try {
            boolean z;
            if (fromServiceMsg.getMsfCommand() == MsfCommand.onConnOpened) {
                NetConnInfoCenter.socketConnState = 2;
                if (this.g != null) {
                    this.g.b(fromServiceMsg);
                    z = true;
                }
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.onReceFirstResp) {
                NetConnInfoCenter.socketConnState = 4;
                if (this.g != null) {
                    this.g.d(fromServiceMsg);
                    z = true;
                }
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.onOepnConnAllFailed) {
                NetConnInfoCenter.socketConnState = 3;
                if (this.g != null) {
                    this.g.e(fromServiceMsg);
                    z = true;
                }
                z = false;
            } else {
                if (fromServiceMsg.getMsfCommand() == MsfCommand.onConnClosed) {
                    NetConnInfoCenter.socketConnState = 1;
                    if (this.g != null) {
                        this.g.c(fromServiceMsg);
                        z = true;
                    }
                }
                z = false;
            }
            if (fromServiceMsg.getMsfCommand() == MsfCommand.onRecvConfigPush) {
                if (this.g != null) {
                    this.g.a(fromServiceMsg);
                    z = true;
                }
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.registerPush) {
                if (this.e != null) {
                    this.e.a(null, fromServiceMsg);
                    z = true;
                }
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.onTicketChanged && this.e != null) {
                this.e.a();
                z = true;
            }
            if (z) {
                return z;
            }
            if (this.e == null) {
                return false;
            }
            this.e.a(fromServiceMsg);
            return true;
        } catch (Throwable e) {
            QLog.d(a, 1, "handle push msg error " + e, e);
            return true;
        }
    }

    public boolean a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        try {
            boolean z;
            fromServiceMsg.getResultCode();
            fromServiceMsg.getBusinessFailCode();
            fromServiceMsg.getBusinessFailMsg();
            if (fromServiceMsg.getMsfCommand() == MsfCommand.registerPush) {
                if (this.e != null) {
                    this.e.a(toServiceMsg, fromServiceMsg);
                    z = true;
                }
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.unRegisterPush) {
                if (this.e != null) {
                    this.e.b(toServiceMsg, fromServiceMsg);
                    z = true;
                }
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.registerCmdCallback) {
                if (this.e != null) {
                    this.e.c(toServiceMsg, fromServiceMsg);
                    z = true;
                }
                z = false;
            } else {
                if (fromServiceMsg.getMsfCommand() == MsfCommand.resetCmdCallback && this.e != null) {
                    this.e.d(toServiceMsg, fromServiceMsg);
                    z = true;
                }
                z = false;
            }
            if (z) {
                return z;
            }
            if (this.b == null) {
                return false;
            }
            this.b.a(toServiceMsg, fromServiceMsg);
            return true;
        } catch (Throwable e) {
            QLog.d(a, 1, "handle resp msg error " + e, e);
            return true;
        }
    }
}
