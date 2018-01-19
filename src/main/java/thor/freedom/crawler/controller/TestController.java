package thor.freedom.crawler.controller;

import thor.freedom.crawler.biz.timer.DanJuanTimerBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("iv")
    public void iv() throws IOException, ParseException {
        danJuanBiz.crawlIndexValuation();
    }
}
