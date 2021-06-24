package travelManagement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import travelManagement.pojo.TabRoute;
import travelManagement.pojo.TabRouteExample;

public interface TabRouteMapper {
    long countByExample(TabRouteExample example);

    int deleteByExample(TabRouteExample example);

    int deleteByPrimaryKey(Long rid);

    int insert(TabRoute record);

    int insertSelective(TabRoute record);

    List<TabRoute> selectByExample(TabRouteExample example);

    TabRoute selectByPrimaryKey(Long rid);

    int updateByExampleSelective(@Param("record") TabRoute record, @Param("example") TabRouteExample example);

    int updateByExample(@Param("record") TabRoute record, @Param("example") TabRouteExample example);

    int updateByPrimaryKeySelective(TabRoute record);

    int updateByPrimaryKey(TabRoute record);
}