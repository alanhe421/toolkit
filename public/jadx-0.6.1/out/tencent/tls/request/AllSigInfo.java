package tencent.tls.request;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

class AllSigInfo implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    public TreeMap<Long, SigInfo> _tk_map = new TreeMap();

    AllSigInfo() {
    }

    public int put_siginfo(long j, long j2, long j3, ArrayList<Ticket> arrayList, int i) {
        SigInfo sigInfo = (SigInfo) this._tk_map.get(Long.valueOf(j));
        if (sigInfo != null) {
            this._tk_map.put(Long.valueOf(j), sigInfo.Set(j2, j3, arrayList, i));
        } else {
            this._tk_map.put(Long.valueOf(j), new SigInfo(j2, j3, arrayList, i));
        }
        return 0;
    }

    public int put_siginfo(long j, byte[] bArr, byte[] bArr2, long j2) {
        SigInfo sigInfo = (SigInfo) this._tk_map.get(Long.valueOf(j));
        if (sigInfo != null) {
            byte[][] bArr3 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{2, 0});
            for (int i = 0; i < bArr3.length; i++) {
                bArr3[i] = new byte[0];
            }
            bArr3[0] = bArr;
            bArr3[1] = bArr2;
            this._tk_map.put(Long.valueOf(j), sigInfo.Set(bArr3, j2));
        }
        return 0;
    }

    protected AllSigInfo clone() {
        try {
            AllSigInfo allSigInfo = (AllSigInfo) super.clone();
            allSigInfo._tk_map = (TreeMap) this._tk_map.clone();
            return allSigInfo;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
