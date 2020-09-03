package cn.lizhi.appium.init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class IOSInitDriver {

	/**
	 * 提供公共参数caps对象
	 * @param udid
	 * @return
	 */
	public static DesiredCapabilities getCommonCaps(String udid) {
		DesiredCapabilities caps = new DesiredCapabilities();// 要传递给服务端的参数对象
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");// deviceName的值在安卓下随便写都可以成功
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");// 要测试类型
		caps.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);// 是否不重签名app
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);// 表示服务端session超时时间，默认是60秒
		//caps.setCapability(MobileCapabilityType.UDID, udid);// 表示要连接的哪一台设备
		caps.setCapability(MobileCapabilityType.NO_RESET, true);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		return caps;
	}

	/**
	 * 针对未安装的应用进行driver初始化操作
	 * @param udid
	 * @param appPath
	 * @return
	 * @throws Exception
	 */
	public static AppiumDriver<MobileElement> initDriverWithNotInstaller(String udid, String appPath)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
		caps.setCapability(MobileCapabilityType.APP,appPath);


		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

	public static AppiumDriver<MobileElement> initDriverWithNotInstallerHunHe(String udid, String appPath)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
		caps.setCapability(MobileCapabilityType.APP,appPath);
		caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);

		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

	public static AppiumDriver<MobileElement> initZhenJiDriverWithNotInstaller(String udid, String appPath)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象
		caps.setCapability(MobileCapabilityType.APP,appPath);
		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);

		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

	public static AppiumDriver<MobileElement> initZhenJiDriverWithInstaller(String udid, String bundleid)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象

		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
		caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleid);


		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

	public static AppiumDriver<MobileElement> initZhenJiDriverWithH5(String udid)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象

		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		caps.setCapability(IOSMobileCapabilityType.START_IWDP, true);
		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

	public static AppiumDriver<MobileElement> initZhenJiDriverWithHunHe(String udid,String bundleid)
			throws Exception {

		DesiredCapabilities caps = getCommonCaps(udid);// 要传递给服务端的参数对象

		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
		caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleid);
		caps.setCapability(IOSMobileCapabilityType.START_IWDP, true);
		AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://192.168.0.102:4490/wd/hub"), caps);
		return driver;
	}

}
