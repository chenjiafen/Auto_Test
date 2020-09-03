package cn.lizhi.appium.init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenjiafneg
 * @Date 2020/9/3 14:58
 * @Version 1.0
 */
public class AppiumUtils {
    public static void swipeToUp(AndroidDriver<AndroidElement> driver) {
        // 获取的是应用占用屏幕的大小，而不是真正的分辨率，比分辨率小吧
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        TouchAction ta = new TouchAction(driver);
        // 向上滑动
        ta.press(width / 2, height * 7 / 8).waitAction(Duration.ofMillis(1000)).moveTo(width / 2, height / 8).release()
                .perform();
    }

    public static void swipeToDown(AppiumDriver<MobileElement> driver) {
        // 获取的是应用占用屏幕的大小，而不是真正的分辨率，比分辨率小吧
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        TouchAction ta = new TouchAction(driver);
        // 向上滑动
        ta.press(width / 2, height / 8).waitAction(Duration.ofMillis(1000)).moveTo(width / 2, height * 7 / 8).release()
                .perform();
    }

    public static void swipeToLeft(AppiumDriver<MobileElement> driver) {
        // 获取的是应用占用屏幕的大小，而不是真正的分辨率，比分辨率小吧
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        TouchAction ta = new TouchAction(driver);
        // 向上滑动
        ta.press(width * 7 / 8, height / 2).waitAction(Duration.ofMillis(1000)).moveTo(width / 8, height / 2).release()
                .perform();
    }

    public static void swipeToRight(AppiumDriver<MobileElement> driver) {
        // 获取的是应用占用屏幕的大小，而不是真正的分辨率，比分辨率小吧
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        TouchAction ta = new TouchAction(driver);
        // 向上滑动
        ta.press(width / 8, height / 2).waitAction(Duration.ofMillis(1000)).moveTo(width * 7 / 8, height / 2).release()
                .perform();
    }

    public static Boolean isElementExits(AndroidDriver<AndroidElement> driver, By by) {
        try {
            driver.findElement(by);
            System.out.println("元素存在");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("元素不存在");

        }
        return false;

    }

    /**
     * 获取元素结束点坐标对象
     *
     * @param ele
     * @return
     */
    public static Point getEndCoordinate(MobileElement ele) {
        int e1_x = ele.getLocation().getX();// 获取元素的起始点x
        int e1_y = ele.getLocation().getY();// 获取元素的起始点Y

        int e1_width = ele.getSize().getWidth();// 获取元素的宽
        int el_height = ele.getSize().getHeight();// 获取元素的高

        int e1_endx = e1_x + e1_width;
        int e1_endy = e1_y + el_height;

        return new Point(e1_endx, e1_endy);
    }

    /**
     * 在垂直方向上判断是否被遮挡，是Y轴方向的大小比较
     * 屏幕下方，被遮挡元素ele1的结束点Y坐标的值<=遮挡元素ele2的起始点Y值，则说明元素ele1没有被遮挡
     * 屏幕上方，被遮挡元素ele1的起始点Y坐标的值>=遮挡元素ele2的结束点的Y值，则说明元素ele1没有被遮挡
     *
     * @param ele1
     * @param ele2
     */
    public static Boolean elementIsOcclusion(MobileElement ele1, MobileElement ele2, String direction) {
        int ele1_starty = ele1.getLocation().getY();//被遮挡元素ele1的起始点Y值
        int ele1_endy = getEndCoordinate(ele1).getY();// 被遮挡元素的结束点Y值
        int ele2_starty = ele2.getLocation().getY();// 遮挡元素ele2的起始点Y值
        int ele2_endy = getEndCoordinate(ele2).getY();//遮挡元素ele2的结束点Y值
        if (direction.toLowerCase().equals("down")) {
            if (ele1_endy <= ele2_starty) {
                return false;
            } else {
                return true;
            }
        } else if (direction.toLowerCase().equals("up")) {
            if (ele1_starty >= ele2_endy) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }

    }

