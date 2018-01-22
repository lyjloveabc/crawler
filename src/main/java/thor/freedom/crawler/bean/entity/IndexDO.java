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
public class IndexDO {
    private Integer id;

    private Date createdTime;

    private Date modifiedTime;

    private String indexCode;

    private String indexName;

    private Byte isFollowed;

    private String dataSource;
}