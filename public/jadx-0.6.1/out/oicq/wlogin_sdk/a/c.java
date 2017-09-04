package oicq.wlogin_sdk.a;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request */
public class c {
    public int a = 11;
    public int b = 0;
    public int c = 5;
    public int d = 0;
    protected int e = 1;

    public int a() {
        return this.b;
    }

    public byte[] a(byte[] bArr) {
        Object obj = new byte[((this.a + 2) + bArr.length)];
        util.int8_to_buf(obj, 0, 2);
        util.int16_to_buf(obj, 1, (this.a + bArr.length) + 2);
        util.int16_to_buf(obj, 3, this.e);
        util.int16_to_buf(obj, 5, this.b);
        util.int8_to_buf(obj, 7, 4);
        util.int32_to_buf(obj, 8, 0);
        System.arraycopy(bArr, 0, obj, 12, bArr.length);
        int length = bArr.length + 12;
        util.int8_to_buf(obj, length, 3);
        length++;
        return obj;
    }

    private static int[] b(byte[] bArr) {
        if (1 > bArr.length) {
            return new int[]{-1009, 0};
        } else if (3 > bArr.length) {
            return new int[]{-1009, 1};
        } else if (util.buf_to_int16(bArr, 1) != bArr.length) {
            return new int[]{-1009, 3};
        } else if (7 > bArr.length) {
            return new int[]{-1009, 3};
        } else if (8 > bArr.length) {
            return new int[]{-1009, 7};
        } else {
            int buf_to_int8 = util.buf_to_int8(bArr, 7);
            if (buf_to_int8 + 8 > bArr.length) {
                return new int[]{-1009, 8};
            }
            int i = buf_to_int8 + 8;
            return new int[]{0, i};
        }
    }

    public static int a(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        jVar.d = util.buf_to_int8(bArr, i2) & 255;
        i2++;
        if (i2 + 2 > bArr.length) {
            return -1009;
        }
        int buf_to_int16 = util.buf_to_int16(bArr, i2);
        i2 += 2;
        if (i2 + buf_to_int16 > bArr.length) {
            return -1009;
        }
        Object obj = new byte[buf_to_int16];
        System.arraycopy(bArr, i2, obj, 0, buf_to_int16);
        i2 += buf_to_int16;
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        buf_to_int16 = util.buf_to_int8(bArr, i2);
        i2++;
        if (i2 + buf_to_int16 > bArr.length) {
            return -1009;
        }
        jVar.e = new byte[buf_to_int16];
        System.arraycopy(bArr, i2, jVar.e, 0, buf_to_int16);
        i2 += buf_to_int16;
        if (i2 + 2 > bArr.length) {
            return -1009;
        }
        buf_to_int16 = util.buf_to_int16(bArr, i2);
        i2 += 2;
        if (i2 + buf_to_int16 > bArr.length) {
            return -1009;
        }
        jVar.f = new byte[buf_to_int16];
        System.arraycopy(bArr, i2, jVar.f, 0, buf_to_int16);
        i2 += buf_to_int16;
        if (obj.length <= 0) {
            return i;
        }
        int i3;
        switch (jVar.d) {
            case 0:
                if (4 > obj.length) {
                    return -1009;
                }
                jVar.m = util.buf_to_int32(obj, 0);
                if (5 > obj.length) {
                    return -1009;
                }
                i2 = util.buf_to_int8(obj, 4);
                if (i2 + 5 > obj.length) {
                    return -1009;
                }
                jVar.n = new byte[i2];
                System.arraycopy(obj, 5, jVar.n, 0, i2);
                i3 = i2 + 5;
                return i;
            case 2:
                if (1 > obj.length) {
                    return -1009;
                }
                i2 = util.buf_to_int8(obj, 0);
                if (i2 + 1 > obj.length) {
                    return -1009;
                }
                jVar.o = new byte[i2];
                System.arraycopy(obj, 1, jVar.o, 0, i2);
                i2++;
                buf_to_int16 = i2 + 1;
                i2 = util.buf_to_int8(obj, i2);
                if (buf_to_int16 + i2 > obj.length) {
                    return -1009;
                }
                jVar.p = new byte[i2];
                System.arraycopy(obj, buf_to_int16, jVar.p, 0, i2);
                i2 += buf_to_int16;
                buf_to_int16 = util.buf_to_int16(obj, i2);
                i2 += 2;
                if (i2 + buf_to_int16 > obj.length) {
                    return -1009;
                }
                jVar.q = new byte[buf_to_int16];
                System.arraycopy(obj, i2, jVar.q, 0, buf_to_int16);
                i3 = i2 + buf_to_int16;
                return i;
            case 3:
            case 6:
            case 44:
                if (2 > obj.length) {
                    return -1009;
                }
                i2 = util.buf_to_int16(obj, 0);
                if (i2 + 2 > obj.length) {
                    return -1009;
                }
                jVar.r = new byte[i2];
                System.arraycopy(obj, 2, jVar.r, 0, i2);
                i3 = i2 + 2;
                return i;
            case 4:
                if (2 > obj.length) {
                    return -1009;
                }
                jVar.s = util.buf_to_int16(obj, 0);
                if (4 > obj.length) {
                    return -1009;
                }
                jVar.t = util.buf_to_int16(obj, 2);
                return i;
            case 5:
                if (2 > obj.length) {
                    return -1009;
                }
                jVar.s = util.buf_to_int16(obj, 0);
                if (4 > obj.length) {
                    return -1009;
                }
                jVar.t = util.buf_to_int16(obj, 2);
                return i;
            case 31:
                jVar.s = 0;
                jVar.t = 0;
                return i;
            default:
                util.LOGW("unhandle return code int parse_checkvalid_rsp", "", "");
                return i;
        }
    }