    /**
     * 当senkeys在输入一连串数字出问题的时候可以尝试使用该方法
     *
     * @param number
     */
    public static void sendKeyWithNumber(AndroidDriver<AndroidElement> driver, String number) {
        char[] numChars = number.toCharArray();

        for (char c : numChars) {
            int cNum = Integer.valueOf(String.valueOf(c));
            switch (cNum) {
                case 0:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
                    break;
                case 1:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
                    break;
                case 2:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
                    break;
                case 3:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
                    break;
                case 4:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
                    break;
                case 5:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
                    break;
                case 6:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
                    break;
                case 7:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
                    break;
                case 8:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
                    break;
                case 9:
                    driver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
                    break;
                default:
                    break;
            }
        }
    }

    public static void elementSwipe(MobileElement ele, AndroidDriver<AndroidElement> driver, String direction) {
        int x = ele.getLocation().getX();//起始点x  0
        int y = ele.getLocation().getY();//起始点y  391

        int width = ele.getSize().getWidth();//元素的宽  720/8=90*7=630
        int height = ele.getSize().getHeight();//元素的高 103
        TouchAction ta = new TouchAction(driver);
        switch (direction.toLowerCase()) {
            case "up":
                ta.press(x + width / 2, y + 7 * height / 8).waitAction(Duration.ofMillis(1000)).moveTo(x + width / 2, y + height / 8).release().perform();
                break;
            case "down":
                ta.press(x + width / 2, y + height / 8).waitAction(Duration.ofMillis(1000)).moveTo(x + width / 2, y + 7 * height / 8).release().perform();
                break;
            case "left":
                //630,442     90,442   2x+width=90
                ta.press(x + 7 * width / 8, y + height / 2).waitAction(Duration.ofMillis(1000)).moveTo(x + width / 8, y + height / 2).release().perform();
                break;
            case "right":
                ta.press(x + width / 8, y + height / 2).waitAction(Duration.ofMillis(1000)).moveTo(x + 7 * width / 8, y + height / 2).release().perform();
                break;

            default:
                break;
        }

    }
    //int[] 1258947

    /**
     * 该方法是针对手势密码锁无法定位到每一个小格子但是能够定位到手势密码大框的元素的
     *
     * @param ele
     * @param driver
     * @param pwd
     */
    public static void gesturePwd(MobileElement ele, AppiumDriver<MobileElement> driver, int[] pwd) {
//		int x=ele.getLocation().getX();
//		int y=ele.getLocation().getY();
//
//		int w=ele.getSize().getWidth();
//		int h=ele.getSize().getHeight();

        List<Point> coordanites = splitElement(ele, 3, 3);
        //1. 外循环是行row，内循环是列column
        //第一次执行，row=0，进入内循环， column=0:x+1*w/6,  y+h/6
        //                         column=1:x+3*w/6,y+h/6
        //                         column=2:x+5*w/6,y+h/6
        //第二次执行，row=1，进入内循环， column=0:x+1*w/6,  y+3*h/6
        //                         column=1:x+3*w/6,y+3*h/6
        //                         column=2:x+5*w/6,y+3*h/6
        //第三次执行，row=2，进入内循环， column=0:x+1*w/6,  y+5*h/6
        //                         column=1:x+3*w/6,y+5*h/6
        //                         column=2:x+5*w/6,y+5*h/6

//		for(int row=0;row<3;row++){
//			for(int column=0;column<3;column++){
//				int point_x=x+((2*column+1)*w)/6;
//				int point_y=y+((2*row+1)*h)/6;
//				coordanites.add(new Point(point_x,point_y));
//			}
//		}
        TouchAction ta = new TouchAction(driver);
        ta.press(coordanites.get(pwd[0] - 1).getX(), coordanites.get(pwd[0] - 1).getY()).waitAction(Duration.ofMillis(500));
        for (int i = 1; i < pwd.length; i++) {
            ta.moveTo(coordanites.get(pwd[i] - 1).getX() - coordanites.get(pwd[i - 1] - 1).getX(), coordanites.get(pwd[i] - 1).getY() - coordanites.get(pwd[i - 1] - 1).getY()).waitAction(Duration.ofMillis(500));
        }
        ta.release().perform();
    }

