package cc.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author pengjun
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 空字符串
     */
    private static final String NULLSTR = "";


    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE;

    /**
     * 正则表达式:验证座机
     */
    public static final String REGEX_TELPHONE;

    /**
     * 邮箱格式验证
     */
    public static final String REGEX_EMAIL;

    static {
        REGEX_MOBILE = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$";
        REGEX_TELPHONE = "^(\\+\\d{2}-)?0\\d{2,3}-\\d{7,8}$";
        REGEX_EMAIL = "^*+@*+.(com|cn)(.com|.cn)?$";
    }

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     *                * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }



    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }




    /**
     * 校验手机号
     *
     * @param mobile 手机号
     * @return 校验通过返回true，否则返回false
     * @author zhangkaichuan
     * @date 2019/02/24
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /** 固话
     * 校验通过返回true，否则返回false
     * @param mobile
     * @return
     */
    public static boolean isTelMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(REGEX_TELPHONE, mobile);
    }


    /**
     * 验证字符串是否为数字
     *
     * @param code 验证码
     */
    public static boolean isNumber(String code) {
        if (Objects.isNull(code))
            return false;
        try {
            Integer.parseInt(code);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 检查用户名
     *
     * @param username
     * @return
     */
    public static Boolean checkUsername(String username) {
        if (isEmpty(username) && (username.length() > 20 || username.length() < 6))
            return false;
        if (checkWords(username.substring(0, 1), "[a-zA-Z]$").length() < 1)
            return false;
        return checkWords(username, "^[a-zA-Z0-9_]{6,20}$").length() > 0;
    }


    /**
     * 检查字母
     *
     * @param words
     * @return
     */
    public static Boolean checkWord(String words) {
        if (isEmpty(words))
            return false;
        assert words != null;
        return checkWords(words, "^[a-zA-Z0-9]").length() > 0;
    }

    /**
     * 检查中文
     *
     * @param words
     * @return
     */
    public static Boolean checkChinaWord(String words) {
        if (isEmpty(words))
            return false;
        assert words != null;
        return checkWords(words, "^[\\u4e00-\\u9fa5]").length() > 0;
    }

    //匹配汉字和英文
    public static String checkWords(String paramValue, String regex) {
        if (isEmpty(regex))
            regex = "^[a-zA-Z0-9\\u4E00-\\u9FA5]*";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }

    /**
     * 检查是否包含特殊字符
     *
     * @param words
     * @return
     */
    public static Boolean checkSpecialWord(String words, String special) {
        if (isEmpty(words))
            return false;
        return !(words.split(special).length > 1);
    }


    public static String removeLastChar(String s, String s1) {
        if(s.endsWith(s1)){
            int i = s.lastIndexOf(s1);
            return s.substring(0, i);

        }
        return s;
    }
}