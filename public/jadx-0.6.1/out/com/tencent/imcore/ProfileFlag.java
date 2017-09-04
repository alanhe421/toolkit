package com.tencent.imcore;

public enum ProfileFlag {
    kProfileFlagNone((String) 0),
    kProfileFlagNick((String) 1),
    kProfileFlagAllowType((String) 2),
    kProfileFlagFaceUrl((String) 4),
    kProfileFlagRemark((String) 8),
    kProfileFlagGroup((String) 16),
    kProfileFlagSelfSignature((String) 32),
    kProfileFlagGender((String) 64),
    kProfileFlagBirthday((String) 128),
    kProfileFlagLocation((String) 256),
    kProfileFlagLanguage((String) 512);
    
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

    private ProfileFlag(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private ProfileFlag(ProfileFlag profileFlag) {
        this.swigValue = profileFlag.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static ProfileFlag swigToEnum(int i) {
        ProfileFlag[] profileFlagArr = (ProfileFlag[]) ProfileFlag.class.getEnumConstants();
        if (i < profileFlagArr.length && i >= 0 && profileFlagArr[i].swigValue == i) {
            return profileFlagArr[i];
        }
        for (ProfileFlag profileFlag : profileFlagArr) {
            if (profileFlag.swigValue == i) {
                return profileFlag;
            }
        }
        throw new IllegalArgumentException("No enum " + ProfileFlag.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
