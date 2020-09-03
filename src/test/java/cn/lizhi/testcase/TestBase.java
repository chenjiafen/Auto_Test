package cn.lizhi.testcase;

import cn.lizhi.common.ResourcesPath;
import cn.lizhi.http.HttpRequest;
import cn.lizhi.utils.ExcelUtil;
import cn.lizhi.utils.log;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @Author chenjiafneg
 * @Date 2020/8/5 17:50
 * @Version 1.0
 */
public class TestBase {
    HttpRequest request;
//    Logger log = Logger.getLogger(TestBase.class.getName());
    log log=new log();
    private String token;
    ExcelUtil excel;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public TestBase() {
        this.request = setHeader();
    }

    public HttpRequest setHeader() {
        HttpRequest req = new HttpRequest()
                .header("Accept", "application/json")
                .header("jt", token)
                .header("Sec-Fetch-Site", "same-origin")
                .header("Sec-Fetch-Mode", "cors");
        return req;

    }
    /**
     * exel数据驱动
     * @param
     * @return
     * @throws Exception
     */
    @DataProvider
    public Object[][] optionsData() throws Exception {
        excel = new ExcelUtil(ResourcesPath.testCasePath);
        Object[][] obj = excel.getTestData("login-options");
        return obj;
    }
    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }

    /**
     * 读取exel数据
     * @param casenum
     * @param casename
     * @param url
     * @param parm
     * @param assertValue
     * @throws Exception
     */
    @Test(dataProvider = "optionsData")
    public void loginDataTest(String casenum ,String casename,String url,String HttpMethod,String parm,String assertValue) throws Exception {
//        ExcelUtil excel = new ExcelUtil("/Users/Work/testapi01/src/main/resources/tianhong.xlsx");
        System.out.println("casenum==="+casenum+"casename==="+casename+"url==="+url+"HttpMethod==="+HttpMethod+"parm==="+parm+"assertValue==="+assertValue);
//        excel.setCellData(Integer.valueOf(casenum),7,"测试失败","login",false);
    }
}
