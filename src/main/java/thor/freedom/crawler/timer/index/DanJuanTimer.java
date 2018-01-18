package thor.freedom.crawler.timer.index;

import thor.freedom.crawler.bean.common.TimerScheduledCron;
import thor.freedom.crawler.biz.danjuanapp.DanJuanBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Thor on 2018/1/18.
 */
@Component
@Slf4j
public class DanJuanTimer {
    @Autowired
    private DanJuanBiz danJuanBiz;

    /**
     * 每天凌晨00:00:00获取蛋卷基金的常用指数估值数据
     */
    @Scheduled(cron = TimerScheduledCron.CRAWL_INDEX_VALUATION)
    @Transactional
    public void crawlIndexValuation() throws IOException, ParseException {
        danJuanBiz.crawlIndexValuation();
    }

}
