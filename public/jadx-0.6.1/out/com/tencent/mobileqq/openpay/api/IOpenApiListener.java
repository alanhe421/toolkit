package com.tencent.mobileqq.openpay.api;

import com.tencent.mobileqq.openpay.data.base.BaseResponse;

public interface IOpenApiListener {
    void onOpenResponse(BaseResponse baseResponse);
}
