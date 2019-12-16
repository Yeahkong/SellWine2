package com.hzxy.common.utils;

import java.util.regex.Pattern;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:41
 * @Description:
 */
public class MobileUtils {

    private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" }; //定义移动端请求的所有可能类型
    /**
     * 判断User-Agent 是不是来自于手机
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if(ua!=null||!ua.equals("")){
            if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
// 排除 苹果桌面系统
                if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                    for (String item : agent) {
                        if (ua.contains(item)) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean siMobileOrNo(String mobiles) {
        if (Pattern.matches("^[1][3-9][0-9]{9}$", mobiles)) {
            return true;
        }
        return false;
    }
}
