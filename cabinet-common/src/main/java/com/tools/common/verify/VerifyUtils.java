package com.tools.common.verify;

import com.tools.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: VerifyUtils
 * @Author LinLuoChen
 * @ProjectName RuoYi-Vue-master
 * @Description: 常用验证类
 * @Date 2023/4/7 13:48
 */
public class VerifyUtils {

    //正则校验     如果想要不区分大小写，就在最后一个数组中加入 abcsefghjklmnpqrtuwxy
    private static final String REGEX = "[1-9NY]{1}[1-9]{1}[1-6]{1}[0-9]{5}[0123456789ABCDEFGHJKLMNPQRTUWXYabcsefghjklmnpqrtuwxy]{10}";

    private static final int[] WEIGHT = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    private static final String BASE_CODE_STRING = "0123456789ABCDEFGHJKLMNPQRTUWXY";

    private static final char[] BASE_CODE_ARRAY = BASE_CODE_STRING.toCharArray();

    private static final List<Character> BASE_CODES = new ArrayList<>();
    /**
     * @Description 登记管理部门+机构类别代码
     * @Author LinLuoChen
     * @Date 2023/4/7 13:58
     **/
    private static final List<String> REGIST_CODE = Arrays.asList(
            "11", "12", "13", "19",
            "21", "29",
            "31", "32", "33", "34", "35", "39",
            "41", "49",
            "51", "52", "53", "59",
            "61", "62", "69",
            "71", "72", "79",
            "81", "89",
            "91", "92", "93",
            "A1", "A9",
            "N1", "N2", "N3", "N9",
            "Y1"
    );
    /**
     * @Description 省级行政区划代码
     * @Author LinLuoChen
     * @Date 2023/4/7 13:59
     **/
    private static final List<String> PROVINCE_REGION_CODE = Arrays.asList(
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46",
            "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"
    );

    static {
        for (char c : BASE_CODE_ARRAY) {
            BASE_CODES.add(c);
        }
    }

    /**
     * @Description 验证邮箱
     * @Author LinLuoChen
     * @Date 2023/4/7 13:50
     * @Param [email]
     * @Return boolean
     **/
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
            /*Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();*/
            flag = matcher(check, email);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @Description 验证是否是数字
     * @Author LinLuoChen
     * @Date 2023/4/7 13:52
     * @Param [value]
     * @Return boolean
     **/
    public static boolean isInteger(String value) {
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher m = p.matcher(value);
        boolean isOK = m.find();
        return isOK;
    }

    /**
     * @Description 验证手机号
     * @Author LinLuoChen
     * @Date 2023/4/7 14:16
     * @Param [value]
     * @Return boolean
     **/
    public static boolean isPhone(String value) {
        boolean flag = false;
        String tel = "^1[3,4,5,6,7,8,9][0-9]{9}$";
        flag = matcher(tel, value);
        return flag;
    }

    /**
     * @Description 验证座机号
     * @Author LinLuoChen
     * @Date 2023/4/7 14:16
     * @Param [value]
     * @Return boolean
     **/
    public static boolean isFixedDial(String value) {
        boolean flag = false;
        String tel = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
        flag = matcher(tel, value);
        return flag;
    }

    /**
     * @Description 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     * @Author LinLuoChen
     * @Date 2023/4/7 13:50
     * @Param [userName]
     * @Return boolean
     **/
    public static boolean validateUserName(String userName) {
        String validateStr = "^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
        boolean rs = false;
        rs = matcher(validateStr, userName);
        if (rs) {
            int strLenth = getStrLength(userName);
            if (strLenth < 4 || strLenth > 15) {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * @Description 获取字符串的长度，对双字符（包括汉字）按两位计数 --- 配合用户验证
     * @Author LinLuoChen
     * @Date 2023/4/7 13:51
     * @Param [value]
     * @Return int
     **/
    public static int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * @Description 匹配规则
     * @Author LinLuoChen
     * @Date 2023/4/7 13:55
     * @Param [reg, string]
     * @Return boolean
     **/
    private static boolean matcher(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    /**
     * @Description 效验统一社会信用代码规则
     * @Author LinLuoChen
     * @Date 2023/4/7 13:59
     * @Param [value]
     * @Return boolean
     **/
    public static boolean getCodeValidate(String value) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        // 长度验证
        if (value.length() != 18) {
            return false;
        }
        // 登记管理部门+机构类别代码验证
        if (!REGIST_CODE.contains(value.substring(0, 2))) {
            return false;
        }
        // 省级行政区划代码验证
        if (!PROVINCE_REGION_CODE.contains(value.substring(2, 4))) {
            return false;
        }
        // 正则初验
        if (!Pattern.matches(REGEX, value)) {
            return false;
        }
        // 效验码校验
        char[] businessCodeArray = value.toCharArray();
        String check = Character.toString(businessCodeArray[17]);
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            char key = businessCodeArray[i];
            sum += (BASE_CODES.indexOf(key) * WEIGHT[i]);
        }
        int checkCode = 31 - sum % 31;
        String s = Character.toString(BASE_CODE_ARRAY[checkCode % 31]);
        //对比时将两个字符转为字符串，不区分大小写对比最后一位
        return s.equalsIgnoreCase(check);
    }


}
