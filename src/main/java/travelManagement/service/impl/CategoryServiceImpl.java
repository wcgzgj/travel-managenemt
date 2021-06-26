package travelManagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import travelManagement.mapper.TabCategoryMapper;
import travelManagement.pojo.TabCategory;
import travelManagement.pojo.TabCategoryExample;
import travelManagement.req.CategoryQueryReq;
import travelManagement.req.CategorySaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.CategoryQueryResp;
import travelManagement.service.CategoryService;
import travelManagement.util.CopyUtil;
import travelManagement.util.SnowFlake;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private TabCategoryMapper tabCategoryMapper;

    private static final Logger LOG= LoggerFactory.getLogger(CategoryServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public PageResp list(CategoryQueryReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();

        TabCategoryExample tabCategoryExample = new TabCategoryExample();
        TabCategoryExample.Criteria criteria = tabCategoryExample.createCriteria();

        PageHelper.startPage(pageNum,pageSize);
        List<TabCategory> categorys = tabCategoryMapper.selectByExample(tabCategoryExample);
        PageInfo<TabCategory> pageInfo = new PageInfo<>(categorys);

        List<CategoryQueryResp> categoryQueryResps = CopyUtil.copyList(categorys, CategoryQueryResp.class);

        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setList(categoryQueryResps);

        return pageResp;
    }

    @Override
    public void deleteById(Long id) {
        tabCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(CategorySaveReq req) {
        Long id = req.getCid();
        if (id==null) {
            TabCategory copy = CopyUtil.copy(req, TabCategory.class);
            copy.setCid(snowFlake.nextId());
            tabCategoryMapper.insert(copy);
        } else {
            TabCategory copy = CopyUtil.copy(req, TabCategory.class);
            tabCategoryMapper.updateByPrimaryKeySelective(copy);
        }
    }

    @Override
    public CategoryQueryResp selectById(Long id) {
        TabCategory tabCategory = tabCategoryMapper.selectByPrimaryKey(id);
        CategoryQueryResp copy = CopyUtil.copy(tabCategory, CategoryQueryResp.class);
        return copy;
    }

    @Override
    public List<CategoryQueryResp> getAll() {
        List<TabCategory> tabCategories = tabCategoryMapper.selectByExample(null);
        List<CategoryQueryResp> categoryQueryResps = CopyUtil.copyList(tabCategories, CategoryQueryResp.class);
        return categoryQueryResps;
    }
}
