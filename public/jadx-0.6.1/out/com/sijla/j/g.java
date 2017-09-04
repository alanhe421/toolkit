package com.sijla.j;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.sijla.common.b;
import com.sijla.common.c;
import com.sijla.j.a.d;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.api.MidEntity;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONObject;

public class g {

    public interface a {
        void a(String str);

        void a(String str, JSONObject jSONObject);
    }

    public static void a(String str, JSONObject jSONObject, a aVar) {
        a(str, jSONObject, aVar, false);
    }

    public static void a(String str, JSONObject jSONObject, a aVar, boolean z) {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Exception exception;
        InputStream inputStream;
        Object obj;
        Throwable th;
        JSONObject jSONObject2;
        JSONObject c;
        Exception exception2;
        Throwable th2;
        Object obj2;
        String str2 = null;
        byte[] bytes = a(jSONObject).toString().getBytes();
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                a(bytes, httpURLConnection2);
                outputStream = httpURLConnection2.getOutputStream();
            } catch (Exception e) {
                outputStream = null;
                httpURLConnection = httpURLConnection2;
                exception = e;
                inputStream = null;
                obj = httpURLConnection;
                try {
                    f.d("request:" + str + " error:" + exception.getMessage());
                    if (aVar != null) {
                        aVar.a(exception.getMessage());
                    }
                    b.a(outputStream, inputStream);
                    b.a(str2);
                } catch (Throwable th3) {
                    th = th3;
                    b.a(outputStream, inputStream);
                    b.a(str2);
                    throw th;
                }
            } catch (Throwable th4) {
                outputStream = null;
                httpURLConnection = httpURLConnection2;
                th = th4;
                inputStream = null;
                obj = httpURLConnection;
                b.a(outputStream, inputStream);
                b.a(str2);
                throw th;
            }
            try {
                outputStream.write(bytes);
                int responseCode = httpURLConnection2.getResponseCode();
                f.a(str + " code:" + responseCode);
                if (aVar != null) {
                    if (responseCode != 200) {
                        aVar.a(responseCode + "");
                    } else if (z) {
                        try {
                            inputStream = httpURLConnection2.getInputStream();
                            try {
                                str2 = d.a(inputStream);
                                JSONObject jSONObject3 = new JSONObject(str2);
                                str2 = inputStream;
                                jSONObject2 = jSONObject3;
                            } catch (Throwable th5) {
                                try {
                                    c = b.c(str2);
                                    obj = inputStream;
                                    jSONObject2 = c;
                                    aVar.a(str, jSONObject2);
                                    b.a(outputStream, str2);
                                    b.a(httpURLConnection2);
                                } catch (Exception e2) {
                                    exception2 = e2;
                                    obj = httpURLConnection2;
                                    exception = exception2;
                                    f.d("request:" + str + " error:" + exception.getMessage());
                                    if (aVar != null) {
                                        aVar.a(exception.getMessage());
                                    }
                                    b.a(outputStream, inputStream);
                                    b.a(str2);
                                } catch (Throwable th6) {
                                    th2 = th6;
                                    obj = httpURLConnection2;
                                    th = th2;
                                    b.a(outputStream, inputStream);
                                    b.a(str2);
                                    throw th;
                                }
                            }
                        } catch (Throwable th7) {
                            inputStream = null;
                            c = b.c(str2);
                            obj = inputStream;
                            jSONObject2 = c;
                            aVar.a(str, jSONObject2);
                            b.a(outputStream, str2);
                            b.a(httpURLConnection2);
                        }
                        try {
                            aVar.a(str, jSONObject2);
                        } catch (Exception e3) {
                            exception2 = e3;
                            obj2 = str2;
                            obj = httpURLConnection2;
                            exception = exception2;
                            f.d("request:" + str + " error:" + exception.getMessage());
                            if (aVar != null) {
                                aVar.a(exception.getMessage());
                            }
                            b.a(outputStream, inputStream);
                            b.a(str2);
                        } catch (Throwable th42) {
                            th2 = th42;
                            obj2 = str2;
                            obj = httpURLConnection2;
                            th = th2;
                            b.a(outputStream, inputStream);
                            b.a(str2);
                            throw th;
                        }
                    } else {
                        aVar.a(str, jSONObject);
                    }
                }
                b.a(outputStream, str2);
                b.a(httpURLConnection2);
            } catch (Exception e32) {
                exception2 = e32;
                inputStream = null;
                str2 = httpURLConnection2;
                exception = exception2;
                f.d("request:" + str + " error:" + exception.getMessage());
                if (aVar != null) {
                    aVar.a(exception.getMessage());
                }
                b.a(outputStream, inputStream);
                b.a(str2);
            } catch (Throwable th422) {
                th2 = th422;
                inputStream = null;
                str2 = httpURLConnection2;
                th = th2;
                b.a(outputStream, inputStream);
                b.a(str2);
                throw th;
            }
        } catch (Exception e4) {
            exception = e4;
            inputStream = null;
            outputStream = null;
            f.d("request:" + str + " error:" + exception.getMessage());
            if (aVar != null) {
                aVar.a(exception.getMessage());
            }
            b.a(outputStream, inputStream);
            b.a(str2);
        } catch (Throwable th8) {
            th = th8;
            inputStream = null;
            outputStream = null;
            b.a(outputStream, inputStream);
            b.a(str2);
            throw th;
        }
    }

    public static boolean a(String str, JSONObject jSONObject) {
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        OutputStream outputStream = null;
        byte[] bytes = a(jSONObject).toString().getBytes();
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                a(bytes, httpURLConnection2);
                outputStream = httpURLConnection2.getOutputStream();
                outputStream.write(bytes);
                int responseCode = httpURLConnection2.getResponseCode();
                f.a(str + " code:" + responseCode);
                boolean z = responseCode == 200;
                b.a(outputStream);
                b.a(httpURLConnection2);
                return z;
            } catch (Exception e) {
                Exception exception2 = e;
                httpURLConnection = httpURLConnection2;
                exception = exception2;
                try {
                    f.d("request:" + str + " error:" + exception.getMessage());
                    b.a(outputStream);
                    b.a(httpURLConnection);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    b.a(outputStream);
                    b.a(httpURLConnection);
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection = httpURLConnection2;
                th = th4;
                b.a(outputStream);
                b.a(httpURLConnection);
                throw th;
            }
        } catch (Exception e2) {
            exception = e2;
            httpURLConnection = null;
            f.d("request:" + str + " error:" + exception.getMessage());
            b.a(outputStream);
            b.a(httpURLConnection);
            return false;
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            b.a(outputStream);
            b.a(httpURLConnection);
            throw th;
        }
    }

    private static void a(byte[] bArr, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(Constants.ERRORCODE_UNKNOWN);
        httpURLConnection.setReadTimeout(Constants.ERRORCODE_UNKNOWN);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod(com.tencent.connect.common.Constants.HTTP_POST);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
        if (b.a != null) {
            httpURLConnection.setRequestProperty("User-Agent", b.a);
        }
        if (b.b != null) {
            httpURLConnection.setRequestProperty("Qt-Agent", b.b);
        }
        httpURLConnection.setRequestProperty("Qt-v", "170425");
    }

    private static StringBuffer a(JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                stringBuffer.append(str).append("=").append(URLEncoder.encode(jSONObject.get(str) + "", "UTF-8")).append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public static c a(String str, JSONObject jSONObject, Map<String, File> map) {
        DataOutputStream dataOutputStream;
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        f.a("uploadFiles actionUrl = " + str);
        c cVar = new c();
        String uuid = UUID.randomUUID().toString();
        String str2 = "--";
        String str3 = "\r\n";
        String str4 = "multipart/form-data";
        String str5 = "UTF-8";
        Closeable closeable = null;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput(true);
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setRequestMethod(com.tencent.connect.common.Constants.HTTP_POST);
                httpURLConnection3.setRequestProperty("connection", "keep-alive");
                httpURLConnection3.setRequestProperty("Charsert", "UTF-8");
                httpURLConnection3.setRequestProperty("Content-Type", str4 + ";boundary=" + uuid);
                if (jSONObject != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str6 = (String) keys.next();
                        String optString = jSONObject.optString(str6);
                        stringBuilder.append(str2);
                        stringBuilder.append(uuid);
                        stringBuilder.append(str3);
                        stringBuilder.append("Content-Disposition: form-data; name=\"" + str6 + "\"" + str3);
                        stringBuilder.append("Content-Type: text/plain; charset=" + str5 + str3);
                        stringBuilder.append("Content-Transfer-Encoding: 8bit" + str3);
                        stringBuilder.append(str3);
                        stringBuilder.append(optString);
                        stringBuilder.append(str3);
                    }
                    dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                    try {
                        dataOutputStream.write(stringBuilder.toString().getBytes());
                    } catch (Exception e) {
                        Object obj = dataOutputStream;
                        httpURLConnection = httpURLConnection3;
                        exception = e;
                        httpURLConnection2 = httpURLConnection;
                        try {
                            cVar.a(false);
                            cVar.a(exception.getMessage());
                            b.a(closeable);
                            b.a(httpURLConnection2);
                            return cVar;
                        } catch (Throwable th2) {
                            th = th2;
                            Closeable closeable2 = closeable;
                            b.a(dataOutputStream);
                            b.a(httpURLConnection2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        httpURLConnection2 = httpURLConnection3;
                        th = th4;
                        b.a(dataOutputStream);
                        b.a(httpURLConnection2);
                        throw th;
                    }
                }
                dataOutputStream = null;
                if (map != null) {
                    for (Entry entry : map.entrySet()) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str2);
                        stringBuilder2.append(uuid);
                        stringBuilder2.append(str3);
                        stringBuilder2.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + ((String) entry.getKey()) + "\"" + str3);
                        stringBuilder2.append("Content-Type: application/octet-stream; charset=" + str5 + str3);
                        stringBuilder2.append(str3);
                        dataOutputStream.write(stringBuilder2.toString().getBytes());
                        InputStream fileInputStream = new FileInputStream((File) entry.getValue());
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            dataOutputStream.write(bArr, 0, read);
                        }
                        fileInputStream.close();
                        dataOutputStream.write(str3.getBytes());
                    }
                }
                dataOutputStream.write((str2 + uuid + str2 + str3).getBytes());
                dataOutputStream.flush();
                int responseCode = httpURLConnection3.getResponseCode();
                cVar.a(responseCode == 200);
                cVar.a(responseCode + "");
                f.a("post:" + str + " " + responseCode);
                b.a(dataOutputStream);
                b.a(httpURLConnection3);
            } catch (Exception e2) {
                Exception exception2 = e2;
                httpURLConnection2 = httpURLConnection3;
                exception = exception2;
                cVar.a(false);
                cVar.a(exception.getMessage());
                b.a(closeable);
                b.a(httpURLConnection2);
                return cVar;
            } catch (Throwable th32) {
                dataOutputStream = null;
                httpURLConnection = httpURLConnection3;
                th = th32;
                httpURLConnection2 = httpURLConnection;
                b.a(dataOutputStream);
                b.a(httpURLConnection2);
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            cVar.a(false);
            cVar.a(exception.getMessage());
            b.a(closeable);
            b.a(httpURLConnection2);
            return cVar;
        } catch (Throwable th5) {
            th = th5;
            dataOutputStream = null;
            b.a(dataOutputStream);
            b.a(httpURLConnection2);
            throw th;
        }
        return cVar;
    }

    public static File a(String str, String str2) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Object obj;
        HttpURLConnection httpURLConnection;
        Exception exception;
        Object obj2;
        HttpURLConnection httpURLConnection2;
        Closeable closeable;
        Closeable closeable2;
        Throwable th;
        Object obj3;
        Exception exception2;
        File file = null;
        if (b.a(str) || b.a(str2)) {
            return null;
        }
        f.a("down urlPath = [" + str + "], filename = [" + str2 + "]");
        File file2 = new File(str2);
        File parentFile = file2.getParentFile();
        if (!(parentFile == null || parentFile.exists())) {
            parentFile.mkdirs();
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(5000);
                httpURLConnection3.setReadTimeout(5000);
                httpURLConnection3.setRequestMethod(com.tencent.connect.common.Constants.HTTP_GET);
                int responseCode = httpURLConnection3.getResponseCode();
                f.a("NetWorkUtils.downFile responseCode:" + responseCode);
                if (200 == responseCode) {
                    inputStream = httpURLConnection3.getInputStream();
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (Exception e) {
                        obj = null;
                        httpURLConnection = httpURLConnection3;
                        exception = e;
                        obj2 = inputStream;
                        httpURLConnection2 = httpURLConnection;
                        try {
                            exception.printStackTrace();
                            f.d("down config fail:" + exception.getMessage());
                            b.a(closeable, closeable2);
                            b.a(httpURLConnection2);
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            obj3 = closeable2;
                            b.a(closeable, file);
                            b.a(httpURLConnection2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        obj = null;
                        obj3 = inputStream;
                        httpURLConnection2 = httpURLConnection3;
                        th = th3;
                        b.a(closeable, file);
                        b.a(httpURLConnection2);
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        file = fileOutputStream;
                    } catch (Exception e2) {
                        exception2 = e2;
                        closeable = fileOutputStream;
                        closeable2 = inputStream;
                        httpURLConnection2 = httpURLConnection3;
                        exception = exception2;
                        exception.printStackTrace();
                        f.d("down config fail:" + exception.getMessage());
                        b.a(closeable, closeable2);
                        b.a(httpURLConnection2);
                        return null;
                    } catch (Throwable th4) {
                        obj = fileOutputStream;
                        httpURLConnection = httpURLConnection3;
                        th = th4;
                        obj3 = inputStream;
                        httpURLConnection2 = httpURLConnection;
                        b.a(closeable, file);
                        b.a(httpURLConnection2);
                        throw th;
                    }
                }
                if (204 == responseCode) {
                    File file3 = new File(str2.replace(".gz", ""));
                    if (file3.exists() && file3.isFile()) {
                        com.sijla.j.a.b.a(" ", file3.getAbsolutePath(), true);
                        f.a("lastModified:" + b.b(file3.lastModified()));
                    }
                }
                inputStream = null;
                b.a(file, inputStream);
                b.a(httpURLConnection3);
                return file2;
            } catch (Exception e3) {
                obj2 = null;
                obj = null;
                exception2 = e3;
                httpURLConnection2 = httpURLConnection3;
                exception = exception2;
                exception.printStackTrace();
                f.d("down config fail:" + exception.getMessage());
                b.a(closeable, closeable2);
                b.a(httpURLConnection2);
                return null;
            } catch (Throwable th5) {
                obj = null;
                httpURLConnection = httpURLConnection3;
                th = th5;
                httpURLConnection2 = httpURLConnection;
                b.a(closeable, file);
                b.a(httpURLConnection2);
                throw th;
            }
        } catch (Exception e4) {
            exception = e4;
            Object obj4 = null;
            obj2 = null;
            obj = null;
            exception.printStackTrace();
            f.d("down config fail:" + exception.getMessage());
            b.a(closeable, closeable2);
            b.a(httpURLConnection2);
            return null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection2 = null;
            closeable = null;
            b.a(closeable, file);
            b.a(httpURLConnection2);
            throw th;
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            String str3 = b.g() + "";
            String substring = com.sijla.j.a.c.a(str3).substring(0, 8);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("quid", i.b(context));
            jSONObject.put("did", com.sijla.j.a.a.i(context));
            jSONObject.put("tchannel", b.m(context));
            jSONObject.put("from", str);
            jSONObject.put("tappid", context.getPackageName());
            jSONObject.put("tappver", com.sijla.j.a.a.f(context));
            jSONObject.put("tsdkver", 170425);
            jSONObject.put("mode", Build.MODEL);
            jSONObject.put("manufactuer", Build.MANUFACTURER);
            jSONObject.put("brand", Build.BRAND);
            jSONObject.put("osver", VERSION.SDK_INT);
            jSONObject.put(MidEntity.TAG_TIMESTAMPS, str3);
            f.a("ag:" + jSONObject.toString());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("s1", str3);
            jSONObject2.put("appid", context.getPackageName());
            jSONObject2.put("s2", com.sijla.f.b.a(substring, jSONObject.toString()));
            jSONObject2.put("ln", str2);
            str3 = com.sijla.d.c.a.optString("lgdata", "http://www.qmlog.cn/n/mlog/");
            f.c("lgdataurl = " + str3);
            if (!b.a(str3)) {
                a(str3, jSONObject2, new 1(str3), true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            f.b("save lg error:" + th.getMessage());
        }
    }
}
