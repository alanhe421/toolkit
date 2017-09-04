package com.samsung.android.sdk.multiwindow;

import android.app.ActivityThread;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import com.samsung.android.sdk.multiwindow.SMultiWindowReflator.PackageManager;

public final class SMultiWindow implements SsdkInterface {
    public static final int FREE_STYLE = 2;
    public static final int MULTIWINDOW = 1;
    private static final String TAG = "SMultiWindow";
    private static boolean enableQueried = false;
    private static boolean isFreeStyleEnabled = false;
    private static boolean isMultiWindowEnabled = false;
    private static int mVersionCode = 5;
    private static String mVersionName = "1.2.6";
    private boolean mInsertLog = false;

    public SMultiWindow() {
        initMultiWindowFeature();
    }

    public void initialize(Context context) throws SsdkUnsupportedException {
        if (!SsdkVendorCheck.isSamsungDevice()) {
            throw new SsdkUnsupportedException(Build.BRAND + " is not supported.", 0);
        } else if (isMultiWindowEnabled) {
            try {
                if (!this.mInsertLog) {
                    insertLog(context);
                }
            } catch (SecurityException e) {
                throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
            }
        } else {
            throw new SsdkUnsupportedException("The device is not supported.", 1);
        }
    }

    public boolean isFeatureEnabled(int i) {
        switch (i) {
            case 1:
                return isMultiWindowEnabled;
            case 2:
                return isFreeStyleEnabled;
            default:
                return false;
        }
    }

    public int getVersionCode() {
        return mVersionCode;
    }

    public String getVersionName() {
        return mVersionName;
    }

    private void initMultiWindowFeature() {
        try {
            if (!enableQueried) {
                enableQueried = true;
                IPackageManager packageManager = ActivityThread.getPackageManager();
                if (packageManager != null) {
                    isMultiWindowEnabled = packageManager.hasSystemFeature(PackageManager.FEATURE_MULTIWINDOW);
                    isFreeStyleEnabled = packageManager.hasSystemFeature(PackageManager.FEATURE_MULTIWINDOW_FREESTYLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLog(Context context) {
        int i = -1;
        try {
            i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
        } catch (NameNotFoundException e) {
            Log.d(TAG, "Could not find ContextProvider");
        }
        Log.d(TAG, "versionCode: " + i);
        if (i <= 1) {
            Log.d(TAG, "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission");
        } else if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
            throw new SecurityException();
        } else {
            Parcelable contentValues = new ContentValues();
            String name = getClass().getPackage().getName();
            String str = context.getPackageName() + "#" + getVersionCode();
            contentValues.put("app_id", name);
            contentValues.put("feature", str);
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
            intent.putExtra("data", contentValues);
            intent.setPackage("com.samsung.android.providers.context");
            context.sendBroadcast(intent);
            this.mInsertLog = true;
        }
    }
}
