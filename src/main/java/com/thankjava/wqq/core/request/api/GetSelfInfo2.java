package com.thankjava.wqq.core.request.api;

import com.thankjava.toolkit.bean.aop.anno.Before;
import com.thankjava.toolkit3d.bean.http.*;
import com.thankjava.wqq.consts.RequestUrls;
import com.thankjava.wqq.core.request.aop.DoRequest;
import com.thankjava.wqq.extend.CallBackListener;

public class GetSelfInfo2 extends BaseHttpService {

    @Override
    @Before(cutClass = DoRequest.class, cutMethod = "doRequest")
    public AsyncResponse doRequest(CallBackListener listener) {
        return null;
    }

    @Override
    protected AsyncRequest buildRequestParams() {
        Parameters params = new Parameters("t", String.valueOf(System.currentTimeMillis() / 1000));

        Headers headers = new Headers("Referer", RequestUrls.referer_common.url);
        return new AsyncRequest(
                RequestUrls.get_self_info2.url,
                HttpMethod.get,
                params,
                headers
        );
    }

}
