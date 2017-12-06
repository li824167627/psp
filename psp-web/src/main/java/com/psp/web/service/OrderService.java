package com.psp.web.service;

import com.psp.web.controller.res.bean.RCategoryBean;
import com.psp.web.controller.res.bean.RCategoryJSONBean;
import com.psp.web.service.res.PageResult;

public interface OrderService {

	RCategoryJSONBean getAllServices();

	RCategoryJSONBean getCategories();

	PageResult<RCategoryBean> getService(int parentId);

	boolean submitOrder(int cid, String name, String phoneNum, String content);

}
