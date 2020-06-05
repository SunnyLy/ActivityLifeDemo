package ext.sunny.com.activitylifedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/4/29
 * @Version V1.0.0
 */
public class Test {

    public static void main(String[] args) {
        String var1 = "Hello";
        String var2 = "World";
        boolean result = (var1 == var2);
        String str1 = "result="+result;
        String str2 = str1 + ",hello";
        System.out.println(str2);
    }

    public static class Sunny{
        public static String name;
    }
}
