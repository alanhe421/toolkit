package com.tencent.imcore;

public class IMCore {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected IMCore(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static IMCore get() {
        return new IMCore(internalJNI.IMCore_get(), false);
    }

    protected static long getCPtr(IMCore iMCore) {
        return iMCore == null ? 0 : iMCore.swigCPtr;
    }

    public void addIgnoreStoreSession(SessionType sessionType, String str) {
        internalJNI.IMCore_addIgnoreStoreSession(this.swigCPtr, this, sessionType.swigValue(), str);
    }

    public void clearLog(String str, long j) {
        internalJNI.IMCore_clearLog__SWIG_3(this.swigCPtr, this, str, j);
    }

    public void clearLog(String str, long j, String str2) {
        internalJNI.IMCore_clearLog__SWIG_2(this.swigCPtr, this, str, j, str2);
    }

    public void clearLog(String str, long j, String str2, long j2) {
        internalJNI.IMCore_clearLog__SWIG_1(this.swigCPtr, this, str, j, str2, j2);
    }

    public void clearLog(String str, long j, String str2, long j2, int i) {
        internalJNI.IMCore_clearLog__SWIG_0(this.swigCPtr, this, str, j, str2, j2, i);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public Context getContext() {
        return new Context(internalJNI.IMCore_getContext(this.swigCPtr, this), false);
    }

    public long getTime() {
        return internalJNI.IMCore_getTime(this.swigCPtr, this);
    }

    public IMCoreUser getUser(String str) {
        long IMCore_getUser = internalJNI.IMCore_getUser(this.swigCPtr, this, str);
        return IMCore_getUser == 0 ? null : new IMCoreUser(IMCore_getUser, true);
    }

    public String getVersion(int i) {
        return internalJNI.IMCore_getVersion(this.swigCPtr, this, i);
    }

    public boolean initOpenIM(String str, String str2, IEnv iEnv, String str3, String str4) {
        return internalJNI.IMCore_initOpenIM(this.swigCPtr, this, str, str2, IEnv.getCPtr(iEnv), iEnv, str3, str4);
    }

    public int initUser(int i, String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6, UserConfig userConfig, IInit iInit) {
        return internalJNI.IMCore_initUser(this.swigCPtr, this, i, str, str2, str3, str4, bArr, str5, str6, UserConfig.getCPtr(userConfig), userConfig, IInit.getCPtr(iInit), iInit);
    }

    public void lOGGERINIT(String str, String str2, boolean z, ILogMsgCallback iLogMsgCallback) {
        internalJNI.IMCore_lOGGERINIT(this.swigCPtr, this, str, str2, z, ILogMsgCallback.getCPtr(iLogMsgCallback), iLogMsgCallback);
    }

    public void lOGGERLOG(int i, String str, int i2, String str2, String str3, String str4) {
        internalJNI.IMCore_lOGGERLOG(this.swigCPtr, this, i, str, i2, str2, str3, str4);
    }

    public void lOGGERSETLOGCBLEVEL(String str) {
        internalJNI.IMCore_lOGGERSETLOGCBLEVEL(this.swigCPtr, this, str);
    }

    public void qrReportEvent(SdkReportItem sdkReportItem) {
        internalJNI.IMCore_qrReportEvent(this.swigCPtr, this, SdkReportItem.getCPtr(sdkReportItem), sdkReportItem);
    }

    public void setContext(Context context) {
        internalJNI.IMCore_setContext(this.swigCPtr, this, Context.getCPtr(context), context);
    }

    public int unInitUser(String str) {
        return internalJNI.IMCore_unInitUser(this.swigCPtr, this, str);
    }

    public boolean uploadLogFile(String str, UploadLogFileOpt uploadLogFileOpt) {
        return internalJNI.IMCore_uploadLogFile(this.swigCPtr, this, str, UploadLogFileOpt.getCPtr(uploadLogFileOpt), uploadLogFileOpt);
    }
}
