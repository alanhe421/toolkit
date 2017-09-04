package com.tencent.imcore;

import com.tencent.tesla.soload.SoLoadCore;

public enum GetBaseInfoFlag {
    kGetGroupBaseInfoNone((String) 0),
    kGetGroupBaseInfoName((String) 1),
    kGetGroupBaseInfoCreateTime((String) 2),
    kGetGroupBaseInfoOwnerUin((String) 4),
    kGetGroupBaseInfoSeq((String) 8),
    kGetGroupBaseInfoTime((String) 16),
    kGetGroupBaseInfoNextMsgSeq((String) 32),
    kGetGroupBaseInfoLastMsgTime((String) 64),
    kGetGroupBaseInfoAppId((String) 128),
    kGetGroupBaseInfoMemberNum((String) 256),
    kGetGroupBaseInfoMaxMemberNum((String) 512),
    kGetGroupBaseInfoNotification((String) 1024),
    kGetGroupBaseInfoIntroduction((String) 2048),
    kGetGroupBaseInfoFaceUrl((String) 4096),
    kGetGroupBaseInfoAddOpton((String) 8192),
    kGetGroupBaseInfoGroupType((String) 16384),
    kGetGroupBaseInfoLastMsg((String) 32768),
    kGetGroupBaseInfoOnlineNum((String) 65536),
    kGetGroupBaseInfoVisible((String) SoLoadCore.IF_SO_CONFIG_EXIST),
    kGetGroupBaseInfoSearchable((String) 262144);
    
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

    private GetBaseInfoFlag(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private GetBaseInfoFlag(GetBaseInfoFlag getBaseInfoFlag) {
        this.swigValue = getBaseInfoFlag.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static GetBaseInfoFlag swigToEnum(int i) {
        GetBaseInfoFlag[] getBaseInfoFlagArr = (GetBaseInfoFlag[]) GetBaseInfoFlag.class.getEnumConstants();
        if (i < getBaseInfoFlagArr.length && i >= 0 && getBaseInfoFlagArr[i].swigValue == i) {
            return getBaseInfoFlagArr[i];
        }
        for (GetBaseInfoFlag getBaseInfoFlag : getBaseInfoFlagArr) {
            if (getBaseInfoFlag.swigValue == i) {
                return getBaseInfoFlag;
            }
        }
        throw new IllegalArgumentException("No enum " + GetBaseInfoFlag.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
