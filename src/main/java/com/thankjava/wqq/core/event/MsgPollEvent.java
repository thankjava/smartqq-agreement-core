package com.thankjava.wqq.core.event;

import com.thankjava.wqq.extend.ActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thankjava.toolkit3d.http.async.entity.AsyncResponse;
import com.thankjava.wqq.WQQClient;
import com.thankjava.wqq.core.request.RequestBuilder;
import com.thankjava.wqq.core.request.api.Poll2;
import com.thankjava.wqq.entity.msg.PollMsg;
import com.thankjava.wqq.extend.CallBackListener;
import com.thankjava.wqq.factory.RequestFactory;
import com.thankjava.wqq.util.JSON2Entity;

/**
 * 消息事件处理器
 */
public class MsgPollEvent {

    private static final Logger logger = LoggerFactory.getLogger(MsgPollEvent.class);

    private RequestBuilder poll2 = RequestFactory.getInstance(Poll2.class);

    public void poll() {

        poll2.doRequest(new CallBackListener() {

            @Override
            public void onListener(ActionListener actionListener) {
                if (actionListener.getData() != null) {
                    AsyncResponse response = (AsyncResponse) actionListener.getData();
                    logger.debug("msgPoll Event > httpStatus: " + response.getHttpCode());
                    if (response.getHttpCode() == 200) {
                        PollMsg pollMsg = JSON2Entity.pollMsg(response.getDataString());
                        if (pollMsg != null) {
                            notifyMsgEvent(pollMsg);
                        }
                    }
                    poll();
                }
            }
        });
    }

    private void notifyMsgEvent(PollMsg pollMsg) {
        WQQClient.getNotifyListener().handler(WQQClient.getInstance(), pollMsg);
    }
}
