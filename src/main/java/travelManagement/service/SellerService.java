package travelManagement.service;

import travelManagement.req.SellerQueryReq;
import travelManagement.req.SellerSaveReq;
import travelManagement.resp.SellerQueryResp;
import travelManagement.resp.PageResp;

public interface SellerService {

    PageResp list(SellerQueryReq req);

    void deleteById(Long id);

    void save(SellerSaveReq req);

    SellerQueryResp selectById(Long id);

}
