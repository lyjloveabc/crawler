package thor.freedom.crawler.biz.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thor.freedom.crawler.biz.crawlerStrategy.FundCrawlerStrategy;
import thor.freedom.crawler.crawler.BaseCrawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thor on 2018/1/18.
 */
@Service
public class FundCrawlerBizImpl implements FundCrawlerBiz {
    @Autowired
    private BaseCrawler crawlerUtil;

    @Override
    public Boolean luFund() throws Exception {
        Integer pageTotalNum = 1;

        Document doc = Jsoup.connect(FundCrawlerStrategy.INDEX).get();//获取首页的数据
        Elements pageNumElements = doc.getElementsByClass("pageNum");//获取页码DOM

        Pattern pattern = Pattern.compile("第1页/共(\\d+)页");
        Matcher matcher = pattern.matcher(pageNumElements.get(0).text());
        while (matcher.find()) {
            pageTotalNum = Integer.parseInt(matcher.group(1));
        }

        CrawlController controller = crawlerUtil.getSimpleCrawlController();

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        for (int i = 1; i < pageTotalNum; i++) {
            controller.addSeed(String.format(FundCrawlerStrategy.BASE, i));
        }

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(FundCrawlerStrategy.class, BaseCrawler.MULTI_THREAD_CRAWLER);

        return true;
    }
}