    /**
     * int[] 1258947
     * 如果可以定位到手势密码的每一个小格子，可以使用下面的方法
     *
     */
//	public static void gesturePwd(AndroidElement samllPwdList, AndroidDriver<AndroidElement> driver, int[] pwd){
//		TouchAction ta=new TouchAction(driver);
//		ta.press(samllPwdList.get(pwd[0])).waitAction(Duration.ofMillis(500));
//		for(int i=1;i<pwd.length;i++){
//			ta.moveTo(samllPwdList.(pwd[i])).waitAction(Duration.ofMillis(500));
//		}
//		ta.release().perform();
    //ta.tap(300, 400).release().perform();//如果这个元素真的没有办法定位到
    //第二种就是这个元素能够定位到，但是click的时候不生效或者报错，此时可以尝试使用坐标点击，但是这个坐标并不是绝对坐标，而是根据元素获取元素的中点坐标，然后传递给tap方法
//		AndroidElement a=driver.findElement(By.xpath("xxx"));
//		ta.tap(a.getCenter().getX(),a.getCenter().getY()).release().perform()

//	}
    //102034
    public static void anquanPwd(MobileElement ele, AppiumDriver<MobileElement> driver, int[] pwd) {
//		int x=ele.getLocation().getX();
//		int y=ele.getLocation().getY();
//
//		int w=ele.getSize().getWidth();
//		int h=ele.getSize().getHeight();

        List<Point> coordanites = splitElement(ele, 4, 4);

//		for(int row=0;row<4;row++){
//			for(int column=0;column<4;column++){
//				int point_x=x+((2*column+1)*w)/8;
//				int point_y=y+((2*row+1)*h)/8;
//				coordanites.add(new Point(point_x,point_y));
//			}
//		}
        TouchAction ta = new TouchAction(driver);

        for (int i = 0; i < pwd.length; i++) {
            if (pwd[i] == 0) {
                ta.press(coordanites.get(13).getX(), coordanites.get(13).getY()).release().perform();
            } else {
                if (pwd[i] <= 3) {
                    ta.press(coordanites.get(pwd[i] - 1).getX(), coordanites.get(pwd[i] - 1).getY()).release().perform();
                } else if (pwd[i] >= 4 && pwd[i] <= 6) {
                    ta.press(coordanites.get(pwd[i]).getX(), coordanites.get(pwd[i]).getY()).release().perform();
                } else if (pwd[i] >= 7 && pwd[i] <= 9) {
                    ta.press(coordanites.get(pwd[i] + 1).getX(), coordanites.get(pwd[i] + 1).getY()).release().perform();
                }
            }
        }

        //x和完成对应的集合坐标点的索引分别是：3和7对应的是x，11和15对应的是完成

    }

    //这个方法专门用来将一个大框元素拆分成几行几列的小元素坐标的
    public static List<Point> splitElement(MobileElement ele, int row, int column) {
        int x = ele.getLocation().getX();
        int y = ele.getLocation().getY();

        int w = ele.getSize().getWidth();
        int h = ele.getSize().getHeight();
        List<Point> coordanites = new ArrayList<Point>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                int point_x = x + ((2 * c + 1) * w) / (2 * column);
                int point_y = y + ((2 * r + 1) * h) / (2 * row);
                coordanites.add(new Point(point_x, point_y));
            }
        }
        return coordanites;
    }

    //通过package和activity在测试过程启动其他app或者app本身
    public static void startActivity(AndroidDriver<AndroidElement> driver, String appPackage, String appActivity, String appWaitActivity) {
        Activity activity = new Activity(appPackage, appActivity);
        activity.setAppWaitActivity(appWaitActivity);
        driver.startActivity(activity);
    }
}
