package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RAreaListBean;
import com.psp.admin.service.res.PageResult;

public interface AreaService {

	PageResult<RAreaListBean> getAreas();

}
