package com.qq.reader.common.utils;

import com.qq.reader.common.c.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomArrayList<E> extends ArrayList<E> {
    public static final String Class_ChargeActivity = "ChargeActivity";
    public static final String Class_FeedGoogleCardsActivity = "FeedGoogleCardsActivity";
    public static final String Class_NativePageAudioQuestionDetail = "NativePageAudioQuestionDetail";
    public static final String Class_NativePageAudioQuiz = "NativePageAudioQuiz";
    public static final String Class_NativePageFragmentOfClub = "NativePageFragmentOfClub";
    public static final String Class_NativePageFragmentforClassify = "NativePageFragmentforClassify";
    public static final String Class_NativePageFragmentforClassify_1 = "NativePageFragmentforClassify_1";
    public static final String Class_NativePageFragmentforOther = "NativePageFragmentforOther";
    public static final String Class_SearchActivity = "SearchActivity";
    private static final String ENTER = "\n";
    private static int LOG_MAX_SIZE = 512000;
    String mTag = "null";

    public CustomArrayList(String str) {
        this.mTag = str;
    }

    public E get(int i) {
        if (Class_SearchActivity.equals(this.mTag)) {
            return getbySearchActivity(i);
        }
        if (Class_FeedGoogleCardsActivity.equals(this.mTag)) {
            return getbyFeedGoogleCardsActivity(i);
        }
        if (Class_NativePageFragmentforClassify_1.equals(this.mTag)) {
            return getbyNativePageFragmentforClassify_1(i);
        }
        if (Class_NativePageFragmentforClassify.equals(this.mTag)) {
            return getbyNativePageFragmentforClassify(i);
        }
        if (Class_NativePageFragmentforOther.equals(this.mTag)) {
            return getbyNativePageFragmentforOther(i);
        }
        if (Class_NativePageFragmentOfClub.equals(this.mTag)) {
            return getbyNativePageFragmentOfClub(i);
        }
        return super.get(i);
    }

    public E getbyFeedGoogleCardsActivity(int i) {
        return super.get(i);
    }

    public E getbySearchActivity(int i) {
        return super.get(i);
    }

    public E getbyNativePageFragmentforClassify_1(int i) {
        return super.get(i);
    }

    public E getbyNativePageFragmentforClassify(int i) {
        return super.get(i);
    }

    public E getbyNativePageFragmentforOther(int i) {
        return super.get(i);
    }

    public E getbyNativePageFragmentOfClub(int i) {
        return super.get(i);
    }

    public static void saveDebugLog(String str) {
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        File c = ao.c(a.l + "HeaderViewListAdapterError.log");
        if (c != null && c.length() > ((long) LOG_MAX_SIZE)) {
            c.delete();
            c = ao.c(a.l + "HeaderViewListAdapterError.log");
        }
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())) + "]----");
            stringBuffer.append(str);
            stringBuffer.append(ENTER);
            randomAccessFile2 = new RandomAccessFile(c, "rw");
            try {
                randomAccessFile2.seek(randomAccessFile2.length());
                randomAccessFile2.write(stringBuffer.toString().getBytes("UTF-8"));
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                randomAccessFile = randomAccessFile2;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (Exception e8) {
                e3 = e8;
                randomAccessFile = randomAccessFile2;
                e3.printStackTrace();
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4222) {
                        e4222.printStackTrace();
                    }
                }
            }
        } catch (UnsupportedEncodingException e9) {
            e = e9;
            randomAccessFile2 = null;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        } catch (FileNotFoundException e10) {
            e2 = e10;
            e2.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Exception e11) {
            e3 = e11;
            e3.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
    }
}
