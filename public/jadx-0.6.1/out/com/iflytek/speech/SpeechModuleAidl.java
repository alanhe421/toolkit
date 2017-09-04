package com.iflytek.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.a;
import com.iflytek.cloud.e;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

abstract class SpeechModuleAidl<I extends IInterface> implements ISpeechModule {
    private String mBindAction = null;
    private ServiceConnection mConnection = null;
    protected Context mContext = null;
    private a mInitListener = null;
    private HashMap<String, String> mParams = new HashMap();
    protected I mService;
    protected Object mSynLock = new Object();
    private Handler mUiHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (SpeechModuleAidl.this.mInitListener != null) {
                SpeechModuleAidl.this.mInitListener.a(message.what);
            }
        }
    };
    private volatile boolean userDestroy = false;

    public SpeechModuleAidl(Context context, a aVar, String str) {
        this.mContext = context;
        this.mInitListener = aVar;
        this.mBindAction = str;
        bindService();
    }

    private void bindService() {
        if (isActionInstalled(this.mContext, this.mBindAction)) {
            Intent intent = getIntent();
            intent.setAction(this.mBindAction);
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            this.mConnection = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    synchronized (SpeechModuleAidl.this.mSynLock) {
                        Log.d(SpeechModuleAidl.this.getTag(), "init success");
                        SpeechModuleAidl.this.mService = SpeechModuleAidl.this.getService(iBinder);
                        Log.d(SpeechModuleAidl.this.getTag(), "mService :" + SpeechModuleAidl.this.mService);
                        if (SpeechModuleAidl.this.mInitListener != null) {
                            Message.obtain(SpeechModuleAidl.this.mUiHandler, 0, 0, 0, null).sendToTarget();
                        }
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(SpeechModuleAidl.this.getTag(), "onServiceDisconnected");
                    SpeechModuleAidl.this.mService = null;
                    if (!SpeechModuleAidl.this.userDestroy) {
                        SpeechModuleAidl.this.bindService();
                    }
                }
            };
            this.mContext.bindService(intent, this.mConnection, 1);
        } else if (this.mInitListener != null) {
            Message.obtain(this.mUiHandler, 21001, 0, 0, null).sendToTarget();
        }
    }

    private I getService(IBinder iBinder) {
        try {
            String name = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
            Log.d(getTag(), "className = " + name);
            return (IInterface) Class.forName(name + "$Stub").getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return null;
    }

    public boolean destory() {
        Log.d(getTag(), "destory");
        try {
            this.userDestroy = true;
            if (this.mConnection == null) {
                return true;
            }
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Intent getIntent() {
        Intent intent = new Intent();
        if (!this.mParams.isEmpty()) {
            for (String str : this.mParams.keySet()) {
                intent.putExtra(str, (String) this.mParams.get(str));
            }
            HashMap c = new com.iflytek.cloud.b.a((String) this.mParams.get("params"), (String[][]) null).c();
            if (!(c == null || c.isEmpty())) {
                for (String str2 : c.keySet()) {
                    intent.putExtra(str2, (String) c.get(str2));
                }
            }
        }
        intent.putExtra(UtilityConfig.KEY_CALLER_APPID, e.a().a("appid"));
        intent.putExtra(UtilityConfig.KEY_CALLER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_PKG_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_PKG_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_CODE, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_CODE));
        return intent;
    }

    public String getParameter(String str) {
        return (String) this.mParams.get(str);
    }

    protected final String getTag() {
        return getClass().toString();
    }

    public boolean isActionInstalled(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || context.getPackageManager().resolveService(new Intent(str), 0) == null) ? false : true;
    }

    public boolean isAvailable() {
        return this.mService != null;
    }

    public int setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 20012;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mParams.remove(str);
            return 0;
        }
        this.mParams.put(str, str2);
        return 0;
    }
}
