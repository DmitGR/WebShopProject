package Logic;


import java.security.SecureRandom;
/**
Генерирует случайный код, заданной длины
 */
public class CodeGenerator
{
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static int multiply = 2;

    public static String randomString(int len)
    {
        StringBuilder sb = new StringBuilder(len *= multiply);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}