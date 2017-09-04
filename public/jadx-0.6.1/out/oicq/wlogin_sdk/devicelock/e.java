package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

/* compiled from: DevlockTLV */
public class e {
    protected int _body_len = 0;
    protected byte[] _buf = new byte[this._max];
    protected int _head_len = 4;
    protected int _max = 128;
    protected int _pos = 0;
    protected int _type = 0;

    public int get_type() {
        return this._type;
    }

    public byte[] get_buf() {
        Object obj = new byte[this._pos];
        System.arraycopy(this._buf, 0, obj, 0, this._pos);
        return obj;
    }

    public byte[] get_data() {
        Object obj = new byte[this._body_len];
        System.arraycopy(this._buf, this._head_len, obj, 0, this._body_len);
        return obj;
    }

    public int get_data_len() {
        return this._body_len;
    }

    public int set_buf(byte[] bArr, int i) {
        if (i > this._max) {
            this._max = i + 128;
            this._buf = new byte[this._max];
        }
        this._pos = i;
        System.arraycopy(bArr, 0, this._buf, 0, i);
        this._type = util.buf_to_int16(bArr, 0);
        this._body_len = i - this._head_len;
        try {
            parse();
            return 0;
        } catch (Exception e) {
            return -1009;
        }
    }

    public int set_data(byte[] bArr, int i) {
        if (this._head_len + i > this._max) {
            this._max = (this._head_len + i) + 128;
            Object obj = new byte[this._max];
            System.arraycopy(this._buf, 0, obj, 0, this._head_len);
            this._buf = obj;
        }
        this._pos = this._head_len + i;
        System.arraycopy(bArr, 0, this._buf, this._head_len, i);
        this._body_len = i;
        util.int16_to_buf(this._buf, 0, this._type);
        util.int16_to_buf(this._buf, 2, this._body_len);
        try {
            parse();
            return 0;
        } catch (Exception e) {
            return -1009;
        }
    }

    public void parse() {
    }

    public void fill_head() {
        util.int16_to_buf(this._buf, this._pos, this._type);
        this._pos += 2;
        util.int16_to_buf(this._buf, this._pos, 0);
        this._pos += 2;
    }

    public void set_length() {
        util.int16_to_buf(this._buf, 2, this._pos - this._head_len);
    }

    public void fill_body(byte[] bArr, int i) {
        if (i > this._max - this._head_len) {
            this._max = (this._head_len + i) + 64;
            Object obj = new byte[this._max];
            System.arraycopy(this._buf, 0, obj, 0, this._pos);
            this._buf = obj;
        }
        this._body_len = i;
        System.arraycopy(bArr, 0, this._buf, this._pos, i);
        this._pos += i;
        set_length();
    }

    public int get_size() {
        return this._pos;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this._pos; i++) {
            str = (str + Integer.toHexString((this._buf[i] >> 4) & 15)) + Integer.toHexString(this._buf[i] & 15);
        }
        return str;
    }

    public int put_block(byte[] bArr, int i) {
        int length = bArr.length;
        util.int16_to_buf(this._buf, i, length);
        int i2 = i + 2;
        System.arraycopy(bArr, 0, this._buf, i2, length);
        return length + i2;
    }

    public int put_int8(int i, int i2) {
        util.int8_to_buf(this._buf, i2, i);
        return i2 + 1;
    }

    public int put_int16(int i, int i2) {
        util.int16_to_buf(this._buf, i2, i);
        return i2 + 2;
    }

    public int put_int32(long j, int i) {
        util.int64_to_buf32(this._buf, i, j);
        return i + 4;
    }

    public int put_int64(long j, int i) {
        util.int64_to_buf(this._buf, i, j);
        return i + 8;
    }
}
