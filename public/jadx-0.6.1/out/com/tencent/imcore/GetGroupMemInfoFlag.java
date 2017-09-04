package com.tencent.imcore;

public enum GetGroupMemInfoFlag {
    kGetGroupMemInfoNone((String) 0),
    kGetGroupMemJionTime((String) 1),
    kGetGroupMemMsgFlag((String) 2),
    kGetGroupMemMsgSeq((String) 4),
    kGetGroupMemRole((String) 8),
    kGetGroupMemShutupUntill((String) 16),
    kGetGroupMemNameCard((String) 32);
    
    private final int swigValue;

    private static class aa {
        private static int a;

        static {
            a = 0;
        }

        static /* synthetic */ int a() {
            int i = a;
            a = i + 1;
            return i;
        }
    }

    private GetGroupMemInfoFlag(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private GetGroupMemInfoFlag(GetGroupMemInfoFlag getGroupMemInfoFlag) {
        this.swigValue = getGroupMemInfoFlag.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static GetGroupMemInfoFlag swigToEnum(int i) {
        GetGroupMemInfoFlag[] getGroupMemInfoFlagArr = (GetGroupMemInfoFlag[]) GetGroupMemInfoFlag.class.getEnumConstants();
        if (i < getGroupMemInfoFlagArr.length && i >= 0 && getGroupMemInfoFlagArr[i].swigValue == i) {
            return getGroupMemInfoFlagArr[i];
        }
        for (GetGroupMemInfoFlag getGroupMemInfoFlag : getGroupMemInfoFlagArr) {
            if (getGroupMemInfoFlag.swigValue == i) {
                return getGroupMemInfoFlag;
            }
        }
        throw new IllegalArgumentException("No enum " + GetGroupMemInfoFlag.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
