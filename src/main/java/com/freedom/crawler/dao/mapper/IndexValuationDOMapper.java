package com.freedom.crawler.dao.mapper;

import com.freedom.crawler.bean.entity.IndexValuationDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexValuationDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexValuationDO record);

    int insertSelective(IndexValuationDO record);

    IndexValuationDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexValuationDO record);

    int updateByPrimaryKey(IndexValuationDO record);

    @Select("select * from crawler_index_valuation where request_day = #{requestDay}")
    @ResultType(IndexValuationDO.class)
    List<IndexValuationDO> selectByRequestDay(@Param("requestDay") Integer requestDay);
}