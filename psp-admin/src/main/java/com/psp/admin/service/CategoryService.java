package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RCategoryBean;
import com.psp.admin.controller.res.bean.RCategoryJSONBean;
import com.psp.admin.service.res.PageResult;

public interface CategoryService {

	RCategoryJSONBean getAllServices();

	RCategoryJSONBean getCategories();

	PageResult<RCategoryBean> getService(int parentId);

	boolean addService(String aid, int parentId, String name, int sort, int isService);

	boolean editService(String aid, int cid, int parentId, String name, int sort, int isService);
	

}
