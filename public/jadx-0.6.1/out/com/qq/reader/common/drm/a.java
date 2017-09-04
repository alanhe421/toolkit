package com.qq.reader.common.drm;

import android.content.Context;
import com.qq.reader.common.drm.teb.TeaTool;
import com.qq.reader.common.login.c;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;
import com.qq.reader.common.readertask.protocol.IdentifyTask;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Drm */
public class a {
    public static String a;
    private a b;
    private Context c;
    private String d;

    /* compiled from: Drm */
    public interface a {
        void f(int i);

        void x();
    }

    public a(Context context, String str) {
        this.c = context;
        this.d = str;
    }

    public void a(String str, boolean z) {
        ReaderTask identifyTask = new IdentifyTask(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                this.a.a(readerProtocolTask, inputStream);
            }

            public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.b != null) {
                    this.a.b.f(3);
                }
            }
        }, str);
        identifyTask.setTrial(z);
        g.a().a(identifyTask);
    }

    public void b(String str, boolean z) {
        ReaderTask identifyTask = new IdentifyTask(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                this.a.a(readerProtocolTask, inputStream);
            }

            public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.b != null) {
                    this.a.b.f(3);
                }
            }
        }, str);
        identifyTask.setTrial(z);
        g.a().a(identifyTask);
    }

    public int a() {
        Exception e;
        JSONException jSONException;
        Throwable th;
        int i = -1;
        String a = a(this.d);
        if (a == null) {
            return i;
        }
        File file = new File(a);
        if (file == null || !file.exists()) {
            return 1;
        }
        byte[] bArr = new byte[((int) file.length())];
        DataInputStream dataInputStream = null;
        try {
            DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(file));
            try {
                dataInputStream2.readFully(bArr);
                i = a(bArr);
                if (dataInputStream2 == null) {
                    return i;
                }
                try {
                    dataInputStream2.close();
                    return i;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return i;
                }
            } catch (JSONException e3) {
                dataInputStream = dataInputStream2;
                jSONException = e3;
                try {
                    if (c.b()) {
                        i = -2;
                    } else {
                        i = 2;
                    }
                    jSONException.printStackTrace();
                    if (dataInputStream != null) {
                        return i;
                    }
                    try {
                        dataInputStream.close();
                        return i;
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        return i;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                Exception exception = e4;
                dataInputStream = dataInputStream2;
                e222 = exception;
                e222.printStackTrace();
                if (dataInputStream != null) {
                    return i;
                }
                try {
                    dataInputStream.close();
                    return i;
                } catch (Exception e2222) {
                    e2222.printStackTrace();
                    return i;
                }
            } catch (Throwable th3) {
                th = th3;
                dataInputStream = dataInputStream2;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                throw th;
            }
        } catch (JSONException e32) {
            jSONException = e32;
            if (c.b()) {
                i = 2;
            } else {
                i = -2;
            }
            jSONException.printStackTrace();
            if (dataInputStream != null) {
                return i;
            }
            dataInputStream.close();
            return i;
        } catch (Exception e5) {
            e2222 = e5;
            e2222.printStackTrace();
            if (dataInputStream != null) {
                return i;
            }
            dataInputStream.close();
            return i;
        }
    }

    public static String b() {
        return a;
    }

    private byte[] a(byte[] bArr, int[] iArr) {
        Object obj;
        int length = bArr.length % 8;
        if (length != 0) {
            obj = new byte[(length + bArr.length)];
        } else {
            obj = new byte[bArr.length];
        }
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        Object obj2 = new byte[8];
        int length2 = obj.length / 8;
        for (int i = 0; i < length2; i++) {
            System.arraycopy(obj, i * 8, obj2, 0, 8);
            System.arraycopy(TeaTool.b(obj2, iArr), 0, obj, i * 8, 8);
        }
        return obj;
    }

    private String a(Context context) {
        if (context == null) {
            return null;
        }
        return com.qq.reader.appconfig.a.d.R(context);
    }

    private int a(byte[] bArr) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject(new String(a(bArr, TeaTool.a(a(this.c))), 0, bArr.length, "utf-8").trim());
        JSONArray jSONArray = jSONObject.getJSONArray("readid");
        String str = "";
        List arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.getString(i));
        }
        if (arrayList.contains(com.qq.reader.appconfig.a.d.i(this.c))) {
            a = jSONObject.getString("pwd");
            return 0;
        } else if (jSONObject.getInt("max") <= 0) {
            return -3;
        } else {
            return 1;
        }
    }

    private void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            if (c.b()) {
                JSONObject jSONObject = new JSONObject(new String(a(toByteArray, TeaTool.a(a(this.c))), 0, toByteArray.length, "utf-8").trim());
                int i = jSONObject.getInt("code");
                if (i == 0) {
                    b(toByteArray);
                    a = jSONObject.getString("pwd");
                    if (this.b != null) {
                        this.b.x();
                    }
                } else if (this.b != null) {
                    this.b.f(i);
                }
            } else if (this.b != null) {
                this.b.f(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (this.b != null) {
                this.b.f(3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.b != null) {
                this.b.f(-1);
            }
        }
    }

    private void b(byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream;
        Throwable th;
        String a = a(this.d);
        if (a == null) {
            throw new IOException("keyFilePath fatal!!");
        } else if (bArr == null) {
            throw new IOException("teaXmlBytes fatal!!");
        } else {
            File file = new File(a);
            if (file != null && file.exists()) {
                file.delete();
            }
            file.createNewFile();
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileOutputStream.close();
                throw th;
            }
        }
    }

    public static String a(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf) + "/" + str.hashCode() + ".ks";
        }
        return str + ".ks";
    }

    public static String b(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf) + ".key";
        }
        return str + ".key";
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
