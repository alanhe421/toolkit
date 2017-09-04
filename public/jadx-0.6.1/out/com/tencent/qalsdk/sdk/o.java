package com.tencent.qalsdk.sdk;

import android.content.Context;
import com.dynamicload.Lib.DLConstants;
import com.tencent.connect.common.Constants;
import com.tencent.mobileqq.pb.InvalidProtocolBufferMicroException;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.im_open.logParams.LogParams;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.util.ALog;
import com.tencent.qalsdk.util.QLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;

/* compiled from: LogReport */
public class o {
    static String b = o.class.getSimpleName();
    static final String c = "-------qalsdklogios";
    static String d = "report.zip";
    static String e = (QLog.getLogBasePath() + "/logzip/");
    static String f = "--";
    static String g = "\r\n";
    Context a = QalService.context;

    public void a(FromServiceMsg fromServiceMsg) {
        LogParams logParams = new LogParams();
        try {
            logParams.mergeFrom(e.a(fromServiceMsg));
            new p(this, logParams).start();
        } catch (InvalidProtocolBufferMicroException e) {
            e.printStackTrace();
        }
    }

    private boolean a(int i) {
        QLog.d(b, "network:" + m.e() + ":" + m.f());
        if (m.e() || i == 8) {
            return true;
        }
        return false;
    }

