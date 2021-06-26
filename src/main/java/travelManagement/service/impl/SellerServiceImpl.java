package travelManagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import travelManagement.mapper.TabSellerMapper;
import travelManagement.pojo.TabCategory;
import travelManagement.pojo.TabSeller;
import travelManagement.pojo.TabSellerExample;
import travelManagement.req.SellerQueryReq;
import travelManagement.req.SellerSaveReq;
import travelManagement.resp.CategoryQueryResp;
import travelManagement.resp.SellerQueryResp;
import travelManagement.resp.PageResp;
import travelManagement.service.SellerService;
import travelManagement.util.CopyUtil;
import travelManagement.util.SnowFlake;
import travelManagement.util.StringUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName SellerServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private TabSellerMapper tabSellerMapper;

    private static final Logger LOG= LoggerFactory.getLogger(SellerServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public PageResp list(SellerQueryReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();
        String name = req.getSname();
        String address = req.getAddress();


        TabSellerExample tabSellerExample = new TabSellerExample();
        TabSellerExample.Criteria criteria = tabSellerExample.createCriteria();

        if (!StringUtil.isEmpty(name)) {
            criteria.andSnameLike("%"+name+"%");
        }
        if (!StringUtil.isEmpty(address)) {
            criteria.andAddressLike("%"+address+"%");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<TabSeller> sellers = tabSellerMapper.selectByExample(tabSellerExample);
        PageInfo<TabSeller> pageInfo = new PageInfo<>(sellers);

        List<SellerQueryResp> sellerQueryResps = CopyUtil.copyList(sellers, SellerQueryResp.class);

        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setList(sellerQueryResps);

        return pageResp;
    }

    @Override
    public void deleteById(Long id) {
        tabSellerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(SellerSaveReq req) {
        Long id = req.getSid();
        if (id==null) {
            TabSeller copy = CopyUtil.copy(req, TabSeller.class);
            copy.setSid(snowFlake.nextId());
            tabSellerMapper.insert(copy);
        } else {
            TabSeller copy = CopyUtil.copy(req, TabSeller.class);
            tabSellerMapper.updateByPrimaryKeySelective(copy);
        }
    }

    @Override
    public SellerQueryResp selectById(Long id) {
        TabSeller tabSeller = tabSellerMapper.selectByPrimaryKey(id);
        SellerQueryResp copy = CopyUtil.copy(tabSeller, SellerQueryResp.class);
        return copy;
    }


    @Override
    public List<SellerQueryResp> getAll() {
        List<TabSeller> tabCategories = tabSellerMapper.selectByExample(null);
        List<SellerQueryResp> sellerQueryResps = CopyUtil.copyList(tabCategories, SellerQueryResp.class);
        return sellerQueryResps;
    }
}
