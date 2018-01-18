package thor.freedom.crawler.bean.dto.timer.index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Thor on 2018/1/18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DataOfDanJuanResultDTO {
    private String current_page;

    private String size;

    private String total_items;

    private String total_pages;

    private List<IndexOfDanJuanDTO> items;
}
