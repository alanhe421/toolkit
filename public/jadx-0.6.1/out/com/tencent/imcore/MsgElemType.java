package com.tencent.imcore;

public enum MsgElemType {
    kInvalid((String) 0),
    kText,
    kPic,
    kPtt,
    kPicNew,
    kPttNew,
    kCustom,
    kFile,
    kSystem,
    kGroupTips,
    kFace,
    kLocation,
    kGroupReport,
    kFriendChange,
    kProfileChange,
    kVideo;
    
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

    private MsgElemType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private MsgElemType(MsgElemType msgElemType) {
        this.swigValue = msgElemType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static MsgElemType swigToEnum(int i) {
        MsgElemType[] msgElemTypeArr = (MsgElemType[]) MsgElemType.class.getEnumConstants();
        if (i < msgElemTypeArr.length && i >= 0 && msgElemTypeArr[i].swigValue == i) {
            return msgElemTypeArr[i];
        }
        for (MsgElemType msgElemType : msgElemTypeArr) {
            if (msgElemType.swigValue == i) {
                return msgElemType;
            }
        }
        throw new IllegalArgumentException("No enum " + MsgElemType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
