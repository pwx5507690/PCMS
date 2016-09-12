package com.pcms.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

    private StringUtil() {
    }

    public static final String US_ASCII = "US-ASCII";

    public static final String ISO_8859_1 = "ISO-8859-1";

    public static final String UTF_8 = "UTF-8";

    public static final String UTF_16BE = "UTF-16BE";

    public static final String UTF_16LE = "UTF-16LE";

    public static final String UTF_16 = "UTF-16";

    public static final String GBK = "GBK";

    private static final char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖',
        '昔', '压', '匝', '座'};
    private static final char[] alphatable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private int[] table = new int[chartable.length];

    {
        for (int i = 0; i < chartable.length; ++i) {
            table[i] = gbValue(chartable[i]);
        }
    }

    /**
     * Md5加密
     *
     * @param str 字符串
     * @return 加密后字符串
     */
    public static final String getMd5(String string) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String s = null;
        if (isNotEmpty(string)) {
            try {
                byte[] strTemp = string.getBytes();
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                s = new String(str);
            } catch (NoSuchAlgorithmException e) {
                s = null;
            }
        }
        return s;
    }

    /**
     * 填充左边字符
     *
     * @param source 源字符串
     * @param fill 填充字符
     * @param len 填充到的长度
     * @return 填充后的字符串
     */
    public static final String fillLeft(String source, char fill, int len) {
        StringBuffer ret = new StringBuffer();
        if (null == source) {
            ret.append("");
        }
        if (source.length() > len) {
            ret.append(source);
        } else {
            int slen = source.length();
            while (ret.toString().length() + slen < len) {
                ret.append(fill);
            }
            ret.append(source);
        }
        return ret.toString();
    }

    /**
     * 填充右边字符
     *
     * @param source 源字符串
     * @param fill 填充字符
     * @param len 填充到的长度
     * @return 填充后的字符串
     */
    public static final String filRight(String source, char fill, int len) {
        StringBuffer ret = new StringBuffer();
        if (null == source) {
            ret.append("");
        }
        if (source.length() > len) {
            ret.append(source);
        } else {
            ret.append(source);
            while (ret.toString().length() < len) {
                ret.append(fill);
            }
        }
        return ret.toString();
    }

    public static final String check(String str) {
        StringBuilder error = new StringBuilder();
        if (isNotBlank(str)) {
            char c;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (c == '"') {
                    error.append(" 特殊字符[\"]");
                }
                if (c == '\'') {
                    error.append(" 特殊字符[']");
                }
                if (c == '<') {
                    error.append(" 特殊字符[<]");
                }
                if (c == '>') {
                    error.append(" 特殊字符[>]");
                }
                if (c == '&') {
                    error.append(" 特殊字符[&]");
                }
                if (c == '%') {
                    error.append(" 特殊字符[%]");
                }
                if (c == ':' || c == '：') {
                    error.append(" 特殊字符[:]");
                }
                if (c == '*') {
                    error.append(" 特殊字符[*]");
                }
                if (c == '|') {
                    error.append(" 特殊字符[|]");
                }
            }
        }
        return error.toString();
    }


    public static final String isEmptyToDefault(String str, String define) {
        String s = "";
        if (isEmpty(str)) {
            s = define;
        }
        return s;
    }

    /**
     * 检测字符是否为空,为空的时候返回提示
     *
     * @param str 字符串
     * @param define 为空的时候返回提示
     * @return 默认字符串
     */
    public static final String isBlankToDefault(String str, String define) {
        String s = "";
        if (isBlank(str)) {
            s = define;
        }
        return s;
    }

    /**
     * 修改编码
     *
     * @param str 字符串
     * @param oldCharset 老编码
     * @param newCharset 新编码
     * @return 修改后的字符串
     */
    public static final String changeCharset(String str, String oldCharset, String newCharset) {
        String s = "";
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = null;
            try {
                if (isNotEmpty(oldCharset)) {
                    bs = str.getBytes(oldCharset);
                } else {
                    bs = str.getBytes();
                }
            } catch (UnsupportedEncodingException e) {
                s = null;
            }
            // 用新的字符编码生成字符串
            try {
                s = new String(bs, newCharset);
            } catch (Exception e) {
                s = null;
            }
        }
        return s;
    }


    public static final boolean isEmptyAll(Object... args) {
        boolean flag = true;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (!isEmpty((String) args[i])) {
                        flag = false;
                    }
                } else if (null != args[i]) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 检测是否所有参数为空
     *
     * @param args 参数
     * @return true:有 false:无
     */
    public static final boolean isBlankAll(Object... args) {
        boolean flag = true;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (!isBlank((String) args[i])) {
                        flag = false;
                    }
                } else if (null != args[i]) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 检测是否所有参数都不为空
     *
     * @param args 参数
     * @return true:是 false:否
     */
    public static final boolean isNotEmptyAll(Object... args) {
        boolean flag = true;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (isEmpty((String) args[i])) {
                        flag = false;
                    }
                } else if (null == args[i]) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 检测是否所有参数都不为空
     *
     * @param args 参数
     * @return true:是 false:否
     */
    public static final boolean isNotBlankAll(Object... args) {
        boolean flag = true;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (isBlank((String) args[i])) {
                        flag = false;
                    }
                } else if (null == args[i]) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 检测是否有至少一个参数为空
     *
     * @param args 参数
     * @return true:有 false:无
     */
    public static final boolean isEmptyOne(Object... args) {
        boolean flag = false;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (isEmpty((String) args[i])) {
                        flag = true;
                    }
                } else if (null == args[i]) {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测是否有至少一个参数为空
     *
     * @param args 参数
     * @return true:有 false:无
     */
    public static final boolean isBlankOne(Object... args) {
        boolean flag = false;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    if (isBlank((String) args[i])) {
                        flag = true;
                    }
                } else if (null == args[i]) {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 输入中文得到每个字拼音的第一个字母
     *
     * @param str 中文
     * @return 每个字拼音的第一个字母字符串
     */
    public static final String getString2Alpha(String str) {
        String result = "";
        int StrLength = str.length();
        int i;
        StringUtil su = new StringUtil();
        try {
            for (i = 0; i < StrLength; i++) {
                result += su.getChar2Alpha(str.charAt(i));
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    /**
     * 获取字符串长度, 包含中文
     *
     * @param str
     * @return
     */
    public static final int getLength(String str) {
        int sum = 0;
        if (isNotBlank(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (isChinese(str.substring(i, i + 1))) {
                    sum = sum + 2;
                } else {
                    sum = sum + 1;
                }
            }
        }
        return sum;
    }

    /**
     * 检测是否是汉字, 一个字
     *
     * @param str
     * @return
     */
    public static final boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4E00-\u9FA5\uF900-\uFA2D]");
        Matcher matcher = pattern.matcher(str);
        String perfix = "，。‘；【】）（……￥！~·“：《》？";
        return matcher.matches() || perfix.contains(str);
    }

    /**
     * 检测输入字母
     *
     * @param str
     * @return
     */
    public static final boolean isChar(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 验证邮箱地址
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * *
     * 验证手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobileNO(String str) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 检测输入数字
     *
     * @param str
     * @return
     */
    public static final boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 检测输入字母数字
     *
     * @param str
     * @return
     */
    public static final boolean isCharAndNum(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 电话区号转换
     */
    public static final String mobile(String contel) {
        StringBuffer stringBuffer = new StringBuffer(contel);
        String mobile = contel.substring(0, 2);
        if (mobile.equals("01") || mobile.equals("02")) {
            contel = stringBuffer.insert(3, "-").toString();
        } else {
            contel = stringBuffer.insert(4, "-").toString();
        }

        return contel;
    }

    /**
     * 得到输入字符的声母,
     *
     * @param ch 输入的字符
     * @return 声母
     */
    private char getChar2Alpha(char ch) {
        if ('a' <= ch && 'z' >= ch) {
            return (char) (ch - 'a' + 'A');
        }
        if ('A' <= ch && 'Z' >= ch) {
            return ch;
        }
        int gb = gbValue(ch);
        if (table[0] > gb) {
            return '0';
        }
        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb)) {
                break;
            }
        }
        if (26 <= i) {
            return '0';
        } else {
            return alphatable[i];
        }
    }

    private boolean match(int i, int gb) {
        if (table[i] > gb) {
            return false;
        }
        int j = i + 1;
        while (26 > j && (table[j] == table[i])) {
            ++j;
        }
        if (26 == j) {
            return gb <= table[j];
        } else {
            return gb < table[j];
        }
    }

    /**
     * 取出中文字编码
     *
     * @param ch 中文字
     * @return
     */
    private int gbValue(char ch) {
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes(GBK);
            if (2 > bytes.length) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