    private void a(LogParams logParams) {
        OutputStream outputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        OutputStream outputStream2;
        InputStream inputStream2;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        String str = logParams.url.get();
        String str2 = logParams.authorization.get();
        long j = (long) logParams.begtime.get();
        long j2 = (long) logParams.endtime.get();
        if (a(logParams.apn.get())) {
            byte[] a = a(j, j2, logParams.logtype.get());
            if (a == null) {
                QLog.d(b, "no log");
                return;
            }
            try {
                URL url = new URL(str);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection.setRequestProperty("Host", "web.file.myqcloud.com");
                httpURLConnection.setRequestProperty("Authorization", str2);
                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=-------qalsdklogios");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(g);
                stringBuilder.append(f + c + g);
                stringBuilder.append("Content-Disposition: form-data; name=\"op\"");
                stringBuilder.append(g + g);
                stringBuilder.append("upload");
                stringBuilder.append(g);
                stringBuilder.append(f + c + g);
                stringBuilder.append("Content-Disposition: form-data; name=\"filecontent\";filename=\"report.zip\"");
                stringBuilder.append(g + g);
                String str3 = f + c + g;
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(((stringBuilder.toString().getBytes().length + a.length) + g.length()) + str3.length()));
                outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(stringBuilder.toString().getBytes());
                    outputStream.write(a);
                    outputStream.write(g.getBytes());
                    outputStream.write(str3.getBytes());
                    outputStream.flush();
                    QLog.i(b, "http request:" + url + DLConstants.DEPENDENCY_PACKAGE_DIV + str2 + "|body len:" + stringBuilder.length());
                    int responseCode = httpURLConnection.getResponseCode();
                    QLog.i(b, "response:" + responseCode + ":" + httpURLConnection.getResponseMessage());
                    if (responseCode == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (Exception e2) {
                            e = e2;
                            byteArrayOutputStream = null;
                            outputStream2 = outputStream;
                            inputStream2 = inputStream;
                            try {
                                e.printStackTrace();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        return;
                                    }
                                }
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (byteArrayOutputStream == null) {
                                    byteArrayOutputStream.close();
                                    return;
                                }
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = inputStream2;
                                outputStream = outputStream2;
                                byteArrayOutputStream2 = byteArrayOutputStream;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (byteArrayOutputStream2 != null) {
                                    byteArrayOutputStream2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                            throw th;
                        }
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read < 1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            JSONObject jSONObject = new JSONObject(new String(byteArrayOutputStream.toByteArray()));
                            String string = jSONObject.getString("code");
                            QLog.d(b, "response:" + string + ":" + jSONObject.getString("message") + ":" + new JSONObject(jSONObject.getString("data")).getString("access_url"));
                        } catch (Exception e5) {
                            e = e5;
                            outputStream2 = outputStream;
                            inputStream2 = inputStream;
                            e.printStackTrace();
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (outputStream2 != null) {
                                outputStream2.close();
                            }
                            if (byteArrayOutputStream == null) {
                                byteArrayOutputStream.close();
                                return;
                            }
                            return;
                        } catch (Throwable th4) {
                            th = th4;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                            throw th;
                        }
                    }
                    byteArrayOutputStream = null;
                    inputStream = null;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return;
                        }
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                        return;
                    }
                    return;
                } catch (Exception e6) {
                    e = e6;
                    byteArrayOutputStream = null;
                    OutputStream outputStream3 = outputStream;
                    inputStream2 = null;
                    outputStream2 = outputStream3;
                    e.printStackTrace();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (byteArrayOutputStream == null) {
                        byteArrayOutputStream.close();
                        return;
                    }
                    return;
                } catch (Throwable th5) {
                    th = th5;
                    inputStream = null;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                byteArrayOutputStream = null;
                inputStream2 = null;
                e.printStackTrace();
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (byteArrayOutputStream == null) {
                    byteArrayOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th6) {
                th = th6;
                outputStream = null;
                inputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th;
            }
        }
        QLog.d(b, "network check, need not report");
    }

    private byte[] a(long j, long j2, int i) {
        try {
            File file = new File(e + d);
            if (file.exists()) {
                file.delete();
            }
            a(j, j2, i, e);
            FileInputStream fileInputStream = new FileInputStream(e + d);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void a(long j, long j2, int i, String str) throws IOException {
        ZipOutputStream zipOutputStream;
        FileNotFoundException e;
        Throwable th;
        long j3 = (j / 3600) * 3600;
        long j4 = ((j2 / 3600) + 1) * 3600;
        ZipOutputStream zipOutputStream2 = null;
        try {
            long j5;
            String str2;
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str + d));
            if (i == 1 || i == 3) {
                j5 = j3;
                while (j5 < j4) {
                    try {
                        str2 = QLog.getFilePath() + QLog.getLogFileName(1000 * j5);
                        File file2 = new File(str2);
                        if (file2.exists()) {
                            QLog.d(b, "zip file:" + str2);
                            a(file2, zipOutputStream);
                        }
                        j5 += 3600;
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        zipOutputStream2 = zipOutputStream;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
            if (i == 2 || i == 3) {
                for (j5 = j3; j5 < j4; j5 += 3600) {
                    str2 = ALog.getFilePath() + ALog.getLogFileName(1000 * j5);
                    File file3 = new File(str2);
                    if (file3.exists()) {
                        QLog.d(b, "zip file:" + str2);
                        a(file3, zipOutputStream);
                    }
                }
            }
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            try {
                e.printStackTrace();
                if (zipOutputStream2 != null) {
                    try {
                        zipOutputStream2.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            zipOutputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            throw th;
        }
    }

    void a(File file, ZipOutputStream zipOutputStream) {
        DataInputStream dataInputStream;
        Object e;
        Throwable th;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(new BufferedInputStream(fileInputStream));
            try {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                    zipOutputStream.flush();
                }
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e2) {
                        QLog.e(b, "创建ZIP文件失败" + e2);
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    QLog.e(b, "创建ZIP文件失败" + e);
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e22) {
                            QLog.e(b, "创建ZIP文件失败" + e22);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e4) {
                            QLog.e(b, "创建ZIP文件失败" + e4);
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                QLog.e(b, "创建ZIP文件失败" + e);
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e222) {
                        QLog.e(b, "创建ZIP文件失败" + e222);
                    }
                }
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            dataInputStream = null;
            QLog.e(b, "创建ZIP文件失败" + e);
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        } catch (IOException e7) {
            e = e7;
            dataInputStream = null;
            QLog.e(b, "创建ZIP文件失败" + e);
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
    }
}
