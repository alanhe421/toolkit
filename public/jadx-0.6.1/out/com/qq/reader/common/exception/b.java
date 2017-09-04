package com.qq.reader.common.exception;

/* compiled from: ErrorType */
public final class b {
    public static String a(int i) {
        switch (i) {
            case 1001:
                return "获取章节文件为空";
            case 1002:
                return "读取流不成功";
            case 1003:
                return "文件不存在";
            case 1004:
                return "文件流读取异常";
            case 1005:
                return "编码异常";
            case 1006:
                return "存储卡当前不可用";
            case 1007:
                return "解压缩失败";
            case 1008:
                return "数据库访问失败，已经尝试修复，请重新打开本书";
            case 1009:
                return "系统配置错误";
            case 1010:
                return "打开epub图书失败";
            default:
                return null;
        }
    }
}
