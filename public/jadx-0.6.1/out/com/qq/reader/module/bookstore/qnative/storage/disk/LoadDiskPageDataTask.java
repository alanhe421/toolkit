package com.qq.reader.module.bookstore.qnative.storage.disk;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONObject;

public class LoadDiskPageDataTask extends ReaderIOTask {
    private a mLoadListener;
    private b mPage;
    private File mPageFile;

    public LoadDiskPageDataTask(b bVar, File file) {
        this.mPage = bVar;
        this.mPageFile = file;
    }

    public void run() {
        Exception e;
        Throwable th;
        InputStream bufferedInputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(this.mPageFile));
            try {
                org.apache.commons.compress.a.b.a(bufferedInputStream, byteArrayOutputStream);
                JSONObject jSONObject = new JSONObject(new String(byteArrayOutputStream.toByteArray(), "UTF-8"));
                if (this.mLoadListener != null) {
                    this.mPage.b(jSONObject);
                    this.mLoadListener.onLoadSucess(this.mPage);
                    if (this.mPage.c(jSONObject)) {
                        this.mLoadListener.onLoadFailed(this.mPage);
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (this.mLoadListener != null) {
                        this.mLoadListener.onLoadFailed(this.mPage);
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e22 = e5;
            bufferedInputStream = null;
            e22.printStackTrace();
            if (this.mLoadListener != null) {
                this.mLoadListener.onLoadFailed(this.mPage);
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public void setLoadListener(a aVar) {
        this.mLoadListener = aVar;
    }
}
