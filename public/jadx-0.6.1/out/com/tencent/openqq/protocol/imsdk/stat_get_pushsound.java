package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;

public final class stat_get_pushsound {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int UINT32_PLATFORM_FIELD_NUMBER = 2;
        public static final int UINT64_TINYID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16}, new String[]{"uint64_tinyid", "uint32_platform"}, new Object[]{Long.valueOf(0), Integer.valueOf(0)}, ReqBody.class);
        public final PBUInt32Field uint32_platform = PBField.initUInt32(0);
        public final PBUInt64Field uint64_tinyid = PBField.initUInt64(0);
    }

    private stat_get_pushsound() {
    }
}
