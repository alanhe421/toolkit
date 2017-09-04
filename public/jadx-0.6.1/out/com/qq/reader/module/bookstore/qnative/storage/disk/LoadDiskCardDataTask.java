package com.qq.reader.module.bookstore.qnative.storage.disk;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.card.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.a.b;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoadDiskCardDataTask extends ReaderIOTask {
    private a mCard;
    private File mCardFile;
    private a mLoadListener;

    public LoadDiskCardDataTask(a aVar, File file) {
        this.mCard = aVar;
        this.mCardFile = file;
    }

    public void run() {
        InputStream bufferedInputStream;
        Exception e;
        Throwable th;
        Object obj = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(this.mCardFile));
            try {
                b.a(bufferedInputStream, byteArrayOutputStream);
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String t = ao.t(str);
                if ("JSON_TYPE_OBJECT".equals(t)) {
                    obj = new JSONObject(str);
                    if (obj != null) {
                        obj.put("data_from_cache", true);
                    }
                } else if ("JSON_TYPE_ARRAY".equals(t)) {
                    obj = new JSONArray(str);
                }
                if (obj == null || !this.mCard.fillData(obj)) {
                    if (this.mLoadListener != null) {
                        this.mLoadListener.onLoadFailed(this.mCard);
                    }
                } else if (this.mLoadListener != null) {
                    this.mLoadListener.onLoadSucess(this.mCard);
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
                        this.mLoadListener.onLoadFailed(this.mCard);
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
                this.mLoadListener.onLoadFailed(this.mCard);
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
