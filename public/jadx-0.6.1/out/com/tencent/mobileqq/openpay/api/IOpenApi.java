package com.tencent.mobileqq.openpay.api;

import android.content.Intent;
import com.tencent.mobileqq.openpay.data.base.BaseApi;

public interface IOpenApi {
    boolean execApi(BaseApi baseApi);

    boolean handleIntent(Intent intent, IOpenApiListener iOpenApiListener);

    boolean isMobileQQInstalled();

    boolean isMobileQQSupportApi(String str);
}
