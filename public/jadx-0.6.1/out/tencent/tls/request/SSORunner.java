package tencent.tls.request;

import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import tencent.tls.report.QLog;

public class SSORunner implements Runnable {
    private final Object __sync = new Object();
    private byte[] reqData;
    private byte[] resData = null;
    private int ret;
    private String serviceCmd;
    private int timeout;

    class ProxyListener implements InvocationHandler {
        ProxyListener() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            boolean z = false;
            String name = method.getName();
            QLog.i("proxy " + name);
            if (name.equals("onSuccess")) {
                onSuccess((byte[]) objArr[0]);
                return obj;
            } else if (name.equals("onError")) {
                onError(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                return obj;
            } else if ("equals".equals(name)) {
                if (obj == objArr[0]) {
                    z = true;
                }
                return Boolean.valueOf(z);
            } else if ("hashCode".equals(name)) {
                return Integer.valueOf(System.identityHashCode(obj));
            } else {
                if ("toString".equals(name)) {
                    return obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj)) + ", with InvocationHandler " + this;
                }
                return obj;
            }
        }

        void onSuccess(byte[] bArr) {
            SSORunner.this.resData = bArr;
            synchronized (SSORunner.this.__sync) {
                SSORunner.this.__sync.notify();
            }
        }

        void onError(int i, String str) {
            QLog.i("sso err " + i + " errmsg " + str);
            SSORunner.this.resData = null;
            SSORunner.this.ret = i;
            synchronized (SSORunner.this.__sync) {
                SSORunner.this.__sync.notify();
            }
        }
    }

    public SSORunner(String str, byte[] bArr, int i) {
        if (str == null) {
            str = "";
        }
        this.serviceCmd = str;
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.reqData = bArr;
        if (i <= 0) {
            i = Constants.ERRORCODE_UNKNOWN;
        }
        this.timeout = i;
    }

    public int getRet() {
        return this.ret;
    }

    public byte[] getResData() {
        return this.resData;
    }

    public void run() {
        this.ret = 0;
        Class cls;
        Object invoke;
        Object newProxyInstance;
        try {
            cls = Class.forName("com.tencent.qalsdk.QALSDKManager");
            invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            newProxyInstance = Proxy.newProxyInstance(Class.forName("com.tencent.qalsdk.QALValueCallBack").getClassLoader(), new Class[]{r2}, new ProxyListener());
            Method method = cls.getMethod("sendMsg", new Class[]{String.class, String.class, byte[].class, Long.TYPE, r2});
            QLog.i("SSORunner serviceCmd=" + this.serviceCmd + " reqData.length=" + this.reqData.length + " timeout=" + this.timeout);
            method.invoke(invoke, new Object[]{"0", this.serviceCmd, this.reqData, Integer.valueOf(this.timeout), newProxyInstance});
            synchronized (this.__sync) {
                this.__sync.wait((long) (this.timeout + http.Internal_Server_Error));
            }
        } catch (Exception e) {
            try {
                cls = Class.forName("com.tencent.timint.TIMIntManager");
                invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                newProxyInstance = Proxy.newProxyInstance(Class.forName("com.tencent.TIMValueCallBack").getClassLoader(), new Class[]{r2}, new ProxyListener());
                cls.getMethod(SocialConstants.TYPE_REQUEST, new Class[]{String.class, byte[].class, r2, Long.TYPE}).invoke(invoke, new Object[]{this.serviceCmd, this.reqData, newProxyInstance, Integer.valueOf(this.timeout)});
                synchronized (this.__sync) {
                    this.__sync.wait((long) (this.timeout + http.Internal_Server_Error));
                }
            } catch (Throwable e2) {
                QLog.e(e2);
                this.ret = -1000;
            }
        }
    }
}
