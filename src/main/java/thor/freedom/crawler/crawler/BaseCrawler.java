package thor.freedom.crawler.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.core.OkHttp3Util;

import java.util.regex.Pattern;

/**
 * Created by Thor on 2018/1/19.
 */
@Component
public class BaseCrawler {
    @Value("${crawl-storage-folder}")
    private String crawlStorageFolder;

    public final static Integer SINGLETON_CRAWLER = 1;

    public final static Integer MULTI_THREAD_CRAWLER = 10;

    public final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");

    private CrawlConfig getSimpleCrawlConfig() {
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setUserAgentString(OkHttp3Util.getRandomUserAgent());
        config.setIncludeBinaryContentInCrawling(true);

        return config;
    }

    public CrawlController getSimpleCrawlController() throws Exception {
        CrawlConfig config = getSimpleCrawlConfig();

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        return new CrawlController(config, pageFetcher, robotstxtServer);
    }
}
