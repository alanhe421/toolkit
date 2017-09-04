package com.tencent.av.sdk;

public class AVRoomMulti$EnterParam {
    int audioCategory;
    long authBits;
    byte[] authBuffer;
    String controlRole;
    boolean createRoom;
    boolean enableHdAudio;
    boolean enableHwDec;
    boolean enableHwEnc;
    boolean enableMic;
    boolean enableSpeaker;
    int relationId;
    int videoRecvMode;

    public static class Builder {
        int audioCategory = 0;
        long authBits = -1;
        byte[] authBuffer = null;
        boolean autoCreateRoom = true;
        String controlRole = "";
        boolean enableHdAudio = false;
        boolean enableHwDec = true;
        boolean enableHwEnc = true;
        boolean enableMic = false;
        boolean enableSpeaker = false;
        int relationId;
        int videoRecvMode = 0;

        public Builder(int i) {
            this.relationId = i;
        }

        public Builder auth(long j, byte[] bArr) {
            this.authBits = j;
            this.authBuffer = bArr;
            return this;
        }

        public Builder avControlRole(String str) {
            this.controlRole = str;
            return this;
        }

        public Builder audioCategory(int i) {
            this.audioCategory = i;
            return this;
        }

        public Builder autoCreateRoom(boolean z) {
            this.autoCreateRoom = z;
            return this;
        }

        public Builder videoRecvMode(int i) {
            this.videoRecvMode = i;
            return this;
        }

        public Builder isEnableMic(boolean z) {
            this.enableMic = z;
            return this;
        }

        public Builder isEnableSpeaker(boolean z) {
            this.enableSpeaker = z;
            return this;
        }

        public Builder isEnableHdAudio(boolean z) {
            this.enableHdAudio = z;
            return this;
        }

        public Builder isEnableHwEnc(boolean z) {
            this.enableHwEnc = z;
            return this;
        }

        public Builder isEnableHwDec(boolean z) {
            this.enableHwDec = z;
            return this;
        }

        public AVRoomMulti$EnterParam build() {
            return new AVRoomMulti$EnterParam(this);
        }
    }

    AVRoomMulti$EnterParam(Builder builder) {
        this.relationId = builder.relationId;
        this.authBits = builder.authBits;
        this.authBuffer = builder.authBuffer;
        this.controlRole = builder.controlRole;
        this.audioCategory = builder.audioCategory;
        this.createRoom = builder.autoCreateRoom;
        this.videoRecvMode = builder.videoRecvMode;
        this.enableMic = builder.enableMic;
        this.enableSpeaker = builder.enableSpeaker;
        this.enableHdAudio = builder.enableHdAudio;
        this.enableHwEnc = builder.enableHwEnc;
        this.enableHwDec = builder.enableHwDec;
    }
}
