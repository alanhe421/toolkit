package qalsdk;

/* compiled from: SsoSocketConnStat */
public enum v {
    connInit,
    connStarting,
    connSucc,
    connClosing,
    connClosed,
    interrupted,
    connError_illegalargument,
    connError_enotsock,
    connError_permission,
    connError_ebadFileNum,
    connError_errno,
    connError_enobufs,
    connError_unreachable,
    connError_invalidArgument,
    connError_unresolved,
    connError_unknownhost,
    connError_noroute,
    connError_reset,
    connError_refused,
    connError_timeout,
    connError_unknown,
    recvSsoRespSucc,
    decodeSsoRespError,
    recvSsoData,
    recvSsoDataError;

    public static v a(String str) {
        return (v) Enum.valueOf(v.class, str);
    }

    public static v[] a() {
        return (v[]) z.clone();
    }
}
