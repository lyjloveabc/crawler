package thor.freedom.crawler.controller;

import thor.freedom.crawler.biz.timer.DanJuanTimerBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thor.freedom.crawler.biz.timer.IndexValuationWarnTimerBiz;
import thor.freedom.crawler.biz.timer.ItlBugInfoBiz;
import thor.freedom.crawler.timer.warn.IndexValuationWarnTimer;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Thor on 2018/1/18.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private DanJuanTimerBiz danJuanBiz;

    @Autowired
    private IndexValuationWarnTimerBiz indexValuationWarn;

    @Autowired
    private ItlBugInfoBiz itlBugInfoBiz;

    @GetMapping("iv")
    public void iv() throws IOException, ParseException {
        danJuanBiz.crawlIndexValuation();
    }

    @GetMapping("warn")
    public void warn() {
        indexValuationWarn.indexValuationWarn();
    }

    @GetMapping("bug")
    public void bug() throws IOException {
        itlBugInfoBiz.bugInfo();
    }
}
