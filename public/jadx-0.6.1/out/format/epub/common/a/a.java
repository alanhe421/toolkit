package format.epub.common.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.util.Log;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import format.epub.b;
import format.epub.common.book.BookEPub;
import format.epub.common.image.ZLImageMap;
import format.epub.common.image.e;
import format.epub.common.image.g;
import format.epub.common.text.model.i;
import format.epub.common.text.model.p;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/* compiled from: BookModel */
public final class a {
    public final BookEPub a;
    public i b;
    public final c c = new c();
    private final ZLImageMap d = new ZLImageMap();
    private final HashMap<String, i> e = new HashMap();
    private b f;
    private String g;
    private format.epub.common.text.model.b h;
    private char[] i;
    private int j;

    /* compiled from: BookModel */
    public static final class a {
        public final String a;
        public final int b;

        public a(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public static a a(BookEPub bookEPub, b bVar) {
        format.epub.common.c.a a = format.epub.common.c.b.a().a(bookEPub.File);
        if (a == null) {
            return null;
        }
        a aVar = new a(bookEPub);
        aVar.a(bVar);
        if (a.a(aVar)) {
            return aVar;
        }
        return null;
    }

    public static void a(BookEPub bookEPub) {
        if (ao.a(new StringBuffer(bookEPub.getBookShortName())) != null) {
            File b = c.a(ReaderApplication.getApplicationImp()).b(bookEPub.getImagePath());
            if (b == null || !b.exists()) {
                format.epub.common.c.a a = format.epub.common.c.b.a().a(bookEPub.File);
                if (a != null) {
                    e eVar = (e) a.b(bookEPub.File);
                    if (eVar != null) {
                        g a2 = eVar.a();
                        if (a2 != null) {
                            InputStream inputStream = null;
                            try {
                                inputStream = a2.c();
                                Options options = new Options();
                                options.inJustDecodeBounds = true;
                                options.inPurgeable = true;
                                BitmapFactory.decodeStream(inputStream, new Rect(), options);
                                inputStream.close();
                                int i = options.outWidth;
                                int i2 = options.outHeight;
                                options.inJustDecodeBounds = false;
                                int i3 = 1;
                                while (true) {
                                    if (i2 <= com.qq.reader.common.c.a.cd * i3 && i <= com.qq.reader.common.c.a.cc * i3) {
                                        break;
                                    }
                                    i3++;
                                }
                                options.inSampleSize = i3 - 1;
                                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                inputStream = a2.c();
                                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, new Rect(), options);
                                decodeStream.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                                if (decodeStream != null) {
                                    decodeStream.recycle();
                                }
                                c.a(ReaderApplication.getApplicationImp()).a(bookEPub.getImagePath(), new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                                inputStream.close();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e) {
                                        Log.e("BookModel", "readCover error : " + e.toString());
                                    }
                                }
                            } catch (Exception e2) {
                                Log.e("BookModel", "readCover error : " + e2.toString());
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e22) {
                                        Log.e("BookModel", "readCover error : " + e22.toString());
                                    }
                                }
                            } catch (Throwable th) {
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e3) {
                                        Log.e("BookModel", "readCover error : " + e3.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private a(BookEPub bookEPub) {
        this.a = bookEPub;
        this.g = this.a.getBookPath().hashCode() + ao.l(this.a.getBookPath());
        this.b = new p(null, bookEPub.getLanguage(), 10240, 65536, com.qq.reader.common.c.a.l + "epub/" + this.g, "cache", this.d, bookEPub.getEncrypted_flag());
        this.h = new format.epub.common.text.model.a(32768, com.qq.reader.common.c.a.l + "epub/" + this.g, "links");
    }

    void a(String str, i iVar, int i) {
        String a = iVar.a();
        int length = str.length();
        int length2 = a != null ? a.length() : 0;
        int i2 = (length + 4) + length2;
        char[] cArr = this.i;
        int i3 = this.j;
        if (cArr == null || i3 + i2 > cArr.length) {
            if (cArr != null) {
                this.h.b();
            }
            char[] b = this.h.b(i2);
            this.i = b;
            cArr = b;
            i3 = 0;
        }
        i2 = i3 + 1;
        cArr[i3] = (char) length;
        str.getChars(0, length, cArr, i2);
        length += i2;
        i3 = length + 1;
        cArr[length] = (char) length2;
        if (length2 > 0) {
            a.getChars(0, length2, cArr, i3);
            length2 += i3;
        } else {
            length2 = i3;
        }
        int i4 = length2 + 1;
        cArr[length2] = (char) (i >> 16);
        length2 = i4 + 1;
        cArr[i4] = (char) i;
        this.j = length2;
    }

    public a a(String str) {
        char length = str.length();
        int a = this.h.a();
        for (int i = 0; i < a; i++) {
            char[] a2 = this.h.a(i);
            int i2 = 0;
            while (i2 < a2.length) {
                int i3 = i2 + 1;
                char c = a2[i2];
                if (c == '\u0000') {
                    continue;
                    break;
                }
                char c2 = a2[i3 + c];
                if (c == length && str.equals(new String(a2, i3, c))) {
                    int i4 = i3 + (c + 1);
                    String str2 = c2 > '\u0000' ? new String(a2, i4, c2) : null;
                    i4 += c2;
                    return new a(str2, (a2[i4] << 16) + a2[i4 + 1]);
                }
                i2 = ((c + c2) + 3) + i3;
            }
        }
        return null;
    }

    void a(String str, format.epub.common.image.b bVar) {
        this.d.put(str, bVar);
    }

    private boolean a(String str, String str2, String str3) {
        int i = 0;
        if (str == null || str2 == null || str3 == null) {
            return false;
        }
        File file = new File(str);
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        int i2;
        if (listFiles.length > 5) {
            long currentTimeMillis = System.currentTimeMillis();
            i2 = 0;
            while (i < listFiles.length) {
                long lastModified = listFiles[i].lastModified();
                if (lastModified < currentTimeMillis) {
                    i2 = i;
                    currentTimeMillis = lastModified;
                }
                i++;
            }
            ao.c(listFiles[i2]);
            return true;
        }
        file = new File(str3);
        if (file != null && file.exists()) {
            for (i2 = 0; i2 < listFiles.length; i2++) {
                if (str2.equals(listFiles[i2].getPath())) {
                    listFiles[i2].setLastModified(System.currentTimeMillis());
                    return false;
                }
            }
        }
        return true;
    }

    public static void a() {
        File file = new File(com.qq.reader.common.c.a.aH);
        if (file != null && file.exists()) {
            ao.c(file);
        }
    }

    public static void b(String str) {
        if (str != null) {
            int hashCode = str.hashCode();
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf != -1) {
                String substring = str.substring(lastIndexOf + 1);
                int lastIndexOf2 = substring.lastIndexOf(".");
                if (lastIndexOf2 != -1) {
                    substring = substring.substring(0, lastIndexOf2);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(com.qq.reader.common.c.a.aH);
                    stringBuffer.append(hashCode);
                    stringBuffer.append(substring);
                    File file = new File(stringBuffer.toString());
                    if (file != null && file.exists()) {
                        ao.c(file);
                    }
                }
            }
        }
    }

    public void b() {
        FileOutputStream fileOutputStream;
        DataOutputStream dataOutputStream;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        String str = com.qq.reader.common.c.a.aH;
        String str2 = str + this.g;
        String str3 = str2 + "/newmodel2.t";
        if (a(str, str2, str3)) {
            try {
                File file = new File(str3);
                this.h.b();
                file = ao.c(str3);
                if (file != null) {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                        try {
                            this.b.a(dataOutputStream);
                            dataOutputStream.writeInt(this.h.a());
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream2 = fileOutputStream;
                            try {
                                Log.e("BookModel", "cache error : " + e.toString());
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        return;
                                    }
                                }
                                if (fileOutputStream2 == null) {
                                    fileOutputStream2.close();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    fileOutputStream2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream2 = fileOutputStream;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        dataOutputStream = null;
                        fileOutputStream2 = fileOutputStream;
                        Log.e("BookModel", "cache error : " + e.toString());
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (fileOutputStream2 == null) {
                            fileOutputStream2.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        dataOutputStream = null;
                        fileOutputStream2 = fileOutputStream;
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                }
                dataOutputStream = null;
                fileOutputStream = null;
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                        return;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e6) {
                e = e6;
                dataOutputStream = null;
                Log.e("BookModel", "cache error : " + e.toString());
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th5) {
                th = th5;
                dataOutputStream = null;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
    }

    public boolean c() {
        FileInputStream fileInputStream;
        boolean z;
        DataInputStream dataInputStream;
        Exception exception;
        Throwable th;
        DataInputStream dataInputStream2 = null;
        String str = com.qq.reader.common.c.a.aH + this.g + "/newmodel2.t";
        try {
            File file = new File(str);
            if (file == null || !file.exists()) {
                fileInputStream = null;
                z = false;
            } else {
                InputStream inputStream;
                File c = ao.c(str);
                if (c != null) {
                    fileInputStream = new FileInputStream(c);
                    try {
                        dataInputStream = new DataInputStream(new BufferedInputStream(fileInputStream));
                        try {
                            this.b.a(dataInputStream);
                            this.h.d(dataInputStream.readInt());
                            inputStream = fileInputStream;
                        } catch (Exception e) {
                            Exception exception2 = e;
                            dataInputStream2 = dataInputStream;
                            exception = exception2;
                            try {
                                Log.e("BookModel", "reloadCache error : " + exception.toString());
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                        return false;
                                    }
                                }
                                if (dataInputStream2 != null) {
                                    dataInputStream2.close();
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (dataInputStream2 != null) {
                                    dataInputStream2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            dataInputStream2 = dataInputStream;
                            th = th4;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (dataInputStream2 != null) {
                                dataInputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        exception = e4;
                        Log.e("BookModel", "reloadCache error : " + exception.toString());
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        return false;
                    }
                }
                dataInputStream = null;
                InputStream inputStream2 = inputStream;
                dataInputStream2 = dataInputStream;
                z = true;
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                    return z;
                }
            }
            if (dataInputStream2 == null) {
                return z;
            }
            dataInputStream2.close();
            return z;
        } catch (Exception e5) {
            exception = e5;
            fileInputStream = null;
            Log.e("BookModel", "reloadCache error : " + exception.toString());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            throw th;
        }
    }

    public b d() {
        return this.f;
    }

    public void a(b bVar) {
        this.f = bVar;
    }
}
