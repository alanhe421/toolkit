package format.archive;

import android.util.Log;
import com.qq.reader.common.c.a;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import org.apache.a.a.e;

/* compiled from: ZipUtils */
public class d {
    static {
        File file = new File(a.u);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static List<FileItem> a(String str, String str2, boolean z) {
        Object obj;
        List<FileItem> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        try {
            Enumeration b = new b(str, "gbk").b();
            int i = 0;
            while (b.hasMoreElements()) {
                e eVar = (e) b.nextElement();
                String a = a(eVar);
                String c = c(str2, a, z);
                if (!(c == null || com.qq.reader.readengine.model.a.c(c))) {
                    FileItem fileItem = (FileItem) hashMap.get(c);
                    if (fileItem == null) {
                        FileItem fileItem2 = new FileItem();
                        fileItem2.mCompressType = "zip";
                        int i2 = i + 1;
                        fileItem2.mId = i;
                        fileItem2.mFileName = c;
                        fileItem2.mArchivePath = str;
                        if (str2.length() > 0) {
                            fileItem2.mInterfolderPath = str2.replace('/', '/') + '/' + c;
                        } else {
                            fileItem2.mInterfolderPath = str2.replace('/', '/') + c;
                        }
                        fileItem2.mAbsolutePath = str + File.separator + fileItem2.mInterfolderPath;
                        fileItem2.mSubFileNum = 0;
                        arrayList.add(fileItem2);
                        hashMap.put(c, fileItem2);
                        i = i2;
                        fileItem = fileItem2;
                    }
                    if (b(str2, a, z)) {
                        fileItem.mFileSize = eVar.getSize();
                        fileItem.mFileTime = eVar.getTime();
                        if (eVar.isDirectory()) {
                            fileItem.isDirectory = true;
                        }
                    } else {
                        for (FileItem fileItem3 : arrayList) {
                            if (fileItem3.mSubFileNum >= 0) {
                                String str3;
                                if (str2.length() > 0) {
                                    str3 = str2 + '/' + fileItem3.mFileName;
                                } else {
                                    str3 = fileItem3.mFileName;
                                }
                                if (b(str3, a, z)) {
                                    fileItem3.mSubFileNum++;
                                    fileItem3.isDirectory = true;
                                }
                            }
                        }
                        continue;
                    }
                }
            }
            int i3 = 1;
        } catch (IOException e) {
            Log.e("ZipUtils", "Error while reading compress file - " + e);
            obj = null;
        } catch (OutOfMemoryError e2) {
            Log.e("ZipUtils", "OOM while reading compress file - " + e2);
            obj = null;
        } catch (Exception e3) {
            Log.e("ZipUtils", "Error while reading compress file - " + e3);
            obj = null;
        }
        if (obj == null) {
            arrayList.clear();
        }
        return arrayList;
    }

    private static boolean b(String str, String str2, boolean z) {
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != '/') {
            str = str + '/';
            length++;
        }
        if (!str2.startsWith(str) || (!z && str2.substring(length).startsWith("."))) {
            return false;
        }
        length = str2.indexOf(47, length);
        return length < 0 || length == str2.length() - 1;
    }

