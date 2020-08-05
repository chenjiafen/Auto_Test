package cn.lizhi.yongwangcase;
import cn.lizhi.common.ResultEnum;
import cn.lizhi.constans.YongWangCrm;
import cn.lizhi.http.HttpMethod;
import cn.lizhi.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenjiafneg
 * @Date 2020/8/5 18:45
 * @Version 1.0
 */
public class SendSms extends  TestBase {
    @Test(dataProvider = "sendDatas")
    @Description("发送短信消息")
    public void testSend(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "app");
        map.put("mobile", mobile);
        map.put("smsTemplateType", "4");
        String basicInfoJsonStr = JSON.toJSONString(map);
        HttpResponse reqs = request.method(HttpMethod.POST).host(YongWangCrm.Base_URL_DIV)
                .path(YongWangCrm.SendSms_URL)
                .data(basicInfoJsonStr).send();
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String code = jsonResult.getString("code");
        log.info("响应体" + reponseResult + "响应code" + code + "statusLine" + reqs.statusLine());
        Assert.assertEquals(ResultEnum.SUCCESS_MESSAGE.getMsg(), jsonResult.getString("message"));
    }

    @DataProvider
    public Object[][] sendDatas() {
        Object[][] datas = {
                {"18566582390"},
                {"17688732017"},
        };
        return datas;
    }
}
