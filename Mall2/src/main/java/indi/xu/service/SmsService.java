package indi.xu.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author a_apple
 * @create 2020-05-28 16:56
 */
public interface SmsService {

    /**
     * 发送短信服务
     * @param phoneNumbers 手机号
     * @param templateCode 短信模板码
     * @param map 验证码
     * @return
     */
    boolean send(String phoneNumbers, String templateCode,Map<String,String> map) throws JsonProcessingException;
}
