package com.qq.reader.common.offline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.conn.http.HttpErrorException;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.ao;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: OfflineRequestManager */
public class c {
    private static volatile c c;
    private Map<String, Long> a = Collections.synchronizedMap(new HashMap());
    private long b = 1;
    private Context d;
    private Map<String, WeakReference<Handler>> e = new HashMap();
    private Thread f;
    private String g = "";
    private HashMap<String, Boolean> h = new HashMap();

    public c(Context context) {
        this.d = context;
    }

    public static c a(Context context) {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c(context.getApplicationContext());
                }
            }
        }
        return c;
    }

    public void a(Handler handler, String str) {
        this.e.put(str, new WeakReference(handler));
    }

    public void a(String str) {
        this.e.remove(str);
    }

    public void a(OfflineRequestTask offlineRequestTask) {
        offlineRequestTask.registerReaderNetTaskListener(new d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                try {
                    this.a.a((ReaderNetTask) readerProtocolTask, exception);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void a(ReaderProtocolTask readerProtocolTask, String str, String str2) {
                this.a.a(readerProtocolTask, str, str2);
            }
        });
        offlineRequestTask.setTid(this.b);
        if (offlineRequestTask instanceof OfflineRequestTask) {
            this.a.put(offlineRequestTask.getUrl(), Long.valueOf(System.currentTimeMillis()));
        }
        this.b++;
        g.a().a((ReaderTask) offlineRequestTask);
    }

    private void a(ReaderProtocolTask readerProtocolTask, String str, String str2) {
        long j = 0;
        OfflineRequestTask offlineRequestTask = (OfflineRequestTask) readerProtocolTask;
        offlineRequestTask.getTid();
        String callBackMethod = offlineRequestTask.getCallBackMethod();
        b bVar = new b();
        bVar.a(callBackMethod);
        bVar.b(str);
        if (str2.contains("max-age")) {
            String[] split = str2.split("=");
            if (split.length == 2) {
                j = Long.valueOf(split[1]).longValue() * 1000;
            }
        }
        long currentTimeMillis = j + System.currentTimeMillis();
        String hostName = offlineRequestTask.getHostName();
        if (offlineRequestTask.getShouldCallBack()) {
            Handler handler;
            WeakReference weakReference = (WeakReference) this.e.get(hostName);
            if (weakReference != null) {
                handler = (Handler) weakReference.get();
            } else {
                handler = null;
            }
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.obj = bVar;
                obtainMessage.what = 90004;
                handler.sendMessageDelayed(obtainMessage, 100);
                this.h.put(hostName, new Boolean(false));
            } else {
                this.h.put(hostName, new Boolean(true));
            }
            long currentTimeMillis2 = System.currentTimeMillis() - ((Long) this.a.get(offlineRequestTask.getUrl())).longValue();
            i.a("event_offline_pagedata_load_from_net", true, System.currentTimeMillis() - offlineRequestTask.getRunTime(), 0, null, ReaderApplication.getApplicationImp());
        }
        if (offlineRequestTask.getShouldCache() && str != null) {
            a(offlineRequestTask.getUrl(), callBackMethod, str, String.valueOf(currentTimeMillis));
        }
    }

    public void a(ReaderDownloadTask readerDownloadTask) {
        g.a().a((ReaderTask) readerDownloadTask);
    }

    public boolean b(String str) {
        Boolean bool = (Boolean) this.h.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void a(String str, boolean z) {
        this.h.put(str, new Boolean(z));
    }

    private void a(ReaderNetTask readerNetTask, Exception exception) {
        int i = 1002;
        if (readerNetTask instanceof OfflineRequestTask) {
            int i2 = 1000;
            if (exception instanceof HttpResponseException) {
                i2 = ((HttpResponseException) exception).getStateCode();
            } else if (exception instanceof UnknownHostException) {
                i2 = 1001;
            } else if (exception instanceof SocketTimeoutException) {
                i2 = 1002;
            }
            OfflineRequestTask offlineRequestTask = (OfflineRequestTask) readerNetTask;
            if (offlineRequestTask.getShouldCallBack()) {
                Handler handler;
                String callBackMethod = offlineRequestTask.getCallBackMethod();
                Object c = c(offlineRequestTask.getUrl());
                if (c == null) {
                    c = new b();
                    c.a(callBackMethod);
                    c.b("{\"httpcode\":\"" + i2 + "\"}");
                }
                String hostName = offlineRequestTask.getHostName();
                WeakReference weakReference = (WeakReference) this.e.get(hostName);
                if (weakReference != null) {
                    handler = (Handler) weakReference.get();
                } else {
                    handler = null;
                }
                if (handler != null) {
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.obj = c;
                    obtainMessage.what = 90004;
                    handler.sendMessageDelayed(obtainMessage, 100);
                    this.h.put(hostName, new Boolean(false));
                } else {
                    this.h.put(offlineRequestTask.getHostName(), new Boolean(true));
                }
                f.a("error", "finish");
                Map hashMap = new HashMap();
                hashMap.put("Exception", exception.toString() + " || " + exception.getMessage());
                hashMap.put(SocialConstants.PARAM_URL, offlineRequestTask.getUrl());
                i2 = Constants.ERRORCODE_UNKNOWN;
                try {
                    if (exception instanceof SocketTimeoutException) {
                        i2 = 1001;
                    } else if (exception instanceof HttpErrorException) {
                        i2 = ((HttpErrorException) exception).getStateCode();
                    } else if (exception instanceof SocketException) {
                        if (exception instanceof ConnectException) {
                            if (!(exception.getMessage() == null || exception.getMessage().indexOf("failed to connect to /10.0.0.172") == -1 || exception.getMessage().indexOf("ECONNREFUSED") == -1)) {
                                i2 = 1005;
                            }
                        } else if (!(exception.getMessage() == null || exception.getMessage().indexOf("No route") == -1)) {
                            i2 = 1006;
                        }
                    } else if (exception instanceof IOException) {
                        String message = exception.getMessage();
                        if (message != null) {
                            if (exception.getMessage().indexOf("No space left") == -1) {
                                if (message.indexOf("onlineUnZip failed unknown format") != -1) {
                                    i = 1008;
                                } else if (!(message.indexOf("onlineUnZip failed") == -1 || message.indexOf("No such file") == -1)) {
                                    i = 1009;
                                }
                            }
                            i2 = i;
                        }
                        i = Constants.ERRORCODE_UNKNOWN;
                        i2 = i;
                    } else if (exception instanceof IllegalArgumentException) {
                        i2 = 1007;
                    }
                } catch (Throwable th) {
                }
                hashMap.put("param_FailCode", "" + i2);
                i.a("event_offline_pagedata_load_from_net", false, 0, 0, hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
            }
        }
    }

    private String d(String str) {
        String[] split = str.substring(str.indexOf("?") + 1).split("&");
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            String str3 = str2.split("=")[0];
            stringBuilder.append(str2 + "&");
        }
        return str.substring(0, str.indexOf("?") + 1) + stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public void a(String str, String str2, String str3, String str4) {
        IOException e;
        FileOutputStream fileOutputStream;
        FileNotFoundException e2;
        Throwable th;
        File file = new File(a.db);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str5 = a.db + ao.r(d(str));
        File file2 = new File(str5 + com.qq.reader.common.download.task.f.DOWNLOAD_FILE_TMP);
        if (!file2.exists()) {
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            String str6 = str4 + "$" + str2 + "$" + str3;
            b bVar = new b(str2, str3, str4);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    fileOutputStream.write(str6.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    File file3 = new File(str5);
                    if (file3 != null && file3.exists()) {
                        file3.delete();
                    }
                    file2.renameTo(file3);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    try {
                        e2.printStackTrace();
                        file2.delete();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e322 = e6;
                    e322.printStackTrace();
                    file2.delete();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                fileOutputStream = null;
                e2.printStackTrace();
                file2.delete();
                fileOutputStream.close();
            } catch (IOException e8) {
                e3222 = e8;
                fileOutputStream = null;
                e3222.printStackTrace();
                file2.delete();
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileOutputStream.close();
                throw th;
            }
        }
    }

    public b c(String str) {
        return e(str);
    }

    private b e(String str) {
        File file = new File(a.db + ao.r(d(str)));
        if (file == null || !file.exists()) {
            return null;
        }
        return a(file);
    }

    private b a(File file) {
        BufferedReader bufferedReader;
        String readLine;
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e2 = e4;
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            bufferedReader = null;
            try {
                e.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                readLine = stringBuilder.toString();
                return readLine != null ? null : null;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e2222 = e6;
            bufferedReader = null;
            e2222.printStackTrace();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
            readLine = stringBuilder.toString();
            if (readLine != null) {
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        readLine = stringBuilder.toString();
        if (readLine != null && !readLine.equals("")) {
            b bVar = new b();
            int indexOf = readLine.indexOf("$");
            bVar.c(readLine.substring(0, indexOf));
            readLine = readLine.substring(indexOf + 1);
            indexOf = readLine.indexOf("$");
            bVar.a(readLine.substring(0, indexOf));
            bVar.b(readLine.substring(indexOf + 1));
            return bVar;
        }
    }

    public void a() {
        if (this.f == null || !this.f.isAlive()) {
            this.f = new Thread(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    int i = 0;
                    super.run();
                    File file = new File(a.db);
                    if (file != null && file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null) {
                            for (File file2 : listFiles) {
                                if (System.currentTimeMillis() - file2.lastModified() > 259200000) {
                                    file2.delete();
                                }
                            }
                        }
                    }
                    file = new File(a.dc);
                    if (file != null && file.exists()) {
                        File[] listFiles2 = file.listFiles();
                        if (listFiles2 != null) {
                            int length = listFiles2.length;
                            while (i < length) {
                                File file3 = listFiles2[i];
                                if (System.currentTimeMillis() - file3.lastModified() > 259200000) {
                                    file3.delete();
                                }
                                i++;
                            }
                        }
                    }
                }
            };
            this.f.start();
        }
    }
}
