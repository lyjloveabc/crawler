package com.freedom.crawler.bean.dto.timer.index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Thor on 2018/1/18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IndexOfDanJuanDTO {
    private String id;

    private String index_code;

    private String name;

    private String pe;

    private String pb;

    private String pe_percentile;

    private String pb_percentile;

    private String roe;

    private String yeild;

    private String ts;

    private String eva_type;

    private String eva_type_int;

    private String url;

    private String bond_yeild;

    private String begin_at;

    private String created_at;

    private String updated_at;
}
