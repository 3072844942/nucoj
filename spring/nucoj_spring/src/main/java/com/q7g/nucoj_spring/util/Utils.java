package com.q7g.nucoj_spring.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.q7g.nucoj_spring.enums.FilePathEnum.JUDGE;

/**
 * 自定义工具类
 */
public class Utils {
    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;

    /**
     * 访问url
     */
    @Value("${upload.local.url}")
    private String localUrl;

    /**
     * 将时间类型转换为字符串
     * @param date 时间
     * @return yyyy-MM-dd hh:mm:ss
     */
    public static String getDate(Long date) {
        Date time = new Date(date);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(time);
    }

    /**
     * 系统当前时间
     * @return yyyy-MM-dd hh:mm:ss
     */
    public static String now() {
        Date date = new Date();
        return getDate(date.getTime());
    }

    /**
     * 获取随机数字串
     * @param length 长度
     * @return 数字串
     */
    public static String getRandCode(int length) {
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i ++ ) {
            res.append(random.nextInt(10));
        }
        return res.toString();
    }

    /**
     * 检测邮箱是否合法
     *
     * @param email 用户名
     * @return 合法状态
     */
    public static boolean checkEmail(String email) {
        String rule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(rule);
        //正则表达式的匹配器
        Matcher m = p.matcher(email);
        //进行正则匹配
        return m.matches();
    }
}
