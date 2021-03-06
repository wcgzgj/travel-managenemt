package travelManagement.service;

import travelManagement.req.CategoryQueryReq;
import travelManagement.req.CategorySaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.CategoryQueryResp;

import java.util.List;

public interface CategoryService {

    PageResp list(CategoryQueryReq req);

    void deleteById(Long id);

    void save(CategorySaveReq req);

    CategoryQueryResp selectById(Long id);

    List<CategoryQueryResp> getAll();

}
