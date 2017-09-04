package com.tencent.imcore;

public enum GroupInfoChangeType {
    IMCORE_GROUP_INFO_CHAGE_TYPE_GROUP_NAME((String) 1),
    IMCORE_GROUP_INFO_CHAGE_TYPE_INTRODUCTION((String) 2),
    IMCORE_GROUP_INFO_CHAGE_TYPE_NOTIFACTION((String) 3),
    IMCORE_GROUP_INFO_CHAGE_TYPE_FACE_URL((String) 4),
    IMCORE_GROUP_INFO_CHAGE_TYPE_OWNER((String) 5);
    
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

    private GroupInfoChangeType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private GroupInfoChangeType(GroupInfoChangeType groupInfoChangeType) {
        this.swigValue = groupInfoChangeType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static GroupInfoChangeType swigToEnum(int i) {
        GroupInfoChangeType[] groupInfoChangeTypeArr = (GroupInfoChangeType[]) GroupInfoChangeType.class.getEnumConstants();
        if (i < groupInfoChangeTypeArr.length && i >= 0 && groupInfoChangeTypeArr[i].swigValue == i) {
            return groupInfoChangeTypeArr[i];
        }
        for (GroupInfoChangeType groupInfoChangeType : groupInfoChangeTypeArr) {
            if (groupInfoChangeType.swigValue == i) {
                return groupInfoChangeType;
            }
        }
        throw new IllegalArgumentException("No enum " + GroupInfoChangeType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
