package thor.freedom.crawler.biz.timer;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import thor.freedom.crawler.core.OkHttp3Util;

import java.io.IOException;

/**
 * Created by Thor on 2018/5/23.
 */
@Service
public class ItlBugInfoBizImpl implements ItlBugInfoBiz {
    private static final String ZENTAO_UNRESOLVED_BUG_URL = "http://52.80.59.135:8999/zentao/bug-browse-1-0-unresolved-0.html";

    static class BugInfoHeader {
        static Headers getHeaders() {
            return new Headers.Builder().build()
                    .newBuilder()
                    .add(OkHttp3Util.USER_AGENT, OkHttp3Util.getRandomUserAgent())
                    .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .add("Accept-Encoding", "gzip, deflate")
                    .add("Accept-Language", "zh-CN,zh;q=0.9")
                    .add("Connection", "keep-alive")
                    .add("Cookie", "lang=zh-cn; device=desktop; theme=default; keepLogin=on; za=luoxuan; preProductID=1; preBranch=0; lastProduct=1; qaBugOrder=assignedTo_asc; zp=39ab4f0424678e12c9be8f03b964a7fd49238f45; selfClose=1; windowWidth=1279; windowHeight=282; zentaosid=2tjdid1cmnssfk0qca89b7egr0")
                    .add("Host", "52.80.59.135:8999")
                    .add("Referer", "http://52.80.59.135:8999/zentao/bug-browse-1-0-assigntonull-0.html")
                    .add("Upgrade-Insecure-Requests", "1")
                    .add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                    .build();
        }
    }

    @Override
    public void bugInfo() throws IOException {
        Request request = new Request.Builder()
                .url(ZENTAO_UNRESOLVED_BUG_URL)
                .headers(BugInfoHeader.getHeaders())
                .build();

        Response response = OkHttp3Util.okHttpClient().newCall(request).execute();

        ResponseBody responseBody = response.body();

        if (responseBody == null) {
            throw new RuntimeException("禅道数据获取失败");
        }

        String html = responseBody.string();

        Document document = Jsoup.parse(new String(html.getBytes("ISO8859-1"), "UTF-8"));

        System.out.println(document);

        Element content = document.getElementById("bugList");

        System.out.println(content);

        Elements tfoot = content.getElementsByTag("tfoot");

        System.out.println(tfoot);
    }
}
