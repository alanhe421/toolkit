package oicq.wlogin_sdk.sharemem;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import oicq.wlogin_sdk.request.Ticket;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.util;

public class WloginSigInfo implements Parcelable, Serializable {
    public static final Creator<WloginSigInfo> CREATOR = new Creator<WloginSigInfo>() {
        public WloginSigInfo createFromParcel(Parcel parcel) {
            return new WloginSigInfo(parcel);
        }

        public WloginSigInfo[] newArray(int i) {
            return new WloginSigInfo[i];
        }
    };
    public static final int SIG_RESERVE_LENGTH = 15;
    public static final int SIG_RESERVE_VALID_LENGTH = 7;
    public static byte[] _LHSig = new byte[0];
    public static byte[] _QRPUSHSig = new byte[0];
    private static final long serialVersionUID = 1;
    public long _A2_create_time;
    public long _A2_expire_time;
    public byte[] _D2;
    public byte[] _D2Key;
    public long _D2_create_time;
    public long _D2_expire_time;
    public byte[] _DA2;
    public byte[] _G;
    public byte[] _TGT;
    public byte[] _TGTKey;
    public byte[] _access_token;
    public long _access_token_create_time;
    public long _app_pri;
    public byte[] _aqSig;
    public long _aqSig_create_time;
    public long _create_time;
    public byte[] _dpwd;
    public byte[] _en_A1;
    public int _login_bitmap;
    public byte[] _lsKey;
    public long _lsKey_create_time;
    public long _lsKey_expire_time;
    public byte[] _noPicSig;
    public byte[] _openid;
    public byte[] _openkey;
    public long _openkey_create_time;
    public byte[] _pay_token;
    public byte[] _pf;
    public byte[] _pfKey;
    public byte[] _psKey;
    public long _psKey_create_time;
    public byte[] _pt4Token;
    public byte[] _randseed;
    public byte[] _sKey;
    public long _sKey_create_time;
    public long _sKey_expire_time;
    public byte[] _sid;
    public long _sid_create_time;
    public long _sid_expire_time;
    public byte[] _superKey;
    public byte[] _userA5;
    public long _userA5_create_time;
    public byte[] _userA8;
    public long _userA8_create_time;
    public long _userA8_expire_time;
    public byte[] _userSig64;
    public long _userSig64_create_time;
    public byte[] _userStSig;
    public long _userStSig_create_time;
    public byte[] _userStWebSig;
    public long _userStWebSig_create_time;
    public long _userStWebSig_expire_time;
    public byte[] _userSt_Key;
    public long _vKey_expire_time;
    public byte[] _vkey;
    public long _vkey_create_time;
    public transient List<Ticket> cacheTickets;
    public transient long cacheUpdateStamp;
    public int mainSigMap;
    public byte[] wtSessionTicket;
    public long wtSessionTicketCreatTime;
    public byte[] wtSessionTicketKey;

    public boolean isWtSessionTicketExpired() {
        long j = this.wtSessionTicketCreatTime + 2592000;
        long f = u.f();
        if (f > j) {
            return true;
        }
        if (j <= 2592000 + f) {
            return false;
        }
        util.LOGI("time for system may be  modified manually expireTime " + j + " current " + f, "");
        return true;
    }

    public String toString() {
        int i = 0;
        try {
            Locale locale = Locale.getDefault();
            String str = "{A1:%d,%d A2:%d pskey:%d,%d pt4token:%d wtST:%d da2:%d}";
            Object[] objArr = new Object[8];
            objArr[0] = Integer.valueOf(this._en_A1.length);
            objArr[1] = Long.valueOf(this._create_time);
            objArr[2] = Integer.valueOf(this._TGT.length);
            objArr[3] = Integer.valueOf(this._psKey.length);
            objArr[4] = Long.valueOf(this._psKey_create_time);
            objArr[5] = Integer.valueOf(this._pt4Token.length);
            if (this.wtSessionTicket != null) {
                i = this.wtSessionTicket.length;
            }
            objArr[6] = Integer.valueOf(i);
            objArr[7] = Integer.valueOf(this._DA2.length);
            return String.format(locale, str, objArr);
        } catch (Exception e) {
            return "WloginSigInfo";
        }
    }

