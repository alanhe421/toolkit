package tencent.tls.tlvs;

import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class tlv_t {
    protected int _body_len;
    protected byte[] _buf;
    protected int _cmd;
    protected int _head_len;
    protected int _max;
    protected int _pos;

    public tlv_t() {
        this._max = 128;
        this._pos = 0;
        this._head_len = 4;
        this._body_len = 0;
        this._buf = new byte[this._max];
        this._cmd = 0;
    }

    public tlv_t(int i) {
        this._max = 128;
        this._pos = 0;
        this._head_len = 4;
        this._body_len = 0;
        this._buf = new byte[this._max];
        this._cmd = 0;
        this._cmd = i;
    }

    public byte[] get_buf() {
        Object obj = new byte[this._pos];
        System.arraycopy(this._buf, 0, obj, 0, this._pos);
        return obj;
    }

    public int getTLVSize() {
        return this._pos;
    }

    public byte[] get_data() {
        Object obj = new byte[this._body_len];
        System.arraycopy(this._buf, this._head_len, obj, 0, this._body_len);
        return obj;
    }

    public int get_data_len() {
        return this._body_len;
    }

    public void set_data(byte[] bArr, int i) {
        if (this._head_len + i > this._max) {
            this._max = (this._head_len + i) + 128;
            Object obj = new byte[this._max];
            System.arraycopy(this._buf, 0, obj, 0, this._head_len);
            this._buf = obj;
        }
        this._pos = this._head_len + i;
        System.arraycopy(bArr, 0, this._buf, this._head_len, i);
        this._body_len = i;
        util.int16_to_buf(this._buf, 0, this._cmd);
        util.int16_to_buf(this._buf, 2, this._body_len);
    }

    public byte[] build_tlv(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        set_data(bArr, bArr.length);
        return get_buf();
    }

    public void set_buf(byte[] bArr, int i, int i2) {
        if (i2 > this._max) {
            this._max = i2 + 128;
            this._buf = new byte[this._max];
        }
        this._pos = i2;
        System.arraycopy(bArr, i, this._buf, 0, i2);
        this._cmd = util.buf_to_int16(bArr, i);
        this._body_len = i2 - this._head_len;
    }

    int search_tlv(byte[] bArr, int i, int i2, int i3) {
        int length = bArr.length;
        int i4 = i;
        while (i4 < length && i4 + 2 <= length) {
            if (util.buf_to_int16(bArr, i4) == i3) {
                return i4;
            }
            i4 += 2;
            if (i4 + 2 > length) {
                return -1;
            }
            i4 += util.buf_to_int16(bArr, i4) + 2;
        }
        return -1;
    }

    public int get_tlv(byte[] bArr, int i, int i2) {
        int search_tlv = search_tlv(bArr, i, i2, this._cmd);
        if (search_tlv < 0) {
            return -1;
        }
        int i3 = i2 - (search_tlv - i);
        if (this._head_len >= i3) {
            return -1;
        }
        this._body_len = util.buf_to_int16(bArr, search_tlv + 2);
        if (this._head_len + this._body_len > i3) {
            return -1;
        }
        set_buf(bArr, search_tlv, this._head_len + this._body_len);
        if (verify()) {
            return (this._head_len + search_tlv) + this._body_len;
        }
        return -1005;
    }

    int get_tlv(byte[] bArr, int i, byte[] bArr2) {
        if (this._head_len >= i) {
            return -1;
        }
        this._body_len = util.buf_to_int16(bArr, 2);
        if (this._head_len + this._body_len > i) {
            return -1;
        }
        Object decrypt = cryptor.decrypt(bArr, this._head_len, this._body_len, bArr2);
        if (decrypt == null) {
            return -1002;
        }
        if (this._head_len + decrypt.length > this._max) {
            this._max = this._head_len + decrypt.length;
            this._buf = new byte[this._max];
        }
        this._pos = 0;
        System.arraycopy(bArr, 0, this._buf, 0, this._head_len);
        this._pos += this._head_len;
        System.arraycopy(decrypt, 0, this._buf, this._pos, decrypt.length);
        this._pos += decrypt.length;
        this._body_len = decrypt.length;
        return !verify() ? -1005 : 0;
    }

    public int get_tlv(byte[] bArr, int i, int i2, byte[] bArr2) {
        int search_tlv = search_tlv(bArr, i, i2, this._cmd);
        if (search_tlv < 0) {
            return -1;
        }
        int i3 = i2 - (search_tlv - i);
        byte[] bArr3 = new byte[i3];
        System.arraycopy(bArr, search_tlv, bArr3, 0, i3);
        return get_tlv(bArr3, i3, bArr2);
    }

    public boolean verify() {
        return true;
    }

    public int limit_len(byte[] bArr, int i) {
        if (bArr == null) {
            return 0;
        }
        if (bArr.length > i) {
            return i;
        }
        return bArr.length;
    }
}
