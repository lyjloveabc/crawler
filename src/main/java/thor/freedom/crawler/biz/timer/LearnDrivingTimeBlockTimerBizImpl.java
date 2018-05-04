package thor.freedom.crawler.biz.timer;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;
import thor.freedom.crawler.bean.dto.timer.car.LearnDrivingTimeBlockDTO;
import thor.freedom.crawler.core.OkHttp3Util;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Thor on 2018/5/4.
 */
@Service
public class LearnDrivingTimeBlockTimerBizImpl implements LearnDrivingTimeBlockTimerBiz {
    private static final String VERSION = "3.05.20";

    private static final String STUDENT_FIND_COACH_DAILY_PLAN = "http://api.zhaojl.cn/qx_client/studentFindCoachDailyPlan.do?version=" + VERSION + "&ts=%s";

    private static final String MEDIA_TYPE_TEXT_CONTENT = "dHMAWrkbkaQdP7+s0lJSXnBJxp2yZDHRJeRqU781xM0FtSjQwlvADk6fzQyQGBnfzUP/enCndnMm\nB4D/BY33oA==";

    class HeadersOfLearnDrivingTimeBlock {
        Headers getHeaders() {
            return new Headers.Builder().build()
                    .newBuilder()
                    .add(OkHttp3Util.USER_AGENT, OkHttp3Util.getRandomUserAgent())
                    .add("citycode", "330100")
                    .add("appId", "com.qx.st.and")
                    .add("sign", "959E3D30D3CD86A788501ADFE701B33FFF256C4227B7CB8390FD1B1A880B6BB")
                    .add("Content-Type", "text/plain")
                    .add("Content-Length", "89")
                    .add("Host", "api.zhaojl.cn")
                    .add("Connection", "Keep-Alive")
                    .add("Accept-Encoding", "gzip")
                    .add("Cookie", "JSESSIONID=E2330269EFA09E90365201B489C9345D")
                    .build();
        }
    }

    @Override
    public void learnDrivingTimeBlock() throws IOException {
        Request request = new Request.Builder()
                .url(String.format(STUDENT_FIND_COACH_DAILY_PLAN, new Date().getTime()))
                .headers(new HeadersOfLearnDrivingTimeBlock().getHeaders())
                .post(RequestBody.create(MediaType.parse("text/plain"), MEDIA_TYPE_TEXT_CONTENT))
                .build();

        Response response = OkHttp3Util.okHttpClient().newCall(request).execute();

        ResponseBody responseBody = response.body();

        if (responseBody == null) {
            throw new RuntimeException("找教练获取预约时间响应失败");
        }

        LearnDrivingTimeBlockDTO learnDrivingTimeBlockDTO = new Gson().fromJson(responseBody.string(), LearnDrivingTimeBlockDTO.class);

        if (learnDrivingTimeBlockDTO == null) {
            throw new RuntimeException("找教练获取预约时间json解析失败");
        }



        System.out.println(":" + learnDrivingTimeBlockDTO);
    }

}
