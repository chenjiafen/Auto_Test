package cn.lizhi.yongwangcase;

import cn.lizhi.common.ResultEnum;
import cn.lizhi.constans.YongWangCrm;
import cn.lizhi.http.HttpMethod;
import cn.lizhi.http.HttpRequest;
import cn.lizhi.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author chenjiafneg
 * @Date 2020/8/5 18:51
 * @Version 1.0
 */
public class MemberLogin extends TestBase {
    String token;

    @Test(dataProvider = "loginDatas")
    @Description("cmr登录接口测试不同账号")
//    @Step("登录")
    public void testLogin(String username, String password) {
        String parm = "{\n" +
                "\"deviceRequest\": {\n" +
                "\"deviceCode\": \"4.4.2\",\n" +
                "\"deviceName\": \"M688C\",\n" +
                "\"deviceToken\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\"deviceUid\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\"deviceType\": 2\n" +
                "},\n" +
                "\"ip\": \"172.16.152.15\",\n" +
                "\"latitude\": \"36.056967\",\n" +
                "\"longitude\": \"120.397783\",\n" +
                "password: \"" + password + "\",\n" +
                "\"username\": \"" + username + "\"\n" + "}";

        HttpResponse reqs = request.method(HttpMethod.POST).host(YongWangCrm.Base_URL_DIV)
                .path(YongWangCrm.Login_UR)
                .contentType("application/json").data(parm).send();
        log.info("请求头状态码" + reqs.statusLine());
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String message = jsonResult.getString("message");
        token = jsonResult.getJSONObject("data").getString("token");
        super.setToken(token);
        if (token == null) {
            log.info("登录失败");
        }
        log.info("请求头状态码" + reqs.statusLine() + "=======" + "响应体" + jsonResult + "======" + "message" + message + "====" + "token" + token);

        try {
            Assert.assertEquals(ResultEnum.SUCCESS_OPERATION.getMsg(), message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error_Message" + e.getMessage());
        }


    }

    @Test(dataProvider = "MyInfoDatas",dependsOnMethods="testLogin")
    public void TestMyInfo(String storeCode, String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("storeCode", storeCode);
        map.put("code", code);
        String parm = JSON.toJSONString(map);
        request = setHeader();
        HttpResponse reqs = request.method(HttpMethod.POST).host(YongWangCrm.Base_URL_DIV)
                .path(YongWangCrm.MyInfo_URL)
                .contentType("application/json").data(parm).send();
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String message = jsonResult.getString("message");
        log.info("jsonResult===>>" + jsonResult);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);

    }

    @DataProvider(name = "MyInfoDatas")
    public Object[][] MyInfoDatas() {
        Object[][] datas = {
                {"storeCode", "0001"}
        };
        return datas;
    }

    @DataProvider
    public Object[][] loginDatas() {
        Object[][] datas = {
                {"18566582390", "chen980985672"}

        };
        return datas;
    }
}
