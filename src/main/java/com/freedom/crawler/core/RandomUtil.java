package com.freedom.crawler.core;

/**
 * Created by Thor on 2018/1/18.
 */
public class RandomUtil {
    private static final Integer RANDOM_MIN = 1111;
    private static final Integer RANDOM_MAX = 9999;

    /**
     * 获取随机数，使用默认区间
     *
     * @return int随机数
     */
    public static int get() {
        return get(RANDOM_MIN, RANDOM_MAX);
    }

    /**
     * 获取随机数，指定最大最小值（前后闭区间）
     *
     * @param min 最小值
     * @param max 最大值
     * @return int随机数
     */
    public static int get(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

}
