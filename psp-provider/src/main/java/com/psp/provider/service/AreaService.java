package com.psp.provider.service;

import com.psp.provider.controller.res.bean.RAreaListBean;
import com.psp.provider.service.impl.res.PageResult;

public interface AreaService {

	PageResult<RAreaListBean> getAreas();

}
