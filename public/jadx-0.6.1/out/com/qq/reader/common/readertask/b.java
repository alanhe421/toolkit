package com.qq.reader.common.readertask;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a.a.b.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.tencent.android.tpush.common.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* compiled from: ReaderFailedTaskDiskManager */
public class b {
    private static volatile b a = null;
    private com.qq.reader.common.imageloader.a.a.b b;

    private b() {
        d();
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
            bVar = a;
        }
        return bVar;
    }

    private void d() {
        try {
            this.b = a.a(ReaderApplication.getApplicationImp(), a.b(), 2097152, 0, new File(com.qq.reader.common.c.a.aj).getAbsolutePath());
        } catch (IOException e) {
            this.b = null;
            e.printStackTrace();
        }
    }

    protected synchronized boolean a(ReaderTask readerTask) {
        boolean z;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream byteArrayInputStream;
        Exception e;
        ByteArrayInputStream byteArrayInputStream2;
        ByteArrayOutputStream byteArrayOutputStream2;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream3 = null;
        synchronized (this) {
            if (this.b == null) {
                z = false;
            } else {
                File a = this.b.a(readerTask.getTaskKey());
                if (a == null || !a.exists()) {
                    if (readerTask instanceof ReaderProtocolTask) {
                        ((ReaderProtocolTask) readerTask).setHttpResponse(null);
                    }
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                            objectOutputStream.writeObject(readerTask);
                            objectOutputStream.flush();
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        } catch (Exception e2) {
                            e = e2;
                            byteArrayInputStream2 = null;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            try {
                                e.printStackTrace();
                                if (byteArrayInputStream2 != null) {
                                    try {
                                        byteArrayInputStream2.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e32) {
                                        e32.printStackTrace();
                                    }
                                }
                                z = false;
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                byteArrayInputStream3 = byteArrayInputStream2;
                                if (byteArrayInputStream3 != null) {
                                    try {
                                        byteArrayInputStream3.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e42) {
                                        e42.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (byteArrayInputStream3 != null) {
                                byteArrayInputStream3.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            throw th;
                        }
                        try {
                            this.b.a(readerTask.getTaskKey(), byteArrayInputStream, null);
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (IOException e322) {
                                    e322.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e3222) {
                                    e3222.printStackTrace();
                                }
                            }
                            z = true;
                        } catch (Exception e5) {
                            e = e5;
                            InputStream inputStream = byteArrayInputStream;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            e.printStackTrace();
                            if (byteArrayInputStream2 != null) {
                                byteArrayInputStream2.close();
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                            z = false;
                            return z;
                        } catch (Throwable th4) {
                            th = th4;
                            InputStream inputStream2 = byteArrayInputStream;
                            if (byteArrayInputStream3 != null) {
                                byteArrayInputStream3.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        byteArrayInputStream2 = null;
                        e.printStackTrace();
                        if (byteArrayInputStream2 != null) {
                            byteArrayInputStream2.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        z = false;
                        return z;
                    } catch (Throwable th5) {
                        th = th5;
                        byteArrayOutputStream = null;
                        if (byteArrayInputStream3 != null) {
                            byteArrayInputStream3.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                }
                c.a(Constants.LogTag, "ReaderFailedTaskDiskManager try save to disk : had found the same one  " + readerTask.getTaskKey());
                z = true;
            }
        }
        return z;
    }

    protected synchronized boolean a(String str) {
        boolean z;
        if (this.b == null) {
            z = false;
        } else {
            z = this.b.b(str);
        }
        return z;
    }

    protected ArrayList<ReaderProtocolTask> b() {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        FileInputStream fileInputStream;
        Exception exception;
        Throwable th;
        Throwable th2;
        Exception exception2;
        ArrayList<ReaderProtocolTask> arrayList = new ArrayList();
        if (this.b != null) {
            File a = this.b.a();
            if (a != null && a.isDirectory()) {
                File[] listFiles = a.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    int length = listFiles.length;
                    int i = 0;
                    objectInputStream = null;
                    FileInputStream fileInputStream2 = null;
                    while (i < length) {
                        File file = listFiles[i];
                        if (!file.exists()) {
                            objectInputStream2 = objectInputStream;
                        } else if (file.getName().startsWith("journal")) {
                            objectInputStream2 = objectInputStream;
                        } else {
                            try {
                                fileInputStream = new FileInputStream(file);
                                try {
                                    ObjectInputStream objectInputStream3 = new ObjectInputStream(fileInputStream);
                                    try {
                                        ReaderProtocolTask readerProtocolTask = (ReaderProtocolTask) objectInputStream3.readObject();
                                        if (readerProtocolTask != null) {
                                            arrayList.add(readerProtocolTask);
                                        }
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception e) {
                                                fileInputStream2 = fileInputStream;
                                                objectInputStream2 = objectInputStream3;
                                            }
                                        }
                                        if (objectInputStream3 != null) {
                                            objectInputStream3.close();
                                        }
                                        fileInputStream2 = fileInputStream;
                                        objectInputStream2 = objectInputStream3;
                                    } catch (Exception e2) {
                                        exception = e2;
                                        fileInputStream2 = fileInputStream;
                                        objectInputStream2 = objectInputStream3;
                                        try {
                                            exception.printStackTrace();
                                            if (fileInputStream2 != null) {
                                                try {
                                                    fileInputStream2.close();
                                                } catch (Exception e3) {
                                                }
                                            }
                                            if (objectInputStream2 == null) {
                                                objectInputStream2.close();
                                            }
                                            i++;
                                            objectInputStream = objectInputStream2;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            objectInputStream = objectInputStream2;
                                            fileInputStream = fileInputStream2;
                                            th2 = th;
                                        }
                                    } catch (Throwable th4) {
                                        th2 = th4;
                                        objectInputStream = objectInputStream3;
                                    }
                                } catch (Exception e22) {
                                    exception2 = e22;
                                    fileInputStream2 = fileInputStream;
                                    objectInputStream2 = objectInputStream;
                                    exception = exception2;
                                    exception.printStackTrace();
                                    if (fileInputStream2 != null) {
                                        fileInputStream2.close();
                                    }
                                    if (objectInputStream2 == null) {
                                        objectInputStream2.close();
                                    }
                                    i++;
                                    objectInputStream = objectInputStream2;
                                } catch (Throwable th5) {
                                    th2 = th5;
                                }
                            } catch (Exception e4) {
                                exception2 = e4;
                                objectInputStream2 = objectInputStream;
                                exception = exception2;
                                exception.printStackTrace();
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                if (objectInputStream2 == null) {
                                    objectInputStream2.close();
                                }
                                i++;
                                objectInputStream = objectInputStream2;
                            } catch (Throwable th6) {
                                th = th6;
                                fileInputStream = fileInputStream2;
                                th2 = th;
                            }
                        }
                        i++;
                        objectInputStream = objectInputStream2;
                    }
                }
            }
        }
        return arrayList;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e5) {
                throw th2;
            }
        }
        if (objectInputStream != null) {
            objectInputStream.close();
        }
        throw th2;
    }

    public void c() {
        synchronized (b.class) {
            a = null;
        }
    }
}
