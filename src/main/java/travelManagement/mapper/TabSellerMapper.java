package travelManagement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import travelManagement.pojo.TabSeller;
import travelManagement.pojo.TabSellerExample;

public interface TabSellerMapper {
    long countByExample(TabSellerExample example);

    int deleteByExample(TabSellerExample example);

    int deleteByPrimaryKey(Long sid);

    int insert(TabSeller record);

    int insertSelective(TabSeller record);

    List<TabSeller> selectByExample(TabSellerExample example);

    TabSeller selectByPrimaryKey(Long sid);

    int updateByExampleSelective(@Param("record") TabSeller record, @Param("example") TabSellerExample example);

    int updateByExample(@Param("record") TabSeller record, @Param("example") TabSellerExample example);

    int updateByPrimaryKeySelective(TabSeller record);

    int updateByPrimaryKey(TabSeller record);
}