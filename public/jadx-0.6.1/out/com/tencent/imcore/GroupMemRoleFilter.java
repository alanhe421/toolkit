package com.tencent.imcore;

public enum GroupMemRoleFilter {
    kGroupMemberAll((String) 0),
    kGroupMemRoleOwner((String) 1),
    kGroupMemRoleAdmin((String) 2),
    kGroupMemRoleCommon_member((String) 4);
    
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

    private GroupMemRoleFilter(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private GroupMemRoleFilter(GroupMemRoleFilter groupMemRoleFilter) {
        this.swigValue = groupMemRoleFilter.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static GroupMemRoleFilter swigToEnum(int i) {
        GroupMemRoleFilter[] groupMemRoleFilterArr = (GroupMemRoleFilter[]) GroupMemRoleFilter.class.getEnumConstants();
        if (i < groupMemRoleFilterArr.length && i >= 0 && groupMemRoleFilterArr[i].swigValue == i) {
            return groupMemRoleFilterArr[i];
        }
        for (GroupMemRoleFilter groupMemRoleFilter : groupMemRoleFilterArr) {
            if (groupMemRoleFilter.swigValue == i) {
                return groupMemRoleFilter;
            }
        }
        throw new IllegalArgumentException("No enum " + GroupMemRoleFilter.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
