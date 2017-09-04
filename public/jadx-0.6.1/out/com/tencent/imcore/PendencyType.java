package com.tencent.imcore;

public enum PendencyType {
    PendencyTypeComeIn((String) 1),
    PendencyTypeSendOut((String) 2),
    PendencyTypeBoth((String) 3);
    
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

    private PendencyType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private PendencyType(PendencyType pendencyType) {
        this.swigValue = pendencyType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static PendencyType swigToEnum(int i) {
        PendencyType[] pendencyTypeArr = (PendencyType[]) PendencyType.class.getEnumConstants();
        if (i < pendencyTypeArr.length && i >= 0 && pendencyTypeArr[i].swigValue == i) {
            return pendencyTypeArr[i];
        }
        for (PendencyType pendencyType : pendencyTypeArr) {
            if (pendencyType.swigValue == i) {
                return pendencyType;
            }
        }
        throw new IllegalArgumentException("No enum " + PendencyType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
