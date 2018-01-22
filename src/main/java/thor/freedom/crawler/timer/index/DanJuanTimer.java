package thor.freedom.crawler.timer.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.common.TimerScheduledCron;
import thor.freedom.crawler.biz.timer.DanJuanTimerBiz;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Thor on 2018/1/18.
 */
@Component
public class DanJuanTimer {
    @Autowired
    private DanJuanTimerBiz danJuanBiz;

    /**
     * 每天凌晨00:00:00获取蛋卷基金的常用指数估值数据
     */
    @Scheduled(cron = TimerScheduledCron.CRAWL_INDEX_VALUATION)
    public void crawlIndexValuation() throws IOException, ParseException {
        danJuanBiz.crawlIndexValuation();
    }
}
