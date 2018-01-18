package com.freedom.crawler.biz.danjuanapp;

import com.freedom.crawler.bean.dto.timer.index.DanJuanResultDTO;
import com.freedom.crawler.bean.dto.timer.index.IndexOfDanJuanDTO;
import com.freedom.crawler.bean.entity.IndexValuationDO;
import com.freedom.crawler.core.DateUtil;
import com.freedom.crawler.core.OkHttp3Util;
import com.freedom.crawler.dao.mapper.IndexValuationDOMapper;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created by Thor on 2018/1/18.
 */
@Service
public class DanJuanBizImpl implements DanJuanBiz {
    private static final String SOURCE = "蛋卷基金";
    private static final String SOURCE_URL = "https://danjuanapp.com";

    private static final String INDEX_VALUATION_URL = "https://danjuanapp.com/djapi/index_eva/dj";

    @Autowired
    private IndexValuationDOMapper indexValuationDOMapper;

    @Override
    public void crawlIndexValuation() throws IOException, ParseException {
        ResponseBody responseBody = OkHttp3Util.simpleGet(INDEX_VALUATION_URL);

        if (responseBody == null) {
            throw new RuntimeException("蛋卷基金估值响应失败");
        }

        DanJuanResultDTO danJuanResultDTO = new Gson().fromJson(responseBody.string(), DanJuanResultDTO.class);

        if (danJuanResultDTO == null) {
            throw new RuntimeException("蛋卷基金估值json解析失败");
        }

        Integer today = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        List<IndexValuationDO> todayList = indexValuationDOMapper.selectByRequestDay(today);

        if (ObjectUtils.isEmpty(todayList) && danJuanResultDTO.getData() != null) {
            Date now = new Date();

            for (IndexOfDanJuanDTO item : danJuanResultDTO.getData().getItems()) {
                indexValuationDOMapper.insertSelective(
                        IndexValuationDO.builder()
                                .beginAt(item.getBegin_at())
                                .beginAtStr(DateUtil.dateToString(DateUtil.timestampToDate(Long.parseLong(item.getBegin_at())), "yyyy-MM-dd"))
                                .bondYeild(item.getBond_yeild())
                                .createdTime(now)
                                .dataSource(SOURCE)
                                .evaType(item.getEva_type())
                                .evaTypeInt(item.getEva_type_int())
                                .indexCode(item.getIndex_code())
                                .indexName(item.getName())
                                .modifiedTime(now)
                                .pb(item.getPb())
                                .pbPercentile(item.getPb_percentile())
                                .pe(item.getPe())
                                .pePercentile(item.getPe_percentile())
                                .requestDay(today)
                                .roe(item.getRoe())
                                .url(item.getUrl())
                                .yeild(item.getYeild())
                                .build()
                );
            }
        }
    }

}
