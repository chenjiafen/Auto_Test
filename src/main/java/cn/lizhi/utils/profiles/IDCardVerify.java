package cn.lizhi.utils.profiles;

import java.util.regex.Pattern;

/**
 * @Author chenjiafneg
 * @Date 2020/8/18 18:07
 * @Version 1.0
 */
public class IDCardVerify {
    // 18位身份证中，各个数字的生成校验码时的权值
    private static final int[] WI = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    //18位身份证中最后一位校验码
    private static final char[] CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    /**
     *
     * @param card
     * @return
     */
    public static boolean verify(String card) {
        // Pattern.matches("^\\d{15}$", card)意思是字符串card是不是匹配正则表达式  ^\d{15}$
        if (card.length() == 15 && Pattern.matches("^\\d{15}$", card)) {
            card = card15$18(card);
        }
        if (card.length() == 18 && isDate(card)) {
            card = card.toUpperCase();
            if (Pattern.matches("^\\d{17}[xX]|\\d{18}$", card)) {
                char[] chars = card.toCharArray();
                int si = 0;
                for (int i = 0; i < 17; i++) {
                    si += (chars[i] - '0') * WI[i];
                }
                return chars[17] == CODE[si % 11];
            }
            return false;
        }
        return false;
    }

    /**
     *
     * @param card
     * @return
     */
    private static boolean isDate(String card) {
        String y = card.substring(6, 10);
        String m = card.substring(10, 12);
        String d = card.substring(12, 14);
        String date = y + "-" + m + "-" + d;
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        return p.matcher(date).matches();
    }

    /**
     *
     * @param $15
     * @return
     */
    public static String card15$18(String $15) {
        try {
            if ($15.length() == 15) {
                int si = 0;
                StringBuffer $18 = new StringBuffer();

                $18.append($15.substring(0, 6));
                $18.append("19");
                $18.append($15.substring(6, 15));
                for (int i = 0; i < 17; i++) {
                    si += ($18.charAt(i) - '0') * WI[i];
                }
                $18.append(CODE[si % 11]);
                return $18.toString();
            }
        } catch (Exception ex) {
            return null;
        }
        return $15;
    }

    public static void main(String[] args) {
        System.out.println(verify(""));
    }
}
