package com.tools.common.utils.html;

import com.tools.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 转义和反转义工具类
 *
 * @author tools
 */
public class EscapeUtil
{
    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

    private static final char[][] TEXT = new char[64][];

    static
    {
        for (int i = 0; i < 64; i++)
        {
            TEXT[i] = new char[] { (char) i };
        }

        // special HTML characters
        TEXT['\''] = "&#039;".toCharArray(); // 单引号
        TEXT['"'] = "&#34;".toCharArray(); // 双引号
        TEXT['&'] = "&#38;".toCharArray(); // &符
        TEXT['<'] = "&#60;".toCharArray(); // 小于号
        TEXT['>'] = "&#62;".toCharArray(); // 大于号
    }

    /**
     * 转义文本中的HTML字符为安全的字符
     * 
     * @param text 被转义的文本
     * @return 转义后的文本
     */
    public static String escape(String text)
    {
        return encode(text);
    }

    /**
     * 还原被转义的HTML特殊字符
     * 
     * @param content 包含转义符的HTML内容
     * @return 转换后的字符串
     */
    public static String unescape(String content)
    {
        return decode(content);
    }

    /**
     * 清除所有HTML标签，但是不删除标签内的内容
     * 
     * @param content 文本
     * @return 清除标签后的文本
     */
    public static String clean(String content)
    {
        return new HTMLFilter().filter(content);
    }

    /**
     * Escape编码
     * 
     * @param text 被编码的文本
     * @return 编码后的字符
     */
    private static String encode(String text)
    {
        if (StringUtils.isEmpty(text))
        {
            return StringUtils.EMPTY;
        }

        final StringBuilder tmp = new StringBuilder(text.length() * 6);
        char c;
        for (int i = 0; i < text.length(); i++)
        {
            c = text.charAt(i);
            if (c < 256)
            {
                tmp.append("%");
                if (c < 16)
                {
                    tmp.append("0");
                }
                tmp.append(Integer.toString(c, 16));
            }
            else
            {
                tmp.append("%u");
                if (c <= 0xfff)
                {
                    // issue#I49JU8@Gitee
                    tmp.append("0");
                }
                tmp.append(Integer.toString(c, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * Escape解码
     * 
     * @param content 被转义的内容
     * @return 解码后的字符串
     */
    public static String decode(String content)
    {
        if (StringUtils.isEmpty(content))
        {
            return content;
        }

        StringBuilder tmp = new StringBuilder(content.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < content.length())
        {
            pos = content.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (content.charAt(pos + 1) == 'u')
                {
                    ch = (char) Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char) Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(content.substring(lastPos));
                    lastPos = content.length();
                }
                else
                {
                    tmp.append(content.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * @Description 清除所有Map标签，但是不删除标签内的内容
     * @Author LinLuoChen
     * @Date  2023/3/24 16:21
     * @Param [map]
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String,Object> cleanMap(Map<String,Object> map){
        Map<String,Object> newmap = new HashMap<String,Object>();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> ent=it.next();
            String key = ent.getKey();
            Object value = ent.getValue();
            value = clean(value+"");
            newmap.put(key, (Object)value);
        }
        return newmap;
    }

    /**
     * @Description 清除所有Map标签，但是不删除标签内的内容
     * @Author LinLuoChen
     * @Date  2023/3/24 16:21
     * @Param [map]
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String,String> cleanMapString(Map<String,String> map){
        Map<String,String> newmap = new HashMap<String,String>();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> ent=it.next();
            String key = ent.getKey();
            String value = ent.getValue();
            value = clean(value);
            newmap.put(key, value);
        }
        return newmap;
    }


    public static void main(String[] args)
    {
        String html = "<script>alert(1);</script>";
        String escape = EscapeUtil.escape(html);
        // String html = "<scr<script>ipt>alert(\"XSS\")</scr<script>ipt>";
        // String html = "<123";
        // String html = "123>";
        System.out.println("clean: " + EscapeUtil.clean(html));
        System.out.println("escape: " + escape);
        System.out.println("unescape: " + EscapeUtil.unescape(escape));
    }
}
