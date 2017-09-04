package qalsdk;

/* compiled from: RegPushReason */
public enum am {
    msfBoot,
    appRegister,
    c,
    msfHeartTimeTooLong,
    msfByNetChange,
    serverPush,
    fillRegProxy,
    createDefaultRegInfo,
    setAppQuit;

    public static am a(String str) {
        return (am) Enum.valueOf(am.class, str);
    }

    public static am[] a() {
        return (am[]) j.clone();
    }
}
