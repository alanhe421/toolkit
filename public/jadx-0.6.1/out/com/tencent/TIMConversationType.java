package com.tencent;

import com.tencent.imcore.SessionType;

public enum TIMConversationType {
    Invalid,
    C2C,
    Group,
    System;

    static TIMConversationType getType(SessionType sessionType) {
        if (sessionType != SessionType.kNull) {
            if (sessionType == SessionType.kC2C) {
                return C2C;
            }
            if (sessionType == SessionType.kGroup) {
                return Group;
            }
            if (sessionType == SessionType.kSystemSessionType) {
                return System;
            }
        }
        return Invalid;
    }

    static SessionType getType(TIMConversationType tIMConversationType) {
        SessionType sessionType = SessionType.kNull;
        switch (ce.a[tIMConversationType.ordinal()]) {
            case 1:
                return SessionType.kNull;
            case 2:
                return SessionType.kC2C;
            case 3:
                return SessionType.kGroup;
            case 4:
                return SessionType.kSystemSessionType;
            default:
                return sessionType;
        }
    }
}
