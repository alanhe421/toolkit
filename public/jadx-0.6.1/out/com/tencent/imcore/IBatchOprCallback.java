package com.tencent.imcore;

public class IBatchOprCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public static class BatchOprDetailInfo {
        protected transient boolean swigCMemOwn;
        private transient long swigCPtr;

        public static class ErrInfo {
            protected transient boolean swigCMemOwn;
            private transient long swigCPtr;

            public ErrInfo() {
                this(internalJNI.new_IBatchOprCallback_BatchOprDetailInfo_ErrInfo(), true);
            }

            protected ErrInfo(long j, boolean z) {
                this.swigCMemOwn = z;
                this.swigCPtr = j;
            }

            protected static long getCPtr(ErrInfo errInfo) {
                return errInfo == null ? 0 : errInfo.swigCPtr;
            }

            public synchronized void delete() {
                if (this.swigCPtr != 0) {
                    if (this.swigCMemOwn) {
                        this.swigCMemOwn = false;
                        internalJNI.delete_IBatchOprCallback_BatchOprDetailInfo_ErrInfo(this.swigCPtr);
                    }
                    this.swigCPtr = 0;
                }
            }

            protected void finalize() {
                delete();
            }

            public int getErr_code() {
                return internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_code_get(this.swigCPtr, this);
            }

            public String getErr_msg() {
                return internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_msg_get(this.swigCPtr, this);
            }

            public String getId() {
                return internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_id_get(this.swigCPtr, this);
            }

            public void setErr_code(int i) {
                internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_code_set(this.swigCPtr, this, i);
            }

            public void setErr_msg(String str) {
                internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_msg_set(this.swigCPtr, this, str);
            }

            public void setId(String str) {
                internalJNI.IBatchOprCallback_BatchOprDetailInfo_ErrInfo_id_set(this.swigCPtr, this, str);
            }
        }

        public BatchOprDetailInfo() {
            this(internalJNI.new_IBatchOprCallback_BatchOprDetailInfo(), true);
        }

        protected BatchOprDetailInfo(long j, boolean z) {
            this.swigCMemOwn = z;
            this.swigCPtr = j;
        }

        protected static long getCPtr(BatchOprDetailInfo batchOprDetailInfo) {
            return batchOprDetailInfo == null ? 0 : batchOprDetailInfo.swigCPtr;
        }

        public synchronized void delete() {
            if (this.swigCPtr != 0) {
                if (this.swigCMemOwn) {
                    this.swigCMemOwn = false;
                    internalJNI.delete_IBatchOprCallback_BatchOprDetailInfo(this.swigCPtr);
                }
                this.swigCPtr = 0;
            }
        }

        protected void finalize() {
            delete();
        }

        public ErrInfoVec getErrs() {
            long IBatchOprCallback_BatchOprDetailInfo_errs_get = internalJNI.IBatchOprCallback_BatchOprDetailInfo_errs_get(this.swigCPtr, this);
            return IBatchOprCallback_BatchOprDetailInfo_errs_get == 0 ? null : new ErrInfoVec(IBatchOprCallback_BatchOprDetailInfo_errs_get, false);
        }

        public long getFail_num() {
            return internalJNI.IBatchOprCallback_BatchOprDetailInfo_fail_num_get(this.swigCPtr, this);
        }

        public long getSucc_num() {
            return internalJNI.IBatchOprCallback_BatchOprDetailInfo_succ_num_get(this.swigCPtr, this);
        }

        public void setErrs(ErrInfoVec errInfoVec) {
            internalJNI.IBatchOprCallback_BatchOprDetailInfo_errs_set(this.swigCPtr, this, ErrInfoVec.getCPtr(errInfoVec), errInfoVec);
        }

        public void setFail_num(long j) {
            internalJNI.IBatchOprCallback_BatchOprDetailInfo_fail_num_set(this.swigCPtr, this, j);
        }

        public void setSucc_num(long j) {
            internalJNI.IBatchOprCallback_BatchOprDetailInfo_succ_num_set(this.swigCPtr, this, j);
        }
    }

    public IBatchOprCallback() {
        this(internalJNI.new_IBatchOprCallback(), true);
        internalJNI.IBatchOprCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IBatchOprCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IBatchOprCallback iBatchOprCallback) {
        return iBatchOprCallback == null ? 0 : iBatchOprCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IBatchOprCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IBatchOprCallback.class) {
            internalJNI.IBatchOprCallback_done(this.swigCPtr, this);
        } else {
            internalJNI.IBatchOprCallback_doneSwigExplicitIBatchOprCallback(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str, BatchOprDetailInfo batchOprDetailInfo) {
        if (getClass() == IBatchOprCallback.class) {
            internalJNI.IBatchOprCallback_fail(this.swigCPtr, this, i, str, BatchOprDetailInfo.getCPtr(batchOprDetailInfo), batchOprDetailInfo);
            return;
        }
        internalJNI.IBatchOprCallback_failSwigExplicitIBatchOprCallback(this.swigCPtr, this, i, str, BatchOprDetailInfo.getCPtr(batchOprDetailInfo), batchOprDetailInfo);
    }

    protected void finalize() {
        delete();
    }

    public void setLogin(String str, boolean z) {
        if (getClass() == IBatchOprCallback.class) {
            internalJNI.IBatchOprCallback_setLogin(this.swigCPtr, this, str, z);
        } else {
            internalJNI.IBatchOprCallback_setLoginSwigExplicitIBatchOprCallback(this.swigCPtr, this, str, z);
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IBatchOprCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IBatchOprCallback_change_ownership(this, this.swigCPtr, true);
    }
}
