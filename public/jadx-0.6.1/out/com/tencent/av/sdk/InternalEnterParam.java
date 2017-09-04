package com.tencent.av.sdk;

import com.tencent.av.sdk.AVRoomMulti.EnterParam.Builder;

public class InternalEnterParam extends AVRoomMulti$EnterParam {
    protected int bussType;
    protected byte[] extraData;
    protected byte[] freeFlowSignature;
    protected int relationType;

    public static class InternalEnterRoomParamBuilder extends Builder {
        protected int bussType = 7;
        protected byte[] extraData;
        protected byte[] freeFlowSignature;
        protected int relationType = 6;

        public InternalEnterRoomParamBuilder(int i) {
            super(i);
        }
    }

    public InternalEnterParam(InternalEnterRoomParamBuilder internalEnterRoomParamBuilder) {
        super(internalEnterRoomParamBuilder);
        this.bussType = internalEnterRoomParamBuilder.bussType;
        this.relationType = internalEnterRoomParamBuilder.relationType;
        this.extraData = internalEnterRoomParamBuilder.extraData;
        this.freeFlowSignature = internalEnterRoomParamBuilder.freeFlowSignature;
    }
}
