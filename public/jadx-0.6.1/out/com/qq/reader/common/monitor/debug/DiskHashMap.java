package com.qq.reader.common.monitor.debug;

import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ab;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class DiskHashMap extends HashMap<String, Object> {
    public static final String STATDISKCACHE_PATH = (a.l + "statDiskCache/");
    private static final long serialVersionUID = 1001000;
    private String diskpath = "";

    public abstract String decode(Object obj);

    public abstract Object encode(String str);

    public DiskHashMap(String str) {
        this.diskpath = STATDISKCACHE_PATH + str + "/";
        initMap();
    }

    public DiskHashMap(int i, float f) {
        super(i, f);
        initMap();
    }

    public DiskHashMap(int i) {
        super(i);
        initMap();
    }

    public DiskHashMap(Map<? extends String, ? extends Object> map) {
        super(map);
        initMap();
    }

    public Object put(String str, Object obj) {
        saveData(str, decode(obj));
        return super.put(str, obj);
    }

    public Object get(Object obj) {
        return super.get(obj);
    }

    public Object remove(Object obj) {
        removeData((String) obj);
        return super.remove(obj);
    }

    public boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    public void initMap() {
        IOException e;
        OptionalDataException e2;
        Throwable th;
        InputStream inputStream = null;
        File file = new File(this.diskpath);
        if (file != null && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                InputStream inputStream2;
                File file2 = listFiles[i];
                if (file2 == null || !file2.isFile()) {
                    inputStream2 = inputStream;
                } else {
                    try {
                        String str = "UTF-8";
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        inputStream2 = new BufferedInputStream(new FileInputStream(file2));
                        try {
                            byte[] bArr = new byte[512];
                            while (true) {
                                int read = inputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            super.put(file2.getName(), encode(new String(byteArrayOutputStream.toByteArray(), str)));
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (OptionalDataException e4) {
                            e2 = e4;
                            f.d("localhashmap", "  error " + e2);
                            e2.printStackTrace();
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            i++;
                            inputStream = inputStream2;
                        } catch (IOException e5) {
                            e32 = e5;
                            try {
                                f.d("localhashmap", "  error " + e32);
                                e32.printStackTrace();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e322) {
                                        e322.printStackTrace();
                                    }
                                }
                                i++;
                                inputStream = inputStream2;
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                inputStream = inputStream2;
                                th = th3;
                            }
                        }
                    } catch (OptionalDataException e6) {
                        OptionalDataException optionalDataException = e6;
                        inputStream2 = inputStream;
                        e2 = optionalDataException;
                        f.d("localhashmap", "  error " + e2);
                        e2.printStackTrace();
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        i++;
                        inputStream = inputStream2;
                    } catch (IOException e7) {
                        IOException iOException = e7;
                        inputStream2 = inputStream;
                        e322 = iOException;
                        f.d("localhashmap", "  error " + e322);
                        e322.printStackTrace();
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        i++;
                        inputStream = inputStream2;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
                i++;
                inputStream = inputStream2;
            }
            return;
        }
        return;
        throw th;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3222) {
                e3222.printStackTrace();
            }
        }
        throw th;
    }

    public synchronized void saveData(String str, String str2) {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        if (str2 != null) {
            OutputStream outputStream = null;
            try {
                OutputStream bufferedOutputStream;
                String str3 = this.diskpath + str;
                File file = new File(str3 + com.qq.reader.common.download.task.f.DOWNLOAD_FILE_TMP);
                if (file.exists()) {
                    file.delete();
                }
                if (createFile(file)) {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, false));
                    try {
                        bufferedOutputStream.write(str2.getBytes("UTF-8"));
                        File file2 = new File(str3);
                        if (file2.exists()) {
                            file2.delete();
                        }
                        file.renameTo(file2);
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        outputStream = bufferedOutputStream;
                        try {
                            f.d("localhashmap", "  error " + e);
                            e.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e22 = e5;
                        outputStream = bufferedOutputStream;
                        f.d("localhashmap", "  error " + e22);
                        e22.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = bufferedOutputStream;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw th;
                    }
                }
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                f.d("localhashmap", "  error " + e);
                e.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e7) {
                e2222 = e7;
                f.d("localhashmap", "  error " + e2222);
                e2222.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

    public synchronized void removeData(String str) {
        String str2 = this.diskpath;
        File file = new File(str2);
        if (file != null && file.isDirectory()) {
            file = new File(str2 + str);
            if (file != null) {
                file.delete();
            }
        }
        f.d("localhashmap", "remove error " + str + "  not found");
    }

    private boolean createFile(File file) {
        if (!ab.a(file.getParentFile()) || file.exists()) {
            return false;
        }
        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
