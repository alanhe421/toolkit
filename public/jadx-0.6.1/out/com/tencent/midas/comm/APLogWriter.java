package com.tencent.midas.comm;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APLogWriter {
    private static final double LOG_MAX_SIZE = 1024.0d;
    private static SimpleDateFormat df;
    private static APLogWriter mLogFile = null;
    private static Writer mWriter;
    private APLogHandlerThread apHandlerThread = null;
    private Handler mLogHandler = null;

    private APLogWriter() {
        createWriter();
    }

    public static APLogWriter open() throws Exception {
        if (mLogFile == null) {
            mLogFile = new APLogWriter();
        }
        if (mWriter == null || isLogFileUpMax() || !isLogFileExist()) {
            mLogFile = new APLogWriter();
        }
        if (df == null) {
            df = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ");
        }
        return mLogFile;
    }

    protected static String getPackage(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).packageName;
        } catch (Exception e) {
            Log.w(APLogInfo.INNER_LOG_TAG, e.toString());
            return str;
        }
    }

    private static String getFileDirName() {
        String str = "";
        try {
            Object obj = getPackage(APLog.getLogInfo().getContext());
            if (!TextUtils.isEmpty(obj)) {
                str = Environment.getExternalStorageDirectory() + File.separator + "Tencent" + File.separator + "Midas" + File.separator + "Log" + File.separator + obj + File.separator;
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
        return str;
    }

    public static String createLogFileName() {
        StringBuffer stringBuffer;
        Exception exception;
        Object obj;
        String format;
        try {
            if (TextUtils.isEmpty(getFileDirName())) {
                stringBuffer = null;
            } else {
                stringBuffer = new StringBuffer(getFileDirName());
                try {
                    format = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
                    stringBuffer.append("MidasLog");
                    stringBuffer.append("_");
                    stringBuffer.append(format);
                    stringBuffer.append(".txt");
                } catch (Exception e) {
                    Exception exception2 = e;
                    Object obj2 = stringBuffer;
                    exception = exception2;
                    Log.i(APLogInfo.INNER_LOG_TAG, exception.toString());
                    obj = format;
                    if (stringBuffer != null) {
                        return null;
                    }
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e2) {
            exception = e2;
            format = null;
            Log.i(APLogInfo.INNER_LOG_TAG, exception.toString());
            obj = format;
            if (stringBuffer != null) {
                return stringBuffer.toString();
            }
            return null;
        }
        if (stringBuffer != null) {
            return stringBuffer.toString();
        }
        return null;
    }

    private static boolean createDir(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return file.mkdirs();
            }
            deleteElseLog();
            return true;
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
            return false;
        }
    }

    private static void deleteElseLog() {
        try {
            if (!TextUtils.isEmpty(getFileDirName())) {
                APFileSizeUtil.DeleteFile(new File(getFileDirName()));
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    private static boolean isLogFileExist() {
        try {
            Object createLogFileName = createLogFileName();
            if (!TextUtils.isEmpty(createLogFileName)) {
                File file = new File(createLogFileName);
                if (file.exists() && file.length() > 0) {
                    return true;
                }
            }
            mWriter = null;
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
        return false;
    }

    private static boolean isLogFileUpMax() {
        try {
            String createLogFileName = createLogFileName();
            if (!TextUtils.isEmpty(createLogFileName)) {
                File file = new File(createLogFileName);
                if (APFileSizeUtil.getFileOrFilesSize(createLogFileName, 2) > LOG_MAX_SIZE) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
        return false;
    }

    private static void createLogFile() {
        try {
            String createLogFileName = createLogFileName();
            if (!TextUtils.isEmpty(createLogFileName)) {
                File file = new File(createLogFileName);
                if (!file.isFile() || !file.exists()) {
                    try {
                        if (!TextUtils.isEmpty(getFileDirName())) {
                            createDir(getFileDirName());
                            file.createNewFile();
                        }
                    } catch (Exception e) {
                        Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
                    }
                } else if (APFileSizeUtil.getFileOrFilesSize(createLogFileName, 2) > LOG_MAX_SIZE) {
                    file.delete();
                    mWriter = null;
                    try {
                        file.createNewFile();
                    } catch (Exception e2) {
                        Log.i("TencentPay createLogFile file.exists", e2.toString());
                    }
                } else {
                    deleteElseLog();
                }
            }
        } catch (Exception e22) {
            Log.i(APLogInfo.INNER_LOG_TAG, e22.toString());
        }
    }

    private void createWriter() {
        try {
            if (isSDcardExist() && isDirExist(getFileDirName())) {
                createLogFile();
                if (mWriter == null) {
                    Object createLogFileName = createLogFileName();
                    if (!TextUtils.isEmpty(createLogFileName)) {
                        mWriter = new BufferedWriter(new FileWriter(createLogFileName, true), 2048);
                    }
                }
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    private boolean isSDcardExist() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static boolean isDirExist(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void createWriteThread() {
        try {
            if (this.apHandlerThread == null) {
                this.apHandlerThread = new APLogHandlerThread("APMidasLogThread");
                this.apHandlerThread.start();
            }
            if (this.mLogHandler == null) {
                this.mLogHandler = new Handler(this.apHandlerThread.getLooper());
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    public void close() throws Exception {
        try {
            this.mLogHandler.post(new Runnable() {
                public void run() {
                    try {
                        if (APLogWriter.mLogFile != null) {
                            APLogWriter.mLogFile.close();
                            APLogWriter.mLogFile = null;
                        }
                        if (APLogWriter.mWriter != null) {
                            APLogWriter.mWriter.close();
                            APLogWriter.mWriter = null;
                        }
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    public void print(final String str) throws Exception {
        try {
            if (this.mLogHandler == null) {
                createWriteThread();
            }
            this.mLogHandler.post(new Runnable() {
                public void run() {
                    try {
                        if (APLogWriter.mWriter != null) {
                            APLogWriter.mWriter.write(APLogWriter.df.format(new Date()));
                            APLogWriter.mWriter.write(str);
                            APLogWriter.mWriter.write("\r\n");
                            APLogWriter.mWriter.flush();
                        }
                    } catch (Exception e) {
                        Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
                    }
                }
            });
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    public void print(final String str, final String str2) throws Exception {
        try {
            if (this.mLogHandler == null) {
                createWriteThread();
            }
            this.mLogHandler.post(new Runnable() {
                public void run() {
                    try {
                        if (APLogWriter.mWriter != null) {
                            APLogWriter.mWriter.write(APLogWriter.df.format(new Date()));
                            APLogWriter.mWriter.write(str + " ");
                            APLogWriter.mWriter.write(str2);
                            APLogWriter.mWriter.write("\r\n");
                            APLogWriter.mWriter.flush();
                        }
                    } catch (Exception e) {
                        Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
                    }
                }
            });
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }
}
