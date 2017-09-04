package com.tencent.av.sdk;

public class AVView {
    public static final int MAX_VIEW_COUNT = 5;
    static final String TAG = "SdkJni";
    public static final int VIDEO_SRC_TYPE_CAMERA = 1;
    public static final int VIDEO_SRC_TYPE_MEDIA = 3;
    public static final int VIDEO_SRC_TYPE_NONE = 0;
    public static final int VIDEO_SRC_TYPE_SCREEN = 2;
    public static final int VIEW_SIZE_TYPE_BIG = 1;
    public static final int VIEW_SIZE_TYPE_SMALL = 0;
    public int videoSrcType = 0;
    public int viewSizeType = 1;
}
