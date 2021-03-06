package thor.freedom.crawler.core;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Thor on 2018/1/18.
 */
@Slf4j
public class OkHttp3Util {
    public static final String USER_AGENT = "User-Agent";

    public static final List<String> USER_AGENT_LIST = Lists.newArrayList(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:57.0) Gecko/20100101 Firefox/57.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/604.3.5 (KHTML, like Gecko) Version/11.0.1 Safari/604.3.5",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.3 Mobile/14E277 Safari/603.1.30",
            "Mozilla/5.0 (iPod; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.3 Mobile/14E277 Safari/603.1.30",
            "Mozilla/5.0 (iPad; CPU OS 10_3 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.3 Mobile/14E277 Safari/603.1.30",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 6.3; Win64, x64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:52.0) Gecko/20100101 Firefox/52.0"
    );

    public static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public static ResponseBody simpleGet(String url) throws IOException {
        log.info("simpleGet: " + url);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();//获取客户端

        Request request = new Request.Builder()
                .headers(OkHttp3Util.getRandomUserAgentHeaders())
                .url(url)
                .build();//构造请求

        Response response = okHttpClient.newCall(request).execute();//执行请求，获得响应

        if (response == null) {
            throw new RuntimeException(String.format("URL [%s] error: the response is null", url));
        }

        return response.body();
    }

    public static ResponseBody simplePost(String url) throws IOException {
        return simplePost(url, new FormBody.Builder().build());
    }

    public static ResponseBody simplePost(String url, RequestBody requestBody) throws IOException {
        log.info("simplePost: " + url);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();//获取客户端

        Request request = new Request.Builder()
                .headers(OkHttp3Util.getRandomUserAgentHeaders())
                .url(url)
                .post(requestBody)
                .build();//构造请求

        Response response = okHttpClient.newCall(request).execute();//执行请求，获得响应

        if (response == null) {
            throw new RuntimeException(String.format("URL [%s] error: the response is null", url));
        }

        return response.body();
    }

    public static ResponseBody post(String url, Headers headers) throws IOException {
        return post(url, headers, new FormBody.Builder().build());
    }

    public static ResponseBody post(String url, Headers headers, RequestBody requestBody) throws IOException {
        log.info("post: " + url);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();//获取客户端

        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .post(requestBody)
                .build();//构造请求

        Response response = okHttpClient.newCall(request).execute();//执行请求，获得响应

        if (response == null) {
            throw new RuntimeException(String.format("URL [%s] error: the response is null", url));
        }

        return response.body();
    }

    /**
     * 获取一个简单的请求头
     *
     * @return 设置了随机UA的请求头
     */
    public static Headers getRandomUserAgentHeaders() {
        return OkHttp3Util.setRandomUserAgent(new Headers.Builder().build());
    }

    /**
     * 给请求头设置一个随机的UA
     *
     * @param headers 请求头
     * @return 设置好UA的请求头
     */
    private static Headers setRandomUserAgent(Headers headers) {
        Headers.Builder builder = headers.newBuilder();
        builder.add(USER_AGENT, getRandomUserAgent());
        return builder.build();
    }

    /**
     * 获取一个随机的UA
     *
     * @return 随机UA
     */
    public static String getRandomUserAgent() {
        return USER_AGENT_LIST.get(RandomUtil.get(0, USER_AGENT_LIST.size() - 1));
    }
}
