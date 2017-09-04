package com.tencent.av.channel;

public abstract class AVAppChannel {

    public interface CsCmdCallback {
        void onError(int i, String str);

        void onSuccess(byte[] bArr);
    }

    public interface IdToIdCallback {
        void onError(int i, String str);

        void onSuccess(String[] strArr, long[] jArr);
    }

    public abstract long getTinyId();

    public abstract boolean requestAppCmd(byte[] bArr, CsCmdCallback csCmdCallback);

    public abstract boolean requestCmd(String str, byte[] bArr, CsCmdCallback csCmdCallback);

    public abstract boolean requestInfoCmd(byte[] bArr, CsCmdCallback csCmdCallback);

    public abstract boolean requestReportCmd(int i, byte[] bArr, CsCmdCallback csCmdCallback);

    public boolean identifierToTinyId(String str, String str2, String[] strArr, IdToIdCallback idToIdCallback) {
        long[] jArr = new long[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            jArr[i] = Long.parseLong(strArr[i]);
        }
        idToIdCallback.onSuccess(strArr, jArr);
        return true;
    }

    public boolean tinyIdToIdentifier(long[] jArr, IdToIdCallback idToIdCallback) {
        String[] strArr = new String[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            strArr[i] = Long.toString(jArr[i]);
        }
        idToIdCallback.onSuccess(strArr, jArr);
        return true;
    }

    public int getServerEnvType() {
        return -1;
    }
}
