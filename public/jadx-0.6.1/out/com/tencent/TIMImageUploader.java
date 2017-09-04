package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.IImageUploadCallback;
import com.tencent.imcore.IMCoreUser;
import com.tencent.imcore.ImageElem;
import com.tencent.imsdk.QLog;
import java.util.concurrent.ConcurrentHashMap;

public class TIMImageUploader {
    static String defaultId = "";
    static ConcurrentHashMap<String, TIMImageUploader> mutiMap = new ConcurrentHashMap();
    static final String tag = "TIMImageUploader";
    private String identifier = "";

    class aa extends IImageUploadCallback {
        private TIMValueCallBack<TIMImageElem> a;
        private /* synthetic */ TIMImageUploader b;

        aa(TIMImageUploader tIMImageUploader, TIMValueCallBack<TIMImageElem> tIMValueCallBack) {
            this.b = tIMImageUploader;
            this.a = tIMValueCallBack;
            swigReleaseOwnership();
        }

        public final void done(ImageElem imageElem) {
            this.a.onSuccess(TIMElem.buildImageElem(imageElem, this.b.identifier));
            swigTakeOwnership();
        }

        public final void fail(int i, String str) {
            this.a.onError(i, str);
            swigTakeOwnership();
        }
    }

    private TIMImageUploader(String str) {
        this.identifier = str;
    }

    private IMCoreUser getIMCoreUser() {
        return TextUtils.isEmpty(this.identifier) ? TIMManager.getInstance().getCoreUser() : TIMManager.getInstanceById(this.identifier).getCoreUser();
    }

    public static TIMImageUploader getInstance() {
        return getInstanceById(TIMManager.getInstance().getIdentification());
    }

    public static TIMImageUploader getInstanceById(String str) {
        return new TIMImageUploader(str);
    }

    public void cancelTask(int i) {
        getIMCoreUser().cancelTask((long) i);
    }

    public int compressPic(String str, String str2, int i) {
        if (str == null || str2 == null || !(i == 1 || i == 2)) {
            QLog.d(tag, 1, "invalid params");
            return -1;
        } else if (IMCoreWrapper.get().isReady()) {
            return getIMCoreUser().compressPic(str, str2, i);
        } else {
            QLog.d(tag, 1, "sdk not initialized or not logged in.");
            return -1;
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getUploadProgress(long j) {
        return !IMCoreWrapper.get().isReady() ? -1 : getIMCoreUser().getImageUploadProgrss(j);
    }

    public int submitUploadTask(String str, TIMValueCallBack<TIMImageElem> tIMValueCallBack) {
        if (tIMValueCallBack == null) {
            return -1;
        }
        if (IMCoreWrapper.get().isReady()) {
            return (int) getIMCoreUser().submitUploadTask(str, new aa(this, tIMValueCallBack));
        }
        tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        return -1;
    }

    public int submitUploadTask(String str, TIMValueCallBack<TIMImageElem> tIMValueCallBack, int i) {
        if (tIMValueCallBack == null) {
            return -1;
        }
        if (IMCoreWrapper.get().isReady()) {
            return (int) getIMCoreUser().submitUploadTask(str, new aa(this, tIMValueCallBack), i);
        }
        tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        return -1;
    }
}
