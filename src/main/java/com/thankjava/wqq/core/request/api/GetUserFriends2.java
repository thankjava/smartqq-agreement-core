package com.thankjava.wqq.core.request.api;

import com.alibaba.fastjson.JSONObject;
import com.thankjava.toolkit.bean.aop.anno.Before;
import com.thankjava.toolkit3d.bean.http.*;
import com.thankjava.wqq.consts.RequestUrls;
import com.thankjava.wqq.core.request.aop.DoRequest;
import com.thankjava.wqq.extend.CallBackListener;
import com.thankjava.wqq.util.WqqEncryptor;

public class GetUserFriends2 extends BaseHttpService {

    @Override
    @Before(cutClass = DoRequest.class, cutMethod = "doRequest")
    public AsyncResponse doRequest(CallBackListener listener) {
        return null;
    }

    @Override
    protected AsyncRequest buildRequestParams() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("vfwebqq", session.getVfwebqq());
        jsonObject.put("hash", WqqEncryptor.hash(String.valueOf(session.getUin()), session.getPtwebqq()));
        Parameters params = new Parameters("r", jsonObject.toJSONString());
        Headers headers = new Headers("Referer", RequestUrls.referer_common.url);
        return new AsyncRequest(RequestUrls.get_user_friends2.url, HttpMethod.post, params, headers);
    }

}
