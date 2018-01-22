package thor.freedom.crawler.biz.crawlerStrategy;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.ObjectUtils;
import thor.freedom.crawler.bean.common.DataSource;
import thor.freedom.crawler.bean.entity.FundDO;
import thor.freedom.crawler.crawler.BaseCrawler;
import thor.freedom.crawler.dao.mapper.FundDOMapper;
import thor.freedom.crawler.sys.SpringContextUtil;

import java.util.Date;

/**
 * Created by Thor on 2018/1/18.
 */
public class FundCrawlerStrategy extends WebCrawler {
    public final static String BASE = "https://e.lufunds.com/jijin/list?subType=&fundGroupId=&currentPage=%s&orderType=twelve_month_increase_desc&canFixInvest=&searchWord=#sortTab";
    public final static String INDEX = String.format(BASE, 1);
    private final static String URL_PRE = "https://e.lufunds.com/jijin/list";

    private FundDOMapper fundDOMapper = (FundDOMapper) SpringContextUtil.getBean(FundDOMapper.class);

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawlerStrategy to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        return !BaseCrawler.FILTERS.matcher(href).matches() && href.startsWith(URL_PRE);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            Document doc = Jsoup.parse(htmlParseData.getHtml());
            Element fundTable = doc.getElementById("fundTable");

            Elements trs = fundTable.getElementsByTag("tbody").get(0).getElementsByTag("tr");

            if (trs != null) {
                Date now = new Date();

                for (Element tr : trs) {
                    String code;
                    Elements tds = tr.getElementsByTag("td");

                    if (tds != null) {
                        if (ObjectUtils.isEmpty(tds.get(0).getElementsByTag("div"))) {
                            code = tds.get(0).text();
                        } else {
                            code = tds.get(0).getElementsByTag("div").get(0).text();
                        }

                        fundDOMapper.insertSelective(
                                FundDO.builder()
                                        .code(code)
                                        .createdTime(now)
                                        .dataSource(DataSource.LU)
                                        .modifiedTime(now)
                                        .name(tds.get(1).getElementsByTag("a").get(0).text())
                                        .build()
                        );
                    }
                }
            }
        }
    }
}
