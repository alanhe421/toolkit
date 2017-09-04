package format.chm;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: ChmIndex */
public class a {
    String a;
    String b;
    String c;
    String d;
    String e;
    private File f;

    public a(String str) throws FileNotFoundException {
        this(new File(str));
    }

    public a(File file) throws FileNotFoundException {
        this.a = "HOMEPATH";
        this.b = "FILE";
        this.c = "FILE2";
        this.d = "LENGTH";
        if (!file.exists()) {
            throw new FileNotFoundException("file not found : " + file.getAbsolutePath());
        } else if (file.isDirectory()) {
            throw new IllegalArgumentException("the file is a directory : " + file.getAbsolutePath());
        } else {
            this.f = file;
        }
    }

    public void a() {
        DataInputStream dataInputStream;
        IOException e;
        EOFException e2;
        Throwable th;
        DataInputStream dataInputStream2 = null;
        Object obj = null;
        if (this.f.exists()) {
            try {
                dataInputStream = new DataInputStream(new FileInputStream(this.f));
                try {
                    dataInputStream.skipBytes(4);
                    for (short readShort = dataInputStream.readShort(); readShort != (short) 512; readShort = dataInputStream.readShort()) {
                        dataInputStream.skipBytes(dataInputStream.readShort() / 256);
                    }
                    int readShort2 = dataInputStream.readShort() / 256;
                    byte[] bArr = new byte[(readShort2 - 1)];
                    dataInputStream.read(bArr, 0, readShort2 - 1);
                    dataInputStream.close();
                    DataInputStream dataInputStream3 = null;
                    for (byte b : bArr) {
                        if (b != (byte) 0) {
                            obj = 1;
                            break;
                        }
                    }
                    if (obj != null) {
                        this.e = new String(bArr, "GBK");
                    } else {
                        this.e = "";
                    }
                    if (null != null) {
                        try {
                            dataInputStream3.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (EOFException e4) {
                    e2 = e4;
                    try {
                        e2.printStackTrace();
                        if (dataInputStream != null) {
                            try {
                                dataInputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        dataInputStream2 = dataInputStream;
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e32 = e6;
                    dataInputStream2 = dataInputStream;
                    try {
                        e32.printStackTrace();
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        throw th;
                    }
                }
            } catch (EOFException e7) {
                e2 = e7;
                dataInputStream = null;
                e2.printStackTrace();
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e8) {
                e322 = e8;
                e322.printStackTrace();
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
            }
        }
    }
}
