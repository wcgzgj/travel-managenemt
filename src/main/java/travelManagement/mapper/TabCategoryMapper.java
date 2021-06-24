package travelManagement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import travelManagement.pojo.TabCategory;
import travelManagement.pojo.TabCategoryExample;

public interface TabCategoryMapper {
    long countByExample(TabCategoryExample example);

    int deleteByExample(TabCategoryExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(TabCategory record);

    int insertSelective(TabCategory record);

    List<TabCategory> selectByExample(TabCategoryExample example);

    TabCategory selectByPrimaryKey(Long cid);

    int updateByExampleSelective(@Param("record") TabCategory record, @Param("example") TabCategoryExample example);

    int updateByExample(@Param("record") TabCategory record, @Param("example") TabCategoryExample example);

    int updateByPrimaryKeySelective(TabCategory record);

    int updateByPrimaryKey(TabCategory record);
}