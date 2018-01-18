package thor.freedom.crawler.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IndexValuationDO {
    private Integer id;

    private Date createdTime;

    private Date modifiedTime;

    private Integer requestDay;

    private String indexCode;

    private String indexName;

    private String pe;

    private String pb;

    private String pePercentile;

    private String pbPercentile;

    private String roe;

    private String yeild;

    private String evaType;

    private String evaTypeInt;

    private String url;

    private String bondYeild;

    private String beginAt;

    private String beginAtStr;

    private String dataSource;
}