package travelManagement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import travelManagement.pojo.TabUser;
import travelManagement.pojo.TabUserExample;

public interface TabUserMapper {
    long countByExample(TabUserExample example);

    int deleteByExample(TabUserExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(TabUser record);

    int insertSelective(TabUser record);

    List<TabUser> selectByExample(TabUserExample example);

    TabUser selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") TabUser record, @Param("example") TabUserExample example);

    int updateByExample(@Param("record") TabUser record, @Param("example") TabUserExample example);

    int updateByPrimaryKeySelective(TabUser record);

    int updateByPrimaryKey(TabUser record);
}