package tencent.tls.oidb.cmd0x601;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;

public final class Oidb0X601 {

    public static final class OpenAccountInfo extends MessageMicro<OpenAccountInfo> {
        public static final int STR_OPENAPPID_FIELD_NUMBER = 2;
        public static final int STR_OPENID_FIELD_NUMBER = 3;
        public static final int UINT32_OPENACCTYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_openacctype", "str_openappid", "str_openid"}, new Object[]{Integer.valueOf(0), "", ""}, OpenAccountInfo.class);
        public final PBStringField str_openappid = PBField.initString("");
        public final PBStringField str_openid = PBField.initString("");
        public final PBUInt32Field uint32_openacctype = PBField.initUInt32(0);
    }

    public static final class OpenSigInfo extends MessageMicro<OpenSigInfo> {
        public static final int BYTES_OPENSIG_FIELD_NUMBER = 3;
        public static final int STR_OPENAPPID_FIELD_NUMBER = 2;
        public static final int UINT32_OPENSIGTYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_opensigtype", "str_openappid", "bytes_opensig"}, new Object[]{Integer.valueOf(0), "", ByteStringMicro.EMPTY}, OpenSigInfo.class);
        public final PBBytesField bytes_opensig = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBStringField str_openappid = PBField.initString("");
        public final PBUInt32Field uint32_opensigtype = PBField.initUInt32(0);
    }

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int OPENACCOUNT_FIELD_NUMBER = 1;
        public static final int OPENSIG_FIELD_NUMBER = 3;
        public static final int UINT32_SDKAPPID_FIELD_NUMBER = 4;
        public static final int UINT64_TINYID_FIELD_NUMBER = 5;
        public static final int USERACCOUNT_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 32, 40}, new String[]{"openaccount", "useraccount", "opensig", "uint32_sdkappid", "uint64_tinyid"}, new Object[]{null, null, null, Integer.valueOf(0), Long.valueOf(0)}, ReqBody.class);
        public OpenAccountInfo openaccount = new OpenAccountInfo();
        public OpenSigInfo opensig = new OpenSigInfo();
        public final PBUInt32Field uint32_sdkappid = PBField.initUInt32(0);
        public final PBUInt64Field uint64_tinyid = PBField.initUInt64(0);
        public UserAccountInfo useraccount = new UserAccountInfo();
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[0], new String[0], new Object[0], RspBody.class);
    }

    public static final class UserAccountInfo extends MessageMicro<UserAccountInfo> {
        public static final int STR_USERACCOUNT_FIELD_NUMBER = 2;
        public static final int STR_USERSIG_FIELD_NUMBER = 3;
        public static final int UINT32_USERACCTYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_useracctype", "str_useraccount", "str_usersig"}, new Object[]{Integer.valueOf(0), "", ""}, UserAccountInfo.class);
        public final PBStringField str_useraccount = PBField.initString("");
        public final PBStringField str_usersig = PBField.initString("");
        public final PBUInt32Field uint32_useracctype = PBField.initUInt32(0);
    }

    private Oidb0X601() {
    }
}
