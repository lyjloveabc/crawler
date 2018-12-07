package thor.freedom.crawler.timer.youku;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.common.TimerScheduledCron;
import thor.freedom.crawler.core.OkHttp3Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 优酷签到
 * Created by Thor on 2018/6/17.
 */
@Component
public class YkSignInTimer {
    private static final String YK_URL = "aHR0cDovLzIwMTh4Lndpbi9mb3J1bS5waHA=";

    static class BugInfoHeader {
        static Headers getHeaders() throws UnsupportedEncodingException {
            String host = new String(Base64.getDecoder().decode("MjAxOHgud2lu"), "UTF-8");

            return new Headers.Builder().build()
                    .newBuilder()
                    .add(OkHttp3Util.USER_AGENT, OkHttp3Util.getRandomUserAgent())
                    .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .add("Accept-Encoding", "gzip, deflate")
                    .add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                    .add("Cache-Control", "max-age=0")
                    .add("Connection", "keep-alive")
                    .add("Cookie", "__cfduid=de491d3560dccf4b8eb5f487a6cda92ff1529232442; mH69_2132_saltkey=Zc99s98F; mH69_2132_lastvisit=1529228780; mH69_2132_sid=p45883; mH69_2132_lastact=1529238111%09home.php%09misc; guestview_7ree=4; mH69_2132_st_p=0%7C1529237590%7C4671138537915bd2dd2b886be108ba02; mH69_2132_viewid=tid_1315529; mH69_2132_ulastactivity=d4a01cdJs0hhQLk3k1HYZTpP3YXI3xZlAQ%2B2RP3gp9j3UuB%2Bw%2Fqd; mH69_2132_auth=19bfXt6bCEbFVPykISVHo3B%2FlGZg6oEcZXwr%2Fpx%2BCwz5UMoLqbKZMy5CdD6cM8aeUnsIUYRcZtZS1XerGE1%2BgC3Ts6dl; mH69_2132_lastcheckfeed=1448839%7C1529238109; mH69_2132_lip=115.192.187.163%2C1529105351; mH69_2132_nofavfid=1; mH69_2132_sendmail=1")
                    .add("Host", host)
                    .add("Referer", "1")
                    .add("Upgrade-Insecure-Requests", "1")
                    .add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:60.0) Gecko/20100101 Firefox/60.0")
                    .build();
        }
    }

    @Scheduled(cron = TimerScheduledCron.YK_SIGN_IN)
    public void ykSignIn() throws IOException {
        Request request = new Request.Builder()
                .url(new String(Base64.getDecoder().decode(YK_URL), "UTF-8"))
                .headers(YkSignInTimer.BugInfoHeader.getHeaders())
                .build();

        Response response = OkHttp3Util.okHttpClient().newCall(request).execute();

        ResponseBody responseBody = response.body();

        if (responseBody == null) {
            throw new RuntimeException("优酷数据获取失败");
        }

        String html = responseBody.string();

        /*Document document = Jsoup.parse(new String(html.getBytes("ISO8859-1"), "UTF-8"));

        System.out.println(document);

        Element content = document.getElementById("bugList");

        System.out.println(content);

        Elements tfoot = content.getElementsByTag("tfoot");

        System.out.println(tfoot);*/
        System.out.println(html);
    }

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("http://2018x.win/forum.php".getBytes()));
    }

}
