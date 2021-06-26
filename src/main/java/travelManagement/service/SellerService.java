package travelManagement.service;

import travelManagement.req.SellerQueryReq;
import travelManagement.req.SellerSaveReq;
import travelManagement.resp.CategoryQueryResp;
import travelManagement.resp.SellerQueryResp;
import travelManagement.resp.PageResp;

import java.util.List;

public interface SellerService {

    PageResp list(SellerQueryReq req);

    void deleteById(Long id);

    void save(SellerSaveReq req);

    SellerQueryResp selectById(Long id);

    List<SellerQueryResp> getAll();
}
