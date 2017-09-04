package com.tencent.qalsdk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private byte[] buffer = new byte[64];
    private byte[] digest = new byte[16];
    public String digestHexStr;

    native byte[] getBufferMd5(byte[] bArr);

    native byte[] getStremMd5(InputStream inputStream, long j);

    public static long b2iu(byte b) {
        return b < (byte) 0 ? (long) (b & 255) : (long) b;
    }

    public byte[] getMD5(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 == 0 || i < 0) {
            return null;
        }
        byte[] sysGetBufferMd5 = sysGetBufferMd5(bArr, i, i2);
        if (sysGetBufferMd5 != null) {
            this.digest = sysGetBufferMd5;
            return this.digest;
        }
        try {
            sysGetBufferMd5 = getBufferMd5(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sysGetBufferMd5 == null) {
            return this.digest;
        }
        this.digest = sysGetBufferMd5;
        return this.digest;
    }

    public byte[] getMD5(InputStream inputStream, long j) {
        if (inputStream == null || j < 0) {
            return null;
        }
        try {
            long available = (long) inputStream.available();
            if (j == 0 || (available != 0 && ((long) inputStream.available()) < j)) {
                j = (long) inputStream.available();
            }
            if (j == 0) {
                return null;
            }
            byte[] sysGetStremMd5 = sysGetStremMd5(inputStream, j);
            if (sysGetStremMd5 != null) {
                this.digest = sysGetStremMd5;
                return this.digest;
            }
            try {
                sysGetStremMd5 = getStremMd5(inputStream, j);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (sysGetStremMd5 == null) {
                return this.digest;
            }
            this.digest = sysGetStremMd5;
            return this.digest;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream == null) {
                return null;
            }
            try {
                inputStream.close();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    public static String byteHEX(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    public static byte[] toMD5Byte(byte[] bArr) {
        return toMD5Byte(bArr, 0, bArr.length);
    }

    public static byte[] toMD5Byte(byte[] bArr, int i, int i2) {
        return new MD5().getMD5(bArr, i, i2);
    }

    public static byte[] getFileMd5(String str) {
        byte[] bArr = null;
        if (!(str == null || str.length() == 0)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    bArr = getPartfileMd5(str, file.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] getPartfileMd5(String str, long j) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (!(str == null || str.length() == 0 || j < 0)) {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    File file = new File(str);
                    if (file.exists()) {
                        long length = file.length();
                        if (j == 0 || length < j) {
                            j = length;
                        }
                        bArr = toMD5Byte(fileInputStream, j);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static byte[] toMD5Byte(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        return new MD5().getMD5(bytes, 0, bytes.length);
    }

    public static byte[] toMD5Byte(InputStream inputStream, long j) {
        return new MD5().getMD5(inputStream, j);
    }

    public static String toMD5(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        byte[] md5 = new MD5().getMD5(bArr, 0, bArr.length);
        String str = "";
        int i = 0;
        while (i < 16) {
            String str2 = str + byteHEX(md5[i]);
            i++;
            str = str2;
        }
        return str;
    }

    public static String toMD5(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        byte[] md5 = new MD5().getMD5(bytes, 0, bytes.length);
        String str2 = "";
        if (md5 == null) {
            return str2;
        }
        while (i < 16) {
            String str3 = str2 + byteHEX(md5[i]);
            i++;
            str2 = str3;
        }
        return str2;
    }

    public static byte[] sysGetStremMd5(InputStream inputStream, long j) {
        long j2 = 0;
        byte[] bArr = null;
        if (inputStream != null && j != 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                byte[] bArr2 = new byte[32768];
                int length = bArr2.length;
                while (j2 < j) {
                    if (((long) bArr2.length) + j2 > j) {
                        length = (int) (j - j2);
                    }
                    length = inputStream.read(bArr2, 0, length);
                    if (length < 0) {
                        break;
                    }
                    instance.update(bArr2, 0, length);
                    j2 += (long) length;
                }
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
                bArr = instance.digest();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] sysGetBufferMd5(byte[] bArr, int i, int i2) {
        byte[] bArr2 = null;
        if (!(bArr == null || i2 == 0)) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bArr, i, i2);
                bArr2 = instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return bArr2;
    }
}
