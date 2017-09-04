package com.tencent.imcore;

public enum FutureType {
    FutureTypePendencyComeIn((String) 1),
    FutureTypePendencySendOut((String) 2),
    FutureTypeRecommend((String) 4),
    FutureTypeDecide((String) 8);
    
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

    private FutureType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private FutureType(FutureType futureType) {
        this.swigValue = futureType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static FutureType swigToEnum(int i) {
        FutureType[] futureTypeArr = (FutureType[]) FutureType.class.getEnumConstants();
        if (i < futureTypeArr.length && i >= 0 && futureTypeArr[i].swigValue == i) {
            return futureTypeArr[i];
        }
        for (FutureType futureType : futureTypeArr) {
            if (futureType.swigValue == i) {
                return futureType;
            }
        }
        throw new IllegalArgumentException("No enum " + FutureType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
