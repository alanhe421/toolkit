package qalsdk;

import android.os.Build;
import android.os.Build.VERSION;
import com.dynamicload.Lib.DLConstants;
import com.pay.http.APPluginErrorCode;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.av.sdk.AVError;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.im_open.stat_hello;
import com.tencent.qalsdk.im_open.stat_reg.ReqBody;
import com.tencent.qalsdk.im_open.stat_reg.RspBody;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.service.g;
import com.tencent.qalsdk.util.QLog;
import java.nio.ByteBuffer;
import java.util.Locale;

/* compiled from: PushCoder */
public class ai {
    public static final String a = "MSF.C.PushManager:PushCoder";
    public static String e = "im_open_status.stat_hello";
    private static byte h = (byte) 0;
    aj b;
    long c = 0;
    int d = 0;
    long f = 0;
    int g = 0;
    private final String i = "unrtime";

    public ai(aj ajVar) {
        this.b = ajVar;
    }

    boolean a(am amVar) {
        return amVar == am.appRegister || amVar == am.fillRegProxy || amVar == am.createDefaultRegInfo;
    }

    void a(String str, long j) {
        QalService.context.getSharedPreferences("unrtime", 0).edit().putLong(str, j).commit();
    }

    long a(String str) {
        return QalService.context.getSharedPreferences("unrtime", 0).getLong(str, 0);
    }

