package oicq.wlogin_sdk.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.tools.util;

public class Ticket implements Parcelable {
    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        public Ticket createFromParcel(Parcel parcel) {
            return new Ticket(parcel);
        }

        public Ticket[] newArray(int i) {
            return new Ticket[i];
        }
    };
    private static final int EXPIRE_FIELD = 65535;
    public long _create_time;
    public long _expire_time;
    public Map<String, Long> _pskey_expire;
    public Map<String, byte[]> _pskey_map;
    public Map<String, Long> _pt4token_expire;
    public Map<String, byte[]> _pt4token_map;
    public byte[] _sig;
    public byte[] _sig_key;
    public int _type;

    public Ticket() {
        this._pskey_map = new HashMap();
        this._pskey_expire = new HashMap();
        this._pt4token_map = new HashMap();
        this._pt4token_expire = new HashMap();
    }

    public Ticket(int i, byte[] bArr, byte[] bArr2, long j, long j2) {
        this._pskey_map = new HashMap();
        this._pskey_expire = new HashMap();
        this._pt4token_map = new HashMap();
        this._pt4token_expire = new HashMap();
        this._type = i;
        this._sig = bArr == null ? new byte[0] : (byte[]) bArr.clone();
        this._sig_key = bArr2 == null ? new byte[0] : (byte[]) bArr2.clone();
        this._create_time = j;
        this._expire_time = j2;
    }

    public Ticket(int i, byte[] bArr, byte[] bArr2, long j, byte[] bArr3) {
        this._pskey_map = new HashMap();
        this._pskey_expire = new HashMap();
        this._pt4token_map = new HashMap();
        this._pt4token_expire = new HashMap();
        this._type = i;
        this._sig = bArr == null ? new byte[0] : (byte[]) bArr.clone();
        this._sig_key = bArr2 == null ? new byte[0] : (byte[]) bArr2.clone();
        this._create_time = j;
        this._expire_time = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j;
        parsePsBuf(bArr3, this._create_time, this._pskey_map, this._pskey_expire);
    }

    public Ticket(int i, byte[] bArr, byte[] bArr2, long j, byte[] bArr3, byte[] bArr4) {
        this._pskey_map = new HashMap();
        this._pskey_expire = new HashMap();
        this._pt4token_map = new HashMap();
        this._pt4token_expire = new HashMap();
        this._type = i;
        this._sig = bArr == null ? new byte[0] : (byte[]) bArr.clone();
        this._sig_key = bArr2 == null ? new byte[0] : (byte[]) bArr2.clone();
        this._create_time = j;
        this._expire_time = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j;
        parsePsBuf(bArr3, this._create_time, this._pskey_map, this._pskey_expire);
        parsePsBuf(bArr4, this._create_time, this._pt4token_map, this._pt4token_expire);
    }

    private String __getPskey(String str, Map<String, byte[]> map, Map<String, Long> map2) {
        util.LOGI("__getPskey get domain " + str + " pskey or pt4token", "");
        if (map == null) {
            return null;
        }
        byte[] bArr = (byte[]) map.get(str);
        if (bArr == null) {
            util.LOGI("__getPskey get domain " + str + " pskey or pt4token null", "");
            return null;
        }
        if (map2 != null) {
            Long l = (Long) map2.get(str);
            if (l != null && l.longValue() <= u.f()) {
                util.LOGI("__getPskey delete domain " + str + " expired pskey or pt4token expire time " + l, "");
                map2.remove(str);
                map.remove(str);
                return null;
            }
        }
        String str2 = new String(bArr);
        util.LOGI("__getPskey get domain " + str + " pskey or pt4token len " + str2.length() + " " + str2.substring(0, 5) + "***" + str2.substring(str2.length() - 5, str2.length()), "");
        return str2;
    }

    public String getPSkey(String str) {
        return __getPskey(str, this._pskey_map, this._pskey_expire);
    }

    public String getPt4Token(String str) {
        util.LOGI("getPt4Token get domain " + str + " pt4token", "");
        return __getPskey(str, this._pt4token_map, this._pt4token_expire);
    }

    protected static void parseSvrPs(byte[] bArr, long j, Map<String, byte[]> map, Map<String, Long> map2, Map<String, byte[]> map3, Map<String, Long> map4) {
        util.LOGI("pskeyMap " + map.size() + ", tokenMap " + map3.size() + " create time:" + j, "");
        if (bArr != null && bArr.length > 2) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            short s = wrap.getShort();
            for (short s2 = (short) 0; s2 < s; s2++) {
                byte[] bArr2 = new byte[wrap.getShort()];
                wrap.get(bArr2);
                String str = new String(bArr2);
                Object obj = new byte[wrap.getShort()];
                wrap.get(obj);
                Object obj2 = new byte[wrap.getShort()];
                wrap.get(obj2);
                long j2 = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j;
                if (obj.length > 0) {
                    map.put(str, obj);
                    map2.put(str, Long.valueOf(j2));
                }
                if (obj2.length > 0) {
                    String str2 = new String(obj2);
                    util.LOGI("parseSvrPs add domain " + str + " pt4token len " + obj2.length + " " + str2.substring(0, 5) + "***" + str2.substring(str2.length() - 5), "");
                    map3.put(str, obj2);
                    map4.put(str, Long.valueOf(j2));
                }
                util.LOGI(str + " pskey:" + obj.length + " pt4token " + obj2.length + " expire: " + j2, "");
            }
        }
    }

    protected static void parsePsBuf(byte[] bArr, long j, Map<String, byte[]> map, Map<String, Long> map2) {
        util.LOGI("ps_buf " + (bArr == null ? "null" : Integer.valueOf(bArr.length)), "");
        if (bArr != null && bArr.length > 2) {
            long f = u.f();
            int buf_to_int16 = util.buf_to_int16(bArr, 0);
            int i = 2;
            util.LOGI("domainCnt " + buf_to_int16, "");
            int i2 = 0;
            while (i2 < buf_to_int16 && bArr.length >= i + 2) {
                int buf_to_int162 = util.buf_to_int16(bArr, i);
                int i3 = i + 2;
                if (bArr.length >= i3 + buf_to_int162) {
                    String str = new String(bArr, i3, buf_to_int162);
                    buf_to_int162 += i3;
                    if (bArr.length >= buf_to_int162 + 2) {
                        int buf_to_int163 = util.buf_to_int16(bArr, buf_to_int162);
                        buf_to_int162 += 2;
                        if (bArr.length >= buf_to_int162 + buf_to_int163) {
                            long j2;
                            Object obj = new byte[buf_to_int163];
                            System.arraycopy(bArr, buf_to_int162, obj, 0, buf_to_int163);
                            i = buf_to_int162 + buf_to_int163;
                            if (bArr.length <= i + 2 || util.buf_to_int16(bArr, i) != 65535) {
                                j2 = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j;
                            } else {
                                i += 2;
                                j2 = util.buf_to_int64(bArr, i);
                                i += 8;
                            }
                            if (j2 > f) {
                                map.put(str, obj);
                                map2.put(str, Long.valueOf(j2));
                            }
                            util.LOGI(str + " pskey:" + buf_to_int163 + " expire: " + j2, "");
                            i2++;
                        } else {
                            return;
                        }
                    }
                    return;
                }
                return;
            }
        }
    }

    protected static byte[] packPsBuf(Map<String, byte[]> map, long j, Map<String, Long> map2) {
        util.LOGI("pskeyMap " + map.size(), "");
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        allocate.putShort((short) map.size());
        for (String str : map.keySet()) {
            allocate.putShort((short) str.length());
            allocate.put(str.getBytes());
            byte[] bArr = (byte[]) map.get(str);
            allocate.putShort((short) bArr.length);
            allocate.put(bArr);
            allocate.putShort((short) -1);
            Long l = (Long) map2.get(str);
            if (l != null) {
                allocate.putLong(l.longValue());
            } else {
                allocate.putLong(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j);
            }
        }
        allocate.flip();
        byte[] bArr2 = new byte[allocate.limit()];
        allocate.get(bArr2);
        return bArr2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this._type);
        parcel.writeByteArray(this._sig);
        parcel.writeByteArray(this._sig_key);
        parcel.writeLong(this._create_time);
        parcel.writeLong(this._expire_time);
        parcel.writeMap(this._pskey_map);
        parcel.writeMap(this._pt4token_map);
    }

    public void readFromParcel(Parcel parcel) {
        this._type = parcel.readInt();
        this._sig = parcel.createByteArray();
        this._sig_key = parcel.createByteArray();
        this._create_time = parcel.readLong();
        this._expire_time = parcel.readLong();
        this._pskey_map = parcel.readHashMap(Map.class.getClassLoader());
        this._pt4token_map = parcel.readHashMap(Map.class.getClassLoader());
    }

    private Ticket(Parcel parcel) {
        this._pskey_map = new HashMap();
        this._pskey_expire = new HashMap();
        this._pt4token_map = new HashMap();
        this._pt4token_expire = new HashMap();
        readFromParcel(parcel);
    }

    public static boolean isPskeyExpired(long j) {
        long f = u.f();
        if (f > j) {
            return true;
        }
        if (j <= TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + f) {
            return false;
        }
        util.LOGI("time for system may be  modified manually expireTime " + j + " current " + f, "");
        return true;
    }

    public static boolean isPt4TokenExpired(long j) {
        return isPskeyExpired(j);
    }
}
