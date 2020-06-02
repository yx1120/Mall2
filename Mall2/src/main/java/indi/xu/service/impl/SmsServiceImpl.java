package indi.xu.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import indi.xu.service.SmsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author a_apple
 * @create 2020-05-28 17:00
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean send(String phoneNumbers,  String templateCode, Map<String, String> map) throws JsonProcessingException {

        // 连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FxyGHaMfXrGNfDmJdY8", "3f4Oj4Iibl8HL8tpf9BgTyzi9OGz8a");
        IAcsClient client = new DefaultAcsClient(profile);

        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms"); // 上面都不用动

        // 注：这几个参数的名称是固定的
        // 手机号、签名、模板Code :代表使用的是哪一个短信模板
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "小花商城");
        request.putQueryParameter("TemplateCode", templateCode);
        // 您的验证码为：${code}，该验证码 5 分钟内有效，请勿泄漏于他人。
        // 模板参数：就是{code}的值
        request.putQueryParameter("TemplateParam", new ObjectMapper().writeValueAsString(map));

        try {
            // client发送短信
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("response:"+response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
