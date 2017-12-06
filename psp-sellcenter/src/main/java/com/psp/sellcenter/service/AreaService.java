package com.psp.sellcenter.service;

import com.psp.sellcenter.controller.res.bean.RAreaListBean;
import com.psp.sellcenter.service.res.PageResult;

public interface AreaService {

	PageResult<RAreaListBean> getAreas();

}
