package com.sijla.j.a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class b {
    public static void a(File file) {
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles == null) {
                        return;
                    }
                    if (listFiles.length > 0) {
                        for (File a : listFiles) {
                            a(a);
                        }
                        return;
                    }
                    file.delete();
                    return;
                }
                file.delete();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean b(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && parentFile.mkdirs()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String a(String str) {
        return c(new File(str));
    }

    public static String c(File file) {
        IOException e;
        Throwable th;
        if (file.exists()) {
            String property = System.getProperty("line.separator", "\n");
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            stringBuffer.append(readLine);
                            stringBuffer.append(property);
                        } else {
                            property = stringBuffer.toString();
                            com.sijla.j.b.a(bufferedReader);
                            return property;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
                try {
                    e.printStackTrace();
                    com.sijla.j.b.a(bufferedReader);
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    com.sijla.j.b.a(bufferedReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                com.sijla.j.b.a(bufferedReader);
                throw th;
            }
        }
        return "";
    }

    public static boolean a(String str, String str2, boolean z) {
        BufferedReader bufferedReader;
        Exception e;
        Object obj;
        Throwable th;
        Closeable closeable = null;
        BufferedWriter bufferedWriter;
        try {
            File file = new File(str2);
            b(file);
            bufferedReader = new BufferedReader(new StringReader(str));
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            } catch (Exception e2) {
                e = e2;
                bufferedWriter = null;
                obj = bufferedReader;
                try {
                    e.printStackTrace();
                    com.sijla.j.b.a(bufferedWriter, closeable);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    Closeable closeable2 = closeable;
                    obj = bufferedWriter;
                    com.sijla.j.b.a(closeable, bufferedReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                com.sijla.j.b.a(closeable, bufferedReader);
                throw th;
            }
            try {
                char[] cArr = new char[1024];
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read != -1) {
                        bufferedWriter.write(cArr, 0, read);
                    } else {
                        bufferedWriter.flush();
                        com.sijla.j.b.a(bufferedWriter, bufferedReader);
                        return true;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                closeable = bufferedReader;
                e.printStackTrace();
                com.sijla.j.b.a(bufferedWriter, closeable);
                return false;
            } catch (Throwable th4) {
                th = th4;
                obj = bufferedWriter;
                com.sijla.j.b.a(closeable, bufferedReader);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedWriter = null;
            e.printStackTrace();
            com.sijla.j.b.a(bufferedWriter, closeable);
            return false;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            com.sijla.j.b.a(closeable, bufferedReader);
            throw th;
        }
    }

    public static File a(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        if (com.sijla.j.b.a(str) || bArr == null) {
            return null;
        }
        File file = new File(str);
        b(file);
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                fileOutputStream.write(bArr);
                com.sijla.j.b.a(fileOutputStream);
                return file;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    com.sijla.j.b.a(fileOutputStream);
                    return file;
                } catch (Throwable th2) {
                    th = th2;
                    com.sijla.j.b.a(fileOutputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            fileOutputStream = null;
            e = exception;
            e.printStackTrace();
            com.sijla.j.b.a(fileOutputStream);
            return file;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            com.sijla.j.b.a(fileOutputStream);
            throw th;
        }
    }
}
