package com.tencent.upload;

public class Const {

    public enum FileType {
        File,
        Photo,
        Audio,
        Video,
        Other
    }

    public enum RetCode {
        SUCCEED(0, "成功"),
        FAST_SUCCEED(-20001, "秒传成功"),
        CANCELED(-20002, "任务取消"),
        PAUSED(-20003, "任务暂停"),
        FILE_NOT_EXIST(-20004, "文件不存在"),
        FILE_LENGTH_INVAID(-20005, "文件长度不合法"),
        NETWORK_NOT_AVAILABLE(-20006, "网络不可用"),
        RESPONSE_IS_NULL(-20007, "服务器回包为空"),
        REQUEST_TIMEOUT(-20008, "请求超时"),
        NO_SESSION(-20011, "没有可用Session"),
        SESSION_DISCONNECT(-20012, "Session连接断开"),
        SESSION_STATE_INVALID(-20013, "Session状态非法"),
        SESSION_DETECT_ERROR(-20014, "网络探测失败"),
        NDK_NETWORK_ERROR(-20015, "NDK Network Error"),
        NO_ROUTE(-20100, "没有可用路由"),
        SESSION_ALL_ROUTE_FAILED(-20101, "网络通道建立失败"),
        SESSION_CONN_SEND_FAILED(-20102, "Connection Send Failed"),
        SESSION_WITHOUT_CONN(-20103, "Connection为空"),
        SESSION_DIVIDE_PACKET_ERROR(-20104, "接收数据异常 分包失败"),
        SESSION_REQUEST_ENCODE_ERROR(-20105, "Request encode error"),
        HANDSHAKE_FAILED(-20290, "握手失败"),
        HANDSHAKE_TIMEOUT(-20291, "握手超时"),
        OOM(-21001, "Out Of Memory"),
        IO_EXCEPTION(-22000, "IO异常"),
        OTHER(-25000, "其他");
        
        private int code;
        private String desc;

        private RetCode(int i, String str) {
            this.code = i;
            this.desc = str;
        }

        public final int getCode() {
            return this.code;
        }

        public final String getDesc() {
            return this.desc;
        }
    }

    public enum ServerEnv {
        NORMAL(0, "正式环境"),
        DEV(1, "开发环境");
        
        private int code;
        private String desc;

        private ServerEnv(int i, String str) {
            this.code = i;
            this.desc = str;
        }

        public final int getCode() {
            return this.code;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String toString() {
            return "[" + this.code + "," + this.desc + "]";
        }
    }
}
