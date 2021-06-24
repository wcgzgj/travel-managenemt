package travelManagement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import travelManagement.pojo.TabRouteImg;
import travelManagement.pojo.TabRouteImgExample;

public interface TabRouteImgMapper {
    long countByExample(TabRouteImgExample example);

    int deleteByExample(TabRouteImgExample example);

    int deleteByPrimaryKey(Long rgid);

    int insert(TabRouteImg record);

    int insertSelective(TabRouteImg record);

    List<TabRouteImg> selectByExample(TabRouteImgExample example);

    TabRouteImg selectByPrimaryKey(Long rgid);

    int updateByExampleSelective(@Param("record") TabRouteImg record, @Param("example") TabRouteImgExample example);

    int updateByExample(@Param("record") TabRouteImg record, @Param("example") TabRouteImgExample example);

    int updateByPrimaryKeySelective(TabRouteImg record);

    int updateByPrimaryKey(TabRouteImg record);
}