package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AiManager {
    @Resource
    private YuCongMingClient yuCongMingClient;


    /**
     * AI 对话
     *
     * @param modelId
     * @param messsage
     * @return
     */
    public String doChat(Long modelId, String messsage) {
        // 第三步，构造请求参数
        DevChatRequest devChatRequest = new DevChatRequest();
        // 模型id，尾后加L，转成long类型
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(messsage);
        // 第四步，获取响应结果
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        // 如果响应为null，就抛出异常，提示“AI 响应错误”
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
}