    private static String c(String str, String str2, boolean z) {
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != '/') {
            str = str + '/';
            length++;
        }
        if (str2.startsWith(str)) {
            int indexOf = str2.indexOf(47, length);
            if (indexOf <= -1) {
                indexOf = str2.length();
            }
            if (length < indexOf) {
                String substring = str2.substring(length, indexOf);
                if (z || !substring.startsWith(".")) {
                    return substring;
                }
            }
        }
        return null;
    }

    private static String a(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return str.substring(lastIndexOf > -1 ? lastIndexOf + 1 : 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r11, java.lang.String r12) {
        /*
        r1 = 0;
        r3 = 47;
        r0 = r12.length();
        r2 = r11.length();
        if (r0 <= r2) goto L_0x017f;
    L_0x000d:
        r0 = r11.length();
        r0 = r0 + 1;
        r0 = r12.substring(r0);
        r0 = r0.replace(r3, r3);
        r2 = r0.replace(r3, r3);
        r4 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r3 = 0;
        r5 = 0;
        r6 = new format.archive.b;	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r7 = new java.io.File;	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r7.<init>(r11);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r8 = "gbk";
        r6.<init>(r7, r8);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r7 = r6.a(r0);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        if (r7 == 0) goto L_0x017c;
    L_0x0036:
        if (r1 != 0) goto L_0x017c;
    L_0x0038:
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r8.<init>();	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r9 = com.qq.reader.common.c.a.u;	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r9 = java.io.File.separator;	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r9 = r11.hashCode();	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r2 = r2.hashCode();	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r2 = r8.append(r2);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r0 = a(r0);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r0 = r2.append(r0);	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0167, OutOfMemoryError -> 0x00d0, Exception -> 0x0101, all -> 0x0133 }
        r2 = r7.isDirectory();	 Catch:{ IOException -> 0x016f, OutOfMemoryError -> 0x0157, Exception -> 0x014a, all -> 0x0133 }
        if (r2 == 0) goto L_0x007e;
    L_0x006b:
        r2 = new java.io.File;	 Catch:{ IOException -> 0x016f, OutOfMemoryError -> 0x0157, Exception -> 0x014a, all -> 0x0133 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x016f, OutOfMemoryError -> 0x0157, Exception -> 0x014a, all -> 0x0133 }
        com.qq.reader.common.utils.ab.b(r2);	 Catch:{ IOException -> 0x016f, OutOfMemoryError -> 0x0157, Exception -> 0x014a, all -> 0x0133 }
    L_0x0073:
        if (r1 == 0) goto L_0x0078;
    L_0x0075:
        r5.close();	 Catch:{ Exception -> 0x00cb }
    L_0x0078:
        if (r1 == 0) goto L_0x007d;
    L_0x007a:
        r3.close();	 Catch:{ Exception -> 0x00cb }
    L_0x007d:
        return r0;
    L_0x007e:
        r3 = r6.a(r7);	 Catch:{ IOException -> 0x016f, OutOfMemoryError -> 0x0157, Exception -> 0x014a, all -> 0x0133 }
        r5 = new byte[r4];	 Catch:{ IOException -> 0x0176, OutOfMemoryError -> 0x015e, Exception -> 0x0150, all -> 0x0145 }
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0176, OutOfMemoryError -> 0x015e, Exception -> 0x0150, all -> 0x0145 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0176, OutOfMemoryError -> 0x015e, Exception -> 0x0150, all -> 0x0145 }
        r1 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
        r1.<init>(r2, r4);	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
    L_0x008e:
        r6 = 0;
        r6 = r3.read(r5, r6, r4);	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
        r7 = -1;
        if (r6 == r7) goto L_0x00b5;
    L_0x0096:
        r7 = 0;
        r1.write(r5, r7, r6);	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
        goto L_0x008e;
    L_0x009b:
        r1 = move-exception;
    L_0x009c:
        r4 = "ZipUtils";
        r5 = "Error while extracting file - ";
        android.util.Log.e(r4, r5, r1);	 Catch:{ all -> 0x0147 }
        if (r2 == 0) goto L_0x00aa;
    L_0x00a7:
        r2.close();	 Catch:{ Exception -> 0x00b0 }
    L_0x00aa:
        if (r3 == 0) goto L_0x007d;
    L_0x00ac:
        r3.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x007d;
    L_0x00b0:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007d;
    L_0x00b5:
        r1.flush();	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
        r1.close();	 Catch:{ IOException -> 0x009b, OutOfMemoryError -> 0x0164, Exception -> 0x0155 }
        if (r2 == 0) goto L_0x00c0;
    L_0x00bd:
        r2.close();	 Catch:{ Exception -> 0x00c6 }
    L_0x00c0:
        if (r3 == 0) goto L_0x007d;
    L_0x00c2:
        r3.close();	 Catch:{ Exception -> 0x00c6 }
        goto L_0x007d;
    L_0x00c6:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007d;
    L_0x00cb:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007d;
    L_0x00d0:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x00d6:
        r4 = "ZipUtils";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0147 }
        r5.<init>();	 Catch:{ all -> 0x0147 }
        r6 = "OOM while extracting compress file - ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0147 }
        r1 = r5.append(r1);	 Catch:{ all -> 0x0147 }
        r1 = r1.toString();	 Catch:{ all -> 0x0147 }
        android.util.Log.e(r4, r1);	 Catch:{ all -> 0x0147 }
        if (r2 == 0) goto L_0x00f5;
    L_0x00f2:
        r2.close();	 Catch:{ Exception -> 0x00fb }
    L_0x00f5:
        if (r3 == 0) goto L_0x007d;
    L_0x00f7:
        r3.close();	 Catch:{ Exception -> 0x00fb }
        goto L_0x007d;
    L_0x00fb:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007d;
    L_0x0101:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0107:
        r4 = "ZipUtils";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0147 }
        r5.<init>();	 Catch:{ all -> 0x0147 }
        r6 = "Error while reading compress file - ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0147 }
        r1 = r5.append(r1);	 Catch:{ all -> 0x0147 }
        r1 = r1.toString();	 Catch:{ all -> 0x0147 }
        android.util.Log.e(r4, r1);	 Catch:{ all -> 0x0147 }
        if (r2 == 0) goto L_0x0126;
    L_0x0123:
        r2.close();	 Catch:{ Exception -> 0x012d }
    L_0x0126:
        if (r3 == 0) goto L_0x007d;
    L_0x0128:
        r3.close();	 Catch:{ Exception -> 0x012d }
        goto L_0x007d;
    L_0x012d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007d;
    L_0x0133:
        r0 = move-exception;
        r3 = r1;
    L_0x0135:
        if (r1 == 0) goto L_0x013a;
    L_0x0137:
        r1.close();	 Catch:{ Exception -> 0x0140 }
    L_0x013a:
        if (r3 == 0) goto L_0x013f;
    L_0x013c:
        r3.close();	 Catch:{ Exception -> 0x0140 }
    L_0x013f:
        throw r0;
    L_0x0140:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x013f;
    L_0x0145:
        r0 = move-exception;
        goto L_0x0135;
    L_0x0147:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0135;
    L_0x014a:
        r2 = move-exception;
        r3 = r1;
        r10 = r1;
        r1 = r2;
        r2 = r10;
        goto L_0x0107;
    L_0x0150:
        r2 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0107;
    L_0x0155:
        r1 = move-exception;
        goto L_0x0107;
    L_0x0157:
        r2 = move-exception;
        r3 = r1;
        r10 = r1;
        r1 = r2;
        r2 = r10;
        goto L_0x00d6;
    L_0x015e:
        r2 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x00d6;
    L_0x0164:
        r1 = move-exception;
        goto L_0x00d6;
    L_0x0167:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x009c;
    L_0x016f:
        r2 = move-exception;
        r3 = r1;
        r10 = r1;
        r1 = r2;
        r2 = r10;
        goto L_0x009c;
    L_0x0176:
        r2 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x009c;
    L_0x017c:
        r0 = r1;
        goto L_0x0073;
    L_0x017f:
        r0 = r1;
        goto L_0x007d;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.archive.d.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String a(e eVar) throws UnsupportedEncodingException {
        return eVar.getName();
    }
}
