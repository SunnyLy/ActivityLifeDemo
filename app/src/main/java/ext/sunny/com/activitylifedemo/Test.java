package ext.sunny.com.activitylifedemo;


import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/4/29
 * @Version V1.0.0
 */
public class Test {

    public static void main(String[] args) {
//        String var1 = "Hello";
//        String var2 = "World";
//        boolean result = (var1 == var2);
//        String str1 = "result="+result;
//        String str2 = str1 + ",hello";
//        System.out.println(str2);

        try {
            DESKeySpec localObject1 = new DESKeySpec("Captur3Th1s".getBytes());
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)localObject1);
            byte[] decData = Base64.decode("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ==", 0);
            Cipher localCipher = Cipher.getInstance("DES");
            localCipher.init(2, secretKey);
            String decryptString = new String(localCipher.doFinal(decData));
            System.out.println("解密数据："+decryptString);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

    }

    public static class Sunny{
        public static String name;
    }
}