    void a(ah ahVar, ToServiceMsg toServiceMsg, boolean z, am amVar) {
        if (ahVar.k != null) {
            boolean a = a(amVar);
            if (!d.t() || d.a(ahVar.k.a)) {
                if (a || z || ahVar.o != (byte) 1 || System.currentTimeMillis() - ahVar.e >= 30000) {
                    if (z) {
                        try {
                            a(toServiceMsg.getUin(), System.currentTimeMillis());
                        } catch (Throwable e) {
                            QLog.d(a, 1, "get reginterv error " + e, e);
                        }
                    } else if (toServiceMsg != null) {
                        if (a(toServiceMsg.getUin()) != 0) {
                            a(toServiceMsg.getUin(), 0);
                        }
                    }
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "ConfigManager.isAutoStarting():" + d.t());
                    }
                    try {
                        ReqBody reqBody = new ReqBody();
                        reqBody.uint64_bid.set(ahVar.c);
                        reqBody.uint32_conn_type.set(h);
                        reqBody.uint32_status.set(ahVar.k.c);
                        reqBody.uint32_kick_pc.set(ahVar.k.d);
                        reqBody.uint32_timestamp.set((int) ahVar.k.f);
                        PBUInt32Field pBUInt32Field = reqBody.uint32_regtype;
                        int i = (amVar == am.appRegister || amVar == am.fillRegProxy || amVar == am.createDefaultRegInfo) ? 0 : 1;
                        pBUInt32Field.set((byte) i);
                        reqBody.bytes_guid.set(ByteStringMicro.copyFrom(j.a().c()));
                        reqBody.str_dev_name.set(Build.MODEL);
                        reqBody.str_dev_type.set(Build.MODEL);
                        reqBody.str_os_ver.set(VERSION.RELEASE);
                        reqBody.str_build_ver.set(VERSION.SDK);
                        reqBody.vender_appid.set(0);
                        reqBody.uint64_tinyid.set(Long.parseLong(ahVar.k.a));
                        String str = Build.MANUFACTURER;
                        i = AVError.AV_ERR_TRY_NEW_ROOM_FAILED;
                        if (str.toLowerCase(Locale.ENGLISH).contains("xiaomi")) {
                            i = APPluginErrorCode.ERROR_APP_SYSTEM;
                        } else if (str.toLowerCase(Locale.ENGLISH).contains("huawei")) {
                            i = 2001;
                        }
                        reqBody.uint32_inst_type.set(i);
                        byte[] toByteArray = reqBody.toByteArray();
                        ToServiceMsg toServiceMsg2 = new ToServiceMsg("", ahVar.k.a, a.M);
                        toServiceMsg2.setAppId(ahVar.a);
                        if (toServiceMsg != null) {
                            toServiceMsg2.setAppSeq(toServiceMsg.getAppSeq());
                            toServiceMsg2.addAttribute(v.i, toServiceMsg.getServiceCmd());
                        } else {
                            toServiceMsg2.addAttribute(v.i, a.M);
                        }
                        toServiceMsg2.setRequestSsoSeq(j.f());
                        toServiceMsg2.putWupBuffer(o.b(toByteArray));
                        toServiceMsg2.setTimeout(a.ap);
                        toServiceMsg2.addAttribute("regPushReason", amVar.toString());
                        if (z) {
                            toServiceMsg2.setMsfCommand(MsfCommand._msf_UnRegPush);
                        } else {
                            toServiceMsg2.setMsfCommand(MsfCommand._msf_RegPush);
                        }
                        MsfSdkUtils.addToMsgProcessName(ahVar.b, toServiceMsg2);
                        this.c = System.currentTimeMillis();
                        if (amVar == am.appRegister) {
                            toServiceMsg2.setActionListener(toServiceMsg.getActionListener());
                        } else if (amVar == am.setAppQuit) {
                            toServiceMsg2.setNeedCallback(false);
                        }
                        this.b.b.a(toServiceMsg2);
                        ahVar.e = System.currentTimeMillis();
                        if (z) {
                            QLog.i(a, 1, "send unregister.push id " + ahVar.c + " pushStatus:" + ahVar.k.c + " bRegType:" + reqBody.uint32_regtype.get());
                        } else {
                            ahVar.o = (byte) 1;
                            QLog.i(a, 1, "send register. push id " + ahVar.c + " pushStatus:" + ahVar.k.c + " bRegType:" + reqBody.uint32_regtype.get() + " timeStamp:" + reqBody.uint32_timestamp.get() + ":" + ahVar.e);
                        }
                    } catch (Throwable e2) {
                        QLog.d(a, 1, "send registerPush error " + e2, e2);
                    }
                    this.b.a();
                    return;
                }
                QLog.i(a, 1, "is in registting, no need send register again, return. ");
            } else if (QLog.isColorLevel()) {
                QLog.d(a, 2, ahVar.k.a + " is booting and can not autoRegisterPush,return.");
            }
        }
    }

    void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        Object obj;
        long j;
        RspBody rspBody;
        int i;
        String str;
        ((Long) toServiceMsg.getAttribute(a.aH)).longValue();
        String str2 = ConfigBaseParser.DEFAULT_VALUE;
        String str3 = g.b(toServiceMsg) + toServiceMsg.getUin();
        int i2 = -1;
        RspBody rspBody2;
        try {
            if (!fromServiceMsg.isSuccess()) {
                obj = null;
                j = -1;
                rspBody = null;
                i = -1;
            } else if (fromServiceMsg.getWupBuffer() == null || fromServiceMsg.getWupBuffer().length < 5) {
                obj = null;
                j = -1;
                rspBody = null;
                i = -1;
            } else {
                rspBody2 = new RspBody();
                Object wupBuffer = fromServiceMsg.getWupBuffer();
                obj = new byte[(wupBuffer.length - 4)];
                System.arraycopy(wupBuffer, 4, obj, 0, wupBuffer.length - 4);
                rspBody2.mergeFrom((byte[]) obj);
                o.b(rspBody2.str_client_ip.get());
                o.b(rspBody2.uint32_client_port.get());
                j.t = rspBody2.str_client_ip.get();
                i2 = (byte) rspBody2.uint32_update_flag.get();
                long j2 = (long) rspBody2.uint32_timestamp.get();
                byte b = (byte) rspBody2.enum_cmd_error_code.uint32_code.get();
                if (b != (byte) 0) {
                    QLog.e(a, 1, "register pushresp error code: " + rspBody2.enum_cmd_error_code.uint32_code.get() + "msg:" + rspBody2.str_errmsg.get() + "sso seq:" + fromServiceMsg.getRequestSsoSeq());
                    wupBuffer = null;
                } else {
                    int i3 = 1;
                }
                NetConnInfoCenter.handleGetServerTimeResp((long) rspBody2.uint32_server_time.get());
                if (QLog.isDevelopLevel()) {
                    QLog.d(a, 4, "pushresp servertime is " + rspBody2.uint32_server_time.get() + this.b.b.n.format(Integer.valueOf(rspBody2.uint32_server_time.get() * 1000)));
                }
                obj = wupBuffer;
                long j3 = j2;
                rspBody = rspBody2;
                byte b2 = b;
                j = j3;
            }
        } catch (Throwable e) {
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "decodeRegisterPushResp error", e);
            }
            obj = null;
            rspBody = rspBody2;
            i = -1;
            j = -1;
        } catch (Throwable e2) {
            this.b.a(this.b.e(), str3);
            e2.printStackTrace();
            return;
        }
        if (toServiceMsg == null || !toServiceMsg.getAttributes().containsKey("regPushReason")) {
            str = str2;
        } else {
            str = (String) toServiceMsg.getAttribute("regPushReason");
        }
        if (obj != null) {
            this.d = 0;
            this.f = System.currentTimeMillis();
            if (toServiceMsg.getMsfCommand() == MsfCommand._msf_UnRegPush) {
                QLog.d(a, 1, "handlerPush unregister push succ");
            } else {
                QLog.d(a, 1, "handlerPush register push succ  bUpdateFlag:" + i2 + " timeStamp:" + j + " cReplyCode:" + i);
            }
            if (this.b.g.containsKey(str3)) {
                ah ahVar = (ah) this.b.g.get(str3);
                if (ahVar != null) {
                    ahVar.o = (byte) 0;
                    ahVar.i = this.b.b.v;
                    ahVar.f = System.currentTimeMillis();
                    if (rspBody != null) {
                        if (QLog.isColorLevel()) {
                            QLog.d(a, 2, "recv register push resp.iInterval=" + rspBody.uint32_hello_interval.get() + ", queryIntervTime=" + this.b.e() + ", next query time is " + aj.f.format(Long.valueOf(System.currentTimeMillis() + ((long) (rspBody.uint32_hello_interval.get() * 1000)))));
                        }
                        this.b.r = (long) (rspBody.uint32_hello_interval.get() * 1000);
                        if (toServiceMsg.getMsfCommand() == MsfCommand._msf_RegPush && ahVar.k != null) {
                            Boolean bool = (Boolean) this.b.h.get(ahVar.k.a);
                            if (bool == null || !bool.booleanValue()) {
                                this.b.a(this.b.e(), str3);
                            }
                        }
                    } else {
                        if (QLog.isColorLevel()) {
                            QLog.d(a, 2, "recv null register push resp, use default intervTime " + this.b.e());
                        }
                        this.b.a(this.b.e(), str3);
                    }
                }
                this.b.h.put(toServiceMsg.getUin(), Boolean.valueOf(true));
            } else {
                QLog.e(a, "onRecePushRegisterResp no key:" + str3);
                return;
            }
        }
        this.b.h.put(toServiceMsg.getUin(), Boolean.valueOf(false));
        if (toServiceMsg.getMsfCommand() == MsfCommand._msf_RegPush) {
            fromServiceMsg.setMsfCommand(MsfCommand.registerPush);
            fromServiceMsg.addAttribute(v.J, f.b());
            fromServiceMsg.addAttribute(v.K, Integer.valueOf(f.c()));
            fromServiceMsg.addAttribute(v.L, this.b.b.d.a.o().e());
            fromServiceMsg.addAttribute(v.M, j.t);
        } else if (toServiceMsg.getMsfCommand() == MsfCommand._msf_UnRegPush) {
            fromServiceMsg.setMsfCommand(MsfCommand.unRegisterPush);
        } else {
            fromServiceMsg.setMsfCommand(toServiceMsg.getMsfCommand());
        }
        if (!str.equals("appRegister")) {
            MsfSdkUtils.addFromMsgProcessName(g.b(toServiceMsg), fromServiceMsg);
            this.b.b.a(null, fromServiceMsg);
        } else if (toServiceMsg.getAttributes().containsKey(v.i)) {
            this.b.b.a(toServiceMsg, fromServiceMsg);
        }
    }

    void a(String str, ah ahVar, boolean z) {
        if (d.i && !z) {
            long d = this.b.d(ahVar.k.a) + this.b.e();
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "sendMsgPushQuery, check time now=" + aj.f.format(Long.valueOf(System.currentTimeMillis())) + ", shouldSendQueryTime=" + aj.f.format(Long.valueOf(d)));
            }
            if (System.currentTimeMillis() < d) {
                this.b.a(d - System.currentTimeMillis(), str);
                return;
            }
        }
        if (ahVar.k == null) {
            return;
        }
        if (ahVar.k.a != null && ahVar.c > 0) {
            String str2 = this.b.c + DLConstants.DEPENDENCY_PACKAGE_DIV + m.n() + DLConstants.DEPENDENCY_PACKAGE_DIV + m.l() + DLConstants.DEPENDENCY_PACKAGE_DIV + (System.currentTimeMillis() - this.f);
            stat_hello.ReqBody reqBody = new stat_hello.ReqBody();
            reqBody.uint32_status.set(ahVar.k.c);
            reqBody.bytes_guid.set(ByteStringMicro.copyFrom(j.a().c()));
            reqBody.str_dev_name.set(Build.MODEL);
            reqBody.str_dev_type.set(Build.MODEL);
            reqBody.str_os_ver.set(VERSION.RELEASE);
            reqBody.str_firmware_ver.set("");
            reqBody.vender_appid.set(0);
            ToServiceMsg toServiceMsg = new ToServiceMsg("", ahVar.k.a, e);
            byte[] toByteArray = reqBody.toByteArray();
            toServiceMsg.setAppId(ahVar.a);
            toServiceMsg.setTimeout(a.ap);
            toServiceMsg.setMsfCommand(MsfCommand._msf_queryPush);
            toServiceMsg.setRequestSsoSeq(j.f());
            toServiceMsg.putWupBuffer(o.b(toByteArray));
            MsfSdkUtils.addToMsgProcessName(ahVar.b, toServiceMsg);
            try {
                this.b.b.a(toServiceMsg);
            } catch (Throwable e) {
                QLog.w(a, 1, "query push error " + e, e);
            }
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "send " + ahVar.k.a + " query push id " + ahVar.c + " model:" + str2);
            }
        } else if (QLog.isColorLevel()) {
            QLog.d(a, 2, "pushUin is " + ahVar.k.a + ", queryPushId is " + ahVar.c + ". no query");
        }
    }

    public boolean a() {
        int length = (((a.cg.getBytes().length + 13) + 1) + 0) + 4;
        byte length2 = (byte) a.cg.getBytes().length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length).putInt(20140601).putInt(0).put(length2).put(a.cg.getBytes()).put((byte) 0).putInt(0);
        byte[] array = allocate.array();
        ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", a.cg);
        toServiceMsg.setAppId(j.a().i());
        toServiceMsg.setTimeout(a.ap);
        toServiceMsg.setMsfCommand(MsfCommand.qal_Hello);
        toServiceMsg.setRequestSsoSeq(j.f());
        toServiceMsg.setNeedCallback(false);
        toServiceMsg.putWupBuffer(array);
        try {
            this.b.b.a(toServiceMsg);
            return true;
        } catch (Exception e) {
            QLog.e(a, 1, "failed keepalive ping  qal.hello " + e.toString());
            return false;
        }
    }

    void b(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        String str = g.b(toServiceMsg) + toServiceMsg.getUin();
        if (!this.b.g.containsKey(str)) {
            QLog.e(a, "onQueryPushResp no key:" + str);
        } else if (((ah) this.b.g.get(str)) != null) {
            try {
                if (fromServiceMsg.isSuccess()) {
                    stat_hello.RspBody rspBody = new stat_hello.RspBody();
                    Object wupBuffer = fromServiceMsg.getWupBuffer();
                    Object obj = new byte[(wupBuffer.length - 4)];
                    System.arraycopy(wupBuffer, 4, obj, 0, wupBuffer.length - 4);
                    rspBody.mergeFrom((byte[]) obj);
                    this.g = 0;
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "recv query push resp.iInterval=" + rspBody.uint32_hello_interval.get() + ", queryIntervTime=" + this.b.e() + ", next query time is " + aj.f.format(Long.valueOf(System.currentTimeMillis() + ((long) (rspBody.uint32_hello_interval.get() * 1000)))));
                    }
                    this.b.r = (long) (rspBody.uint32_hello_interval.get() * 1000);
                    this.b.a(this.b.e(), str);
                    o.b(rspBody.str_client_ip.get());
                    return;
                }
                this.b.a(180000, str);
            } catch (Throwable th) {
                th.printStackTrace();
                this.b.a(this.b.e(), str);
            }
        }
    }
}
