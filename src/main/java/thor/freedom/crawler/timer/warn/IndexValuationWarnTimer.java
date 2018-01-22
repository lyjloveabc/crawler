package thor.freedom.crawler.timer.warn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.common.TimerScheduledCron;
import thor.freedom.crawler.biz.timer.IndexValuationWarnTimerBiz;

/**
 * 指数估值报警器
 * Created by Thor on 2018/1/19.
 */
@Component
public class IndexValuationWarnTimer {
    @Autowired
    private IndexValuationWarnTimerBiz indexValuationWarnTimerBiz;

    @Scheduled(cron = TimerScheduledCron.INDEX_VALUATION_WARN)
    public void indexValuationWarn() {
        indexValuationWarnTimerBiz.indexValuationWarn();
    }
}
