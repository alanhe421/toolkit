package com.qq.reader.filebrowser;

import com.qq.reader.activity.LocalBookActivity.b;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileSearch {
    private volatile boolean a = false;
    private Queue<File> b = new LinkedList();
    private b c;

    public native List<String> getFileList(String str, String str2);

    public native void pauseScan();

    static {
        System.loadLibrary("scan");
    }

    public void a(final String str, final String str2, b bVar) {
        this.c = bVar;
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            new Thread(new Runnable(this) {
                final /* synthetic */ FileSearch c;

                public void run() {
                    this.c.getFileList(str, str2);
                    if (ao.q() && ao.r()) {
                        this.c.getFileList(ao.s(), str2);
                    }
                    this.c.c.a(1003);
                }
            }).start();
        }
    }

    public void notifyChange(List<String> list) {
        List arrayList = new ArrayList();
        for (String file : list) {
            arrayList.add(new File(file));
        }
        this.c.a(arrayList);
        list.clear();
    }

    public boolean a() {
        return this.a;
    }

    public void a(boolean z) {
        this.a = z;
        if (z) {
            pauseScan();
        }
    }
}
