package com.pkpmcloud.fileserver.protocol.tracker.request;

import com.pkpmcloud.fileserver.constant.CmdConstants;
import com.pkpmcloud.fileserver.protocol.BaseRequest;
import com.pkpmcloud.fileserver.protocol.ProtocolHead;


/**
 * 获取Group信息请求
 * 作者：LiZW <br/>
 * 创建时间：2016/11/20 15:06 <br/>
 */
public class GetGroupListRequest extends BaseRequest {
    public GetGroupListRequest() {
        head = new ProtocolHead(CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_GROUP);
    }
}
