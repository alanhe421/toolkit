package com.tencent.av.opengl.utils;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build.VERSION;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.tencent.android.tpush.common.Constants;
import com.tencent.tesla.soload.SoLoadCore;

public class Utils {
    public static final boolean AT_LEAST_16 = (VERSION.SDK_INT >= 16);
    public static final boolean CAN_START_PREVIEW_IN_JPEG_CALLBACK;
    public static final boolean ENABLE_PHOTO_EDITOR;
    public static final boolean HAS_ACTION_BAR;
    public static final boolean HAS_ANNOUNCE_FOR_ACCESSIBILITY;
    public static final boolean HAS_AUTO_FOCUS_MOVE_CALLBACK;
    public static final boolean HAS_CAMERA_FOCUS_AREA;
    public static final boolean HAS_CAMERA_HDR;
    public static final boolean HAS_CAMERA_METERING_AREA;
    public static final boolean HAS_CANCELLATION_SIGNAL;
    public static final boolean HAS_DISPLAY_LISTENER;
    public static final boolean HAS_EFFECTS_RECORDING = false;
    public static final boolean HAS_EFFECTS_RECORDING_CONTEXT_INPUT;
    public static final boolean HAS_FACE_DETECTION;
    public static final boolean HAS_GET_CAMERA_DISABLED = hasMethod(DevicePolicyManager.class, "getCameraDisabled", ComponentName.class);
    public static final boolean HAS_GET_SUPPORTED_VIDEO_SIZE;
    public static final boolean HAS_GLES20_REQUIRED;
    public static final boolean HAS_INTENT_EXTRA_LOCAL_ONLY;
    public static final boolean HAS_MEDIA_ACTION_SOUND;
    public static final boolean HAS_MEDIA_COLUMNS_WIDTH_AND_HEIGHT = hasField(MediaColumns.class, "WIDTH");
    public static final boolean HAS_MEDIA_MUXER;
    public static final boolean HAS_MEDIA_PROVIDER_FILES_TABLE;
    public static final boolean HAS_MOTION_EVENT_TRANSFORM;
    public static final boolean HAS_MTP;
    public static final boolean HAS_OBJECT_ANIMATION;
    public static final boolean HAS_OPTIONS_IN_MUTABLE;
    public static final boolean HAS_ORIENTATION_LOCK;
    public static final boolean HAS_POST_ON_ANIMATION;
    public static final boolean HAS_RELEASE_SURFACE_TEXTURE = hasMethod("android.graphics.SurfaceTexture", "release", new Class[0]);
    public static final boolean HAS_REMOTE_VIEWS_SERVICE;
    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_FACTORY;
    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_REGION_DECODER = (VERSION.SDK_INT >= 16);
    public static final boolean HAS_ROTATION_ANIMATION = hasField(LayoutParams.class, "rotationAnimation");
    public static final boolean HAS_SET_BEAM_PUSH_URIS;
    public static final boolean HAS_SET_DEFALT_BUFFER_SIZE = hasMethod("android.graphics.SurfaceTexture", "setDefaultBufferSize", Integer.TYPE, Integer.TYPE);
    public static final boolean HAS_SET_ICON_ATTRIBUTE;
    public static final boolean HAS_SET_SYSTEM_UI_VISIBILITY = hasMethod(View.class, "setSystemUiVisibility", Integer.TYPE);
    public static final boolean HAS_SURFACE_TEXTURE;
    public static final boolean HAS_SURFACE_TEXTURE_RECORDING;
    public static final boolean HAS_TIME_LAPSE_RECORDING;
    public static final boolean HAS_VIEW_PROPERTY_ANIMATOR;
    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION = hasField(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");
    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE = hasField(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");
    public static final boolean HAS_VIEW_TRANSFORM_PROPERTIES;
    public static final boolean HAS_ZOOM_WHEN_RECORDING;
    static final String TAG = "SDKJni";
    public static final boolean USE_888_PIXEL_FORMAT;

    public interface VERSION_CODES {
        public static final int GINGERBREAD = 9;
        public static final int GINGERBREAD_MR1 = 10;
        public static final int HONEYCOMB = 11;
        public static final int HONEYCOMB_MR1 = 12;
        public static final int HONEYCOMB_MR2 = 13;
        public static final int ICE_CREAM_SANDWICH = 14;
        public static final int ICE_CREAM_SANDWICH_MR1 = 15;
        public static final int JELLY_BEAN = 16;
        public static final int JELLY_BEAN_MR1 = 17;
        public static final int JELLY_BEAN_MR2 = 18;
    }

    public static int getGLVersion(Context context) {
        if (VERSION.SDK_INT < 9) {
            return 1;
        }
        if (context == null) {
            Log.d(TAG, "context is null");
            return 1;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        if (activityManager != null) {
            ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
            if (deviceConfigurationInfo != null) {
                return deviceConfigurationInfo.reqGlEsVersion >= SoLoadCore.IF_SO_CONFIG_EXIST ? 2 : 1;
            }
            Log.d(TAG, "getDeviceConfigurationInfo Error");
        }
        Log.d(TAG, "getSystemService Error");
        return 1;
    }

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        USE_888_PIXEL_FORMAT = z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        ENABLE_PHOTO_EDITOR = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_REUSING_BITMAP_IN_BITMAP_FACTORY = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_SET_BEAM_PUSH_URIS = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_SURFACE_TEXTURE = z;
        if (VERSION.SDK_INT >= 12) {
            z = true;
        } else {
            z = false;
        }
        HAS_MTP = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_AUTO_FOCUS_MOVE_CALLBACK = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_REMOTE_VIEWS_SERVICE = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_INTENT_EXTRA_LOCAL_ONLY = z;
        try {
            z = hasMethod(Camera.class, "setFaceDetectionListener", Class.forName("android.hardware.Camera$FaceDetectionListener")) && hasMethod(Camera.class, "startFaceDetection", new Class[0]) && hasMethod(Camera.class, "stopFaceDetection", new Class[0]) && hasMethod(Parameters.class, "getMaxNumDetectedFaces", new Class[0]);
        } catch (Throwable th) {
            z = false;
        }
        HAS_FACE_DETECTION = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_MEDIA_ACTION_SOUND = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_TIME_LAPSE_RECORDING = z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        HAS_ZOOM_WHEN_RECORDING = z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        HAS_CAMERA_FOCUS_AREA = z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        HAS_CAMERA_METERING_AREA = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_MOTION_EVENT_TRANSFORM = z;
        if (VERSION.SDK_INT >= 17) {
            z = true;
        } else {
            z = false;
        }
        HAS_EFFECTS_RECORDING_CONTEXT_INPUT = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_GET_SUPPORTED_VIDEO_SIZE = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_SET_ICON_ATTRIBUTE = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_MEDIA_PROVIDER_FILES_TABLE = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_SURFACE_TEXTURE_RECORDING = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_ACTION_BAR = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_VIEW_TRANSFORM_PROPERTIES = z;
        if (VERSION.SDK_INT >= 17) {
            z = true;
        } else {
            z = false;
        }
        HAS_CAMERA_HDR = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_OPTIONS_IN_MUTABLE = z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        CAN_START_PREVIEW_IN_JPEG_CALLBACK = z;
        if (VERSION.SDK_INT >= 12) {
            z = true;
        } else {
            z = false;
        }
        HAS_VIEW_PROPERTY_ANIMATOR = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_POST_ON_ANIMATION = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_ANNOUNCE_FOR_ACCESSIBILITY = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_OBJECT_ANIMATION = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        HAS_GLES20_REQUIRED = z;
        if (VERSION.SDK_INT >= 18) {
            z = true;
        } else {
            z = false;
        }
        HAS_ORIENTATION_LOCK = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        HAS_CANCELLATION_SIGNAL = z;
        if (VERSION.SDK_INT >= 18) {
            z = true;
        } else {
            z = false;
        }
        HAS_MEDIA_MUXER = z;
        if (VERSION.SDK_INT < 17) {
            z2 = false;
        }
        HAS_DISPLAY_LISTENER = z2;
    }

    public static int getIntFieldIfExists(Class<?> cls, String str, Class<?> cls2, int i) {
        try {
            i = cls.getDeclaredField(str).getInt(cls2);
        } catch (Exception e) {
        }
        return i;
    }

    private static boolean hasField(Class<?> cls, String str) {
        try {
            cls.getDeclaredField(str);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static boolean hasMethod(String str, String str2, Class<?>... clsArr) {
        try {
            Class.forName(str).getDeclaredMethod(str2, clsArr);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean hasMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            cls.getDeclaredMethod(str, clsArr);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int nextPowerOf2(int i) {
        if (i <= 0 || i > 1073741824) {
            throw new IllegalArgumentException("n is invalid: " + i);
        }
        int i2 = i - 1;
        i2 |= i2 >> 16;
        i2 |= i2 >> 8;
        i2 |= i2 >> 4;
        i2 |= i2 >> 2;
        return (i2 | (i2 >> 1)) + 1;
    }

    public static int prevPowerOf2(int i) {
        if (i > 0) {
            return Integer.highestOneBit(i);
        }
        throw new IllegalArgumentException();
    }

    public static int clamp(int i, int i2, int i3) {
        if (i > i3) {
            return i3;
        }
        return i < i2 ? i2 : i;
    }

    public static float clamp(float f, float f2, float f3) {
        if (f > f3) {
            return f3;
        }
        return f < f2 ? f2 : f;
    }

    public static long clamp(long j, long j2, long j3) {
        if (j > j3) {
            return j3;
        }
        return j < j2 ? j2 : j;
    }

    public static boolean isOpaque(int i) {
        return (i >>> 24) == 255;
    }
}
