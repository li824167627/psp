package com.psp.sellcenter.service;

import com.psp.sellcenter.controller.res.bean.RUserNewsBean;
import com.psp.sellcenter.service.res.PageResult;

public interface UserNewsService {

	PageResult<RUserNewsBean> getUserNews(String sid, int page, int pageSize, int stype, String key, String uid);

	boolean add(String sid, String uid, String label, String content);

}
