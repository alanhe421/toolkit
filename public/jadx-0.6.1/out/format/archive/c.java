package format.archive;

import android.util.Log;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ab;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: RarUtil */
public final class c {
    static {
        File file = new File(a.u);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static List<FileItem> a(String str, String str2, boolean z) {
        String replace = str2.replace('/', '\\');
        List<FileItem> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        try {
            List c = new de.innosystec.unrar.a(new File(str)).c();
            int size = c.size();
            int i = 0;
            int i2 = 0;
            while (i2 < size) {
                int i3;
                g gVar = (g) c.get(i2);
                String a = a(gVar);
                String c2 = c(replace, a, z);
                if (c2 == null || com.qq.reader.readengine.model.a.c(c2)) {
                    i3 = i;
                } else {
                    int i4;
                    FileItem fileItem;
                    FileItem fileItem2 = (FileItem) hashMap.get(c2);
                    if (fileItem2 == null) {
                        FileItem fileItem3 = new FileItem();
                        fileItem3.mCompressType = "rar";
                        i4 = i + 1;
                        fileItem3.mId = i;
                        fileItem3.mFileName = c2;
                        fileItem3.mArchivePath = str;
                        if (replace.length() > 0) {
                            fileItem3.mInterfolderPath = replace.replace('\\', '/') + '/' + c2;
                        } else {
                            fileItem3.mInterfolderPath = replace.replace('\\', '/') + c2;
                        }
                        fileItem3.mAbsolutePath = str + File.separator + fileItem3.mInterfolderPath;
                        fileItem3.mSubFileNum = 0;
                        arrayList.add(fileItem3);
                        hashMap.put(c2, fileItem3);
                        fileItem = fileItem3;
                    } else {
                        int i5 = i;
                        fileItem = fileItem2;
                        i4 = i5;
                    }
                    if (b(replace, a, z)) {
                        fileItem.mFileSize = gVar.u();
                        fileItem.mFileTime = gVar.p().getTime();
                        if (gVar.D()) {
                            fileItem.isDirectory = true;
                            i3 = i4;
                        }
                    } else {
                        for (FileItem fileItem4 : arrayList) {
                            if (fileItem4.mSubFileNum >= 0) {
                                String str3;
                                if (replace.length() > 0) {
                                    str3 = replace + '\\' + fileItem4.mFileName;
                                } else {
                                    str3 = fileItem4.mFileName;
                                }
                                if (b(str3, a, z)) {
                                    fileItem4.mSubFileNum++;
                                    fileItem4.isDirectory = true;
                                }
                            }
                        }
                    }
                    i3 = i4;
                }
                i2++;
                i = i3;
            }
        } catch (RarException e) {
            Log.e("RarUtils", "Error while decompress rar file - " + e);
        } catch (IOException e2) {
            Log.e("RarUtils", "Error while reading compress file - " + e2);
        } catch (OutOfMemoryError e3) {
            Log.e("RarUtils", "OOM while reading compress file - " + e3);
        } catch (Exception e4) {
            Log.e("RarUtils", "Error while reading compress file - " + e4);
        }
        return arrayList;
    }

    private static boolean b(String str, String str2, boolean z) {
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != '\\') {
            str = str + '\\';
            length++;
        }
        if (!str2.startsWith(str) || (!z && str2.substring(length).startsWith("."))) {
            return false;
        }
        length = str2.indexOf(92, length);
        return length < 0 || length == str2.length() - 1;
    }

    private static String c(String str, String str2, boolean z) {
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != '\\') {
            str = str + '\\';
            length++;
        }
        if (str2.startsWith(str)) {
            int indexOf = str2.indexOf(92, length);
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

    private static String d(String str, String str2, boolean z) {
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != '\\') {
            str = str + '\\';
            length++;
        }
        if (str2.startsWith(str)) {
            String substring = str2.substring(length);
            if (z || !substring.startsWith(".")) {
                return substring;
            }
        }
        return null;
    }

    private static String a(String str) {
        int lastIndexOf = str.lastIndexOf(92);
        if (lastIndexOf > -1) {
            return str.substring(0, lastIndexOf);
        }
        return null;
    }

    private static String b(String str) {
        int lastIndexOf = str.lastIndexOf(92);
        return str.substring(lastIndexOf > -1 ? lastIndexOf + 1 : 0);
    }

    public static String a(String str, String str2) {
        String str3 = null;
        if (str2.length() > str.length()) {
            String replace = str2.substring(str.length() + 1).replace('/', '\\');
            String replace2 = replace.replace('\\', '/');
            OutputStream fileOutputStream;
            try {
                de.innosystec.unrar.a aVar = new de.innosystec.unrar.a(new File(str));
                List c = aVar.c();
                int size = c.size();
                int i = 0;
                while (i < size) {
                    String str4;
                    g gVar = (g) c.get(i);
                    String a = a(gVar);
                    if (replace.equals(a)) {
                        if (str3 == null) {
                            str3 = a.u + File.separator + str.hashCode() + replace2.hashCode() + b(replace);
                            if (gVar.D()) {
                                ab.b(new File(str3));
                                str4 = str3;
                            } else {
                                fileOutputStream = new FileOutputStream(str3);
                                aVar.a(gVar, fileOutputStream);
                                fileOutputStream.close();
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            }
                        }
                        str4 = str3;
                    } else {
                        String d = d(replace, a, true);
                        if (d != null) {
                            if (str3 == null) {
                                str3 = a.u + File.separator + b(replace);
                                ab.b(new File(str3));
                            }
                            if (gVar.D()) {
                                ab.b(new File(str3 + File.separator + d.replace('\\', '/')));
                                str4 = str3;
                            } else {
                                String a2 = a(d);
                                if (a2 != null) {
                                    ab.b(new File(str3 + File.separator + a2.replace('\\', '/')));
                                }
                                OutputStream fileOutputStream2 = new FileOutputStream(str3 + File.separator + d.replace('\\', '/'));
                                try {
                                    aVar.a(gVar, fileOutputStream2);
                                    fileOutputStream2.close();
                                    if (fileOutputStream2 != null) {
                                        fileOutputStream2.close();
                                        str4 = str3;
                                    }
                                } catch (RarException e) {
                                    Log.e("RarUtils", "Error while decompress rar file " + a + " - " + e);
                                    if (fileOutputStream2 != null) {
                                        fileOutputStream2.close();
                                        str4 = str3;
                                    }
                                } catch (Throwable th) {
                                    if (fileOutputStream2 != null) {
                                        fileOutputStream2.close();
                                    }
                                }
                            }
                        }
                        str4 = str3;
                    }
                    i++;
                    str3 = str4;
                }
            } catch (RarException e2) {
                Log.e("RarUtils", "Error while decompress rar file - " + e2);
            } catch (Throwable e3) {
                Log.e("RarUtils", "Error while extracting file - ", e3);
            } catch (OutOfMemoryError e4) {
                Log.e("RarUtils", "OOM while extracting compress file - " + e4);
            } catch (Exception e5) {
                Log.e("RarUtils", "Error while reading compress file - " + e5);
            } catch (Throwable th2) {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
        return str3;
    }

    private static String a(g gVar) {
        String o = gVar.o();
        if (o.length() == 0) {
            return gVar.n();
        }
        return o;
    }
}
