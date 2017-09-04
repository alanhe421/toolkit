package com.samsung.android.sdk.multiwindow;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.samsung.android.sdk.multiwindow.SMultiWindowReflator.Intent;
import com.samsung.android.sdk.multiwindow.SMultiWindowReflator.MultiWindowStyle;
import com.samsung.android.sdk.multiwindow.SMultiWindowReflator.WindowManagerPolicy;
import tencent.tls.platform.SigType;

public class SMultiWindowActivity {
    private static final String TAG = "SMultiWindowActivity";
    public static final int ZONE_A = WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_A;
    public static final int ZONE_B = WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_B;
    public static final int ZONE_FULL = (ZONE_A | ZONE_B);
    private Context mContext;
    private Rect mDefaultSize;
    private float mDensity;
    private SMultiWindow mMultiWindow = new SMultiWindow();
    private SMultiWindowReflator mMultiWindowReflator = new SMultiWindowReflator();
    private StateChangeListener mStateChangeListener;
    private int mWindowMode;

    public interface StateChangeListener {
        void onModeChanged(boolean z);

        void onSizeChanged(Rect rect);

        void onZoneChanged(int i);
    }

    private boolean checkMode(int i) {
        return (this.mWindowMode & i) != 0;
    }

    private boolean checkOption(int i) {
        return (this.mWindowMode & i) != 0;
    }

    private void updateWindowMode() {
        if (this.mMultiWindow.isFeatureEnabled(1)) {
            Object invoke = this.mMultiWindowReflator.invoke("getWindowMode", (Object[]) null);
            if (invoke != null) {
                this.mWindowMode = ((Integer) invoke).intValue();
            }
        }
    }

    private void setWindowMode() {
        if (this.mMultiWindow.isFeatureEnabled(1)) {
            this.mMultiWindowReflator.invoke("setWindowMode", new Object[]{Integer.valueOf(this.mWindowMode), Boolean.valueOf(true)});
        }
    }

    private Bundle getWindowInfo() {
        return (Bundle) this.mMultiWindowReflator.invoke("getWindowInfo", (Object[]) null);
    }

    private Rect getLastSize() {
        Rect rect = null;
        Bundle windowInfo = getWindowInfo();
        if (windowInfo != null) {
            rect = (Rect) windowInfo.getParcelable(Intent.EXTRA_WINDOW_LAST_SIZE);
        }
        return rect != null ? rect : this.mDefaultSize;
    }

    private Object getMultiPhoneWindowEvent() {
        return this.mMultiWindowReflator.invoke("getMultiPhoneWindowEvent", (Object[]) null);
    }

