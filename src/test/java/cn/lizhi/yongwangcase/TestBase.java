package cn.lizhi.yongwangcase;

import cn.lizhi.http.HttpRequest;
import cn.lizhi.utils.ExcelUtil;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.logging.Logger;
/**
 * @Author chenjiafneg
 * @Date 2020/8/5 18:43
 * @Version 1.0
 */
public class TestBase {
    HttpRequest request;
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    Logger log = Logger.getLogger(TestBase.class.getName());

    public TestBase() {
        this.request = setHeader();
    }

    public HttpRequest setHeader() {
        HttpRequest req = new HttpRequest().header("x-http-channel", "app")
                .header("x-http-devicetoken", "13065ffa4e8d8011841")
                .header("x-http-devicetypen", "android")
                .header("x-http-version", " 1.1.5.8")
                .header("x-http-screenheight", "1920")
                .header("x-http-osversion", "4.4.2")
                .header("x-http-encryption", " 0")
                .header("x-http-deviceuid", "13065ffa4e8d8011841")
                .header("x-http-screenwidth", "1080")
                .header("x-http-screenheight", "1920")
                .header("Content-Typet", "application/json;charset=UTF-8");
        return req;
    }

    @DataProvider
    public Object[][] getData(Method method) throws Exception {
        String fileName = this.getClass().getClassLoader().getResource("tianhong,xlsx").getPath();
        ExcelUtil excel = new ExcelUtil(fileName);
        Object[][] result = null;
        if (method.getName().equals("test001_login")) {
            result = excel.getTestData("login");
        } else if (method.getName().equals("test004_search")) {
            result = excel.getTestData("search");
        } else {
            result = new Object[][]{new Object[]{3}};
        }
        return result;
    }

    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }
}
