package com.tencent.imcore;

public enum NotifyFlag {
    kC2CNotify((String) 1),
    kAllGroupNotify((String) 2),
    kAllNotify((String) 3);
    
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

    private NotifyFlag(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private NotifyFlag(NotifyFlag notifyFlag) {
        this.swigValue = notifyFlag.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static NotifyFlag swigToEnum(int i) {
        NotifyFlag[] notifyFlagArr = (NotifyFlag[]) NotifyFlag.class.getEnumConstants();
        if (i < notifyFlagArr.length && i >= 0 && notifyFlagArr[i].swigValue == i) {
            return notifyFlagArr[i];
        }
        for (NotifyFlag notifyFlag : notifyFlagArr) {
            if (notifyFlag.swigValue == i) {
                return notifyFlag;
            }
        }
        throw new IllegalArgumentException("No enum " + NotifyFlag.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
