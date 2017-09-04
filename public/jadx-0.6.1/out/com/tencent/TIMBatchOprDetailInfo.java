package com.tencent;

import com.tencent.imcore.ErrInfoVec;
import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo;
import java.util.ArrayList;
import java.util.List;

public class TIMBatchOprDetailInfo {
    private List<ErrInfo> errors = new ArrayList();
    private long failNum;
    private long succNum;

    public TIMBatchOprDetailInfo(BatchOprDetailInfo batchOprDetailInfo) {
        if (batchOprDetailInfo != null) {
            this.succNum = batchOprDetailInfo.getSucc_num();
            this.failNum = batchOprDetailInfo.getFail_num();
            ErrInfoVec errs = batchOprDetailInfo.getErrs();
            long size = errs.size();
            for (int i = 0; ((long) i) < size; i++) {
                this.errors.add(new ErrInfo(this, errs.get(i)));
            }
        }
    }

    public List<ErrInfo> getErrors() {
        return this.errors;
    }

    public long getFailNum() {
        return this.failNum;
    }

    public long getSuccNum() {
        return this.succNum;
    }
}
