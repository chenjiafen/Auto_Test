package cn.lizhi.appium.init;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * 这个类封装所有driver初始化的各种情况
 */
public class InitDriver {
    /**
     * 提供公共参数caps对象
     * @param udid
     * @return
     */
    public static DesiredCapabilities getCommonCaps(String udid) {
        DesiredCapabilities caps = new DesiredCapabilities();// 要传递给服务端的参数对象
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "随便写");// deviceName的值在安卓下随便写都可以成功
        caps.setCapability(MobileCapabilityType.PLATFORM, "Android");// 要测试类型
        caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);// 是否支持appium自带的unicode输入法
        caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);// 是否重置设备的输入法
        caps.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);// 是否不重签名app
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);// 表示服务端session超时时间，默认是60秒
        caps.setCapability(MobileCapabilityType.UDID, udid);// 表示要连接的哪一台设备
        caps.setCapability(MobileCapabilityType.NO_RESET, true);

        return caps;
    }
    /**
     * 针对未安装的应用进行driver初始化操作
     * @param udid
     * @param appPath
     * @return
     * @throws Exception
     */
    public static AndroidDriver<AndroidElement> initDriverWithNotInstaller(String udid, String appPath)
            throws Exception {
        File app = new File(appPath);// 要安装测试的app

        DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        return driver;
    }

    public static AndroidDriver<AndroidElement> initDriverWithNotInstaller(String udid, String appPath,String appWaitActivity)
            throws Exception {
        File app = new File(appPath);// 要安装测试的app

        DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appWaitActivity);

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        return driver;
    }
    /**
     * 针对已安装的应用，想直接启动应用进行测试的初始化driver操作
     * @param udid
     * @param appPackage
     * @param activity
     * @return
     * @throws Exception
     */
    public static AndroidDriver<AndroidElement> initDriverWithInstaller(String udid,String appPackage,String activity) throws Exception {
        DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象

        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, activity);
        //该模式输入速度快，可以针对toast消息做断言操作，仅支持安卓5.0以上
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        //该参数可以用来给app默认授权
        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "C:/Users/LXG/AppData/Roaming/npm/node_modules/appium/node_modules/appium-chromedriver/chromedriver/win/chromedriver2.28.exe");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        return driver;

    }

    public static AndroidDriver<AndroidElement> initDriverWithInstaller(String udid,String appPackage,String activity,String appWaitActivity,String port,String systemPort) throws Exception {
        DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象

        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, activity);
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appWaitActivity);
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"), caps);
        return driver;

    }

    public static AndroidDriver<AndroidElement> initDriverWithH5(String udid) throws Exception {
        DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability(AndroidMobileCapabilityType.NATIVE_WEB_SCREENSHOT, true);
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        return driver;

    }

    public static void main(String[] args) throws Exception {
        // initDriver();
        //initDriverWithInstaller();
        String s="sdddddddd";
        s.length();
    }

}
