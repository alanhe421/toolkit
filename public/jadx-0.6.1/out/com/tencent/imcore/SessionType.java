package com.tencent.imcore;

public enum SessionType {
    kNull((String) 0),
    kC2C,
    kGroup,
    kSystemSessionType;
    
    private final int swigValue;

    private SessionType(int i) {
        this.swigValue = i;
        aa.a(i + 1);
    }

    private SessionType(SessionType sessionType) {
        this.swigValue = sessionType.swigValue;
        aa.a(this.swigValue + 1);
    }

    public static SessionType swigToEnum(int i) {
        SessionType[] sessionTypeArr = (SessionType[]) SessionType.class.getEnumConstants();
        if (i < sessionTypeArr.length && i >= 0 && sessionTypeArr[i].swigValue == i) {
            return sessionTypeArr[i];
        }
        for (SessionType sessionType : sessionTypeArr) {
            if (sessionType.swigValue == i) {
                return sessionType;
            }
        }
        throw new IllegalArgumentException("No enum " + SessionType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
