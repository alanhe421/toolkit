package com.tencent.midas.comm;

import com.tencent.imsdk.BaseConstants;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

public class APFileSizeUtil {
    public static final int SIZETYPE_B = 1;
    public static final int SIZETYPE_GB = 4;
    public static final int SIZETYPE_KB = 2;
    public static final int SIZETYPE_MB = 3;

    public static double getFileOrFilesSize(String str, int i) {
        File file = new File(str);
        long j = 0;
        try {
            if (file.isDirectory()) {
                j = getFileSizes(file);
            } else {
                j = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(j, i);
    }

    public static String getAutoFileOrFilesSize(String str) {
        File file = new File(str);
        long j = 0;
        try {
            if (file.isDirectory()) {
                j = getFileSizes(file);
            } else {
                j = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(j);
    }

    private static long getFileSize(File file) throws Exception {
        if (file.exists()) {
            return (long) new FileInputStream(file).available();
        }
        file.createNewFile();
        return 0;
    }

    private static long getFileSizes(File file) throws Exception {
        long j = 0;
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                j += getFileSizes(listFiles[i]);
            } else {
                j += getFileSize(listFiles[i]);
            }
        }
        return j;
    }

    private static String FormetFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String str = "";
        str = "0B";
        if (j == 0) {
            return str;
        }
        if (j < 1024) {
            return decimalFormat.format((double) j) + "B";
        }
        if (j < BaseConstants.MEGA) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        }
        return decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
    }

    private static double FormetFileSize(long j, int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        switch (i) {
            case 1:
                return Double.valueOf(decimalFormat.format((double) j)).doubleValue();
            case 2:
                return Double.valueOf(decimalFormat.format(((double) j) / 1024.0d)).doubleValue();
            case 3:
                return Double.valueOf(decimalFormat.format(((double) j) / 1048576.0d)).doubleValue();
            case 4:
                return Double.valueOf(decimalFormat.format(((double) j) / 1.073741824E9d)).doubleValue();
            default:
                return 0.0d;
        }
    }

    public static void DeleteFile(File file) {
        try {
            if (!file.exists()) {
                return;
            }
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (!APLogWriter.createLogFileName().endsWith(listFiles[i].getName())) {
                            listFiles[i].delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
