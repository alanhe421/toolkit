package com.tencent.imcore;

public enum ComStatus {
    kNotSet((String) 0),
    kClose((String) 1),
    kOpen((String) 2);
    
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

    private ComStatus(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private ComStatus(ComStatus comStatus) {
        this.swigValue = comStatus.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static ComStatus swigToEnum(int i) {
        ComStatus[] comStatusArr = (ComStatus[]) ComStatus.class.getEnumConstants();
        if (i < comStatusArr.length && i >= 0 && comStatusArr[i].swigValue == i) {
            return comStatusArr[i];
        }
        for (ComStatus comStatus : comStatusArr) {
            if (comStatus.swigValue == i) {
                return comStatus;
            }
        }
        throw new IllegalArgumentException("No enum " + ComStatus.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
