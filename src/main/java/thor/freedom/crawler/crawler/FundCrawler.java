package thor.freedom.crawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thor.freedom.crawler.bean.common.Result;
import thor.freedom.crawler.biz.crawler.FundCrawlerBiz;
import thor.freedom.crawler.core.ResultUtil;

/**
 * Created by Thor on 2018/1/18.
 */
@RestController
@RequestMapping("fundCrawler")
public class FundCrawler {
    @Autowired
    private FundCrawlerBiz fundCrawlerBiz;

    @GetMapping("luFund")
    public Result<Boolean> luFund() throws Exception {
        return ResultUtil.success(fundCrawlerBiz.luFund());
    }
}
