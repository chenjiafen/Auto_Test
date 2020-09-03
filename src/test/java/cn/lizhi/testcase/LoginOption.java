package cn.lizhi.testcase;

import cn.lizhi.constans.RainbowcnCrm;
import cn.lizhi.http.HttpMethod;
import cn.lizhi.http.HttpResponse;
import cn.lizhi.utils.log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 主要exel驱动数据
 *
 * @Author chenjiafneg
 * @Date 2020/8/12 15:01
 * @Version 1.0
 */
public class LoginOption extends TestBase {

    @Test(dataProvider = "optionsData")
    public void testOption(String casenum, String casename, String url, String HttpMethod, String parm, String assertValue) throws Exception {
        log.startTestCase();
        HttpResponse reqs = request.method(cn.lizhi.http.HttpMethod.POST).host(RainbowcnCrm.Base_URL_SIT)
                .path(url)
                .contentType("application/json; charset=utf-8").data(parm).send();
        String reponseResult = reqs.body();
        log.info("请求参数" + parm + "=======" + assertValue);
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String data = jsonResult.getJSONObject("data").toString();
        String mobile = jsonResult.getJSONObject("data").getString("mobile");
        log.info(casename + "jsonResult===>>" + jsonResult + "======>>" + "mobile:" + mobile);
        try {
            Assert.assertEquals(data, assertValue);
//            Assert.assertEquals(mobile, "176****2017");
            excel.setCellData(Integer.valueOf(casenum), 7, "测试通过", "login-options", true);
        } catch (Error e) {
            excel.setCellData(Integer.valueOf(casenum), 7, "测试失败", "login-options", false);
            log.endTestCase();
            Assert.fail(e.getMessage());
        }
        log.endTestCase();

    }
}
