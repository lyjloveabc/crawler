package thor.freedom.crawler.core;

import java.util.UUID;

/**
 * Created by Thor on 2018/1/18.
 */
public class UniqueUtil {
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String uuid32() {
        return uuid().replaceAll("-", "");
    }
}
