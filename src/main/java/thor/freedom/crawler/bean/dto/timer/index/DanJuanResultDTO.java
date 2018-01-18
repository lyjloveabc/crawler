package thor.freedom.crawler.bean.dto.timer.index;

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
public class DanJuanResultDTO {
    private String result_code;

    private DataOfDanJuanResultDTO data;
}
