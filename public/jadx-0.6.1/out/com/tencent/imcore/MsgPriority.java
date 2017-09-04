package com.tencent.imcore;

public enum MsgPriority {
    MsgPriorityHigh((String) 1),
    MsgPriorityNormal((String) 2),
    MsgPriorityLow((String) 3),
    MsgPriorityLowest((String) 4);
    
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

    private MsgPriority(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private MsgPriority(MsgPriority msgPriority) {
        this.swigValue = msgPriority.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static MsgPriority swigToEnum(int i) {
        MsgPriority[] msgPriorityArr = (MsgPriority[]) MsgPriority.class.getEnumConstants();
        if (i < msgPriorityArr.length && i >= 0 && msgPriorityArr[i].swigValue == i) {
            return msgPriorityArr[i];
        }
        for (MsgPriority msgPriority : msgPriorityArr) {
            if (msgPriority.swigValue == i) {
                return msgPriority;
            }
        }
        throw new IllegalArgumentException("No enum " + MsgPriority.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
