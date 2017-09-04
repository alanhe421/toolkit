package com.tencent.av.sdk;

public class AVEndpoint {
    public int nativeObj;

    public static class Info {
        public static final int AVTerminal_Android = 4;
        public static final int AVTerminal_AndroidPad = 8;
        public static final int AVTerminal_Mobile = 1;
        public static final int AVTerminal_PC = 5;
        public static final int AVTerminal_Unknown = 0;
        public static final int AVTerminal_WINRTPAD = 6;
        public static final int AVTerminal_WINRTPHONE = 7;
        public static final int AVTerminal_iPad = 3;
        public static final int AVTerminal_iPhone = 2;
        static final String TAG = "SdkJni";
        public String openId;
        public int sdkVersion;
        public int state;
        public int terminalType;

        public Info() {
            this.openId = "";
            this.sdkVersion = 0;
            this.terminalType = 0;
            this.state = 0;
        }

        public Info(String str, int i, int i2, int i3) {
            this.openId = str;
            this.sdkVersion = i;
            this.terminalType = i2;
            this.state = i3;
        }
    }

    public native String getId();

    public native Info getInfo();

    public native boolean hasAudio();

    public native boolean hasCameraVideo();

    public native boolean hasMediaVideo();

    public native boolean hasScreenVideo();

    public AVEndpoint() {
        this.nativeObj = 0;
        this.nativeObj = 0;
    }
}
