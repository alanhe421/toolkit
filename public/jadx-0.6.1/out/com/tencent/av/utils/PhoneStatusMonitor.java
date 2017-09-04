package com.tencent.av.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;

public class PhoneStatusMonitor {
    static final String TAG = "PhoneStatusMonitor";
    Context mContext;
    boolean mIsCalling = false;
    PhoneStateListener mPhoneStateListener;
    PhoneStatusListener mPhoneStatusListener;
    PhoneStatusReceiver mPhoneStatusReceiver;

    public interface PhoneStatusListener {
        void onCallStateChanged(boolean z);
    }

    class PhoneStatusReceiver extends BroadcastReceiver {
        PhoneStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                if (QLog.isColorLevel()) {
                    QLog.d(PhoneStatusMonitor.TAG, 0, "onReceive PHONE_STATE");
                }
                if (PhoneStatusMonitor.this.mIsCalling && !PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                    PhoneStatusMonitor.this.mIsCalling = false;
                    if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                        PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(false);
                    }
                } else if (!PhoneStatusMonitor.this.mIsCalling && PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                    PhoneStatusMonitor.this.mIsCalling = true;
                    if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                        PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(true);
                    }
                }
            } else if (QLog.isColorLevel()) {
                QLog.d(PhoneStatusMonitor.TAG, 0, "onReceive NEW_OUTGOING_CALL");
            }
        }
    }

    class QQPhoneStateListener extends PhoneStateListener {
        QQPhoneStateListener() {
        }

        public void onCallStateChanged(int i, String str) {
            switch (i) {
                case 0:
                    if (QLog.isColorLevel()) {
                        QLog.d(PhoneStatusMonitor.TAG, 0, "onCallStateChanged CALL_STATE_IDLE");
                    }
                    if (PhoneStatusMonitor.this.mIsCalling && !PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                        PhoneStatusMonitor.this.mIsCalling = false;
                        if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                            PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(false);
                            break;
                        }
                    }
                    break;
                case 1:
                case 2:
                    if (QLog.isColorLevel()) {
                        QLog.d(PhoneStatusMonitor.TAG, 0, "onCallStateChanged CALL_STATE_RINGING or CALL_STATE_OFFHOOK");
                    }
                    if (!PhoneStatusMonitor.this.mIsCalling) {
                        PhoneStatusMonitor.this.mIsCalling = true;
                        if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                            PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(true);
                            break;
                        }
                    }
                    break;
            }
            super.onCallStateChanged(i, str);
        }
    }

    public PhoneStatusMonitor(Context context, PhoneStatusListener phoneStatusListener) {
        this.mContext = context;
        this.mPhoneStatusListener = phoneStatusListener;
        this.mPhoneStateListener = new QQPhoneStateListener();
        this.mPhoneStatusReceiver = new PhoneStatusReceiver();
        PhoneStatusTools.listen(context, this.mPhoneStateListener, 32);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.PHONE_STATE2");
        intentFilter.addAction("android.intent.action.PHONE_STATE_2");
        intentFilter.addAction("android.intent.action.PHONE_STATE_EXT");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        context.registerReceiver(this.mPhoneStatusReceiver, intentFilter);
    }

    public void uninit() {
        PhoneStatusTools.listen(this.mContext, this.mPhoneStateListener, 0);
        this.mContext.unregisterReceiver(this.mPhoneStatusReceiver);
        this.mPhoneStatusListener = null;
        this.mPhoneStatusReceiver = null;
        this.mPhoneStateListener = null;
        this.mContext = null;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    public boolean isCalling() {
        return this.mIsCalling;
    }
}
