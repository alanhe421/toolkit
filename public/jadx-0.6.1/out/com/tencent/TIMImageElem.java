package com.tencent;

import com.tencent.imsdk.QLog;
import java.util.ArrayList;

public class TIMImageElem extends TIMElem {
    public static final int TIM_IMAGE_FORMAT_BMP = 4;
    public static final int TIM_IMAGE_FORMAT_GIF = 2;
    public static final int TIM_IMAGE_FORMAT_JPG = 1;
    public static final int TIM_IMAGE_FORMAT_PNG = 3;
    public static final int TIM_IMAGE_FORMAT_UNKNOWN = 255;
    private int imageFormat;
    private ArrayList<TIMImage> imageList;
    private int level;
    private String path;
    private int taskId;

    public TIMImageElem() {
        this.taskId = 0;
        this.imageList = new ArrayList();
        this.path = "";
        this.level = 1;
        this.imageFormat = 255;
        this.type = TIMElemType.Image;
    }

    public boolean cancelUploading() {
        if (!IMCoreWrapper.get().isReady() || this.taskId == 0) {
            return false;
        }
        QLog.e("MSF.C.TIMMessage", 1, "canceluploading: " + this.taskId);
        TIMManager.getInstanceById(this.identifier).getCoreUser().cancelTask((long) this.taskId);
        return true;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public ArrayList<TIMImage> getImageList() {
        return this.imageList;
    }

    public int getLevel() {
        return this.level;
    }

    public String getPath() {
        return this.path;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public int getUploadingProgress() {
        return (!IMCoreWrapper.get().isReady() || this.taskId == 0) ? 0 : TIMManager.getInstanceById(this.identifier).getCoreUser().getImageUploadProgrss((long) this.taskId);
    }

    void setImageFormat(int i) {
        this.imageFormat = i;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    void setTaskId(int i) {
        this.taskId = i;
    }

    public void setmageList(ArrayList<TIMImage> arrayList) {
        this.imageList = arrayList;
    }
}
