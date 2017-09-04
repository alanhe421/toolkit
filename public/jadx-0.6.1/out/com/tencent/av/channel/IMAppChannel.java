package com.tencent.av.channel;

import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.av.channel.AVAppChannel.CsCmdCallback;
import com.tencent.av.channel.AVAppChannel.IdToIdCallback;
import com.tencent.timint.TIMIntManager;
import java.util.ArrayList;
import java.util.List;

public class IMAppChannel extends AVAppChannel {

    public class CsCmdCallbackImpl implements TIMValueCallBack<byte[]> {
        private CsCmdCallback mCallback;

        public CsCmdCallbackImpl(CsCmdCallback csCmdCallback) {
            this.mCallback = csCmdCallback;
        }

        public void onError(int i, String str) {
            if (this.mCallback != null) {
                this.mCallback.onError(i, str);
                this.mCallback = null;
            }
        }

        public void onSuccess(byte[] bArr) {
            if (this.mCallback != null) {
                this.mCallback.onSuccess(bArr);
                this.mCallback = null;
            }
        }
    }

    public class IdToIdCallbackImpl implements TIMValueCallBack<List<TIMUser>> {
        private IdToIdCallback mCallback;

        public IdToIdCallbackImpl(IdToIdCallback idToIdCallback) {
            this.mCallback = idToIdCallback;
        }

        public void onError(int i, String str) {
            if (this.mCallback != null) {
                this.mCallback.onError(i, str);
                this.mCallback = null;
            }
        }

        public void onSuccess(List<TIMUser> list) {
            if (this.mCallback != null) {
                int size = list.size();
                String[] strArr = new String[size];
                long[] jArr = new long[size];
                for (int i = 0; i < size; i++) {
                    TIMUser tIMUser = (TIMUser) list.get(i);
                    strArr[i] = tIMUser.getIdentifier();
                    jArr[i] = tIMUser.getTinyId();
                }
                this.mCallback.onSuccess(strArr, jArr);
                this.mCallback = null;
            }
        }
    }

    public class ReportCallbackImpl implements TIMCallBack {
        private CsCmdCallback mCallback;

        public ReportCallbackImpl(CsCmdCallback csCmdCallback) {
            this.mCallback = csCmdCallback;
        }

        public void onError(int i, String str) {
            if (this.mCallback != null) {
                this.mCallback.onError(i, str);
                this.mCallback = null;
            }
        }

        public void onSuccess() {
            if (this.mCallback != null) {
                this.mCallback.onSuccess(null);
                this.mCallback = null;
            }
        }
    }

    public long getTinyId() {
        return TIMIntManager.getInstance().getTinyId();
    }

    public boolean requestAppCmd(byte[] bArr, CsCmdCallback csCmdCallback) {
        TIMIntManager.getInstance().requestMultiVideoApp(bArr, new CsCmdCallbackImpl(csCmdCallback));
        return true;
    }

    public boolean requestInfoCmd(byte[] bArr, CsCmdCallback csCmdCallback) {
        TIMIntManager.getInstance().requestMultiVideoInfo(bArr, new CsCmdCallbackImpl(csCmdCallback));
        return true;
    }

    public boolean requestCmd(String str, byte[] bArr, CsCmdCallback csCmdCallback) {
        TIMIntManager.getInstance().request(str, bArr, new CsCmdCallbackImpl(csCmdCallback));
        return true;
    }

    public boolean requestReportCmd(int i, byte[] bArr, CsCmdCallback csCmdCallback) {
        TIMIntManager.getInstance().requestQualityReport(i, bArr, new ReportCallbackImpl(csCmdCallback));
        return true;
    }

    public boolean identifierToTinyId(String str, String str2, String[] strArr, IdToIdCallback idToIdCallback) {
        List arrayList = new ArrayList();
        for (Object add : strArr) {
            arrayList.add(add);
        }
        TIMIntManager.getInstance().userIdToTinyId(arrayList, new IdToIdCallbackImpl(idToIdCallback));
        return true;
    }

    public boolean tinyIdToIdentifier(long[] jArr, IdToIdCallback idToIdCallback) {
        List arrayList = new ArrayList();
        for (long valueOf : jArr) {
            arrayList.add(Long.valueOf(valueOf));
        }
        TIMIntManager.getInstance().tinyIdToUserId(arrayList, new IdToIdCallbackImpl(idToIdCallback));
        return true;
    }

    public int getServerEnvType() {
        return TIMManager.getInstance().getEnv();
    }
}
