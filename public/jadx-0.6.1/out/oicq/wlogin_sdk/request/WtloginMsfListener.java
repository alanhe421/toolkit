package oicq.wlogin_sdk.request;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import oicq.wlogin_sdk.tools.util;

public class WtloginMsfListener implements Runnable {
    public static String CLIENT_CLASSNAME = "com.tencent.mobileqq.msf.core.auth.WtProvider";
    static Object TicketMgr;
    private static final Object __SyncCB = new Object();
    private static final Object __SyncSeq = new Object();
    private static Map<Long, WtloginMsfListener> __cbs = new HashMap();
    private static long __seq = 0;
    private byte[] data;
    private boolean flag;
    private int ret;
    private byte[] ret_data = null;
    private String ret_serviceCmd;
    private boolean ret_success = false;
    private String ret_uin;
    private final Semaphore semp = new Semaphore(1);
    private String serviceCmd;
    private int timeout;
    private String uin;
    private WUserSigInfo userSigInfo;

    public WtloginMsfListener(String str, String str2, byte[] bArr, int i, boolean z, WUserSigInfo wUserSigInfo) {
        if (str == null) {
            str = "0";
        }
        this.uin = str;
        if (str2 == null) {
            str2 = "";
        }
        this.serviceCmd = str2;
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.data = bArr;
        if (i <= 0) {
            i = 5000;
        }
        this.timeout = i;
        this.flag = z;
        this.userSigInfo = wUserSigInfo;
    }

    public int SendData(byte[] bArr, int i) {
        if (!u.ap) {
            String l = u.l();
            util.LOGI("mqq process: " + l, "");
            if (!l.endsWith(":MSF")) {
                return sendRPCData(bArr, i);
            }
        }
        try {
            util.LOGI("msf sendData", "");
            Class cls = Class.forName(CLIENT_CLASSNAME);
            int intValue = Integer.valueOf(cls.getMethod("sendData", new Class[]{WUserSigInfo.class, String.class, String.class, byte[].class, Integer.TYPE, Boolean.TYPE, WtloginMsfListener.class}).invoke(cls, new Object[]{this.userSigInfo, this.uin, this.serviceCmd, bArr.clone(), Integer.valueOf(i), Boolean.valueOf(this.flag), this}).toString()).intValue();
            if (intValue <= 0) {
                return intValue;
            }
            this.semp.acquire();
            return intValue;
        } catch (Exception e) {
            util.printException(e, this.uin);
            return -1000;
        }
    }

    public void Cancel() {
        try {
            Class cls = Class.forName(CLIENT_CLASSNAME);
            cls.getMethod("cancel", new Class[]{WUserSigInfo.class}).invoke(cls, new Object[]{this.userSigInfo});
        } catch (Exception e) {
            util.printException(e, this.uin);
        }
    }

    public byte[] RecvData() {
        byte[] bArr = null;
        try {
            this.semp.acquire();
            if (this.ret_success) {
                if (this.ret_uin == null || !this.ret_uin.equals(this.uin)) {
                    String str;
                    this.ret = -1009;
                    StringBuilder append = new StringBuilder().append("ret_uin: ");
                    if (this.ret_uin == null) {
                        str = "null";
                    } else {
                        str = this.ret_uin;
                    }
                    append = append.append(str).append(", uin: ");
                    if (this.uin == null) {
                        str = "null";
                    } else {
                        str = this.uin;
                    }
                    util.LOGI(append.append(str).toString(), "");
                } else if (this.ret_serviceCmd == null || !this.ret_serviceCmd.equals(this.serviceCmd)) {
                    this.ret = -1009;
                    util.LOGI("ret_serviceCmd: " + (this.ret_serviceCmd == null ? "null" : this.ret_serviceCmd) + ", serviceCmd:" + (this.serviceCmd == null ? "null" : this.serviceCmd), "");
                } else {
                    this.semp.release();
                    bArr = this.ret_data;
                }
            }
        } catch (Exception e) {
            util.printException(e, this.uin);
        }
        return bArr;
    }

