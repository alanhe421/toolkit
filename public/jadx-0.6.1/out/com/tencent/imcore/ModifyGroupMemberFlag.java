package com.tencent.imcore;

public enum ModifyGroupMemberFlag {
    kModifyGroupMemberNone((String) 0),
    kModifyGroupMemberMsgFlag((String) 1),
    kModifyGroupMemberRole((String) 2),
    kModifyGroupMemberShutupTime((String) 4),
    kModifyGroupMemberNameCard((String) 8);
    
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

    private ModifyGroupMemberFlag(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private ModifyGroupMemberFlag(ModifyGroupMemberFlag modifyGroupMemberFlag) {
        this.swigValue = modifyGroupMemberFlag.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static ModifyGroupMemberFlag swigToEnum(int i) {
        ModifyGroupMemberFlag[] modifyGroupMemberFlagArr = (ModifyGroupMemberFlag[]) ModifyGroupMemberFlag.class.getEnumConstants();
        if (i < modifyGroupMemberFlagArr.length && i >= 0 && modifyGroupMemberFlagArr[i].swigValue == i) {
            return modifyGroupMemberFlagArr[i];
        }
        for (ModifyGroupMemberFlag modifyGroupMemberFlag : modifyGroupMemberFlagArr) {
            if (modifyGroupMemberFlag.swigValue == i) {
                return modifyGroupMemberFlag;
            }
        }
        throw new IllegalArgumentException("No enum " + ModifyGroupMemberFlag.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
