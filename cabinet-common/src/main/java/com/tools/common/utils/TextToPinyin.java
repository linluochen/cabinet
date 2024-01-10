package com.tools.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Title: TextToPinyin
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 中文汉字转拼音
 * @Date 2023/5/10 9:26
 */
public class TextToPinyin {

    /**
     * @Description 1.得到中文全拼
     * @Author LinLuoChen
     * @Date 2023/5/10 9:26
     * @Param [
     * src ： 需要转换的文字
     * ]
     * @Return java.lang.String
     **/
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder t4 = new StringBuilder();
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4.append(t2[0]);
                } else {
                    t4.append(Character.toString(t1[i]));
                }
            }
            return t4.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4.toString();
    }

    /**
     * @Description 2.得到中文首字母缩写
     * @Author LinLuoChen
     * @Date 2023/5/10 9:26
     * @Param [
     * str：需要转换的中文字符
     * ]
     * @Return java.lang.String
     **/
    public static String getPinYinHeadChar(String str) {
        StringBuilder convert = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            } else {
                convert.append(word);
            }
        }
        return convert.toString();
    }

    /**
     * @Description 3.中文首字母并把转化为大写字母
     * @Author LinLuoChen
     * @Date 2023/5/10 9:26
     * @Param [
     * str：需要获取的字符
     * ]
     * @Return java.lang.String
     **/
    public static char getPinYinHeadToInitial(String str) {
        String pinyin = getPinYinHeadChar(str);
        StringBuilder sb = new StringBuilder(pinyin);
        String initial = "";
        if (sb.length() > 1) {
            initial = sb.delete(1, sb.length()).toString();
            return Character.toUpperCase(initial.toCharArray()[0]);
        }
        System.out.println("首字母为空");
        return Character.toUpperCase(initial.toCharArray()[0]);
    }

    // 测试方法
    public static void main(String[] args) {
        // 1.得到中文全拼
        System.out.println("中文全拼:" + getPinYin("山东省"));
        // 2.得到中文首字母缩写
        System.out.println("中文首字母缩写:" + getPinYinHeadChar("山东省"));
        // 3.中文首字母并把转化为大写字母
        System.out.println("中文首字母并把转化为大写字母:" + getPinYinHeadToInitial("山东省"));
    }

}