    public static int b(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        jVar.d = util.buf_to_int8(bArr, i2);
        i = i2 + 1;
        if (i + 1 > bArr.length) {
            return -1009;
        }
        i2 = util.buf_to_int8(bArr, i);
        i++;
        if (i + i2 > bArr.length) {
            return -1009;
        }
        jVar.e = new byte[i2];
        System.arraycopy(bArr, i, jVar.e, 0, i2);
        i += i2;
        if (i + 2 > bArr.length) {
            return -1009;
        }
        i2 = util.buf_to_int16(bArr, i);
        i += 2;
        if (i + i2 > bArr.length) {
            return -1009;
        }
        jVar.f = new byte[i2];
        System.arraycopy(bArr, i, jVar.f, 0, i2);
        i += i2;
        i2 = util.buf_to_int8(bArr, i);
        i++;
        i = oicq.wlogin_sdk.tools.c.a(i2, bArr, i, bArr.length - i, jVar.B);
        if (i == 0) {
            return i;
        }
        util.LOGI("parser tlv failed " + i, "");
        return -1009;
    }

    public static int c(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        jVar.d = util.buf_to_int8(bArr, i2);
        i2++;
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        int buf_to_int8 = util.buf_to_int8(bArr, i2);
        i2++;
        if (i2 + buf_to_int8 > bArr.length) {
            return -1009;
        }
        Object obj = new byte[buf_to_int8];
        System.arraycopy(bArr, i2, obj, 0, buf_to_int8);
        int i3 = i2 + buf_to_int8;
        if (jVar.d == 0) {
            byte[] bytes;
            if (jVar.j == null || jVar.j.length <= 0) {
                bytes = j.a.getBytes();
            } else {
                bytes = MD5.toMD5Byte(jVar.j);
            }
            obj = cryptor.decrypt(obj, 0, obj.length, bytes);
            if (obj == null) {
                return -1009;
            }
            if (1 > obj.length) {
                return -1009;
            }
            i2 = util.buf_to_int8(obj, 0);
            if (i2 + 1 > obj.length) {
                return -1009;
            }
            i2++;
            if (i2 + 8 > obj.length) {
                return -1009;
            }
            jVar.u = util.buf_to_int64(obj, i2);
            i2 += 8;
            if (i2 + 2 > obj.length) {
                return -1009;
            }
            buf_to_int8 = util.buf_to_int16(obj, i2);
            i2 += 2;
            if (i2 + buf_to_int8 > obj.length) {
                return -1009;
            }
            jVar.v = new byte[buf_to_int8];
            System.arraycopy(obj, i2, jVar.v, 0, buf_to_int8);
            i2 += buf_to_int8;
            if (i2 + 1 > obj.length) {
                return -1009;
            }
            int buf_to_int82 = util.buf_to_int8(obj, i2);
            buf_to_int8 = i2 + 1;
            for (i2 = 0; i2 < buf_to_int82; i2++) {
                if (buf_to_int8 + 2 > obj.length) {
                    return -1009;
                }
                int buf_to_int83 = util.buf_to_int8(obj, buf_to_int8);
                buf_to_int8++;
                int buf_to_int84 = util.buf_to_int8(obj, buf_to_int8);
                buf_to_int8++;
                if (buf_to_int8 + buf_to_int84 > obj.length) {
                    return -1009;
                }
                switch (buf_to_int83) {
                    case 7:
                        jVar.w = new byte[buf_to_int84];
                        System.arraycopy(obj, buf_to_int8, jVar.w, 0, buf_to_int84);
                        break;
                    case 12:
                        Object obj2 = new byte[buf_to_int84];
                        System.arraycopy(obj, buf_to_int8, obj2, 0, buf_to_int84);
                        j.y = util.buf_to_int64(obj2, 0);
                        break;
                    default:
                        break;
                }
                buf_to_int8 += buf_to_int84;
            }
        }
        if (i3 + 1 > bArr.length) {
            return -1009;
        }
        i2 = util.buf_to_int8(bArr, i3);
        buf_to_int8 = i3 + 1;
        if (buf_to_int8 + i2 > bArr.length) {
            return -1009;
        }
        jVar.e = new byte[i2];
        System.arraycopy(bArr, buf_to_int8, jVar.e, 0, i2);
        i2 += buf_to_int8;
        if (i2 + 2 > bArr.length) {
            return -1009;
        }
        buf_to_int8 = util.buf_to_int16(bArr, i2);
        i2 += 2;
        if (i2 + buf_to_int8 > bArr.length) {
            return -1009;
        }
        jVar.f = new byte[buf_to_int8];
        System.arraycopy(bArr, i2, jVar.f, 0, buf_to_int8);
        i2 += buf_to_int8;
        return i;
    }

