package Logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Генерирует MD5 хэш
 */
public class HashCodeGen
{
    public static String MD5(String source)
    {
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte anArray : array)
            {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}