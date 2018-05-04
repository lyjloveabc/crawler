package thor.freedom.crawler.bean.common;

/**
 * Created by Thor on 2018/1/18.
 */
public interface TimerScheduledCron {
    String CRAWL_INDEX_VALUATION = "0 0 0 * * ?";

    String INDEX_VALUATION_WARN = "0 0 9 * * ?";

    //String LEARN_DRIVING_TIME_BLOCK = "0 0/1 * * * ?";
    String LEARN_DRIVING_TIME_BLOCK = "0/10 * * * * ?";
}
