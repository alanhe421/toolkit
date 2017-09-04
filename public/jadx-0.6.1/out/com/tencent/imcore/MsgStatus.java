package com.tencent.imcore;

public enum MsgStatus {
    kSending((String) 1),
    kSendSucc((String) 2),
    kSendFail((String) 3),
    kHasDeleted((String) 4),
    kLocalImported((String) 5);
    
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

    private MsgStatus(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private MsgStatus(MsgStatus msgStatus) {
        this.swigValue = msgStatus.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static MsgStatus swigToEnum(int i) {
        MsgStatus[] msgStatusArr = (MsgStatus[]) MsgStatus.class.getEnumConstants();
        if (i < msgStatusArr.length && i >= 0 && msgStatusArr[i].swigValue == i) {
            return msgStatusArr[i];
        }
        for (MsgStatus msgStatus : msgStatusArr) {
            if (msgStatus.swigValue == i) {
                return msgStatus;
            }
        }
        throw new IllegalArgumentException("No enum " + MsgStatus.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
