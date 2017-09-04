package com.qq.reader.module.feed.loader;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.data.impl.g;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.compress.a.b;
import org.json.JSONObject;

public class FeedLoadDiskDataTask extends ReaderIOTask {
    private e mDataPackage;
    private ArrayList<File> mFileList;
    private a mLoadListener;

    public FeedLoadDiskDataTask(e eVar, ArrayList<File> arrayList) {
        this.mDataPackage = eVar;
        this.mFileList = arrayList;
    }

    public void run() {
        InputStream bufferedInputStream;
        Exception e;
        Throwable th;
        this.mDataPackage.k();
        Iterator it = this.mFileList.iterator();
        File file = null;
        Object obj = 1;
        while (it.hasNext()) {
            File file2 = (File) it.next();
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                try {
                    b.a(bufferedInputStream, byteArrayOutputStream);
                    JSONObject jSONObject = new JSONObject(new String(byteArrayOutputStream.toByteArray(), "UTF-8"));
                    g gVar = new g();
                    String name = file2.getName();
                    int lastIndexOf = name.lastIndexOf(".");
                    if (lastIndexOf != -1) {
                        name = name.substring(0, lastIndexOf);
                    }
                    int indexOf = name.indexOf("-");
                    if (indexOf != -1) {
                        lastIndexOf = Integer.parseInt(name.substring(indexOf + 1));
                        name = name.substring(0, indexOf);
                    } else {
                        lastIndexOf = 0;
                    }
                    gVar.a(name);
                    gVar.a(lastIndexOf);
                    gVar.b(jSONObject.toString());
                    this.mDataPackage.a(gVar);
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    obj = 1;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                file = file2;
                                obj = null;
                            }
                        }
                        file = file2;
                        obj = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Exception e5) {
                e4 = e5;
                bufferedInputStream = null;
                e4.printStackTrace();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                file = file2;
                obj = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        }
        if (obj == null && file != null) {
            try {
                if (file.exists() && file.isFile()) {
                    String name2 = file.getName();
                    if (!(name2 == null || name2.indexOf("-") == -1)) {
                        file.delete();
                    }
                }
            } catch (Exception e42) {
                e42.printStackTrace();
            }
        }
        if (this.mLoadListener == null) {
            return;
        }
        if (obj != null) {
            this.mLoadListener.onLoadSucess(this.mDataPackage);
            return;
        } else {
            this.mLoadListener.onLoadFailed(this.mDataPackage);
            return;
        }
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (Exception e422) {
                e422.printStackTrace();
            }
        }
        throw th;
        throw th;
    }

    public void setLoadListener(a aVar) {
        this.mLoadListener = aVar;
    }
}
