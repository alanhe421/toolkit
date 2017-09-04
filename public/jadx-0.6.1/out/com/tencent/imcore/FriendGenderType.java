package com.tencent.imcore;

public enum FriendGenderType {
    kGenderUnkonw((String) 0),
    kGenderMale((String) 1),
    kGenderFemale((String) 2);
    
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

    private FriendGenderType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private FriendGenderType(FriendGenderType friendGenderType) {
        this.swigValue = friendGenderType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static FriendGenderType swigToEnum(int i) {
        FriendGenderType[] friendGenderTypeArr = (FriendGenderType[]) FriendGenderType.class.getEnumConstants();
        if (i < friendGenderTypeArr.length && i >= 0 && friendGenderTypeArr[i].swigValue == i) {
            return friendGenderTypeArr[i];
        }
        for (FriendGenderType friendGenderType : friendGenderTypeArr) {
            if (friendGenderType.swigValue == i) {
                return friendGenderType;
            }
        }
        throw new IllegalArgumentException("No enum " + FriendGenderType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
