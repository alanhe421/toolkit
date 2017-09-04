package oicq.wlogin_sdk.request;

import java.io.Serializable;
import java.util.TreeMap;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;

public class WloginAllSigInfo implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    public TreeMap<Long, WloginSigInfo> _tk_map = new TreeMap();
    public long _uin = 0;
    public WloginSimpleInfo _useInfo = new WloginSimpleInfo();
    public int mainSigMap;

    public int put_simpleinfo(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][] bArr5) {
        if (this._useInfo == null) {
            this._useInfo = new WloginSimpleInfo(j, bArr, bArr2, bArr3, bArr4, bArr5);
        } else {
            this._useInfo.set_info(j, bArr, bArr2, bArr3, bArr4, bArr5);
        }
        return 0;
    }

    public int put_siginfo(long j, long j2, long j3, long j4, long j5, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[][] bArr13, long[] jArr, int i) {
        WloginSigInfo wloginSigInfo = (WloginSigInfo) this._tk_map.get(Long.valueOf(j));
        if (wloginSigInfo != null) {
            wloginSigInfo.mainSigMap = this.mainSigMap;
            this._tk_map.put(Long.valueOf(j), wloginSigInfo.Set(j2, j3, j4, j5, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, jArr, i));
        } else {
            TreeMap treeMap = this._tk_map;
            Long valueOf = Long.valueOf(j);
            treeMap.put(valueOf, new WloginSigInfo(j2, j3, j4, j5, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, jArr, i, this.mainSigMap));
        }
        return 0;
    }

    public int put_siginfo(long j, byte[][] bArr, long j2) {
        WloginSigInfo wloginSigInfo = (WloginSigInfo) this._tk_map.get(Long.valueOf(j));
        if (wloginSigInfo != null) {
            this._tk_map.put(Long.valueOf(j), wloginSigInfo.Set(bArr, j2));
        }
        return 0;
    }

    public int put_siginfo(long j, long j2, long j3, byte[] bArr, byte[] bArr2) {
        this._tk_map.put(Long.valueOf(j), new WloginSigInfo(j2, j3, bArr, bArr2));
        return 0;
    }

    public int put_randseed(long j, byte[] bArr) {
        WloginSigInfo wloginSigInfo = (WloginSigInfo) this._tk_map.get(Long.valueOf(j));
        if (wloginSigInfo != null) {
            this._tk_map.put(Long.valueOf(j), wloginSigInfo.setRandSeed(bArr));
        }
        return 0;
    }

    public int put_da2(long j, byte[] bArr) {
        WloginSigInfo wloginSigInfo = (WloginSigInfo) this._tk_map.get(Long.valueOf(j));
        if (wloginSigInfo != null) {
            this._tk_map.put(Long.valueOf(j), wloginSigInfo.setDA2(bArr));
        }
        return 0;
    }

    public WloginAllSigInfo get_clone() {
        try {
            WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) clone();
            if (this._tk_map == null) {
                return wloginAllSigInfo;
            }
            for (Long longValue : this._tk_map.keySet()) {
                WloginSigInfo wloginSigInfo = (WloginSigInfo) this._tk_map.get(Long.valueOf(longValue.longValue()));
                if (wloginSigInfo != null) {
                    wloginSigInfo.cacheTickets = null;
                    wloginSigInfo.cacheUpdateStamp = 0;
                }
            }
            return wloginAllSigInfo;
        } catch (Exception e) {
            return null;
        }
    }
}
