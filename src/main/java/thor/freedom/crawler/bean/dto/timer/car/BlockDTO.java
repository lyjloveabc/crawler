package thor.freedom.crawler.bean.dto.timer.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Thor on 2018/5/4.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BlockDTO {
    private String timePeriod;

    private Integer status;

    private Integer planId;

    private Integer typeCd;

    private Integer classNum;

    private Integer applyNum;

    private Integer leaveNum;

    private Integer confirmNum;

    private String applyUsers;

    private String confirmUsers;

    private Integer applyStatus;

    private Integer hours;

    private Integer modeCd;

    private Integer tempId;
}