    public void onReceiveData(String str, String str2, byte[] bArr) {
        this.ret_success = true;
        this.ret_uin = str;
        this.ret_serviceCmd = str2;
        this.ret = 0;
        this.ret_data = bArr;
        this.semp.release();
    }

    public void onReceiveFail(String str, String str2, int i) {
        this.ret_success = false;
        this.ret_uin = str;
        this.ret_serviceCmd = str2;
        this.ret = i;
        this.semp.release();
    }

    public int getRet() {
        return this.ret;
    }

    public byte[] getRetData() {
        return this.ret_data;
    }

    private static long allocateSeq() {
        long j;
        synchronized (__SyncSeq) {
            j = __seq + 1;
            __seq = j;
            j %= 2147483647L;
        }
        return j;
    }

    private int sendRPCData(byte[] bArr, int i) {
        long allocateSeq = allocateSeq();
        util.LOGI("sendRPCData seq: " + allocateSeq, "");
        try {
            Class cls = Class.forName("mqq.manager.TicketManager");
            if (TicketMgr == null) {
                Class cls2 = Class.forName("com.tencent.common.app.BaseApplicationImpl");
                Class cls3 = Class.forName("mqq.app.BaseActivity");
                Class cls4 = Class.forName("mqq.app.AppRuntime");
                Method method = cls2.getMethod("getApplication", new Class[0]);
                Method method2 = cls2.getMethod("waitAppRuntime", new Class[]{cls3});
                Method method3 = cls4.getMethod("getManager", new Class[]{Integer.TYPE});
                Field field = cls4.getField("TICKET_MANAGER");
                Object invoke = method.invoke(null, new Object[0]);
                if (invoke != null) {
                    Object invoke2 = method2.invoke(invoke, new Object[]{null});
                    if (invoke2 != null) {
                        TicketMgr = method3.invoke(invoke2, new Object[]{field.get(cls4)});
                    }
                }
            }
            int intValue = Integer.valueOf(cls.getMethod("sendRPCData", new Class[]{Long.TYPE, String.class, String.class, byte[].class, Integer.TYPE}).invoke(TicketMgr, new Object[]{Long.valueOf(allocateSeq), this.uin, this.serviceCmd, bArr.clone(), Integer.valueOf(i)}).toString()).intValue();
            if (intValue != 0) {
                return intValue;
            }
            synchronized (__SyncCB) {
                __cbs.put(Long.valueOf(allocateSeq), this);
            }
            this.semp.acquire();
            return 99;
        } catch (Exception e) {
            util.printException(e, this.uin);
            return -1000;
        }
    }

    public static void onAsyncReceive(String str, String str2, long j, byte[] bArr) {
        util.LOGI("rpc receive " + str2 + " seq: " + j + " data:" + (bArr == null ? "null" : Integer.valueOf(bArr.length)), str);
        WtloginMsfListener pickSeq = pickSeq(j);
        if (pickSeq != null) {
            pickSeq.onReceiveData(str, str2, bArr);
        }
    }

    public static void onAsyncReceiveFail(String str, String str2, long j, int i) {
        util.LOGI("rpc receive " + str2 + " seq: " + j + " fail:" + i, str);
        WtloginMsfListener pickSeq = pickSeq(j);
        if (pickSeq != null) {
            pickSeq.onReceiveFail(str, str2, i);
        }
    }

    private static WtloginMsfListener pickSeq(long j) {
        WtloginMsfListener wtloginMsfListener;
        synchronized (__SyncCB) {
            wtloginMsfListener = (WtloginMsfListener) __cbs.get(Long.valueOf(j));
            __cbs.remove(Long.valueOf(j));
        }
        return wtloginMsfListener;
    }

    public void run() {
        try {
            this.ret = SendData(this.data, this.timeout);
            if (this.ret <= 0) {
                util.LOGI("msf request send data failed, ret=" + this.ret, "");
            } else if (RecvData() == null) {
                this.ret_data = null;
            }
        } catch (Exception e) {
        }
    }
}
