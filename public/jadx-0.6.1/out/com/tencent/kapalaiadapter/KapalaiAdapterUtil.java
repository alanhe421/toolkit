package com.tencent.kapalaiadapter;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import com.tencent.av.utils.QLog;
import com.tencent.kapalaiadapter.sdcardmountinforutil.SDCardMountInforUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class KapalaiAdapterUtil {
    public int editTextSetSelectionIndex;

    private static class KAUHolder {
        public static final KapalaiAdapterUtil kauInstance = new KapalaiAdapterUtil();

        private KAUHolder() {
        }
    }

    private KapalaiAdapterUtil() {
        this.editTextSetSelectionIndex = 0;
    }

    public static KapalaiAdapterUtil getKAUInstance() {
        return KAUHolder.kauInstance;
    }

    public boolean isSupportedFlashModesByKapalai(Parameters parameters, Context context) {
        return parameters.getSupportedFlashModes() != null && context.getPackageManager().hasSystemFeature("android.hardware.camera.flash") && parameters.getSupportedFlashModes().size() > 1;
    }

    public String setFlashMode_TORCH(Parameters parameters) {
        String str = "off";
        List supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes.contains("torch")) {
            return "torch";
        }
        if (supportedFlashModes.contains("on")) {
            return "on";
        }
        if (supportedFlashModes.contains("auto")) {
            return "auto";
        }
        return str;
    }

    public void setShortcutIntentFlag(Intent intent) {
        intent.setFlags(337641472);
    }

    public Camera tryGetFrontCamera() {
        if (Build.MANUFACTURER.equalsIgnoreCase("HTC")) {
            return tryHTCFrontCamera();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("YuLong")) {
            return tryCoolpadFrontCamera();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("HISENSE")) {
            return tryHisenseFrontCamera();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
            return tryHuaweiFrontCamera();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("LENOVO")) {
            return tryLenovoFrontCamera();
        }
        return null;
    }

    private Camera tryHTCFrontCamera() {
        Exception e;
        Camera camera = null;
        Camera open;
        try {
            open = Camera.open();
            if (open != null) {
                try {
                    Parameters parameters = open.getParameters();
                    if (parameters != null) {
                        parameters.set("video_input", "secondary");
                        open.setParameters(parameters);
                    }
                } catch (Exception e2) {
                    e = e2;
                    if (open == null) {
                        camera = open;
                    } else {
                        open.release();
                    }
                    e.printStackTrace();
                    return camera;
                }
            }
            return open;
        } catch (Exception e3) {
            e = e3;
            open = null;
            if (open == null) {
                open.release();
            } else {
                camera = open;
            }
            e.printStackTrace();
            return camera;
        }
    }

    private Camera tryCoolpadFrontCamera() {
        Exception e;
        Camera camera = null;
        Camera open;
        try {
            open = Camera.open();
            if (open != null) {
                try {
                    Parameters parameters = open.getParameters();
                    if (parameters != null) {
                        parameters.set("device", "/dev/video1");
                        open.setParameters(parameters);
                    }
                } catch (Exception e2) {
                    e = e2;
                    if (open == null) {
                        camera = open;
                    } else {
                        open.release();
                    }
                    e.printStackTrace();
                    return camera;
                }
            }
            return open;
        } catch (Exception e3) {
            e = e3;
            open = null;
            if (open == null) {
                open.release();
            } else {
                camera = open;
            }
            e.printStackTrace();
            return camera;
        }
    }

    private Camera tryHisenseFrontCamera() {
        Camera open;
        Exception e;
        Camera camera = null;
        try {
            open = Camera.open();
            if (open != null) {
                try {
                    Method method = open.getClass().getMethod("setSensorID", new Class[]{Integer.TYPE});
                    if (method != null) {
                        method.invoke(open, new Object[]{Integer.valueOf(1)});
                    }
                } catch (Exception e2) {
                    e = e2;
                    if (open == null) {
                        camera = open;
                    } else {
                        open.release();
                    }
                    e.printStackTrace();
                    return camera;
                }
            }
            return open;
        } catch (Exception e3) {
            e = e3;
            open = null;
            if (open == null) {
                open.release();
            } else {
                camera = open;
            }
            e.printStackTrace();
            return camera;
        }
    }

    private Camera tryHuaweiFrontCamera() {
        Camera camera = null;
        try {
            Class cls = Class.forName("android.hardware.Camera");
            Method method = cls.getMethod("setCurrentCamera", new Class[]{Integer.TYPE});
            if (method != null) {
                method.invoke(cls, new Object[]{Integer.valueOf(1)});
                camera = Camera.open();
            }
        } catch (Exception e) {
            if (camera != null) {
                camera.release();
            }
            e.printStackTrace();
        }
        return camera;
    }

    @TargetApi(9)
    private Camera tryLenovoFrontCamera() {
        Camera camera = null;
        try {
            if (VERSION.SDK_INT >= 9) {
                camera = Camera.open();
            }
        } catch (Exception e) {
            if (camera != null) {
                camera.release();
            }
            e.printStackTrace();
        }
        return camera;
    }

    public boolean isExSDCardPath(Context context, String str) {
        return SDCardMountInforUtil.getSelf(context).isExSdcard(str);
    }

    public ArrayList<String> getAllExSDCardMountPaths(Context context) {
        ArrayList allPath = getAllPath(context);
        if (allPath == null || allPath.size() < 1) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (int i = 0; i < allPath.size(); i++) {
            String str = (String) allPath.get(i);
            if (SDCardMountInforUtil.getSelf(context).isExSdcard(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public ArrayList<String> getAllPath(Context context) {
        return SDCardMountInforUtil.getSelf(context).getAllPath();
    }

    public void setEditTextSetSelectionIndex(int i) {
        this.editTextSetSelectionIndex = i;
    }

    public int getEditTextSetSelectionIndex() {
        return this.editTextSetSelectionIndex;
    }

    public void setEditTextSetSelection(EditText editText) {
        if (editText != null) {
            editText.setSelection(this.editTextSetSelectionIndex);
        }
    }

    public Bitmap createScaledBitmapByConfig(Bitmap bitmap, int i) {
        if (bitmap != null) {
            return Bitmap.createScaledBitmap(bitmap, i, i, true);
        }
        return null;
    }

    @TargetApi(16)
    public Notification newNotificationForMeizu(Context context, int i) {
        try {
            Notification notification;
            if (VERSION.SDK_INT < 11) {
                notification = new Notification(i, null, System.currentTimeMillis());
            } else {
                Builder builder = new Builder(context);
                Method declaredMethod = Class.forName("android.app.Notification$Builder").getDeclaredMethod("setInternalApp", new Class[]{Integer.TYPE});
                if (declaredMethod != null) {
                    declaredMethod.invoke(builder, new Object[]{Integer.valueOf(1)});
                    if (VERSION.SDK_INT >= 16) {
                        notification = builder.build();
                    } else if (VERSION.SDK_INT < 16 && VERSION.SDK_INT >= 11) {
                        notification = builder.getNotification();
                    }
                }
                notification = null;
            }
            if (notification == null) {
                return new Notification(i, null, System.currentTimeMillis());
            }
            return notification;
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.e("newNotificationForMeizu", 0, e.getMessage());
            }
            return null == null ? new Notification(i, null, System.currentTimeMillis()) : null;
        } catch (Throwable th) {
            if (null == null) {
                Notification notification2 = new Notification(i, null, System.currentTimeMillis());
            }
        }
    }

    public void setActivityWindowType_TYPE_KEYGUARD(Window window) {
        window.setType(2004);
    }

    public void setActivityWindowType_TYPE_APPLICATION(Window window) {
        window.setType(2);
    }

    public void setScreenBrightness(LayoutParams layoutParams) {
        layoutParams.screenBrightness = 0.035f;
    }

    public int getNumberOfCamera() {
        return 1;
    }
}