    public SMultiWindowActivity(Activity activity) {
        Class cls = activity.getClass();
        this.mMultiWindowReflator.putMethod(cls, activity, "getWindowMode", (Class[]) null);
        this.mMultiWindowReflator.putMethod(cls, activity, "setWindowMode", new Class[]{Integer.TYPE, Boolean.TYPE});
        this.mMultiWindowReflator.putMethod(cls, activity, "getWindowInfo", (Class[]) null);
        this.mMultiWindowReflator.putMethod(cls, activity, "getWindow", (Class[]) null);
        try {
            Class cls2 = Class.forName("com.samsung.android.multiwindow.MultiWindowStyle");
            this.mMultiWindowReflator.putMethod(cls, activity, "getMultiWindowStyle", (Class[]) null);
            this.mMultiWindowReflator.putMethod(cls, activity, "setMultiWindowStyle", new Class[]{cls2});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cls = activity.getWindow().getClass();
        this.mMultiWindowReflator.putMethod(cls, activity.getWindow(), "getMultiPhoneWindowEvent", (Class[]) null);
        this.mMultiWindowReflator.putMethod(cls, activity.getWindow(), "getWindowManager", (Class[]) null);
        this.mMultiWindowReflator.putMethod(cls, activity.getWindow(), "getAttributes", (Class[]) null);
        this.mDensity = activity.getResources().getDisplayMetrics().density;
        try {
            Object multiPhoneWindowEvent = getMultiPhoneWindowEvent();
            if (multiPhoneWindowEvent != null) {
                cls = multiPhoneWindowEvent.getClass();
                this.mMultiWindowReflator.putMethod(cls, multiPhoneWindowEvent, "setStateChangeListener", new Class[]{com.samsung.android.sdk.multiwindow.SMultiWindowListener.StateChangeListener.class});
                this.mMultiWindowReflator.putMethod(cls, multiPhoneWindowEvent, "minimizeWindow", new Class[]{Integer.TYPE, Boolean.TYPE});
                this.mMultiWindowReflator.putMethod(cls, multiPhoneWindowEvent, "multiWindow", new Class[]{Integer.TYPE, Boolean.TYPE});
                this.mMultiWindowReflator.putMethod(cls, multiPhoneWindowEvent, "normalWindow", new Class[]{Integer.TYPE});
                this.mMultiWindowReflator.putMethod(cls, multiPhoneWindowEvent, "getScaleInfo", (Class[]) null);
            }
        } catch (NoClassDefFoundError e2) {
            e2.printStackTrace();
        }
        Bundle windowInfo = getWindowInfo();
        if (windowInfo != null) {
            this.mDefaultSize = (Rect) windowInfo.getParcelable(Intent.EXTRA_WINDOW_DEFAULT_SIZE);
        }
        try {
            this.mContext = activity;
            insertLogForAPI(TAG);
        } catch (SecurityException e3) {
            throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
        }
    }

    public boolean isNormalWindow() {
        if (!this.mMultiWindow.isFeatureEnabled(1)) {
            return true;
        }
        updateWindowMode();
        return checkMode(WindowManagerPolicy.WINDOW_MODE_NORMAL);
    }

    public boolean isMultiWindow() {
        if (!this.mMultiWindow.isFeatureEnabled(1)) {
            return false;
        }
        updateWindowMode();
        return checkMode(WindowManagerPolicy.WINDOW_MODE_FREESTYLE);
    }

    public boolean isScaleWindow() {
        if (!this.mMultiWindow.isFeatureEnabled(2)) {
            return false;
        }
        updateWindowMode();
        return checkOption(WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_SCALE);
    }

    public boolean isMinimized() {
        if (!this.mMultiWindow.isFeatureEnabled(2)) {
            return false;
        }
        updateWindowMode();
        if (checkMode(WindowManagerPolicy.WINDOW_MODE_FREESTYLE) && checkOption(WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_MINIMIZED)) {
            return true;
        }
        return false;
    }

    public void normalWindow() {
        if (this.mMultiWindow.isFeatureEnabled(1)) {
            updateWindowMode();
            if (!checkMode(WindowManagerPolicy.WINDOW_MODE_FREESTYLE)) {
                return;
            }
            if (this.mMultiWindowReflator.checkMethod("normalWindow")) {
                this.mMultiWindowReflator.invoke("normalWindow", new Object[]{Integer.valueOf(this.mWindowMode)});
                return;
            }
            this.mWindowMode &= WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_UNIQUEOP_MASK ^ -1;
            this.mWindowMode &= WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_MASK ^ -1;
            this.mWindowMode &= WindowManagerPolicy.WINDOW_MODE_MASK ^ -1;
            this.mWindowMode |= WindowManagerPolicy.WINDOW_MODE_NORMAL;
            setWindowMode();
        }
    }

    public void multiWindow(float f) {
        if (this.mMultiWindow.isFeatureEnabled(2)) {
            updateWindowMode();
            if ((!checkMode(WindowManagerPolicy.WINDOW_MODE_FREESTYLE) || checkOption(WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_MASK)) && this.mMultiWindowReflator.checkMethod("setMultiWindowStyle")) {
                try {
                    Class cls = Class.forName("com.samsung.android.multiwindow.MultiWindowStyle");
                    if (cls != null) {
                        Object newInstance = cls.newInstance();
                        SMultiWindowReflator.invoke(cls, newInstance, "setType", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(MultiWindowStyle.TYPE_CASCADE)});
                        SMultiWindowReflator.invoke(cls, newInstance, "setOption", new Class[]{Integer.TYPE, Boolean.TYPE}, new Object[]{Integer.valueOf(MultiWindowStyle.OPTION_SCALE), Boolean.valueOf(true)});
                        SMultiWindowReflator.invoke(cls, newInstance, "setScale", new Class[]{Float.TYPE}, new Object[]{Float.valueOf(f)});
                        this.mMultiWindowReflator.invoke("setMultiWindowStyle", new Object[]{newInstance});
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void minimizeWindow() {
        if (this.mMultiWindow.isFeatureEnabled(2)) {
            updateWindowMode();
            if (checkMode(WindowManagerPolicy.WINDOW_MODE_FREESTYLE) && !checkOption(WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_MASK)) {
                if (this.mMultiWindowReflator.checkMethod("minimizeWindow")) {
                    this.mMultiWindowReflator.invoke("minimizeWindow", new Object[]{Integer.valueOf(this.mWindowMode), Boolean.valueOf(false)});
                    return;
                }
                this.mWindowMode &= WindowManagerPolicy.WINDOW_MODE_MASK ^ -1;
                this.mWindowMode |= WindowManagerPolicy.WINDOW_MODE_FREESTYLE;
                this.mWindowMode |= WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_MINIMIZED;
                this.mWindowMode &= WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_MASK ^ -1;
                setWindowMode();
            }
        }
    }

    public Rect getRectInfo() {
        if (isMultiWindow()) {
            return getLastSize();
        }
        Point point = new Point();
        ((WindowManager) this.mMultiWindowReflator.invoke("getWindowManager", (Object[]) null)).getDefaultDisplay().getSize(point);
        if ((((LayoutParams) this.mMultiWindowReflator.invoke("getAttributes", (Object[]) null)).flags & 1024) == 0) {
            return new Rect(0, (int) (25.0f * this.mDensity), point.x, point.y);
        }
        return new Rect(0, 0, point.x, point.y);
    }

    public int getZoneInfo() {
        updateWindowMode();
        return this.mWindowMode & WindowManagerPolicy.WINDOW_MODE_OPTION_SPLIT_ZONE_MASK;
    }

    public PointF getScaleInfo() {
        if (this.mMultiWindow.isFeatureEnabled(2)) {
            return (PointF) this.mMultiWindowReflator.invoke("getScaleInfo", (Object[]) null);
        }
        return new PointF(1.0f, 1.0f);
    }

    public boolean setStateChangeListener(StateChangeListener stateChangeListener) {
        if (!this.mMultiWindow.isFeatureEnabled(1) || !this.mMultiWindowReflator.checkMethod("setStateChangeListener")) {
            return false;
        }
        this.mStateChangeListener = stateChangeListener;
        if (this.mStateChangeListener == null) {
            this.mMultiWindowReflator.invoke("setStateChangeListener", new Object[]{null});
            return true;
        }
        1 1 = new 1(this);
        this.mMultiWindowReflator.invoke("setStateChangeListener", new Object[]{1});
        return true;
    }

    public static android.content.Intent makeMultiWindowIntent(android.content.Intent intent, int i) {
        if (intent == null) {
            intent = new android.content.Intent();
        }
        if (new SMultiWindow().isFeatureEnabled(1)) {
            int i2;
            intent.addFlags(SigType.TLS);
            if (i == ZONE_FULL) {
                i2 = 0 | WindowManagerPolicy.WINDOW_MODE_NORMAL;
            } else {
                i2 = 0 | (WindowManagerPolicy.WINDOW_MODE_FREESTYLE | i);
            }
            intent.putExtra(Intent.EXTRA_WINDOW_MODE, i2);
        }
        return intent;
    }

    public static android.content.Intent makeMultiWindowIntent(android.content.Intent intent, float f) {
        if (intent == null) {
            intent = new android.content.Intent();
        }
        SMultiWindow sMultiWindow = new SMultiWindow();
        if (sMultiWindow.isFeatureEnabled(1) && sMultiWindow.isFeatureEnabled(2)) {
            intent.addFlags(SigType.TLS);
            intent.putExtra(Intent.EXTRA_WINDOW_MODE, 0 | ((WindowManagerPolicy.WINDOW_MODE_FREESTYLE | WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_PINUP) | WindowManagerPolicy.WINDOW_MODE_OPTION_COMMON_SCALE));
            intent.putExtra(Intent.EXTRA_WINDOW_SCALE, f);
        }
        return intent;
    }

    private void insertLogForAPI(String str) {
        if (this.mContext != null) {
            int i = -1;
            SMultiWindow sMultiWindow = new SMultiWindow();
            String name = sMultiWindow.getClass().getPackage().getName();
            String str2 = this.mContext.getPackageName() + "#" + sMultiWindow.getVersionCode();
            try {
                i = this.mContext.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
            } catch (NameNotFoundException e) {
                Log.d("SM_SDK", "Could not find ContextProvider");
            }
            Log.d("SM_SDK", "context framework's  versionCode: " + i);
            if (i <= 1) {
                Log.d("SM_SDK", "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission");
            } else if (this.mContext.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                throw new SecurityException();
            } else {
                Parcelable contentValues = new ContentValues();
                contentValues.put("app_id", name);
                contentValues.put("feature", str2);
                contentValues.put("extra", str);
                Log.d(TAG, name + ", " + str2 + ", " + str);
                android.content.Intent intent = new android.content.Intent();
                intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                intent.putExtra("data", contentValues);
                intent.setPackage("com.samsung.android.providers.context");
                this.mContext.sendBroadcast(intent);
            }
        }
    }
}