    public static int d(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        if (i2 + 1 > bArr.length) {
            return -1009;
        }
        jVar.d = util.buf_to_int8(bArr, i2);
        i2++;
        if (i2 + 2 > bArr.length) {
            return -1009;
        }
        int buf_to_int16 = util.buf_to_int16(bArr, i2);
        i2 += 2;
        if (i2 + buf_to_int16 > bArr.length) {
            return -1009;
        }
        jVar.f = new byte[buf_to_int16];
        System.arraycopy(bArr, i2, jVar.f, 0, buf_to_int16);
        i2 += buf_to_int16;
        if (i2 + 2 > bArr.length) {
            return i;
        }
        buf_to_int16 = util.buf_to_int16(bArr, i2);
        i2 += 2;
        if (i2 + buf_to_int16 > bArr.length) {
            return -1009;
        }
        if (buf_to_int16 <= 0) {
            return i;
        }
        Object obj = new byte[(buf_to_int16 + 2)];
        System.arraycopy(bArr, i2, obj, 1, buf_to_int16);
        i2 += buf_to_int16;
        obj[0] = (byte) 40;
        obj[buf_to_int16 + 1] = (byte) 41;
        jVar.f = new String(jVar.f).replace("。", new String(obj) + "。").getBytes();
        return i;
    }

    public static int e(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, bArr.length - i2);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            dataInputStream.readByte();
            jVar.d = dataInputStream.readShort();
            short readShort = dataInputStream.readShort();
            if (readShort != (short) 0) {
                byte[] bArr2 = new byte[readShort];
                dataInputStream.read(bArr2);
                bArr2 = cryptor.decrypt(bArr2, 0, bArr2.length, jVar.l);
                if (bArr2 == null) {
                    util.LOGI("no tlv in rsp", "");
                    return -1;
                }
                int a = oicq.wlogin_sdk.tools.c.a(util.buf_to_int8(bArr2, 0), bArr2, 1, bArr2.length - 1, jVar.B);
                if (a != 0) {
                    util.LOGI("parser tlv failed " + a, "");
                    return -1009;
                }
            }
            jVar.e = new byte[dataInputStream.readByte()];
            dataInputStream.read(jVar.e);
            readShort = dataInputStream.readShort();
            if (readShort != (short) 0) {
                jVar.f = new byte[readShort];
                if (dataInputStream.read(jVar.f) != readShort) {
                    throw new Exception("msg len " + readShort + " error");
                }
            }
            dataInputStream.close();
            byteArrayInputStream.close();
            return 0;
        } catch (Exception e) {
            util.LOGI("parse0x10Rsp failed " + e.getMessage(), "");
            return -1009;
        }
    }

    public static int f(byte[] bArr, j jVar) {
        int[] b = b(bArr);
        int i = b[0];
        int i2 = b[1];
        if (i == -1009) {
            return i;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, bArr.length - i2);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            dataInputStream.readByte();
            jVar.d = dataInputStream.readShort();
            short readShort = dataInputStream.readShort();
            if (readShort != (short) 0) {
                byte[] bArr2 = new byte[readShort];
                dataInputStream.read(bArr2);
                bArr2 = cryptor.decrypt(bArr2, 0, bArr2.length, jVar.l);
                if (bArr2 == null) {
                    util.LOGI("no tlv in rsp", "");
                    return -1;
                }
                int a = oicq.wlogin_sdk.tools.c.a(util.buf_to_int8(bArr2, 0), bArr2, 1, bArr2.length - 1, jVar.B);
                if (a != 0) {
                    util.LOGI("parser tlv failed " + a, "");
                    return -1009;
                }
            }
            jVar.e = new byte[dataInputStream.readByte()];
            dataInputStream.read(jVar.e);
            readShort = dataInputStream.readShort();
            if (readShort != (short) 0) {
                jVar.f = new byte[readShort];
                if (dataInputStream.read(jVar.f) != readShort) {
                    throw new Exception("msg len " + readShort + " error");
                }
            }
            dataInputStream.close();
            byteArrayInputStream.close();
            return 0;
        } catch (Exception e) {
            util.LOGI("parse0x11Rsp failed " + e.getMessage(), "");
            return -1009;
        }
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + 1)];
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        return cryptor.encrypt(obj, 0, obj.length, MD5.toMD5Byte(bArr2));
    }
}
