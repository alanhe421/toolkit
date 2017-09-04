package com.tencent.upload.task.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.text.TextUtils;
import com.tencent.upload.task.ITask;
import com.tencent.upload.task.UploadTask;
import com.tencent.upload.utils.Base64;
import java.util.ArrayList;
import java.util.List;

public class StorageHelper {
    private String mPersistenceId;
    private SharedPreferences mSharedPreferences;

    public StorageHelper(Context context, String str) {
        this.mPersistenceId = str;
        if (!TextUtils.isEmpty(this.mPersistenceId)) {
            this.mSharedPreferences = context.getSharedPreferences(this.mPersistenceId, 0);
        }
    }

    public List<ITask> loadUploadTasks() {
        Parcel parcel = null;
        List<ITask> arrayList = new ArrayList();
        if (this.mSharedPreferences == null) {
            return arrayList;
        }
        String string = this.mSharedPreferences.getString("upload_tasks", null);
        if (string != null) {
            try {
                byte[] decode = Base64.decode(string, 0);
                parcel = Parcel.obtain();
                parcel.unmarshall(decode, 0, decode.length);
                parcel.setDataPosition(0);
                parcel.readList(arrayList, UploadTask.class.getClassLoader());
                if (parcel != null) {
                    parcel.recycle();
                }
            } catch (Throwable th) {
                if (parcel != null) {
                    parcel.recycle();
                }
            }
        }
        List<ITask> arrayList2 = new ArrayList();
        for (ITask iTask : arrayList) {
            if (((UploadTask) iTask).authIsEmpty()) {
                arrayList2.add(iTask);
            }
        }
        for (ITask iTask2 : arrayList2) {
            arrayList.remove(iTask2);
        }
        return arrayList;
    }

    public void saveUploadTasks(java.util.List<com.tencent.upload.task.ITask> r5) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r0 = r4.mSharedPreferences;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        if (r5 == 0) goto L_0x0004;
    L_0x0007:
        r2 = new java.util.ArrayList;
        r2.<init>();
        r3 = r5.iterator();
    L_0x0010:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0031;
    L_0x0016:
        r0 = r3.next();
        r0 = (com.tencent.upload.task.ITask) r0;
        if (r0 == 0) goto L_0x0010;
    L_0x001e:
        r1 = r0 instanceof com.tencent.upload.task.UploadTask;
        if (r1 == 0) goto L_0x0010;
    L_0x0022:
        r1 = r0;
        r1 = (com.tencent.upload.task.UploadTask) r1;
        r1 = r1.authIsEmpty();
        if (r1 != 0) goto L_0x0010;
    L_0x002b:
        r0 = (com.tencent.upload.task.UploadTask) r0;
        r2.add(r0);
        goto L_0x0010;
    L_0x0031:
        r1 = 0;
        r1 = android.os.Parcel.obtain();	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r1.writeList(r2);	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0 = r1.marshall();	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r3 = 0;	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0 = com.tencent.upload.utils.Base64.encode(r0, r3);	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r2.<init>(r0);	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0 = r4.mSharedPreferences;	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0 = r0.edit();	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r3 = "upload_tasks";	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0 = r0.putString(r3, r2);	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        r0.commit();	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        if (r1 == 0) goto L_0x0004;
    L_0x0059:
        r1.recycle();
        goto L_0x0004;
    L_0x005d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Throwable -> 0x005d, all -> 0x0067 }
        if (r1 == 0) goto L_0x0004;
    L_0x0063:
        r1.recycle();
        goto L_0x0004;
    L_0x0067:
        r0 = move-exception;
        if (r1 == 0) goto L_0x006d;
    L_0x006a:
        r1.recycle();
    L_0x006d:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.task.storage.StorageHelper.saveUploadTasks(java.util.List):void");
    }
}