    public String getAllTicketString() {
        return "A1[" + this._en_A1.length + "][" + this._create_time + "]\nA2[" + this._TGT.length + "][0]\nPskey[" + this._psKey.length + "][" + this._psKey_create_time + "]\nPt4token[" + this._pt4Token.length + "][0]\nWtST[" + this.wtSessionTicket.length + "][0]\nWtSTKey" + this.wtSessionTicketKey.length + "][0]\n";
    }

    public WloginSigInfo(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[][] bArr13, long[] jArr, int i) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._userStSig = new byte[0];
        this._userSt_Key = new byte[0];
        this._userStWebSig = new byte[0];
        this._userA5 = new byte[0];
        this._userA8 = new byte[0];
        this._lsKey = new byte[0];
        this._sKey = new byte[0];
        this._userSig64 = new byte[0];
        this._openid = new byte[0];
        this._openkey = new byte[0];
        this._vkey = new byte[0];
        this._en_A1 = new byte[0];
        this._access_token = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._sid = new byte[0];
        this._noPicSig = new byte[0];
        this._aqSig = new byte[0];
        this._psKey = new byte[0];
        this._pt4Token = new byte[0];
        this._superKey = new byte[0];
        this._G = new byte[0];
        this._dpwd = new byte[0];
        this._randseed = new byte[0];
        this._pay_token = new byte[0];
        this._pf = new byte[0];
        this._pfKey = new byte[0];
        this._DA2 = new byte[0];
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A2_expire_time = 0;
        this._lsKey_expire_time = 0;
        this._sKey_expire_time = 0;
        this._vKey_expire_time = 0;
        this._userA8_expire_time = 0;
        this._userStWebSig_expire_time = 0;
        this._D2_expire_time = 0;
        this._sid_expire_time = 0;
        this._create_time = 0;
        this._A2_create_time = 0;
        this._userStSig_create_time = 0;
        this._userStWebSig_create_time = 0;
        this._userA5_create_time = 0;
        this._userA8_create_time = 0;
        this._lsKey_create_time = 0;
        this._sKey_create_time = 0;
        this._userSig64_create_time = 0;
        this._openkey_create_time = 0;
        this._vkey_create_time = 0;
        this._access_token_create_time = 0;
        this._D2_create_time = 0;
        this._sid_create_time = 0;
        this._aqSig_create_time = 0;
        this._psKey_create_time = 0;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
        this.cacheTickets = null;
        SetSigInfo(j, j2, j3, j4, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, jArr, i);
    }

    public WloginSigInfo(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[][] bArr13, long[] jArr, int i, int i2) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._userStSig = new byte[0];
        this._userSt_Key = new byte[0];
        this._userStWebSig = new byte[0];
        this._userA5 = new byte[0];
        this._userA8 = new byte[0];
        this._lsKey = new byte[0];
        this._sKey = new byte[0];
        this._userSig64 = new byte[0];
        this._openid = new byte[0];
        this._openkey = new byte[0];
        this._vkey = new byte[0];
        this._en_A1 = new byte[0];
        this._access_token = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._sid = new byte[0];
        this._noPicSig = new byte[0];
        this._aqSig = new byte[0];
        this._psKey = new byte[0];
        this._pt4Token = new byte[0];
        this._superKey = new byte[0];
        this._G = new byte[0];
        this._dpwd = new byte[0];
        this._randseed = new byte[0];
        this._pay_token = new byte[0];
        this._pf = new byte[0];
        this._pfKey = new byte[0];
        this._DA2 = new byte[0];
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A2_expire_time = 0;
        this._lsKey_expire_time = 0;
        this._sKey_expire_time = 0;
        this._vKey_expire_time = 0;
        this._userA8_expire_time = 0;
        this._userStWebSig_expire_time = 0;
        this._D2_expire_time = 0;
        this._sid_expire_time = 0;
        this._create_time = 0;
        this._A2_create_time = 0;
        this._userStSig_create_time = 0;
        this._userStWebSig_create_time = 0;
        this._userA5_create_time = 0;
        this._userA8_create_time = 0;
        this._lsKey_create_time = 0;
        this._sKey_create_time = 0;
        this._userSig64_create_time = 0;
        this._openkey_create_time = 0;
        this._vkey_create_time = 0;
        this._access_token_create_time = 0;
        this._D2_create_time = 0;
        this._sid_create_time = 0;
        this._aqSig_create_time = 0;
        this._psKey_create_time = 0;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
        this.cacheTickets = null;
        this.mainSigMap = i2;
        SetSigInfo(j, j2, j3, j4, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, jArr, i);
    }

    public void SetSigInfo(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[][] bArr13, long[] jArr, int i) {
        if (bArr13 == null || bArr13.length != 15) {
            util.LOGI("ERROR:reserve null or length not right", "");
        } else if (jArr == null || jArr.length != 7) {
            util.LOGI("reserve_valid null or length not right", "");
        } else {
            int i2;
            int i3;
            this._app_pri = j;
            this._A2_expire_time = j4;
            this._login_bitmap |= i;
            if (bArr != null && bArr.length > 0) {
                this._TGT = (byte[]) bArr.clone();
                this._A2_create_time = j2;
            }
            if (bArr2 != null && bArr2.length > 0) {
                this._TGTKey = (byte[]) bArr2.clone();
            }
            if (bArr3 != null && bArr3.length > 0) {
                this._userStSig = (byte[]) bArr3.clone();
                this._userStSig_create_time = j2;
            }
            if (bArr4 != null && bArr4.length > 0) {
                this._userSt_Key = (byte[]) bArr4.clone();
            }
            if (bArr5 != null && bArr5.length > 0) {
                this._userStWebSig = (byte[]) bArr5.clone();
                this._userStWebSig_create_time = j2;
                this._userStWebSig_expire_time = jArr[4] + j2;
            }
            if (bArr6 != null && bArr6.length > 0) {
                this._userA5 = (byte[]) bArr6.clone();
                this._userA5_create_time = j2;
            }
            if (bArr7 != null && bArr7.length > 0) {
                this._userA8 = (byte[]) bArr7.clone();
                this._userA8_create_time = j2;
                this._userA8_expire_time = jArr[3] + j2;
            }
            if (bArr8 != null && bArr8.length > 0) {
                this._lsKey = (byte[]) bArr8.clone();
                this._lsKey_create_time = j2;
                this._lsKey_expire_time = jArr[0] + j2;
            }
            if (bArr9 != null && bArr9.length > 0) {
                this._sKey = (byte[]) bArr9.clone();
                this._sKey_create_time = j2;
                this._sKey_expire_time = jArr[1] + j2;
            }
            if (bArr10 != null && bArr10.length > 0) {
                this._userSig64 = (byte[]) bArr10.clone();
                this._userSig64_create_time = j2;
            }
            if (bArr11 != null && bArr11.length > 0) {
                this._openid = (byte[]) bArr11.clone();
            }
            if (bArr12 != null && bArr12.length > 0) {
                this._openkey = (byte[]) bArr12.clone();
                this._openkey_create_time = j2;
            }
            if (bArr13[0] != null && bArr13[0].length > 0) {
                this._vkey = (byte[]) bArr13[0].clone();
                this._vkey_create_time = j2;
                this._vKey_expire_time = jArr[2] + j2;
            }
            if (bArr13[1] != null && bArr13[1].length > 0) {
                this._access_token = (byte[]) bArr13[1].clone();
                this._access_token_create_time = j2;
            }
            if (bArr13[2] != null && bArr13[2].length > 0) {
                this._D2 = (byte[]) bArr13[2].clone();
                this._D2_create_time = j2;
                this._D2_expire_time = jArr[5] + j2;
            }
            if (bArr13[3] != null && bArr13[3].length > 0) {
                this._D2Key = (byte[]) bArr13[3].clone();
            }
            if (bArr13[4] != null && bArr13[4].length > 0) {
                this._sid = (byte[]) bArr13[4].clone();
                this._sid_create_time = j2;
                this._sid_expire_time = jArr[6] + j2;
            }
            if (bArr13[5] != null && bArr13[5].length > 0) {
                this._aqSig = (byte[]) bArr13[5].clone();
                this._aqSig_create_time = j2;
            }
            if (bArr13[6] != null && bArr13[6].length > 2) {
                this._psKey = (byte[]) bArr13[6].clone();
                this._psKey_create_time = j2;
            }
            if (bArr13[7] != null && bArr13[7].length > 0) {
                this._superKey = (byte[]) bArr13[7].clone();
                this._psKey_create_time = j2;
            }
            if (bArr13[8] != null && bArr13[8].length > 0) {
                this._pay_token = (byte[]) bArr13[8].clone();
            }
            if (bArr13[9] != null && bArr13[9].length > 0) {
                this._pf = (byte[]) bArr13[9].clone();
            }
            if (bArr13[10] != null && bArr13[10].length > 0) {
                this._pfKey = (byte[]) bArr13[10].clone();
            }
            if (this._DA2 == null) {
                util.LOGI("_DA2 is null", "");
                i2 = 0;
            } else {
                i2 = this._DA2.length;
            }
            if (bArr13[11] == null) {
                util.LOGI("reserve[11] is null", "");
                i3 = 0;
            } else {
                i3 = bArr13[11].length;
            }
            util.LOGI("mainSigMap 0x" + Integer.toHexString(this.mainSigMap) + " file da2 len " + i2 + " rsp da2 len " + i3, "");
            if (bArr13[11] != null) {
                if (bArr13[11].length > 0) {
                    this._DA2 = (byte[]) bArr13[11].clone();
                    util.LOGI("get _DA2", "");
                } else if ((SigType.WLOGIN_DA2 & this.mainSigMap) != 0) {
                    this._DA2 = new byte[0];
                    util.LOGI("clear _DA2", "");
                }
            }
            if (bArr13[12] != null && bArr13[12].length > 2) {
                this._pt4Token = (byte[]) bArr13[12].clone();
            }
            if (bArr13[13] != null && bArr13[13].length > 0 && bArr13[14] != null && bArr13[14].length > 0) {
                this.wtSessionTicket = bArr13[13];
                this.wtSessionTicketKey = bArr13[14];
                this.wtSessionTicketCreatTime = u.f();
            }
        }
    }

    public WloginSigInfo(long j, long j2, byte[] bArr, byte[] bArr2) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._userStSig = new byte[0];
        this._userSt_Key = new byte[0];
        this._userStWebSig = new byte[0];
        this._userA5 = new byte[0];
        this._userA8 = new byte[0];
        this._lsKey = new byte[0];
        this._sKey = new byte[0];
        this._userSig64 = new byte[0];
        this._openid = new byte[0];
        this._openkey = new byte[0];
        this._vkey = new byte[0];
        this._en_A1 = new byte[0];
        this._access_token = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._sid = new byte[0];
        this._noPicSig = new byte[0];
        this._aqSig = new byte[0];
        this._psKey = new byte[0];
        this._pt4Token = new byte[0];
        this._superKey = new byte[0];
        this._G = new byte[0];
        this._dpwd = new byte[0];
        this._randseed = new byte[0];
        this._pay_token = new byte[0];
        this._pf = new byte[0];
        this._pfKey = new byte[0];
        this._DA2 = new byte[0];
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A2_expire_time = 0;
        this._lsKey_expire_time = 0;
        this._sKey_expire_time = 0;
        this._vKey_expire_time = 0;
        this._userA8_expire_time = 0;
        this._userStWebSig_expire_time = 0;
        this._D2_expire_time = 0;
        this._sid_expire_time = 0;
        this._create_time = 0;
        this._A2_create_time = 0;
        this._userStSig_create_time = 0;
        this._userStWebSig_create_time = 0;
        this._userA5_create_time = 0;
        this._userA8_create_time = 0;
        this._lsKey_create_time = 0;
        this._sKey_create_time = 0;
        this._userSig64_create_time = 0;
        this._openkey_create_time = 0;
        this._vkey_create_time = 0;
        this._access_token_create_time = 0;
        this._D2_create_time = 0;
        this._sid_create_time = 0;
        this._aqSig_create_time = 0;
        this._psKey_create_time = 0;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
        this.cacheTickets = null;
        this._app_pri = 4294967295L;
        if (bArr != null && bArr.length > 0) {
            this._userStSig = (byte[]) bArr.clone();
            this._userStSig_create_time = j;
        }
        if (bArr2 != null && bArr2.length > 0) {
            this._userSt_Key = (byte[]) bArr2.clone();
        }
    }

    public WloginSigInfo Set(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[][] bArr13, long[] jArr, int i) {
        SetSigInfo(j, j2, j3, j4, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, jArr, i);
        return this;
    }

    public WloginSigInfo Set(byte[][] bArr, long j) {
        if (bArr != null && bArr.length == 5) {
            if (bArr[0] != null && bArr[0].length > 0) {
                this._en_A1 = (byte[]) bArr[0].clone();
                this._create_time = j;
                if (bArr[1] == null || bArr[1].length <= 0) {
                    this._noPicSig = new byte[0];
                } else {
                    this._noPicSig = (byte[]) bArr[1].clone();
                }
            }
            if (bArr[2] != null && bArr[2].length > 0) {
                this._G = (byte[]) bArr[2].clone();
            }
            if (bArr[3] != null && bArr[3].length > 0) {
                this._dpwd = (byte[]) bArr[3].clone();
            }
            if (bArr[4] != null && bArr[4].length > 0) {
                this._randseed = (byte[]) bArr[4].clone();
            }
        }
        return this;
    }

    public WloginSigInfo setRandSeed(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            this._randseed = (byte[]) bArr.clone();
        }
        return this;
    }

    public WloginSigInfo setDA2(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            util.LOGI("WloginSiginfo.setDA2 da2 0", "");
            this._DA2 = new byte[0];
        } else {
            this._DA2 = (byte[]) bArr.clone();
        }
        return this;
    }

    public boolean iSExpireA2(long j) {
        return j > this._A2_expire_time;
    }

    private WloginSigInfo(Parcel parcel) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._userStSig = new byte[0];
        this._userSt_Key = new byte[0];
        this._userStWebSig = new byte[0];
        this._userA5 = new byte[0];
        this._userA8 = new byte[0];
        this._lsKey = new byte[0];
        this._sKey = new byte[0];
        this._userSig64 = new byte[0];
        this._openid = new byte[0];
        this._openkey = new byte[0];
        this._vkey = new byte[0];
        this._en_A1 = new byte[0];
        this._access_token = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._sid = new byte[0];
        this._noPicSig = new byte[0];
        this._aqSig = new byte[0];
        this._psKey = new byte[0];
        this._pt4Token = new byte[0];
        this._superKey = new byte[0];
        this._G = new byte[0];
        this._dpwd = new byte[0];
        this._randseed = new byte[0];
        this._pay_token = new byte[0];
        this._pf = new byte[0];
        this._pfKey = new byte[0];
        this._DA2 = new byte[0];
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A2_expire_time = 0;
        this._lsKey_expire_time = 0;
        this._sKey_expire_time = 0;
        this._vKey_expire_time = 0;
        this._userA8_expire_time = 0;
        this._userStWebSig_expire_time = 0;
        this._D2_expire_time = 0;
        this._sid_expire_time = 0;
        this._create_time = 0;
        this._A2_create_time = 0;
        this._userStSig_create_time = 0;
        this._userStWebSig_create_time = 0;
        this._userA5_create_time = 0;
        this._userA8_create_time = 0;
        this._lsKey_create_time = 0;
        this._sKey_create_time = 0;
        this._userSig64_create_time = 0;
        this._openkey_create_time = 0;
        this._vkey_create_time = 0;
        this._access_token_create_time = 0;
        this._D2_create_time = 0;
        this._sid_create_time = 0;
        this._aqSig_create_time = 0;
        this._psKey_create_time = 0;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
        this.cacheTickets = null;
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this._TGT);
        parcel.writeByteArray(this._TGTKey);
        parcel.writeByteArray(this._userStSig);
        parcel.writeByteArray(this._userSt_Key);
        parcel.writeByteArray(this._userStWebSig);
        parcel.writeByteArray(this._userA5);
        parcel.writeByteArray(this._userA8);
        parcel.writeByteArray(this._lsKey);
        parcel.writeByteArray(this._sKey);
        parcel.writeByteArray(this._userSig64);
        parcel.writeByteArray(this._openid);
        parcel.writeByteArray(this._openkey);
        parcel.writeByteArray(this._vkey);
        parcel.writeByteArray(this._en_A1);
        parcel.writeByteArray(this._access_token);
        parcel.writeByteArray(this._D2);
        parcel.writeByteArray(this._D2Key);
        parcel.writeByteArray(this._sid);
        parcel.writeByteArray(this._noPicSig);
        parcel.writeByteArray(this._aqSig);
        parcel.writeByteArray(this._psKey);
        parcel.writeByteArray(this._superKey);
        parcel.writeByteArray(this._G);
        parcel.writeByteArray(this._dpwd);
        parcel.writeByteArray(this._randseed);
        parcel.writeInt(this._login_bitmap);
        parcel.writeLong(this._app_pri);
        parcel.writeLong(this._A2_expire_time);
        parcel.writeLong(this._lsKey_expire_time);
        parcel.writeLong(this._sKey_expire_time);
        parcel.writeLong(this._vKey_expire_time);
        parcel.writeLong(this._userA8_expire_time);
        parcel.writeLong(this._userStWebSig_expire_time);
        parcel.writeLong(this._D2_expire_time);
        parcel.writeLong(this._sid_expire_time);
        parcel.writeLong(this._create_time);
        parcel.writeLong(this._A2_create_time);
        parcel.writeLong(this._userStSig_create_time);
        parcel.writeLong(this._userStWebSig_create_time);
        parcel.writeLong(this._userA5_create_time);
        parcel.writeLong(this._userA8_create_time);
        parcel.writeLong(this._lsKey_create_time);
        parcel.writeLong(this._sKey_create_time);
        parcel.writeLong(this._userSig64_create_time);
        parcel.writeLong(this._openkey_create_time);
        parcel.writeLong(this._vkey_create_time);
        parcel.writeLong(this._access_token_create_time);
        parcel.writeLong(this._D2_create_time);
        parcel.writeLong(this._sid_create_time);
        parcel.writeLong(this._aqSig_create_time);
        parcel.writeLong(this._psKey_create_time);
        parcel.writeByteArray(this._pay_token);
        parcel.writeByteArray(this._pf);
        parcel.writeByteArray(this._pfKey);
        parcel.writeByteArray(this._DA2);
        parcel.writeByteArray(this._pt4Token);
        parcel.writeByteArray(this.wtSessionTicket);
        parcel.writeByteArray(this.wtSessionTicketKey);
        parcel.writeLong(this.wtSessionTicketCreatTime);
    }

    public void readFromParcel(Parcel parcel) {
        this._TGT = parcel.createByteArray();
        this._TGTKey = parcel.createByteArray();
        this._userStSig = parcel.createByteArray();
        this._userSt_Key = parcel.createByteArray();
        this._userStWebSig = parcel.createByteArray();
        this._userA5 = parcel.createByteArray();
        this._userA8 = parcel.createByteArray();
        this._lsKey = parcel.createByteArray();
        this._sKey = parcel.createByteArray();
        this._userSig64 = parcel.createByteArray();
        this._openid = parcel.createByteArray();
        this._openkey = parcel.createByteArray();
        this._vkey = parcel.createByteArray();
        this._en_A1 = parcel.createByteArray();
        this._access_token = parcel.createByteArray();
        this._D2 = parcel.createByteArray();
        this._D2Key = parcel.createByteArray();
        this._sid = parcel.createByteArray();
        this._noPicSig = parcel.createByteArray();
        this._aqSig = parcel.createByteArray();
        this._psKey = parcel.createByteArray();
        this._superKey = parcel.createByteArray();
        this._G = parcel.createByteArray();
        this._dpwd = parcel.createByteArray();
        this._randseed = parcel.createByteArray();
        this._login_bitmap = parcel.readInt();
        this._app_pri = parcel.readLong();
        this._A2_expire_time = parcel.readLong();
        this._lsKey_expire_time = parcel.readLong();
        this._sKey_expire_time = parcel.readLong();
        this._vKey_expire_time = parcel.readLong();
        this._userA8_expire_time = parcel.readLong();
        this._userStWebSig_expire_time = parcel.readLong();
        this._D2_expire_time = parcel.readLong();
        this._sid_expire_time = parcel.readLong();
        this._create_time = parcel.readLong();
        this._A2_create_time = parcel.readLong();
        this._userStSig_create_time = parcel.readLong();
        this._userStWebSig_create_time = parcel.readLong();
        this._userA5_create_time = parcel.readLong();
        this._userA8_create_time = parcel.readLong();
        this._lsKey_create_time = parcel.readLong();
        this._sKey_create_time = parcel.readLong();
        this._userSig64_create_time = parcel.readLong();
        this._openkey_create_time = parcel.readLong();
        this._vkey_create_time = parcel.readLong();
        this._access_token_create_time = parcel.readLong();
        this._D2_create_time = parcel.readLong();
        this._sid_create_time = parcel.readLong();
        this._aqSig_create_time = parcel.readLong();
        this._psKey_create_time = parcel.readLong();
        this._pay_token = parcel.createByteArray();
        this._pf = parcel.createByteArray();
        this._pfKey = parcel.createByteArray();
        this._DA2 = parcel.createByteArray();
        this._pt4Token = parcel.createByteArray();
        this.wtSessionTicket = parcel.createByteArray();
        this.wtSessionTicketKey = parcel.createByteArray();
        this.wtSessionTicketCreatTime = parcel.readLong();
    }
}
