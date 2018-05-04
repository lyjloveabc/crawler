package thor.freedom.crawler.bean.dto.timer.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Thor on 2018/5/4.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LearnDrivingTimeBlockDTO {
    private Integer code;

    private String msg;

    private List<BlockDTO> response;

    private String systemDate;
}
