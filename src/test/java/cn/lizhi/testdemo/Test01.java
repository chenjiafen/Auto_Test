package cn.lizhi.testdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author chenjiafneg
 * @Date 2020/8/18 14:08
 * @Version 1.0
 */
public class Test01 {


    @Test
    public void test001() {
        String s = "helloworld";
        Assert.assertEquals(true, s.contains("hello"));


    }

    @Test
    public void testStringBuffer() {

        StringBuffer sff = new StringBuffer("天虹");
        //追加
        sff.append("www");
        sff.append("1222");
        sff.append("发放");
        System.out.println(sff);

        //翻转
        sff.reverse();
        System.out.println(sff);

        //删除
        sff.delete(0, 1);
        System.out.println(sff);

        //插入
        sff.insert(1, "hello");
        System.out.println(sff);

        sff.replace(1,6,"我很好");
        System.out.println(sff);

        sff.substring(1,5);
        System.out.println(sff);
    }

    public static void main(String[] args) {
        StringBuffer sff = new StringBuffer("天虹");
        sff.append("www");
        sff.append("1222");
        sff.append("发放");
        System.out.println(sff);
//
//        sff.reverse();
//        System.out.println(sff);
        sff.delete(1, 2);
        System.out.println(sff);
    }
}
