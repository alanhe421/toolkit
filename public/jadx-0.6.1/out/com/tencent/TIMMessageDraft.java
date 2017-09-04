package com.tencent;

import com.tencent.imcore.Draft;
import com.tencent.imcore.Elem;
import com.tencent.imcore.ElemVec;
import java.util.ArrayList;
import java.util.List;

public class TIMMessageDraft {
    private List<TIMElem> elems = new ArrayList();
    private long timestamp;
    private byte[] userDefinedData;

    protected static TIMMessageDraft convertFrom(Draft draft) {
        if (draft == null) {
            return null;
        }
        TIMMessageDraft tIMMessageDraft = new TIMMessageDraft();
        tIMMessageDraft.setTimestamp(draft.getUint64_edit_time());
        tIMMessageDraft.setUserDefinedData(draft.getUser_define());
        ElemVec elems = draft.getElems();
        if (elems != null) {
            for (int i = 0; ((long) i) < elems.size(); i++) {
                TIMElem convertFrom = TIMElem.convertFrom(elems.get(i));
                if (convertFrom.getType() != TIMElemType.Invalid) {
                    tIMMessageDraft.addElem(convertFrom);
                }
            }
        }
        return tIMMessageDraft;
    }

    public void addElem(TIMElem tIMElem) {
        this.elems.add(tIMElem);
    }

    protected Draft convertTo() {
        Draft draft = new Draft();
        if (!this.elems.isEmpty()) {
            ElemVec elemVec = new ElemVec();
            for (TIMElem convertTo : this.elems) {
                Elem convertTo2 = convertTo.convertTo();
                if (convertTo2 != null) {
                    elemVec.pushBack(convertTo2);
                }
            }
            draft.setElems(elemVec);
        }
        if (this.userDefinedData != null) {
            draft.setUser_define(this.userDefinedData);
        }
        return draft;
    }

    public List<TIMElem> getElems() {
        return this.elems;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public byte[] getUserDefinedData() {
        return this.userDefinedData;
    }

    protected void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setUserDefinedData(byte[] bArr) {
        this.userDefinedData = bArr;
    }
}
