package thor.freedom.crawler.core;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.util.List;

/**
 * Created by Thor on 2018/1/18.
 */
@Slf4j
public class Md5Util {
    /**
     * MD5加密
     *
     * @param before 原始值
     * @return 加密后的值
     */
    public static String MD5(String before) {
        if (StringUtils.isEmpty(before)) {
            return "";
        }

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // 使用指定的字节更新摘要
            mdInst.update(before.getBytes());
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            char str[] = new char[md.length * 2];
            int k = 0;
            for (byte item : md) {
                str[k++] = hexDigits[item >>> 4 & 0xf];
                str[k++] = hexDigits[item & 0xf];
            }

            return String.valueOf(str).toLowerCase();
        } catch (Exception e) {
            log.info("MD5加密失败, param:" + before);
            return before;
        }
    }

    public static void main(String[] args) {
        List<String> nums = Lists.newArrayList(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        );

        List<String> ens = Lists.newArrayList(
                "A", "B", "C", "D", "E", "F", "G", "H"
        );

        for (int i = 0; i < 27; i++) {
            StringBuilder ranNum = new StringBuilder();

            for (int j = 0; j < 6; j++) {
                ranNum.append(nums.get(RandomUtil.get(0, nums.size() - 1)));
            }

            String key = ranNum.toString() + ens.get(RandomUtil.get(0, ens.size() - 1));

            System.out.println(key + ": " + MD5(key));
        }
    }
}
