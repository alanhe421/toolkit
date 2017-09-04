package format.epub.common.text.model;

import com.qq.reader.common.drm.teb.TeaTool;
import com.qq.reader.common.utils.ab;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: CachedCharStorage */
public final class a implements b {
    protected final ArrayList<WeakReference<f>> a = new ArrayList();
    private final int b;
    private final int c;
    private final ArrayList<WeakReference<char[]>> d = new ArrayList();
    private final String e;
    private final String f;
    private int g = 2;

    private String f(int i) {
        return this.e + i + this.f;
    }

    private String g(int i) {
        return this.e + "p" + i + ".t";
    }

    public a(int i, String str, String str2) {
        this.b = i;
        this.e = str.trim() + '/';
        this.f = '.' + str2;
        this.c = 0;
        ab.b(new File(str.trim()));
    }

    public a(int i, int i2, String str, String str2, int i3) {
        this.b = i;
        this.c = i2;
        this.e = str.trim() + '/';
        this.f = '.' + str2;
        this.g = i3;
        ab.b(new File(str.trim()));
    }

    public int a() {
        return this.d.size();
    }

    public char[] a(int i) {
        InputStreamReader inputStreamReader;
        Throwable th;
        char[] cArr = (char[]) ((WeakReference) this.d.get(i)).get();
        if (cArr == null) {
            InputStreamReader inputStreamReader2 = null;
            try {
                File file = new File(f(i));
                int length = (int) file.length();
                if (length < 0) {
                    throw new CachedCharStorageException("Error during reading " + f(i));
                }
                b(f(i));
                cArr = new char[(length / 2)];
                InputStreamReader inputStreamReader3 = new InputStreamReader(new FileInputStream(file), "UTF-16LE");
                try {
                    if (inputStreamReader3.read(cArr) != cArr.length) {
                        throw new CachedCharStorageException("Error during reading " + f(i));
                    }
                    a(f(i));
                    try {
                        inputStreamReader3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.d.set(i, new WeakReference(cArr));
                } catch (FileNotFoundException e2) {
                    inputStreamReader = inputStreamReader3;
                    try {
                        throw new CachedCharStorageException("Error during reading " + f(i));
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        inputStreamReader2 = inputStreamReader;
                        th = th3;
                        try {
                            inputStreamReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    inputStreamReader2 = inputStreamReader3;
                    try {
                        throw new CachedCharStorageException("Error during reading " + f(i));
                    } catch (Throwable th4) {
                        th = th4;
                        inputStreamReader2.close();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    inputStreamReader2 = inputStreamReader3;
                    inputStreamReader2.close();
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                inputStreamReader = null;
                throw new CachedCharStorageException("Error during reading " + f(i));
            } catch (IOException e6) {
                throw new CachedCharStorageException("Error during reading " + f(i));
            }
        }
        return cArr;
    }

    public char[] b(int i) {
        int i2 = this.b;
        if (i <= i2) {
            i = i2;
        }
        Object obj = new char[i];
        this.d.add(new WeakReference(obj));
        return obj;
    }

    public void b() {
        OutputStreamWriter outputStreamWriter;
        Throwable th;
        int size = this.d.size() - 1;
        if (size >= 0) {
            OutputStreamWriter outputStreamWriter2 = null;
            char[] cArr = (char[]) ((WeakReference) this.d.get(size)).get();
            if (cArr == null) {
                throw new CachedCharStorageException("Block reference in null during freeze");
            }
            try {
                OutputStreamWriter outputStreamWriter3 = new OutputStreamWriter(new FileOutputStream(f(size)), "UTF-16LE");
                try {
                    outputStreamWriter3.write(cArr);
                    a(f(size));
                    try {
                        outputStreamWriter3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    outputStreamWriter = outputStreamWriter3;
                    try {
                        throw new CachedCharStorageException("Error during writing " + f(size));
                    } catch (Throwable th2) {
                        outputStreamWriter2 = outputStreamWriter;
                        th = th2;
                        try {
                            outputStreamWriter2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStreamWriter2 = outputStreamWriter3;
                    outputStreamWriter2.close();
                    throw th;
                }
            } catch (IOException e4) {
                outputStreamWriter = null;
                throw new CachedCharStorageException("Error during writing " + f(size));
            } catch (Throwable th4) {
                th = th4;
                outputStreamWriter2.close();
                throw th;
            }
        }
    }

    private void a(String str) {
        Exception e;
        Throwable th;
        if (this.g == 0) {
            Object obj = new byte[128];
            Object obj2 = new byte[8];
            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile(str, "rw");
                try {
                    randomAccessFile.seek(0);
                    randomAccessFile.read(obj);
                    int[] a = TeaTool.a();
                    for (int i = 0; i < 16; i++) {
                        System.arraycopy(obj, i * 8, obj2, 0, 8);
                        System.arraycopy(TeaTool.a(obj2, a), 0, obj, i * 8, 8);
                    }
                    randomAccessFile.seek(0);
                    randomAccessFile.write(obj);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }
    }

    private void b(String str) {
        RandomAccessFile randomAccessFile;
        Exception e;
        Throwable th;
        if (this.g == 0) {
            Object obj = new byte[128];
            Object obj2 = new byte[8];
            try {
                randomAccessFile = new RandomAccessFile(str, "rw");
                try {
                    randomAccessFile.seek(0);
                    randomAccessFile.read(obj);
                    int[] a = TeaTool.a();
                    for (int i = 0; i < 16; i++) {
                        System.arraycopy(obj, i * 8, obj2, 0, 8);
                        System.arraycopy(TeaTool.b(obj2, a), 0, obj, i * 8, 8);
                    }
                    randomAccessFile.seek(0);
                    randomAccessFile.write(obj);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }
    }

    public e c(int i) {
        DataInputStream dataInputStream;
        Throwable th;
        Throwable th2;
        int i2 = i / this.c;
        f fVar = (f) ((WeakReference) this.a.get(i2)).get();
        if (fVar == null) {
            DataInputStream dataInputStream2 = null;
            try {
                File file = new File(g(i2));
                if (((int) file.length()) < 0) {
                    throw new CachedCharStorageException("Error during reading " + f(i2));
                }
                b(g(i2));
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                try {
                    fVar = f.a(dataInputStream);
                    dataInputStream.close();
                    a(g(i2));
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e) {
                            throw new CachedCharStorageException("Error during reading " + f(i2));
                        }
                    }
                    this.a.set(i2, new WeakReference(fVar));
                } catch (FileNotFoundException e2) {
                    dataInputStream2 = dataInputStream;
                    try {
                        throw new CachedCharStorageException("Error during reading " + f(i2));
                    } catch (Throwable th3) {
                        th = th3;
                        dataInputStream = dataInputStream2;
                        th2 = th;
                        if (dataInputStream != null) {
                            try {
                                dataInputStream.close();
                            } catch (IOException e3) {
                                throw new CachedCharStorageException("Error during reading " + f(i2));
                            }
                        }
                        throw th2;
                    }
                } catch (IOException e4) {
                    dataInputStream2 = dataInputStream;
                    throw new CachedCharStorageException("Error during reading " + f(i2));
                } catch (Throwable th4) {
                    th2 = th4;
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    throw th2;
                }
            } catch (FileNotFoundException e5) {
                throw new CachedCharStorageException("Error during reading " + f(i2));
            } catch (IOException e6) {
                throw new CachedCharStorageException("Error during reading " + f(i2));
            } catch (Throwable th32) {
                th = th32;
                dataInputStream = null;
                th2 = th;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                throw th2;
            }
        }
        return fVar.a(i % this.c);
    }

    public f c() {
        f fVar = new f(this.c);
        this.a.add(new WeakReference(fVar));
        return fVar;
    }

    public void d() {
        int size = this.a.size() - 1;
        if (size >= 0) {
            f fVar = (f) ((WeakReference) this.a.get(size)).get();
            if (fVar == null) {
                throw new CachedCharStorageException("Block reference in null during freeze");
            }
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(g(size)));
                fVar.a(dataOutputStream);
                dataOutputStream.close();
                a(g(size));
            } catch (IOException e) {
                throw new CachedCharStorageException("Error during writing " + f(size));
            }
        }
    }

    public void e() {
        this.d.clear();
        this.a.clear();
    }

    public void d(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.d.add(new WeakReference(null));
        }
    }

    public int f() {
        return this.a.size();
    }

    public void e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(new WeakReference(null));
        }
    }
}
