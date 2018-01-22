package thor.freedom.crawler.dao.mapper;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import thor.freedom.crawler.bean.entity.IndexDO;

import java.util.List;

@Component
public interface IndexDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexDO record);

    int insertSelective(IndexDO record);

    IndexDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexDO record);

    int updateByPrimaryKey(IndexDO record);

    @Select("select * from crawler_index where is_followed = 1")
    @ResultType(IndexDO.class)
    List<IndexDO> selectFollowed();
}