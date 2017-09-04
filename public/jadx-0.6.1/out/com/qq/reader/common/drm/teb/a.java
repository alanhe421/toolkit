package com.qq.reader.common.drm.teb;

import com.qq.reader.common.utils.ao;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: TebReader */
public class a {
    static byte[] a = new byte[128];

    /* compiled from: TebReader */
    public static class a {
        int a;
        int b;
        String c;
        int d;

        public int a() {
            return this.a;
        }

        public void a(int i) {
            this.a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public String c() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public void c(int i) {
            this.d = i;
        }
    }

    public static a a(String str) {
        FileNotFoundException fileNotFoundException;
        FileNotFoundException fileNotFoundException2;
        IOException e;
        Throwable th;
        IOException iOException;
        a aVar = null;
        int i = 0;
        File file = new File(str);
        if (file != null && file.exists()) {
            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile.seek(file.length() - 40);
                    byte[] bArr = new byte[16];
                    randomAccessFile.read(bArr);
                    String trim = new String(bArr, "utf-8").trim();
                    int read = randomAccessFile.read();
                    byte[] bArr2 = new byte[4];
                    randomAccessFile.read(bArr2);
                    String str2 = new String(bArr2, "utf-8");
                    int read2 = randomAccessFile.read();
                    if ("epu0".equals(str2.toLowerCase())) {
                        i = 101;
                    } else if ("pda0".equals(str2.toLowerCase())) {
                        i = 102;
                    }
                    a aVar2;
                    if (i != 0) {
                        aVar2 = new a();
                        try {
                            aVar2.b(read2);
                            aVar2.a(i);
                            aVar2.a(trim);
                            aVar2.c(read);
                            aVar = aVar2;
                        } catch (FileNotFoundException e2) {
                            fileNotFoundException = e2;
                            aVar = aVar2;
                            fileNotFoundException2 = fileNotFoundException;
                            try {
                                fileNotFoundException2.printStackTrace();
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                return aVar;
                            } catch (Throwable th2) {
                                th = th2;
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e32) {
                                        e32.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException e4) {
                            iOException = e4;
                            aVar = aVar2;
                            e32 = iOException;
                            e32.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e322) {
                                    e322.printStackTrace();
                                }
                            }
                            return aVar;
                        }
                    }
                    aVar2 = new a();
                    try {
                        aVar2.b(0);
                        aVar2.a(101);
                        aVar2.a("");
                        aVar = aVar2;
                    } catch (FileNotFoundException e22) {
                        fileNotFoundException = e22;
                        aVar = aVar2;
                        fileNotFoundException2 = fileNotFoundException;
                        fileNotFoundException2.printStackTrace();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        return aVar;
                    } catch (IOException e42) {
                        iOException = e42;
                        aVar = aVar2;
                        e322 = iOException;
                        e322.printStackTrace();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        return aVar;
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e5) {
                    fileNotFoundException2 = e5;
                    fileNotFoundException2.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return aVar;
                } catch (IOException e6) {
                    e3222 = e6;
                    e3222.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return aVar;
                }
            } catch (FileNotFoundException e7) {
                fileNotFoundException2 = e7;
                randomAccessFile = null;
                fileNotFoundException2.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return aVar;
            } catch (IOException e8) {
                e3222 = e8;
                randomAccessFile = null;
                e3222.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return aVar;
            } catch (Throwable th3) {
                randomAccessFile = null;
                th = th3;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }
        return aVar;
    }

    public static void b(String str) {
        FileInputStream fileInputStream;
        IOException e;
        FileInputStream fileInputStream2;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Object obj = new byte[8];
        try {
            File c = ao.c(str);
            if (c == null || !c.exists()) {
                fileInputStream = null;
            } else {
                RandomAccessFile randomAccessFile2;
                fileInputStream = new FileInputStream(c);
                try {
                    fileInputStream.read(a);
                    fileInputStream.close();
                    int[] a = TeaTool.a();
                    while (i <= a.length - 8) {
                        System.arraycopy(a, i, obj, 0, 8);
                        obj = TeaTool.b(obj, a);
                        byteArrayOutputStream.write(obj);
                        i += obj.length;
                    }
                    randomAccessFile2 = new RandomAccessFile(c, "rw");
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream2 = fileInputStream;
                    try {
                        e.printStackTrace();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                        if (randomAccessFile == null) {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                throw th;
                            }
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
                try {
                    randomAccessFile2.seek(0);
                    randomAccessFile2.write(byteArrayOutputStream.toByteArray());
                    randomAccessFile = randomAccessFile2;
                } catch (IOException e5) {
                    e = e5;
                    randomAccessFile = randomAccessFile2;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (randomAccessFile == null) {
                        randomAccessFile.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    randomAccessFile = randomAccessFile2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e32) {
                    e32.printStackTrace();
                    return;
                }
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (IOException e6) {
            e = e6;
            fileInputStream2 = null;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (randomAccessFile == null) {
                randomAccessFile.close();
            }
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static void c(String str) {
        IOException e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        RandomAccessFile randomAccessFile;
        try {
            File c = ao.c(str);
            if (c == null || !c.exists()) {
                randomAccessFile = null;
            } else {
                randomAccessFile = new RandomAccessFile(c, "rw");
                try {
                    randomAccessFile.seek(0);
                    randomAccessFile.write(a);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (null != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (null != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                throw th;
                            }
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                }
            }
            if (null != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e32) {
                    e32.printStackTrace();
                    return;
                }
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (IOException e5) {
            e = e5;
            randomAccessFile = null;
            e.printStackTrace();
            if (null != null) {
                fileOutputStream.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (null != null) {
                fileOutputStream.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }
}
