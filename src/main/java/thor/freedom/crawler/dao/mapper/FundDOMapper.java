package thor.freedom.crawler.dao.mapper;

import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.entity.FundDO;

@Component
public interface FundDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundDO record);

    int insertSelective(FundDO record);

    FundDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundDO record);

    int updateByPrimaryKey(FundDO record);
}