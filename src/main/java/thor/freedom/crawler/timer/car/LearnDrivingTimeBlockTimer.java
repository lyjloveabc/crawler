package thor.freedom.crawler.timer.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.common.TimerScheduledCron;
import thor.freedom.crawler.biz.timer.LearnDrivingTimeBlockTimerBiz;

import java.io.IOException;

/**
 * Created by Thor on 2018/5/4.
 */
//@Component
public class LearnDrivingTimeBlockTimer {
    @Autowired
    private LearnDrivingTimeBlockTimerBiz learnDrivingTimeBlock;

    @Scheduled(cron = TimerScheduledCron.LEARN_DRIVING_TIME_BLOCK)
    public void learnDrivingTimeBlock() throws IOException {
        learnDrivingTimeBlock.learnDrivingTimeBlock();
    }
}
