package thor.freedom.crawler.biz.timer;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import thor.freedom.crawler.bean.entity.IndexDO;
import thor.freedom.crawler.bean.entity.IndexValuationDO;
import thor.freedom.crawler.dao.mapper.IndexDOMapper;
import thor.freedom.crawler.dao.mapper.IndexValuationDOMapper;
import thor.freedom.crawler.sys.JavaMailUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Thor on 2018/1/19.
 */
@Service
public class IndexValuationWarnTimerBizImpl implements IndexValuationWarnTimerBiz {
    private final static Double PE_PER_THRESHOLD = 0.8;
    private final static Double PB_PER_THRESHOLD = 0.8;
    private final static String SUBJECT_IV_HIGH = "[报警]指数估值高估";

    @Autowired
    private JavaMailUtil javaMailUtil;

    @Autowired
    private IndexDOMapper indexDOMapper;

    @Autowired
    private IndexValuationDOMapper indexValuationDOMapper;

    @Override
    public void indexValuationWarn() {
        List<IndexDO> indexDOS = indexDOMapper.selectFollowed();

        if (!ObjectUtils.isEmpty(indexDOS)) {
            List<IndexValuationDO> indexValuationDOS = indexValuationDOMapper.selectLastRequestDayByIndex(
                    indexDOS.stream().map(IndexDO::getIndexCode).collect(Collectors.toList())
            );

            if (!ObjectUtils.isEmpty(indexValuationDOS)) {
                List<IndexValuationDO> needWarn = Lists.newArrayList();

                for (IndexValuationDO indexValuationDO : indexValuationDOS) {
                    if (Double.valueOf(indexValuationDO.getPePercentile()) >= PE_PER_THRESHOLD || Double.valueOf(indexValuationDO.getPbPercentile()) >= PB_PER_THRESHOLD) {
                        needWarn.add(indexValuationDO);
                    }
                }

                if (!ObjectUtils.isEmpty(needWarn)) {
                    StringBuilder content = new StringBuilder("PER阈值: PE-" + PE_PER_THRESHOLD + ", PB-" + PB_PER_THRESHOLD + "\n\n\n");

                    for (IndexValuationDO iv : needWarn) {
                        content.append(iv.getIndexCode())
                                .append("-")
                                .append(iv.getIndexName())
                                .append(" PER: PE-")
                                .append(iv.getPePercentile())
                                .append(", PB-")
                                .append(iv.getPbPercentile())
                                .append("\n");
                    }

                    javaMailUtil.send(SUBJECT_IV_HIGH, content.toString());
                }
            }
        }
    }
}
