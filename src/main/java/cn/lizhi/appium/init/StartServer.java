package cn.lizhi.appium.init;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

import java.io.File;

public class StartServer {
	AppiumDriverLocalService service;
	public  void startAppium(String port,String bport){
		AppiumServiceBuilder ab=new AppiumServiceBuilder();
		//给服务加参数
		ab.usingPort(Integer.valueOf(port));
		ab.withLogFile(new File("logs/appium"+port+".log"));
		ab.withArgument(new ServerArgument() {
			public String getArgument() {
				// TODO Auto-generated method stub
				return "-bp";
			}
		}, String.valueOf(bport));
		service=ab.build();
		service.start();
		//	service.stop();
	}
	public  void startAppium(){
		AppiumServiceBuilder ab=new AppiumServiceBuilder();
		//给服务加参数
		ab.withLogFile(new File("logs/appium.log"));
		service=ab.build();
		service.start();
		//	service.stop();
	}
	public  void stopAppium(){
		service.stop();
	}
	public static void main(String[] args) {
		//startAppium();
	}

}
