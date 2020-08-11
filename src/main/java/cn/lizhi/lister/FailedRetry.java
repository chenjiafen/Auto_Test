package cn.lizhi.lister;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.util.logging.Logger;

/*
 *案例执行抛异常则重试
 */
public class FailedRetry implements IRetryAnalyzer {
    private static Logger log = Logger.getLogger(FailedRetry.class.getName());
    private int retryCount = 1;//设置当前的重跑次数
    private static final int maxRetryCount = 2;//设置最大重跑次数，定义为常量

    public boolean retry(ITestResult iTestResult) {
        if (retryCount <= maxRetryCount) {
            //输出当前的重跑次数以及当前的正在重跑的用例名称。
            log.info("重跑次数：" + retryCount + "次，" + "重跑的case：" + iTestResult.getName());
            //重跑之后，次数+1
            retryCount++;
            //当return true之后，代表继续重跑
            return true;
        }
        //return false之后，代表停止重跑
        return false;
    }
}
