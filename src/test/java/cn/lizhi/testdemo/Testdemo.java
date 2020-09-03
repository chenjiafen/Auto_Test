package cn.lizhi.testdemo;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @Author chenjiafneg
 * @Date 2020/8/11 22:42
 * @Version 1.0
 */
public class Testdemo {
    /**
     * 在test suite中的所有test运行之前运行，只运行一次
     */
    @BeforeSuite
    public void test001(){
        System.out.println("test001");
    }

    /**
     * 在xml中的每一个test标签执行前运行
     */
    @BeforeTest
    public void test002(){
        System.out.println("test002");
    }

    /**
     * 在当前类的所有测试方法之前执行
     */
    @BeforeClass
    public void test003(){
        System.out.println("test003");
    }

    /**
     * BeforeMethod,AfterMethod每个测试类执行都会去执行
     */
    @BeforeMethod
    public void test004(){
        System.out.println("test004");
    }

    @Test(priority = 3)
    public void test(){
        System.out.println("testdemo");
    }
    //priority执行顺序
    @Test(priority = 2)
    public void add(){
        int a=10;
        int b=20;
        int sum=a+b;
        //实际值是sum，期望值是31,断言失败不执行打印
        Assert.assertEquals(sum,31);
        System.out.println("test");
    }

    //enabled = false 忽略测试
    @Test(priority = 1,enabled = false)
    public void fail(){
        int a=1;
        int b=3;
        Assert.fail("强制置失败");//强制置为失败
        System.out.println(a+b);
    }

    @AfterMethod
    public void test005(){
        System.out.println("testdemo005");
    }

    /**
     * 在当前类中的所有测试方法之后执行
     */
    @AfterClass
    public void test006(){
        System.out.println("testdemo006");
    }

    /**
     * 在xml中的每一个test标签执行后运行
     */
    @AfterTest
    public void test007(){
        System.out.println("testdemo007");
    }

    /**
     * 在test suite中的所有test运行之后运行，只运行一次
     */
    @AfterSuite
    public void test008(){
        System.out.println("testdemo008");
    }
}
