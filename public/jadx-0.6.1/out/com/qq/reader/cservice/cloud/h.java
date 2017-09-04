package com.qq.reader.cservice.cloud;

import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/* compiled from: CloudVersionFileUtil */
public class h {
    public static void a(long j) {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(ao.c(a.bI), "rw");
            try {
                randomAccessFile.writeLong(j);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e2 = e8;
                randomAccessFile2 = randomAccessFile;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e9) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } catch (Exception e10) {
                e3 = e10;
                randomAccessFile2 = randomAccessFile;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e11) {
                    }
                }
            }
        } catch (UnsupportedEncodingException e12) {
            e = e12;
            randomAccessFile = null;
            e.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (FileNotFoundException e13) {
            e2 = e13;
            e2.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        } catch (Exception e14) {
            e3 = e14;
            e3.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        }
    }

    public static long a() {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        long j = 0;
        File b = ao.b(a.bI);
        if (b != null) {
            RandomAccessFile randomAccessFile2 = null;
            try {
                randomAccessFile = new RandomAccessFile(b, "rw");
                try {
                    j = randomAccessFile.readLong();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (UnsupportedEncodingException e5) {
                    e = e5;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e6) {
                            }
                        }
                        return j;
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e8) {
                    e2 = e8;
                    randomAccessFile2 = randomAccessFile;
                    try {
                        e2.printStackTrace();
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e9) {
                            }
                        }
                        return j;
                    } catch (Throwable th3) {
                        th = th3;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                } catch (Exception e10) {
                    e3 = e10;
                    randomAccessFile2 = randomAccessFile;
                    e3.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e11) {
                        }
                    }
                    return j;
                }
            } catch (UnsupportedEncodingException e12) {
                e = e12;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return j;
            } catch (FileNotFoundException e13) {
                e2 = e13;
                e2.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return j;
            } catch (Exception e14) {
                e3 = e14;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return j;
            }
        }
        return j;
    }

    public static void b(long j) {
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2;
        try {
            randomAccessFile2 = new RandomAccessFile(ao.c(a.bJ), "rw");
            try {
                randomAccessFile2.writeLong(j);
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e2 = e8;
                randomAccessFile = randomAccessFile2;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e9) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (Exception e10) {
                e3 = e10;
                randomAccessFile = randomAccessFile2;
                e3.printStackTrace();
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e11) {
                    }
                }
            }
        } catch (UnsupportedEncodingException e12) {
            e = e12;
            randomAccessFile2 = null;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        } catch (FileNotFoundException e13) {
            e2 = e13;
            e2.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Exception e14) {
            e3 = e14;
            e3.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
    }

    public static long b() {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        long j = 0;
        File b = ao.b(a.bJ);
        if (b != null) {
            RandomAccessFile randomAccessFile2 = null;
            try {
                randomAccessFile = new RandomAccessFile(b, "rw");
                try {
                    j = randomAccessFile.readLong();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (UnsupportedEncodingException e5) {
                    e = e5;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e6) {
                            }
                        }
                        return j;
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e8) {
                    e2 = e8;
                    randomAccessFile2 = randomAccessFile;
                    try {
                        e2.printStackTrace();
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e9) {
                            }
                        }
                        return j;
                    } catch (Throwable th3) {
                        th = th3;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                } catch (Exception e10) {
                    e3 = e10;
                    randomAccessFile2 = randomAccessFile;
                    e3.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e11) {
                        }
                    }
                    return j;
                }
            } catch (UnsupportedEncodingException e12) {
                e = e12;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return j;
            } catch (FileNotFoundException e13) {
                e2 = e13;
                e2.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return j;
            } catch (Exception e14) {
                e3 = e14;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return j;
            }
        }
        return j;
    }
}
