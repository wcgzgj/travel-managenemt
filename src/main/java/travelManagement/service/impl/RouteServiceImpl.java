package travelManagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import travelManagement.mapper.TabCategoryMapper;
import travelManagement.mapper.TabRouteMapper;
import travelManagement.mapper.TabSellerMapper;
import travelManagement.pojo.TabCategory;
import travelManagement.pojo.TabRoute;
import travelManagement.pojo.TabRouteExample;
import travelManagement.pojo.TabSeller;
import travelManagement.req.RouteQueryReq;
import travelManagement.req.RouteSaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.RouteQueryResp;
import travelManagement.service.RouteService;
import travelManagement.util.CopyUtil;
import travelManagement.util.SnowFlake;
import travelManagement.util.StringUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

/**
 * @ClassName RouteServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@Service
public class RouteServiceImpl implements RouteService {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private TabRouteMapper tabRouteMapper;

    @Resource
    private TabSellerMapper tabSellerMapper;

    @Resource
    private TabCategoryMapper tabCategoryMapper;

    private static final Logger LOG= LoggerFactory.getLogger(RouteServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public PageResp list(RouteQueryReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();
        String name = req.getRname();
        String introduce = req.getRouteintroduce();
        //所属分类
        Long cid = req.getCid();
        //旅游公司
        Long sid = req.getSid();


        TabRouteExample tabRouteExample = new TabRouteExample();
        TabRouteExample.Criteria criteria = tabRouteExample.createCriteria();
        if (!StringUtil.isEmpty(name)) {
            criteria.andRnameLike("%"+name+"%");
        }
        if (!StringUtil.isEmpty(introduce)) {
            criteria.andRouteIntroduceLike("%"+introduce+"%");
        }
        if (cid!=null) {
            criteria.andCidEqualTo(cid);
        }
        if (sid!=null) {
            criteria.andSidEqualTo(sid);
        }

        PageHelper.startPage(pageNum,pageSize);
        List<TabRoute> routes = tabRouteMapper.selectByExample(tabRouteExample);
        LOG.info("查询出出路线列表为:{}",routes);

        PageInfo<TabRoute> pageInfo = new PageInfo<>(routes);

        List<RouteQueryResp> routeQueryResps = CopyUtil.copyList(routes, RouteQueryResp.class);
        for (RouteQueryResp routeQueryResp : routeQueryResps) {
            Long myCid = routeQueryResp.getCid();
            Long mySid = routeQueryResp.getSid();
            TabCategory tabCategory = tabCategoryMapper.selectByPrimaryKey(myCid);
            TabSeller tabSeller = tabSellerMapper.selectByPrimaryKey(mySid);
            routeQueryResp.setCname(tabCategory.getCname());
            routeQueryResp.setSname(tabSeller.getSname());
        }

        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setList(routeQueryResps);

        return pageResp;
    }

    @Override
    public void deleteById(Long id) {
        tabRouteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(RouteSaveReq req) {
        Long id = req.getRid();
        if (id==null) {
            TabRoute copy = CopyUtil.copy(req, TabRoute.class);
            copy.setRid(snowFlake.nextId());
            tabRouteMapper.insert(copy);
        } else {
            TabRoute copy = CopyUtil.copy(req, TabRoute.class);
            tabRouteMapper.updateByPrimaryKeySelective(copy);
        }
    }

    @Override
    public RouteQueryResp selectById(Long id) {
        TabRoute tabRoute = tabRouteMapper.selectByPrimaryKey(id);
        RouteQueryResp copy = CopyUtil.copy(tabRoute, RouteQueryResp.class);
        return copy;
    }
}
