package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

@SuppressLint({"NewApi"})
public class i {
    public static String a = null;
    public static final a b = new j();
    private static final int c = "lib/".length();

    public interface a {
        boolean a(File file, File file2);
    }

    public static long a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null) {
            return -1;
        }
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static ByteArrayOutputStream a(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    public static File a() {
        try {
            return Environment.getExternalStorageDirectory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File a(Context context, boolean z, String str) {
        String b = z ? b(context) : a(context);
        if (b == null) {
            return null;
        }
        File file = new File(b);
        if (!(file == null || file.exists())) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static String a(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    public static String a(Context context, int i) {
        if (context == null) {
            return "";
        }
        String str;
        String str2 = "";
        try {
            str = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
            str = str2;
        }
        switch (i) {
            case 1:
                return !str.equals("") ? str + "tencent" + File.separator + "tbs" + File.separator + context.getApplicationInfo().packageName : str;
            case 2:
                return !str.equals("") ? str + "tbs" + File.separator + "backup" + File.separator + context.getApplicationInfo().packageName : str;
            case 3:
                return !str.equals("") ? str + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + context.getApplicationInfo().packageName : str;
            case 4:
                if (str.equals("")) {
                    return a(context, "backup");
                }
                str = str + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + context.getApplicationInfo().packageName;
                File file = new File(str);
                if (file.exists() && file.canWrite()) {
                    return str;
                }
                if (file.exists()) {
                    return a(context, "backup");
                }
                file.mkdirs();
                return !file.canWrite() ? a(context, "backup") : str;
            case 5:
                return !str.equals("") ? str + "tencent" + File.separator + "tbs" + File.separator + context.getApplicationInfo().packageName : str;
            case 6:
                if (a != null) {
                    return a;
                }
                a = a(context, "tbslog");
                return a;
            default:
                return "";
        }
    }

    private static String a(Context context, String str) {
        String str2 = "";
        if (context == null) {
            return str2;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            return applicationContext.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + applicationContext.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return null;
        }
        try {
            FileLock tryLock = fileOutputStream.getChannel().tryLock();
            if (tryLock.isValid()) {
                return tryLock;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream.close();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(File file) {
        a(file, false);
    }

    public static void a(File file, boolean z) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(a, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                fileLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) {
        return a(file, file2, fileFilter, b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    public static int b(InputStream inputStream, OutputStream outputStream) {
        long a = a(inputStream, outputStream);
        return a > 2147483647L ? -1 : (int) a;
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a = a(context, z, str);
        if (a != null) {
            try {
                return new FileOutputStream(a);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static FileOutputStream b(File file) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.exists() || parentFile.mkdirs())) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    static String b(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file.getAbsolutePath() : null : null;
    }

    public static boolean b() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean b(File file, File file2, FileFilter fileFilter, a aVar) {
        Throwable th;
        FileChannel fileChannel = null;
        if (file == null || file2 == null) {
            return false;
        }
        if (fileFilter != null && !fileFilter.accept(file)) {
            return false;
        }
        FileChannel channel;
        try {
            if (file.exists() && file.isFile()) {
                if (file2.exists()) {
                    if (aVar == null || !aVar.a(file, file2)) {
                        a(file2);
                    } else {
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        return true;
                    }
                }
                File parentFile = file2.getParentFile();
                if (parentFile.isFile()) {
                    a(parentFile);
                }
                if (parentFile.exists() || parentFile.mkdirs()) {
                    channel = new FileInputStream(file).getChannel();
                    try {
                        FileChannel channel2 = new FileOutputStream(file2).getChannel();
                        try {
                            long size = channel.size();
                            if (channel2.transferFrom(channel, 0, size) != size) {
                                a(file2);
                                if (channel != null) {
                                    channel.close();
                                }
                                if (channel2 != null) {
                                    channel2.close();
                                }
                                return false;
                            }
                            if (channel != null) {
                                channel.close();
                            }
                            if (channel2 != null) {
                                channel2.close();
                            }
                            return true;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            fileChannel = channel;
                            channel = channel2;
                            th = th3;
                            if (fileChannel != null) {
                                fileChannel.close();
                            }
                            if (channel != null) {
                                channel.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        FileChannel fileChannel2 = fileChannel;
                        fileChannel = channel;
                        channel = fileChannel2;
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        throw th;
                    }
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                return false;
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            channel = fileChannel;
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (channel != null) {
                channel.close();
            }
            throw th;
        }
    }

    public static byte[] b(InputStream inputStream) {
        return a(inputStream).toByteArray();
    }

    public static File c() {
        File file = new File(a().getAbsolutePath() + "/" + ".tbs");
        if (!(file == null || file.exists())) {
            file.mkdirs();
        }
        return file;
    }
}
