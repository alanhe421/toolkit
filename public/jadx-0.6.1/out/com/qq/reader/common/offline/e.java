package com.qq.reader.common.offline;

import com.etrump.jni.ETConverter;
import com.qq.reader.common.utils.a.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OfflineSecurity */
public class e {
    protected static String a;
    private static String b = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrREOFRN9uYS869mOeLmZXFw3djnofd7wbf3ru6zmRB7P6gTpmnvJNnclCcEC7TOmDImvVl+gVPXQ0AmWAI4q042rALV5NPCJiOpIzSgJH2l0F/ZVbj69QztBiKmSHVHqQ8yemqtFljNEJbE9HL3RXE/uwGmHViFl4fGg9am5w7QIDAQAB";
    private static char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean a(String str, String str2) {
        if (str2 == null) {
            str2 = "0";
        }
        a = str2;
        if (a(str)) {
            return b(str);
        }
        return false;
    }

    public static boolean a(String str) {
        InputStream fileInputStream;
        InputStream fileInputStream2;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        InputStream inputStream = null;
        if (new File(str + "/verify.json").exists()) {
            try {
                byte[] b;
                fileInputStream = new FileInputStream(new File(str + "/verify.json"));
                try {
                    b = b(fileInputStream);
                    fileInputStream2 = new FileInputStream(new File(str + "/verify.signature"));
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileInputStream2 = null;
                    inputStream = fileInputStream;
                    try {
                        e.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = inputStream;
                        inputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e2 = e6;
                    try {
                        e2.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e7) {
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                }
                try {
                    boolean a = a(b, b, b(fileInputStream2));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e8) {
                            return a;
                        }
                    }
                    if (fileInputStream2 == null) {
                        return a;
                    }
                    fileInputStream2.close();
                    return a;
                } catch (FileNotFoundException e9) {
                    e = e9;
                    inputStream = fileInputStream;
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return false;
                } catch (IOException e10) {
                    e2 = e10;
                    inputStream = fileInputStream2;
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e11) {
                e = e11;
                fileInputStream2 = null;
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return false;
            } catch (IOException e12) {
                e2 = e12;
                fileInputStream = null;
                e2.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return false;
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        return false;
    }

    public static String a(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return stringBuffer.toString();
            }
            stringBuffer.append(new String(bArr, 0, read));
        }
    }

    public static boolean b(String str) {
        InputStream fileInputStream;
        IOException e;
        JSONException e2;
        FileNotFoundException e3;
        Throwable th;
        InputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(str + "/verify.json"));
            try {
                JSONObject jSONObject = new JSONObject(a(fileInputStream));
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                Iterator keys = jSONObject.keys();
                String str2 = "";
                while (keys.hasNext()) {
                    str2 = keys.next().toString();
                    try {
                        if (!jSONObject.getString(str2).equals(c(str + File.separator + str2))) {
                            return false;
                        }
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                }
                return true;
            } catch (FileNotFoundException e5) {
                e3 = e5;
                try {
                    e3.printStackTrace();
                    if (fileInputStream != null) {
                        return false;
                    }
                    try {
                        fileInputStream.close();
                        return false;
                    } catch (IOException e42) {
                        e42.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (JSONException e6) {
                e22 = e6;
                inputStream = fileInputStream;
                try {
                    e22.printStackTrace();
                    if (inputStream != null) {
                        return false;
                    }
                    try {
                        inputStream.close();
                        return false;
                    } catch (IOException e4222) {
                        e4222.printStackTrace();
                        return false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e7) {
                e4222 = e7;
                inputStream = fileInputStream;
                e4222.printStackTrace();
                if (inputStream != null) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (IOException e42222) {
                    e42222.printStackTrace();
                    return false;
                }
            }
        } catch (FileNotFoundException e8) {
            e3 = e8;
            fileInputStream = null;
            e3.printStackTrace();
            if (fileInputStream != null) {
                return false;
            }
            fileInputStream.close();
            return false;
        } catch (JSONException e9) {
            e22 = e9;
            e22.printStackTrace();
            if (inputStream != null) {
                return false;
            }
            inputStream.close();
            return false;
        } catch (IOException e10) {
            e42222 = e10;
            e42222.printStackTrace();
            if (inputStream != null) {
                return false;
            }
            inputStream.close();
            return false;
        }
    }

    public static String c(String str) {
        String str2 = "";
        try {
            str2 = b(str, "SHA1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String b(String str, String str2) throws Exception {
        InputStream fileInputStream = new FileInputStream(str);
        byte[] bArr = new byte[1024];
        MessageDigest instance = MessageDigest.getInstance(str2);
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read > 0) {
                instance.update(bArr, 0, read);
            } else {
                fileInputStream.close();
                return a(instance.digest());
            }
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(c[(bArr[i] & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4]);
            stringBuilder.append(c[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                byteArrayOutputStream.write(read);
            } else {
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return toByteArray;
            }
        }
    }

    public static boolean a(String str, byte[] bArr, byte[] bArr2) {
        try {
            byte[] b = a.b(str);
            KeyFactory instance = KeyFactory.getInstance("RSA");
            Signature instance2 = Signature.getInstance("SHA1withRSA");
            instance2.initVerify(instance.generatePublic(new X509EncodedKeySpec(b)));
            instance2.update(bArr);
            return instance2.verify(bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
