package thor.freedom.crawler.core;

/**
 * Created by Thor on 2018/1/18.
 */
public class PrintUtil {
    /**
     * 打印1个空行
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * 打印n个空行
     *
     * @param num 空行的个数
     */
    public static void printEmptyLine(Integer num) {
        if (null != num && num > 0) {
            for (int i = 0; i < num; i++) {
                System.out.println();
            }
        }
    }
}
